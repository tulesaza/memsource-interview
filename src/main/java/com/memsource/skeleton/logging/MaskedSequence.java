package com.memsource.skeleton.logging;

import java.util.NoSuchElementException;

class MaskedSequence implements CharSequence {

    private final int length;

    public MaskedSequence(int length) {
        this.length = length;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        if (index >= length) {
            throw new NoSuchElementException("Index: " + index);
        }
        return '*';
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if ((end - start) < 0) {
            throw new IllegalArgumentException("Start is greater than end [" + start + ", " + end + "]");
        }
        return new MaskedSequence(end - start);
    }
}
