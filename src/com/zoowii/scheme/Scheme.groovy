package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class Scheme {
    public SchemeState runStateMachine(SchemeState state) {
        while (!state.cont.eof()) {
            SObject form = state.cont.next()

            if (form instanceof SList) {
                SList formAsList = (SList) form
                SObject firstItem = formAsList.get(0)
                SList params = formAsList.subList(1)
                // TODO: 如果firstItem是宏，则params不需要realize，否则需要
                // 目前所有参数都realize一遍
                List<SObject> paramsRealized = new ArrayList<>()
                for (int i = 0; i < params.size(); ++i) {
                    paramsRealized.add(params.get(i).realize(state.env))
                }
                params = SList.create(paramsRealized)
                if (firstItem instanceof SProc) {
                    SProc proc = (SProc) firstItem
                    state = proc.run(state, params)
                } else if (firstItem instanceof SContinuation) {
                    SContinuation cont = (SContinuation) firstItem
                    cont = cont.acceptParamsWhenCall(params)
                    state.cont = cont
                } else {
                    throw new RuntimeException("不可被调用的元素" + firstItem)
                }
            } else {
                state.last = form
            }
        }
        return null;
    }

    public static SEnv getInitEnv() {
        def env = new SEnv()
        env.put("call/cc", SProc.CALLCC)
        env.put("define", SProc.DEFINE)
        env.put("display", SProc.DISPLAY)
        env.put("newline", SProc.NEWLINE)
        env.put("nil", SObject.NIL)
        env.put("true", SBoolean.TRUE)
        env.put("false", SBoolean.FALSE)
        env.put("+", SProc.ADD)
        return env
    }

    public SObject run(List<SObject> code) {
        return run(Scheme.getInitEnv(), SContinuation.loadFromList(code))
    }

    public SObject run(SEnv env, SContinuation cont) {
        SchemeState state = new SchemeState(env, cont, SObject.NIL)
        state = runStateMachine(state)
        return state.last
    }
}
