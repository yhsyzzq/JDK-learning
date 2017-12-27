package com.zzq.thread.example.future;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 查询匹配的文件数
 * Created by yhsyzzq on 2017/7/25.
 */
public class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private int count;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    public Integer call() throws Exception {
        count = 0;
        try {
            File[] files = directory.listFiles();
            ArrayList<Future<Integer>> futures = new ArrayList<Future<Integer>>();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        //如果获取到的是目录，则递归继续查找
                        MatchCounter counter = new MatchCounter(file, keyword);
                        FutureTask<Integer> task = new FutureTask<Integer>(counter);
                        futures.add(task);
                        Thread thread = new Thread(task);
                        thread.start();
                    } else {
                        if (search(file)) {
                            count++;
                        }
                    }
                }
                //合计future计算结果
                for (Future<Integer> future : futures) {
                    try {
                        if(future != null){
                            count += (future.get() != null ? future.get() : 0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
        }
        return count;
    }

    public boolean search(File file) {
        try {
            Scanner in = new Scanner(new FileInputStream(file));
            boolean found = false;
            int lineNumber = 0;
            while (!found && in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    found = true;
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
            in.close();
            return found;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
