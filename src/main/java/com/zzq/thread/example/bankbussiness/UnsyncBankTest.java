package com.zzq.thread.example.bankbussiness;

/**
 * Created by yhsyzzq on 2017/7/22.
 */
public class UnsyncBankTest {

    //    public final static int NACCOUNTS = 100;
    public final static int NACCOUNTS = 4;
    public final static double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
//            TransferRunnable transferRunnable = new TransferRunnable(bank,i,INITIAL_BALANCE);
            TransferRunnable transferRunnable = new TransferRunnable(bank, i, INITIAL_BALANCE);
            Thread thread = new Thread(transferRunnable);
            thread.start();
        }
    }
}
