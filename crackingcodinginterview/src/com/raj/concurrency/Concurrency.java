package com.raj.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: sraj1
 * Date:    10/23/12
 * Link: http://www.baptiste-wicht.com/2010/09/java-concurrency-part-7-executors-and-thread-pools/
 * http://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html
 */

public class Concurrency {
    /*
    Thread:
        A thread, also called Lightweight Process, is treatment unity. Threads executes code in parallel of each other
        threads currently running. When you’ve only one processor, there is a thread running at the same time of the
        others, you only have the impression of concurrency (I don’t say it’s not useful, I say it’s different), but
        when you’ve multiple processors, you’ll see the power of multi threading. In this case, you can have your
        threads distributed on the processors of the computer.

        You can create threads in two ways :
        1. Extends Thread
        2. Implements Runnable and pass an instance of your news class to the Thread constructor
    */

    public static void main(String[] args) {
        // Demo ThreadLocal : Serial Number generator
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();

        // ExecutorService demo
        ExecutorService pool = Executors.newFixedThreadPool(4);
        // 10 Future objects to store 10 result of callable thread executions through executor service
        List<Future<String>> futures = new ArrayList<Future<String>>(10);
        for (int i = 0; i < 10; i++)
            futures.add(pool.submit(new StringTask(i)));

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();

    }

    /*
    Executors and thread pools
        A thread pool is represented by an instance of the class ExecutorService. With an ExecutorService, you can
        submit task that will be completed in the future. Here are the type of thread pools you can create with the
        Executors class :
        - Single Thread Executor : A thread pool with only one thread. So all the submitted task will be executed
                                   sequentially.
                                   Method : Executors.newSingleThreadExecutor()
        - Cached Thread Pool : A thread pool that create as many threads it needs to execute the task in parralel. The
                               old available threads will be reused for the new tasks. If a thread is not used during
                               60 seconds, it will be terminated and removed from the pool.
                               Method : Executors.newCachedThreadPool()
        - Fixed Thread Pool : A thread pool with a fixed number of threads. If a thread is not available for the task,
                              the task is put in queue waiting for an other task to ends.
                              Method : Executors.newFixedThreadPool()
        - Scheduled Thread Pool : A thread pool made to schedule future task.
                                  Method : Executors.newScheduledThreadPool()
        - Single Thread Scheduled Pool : A thread pool with only one thread to schedule future task.
                                         Method : Executors.newSingleThreadScheduledExecutor()

        Once you have a thread pool, you can submit task to it using the different submit methods. You can submit a
        Runnable or a Callable to the thread pool. The method return a Future representing the future state of the task.
        If you submitted a Runnable, the Future object return null once the task finished.

        The Callable interface is similar to Runnable, in that both are designed for classes whose instances are
        potentially executed by another thread. A Runnable, however, does not return a result and cannot throw a
        checked exception.

        By example, if you have this Callable :
     */

    public static class MyRunnable implements Runnable {  //static because of demo purpose as driver function is main
        @Override
        public void run() {
            System.out.println("Hello I am a Thread!" + SerialNum.get());
        }
    }

    private static class StringTask implements Callable<String> {

        private int id;

        private StringTask(int id) {
            this.id = id;
        }

        public String call() {

            System.out.println("Processing Long running task " + id);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task " + id + " completed !";
        }
    }

    /**
     * Here is a sketch of a network service in which threads in a thread pool service incoming requests.
     * It uses the pre-configured Executors.newFixedThreadPool(int) factory method.
     */
    private static class NetworkService {
        private final ServerSocket serverSocket;
        private final ExecutorService pool;

        public NetworkService(int port, int poolSize) throws IOException {
            serverSocket = new ServerSocket(port);
            pool = Executors.newFixedThreadPool(poolSize);
        }

        public void serve() {
            try {
                for (; ; ) {
                    pool.execute(new Handler(serverSocket.accept()));
                }
            } catch (IOException ex) {
                pool.shutdown();
            }
        }
    }

    private static class Handler implements Runnable {
        private final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {

            System.out.println("Read and service requests");
        }
    }

}

class SerialNum {
    private static int cnt = 0;

    private static ThreadLocal<Integer> nextInt = new ThreadLocal<Integer>() {
       /* @Override
        protected Integer initialValue() {
            return new Integer(cnt++);
        }*/
    };

    public static Integer get() {
        return cnt++;
    }
}

