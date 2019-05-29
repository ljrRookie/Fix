package com.ljr.fix;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class DexManager {
    private Context context;

    public DexManager(Context context) {
        this.context = context;
    }
    public void load(File file){
        try {
            DexFile dexFile = DexFile.loadDex(file.getAbsolutePath(), new File(context.getCacheDir(), "opt").getAbsolutePath(), Context.MODE_PRIVATE);
            //当前的dex里面的class类名集合
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()){
                String clazzName = entry.nextElement();
                //能1   不能2
                Class realClazz = dexFile.loadClass(clazzName, context.getClassLoader());
                if(realClazz != null){
                    fixClazz(realClazz);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fixClazz(Class realClazz) {
        //        andfix  class   ----->bug  class
        //        method    --->  bug   method
        Method[] methods = realClazz.getMethods();
        for (Method rightMethod:methods) {
            Replace replace = rightMethod.getAnnotation(Replace.class);
            if(replace == null){
                continue;
            }
            //            拿到了从网络上下载的 rightMethod
            //            本地的bug  class   中method.

            String clazzName = replace.clazz();
            String methodName = replace.method();
            try {
                Class wrongClazz = Class.forName(clazzName);
                Method wrongMethod = wrongClazz.getDeclaredMethod(methodName, rightMethod.getParameterTypes());
                replace(wrongMethod,rightMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public native static void replace(Method wrongmethod,Method rightMethod);
}
