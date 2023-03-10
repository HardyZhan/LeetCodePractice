package com.hardyz.leetcodepractice.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
@Slf4j
public class ThreadPool {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;
    // 核心线程数
    private int coreSize;
    // 线程池
    private HashSet<Woker> wokers = new HashSet<>();
    // 超时拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;
    // 超时时间
    private long timeout;
    // 时间单位
    private TimeUnit timeUnit;

    public ThreadPool(int queueSize, int coreSize, long timeout, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = new BlockingQueue<>(queueSize);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (wokers) {
            if (wokers.size() < coreSize) {
                Woker woker = new Woker(task);
                log.debug("新增woker加入线程池。。。");
                wokers.add(woker);
                woker.start();
            } else {
                taskQueue.rejectTryPut(task, rejectPolicy);
            }
        }
    }

    // 线程类
    class Woker extends Thread {
        private Runnable task;

        public Woker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || ((task = taskQueue.tryTake(timeout, timeUnit)) != null)) {
                try {
                    log.debug("正在执行任务。。。");
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (wokers) {
                log.debug("任务执行完毕，当前woker被移除。。。");
                wokers.remove(this);
            }
        }
    }
}
