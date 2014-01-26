package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SchemeState {
    public SEnv env
    public SContinuation cont
    public SObject last // 最后的值
    public SchemeState(SEnv env, SContinuation cont, SObject last) {
        this.env = env
        this.cont = cont
        this.last = last
    }
}
