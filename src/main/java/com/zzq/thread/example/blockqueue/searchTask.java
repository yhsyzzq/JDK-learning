package com.zzq.thread.example.blockqueue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * 搜索任务类
 * Created by yhsyzzq on 2017/7/24.
 */
public class searchTask implements Runnable {
    /**
     * 关键词
     */
    private String keyword;

    /**
     * 队列
     */
    private BlockingQueue<File> queue;

    public searchTask(BlockingQueue<File> queue,String keyword){
        this.queue = queue;
        this.keyword = keyword;
    }

    /**
     * 搜索任务
     */
    public void search(File file) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(file));
        int lineNumber = 0;
        while (in.hasNextLine()){
            lineNumber++;
            String line = in.nextLine();
            if(line.contains(keyword)){
                System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
            }
        }
        in.close();
    }

    public void run() {
        boolean done = false;
        try{
            while (!done){
                File file = queue.take();
                if(file == FileEnumerationTask.DUMMY){
                    queue.put(file);
                    done = true;
                }else{
                    search(file);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
