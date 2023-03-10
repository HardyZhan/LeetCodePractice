package com.hardyz.leetcodepractice.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class BlockingQueue<T> {
    private int capacity;
    private Deque<T> queue = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyWaitCondition = lock.newCondition();
    private Condition fullWaitCondition = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    log.debug("当前任务队列已空，等待获取任务。。。");
                    emptyWaitCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("获取任务。。。");
            T task = queue.removeFirst();
            fullWaitCondition.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    public boolean put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("当前任务队列已满，等待执行任务。。。");
                    fullWaitCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("放入任务。。。");
            queue.add(task);
            emptyWaitCondition.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public T tryTake(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        log.debug("等待超时。。。");
                        return null;
                    }
                    log.debug("当前任务队列为空，超时等待获取任务。。。");
                    nanos = emptyWaitCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("获取任务。。。");
            T task = queue.removeFirst();
            fullWaitCondition.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    public boolean tryPut(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        log.debug("放入超时。。。");
                        return false;
                    }
                    log.debug("当前任务队列已满， 超时等待执行任务。。。");
                    nanos = fullWaitCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("放入任务。。。");
            queue.add(task);
            emptyWaitCondition.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void rejectTryPut(T task, RejectPolicy<T> rejectPolicy) {
        lock.lock();
        try {
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                log.debug("放入任务。。。");
                queue.add(task);
                emptyWaitCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
