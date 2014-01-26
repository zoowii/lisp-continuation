package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SEnv extends SObject {
    private SEnv parent = null
    private HashMap<String, SObject> values = new HashMap<>()

    def SEnv getParent() {
        return parent
    }

    def put(String key, SObject val) {
        values.put(key, val)
    }

    def putToRoot(String key, SObject val) {
        getRoot().put(key, val)
    }

    /**
     * 下钻一层，调用函数时使用
     * @return
     */
    def SEnv down() {
        SEnv env = new SEnv()
        env.parent = this
        return env
    }

    /**
     * 上钻一层，函数调用结束时使用
     * @return
     */
    def SEnv up() {
        return parent
    }

    def putToExist(String key, SObject val) {
        if (containsKeyInCurrent(key)) {
            put(key, val)
        } else {
            if (parent == null) {
                // TODO: throw exception if not exist
                return
            } else {
                parent.putToExist(key, val)
            }
        }
    }

    def SObject getInCurrent(String key) {
        return values.get(key, null)
    }

    public boolean containsKeyInCurrent(String key) {
        return getInCurrent(key) != null
    }

    def get(String key) {
        def val = getInCurrent(key)
        if (val != null) {
            return val
        }
        if (parent == null) {
            return null
        } else {
            return parent.get(key)
        }
    }

    public boolean containsKey(String key) {
        return get(key) != null
    }

    def SEnv getRoot() {
        return parent == null ? this : parent.getRoot()
    }
}
