package com.zzq.thread.example.bankbussiness;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yhsyzzq on 2017/7/22.
 */
public class Bank {

    private final double[] accounts;

    private Lock bankLock;

    private Condition sufficientFunds;

    /**
     * 构造函数
     * @param n 银行账户数
     * @param initialBalance 账户金额
     */
    public Bank(int n,double initialBalance){
        accounts = new double[n];
        for(int i=0;i<accounts.length;i++){
            accounts[i] = initialBalance;
        }
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    /**
     * 获取账户个数
     * @return
     */
    public int getSize(){
        return this.accounts.length;
    }

    /**
     * 转账过程
     * @param from 转账发出账号
     * @param to 转账目的账号
     * @param amount 转账金额
     */
    public void transfer(int from,int to,double amount){
        bankLock.lock();
        try {
            while (accounts[from] < amount){
                System.out.printf("准备执行的转账动作 -> 转账金额：%10.2f from account-%d to account-%d\n",amount,from,to);
                //wait
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("执行转账动作完成->转账金额：%10.2f from account-%d to account-%d\n\n",amount,from,to);
            accounts[to] += amount;
            System.out.printf(" Total balance : %10.2f\n",getTotalBalance());
//            sufficientFunds.signalAll();
            sufficientFunds.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * 银行转账总金额
     *
     * @return
     */
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            int i = 0;
            for (double a : accounts) {
                sum += a;
                System.out.printf("账户%d余额:%10.2f",i,a);
                i++;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }
}
