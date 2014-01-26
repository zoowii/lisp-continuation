package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SBoolean extends SObject {
    private boolean value
    
    public static SBoolean TRUE = new SBoolean(true)
    public static SBoolean FALSE = new SBoolean(false)

    private SBoolean(boolean val) {
        this.value = val
    }

    def static SBoolean create(boolean val) {
        return val ? TRUE : FALSE
    }
}
