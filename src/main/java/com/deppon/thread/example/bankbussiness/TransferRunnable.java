package com.deppon.thread.example.bankbussiness;

/**
 * Created by yhsyzzq on 2017/7/22.
 */
public class TransferRunnable implements Runnable {
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 100;

    /**
     * 构造函数，初始化
     * @param bank
     * @param from
     * @param maxAmaount
     */
    public TransferRunnable(Bank bank,int from,double maxAmaount){
        this.bank = bank;
        this.fromAccount = from;
        this.maxAmount = maxAmaount;
    }

    public void run() {
        try{
            while (true){
                int toAccount = (int)(bank.getSize() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount,toAccount,amount);
                Thread.sleep((int)(DELAY*Math.random()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
