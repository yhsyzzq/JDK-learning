package com.zzq.classload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yhsyzzq on 2017-12-25.
 */
public class ClassLoadTest {
    public static void main(String[] args) {
//        String[] strs = System.getProperty("sun.boot.class.path").split(";");
//        for(String str : strs){
//            System.out.println(str);
//        }
//
//        String[] strs2 = System.getProperty("java.ext.dirs").split(";");
//        for(String str : strs2){
//            System.out.println(str);
//        }
//
//        String[] strs3 = System.getProperty("java.class.path").split(";");
//        for(String str : strs3){
//            System.out.println(str);
//        }

        DiskClassLoad diskClassLoad = new DiskClassLoad("F://lib");
        try {
            Class c = diskClassLoad.loadClass("com.zzq.classload.BeanTest");
            if (c != null) {
                Object o = c.newInstance();

                System.out.println(c.getClassLoader().toString());
                System.out.println(c.getClassLoader().getParent().toString());
                System.out.println(c.getClassLoader().getParent().getParent().toString());

                Method method = c.getDeclaredMethod("sayHello");
                //调用sayHello方法
                method.invoke(o, null);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
