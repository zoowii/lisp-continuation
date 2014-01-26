package com.zoowii.scheme

/**
 * TODO: 写时拷贝
 * Created by zoowii on 14-1-26.
 */
class SContinuation extends SObject {
    def SList commands // 指令序列
    def SList currentList // 当前处在哪个指令
    def int currentPos = -1 // 当前处在list的哪个位置

    public static SContinuation loadFromList(SList codeList) {
        SContinuation cont = new SContinuation()
        cont.commands = codeList
        cont.currentList = cont.commands
        cont.currentPos = -1
        return cont
    }

    def SObject next() {
        // 找到cont中下一个要执行的元素
        // (f (g 123))的情况下，下一个要执行的是(g 123)
        // TODO
        // TODO: 将cont指向下一个位置
        return null
    }

    /**
     * TODO
     * cont被调用时接受一些params，用这些params替换当前指令序列中的isContinuationAcception()为true的元素，按出现顺序替换
     * @param params
     * @return
     */
    def SContinuation acceptParamsWhenCall(SList params) {
        SContinuation cont = this.clone()
        // TODO
        return cont
    }

    /**
     * 修改当前cont，在commands前面
     * 但是cont的当前位置还是指向原来那个scheme对象
     * @param index
     * @param val
     * @return
     */
    def SObject insertToCurrent(int index, SObject val) {
        this.commands.insertToCurrent(index, val)
        return this
    }

    /**
     * 修改当前cont，插入值，并且修改当前指针指向新插入的值
     * @param index
     * @param val
     * @return
     */
    def SObject insertToCurrentAndChangePos(int index, SObject val) {
        SContinuation cont = (SContinuation) this.insertToCurrent(index, val)
        if (val instanceof SList) {
            cont.currentList = val
            cont.currentPos = -1
        } else {
            cont.currentList = cont.commands
            cont.currentPos = -1
        }
    }

    /**
     * cont的指令序列的当前指针以一行指令为单位向前移动n次
     * TODO: 如果当前所在的指令抵达所在词法作用域的顶部，则跑到外面一层
     * @param n
     */
    def void goPrevListSteps(int n) {
        // TODO
    }

    def SList nextToCall() {
        // 下一个调用
        // TODO: 目前没有使用
        return null
    }

    def boolean eof() {
        // TODO
        return true
    }

    /**
     * 将cont的当前位置设置为等待值
     */
    def void setCurrentPosIsAccepting() {
        SObject currentObj = currentList
        if (currentObj == null) {
            // TODO
            return
        }
        if (currentObj instanceof SList) {
            def currentObjAsList = (SList) currentObj
            if (currentObjAsList.size() < currentPos + 1 || currentPos < 0) {
                // TODO
                return
            }
            currentObjAsList.get(currentPos).setIsContinuationAcception()
//            currentObjAsList.setValue(currentPos, CONTINUATION_ACCEPT_PLACE)
        } else {
            currentList.setIsContinuationAcception()
//            commands.setValue(currentListIndex, CONTINUATION_ACCEPT_PLACE)
        }
    }

    def Object clone() {
        // TODO: 写时拷贝，只clone引用
        SContinuation other = new SContinuation()
        // TODO: 拷贝时，新的currentList也需要指向commands中的新拷贝的
        // 一个方法是使用一个唯一ID记录每一个对象，并且随时可以从池中找到它
        // 另一个方法还是写时拷贝
//        other.commands = (SList) this.commands.clone()
        other.commands = this.commands
        other.currentList = this.currentList
        other.currentPos = this.currentPos
        return other
    }

}
