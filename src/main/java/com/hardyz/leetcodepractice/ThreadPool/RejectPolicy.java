package com.hardyz.leetcodepractice.ThreadPool;
@FunctionalInterface
public interface RejectPolicy<T> {
    public void reject(BlockingQueue<T> blockingQueue, T task);
}
