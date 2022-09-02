package edu.yu.da;

import java.lang.reflect.Array;
import java.util.*;

/** Implements the WaitNoMoreI API.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

public class WaitNoMore implements WaitNoMoreI {

  /** No-op constructor
   */
  public WaitNoMore() {
    // no-op, students may change the implementation
  }
  public class valAndPos{
    double val;
    int pos;
    valAndPos(double val, int pos){
      this.val = val;
      this.pos = pos;
    }

  }
  public Comparator<valAndPos> comp =new Comparator<valAndPos>() {
    @Override
    public int compare(valAndPos o1, valAndPos o2) {
      if(o1.val> o2.val){
        return -1;
      }
      if(o1.val < o2.val){
        return 1;
      }
      return 0;
    }
  };

  @Override
  public int minTotalWaitingTime(final int[] durations, final int[] weights) {
    valAndPos[] stats = new valAndPos[durations.length];
    for(int i = 0; i < durations.length; i++){
      double weight = (double) weights[i];
      double dur = (double) durations[i];
      stats[i] = new valAndPos(weight/dur,i);
    }
    Arrays.sort(stats, this.comp);
    int klal =0;
    int prat = 0;
    for(int j = 0; j + 1 < durations.length; j++){ //+1 bc last dur doesn't matter!
      prat += durations[stats[j].pos];
      klal += prat;
    }
//    for(valAndPos vp : stats){
////      System.out.println("pos: " + vp.pos+ ", val: " + vp.val);
//
//    }

    return klal;

//    HashMap<Integer, List<Integer>> mapDtoW = new HashMap<>();
//    for(int i = 0; i<durations.length; i++){
//      if(mapDtoW.get(durations[i]) == null){
//        List<Integer> list = new ArrayList<>();
//        list.add(weights[i]);
//        mapDtoW.put(durations[i],list);
//      }else{
//        List<Integer> listA = mapDtoW.get(durations[i]);
//        listA.add(weights[i]);
//      }
//    }

//    HashMap<Integer, List<Integer>> mapWtoD = new HashMap<>();
//    for(int i = 0; i<durations.length; i++){
//      if(mapWtoD.get(weights[i]) == null){
//        List<Integer> list = new ArrayList<>();
//        list.add(durations[i]);
//        mapWtoD.put(weights[i],list);
//      }else{
//        List<Integer> listA = mapWtoD.get(weights[i]);
//        listA.add(durations[i]);
//      }
//    }
//
//    Set<Integer> keys = mapWtoD.keySet();
//    //List<Integer> listOfKeys = new ArrayList<>(setKeys);
//   // Collections.sort(listOfKeys, Collections.reverseOrder());
//    for(Integer j : keys){
//      Collections.sort(mapWtoD.get(j),Collections.reverseOrder());
//    }
//
//    int tracker = 0;
//    int ret = 0;
//    for(int k = 0; k<durations.length; k++){
//      int max = Integer.MIN_VALUE;
//      int maxsDur = -1;
//      int maxsWgt = -1;
//      for(Integer weight : keys){
//        List<Integer> durs = mapWtoD.get(weight);
//        int dur = durs.get(0);
//        int amnt = (dur * weight) + (tracker * weight);
//        if(amnt > max){
//          max = amnt;
//          maxsDur = dur;
//          maxsWgt =weight;
//        }else{
//          if(amnt == max && weight>maxsWgt){
//            max = amnt;
//            maxsDur = dur;
//            maxsWgt =weight;
//          }
//        }
//      }
//      ret += tracker;
//      tracker +=maxsDur;
//      mapWtoD.get(maxsWgt).remove(0); //removing the element form the list
//      if(mapWtoD.get(maxsWgt).size() == 0){
//        keys.remove(maxsWgt);
//      }
//    }
//
//    return ret;











//    Arrays.sort(durations);
//    Arrays.sort(weights);
//    int total = 0;
//    //int wTotal = 0;
//    for(int j = 0 ; j<durations.length; j++){
//      int dPointer = durations.length-1;
//      int wPointer = durations.length-1;
//
//    }

   // return -1;
  }
} // WaitNoMore
