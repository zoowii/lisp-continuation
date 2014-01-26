package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SString extends SObject {
    private String value;

    public SString(String val) {
        this.value = val
    }

    def static SString create(String val) {
        return new SString(val)
    }

    public String toString() {
        return value
    }
}
