package edu.yu.da;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MaximizePayoutTest {
    @Test
    public void testBasics(){
        List<Long> A = new ArrayList<>();
        List<Long> B = new ArrayList<>();
        A.add(2L);
        A.add(3L);
//        A.add(5L);
//        A.add(3L);
//        A.add(4L);

        B.add(1L);
        B.add(4L);
//        B.add(7L);
//        B.add(8L);
//        B.add(9L);
        MaximizePayout mp = new MaximizePayout();
        System.out.println(mp.max(A,B));


    }
    @Test
    public void testBasics2(){
        List<Long> A = new ArrayList<>();
        List<Long> B = new ArrayList<>();
        A.add(2L);
        A.add(5L);
        A.add(3L);
//        A.add(5L);
//        A.add(3L);
//        A.add(4L);

        B.add(1L);
        B.add(4L);
        B.add(6L);
//        B.add(7L);
//        B.add(8L);
//        B.add(9L);
        MaximizePayout mp = new MaximizePayout();
        System.out.println(mp.max(A,B));
        assertEquals( 2531250L,mp.max(A,B));


    }

}