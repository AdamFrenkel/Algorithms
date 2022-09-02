package edu.yu.da;

import java.util.Arrays;
import java.util.HashMap;

public class MultiMerge extends MultiMergeBase {
    int[] extra = null;
   // int[] complete;
    @Override
    public int[] merge(int[][] arrays) {
     //   int[] sortArr = new int[arrays[0].length * arrays.length];
        return this.miniMerge(arrays);
//        boolean haveOdd = false;
//        if (!(arrays.length % 2==0)){
//            haveOdd = true;
//        }
//        int[][]sortDoubleArr = new int[arrays.length/2][arrays[0].length*2];
//        int down = -1;
//        //int across = -1;
//        for(int i = 0; i+1 < arrays.length; i+=2) {
//            int a = 0;
//            int b = 0;
//            down++;
//            for(int across = 0; across<sortDoubleArr[0].length; across++){
//                if(a>=arrays[0].length){
//                    sortDoubleArr[down][across] = arrays[i+1][b];
//                    b++;
//                    continue;
//                }
//                if(b>=arrays[0].length){
//                    sortDoubleArr[down][across] = arrays[i][a];
//                    a++;
//                    continue;
//                }
//                if(arrays[i][a] <= arrays[i+1][b]){
//                    sortDoubleArr[down][across] = arrays[i][a];
//                    a++;
//                    continue;
//                }
//                if(arrays[i][a] > arrays[i+1][b]){
//                    sortDoubleArr[down][across] = arrays[i+1][b];
//                    b++;
//                }
//            }
//
//        }
////        if(arrTracker+1<sortArr.length){
////            for(int j = 0; j<arrays[0].length; j++){
////                arrTracker++;
////                sortArr[arrTracker] = arrays[arrays.length-1][j];
////            }
////        }
//        System.out.println(Arrays.deepToString(sortDoubleArr));
//        return new int[0];
    }

    private int[] miniMerge(int[][] arrays) {
        combinedAMerge();
        boolean haveOdd = false;
        if (!(arrays.length % 2==0)){
            haveOdd = true;
        }
        HashMap<Integer,int[]> halfArrays = new HashMap<>();
        int curArrNum = 0;
        for (int i = 0; i + 1 < arrays.length; i += 2) {
            curArrNum++;
            int[] curArr = new int[arrays[i].length + arrays[i + 1].length];
            halfArrays.put(curArrNum,curArr);
            int a = 0;
            int b = 0;
            for (int across = 0; across < curArr.length; across++) {
                if (a >= arrays[i].length) {
                    curArr[across] = arrays[i + 1][b];
                    b++;
                    continue;
                }
                if (b >= arrays[i + 1].length) {
                    curArr[across] = arrays[i][a];
                    a++;
                    continue;
                }
                if (arrays[i][a] <= arrays[i + 1][b]) {
                    curArr[across] = arrays[i][a];
                    a++;
                    continue;
                }
                if (arrays[i][a] > arrays[i + 1][b]) {
                    curArr[across] = arrays[i + 1][b];
                    b++;
                }
            }
        }
        if(haveOdd){
            int[] oldArr1 = halfArrays.get(curArrNum);
            int[] oldArr2 = arrays[arrays.length-1];
            int[] lastArr = new int[oldArr1.length + oldArr2.length];
            halfArrays.put(curArrNum,lastArr);
            int a = 0;
            int b = 0;
            for (int across = 0; across < lastArr.length; across++) {
                if (a >= oldArr1.length) {
                    lastArr[across] = oldArr2[b];
                    b++;
                    continue;
                }
                if (b >= oldArr2.length) {
                    lastArr[across] = oldArr1[a];
                    a++;
                    continue;
                }
                if (oldArr1[a] <= oldArr2[b]) {
                    lastArr[across] = oldArr1[a];
                    a++;
                    continue;
                }
                if (oldArr1[a] > oldArr2[b]) {
                    lastArr[across] = oldArr2[b];
                    b++;
                }
            }
        }
        if(halfArrays.size() != 1) {
            halfArrays = this.miniMerge2(halfArrays);
        }
        System.out.println(Arrays.toString(halfArrays.get(1)));
//        if(!haveOdd) {
//            int[][] sortDoubleArr = new int[arrays.length / 2][arrays[0].length * 2];
//            int down = -1;
//            //int across = -1;
//            for (int i = 0; i + 1 < arrays.length; i += 2) {
//                int a = 0;
//                int b = 0;
//                down++;
//                for (int across = 0; across < sortDoubleArr[0].length; across++) {
//                    if (a >= arrays[0].length) {
//                        sortDoubleArr[down][across] = arrays[i + 1][b];
//                        b++;
//                        continue;
//                    }
//                    if (b >= arrays[0].length) {
//                        sortDoubleArr[down][across] = arrays[i][a];
//                        a++;
//                        continue;
//                    }
//                    if (arrays[i][a] <= arrays[i + 1][b]) {
//                        sortDoubleArr[down][across] = arrays[i][a];
//                        a++;
//                        continue;
//                    }
//                    if (arrays[i][a] > arrays[i + 1][b]) {
//                        sortDoubleArr[down][across] = arrays[i + 1][b];
//                        b++;
//                    }
//                }
//
//            }
//        }else{
//            this.extra = arrays[arrays.length-1];
//        }
////        if(arrTracker+1<sortArr.length){
////            for(int j = 0; j<arrays[0].length; j++){
////                arrTracker++;
////                sortArr[arrTracker] = arrays[arrays.length-1][j];
////            }
////        }
//        System.out.println(Arrays.deepToString(sortDoubleArr));
//        if(sortDoubleArr.length!=1){
//            miniMerge(sortDoubleArr);
//        }else{
//            if(extra != null) {
//                int[] sortArr = new int[sortDoubleArr[0].length + extra.length];
//                // int[][]sortdoubleArr
//                int arrTracker = -1;
//                // for(int i = 0; i < sortArr.length; i++) {
//                int a = 0;
//                int b = 0;
//                for (int j = 0; j < sortArr.length; j++) {
//                    arrTracker++;
//                    if (b >= extra.length) {
//                        sortArr[arrTracker] = sortDoubleArr[0][a];
//                        a++;
//                        continue;
//                    }
//                    if (a >= sortDoubleArr[0].length) {
//                        sortArr[arrTracker] = extra[b];
//                        b++;
//                        continue;
//                    }
//                    if (sortDoubleArr[0][a] <= extra[b]) {
//                        sortArr[arrTracker] = sortDoubleArr[0][a];
//                        a++;
//                        continue;
//                    }
//                    if (sortDoubleArr[0][a] > extra[b]) {
//                        sortArr[arrTracker] = extra[b];
//                        b++;
//                    }
//                }
//                System.out.println(Arrays.toString(sortArr));
//                return sortArr;
//            }
//        }

        //}
        return halfArrays.get(1);
    }

