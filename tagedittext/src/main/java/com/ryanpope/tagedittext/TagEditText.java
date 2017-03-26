package com.ryanpope.tagedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.ryanpope.tagedittext.tag.Tag;
import com.ryanpope.tagedittext.tag.TagViewComposer;
import com.ryanpope.tagedittext.tag.views.TagSpan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class TagEditText extends AutoCompleteTextView {
    private TagViewComposer mTagViewComposer;
    private boolean mShouldIgnoreEvents;

    private static final String SEPARATOR = "\\s+";

    public TagEditText(final Context context) {
        this(context, null);
    }

    public TagEditText(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(final Context context, final AttributeSet attributeSet, final int defStyleAttr) {
        mTagViewComposer = new TagViewComposer(context, attributeSet, defStyleAttr);

        configureAttributes();
    }

    private void configureAttributes() {
        setFocusableInTouchMode(true);
        setMovementMethod(LinkMovementMethod.getInstance());
        setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_FLAG_MULTI_LINE
                | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

    }

    private void refreshSpans() {
        final List<String> tagsSeparatedBySeparator = getTagsSeparatedBySeparator();
        final List<Tag> tags = buildTagSpansFromSeparatedWords(tagsSeparatedBySeparator);

        addTagViewsToEditText(tags);
    }

    private List<String> getTagsSeparatedBySeparator() {
        if (isEmpty(getText())) {
            return new ArrayList<>();
        }

        return splitAndRemoveDuplicates();
    }

    private List<String> splitAndRemoveDuplicates() {
        final String[] splitValues = String.valueOf(getText()).split(SEPARATOR);

        return Arrays.asList(splitValues);
    }

    private List<Tag> buildTagSpansFromSeparatedWords(final List<String> wordList) {
        final String text = getText().toString();
        final List<Tag> tagList = new ArrayList<>();
        int indexToStartFrom = 0;

        for (final String word : wordList) {
            final int startIndex = text.indexOf(word, indexToStartFrom);
            final int endIndex = startIndex + word.length();

            indexToStartFrom = endIndex;

            final Tag newTag = new Tag.Builder()
                    .withWord(word)
                    .withStartIndex(startIndex)
                    .withEndIndex(endIndex)
                    .build();

            tagList.add(newTag);
        }

        return tagList;
    }

    private void addTagViewsToEditText(final List<Tag> tags) {
        for (final Tag tag : tags) {
            if (tag.getWord().trim().isEmpty()) {
                continue;
            }

            setSpans(tag);
        }

        ensureTrailingWhitespace();
    }

    private void setSpans(final Tag tag) {
        final Drawable drawable = mTagViewComposer.createTagSpanForTag(tag);
        final TagSpan tagSpan = new TagSpan(drawable, tag.getWord());
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(final View widget) {
                removeSpan(tag);
            }
        };

        getText().setSpan(
                tagSpan,
                tag.getStartIndex(),
                tag.getEndIndex(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        getText().setSpan(
                clickableSpan,
                tag.getStartIndex(),
                tag.getEndIndex(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void removeSpan(final Tag span) {
        clearSpansAndRemoveTag(span);

        refreshSpans();
    }

    private void clearSpansAndRemoveTag(final Tag span) {
        setShouldIgnoreEvents(true);

        final Editable cachedText = Editable.Factory.getInstance().newEditable(getText());
        cachedText.clearSpans();
        getText().clear();
        cachedText.delete(span.getStartIndex(), span.getEndIndex() + 1);
        setText(cachedText);

        setShouldIgnoreEvents(false);
    }

    private void setShouldIgnoreEvents(final boolean shouldRefreshSpans) {
        mShouldIgnoreEvents = shouldRefreshSpans;
    }

    private boolean shouldIgnoreEvents() {
        return mShouldIgnoreEvents;
    }

    @Override
    public void onTextChanged(final CharSequence text, final int start, final int lengthBefore, final int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (shouldNotRefresh(text, lengthBefore, lengthAfter)) {
            return;
        }

        refreshSpans();
    }

    private boolean shouldNotRefresh(final CharSequence text, final int lengthBefore, final int lengthAfter) {
        return !hasWhitespace(text.toString())
                && !isBeingCreated(lengthBefore, lengthAfter)
                && !isRemovingTag(lengthBefore, lengthAfter)
                && !shouldIgnoreEvents();
    }

    private boolean isBeingCreated(final int lengthBefore, final int lengthAfter) {
        return !hasSpans() && !isManualEntry(lengthBefore, lengthAfter);
    }

    private boolean isManualEntry(final int lengthBefore, final int lengthAfter) {
        return lengthAfter - lengthBefore == 1;
    }

    private void ensureTrailingWhitespace() {
        if (hasWhitespace(getText().toString())) {
            return;
        }

        append(" ");
    }

    private boolean isRemovingTag(final int lengthBefore, final int lengthAfter) {
        return lengthBefore > lengthAfter;
    }

    private boolean hasWhitespace(final String text) {
        return !text.isEmpty()
                && Character.isWhitespace(text.charAt(text.length() - 1));
    }

    private boolean hasSpans() {
        return getText().getSpans(0, length(), TagSpan.class).length > 0;
    }

    @Override
    protected void onSelectionChanged(final int selStart, final int selEnd) {
        super.onSelectionChanged(selStart, selEnd);

        setSelection(length());
    }
}
