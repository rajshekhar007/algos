package com.raj;

import com.nurkiewicz.asyncretry.AsyncRetryExecutor;
import com.nurkiewicz.asyncretry.RetryExecutor;

import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by rshekh1 on 12/5/16.
 */
public class ErrorRetry {

    public void retry() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        RetryExecutor executor = new AsyncRetryExecutor(scheduler).
                retryOn(SocketException.class).
                withExponentialBackoff(500, 2).     //500ms times 2 after each retry
                withMaxDelay(1_000).               //10 seconds
                withUniformJitter().                //add between +/- 100 ms randomly
                withMaxRetries(20);
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        RetryExecutor executor = new AsyncRetryExecutor(scheduler).
                retryOn(Exception.class).
                withExponentialBackoff(100, 2).     //500ms times 2 after each retry
                withMaxDelay(1_000).               //10 seconds
                withUniformJitter().                //add between +/- 100 ms randomly
                withMaxRetries(2);

        executor.
                getWithRetry(() -> new Socket("localhost", 8980)).
                thenAccept(socket -> System.out.println("Connected! " + socket));

    }

    public static void main(String[] args) {
        ErrorRetry errorRetry = new ErrorRetry();
        errorRetry.execute();
    }

}
