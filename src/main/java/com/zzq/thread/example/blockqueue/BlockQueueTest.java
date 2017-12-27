package com.zzq.thread.example.blockqueue;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yhsyzzq on 2017/7/24.
 */
public class BlockQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入根目录：");
        String directory = in.nextLine();
        System.out.print("请输入目录关键词：");
        String keyword = in.nextLine();

        final int FILE_QUEUE_SIZE = 100;
        final  int SEARCH_THREADS =1000;

        BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
        FileEnumerationTask enumerator = new FileEnumerationTask(queue,new File(directory));
        new Thread(enumerator).start();
        for(int i=0;i<=SEARCH_THREADS;i++){
            new Thread(new searchTask(queue,keyword)).start();
        }
    }
}
