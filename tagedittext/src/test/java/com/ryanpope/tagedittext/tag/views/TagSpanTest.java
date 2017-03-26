package com.ryanpope.tagedittext.tag.views;

import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class TagSpanTest {
    private static final String TEST_SOURCE = "testSource";

    @Mock Drawable mDrawable;

    private TagSpan mTagSpan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mTagSpan = new TagSpan(mDrawable, TEST_SOURCE);
    }

    @Test
    public void should_create_span_of_type_imagespan() {
        // Given // When // Then
        assertEquals(mTagSpan.getClass().getSuperclass(), ImageSpan.class);
    }
}
