package com.hardyz.leetcodepractice.ThreadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolTest {

    @Test
    public void case1() {
        int coreSize = 2;
        int queueSize = 5;
        long timeout = 1000l;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        RejectPolicy<Runnable> rejectPolicy = (queue, task) -> {
            queue.tryPut(task, timeout, timeUnit);
        };
        ThreadPool threadPool = new ThreadPool(queueSize, coreSize, timeout, timeUnit, rejectPolicy);
        for (int i = 0; i < 10; i++) {
            int j = i;
            threadPool.execute(
                    () -> {
                        try {
                            Thread.sleep(3000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.debug("{}", j);
                    }
            );
        }
    }
}
