package edu.yu.da;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultiMergeTest {
    @Test
    public void test1(){
        int[][] arrs = new int[][]{
                {2,4,6,9,12},
                {1,3,7,10,13},
                {0,5,8,11,14}
        };
        MultiMerge m = new MultiMerge();
        m.merge(arrs);
        assertEquals(1, m.getNCombinedMerges());
    }

    @Test
    public void test2(){
        int[][] arrs = new int[][]{
                {2,4,6},
                {1,3,5},
                {0,8,11},
                {7,9,10}
        };
        MultiMerge m = new MultiMerge();
        m.merge(arrs);
        assertEquals(2, m.getNCombinedMerges());
    }
    @Test
    public void test3(){
        int[][] arrs = new int[][]{
                {2, 4, 6},
                {29,30,31},
                {8, 11, 26},
                {12, 13, 14},
                {7, 9, 10},
                {15,16,24},
                {18,19,20},
                {21,22,23},
                {0,17,26},
                {25,27,28},//10th row
                {1, 3, 5},
                {35,36,37},
                {32,33,34},
                {38,39,40}

        };
        MultiMerge m = new MultiMerge();
        m.merge(arrs);
        assertEquals(3, m.getNCombinedMerges());
    }

}