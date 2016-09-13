package com.raj.concurrency;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.concurrent.*;

/**
 * A thread pool executor service backed by a bounded queue with caller runs policy
 *
 */
public class BoundedQueue {

    private ExecutorService executorService;

    public void init(int numTasks, int numThreads, int queueSize, RejectedExecutionHandler rejectedExecutionHandler) {
        executorService = new ThreadPoolExecutor(numThreads, numThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize),
                rejectedExecutionHandler);

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        List<ListenableFuture<Result>> listenableFutures = Lists.newArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(numTasks);
        for (int i = 1; i <= numTasks; i++) {

            try {
                ListenableFuture<Result> listenableFuture = listeningExecutorService.submit(new Work(i));
                listenableFutures.add(listenableFuture);
                Futures.addCallback(listenableFuture, new FutureCallback<Result>() {
                    @Override
                    public void onSuccess(@Nullable Result result) {
                        countDownLatch.countDown();
                        System.out.println(result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                        countDownLatch.countDown();
                        System.out.println(t.getMessage());
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println(e.getMessage());
                try {
                    System.out.println("Wait for queue to drain...");
                    Thread.sleep(1);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        try {
            //countDownLatch.await(10, TimeUnit.SECONDS);
            listeningExecutorService.shutdown();
            listeningExecutorService.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(">>>>>>> Success = " + (numTasks-countDownLatch.getCount()) + ", Failed = " + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        BoundedQueue testBoundedQueue = new BoundedQueue();
        testBoundedQueue.init(50, 5, 5, new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("Total time taken to complete all tasks : " + (System.currentTimeMillis() - start)/1000);
    }

    class Work implements Callable<Result> {

        private Result result = new Result();

        public Work(int i) {
            this.result.i = i;
        }

        public Result call() throws Exception {

            result.queuedTime = System.currentTimeMillis() - result.start;

            // some work
            Thread.sleep(2000);

            result.end = System.currentTimeMillis();
            result.runtime = result.end - result.start;
            return result;
        }
    }

    class Result {
        long i, start, end, queuedTime, runtime;

        public Result() {
            this.start = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("i", i)
                    .add("queuedTime", queuedTime)
                    .add("runtime", runtime)
                    .toString();
        }
    }

}