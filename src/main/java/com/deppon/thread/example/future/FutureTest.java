package com.deppon.thread.example.future;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.FutureTask;

/**
 * Created by yhsyzzq on 2017/7/25.
 */
public class FutureTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入根目录：");
        String directory = in.nextLine();
        System.out.print("请输入目录关键词：");
        String keyword = in.nextLine();

        long startTime = System.currentTimeMillis();
        MatchCounter counter = new MatchCounter(new File(directory),keyword);
        FutureTask<Integer> task = new FutureTask<Integer>(counter);
        Thread thread = new Thread(task);
        thread.start();

        try {
            long endTime = System.currentTimeMillis();
            System.out.printf("找到%d个匹配的文件,搜索耗时：%10.2f秒",task.get(),(float)((endTime-startTime)/1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
