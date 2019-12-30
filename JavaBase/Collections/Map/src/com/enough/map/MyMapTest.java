package com.enough.map;

/**
 * @program: MyMap
 * @description: 测试类
 * @author: lidong
 * @create: 2019/12/30
 */
public class MyMapTest {

    public static void main(String[] args) {
        MyMap <String, String> myMap = new MyHashMap <>();
        for (int i = 0; i < 20; i++) {
            myMap.put("key" + i, "value" + i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(myMap.get("key" + i));
        }
        //        myMap.put("1", "1");
        //        myMap.put("1", "1");
    }
}
