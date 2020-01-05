package com.mall.manage.test;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorService{
    /**
     * 记录当前alive的线程数
     */
    private AtomicInteger ct1 = new AtomicInteger();
    private int poolSize;
    private volatile PolicyHandler handler;
    private final BlockingQueue<Runnable> workQueue;
    private volatile boolean allowTheardTimeOut = false;
    private ReentrantLock mainLock = new ReentrantLock();
    private volatile boolean isShutDown;
    private volatile long completedTaskCount = 0;
    private final HashSet<Worker> workers = new HashSet<>();

    public ExecutorService(int poolSize, int queueSize, long keepAliveTime, PolicyHandler handler) throws Exception {
        if(poolSize < 0){
            throw new Exception("核心线程不能为0");
        }
        this.poolSize = poolSize;
        this.handler = handler;
        this.workQueue = new ArrayBlockingQueue<Runnable>(5);
    }

    public ExecutorService(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }


    public Runnable gatTask(){
        this.workQueue.poll();
        return null;
    }


    class Worker implements Runnable{

        final Thread thread;

        Runnable firstTask;

        public Worker(Runnable task){
            this.firstTask = task;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
