package com.raj.concurrency;

/**
 * @author: sraj1
 * Date:    10/23/12
 */


// We would like to synchronize producer and consumer so that
// producer puts a number in the buffer, then the consumer takes it
// out, then the producer puts another number, and so on.

// This solution provides the right behaviour
// We have changed the class Buffer to include wait() and notify()
// We also have changed the producer and consumer classes
// slightly to handle a new exception

public class ConsumerProducer {

    public static void main(String[] args) {
        Buffer buf = new Buffer();

        // create new threads
        Thread prod = new Producer(10, buf);
        Thread cons = new Consumer(5, buf);
        cons.setName("Consumer Thread 1");
        Thread cons2 = new Consumer(5, buf);
        cons2.setName("Consumer Thread 2");

        // starting threads
        prod.start();
        cons.start();
        cons2.start();

        // Wait for the threads to finish
        try {
            prod.join();
            cons.join();
        } catch (InterruptedException e) {
            return;
        }
    }

}

class Buffer {
    private int contents;
    private boolean empty = true;

    public synchronized void put(int i) throws InterruptedException {
        while (empty == false) {    //wait till the buffer becomes empty
            try {
                wait();
            } catch (InterruptedException e) {
                throw e;
            }
        }
        contents = i;
        empty = false;
        System.out.println("Producer: put..." + i);
        notify();
    }

    public synchronized int get() throws InterruptedException {
        while (empty == true) {    //wait till something appears in the buffer
            try {
                wait();
            } catch (InterruptedException e) {
                throw e;
            }
        }
        int val = contents;
        System.out.println("Consumer: got..." + val + " -->" + Thread.currentThread().getName());
        empty = true;
        notify();
        return val;
    }
}


class Producer extends Thread {
    private int n;
    private Buffer prodBuf;

    public Producer(int m, Buffer buf) {
        n = m;
        prodBuf = buf;
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep((int) Math.random() * 100); // sleep for a randomly chosen time
            } catch (InterruptedException e) {
                return;
            }

            try {
                prodBuf.put(i + 1); //starting from 1, not 0
            } catch (InterruptedException e) {
                return;
            }

        }
    }
}

class Consumer extends Thread {
    private int n;
    private Buffer consBuf;

    public Consumer(int m, Buffer buf) {
        n = m;
        consBuf = buf;
    }

    public void run() {
        int value;
        for (int i = 0; i < n; i++) {
            try {
                value = consBuf.get();
            } catch (InterruptedException e) {
                return;
            }
            try {
                Thread.sleep((int) Math.random() * 100); // sleep for a randomly chosen time
            } catch (InterruptedException e) {
                return;
            }

        }
    }
}


