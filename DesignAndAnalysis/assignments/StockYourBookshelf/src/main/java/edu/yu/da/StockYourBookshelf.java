package edu.yu.da;

/** Implements the StockYourBookshelfI API.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

import java.util.*;

public class StockYourBookshelf implements StockYourBookshelfI {
    private int[][] maxs;
    private Map<String, List<Integer>> sortedMap;
    private String[] keys;
    private boolean ranMaxAmount = false;
    private boolean noSolution = false;
    /** No-op constructor
     */
    public StockYourBookshelf() {
	// no-op, students may change the implementation
    }

    @Override
    public List<Integer> solution() {
        if(!ranMaxAmount){
            throw new IllegalStateException("didn't run maxAmount..() before running solution()");
        }
        if(noSolution){
            return new ArrayList<>();
        }
        int tracker = maxs.length - 1;
        int[] solutions = new int[maxs[0].length];
	    for(int i = maxs[0].length - 1; i >=1 ; i--){
	        while(true) {
	            if(tracker - 1 <0){
	                //have to do more here
	                break;
                }
                if (maxs[tracker][i] != maxs[tracker -1][i]){
                   break;
                }
                tracker--;
            }
	        int init = maxs[tracker][i];
	     //   int add = -1;
	        for(Integer j : sortedMap.get(keys[i])){
	            if(init - j == maxs[tracker-j][i-1]){
	                solutions[i] = j;
	                tracker-=j;
	                break;
                }
                //System.out.println("more to do");
            }
        }
	    solutions[0] = maxs[tracker][0];

	    List<Integer> sol = new ArrayList<>();

	    Map<String, Integer> stringToChoice = new HashMap<>();
	    for(int y = maxs[0].length - 1; y>=0;y--) {
            stringToChoice.put(keys[y],solutions[y]);
        }
	    Arrays.sort(keys);
	    for(String k : keys){
	        sol.add(stringToChoice.get(k));
        }
	    return sol;
       // return Collections.<Integer>emptyList();
    }

    @Override
    public int
	maxAmountThatCanBeSpent
	(final int budget, final Map<String, List<Integer>> seforimClassToTypePrices)
    {
        if(budget < 0){
            throw new IllegalArgumentException("negative budget");
        }
        ranMaxAmount = true;
        sortedMap = new HashMap<>();
        Set<String> keysSet = seforimClassToTypePrices.keySet();
        for(String k : keysSet){
            List<Integer> typesCopy = new ArrayList<>();
            typesCopy.addAll(seforimClassToTypePrices.get(k));
            Collections.sort(typesCopy);
            sortedMap.put(k,typesCopy);
        }
        maxs = new int[budget][seforimClassToTypePrices.keySet().size()]; //budget x axis and amount of items on y axis
        keys = new String[keysSet.size()];
        keysSet.toArray(keys);
        int curKey = 0;
        int max = 0;
        for(int downFirst = 0; downFirst<budget;downFirst++){
            if(sortedMap.get(keys[0]).get(0) >downFirst+1){
                maxs[downFirst][0] = 0;
                continue;
            }
           // int max = 0;
            //for(int j = 0; j < keysSet.size(); j++){
            boolean cont = true;
            while(cont) {
                if(curKey >= sortedMap.get(keys[0]).size()){
                    cont = false;
                    continue;
                }
                if (sortedMap.get(keys[0]).get(curKey) <= downFirst + 1) {
                    max = sortedMap.get(keys[0]).get(curKey);
                    curKey++;
                }else{
                    cont = false;
                }
            }
            maxs[downFirst][0] = max;
           // }
        }

//        if(maxs[budget - 1][0] == 0){
//            return Integer.MIN_VALUE;
//        }
        for(int across = 1; across< seforimClassToTypePrices.keySet().size(); across++){
//            int a = across;
//            int sum = 0;
//            while(a >= 0){
//                sum += sortedMap.get(keys[a]).get(0); //smallest type in this class
//                a--;
//            }
            for(int down = 0; down<budget; down++){
//                if(down<sum){
//                    maxs[down][across] = 0;
//                    continue;
//                }
                max = 0;
                for(Integer val : sortedMap.get(keys[across])){
                    int prev = down - val;
                    if(prev<0){
                        break;
                    }
                    int a = maxs[prev][across - 1];
                    if(a < 1){
                        break;
                    }
                    int num = a + val;
                    if(num> max){
                        max = num;
                    }
                }
                maxs[down][across] = max;

            }
        }
        //System.out.println(Arrays.deepToString(maxs));
        if(maxs[budget - 1][seforimClassToTypePrices.keySet().size()-1] == 0){
            noSolution = true;
            return Integer.MIN_VALUE;
        }
	return maxs[budget - 1][seforimClassToTypePrices.keySet().size()-1];
    }

} // StockYourBookshelf
