package com.deppon.thread.example.reflect;

import sun.applet.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by yhsyzzq on 2017-10-21.
 */
public class reflectTest {
    public static void main(String[] args) {
        try {
            Class hashMapClass = Class.forName("HashMap");
            Method method = hashMapClass.getMethod("put", null);
            Object obj = hashMapClass.newInstance();
            method.invoke(obj, null);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        } catch (InstantiationException ie) {
            ie.printStackTrace();
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        }


    }
}
