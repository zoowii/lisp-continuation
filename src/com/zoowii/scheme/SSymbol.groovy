package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SSymbol extends SObject {
    private String value;

    public SSymbol(String val) {
        this.value = val
    }

    public static SSymbol create(String val) {
        return new SSymbol(val)
    }

    public String toString() {
        return value
    }

    public SObject realize(SEnv env) {
        def key = this.value
        return env.get(key)
    }
}
