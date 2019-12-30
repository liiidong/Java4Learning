package com.enough.map;

/**
 * @program: MyMap
 * @description: 实现MyMap内部接口
 * @author: lidong
 * @create: 2019/12/30
 */
public class Entry<K, V> implements MyMap.Entry <K, V> {

    public K key;
    public V value;
    /**
     * 下一个对象
     */
    public Entry <K, V> next;

    public Entry() {
    }

    public Entry(K key, V value, Entry <K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}
