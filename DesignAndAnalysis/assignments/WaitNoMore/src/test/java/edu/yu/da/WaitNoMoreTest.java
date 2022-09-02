package edu.yu.da;

import org.junit.Test;

import static org.junit.Assert.*;

public class WaitNoMoreTest {
//    @Test
//    public void testBasics(){
//        WaitNoMore wnm = new WaitNoMore();
//        int[] durs = new int[]{1,1,2,3,5,8};
//        int[] wgts = new int[]{1,1,5,2,1,2};
//        System.out.println(wnm.minTotalWaitingTime(durs,wgts));
//        assertEquals(68,wnm.minTotalWaitingTime(durs,wgts));
//    }

    @Test
    public void test2(){
        WaitNoMore wnm = new WaitNoMore();
        int[] durs = new int[]{1,1,1,1};
        int[] wgts = new int[]{1,1,1,1};
        System.out.println(wnm.minTotalWaitingTime(durs,wgts));
    }

    @Test
    public void sortingTest(){
        WaitNoMore wnm = new WaitNoMore();
        int[] durs = new int[]{1,1,2,3,5,8};
        int[] wgts = new int[]{1,1,5,2,1,2};
        wnm.minTotalWaitingTime(durs,wgts);
        //expect 2,0,1,3,5,4
    }

    @Test
    public void test4(){
        WaitNoMore wnm = new WaitNoMore();
        int[] durs = new int[]{2,4,6};
        int[] wgts = new int[]{6,5,7};
        System.out.println(wnm.minTotalWaitingTime(durs,wgts));
        assertEquals(8,wnm.minTotalWaitingTime(durs,wgts));
    }

    @Test
    public void test5(){
        WaitNoMore wnm = new WaitNoMore();
        int[] durs = new int[]{1,5,3};
        int[] wgts = new int[]{3,2,1};
        System.out.println(wnm.minTotalWaitingTime(durs,wgts));
        assertEquals(7,wnm.minTotalWaitingTime(durs,wgts));
    }

    @Test
    public void test6(){
        WaitNoMore wnm = new WaitNoMore();
        int[] durs = new int[]{1,5,3};
        int[] wgts = new int[]{3,2,1};
        System.out.println(wnm.minTotalWaitingTime(durs,wgts));
        assertEquals(7,wnm.minTotalWaitingTime(durs,wgts));
    }







}