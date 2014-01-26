package com.zoowii.scheme

/**
 * Created by zoowii on 14-1-26.
 */
class SList extends SObject {
    private List<SObject> value = new LinkedList<>()

    public SList() {

    }

    public int size() {
        return value.size()
    }

    public SObject get(int i) {
        return value.get(i)
    }

    /**
     * 修改当前对象，插入对象
     * @param index
     * @param val
     */
    public void insertToCurrent(int index, SObject val) {
        this.value.add(index, val)
    }

    public SList setValue(int index, SObject val) {
        // TODO: 写时拷贝
        def other = (SList) clone()
        def otherList = other.value
        otherList.set(index, val)
        return otherList
    }

    public SList subList(int start) {
        return subList(start, this.size())
    }

    public SList subList(int start, int end) {
        SList subList = new SList()
        for (int i = start; i < end; ++i) {
            subList.value.add(this.get(i))
        }
        return subList
    }

    public List<SObject> toJavaList() {
        return this.value
    }

    public Object clone() {
        // TODO: 生成一份写时拷贝的引用拷贝
        def other = new SList()
        for (int i = 0; i < this.size(); ++i) {
            other.value.add(this.get(i))
        }
        return other
    }

    public static SList create(SObject... items) {
        SList obj = new SList()
        List<SObject> list = new LinkedList<>()
        list.addAll(items)
        obj.replaceAllList(list)
        return obj
    }

    public static SList create(List<SObject> items) {
        SList obj = new SList()
        obj.value.addAll(items)
        return obj
    }

    /**
     * 替换整个列表内容，破坏性巨大，慎用
     * @param list
     */
    public void replaceAllList(List<SObject> list) {
        this.value = list
    }

}
