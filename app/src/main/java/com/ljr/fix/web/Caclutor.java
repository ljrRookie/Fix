package com.ljr.fix.web;


import com.ljr.fix.Replace;

public class Caclutor {
//修复类
    @Replace(clazz = "com.ljr.fix.Caclutor",method = "caculator")
    public int caculator()
    {
        return 10;
    }

}
