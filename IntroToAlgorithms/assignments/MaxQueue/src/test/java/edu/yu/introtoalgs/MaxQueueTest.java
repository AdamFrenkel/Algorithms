package edu.yu.introtoalgs;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxQueueTest {
    @Test
    public void testBasics(){
        MaxQueue maxQueue = new MaxQueue();
        assertEquals(0,maxQueue.size());
        maxQueue.enqueue(10);
        assertEquals(1,maxQueue.size());
        assertEquals(10,maxQueue.max());
        maxQueue.enqueue(5);
        assertEquals(10,maxQueue.max());
        assertEquals(2,maxQueue.size());
        assertEquals(10,maxQueue.dequeue());
        assertEquals(1,maxQueue.size());
        assertEquals(5,maxQueue.max());
    }
    @Test
    public void testMax(){
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.enqueue(10);
        maxQueue.enqueue(9);
        maxQueue.enqueue(8);
        maxQueue.enqueue(5);
        maxQueue.enqueue(6);
        maxQueue.enqueue(7);
        maxQueue.enqueue(6);
        assertEquals(10,maxQueue.max());
        assertEquals(10,maxQueue.dequeue());
        assertEquals(9,maxQueue.max());
        assertEquals(9,maxQueue.dequeue());
        assertEquals(8,maxQueue.max());
        assertEquals(8,maxQueue.dequeue());
        assertEquals(7,maxQueue.max());
        assertEquals(5,maxQueue.dequeue());
        assertEquals(7,maxQueue.max());
        assertEquals(6,maxQueue.dequeue());
        assertEquals(7,maxQueue.max());
        assertEquals(7,maxQueue.dequeue());
        assertEquals(6,maxQueue.max());
        assertEquals(1,maxQueue.size());
        assertEquals(6,maxQueue.dequeue());
        assertEquals(0,maxQueue.size());
        maxQueue.enqueue(3);
        assertEquals(3,maxQueue.max());

    }
    /**
     * Good test for dequeue speed, but dont want it running on maven, so commenting it out
     */
//    @Test
//    public void testSpeedOfDeque(){
//        MaxQueue mQ = new MaxQueue();
//        System.out.println("Buffering");
//        //int p = 7;
//        for(int i = 0; i < 1000000000; i++){
//            //int j = (int) (Math.random() * (10001));
//            mQ.enqueue(19);
//            mQ.enqueue(18);
//            mQ.enqueue(17);
//            mQ.dequeue();
//            mQ.dequeue();
//            mQ.dequeue();
//        }
//
//        for(int i = 0; i < 125; i++){
//            int j = (int) (Math.random() * (10001));
//            mQ.enqueue(j);
//        }
//        double timePrev = System.nanoTime();
//        for(int k = 1; k<=125; k++){
//            mQ.dequeue();
//        }
//        double elapsedTimePrev = System.nanoTime() - timePrev;
//        double prev = elapsedTimePrev/125; //prev time per deque
//        for (int n = 250; n <= 536870911; n += n) {
//            for(int i = 0; i < n; i++){
//                int j = (int) (Math.random() * (10001));
//                mQ.enqueue(j);
//            }
//            double time = System.nanoTime();
//            for(int k = 1; k<=n; k++){
//                mQ.dequeue();
//            }
//            double elapsedTime = System.nanoTime() - time;
//            double timePerDeque = elapsedTime/n;
//            System.out.println("n = " + n + ". TimePerDeque = " + timePerDeque);
//            System.out.println("growth rate = " +timePerDeque / prev);
//            prev = timePerDeque;
//        }
//    }


//    @Test
//    public void testSpeedOfQueueBad2(){
//        MaxQueueBadImpl2 mQ = new MaxQueueBadImpl2();
//        System.out.println("Buffering");
//        //int p = 7;
//        for(int i = 0; i < 1000000000; i++){
//            //int j = (int) (Math.random() * (10001));
//            mQ.enqueue(19);
//            mQ.enqueue(18);
//            mQ.enqueue(17);
//            mQ.dequeue();
//            mQ.dequeue();
//            mQ.dequeue();
//        }
//
//        for(int i = 0; i < 125; i++){
//            int j = (int) (Math.random() * (10001));
//            mQ.enqueue(j);
//        }
//        double timePrev = System.nanoTime();
//        for(int k = 1; k<=125; k++){
//            mQ.dequeue();
//        }
//        double elapsedTimePrev = System.nanoTime() - timePrev;
//        double prev = elapsedTimePrev/125; //prev time per deque
//        for (int n = 250; n <= 536870911; n += n) {
//            for(int i = 0; i < n; i++){
//                int j = (int) (Math.random() * (10001));
//                mQ.enqueue(j);
//            }
//            double time = System.nanoTime();
//            for(int k = 1; k<=n; k++){
//                mQ.dequeue();
//            }
//            double elapsedTime = System.nanoTime() - time;
//            double timePerDeque = elapsedTime/n;
//            System.out.println("n = " + n + ". TimePerDeque = " + timePerDeque);
//            System.out.println("growth rate = " +timePerDeque / prev);
//            prev = timePerDeque;
//        }
//    }
//
//    @Test
//    public void testSpeedOfQueueBad(){
//        MaxQueueBadImpl mQ = new MaxQueueBadImpl();
//        System.out.println("Buffering");
//        //int p = 7;
//        for(int i = 0; i < 1000000000; i++){
//            //int j = (int) (Math.random() * (10001));
//            mQ.enqueue(19);
//            mQ.enqueue(18);
//            mQ.enqueue(17);
//            mQ.dequeue();
//            mQ.dequeue();
//            mQ.dequeue();
//        }
//
//        for(int i = 0; i < 125; i++){
//            int j = (int) (Math.random() * (10001));
//            mQ.enqueue(j);
//        }
//        double timePrev = System.nanoTime();
//        for(int k = 1; k<=125; k++){
//            mQ.dequeue();
//        }
//        double elapsedTimePrev = System.nanoTime() - timePrev;
//        double prev = elapsedTimePrev/125; //prev time per deque
//        for (int n = 250; n <= 536870911; n += n) {
//            for(int i = 0; i < n; i++){
//                int j = (int) (Math.random() * (10001));
//                mQ.enqueue(j);
//            }
//            double time = System.nanoTime();
//            for(int k = 1; k<=n; k++){
//                mQ.dequeue();
//            }
//            double elapsedTime = System.nanoTime() - time;
//            double timePerDeque = elapsedTime/n;
//            System.out.println("n = " + n + ". TimePerDeque = " + timePerDeque);
//            System.out.println("growth rate = " +timePerDeque / prev);
//            prev = timePerDeque;
//        }
//    }
}