package com.ljr.fix.web;


import com.ljr.fix.Replace;

public class Caclutor {
//修复类
    @Replace(clazz = "com.ljr.fix",method = "caculator")
    public int caculator(int a)
    {
//跑异常
        return 10;
    }

    public int caculator2()
    {
//跑异常
        return 10;
    }
}
