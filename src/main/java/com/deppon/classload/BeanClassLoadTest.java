package com.deppon.classload;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by yhsyzzq on 2017-12-25.
 */
public class BeanClassLoadTest {
    public static void main(String[] args) {
//        ClassLoader loader = BeanTest2.class.getClassLoader();
//        System.out.println(loader.toString());
//        System.out.println(loader.getParent().toString());
//        System.out.println(loader.getParent().getParent().toString());

//        ClassLoader loader1 = int.class.getClassLoader();
//        System.out.println(loader1.toString());

         long startTime = System.nanoTime();
         for(int i=0;i<100;i++){
             System.out.println(i);
         }
         long endTime = System.nanoTime();
        System.out.println(endTime-startTime);
    }


}
