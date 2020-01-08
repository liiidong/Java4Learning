package com.enough.learn.redis;

import com.enough.learn.redis.utils.CodecUtils;
import org.junit.jupiter.api.Test;

/**
 * @program: learn-redis
 * @description: 加密解密测试
 * @author: lidong
 * @create: 2019/11/18
 */
public class CodecTest {

    @Test
    public void codecTest(){
        String str = "helloword!";
        //加密
        System.out.println(CodecUtils.encode(str));
        //判断
        System.out.println("是否加密" + CodecUtils.isBase64(CodecUtils.encode(str)));
        //解密
        System.out.println(CodecUtils.decode(CodecUtils.encode(str)));
    }
}
