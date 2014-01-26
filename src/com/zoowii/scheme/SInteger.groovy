package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SInteger extends SObject {
    private Integer value = 0

    public SInteger(int n) {
        this.value = n;
    }

    public static SInteger create(int n) {
        return new SInteger(n);
    }

    public Integer toJavaInt() {
        return value;
    }
}
