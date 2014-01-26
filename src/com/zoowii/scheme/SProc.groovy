package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SProc extends SObject {
    public List<String> params = new ArrayList<>()

    def SchemeState run(SchemeState state, SList params) {
        state.last = NIL
        return state
    }

    /**
     * call/cc or call-with-current-continuation
     */
    public static SProc CALLCC = new SProc() {
        def SchemeState run(SchemeState state, SList params) {
            def proc = params.get(0).realize(state.env)
            def cont = state.cont
            // TODO: 创建一个新cont，这个cont是执行proc并返回结果到调用call/cc处
            def newCont = (SContinuation) cont.clone()

            // 将newCont的原位置设置为等待参数
            newCont.setCurrentPosIsAccepting()
            newCont.insertToCurrent(0, SList.create(proc, cont))
            return new SchemeState(state.env, newCont, NIL)
        }
    }

    /**
     * define
     */
    public static SProc DEFINE = new SProc() {
        def SchemeState run(SchemeState state, SList params) {
            def symbol = params.get(0)
            def val = params.get(1).realize(state.env)
            state.env.putToRoot(symbol.toString(), val)
            state.last = val
            return state
        }
    }

    /**
     * set!
     */
    public static SProc SET = new SProc() {
        def SchemeState run(SchemeState state, SList params) {
            def symbol = params.get(0)
            def val = params.get(1).realize(state.env)
            state.env.putToExist(symbol.toString(), val)
            state.last = val
            return state
        }
    }

    public static SProc DISPLAY = new SProc() {
        def SchemeState run(SchemeState state, SList params) {
            for (int i = 0; i < params.size(); ++i) {
                print(params.get(i))
            }
            state.last = NIL
            return state
        }
    }
    public static SProc NEWLINE = new SProc() {
        def SchemeState run(SchemeState state, SList params) {
            print("\n")
            state.last = NIL
            return state
        }
    }
    public static SProc ADD = new SProc() {
        def SchemeState run(SchemeState state, SList params) {
            int sum = 0
            for (int i = 0; i < params.size(); ++i) {
                def p = (SInteger) params.get(i)
                sum += p.toJavaInt()
            }
            state.last = SInteger.create(sum)
            return state
        }
    }
}
