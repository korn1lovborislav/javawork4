package com.shop.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ThreadManager {
    private final ExecutorService executor;

    public ThreadManager(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public <T> List<? extends Future<?>> submitTasks(List<T> items, 
                                                     Predicate<T> filter, 
                                                     Consumer<T> action) {
        List<Future<?>> futures = new ArrayList<>();
        for (T item : items) {
            if (filter.test(item)) {
                futures.add(executor.submit(() -> action.accept(item)));
            }
        }
        executor.shutdown();
        return futures;
    }
}