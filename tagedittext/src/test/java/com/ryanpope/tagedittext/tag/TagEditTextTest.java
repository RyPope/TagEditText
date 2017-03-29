package com.ryanpope.tagedittext.tag;

import android.text.style.ClickableSpan;
import android.widget.LinearLayout;

import com.ryanpope.tagedittext.BuildConfig;
import com.ryanpope.tagedittext.TagEditText;
import com.ryanpope.tagedittext.tag.views.TagSpan;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static android.view.View.VISIBLE;
import static com.ryanpope.tagedittext.tag.TagEditTextTestActivity.TEST_LAYOUT_ID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TagEditTextTest {
    private TagEditTextTestActivity mTagEditTextTestActivity;
    private TagEditText mTagEditText;

    @Before
    public void setup() {
        mTagEditTextTestActivity = Robolectric.setupActivity(TagEditTextTestActivity.class);
        addTagEditTextToActivity();
    }

    @Test
    public void should_add_tag_view_to_layout() {
        // Given // When // Then
        assertSame(VISIBLE, mTagEditText.getVisibility());
    }

    @Test
    public void should_convert_words_into_tags() {
        // Given
        final String testValue = "This is a test value.";
        final int expectedValue = testValue.split(" ").length;

        // When
        mTagEditText.setText(testValue);

        // Then
        assertEquals(expectedValue, getNumberOfTags());
    }

    @Test
    public void should_remove_tag_on_click() {
        // Given
        final String testValue = "This is a test value.";
        final String textToRemove = "test";
        final String expectedValue = "This is a value.";

        // When
        mTagEditText.setText(testValue);
        final ClickableSpan clickableSpan = (ClickableSpan) findSpanForString(textToRemove, ClickableSpan.class);
        clickableSpan.onClick(null);

        // Then
        assertEquals(expectedValue, mTagEditText.getText().toString().trim());
    }

    @Test
    public void should_add_tag_for_word() {
        // Given
        final String testValue = "This is a test";
        final String textToAdd = "value. ";
        final int expectedValue = "This is a test value.".split(" ").length;

        // When
        mTagEditText.setText(testValue);
        mTagEditText.append(textToAdd);

        // Then
        assertEquals(expectedValue, getNumberOfTags());
    }

    private Object findSpanForString(final String textToRemove, final Class<?> clazz) {
        final int startIndex = mTagEditText.getText().toString().indexOf(textToRemove);

        final Object[] tagSpans = mTagEditText.getText().getSpans(
                startIndex,
                startIndex + textToRemove.length(),
                clazz
        );

        assertNotNull(tagSpans[0]);
        return tagSpans[0];
    }

    private int getNumberOfTags() {
        return mTagEditText.getText().getSpans(
                0,
                mTagEditText.getText().length(),
                TagSpan.class
        ).length;
    }

    @SuppressWarnings("ResourceType")
    private void addTagEditTextToActivity() {
        mTagEditText = new TagEditText(RuntimeEnvironment.application);

        final LinearLayout linearLayout = (LinearLayout) mTagEditTextTestActivity
                .findViewById(TEST_LAYOUT_ID);

        linearLayout.addView(mTagEditText);
    }
}
