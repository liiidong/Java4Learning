package com.enough.map;

/**
 * @program: MyMap
 * @description: 自定义Map接口，对外暴露快速存取的方法，
 * @author: lidong
 * @create: 2019/12/30
 */
public interface MyMap<K, V> {
    public V put(K k, V v);

    public V get(K k);

    /**
     * 注意MyMap接口内部定义了一个内部接口Entry。
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V> {
        public K getKey();

        public V getValue();
    }
}
