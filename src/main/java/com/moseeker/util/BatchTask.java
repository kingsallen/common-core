package com.moseeker.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BatchTask {
    private Executor executor;
    private static final Integer MAXTHREADS = 100;

    public BatchTask() {
    }

    public BatchTask(Integer nThreads) {
        executor = Executors.newFixedThreadPool(Math.min(MAXTHREADS, nThreads),(r)->{
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public BatchTask(Executor executor) {
        this.executor = executor;
    }

    public <R, T> List<T> apply(Function<R, T> function, List<R> list) {
        List<CompletableFuture<T>> taskList = list.stream().map(r ->
                buildCompletableFuture(r, function)
        ).collect(Collectors.toList());
        return taskList.stream().map(r -> r.join()).collect(Collectors.toList());
    }

    private <R, T> CompletableFuture<T> buildCompletableFuture(R r, Function<R, T> function) {
        CompletableFuture<R> future = CompletableFuture.completedFuture(r);
        return executor == null ? future.thenApplyAsync(function) : future.thenApplyAsync(function, executor);
    }

    public <R> void accept(Consumer<R> consumer, List<R> list) {
        List<CompletableFuture<Void>> taskList = list.stream().map(r ->
                buildCompletableFuture(r, consumer)
        ).collect(Collectors.toList());
        CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0])).join();
    }

    private <R> CompletableFuture<Void> buildCompletableFuture(R r, Consumer<R> consumer) {
        CompletableFuture<R> future = CompletableFuture.completedFuture(r);
        return executor == null ? future.thenAcceptAsync(consumer) : future.thenAcceptAsync(consumer, executor);
    }
}
