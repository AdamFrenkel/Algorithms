package edu.yu.introtoalgs;

import org.junit.Assert;
import org.junit.Test;
import edu.yu.introtoalgs.MergeAnInterval.Interval;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MergeAnIntervalTest {
    @Test
    public void testBasics(){
        Interval a = new Interval(1,2);
        Interval b = new Interval(3,4);
        Set<Interval> intervals = new HashSet<>();
        intervals.add(a);
        intervals.add(b);
        Interval newInterval = new Interval(2,3);
        Set<Interval> returnSet = MergeAnInterval.merge(intervals, newInterval);
        assertEquals(1,returnSet.size());
        for(Interval i : returnSet){
            assertEquals(1,i.left);
            assertEquals(4,i.right);
        }
        Interval c = new Interval(12,88);
        intervals.add(c);
        Set<Interval> returnSet2 = MergeAnInterval.merge(intervals, newInterval);
        assertEquals(2,returnSet2.size());
        Interval d = new Interval(-9,80);
        Set<Interval> returnSet3 = MergeAnInterval.merge(intervals, d);
        assertEquals(1,returnSet3.size());
        for(Interval i : returnSet3){
            assertEquals(-9,i.left);
            assertEquals(88,i.right);
        }
        intervals.clear();
        intervals.add(a);
        intervals.add(b);
        intervals.add(c);
        Interval e = new Interval(-1,5);
        Set<Interval> returnSet4 = MergeAnInterval.merge(intervals, e);
        assertEquals(2,returnSet4.size());
        //int y = 0;
        for(Interval i : returnSet4){
            if(i.left == -1) {
                assertEquals(-1, i.left);
                assertEquals(5, i.right);
            }else {
                assertEquals(12, i.left);
                assertEquals(88, i.right);
            }
        }
    }

//    @Test
//    public void testSpeed(){
//        Set<Interval> inters = new HashSet<>();
//        System.out.println("Buffering");
//        for(int i = 0; i < 41_504_260; i++) {
//            Interval a = new Interval(1, 2);
//            Interval b = new Interval(3, 4);
//            Set<Interval> intervals = new HashSet<>();
//            intervals.add(a);
//            intervals.add(b);
//            Interval newInterval = new Interval(2, 3);
//            MergeAnInterval.merge(intervals, newInterval);
//            System.out.println(i + "/41_504_260");
//        }
//        //inters = new Interval[125];
//        int k = -2;
//        for(int j = 0; j<125; j= j+2){
//            k=k+2;
//            inters.add(new Interval(k,k+1));
//        }
//        double timeX = System.nanoTime();
//        MergeAnInterval.merge(inters,new Interval(0,k+1));
//        double prev = System.nanoTime() - timeX;
//
//        for (int n = 250; n <= 536870911; n += n) {
//            inters.clear();
//            int p = -2;
//            for(int i = 0; i < n; i++){
//                p=p+2;
//                inters.add(new Interval(p,p+1));
//            }
//            double time = System.nanoTime();
//            MergeAnInterval.merge(inters,new Interval(0,p+1));
//            double elapsedTime = System.nanoTime() - time;
//            System.out.println("n = " + n + ". elapse time = " + elapsedTime);
//            System.out.println("growth rate = " +elapsedTime / prev);
//            prev = elapsedTime;
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

}