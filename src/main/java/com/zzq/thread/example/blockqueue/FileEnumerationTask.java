package com.zzq.thread.example.blockqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 文件枚举任务
 * Created by yhsyzzq on 2017/7/24.
 */
public class FileEnumerationTask implements Runnable {

    private BlockingQueue<File> queue;

    private File startingDirectory;

    public static File DUMMY = new File("");

    public FileEnumerationTask(BlockingQueue<File> queue,File startingDirectory){
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    enumerate(file);
                } else {
                    queue.put(file);
                }
            }
        }
    }

    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
