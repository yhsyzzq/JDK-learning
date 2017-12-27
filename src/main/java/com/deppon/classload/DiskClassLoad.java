package com.deppon.classload;

import java.io.*;

/**
 * Created by yhsyzzq on 2017-12-26.
 */
public class DiskClassLoad extends ClassLoader {
    //自定义加载目录
    private String myLibPath;

    //构造函数
    public DiskClassLoad(String path) {
        this.myLibPath = path;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        String fileName = className.replace(".", "//") + ".class";
        File file = new File(myLibPath, fileName);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int line = 0;
            while ((line = inputStream.read()) != -1) {
                bos.write(line);
            }

            byte[] data = bos.toByteArray();
            inputStream.close();
            bos.close();

            return defineClass(className, data, 0, data.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(className);
    }
}
