package com.deppon.primary.type;

import java.math.BigInteger;

/**
 * Created by yhsyzzq on 2017-11-27.
 */
public class PrimaryTypeTest {

    public static void main(String[] args) {
        Character c = new Character('c');

        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        System.out.println(two.add(three));
    }
}
