package com.deppon.highconcurrence.currentlimit;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计数器限流（初级版）
 * Created by yhsyzzq on 2017-12-27.
 */
public class CountTest {
    private static long count = 0; //计数器
    private final static int limit = 100; //每秒最大请求数
    private final static long interval = 1000; //时间间隔，1000毫秒
    private static long start = System.currentTimeMillis(); //当前时间

    public static boolean entry() {
        long now = System.currentTimeMillis();
        if (now < start + interval) {
            if (count < limit) {
                count++;
                return true;
            } else {
                return false;
            }
        } else {
            start = now;
            count = 1;
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        while (true) {
            service.submit(new worker());
            int intervelMillis = new Random().nextInt(10);
            Thread.sleep(intervelMillis);
        }
    }
}

class worker implements Runnable {
    public void run() {
        if (CountTest.entry()) {
            System.out.println("正常执行");
        } else {
            System.out.println("限制当前请求执行");
        }
    }
}
