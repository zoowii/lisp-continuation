package com.zoowii.scheme;

/**
 * Scheme对象，所有scheme的类型都继承它
 * Created by zoowii on 14-1-26.
 */
public class SObject {
    public static SObject NIL = new SObject()
//    /**
//     * cont接收参数的位置，如果cont中某个位置的值是这个，则表示这里需要一个参数传入，
//     * 只计算指令序列最外层包含的这个对象的位置
//     */
//    public static SObject CONTINUATION_ACCEPT_PLACE = new SObject()

    private boolean continuationAccepting = false;

    public void setIsContinuationAcception() {
        // TODO: 写时拷贝，即时是一个字符串，数字，如果被做了这个修改，其他同源对象不应该改变
        continuationAccepting = true
    }

    public boolean isContinuationAcception() {
        return continuationAccepting
    }

    public boolean toBoolValue() {
        return this != NIL && this != SBoolean.FALSE
    }

    public SObject realize(SEnv env) {
        // 获取实际值，如果是Symbol，获取Symbol在env中的值，否则，返回本身
        return this
    }
}
