package com.enough.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: MyMap
 * @description:
 * @author: lidong
 * @create: 2019/12/30
 */
public class MyHashMap<K, V> implements MyMap <K, V> {

    // 数组默认初始化长度:二进制，左移移，考虑正负数
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    // 默认阈值比例
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 初始化大小
     */
    private int defaultInitSize;
    /**
     * 扩容的阈值
     */
    private float defaultLoadFactor;

    /**
     * map中的entry数量
     */
    private int entryUserSize;
    /**
     * entry数组
     */
    private com.enough.map.Entry <K, V>[] table = null;

    private Set <K> keySet;

    /**
     * 【门面模式】，
     * 两个构造方法对外暴露2个【门面】，其实指向的是同一个构造方法
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor) {
        if (defaultInitialCapacity < 0) {
            throw new IllegalArgumentException("初始化大小不能小于0");
        }
        if (defaultLoadFactor < 0) {
            throw new IllegalArgumentException("给定阈值比例不能小于0");
        }
        this.defaultInitSize = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;

        // 初始化entry数组大小
        this.table = new com.enough.map.Entry[defaultInitialCapacity];
    }

    @Override
    public V put(K k, V v) {
        V oldValue = null;
        // 判断是否需要扩容
        // 扩容完毕，重新散列
        if (entryUserSize >= defaultLoadFactor * defaultInitSize) {
            // 2倍扩容
            resize(2 * defaultInitSize);
        }
        // 得到hash值，计算出数组中位置
        // & 位运算符，表示按位与操作，如果hash没取到，index=默认大小的最后一个位置
        int index = hash(k) & defaultInitSize - 1;
        if (table[index] == null) {
            table[index] = new com.enough.map.Entry <>(k, v, null);
            // i++先做别的事，再自己加1，++i先自己加1，再做别的事情
            ++entryUserSize;
        } else {
            // 遍历单链表
            com.enough.map.Entry <K, V> entry = table[index];
            com.enough.map.Entry <K, V> e = entry;
            while (e != null) {
                if (k == e.getKey() || k.equals(e.getKey())) {
                    // 获取返回值
                    oldValue = e.getValue();
                    // 更新数据
                    e.value = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] = new com.enough.map.Entry <>(k, v, entry);
            ++entryUserSize;
        }
        return oldValue;
    }

    /**
     * 这里取hashMap的位运算代码，，，
     * 要想散列均匀，就得进行二进制的位运算！
     *
     * @param k
     * @return
     */
    private int hash(K k) {
        int hashCode = k.hashCode();
        // 符号为（^）,其主要是对两个操作数进行位的异或运算，相同取0，相反取1。即两操作数相同时，互相抵消。
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    /**
     * 扩容
     *
     * @param size
     */
    private void resize(int size) {
        com.enough.map.Entry[] newTable = new com.enough.map.Entry[size];
        defaultInitSize = size;
        entryUserSize = 0;
        rehash(newTable);
    }

    /**
     * 扩容后重新填充数据
     * 这里可以发现，每次扩容都是完全重新填充数据，频繁进行resize和rehash操作，会影响性能
     *
     * @param newTable
     */
    private void rehash(com.enough.map.Entry <K, V>[] newTable) {
        List <com.enough.map.Entry <K, V>> entryList = new ArrayList <>();
        for (com.enough.map.Entry <K, V> entry : table) {
            if (entry != null) {
                do {
                    // 拿到所有的对象的集合
                    entryList.add(entry);
                    entry = entry.next;
                } while (entry != null);
            }
        }

        // 重新赋值
        if (newTable.length > 0) {
            table = newTable;
        }

        // 此时进行put操作，table对象已经是新的长度的数组了，且其值没空
        for (com.enough.map.Entry <K, V> entry : entryList) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V get(K k) {
        int index = hash(k) & (defaultInitSize - 1);
        if (table[index] == null) {
            return null;
        } else {
            com.enough.map.Entry <K, V> entry = table[index];
            do {
                if (k == entry.getKey() || k.equals(entry.getKey())) {
                    return entry.getValue();
                }
                entry = entry.next;
            } while (entry != null);
        }
        return null;
    }
}