    private HashMap<Integer, int[]> miniMerge2(HashMap<Integer,int[]> halfArrays) {
        combinedAMerge();
        boolean haveOdd = false;
        if (!(halfArrays.size() % 2==0)){
            haveOdd = true;
        }

       HashMap<Integer,int[]> newArrays = new HashMap<>();
        int curArrNum = 0;
        for (int i = 1; i + 1 <= halfArrays.size(); i += 2) {
            curArrNum++;
            int[] arr1 = halfArrays.get(i);
            int[] arr2 = halfArrays.get(i + 1);
            int[] curArr = new int[arr1.length + arr2.length];
            newArrays.put(curArrNum,curArr);
            int a = 0;
            int b = 0;
            for (int across = 0; across < curArr.length; across++) {
                if (a >= arr1.length) {
                    curArr[across] = arr2[b];
                    b++;
                    continue;
                }
                if (b >= arr2.length) {
                    curArr[across] = arr1[a];
                    a++;
                    continue;
                }
                if (arr1[a] <= arr2[b]) {
                    curArr[across] = arr1[a];
                    a++;
                    continue;
                }
                if (arr1[a] > arr2[b]) {
                    curArr[across] = arr2[b];
                    b++;
                }
            }
        }
        if(haveOdd){
            int[] oldArr1 = newArrays.get(curArrNum);
            int[] oldArr2 = halfArrays.get(halfArrays.size());
            int[] lastArr = new int[oldArr1.length + oldArr2.length];
            newArrays.put(curArrNum,lastArr);
            int a = 0;
            int b = 0;
            for (int across = 0; across < lastArr.length; across++) {
                if (a >= oldArr1.length) {
                    lastArr[across] = oldArr2[b];
                    b++;
                    continue;
                }
                if (b >= oldArr2.length) {
                    lastArr[across] = oldArr1[a];
                    a++;
                    continue;
                }
                if (oldArr1[a] <= oldArr2[b]) {
                    lastArr[across] = oldArr1[a];
                    a++;
                    continue;
                }
                if (oldArr1[a] > oldArr2[b]) {
                    lastArr[across] = oldArr2[b];
                    b++;
                }
            }
           // System.out.println(Arrays.toString(newArrays.get(curArrNum)));
        }
        if(newArrays.size() != 1) {
            newArrays = this.miniMerge2(newArrays);
        }
        return newArrays;
    }

}
