package com.ryanpope.tagedittext.tag;

public class Tag {
    private final String mWord;
    private final int mStartIndex;
    private final int mEndIndex;

    public static class Builder {
        private String mWord;
        private int mStartIndex;
        private int mEndIndex;

        public Builder withWord(final String word) {
            mWord = word;
            return this;
        }

        public Builder withStartIndex(final int startIndex) {
            mStartIndex = startIndex;
            return this;
        }

        public Builder withEndIndex(final int endIndex) {
            mEndIndex = endIndex;
            return this;
        }

        public Tag build() {
            return new Tag(this);
        }
    }

    private Tag(final Builder builder) {
        mWord = builder.mWord;
        mStartIndex = builder.mStartIndex;
        mEndIndex = builder.mEndIndex;
    }

    public String getWord() {
        return mWord;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public int getEndIndex() {
        return mEndIndex;
    }
}
