package com.biscuits.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * @author biscuits
 * @date 2019/11/11
 */

public class Work {
    public static void main(String[] args) {
        B b = new B();
        b.setName("ASdasd");
        B b1 = new B();
        b1.setName("aaa");
        List<B> bs = Arrays.asList(b, b1);

        A build = A.builder().value(b).clearValues().values(bs).build();
    }
}
