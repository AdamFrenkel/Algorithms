package edu.yu.introtoalgs;

public class EstimateSecretAlgorithmsClient2 {
    /**
     *  This code was mainly developed by Robert Sedgewick & Kevin Wayne
     *  Edited by Adam Frenkel
     */
    public static double timeTrialAlg1(int n){
        SecretAlgorithm1 secretAlgorithm =  new SecretAlgorithm1();
        secretAlgorithm.setup(n);
        long startTime = System.nanoTime();
        secretAlgorithm.execute();
        return System.nanoTime() - startTime;
    }
    public static double timeTrialAlg2(int n){
        SecretAlgorithm2 secretAlgorithm =  new SecretAlgorithm2();
        secretAlgorithm.setup(n);
        long startTime = System.nanoTime();
        secretAlgorithm.execute();
        return System.nanoTime() - startTime;
    }
    public static double timeTrialAlg3(int n){
        SecretAlgorithm3 secretAlgorithm =  new SecretAlgorithm3();
        secretAlgorithm.setup(n);
        long startTime = System.nanoTime();
        secretAlgorithm.execute();
        return System.nanoTime() - startTime;
    }
    public static double timeTrialAlg4(int n){
        SecretAlgorithm4 secretAlgorithm =  new SecretAlgorithm4();
        secretAlgorithm.setup(n);
        long startTime = System.nanoTime();
        secretAlgorithm.execute();
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        System.out.println("Buffering Alg 1...");
        for (int j = 1; j <= 8; j++) {
            double time = timeTrialAlg1(10000);
            System.out.println(j +"/8");
        }
        System.out.println("Buffering Alg 2...");
        for (int j = 1; j<=1000; j++) {
            System.out.println(j+"/1000");
            double time = timeTrialAlg2(536800000);
        }
        System.out.println("Buffering Alg 3...");
        for (int j = 1; j<=1000; j++) {
            System.out.println(j+"/1000");
            double time = timeTrialAlg3(40000);
        }
        System.out.println("Buffering Alg 4...");
        for (int j = 1; j<=99999999; j++) {
            System.out.println(j+"/99999999");
            double time = timeTrialAlg4(536800000);
        }


        double[][] storeNums1 = runAlg1();
        double[][] storeNums2 = runAlg2();
        double[][] storeNums3 = runAlg3();
        double[][] storeNums4 = runAlg4();


        System.out.println("Averages:");
        System.out.println("Alg1:");
        int n = 250;
        double previous = (storeNums1[0][0] + storeNums1[0][1] + storeNums1[0][2])/3;
        for(int k = 1; k<=7;k++){
            double tim = (storeNums1[k][0] + storeNums1[k][1] + storeNums1[k][2])/3;
            System.out.printf("%7d %7.1f   ",n, tim);
            System.out.printf("%5.1f\n", tim / previous);
            previous = tim;
            n*=2;
        }
        System.out.println("Alg2:");
        n = 250;
        previous = (storeNums2[0][0] + storeNums2[0][1] + storeNums2[0][2])/3;
        for(int k = 1; k<=22;k++){
            double tim = (storeNums2[k][0] + storeNums2[k][1] + storeNums2[k][2])/3;
            System.out.printf("%7d %7.1f   ",n, tim);
            System.out.printf("%5.1f\n", tim / previous);
            previous = tim;
            n*=2;
        }
        System.out.println("Alg3:");
        n = 250;
        previous = (storeNums3[0][0] + storeNums3[0][1] + storeNums3[0][2])/3;
        for(int k = 1; k<=10;k++){
            double tim = (storeNums3[k][0] + storeNums3[k][1] + storeNums3[k][2])/3;
            System.out.printf("%7d %7.1f   ",n, tim);
            System.out.printf("%5.1f\n", tim / previous);
            previous = tim;
            n*=2;
        }
        System.out.println("Alg4:");
        n = 250;
        previous = (storeNums4[0][0] + storeNums4[0][1] + storeNums4[0][2])/3;
        for(int k = 1; k<=22;k++){
            double tim = (storeNums4[k][0] + storeNums4[k][1] + storeNums4[k][2])/3;
            System.out.printf("%7d %7.1f   ",n, tim);
            System.out.printf("%5.1f\n", tim / previous);
            previous = tim;
            n*=2;
        }
    }

    private static double[][] runAlg4() {
        double[][] storeNums = new double[23][3];
        int x = 0;
        while (x < 3) {
            double prev = timeTrialAlg4(125);
            storeNums[0][x] = prev;
            int p = 1;
            for (int n = 250; n <= 536870911; n += n) {
                double time = timeTrialAlg4(n);
                storeNums[p][x] = time;
                System.out.printf("%7d %7.1f   ", n, time);
                System.out.printf("%5.1f\n", time / prev);
                prev = time;
                p++;
            }
            x++;
        }
        return storeNums;
    }

    private static double[][] runAlg3() {
        System.out.println("Results for Alg 3");
        double[][] storeNums = new double[23][3];
//        System.out.println("Buffering...");
//        for (int j = 0; j<1000; j++) {
//            System.out.println(j);
//            double time = timeTrial(536800000);
//        }
        int x = 0;
        while (x < 3) {

            double prev = timeTrialAlg3(125);
            storeNums[0][x] = prev;
            int p = 1;
            for (int n = 250; n <= 1024000; n += n) {
                double time = timeTrialAlg3(n);
                storeNums[p][x] = time;
                System.out.printf("%7d %7.1f   ", n, time);
                System.out.printf("%5.1f\n", time / prev);
                prev = time;
                p++;
            }
            x++;
        }
        return storeNums;
    }

    private static double[][] runAlg2() {
        double[][] storeNums2 = new double[23][3];
//        System.out.println("Buffering...");
//        for (int j = 0; j<1000; j++) {
//            System.out.println(j);
//            double time = timeTrialAlg2(536800000);
//        }
        System.out.println("Results for Alg 2");
        int x = 0;
        while (x < 3) {

            double prev = timeTrialAlg2(125);
            storeNums2[0][x] = prev;
            int p = 1;
            for (int n = 250; n <= 536870911; n += n) {
                double time = timeTrialAlg2(n);
                storeNums2[p][x] = time;
                System.out.printf("%7d %7.1f   ", n, time);
                System.out.printf("%5.1f\n", time / prev);
                prev = time;
                p++;
            }
            x++;
        }
        return storeNums2;
    }

    private static double[][] runAlg1() {
        double[][] storeNums1 = new double[8][3];
        System.out.println("Results for Alg 1");
        int x = 0;
        while (x < 3) {

            double prev = timeTrialAlg1(125);
            storeNums1[0][x] = prev;
            int p = 1;
            for (int n = 250; n <= 16000; n += n) {
                double time = timeTrialAlg1(n);
                storeNums1[p][x] = time;
                System.out.printf("%7d %7.1f   ", n, time);
                System.out.printf("%5.1f\n", time / prev);
                prev = time;
                p++;
            }
            x++;
        }
        return storeNums1;
    }
}