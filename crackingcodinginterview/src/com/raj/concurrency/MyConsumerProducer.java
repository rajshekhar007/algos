package com.raj.concurrency;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: sraj1
 * Date:    11/4/12
 */
public class MyConsumerProducer {

    Queue<Integer> queue = new LinkedList<Integer>();

    public static void main(String[] args) {

        MyConsumerProducer myConsumerProducer = new MyConsumerProducer();

        Thread producerThread = new Thread(myConsumerProducer.new Producer(10));
        Thread consumerThread1 = new Thread(myConsumerProducer.new Consumer());
        //Thread consumerThread2 = new Thread(myConsumerProducer.new Consumer());
        producerThread.start();
        consumerThread1.start();
        //consumerThread2.start();

    }

    class Producer implements Runnable {

        int msgToProduce;
        int msgProduced;

        Producer(int msgToProduce) {
            this.msgToProduce = msgToProduce;
        }

        @Override
        public void run() {
            while (!queue.isEmpty()) {  // wait till queue is empty
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            int msg = (int) Math.random() * 100;
            System.out.println("Produced msg : " + msg);
            queue.add(msg);

            msgProduced++;
            notifyAll();

        }
    }

    class Consumer implements Runnable {
        int cnt;

        @Override
        public void run() {
            while (queue.isEmpty()) {    // wait till queue is having some messages
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // now consume
            System.out.println("Consumed msg : " + queue.remove());
            cnt++;
            //if(cnt >= 10) break;
            notify();
        }
    }


}
