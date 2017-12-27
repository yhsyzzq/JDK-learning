package com.deppon.thread.example.threadpool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by yhsyzzq on 2017/7/26.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入根目录：");
        String directory = in.nextLine();
        System.out.print("请输入目录关键词：");
        String keyword = in.nextLine();

        ExecutorService pool = Executors.newCachedThreadPool();
        MatchCounter2 counter = new MatchCounter2(new File(directory),keyword,pool);
        Future<Integer> future = pool.submit(counter);

        try {
            System.out.printf("找到%d个匹配的文件",future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();

        int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize();
        System.out.println("线程池的最大线程数largestPoolSize="+largestPoolSize);
    }
}
