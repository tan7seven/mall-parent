package com.mall.manage.test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum TestEnum {
    sss;

    TestEnum() {
    }

    public void printHello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) throws Exception {
        Integer loopNum = 10000000;
//        Integer loopNum = 1000;
        Set<Object> instanceSet = new HashSet<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(loopNum);
        for (int i = 0; i < loopNum; i++) {
            executor.execute(() -> {
                TestEnum instance = TestEnum.sss;
                instance.printHello();
                instance.printHello();
//                instanceSet.createAttrName(obj);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdown();

        System.out.println(instanceSet);

    }
}
