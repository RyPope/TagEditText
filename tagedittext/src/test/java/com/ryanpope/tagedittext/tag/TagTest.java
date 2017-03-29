package com.ryanpope.tagedittext.tag;

import com.ryanpope.tagedittext.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TagTest {
    private static final String TEST_WORD = "testWord";
    private static final int TEST_START_INDEX = 0;
    private static final int TEST_END_INDEX = 10;

    private Tag mTag;

    @Before
    public void setup() {
        mTag = setupTag();
    }

    @Test
    public void should_create_tag_with_given_word() {
        // Given // When // Then
        assertEquals(TEST_WORD, mTag.getWord());
    }

    @Test
    public void should_create_tag_with_given_start_index() {
        // Given // When // Then
        assertEquals(TEST_START_INDEX, mTag.getStartIndex());
    }

    @Test
    public void should_create_tag_with_given_end_index() {
        // Given // When // Then
        assertEquals(TEST_END_INDEX, mTag.getEndIndex());
    }

    private Tag setupTag() {
        return new Tag.Builder()
                .withWord(TEST_WORD)
                .withStartIndex(TEST_START_INDEX)
                .withEndIndex(TEST_END_INDEX)
                .build();
    }
}
