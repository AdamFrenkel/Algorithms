package edu.yu.introtoalgs;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnthroChassidusTest {
    @Test
    public void testBasics(){
        int[] a = new int[]{0,0,0,1,3,5,12};
        int[] b = new int[]{2,4,6,3,5,7,11};
        AnthroChassidus aC = new AnthroChassidus(100,a,b);
        assertEquals(4,aC.nShareSameChassidus(4));
        assertEquals(93,aC.getLowerBoundOnChassidusTypes());
        int[] c = new int[]{0,0,0,11,3,0,0,0,4,1,0,12,12,10};
        int[] d = new int[]{1,2,1,12,4,2,2,4,2,3,0,10,11,13};
        AnthroChassidus aC2 = new AnthroChassidus(14,c,d);
        assertEquals(7,aC2.getLowerBoundOnChassidusTypes());
        assertEquals(1,aC2.nShareSameChassidus(8));
        assertEquals(1,aC2.nShareSameChassidus(5));
        assertEquals(4,aC2.nShareSameChassidus(13));
        assertEquals(4,aC2.nShareSameChassidus(11));
        assertEquals(5,aC2.nShareSameChassidus(0));
        assertEquals(5,aC2.nShareSameChassidus(2));
        int[] e = new int[]{0,1,2,3,4,5,6,7};
        int[] f = new int[]{1,2,3,4,5,6,7,8};
        AnthroChassidus aC3 = new AnthroChassidus(10,e,f);
        assertEquals(2,aC3.getLowerBoundOnChassidusTypes());
        assertEquals(1,aC3.nShareSameChassidus(9));
        assertEquals(9,aC3.nShareSameChassidus(8));
        assertEquals(9,aC3.nShareSameChassidus(0));


    }

    @Test//(enabled=true, timeOut=50)
 public void testMinimal() {
 //SoftAssert softAssert = new SoftAssert();
// There are 50 different people in the group, and the only fact I know is
 // that person 0 and person 1 share the same chassidus
 final int n = 50;
 final int[] a = {0};
 final int[] b = {1};

final AnthroChassidus ac = new AnthroChassidus(n, a, b);
 assertEquals(ac.getLowerBoundOnChassidusTypes(), 49);
 assertEquals(ac.nShareSameChassidus(0), 2);
 assertEquals(ac.nShareSameChassidus(1), 2);
//softAssert.assertAll();
}
/**************************
 * Good test, but commented out so as not to interfere with Professor's tests, bc this takes a long time
 */
//    @Test
//    public void testSpeedOfConstructor(){
//        /**
//         *  This code was mainly developed by Robert Sedgewick & Kevin Wayne
//         *  Edited by Adam Frenkel
//         */
//
//        System.out.println("Buffering...");
//        System.out.println("0/10");
//        for (int j = 1; j <= 10; j++) {
//            double time = timeTrialAlg1(100000000);
//            System.out.println(j +"/10");
//        }
//
//        System.out.println("Alg1:");
//        double[][] storeNums1 = runAlg1();
//        int n = 250;
//        double previous = (storeNums1[0][0] + storeNums1[0][1] + storeNums1[0][2])/3;
//        System.out.println("Averages:");
//        for(int k = 1; k<=19;k++){
//            double tim = (storeNums1[k][0] + storeNums1[k][1] + storeNums1[k][2])/3;
//            System.out.printf("%7d %7.1f   ",n, tim);
//            System.out.printf("%5.1f\n", tim / previous);
//            previous = tim;
//            n*=2;
//        }
//
//
//
//    }
//    public double timeTrialAlg1(int n){
//        int[] a = new int[n+10000];
//        int[] b = new int[n+10000];
//        for(int i=0; i<n+10000; i++){
//            int c = (int) (Math.random() * (n-1));
//            int d = (int) (Math.random() * (n-1));
//            a[i] = c;
//            b[i] = d;
//        }
//        long startTime = System.nanoTime();
//        AnthroChassidus aC = new AnthroChassidus(n,a,b);
//        return System.nanoTime() - startTime;
//    }
//    private  double[][] runAlg1() {
//        double[][] storeNums1 = new double[20][3];
//        System.out.println("Results for Alg 1");
//        int x = 0;
//        while (x < 3) {
//
//            double prev = timeTrialAlg1(125);
//            storeNums1[0][x] = prev;
//            int p = 1;
//            for (int n = 250; n <= 65536000; n += n) {
//                double time = timeTrialAlg1(n);
//                storeNums1[p][x] = time;
//                System.out.printf("%7d %7.1f   ", n, time);
//                System.out.printf("%5.1f\n", time / prev);
//                prev = time;
//                p++;
//            }
//            x++;
//        }
//        return storeNums1;
//    }

}