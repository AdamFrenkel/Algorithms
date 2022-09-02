package edu.yu.introtoalgs;

/** Specifies the interface for generating a sequence of transportation states
 * that moves the radioactives from src to dest per the requirements doc.
 *
 * @author Avraham Leff
 */

import java.util.*;

public class TransportRadioactives {

  /** Computes a sequence of "transport radioactives" movements between the src
   * and the dest such that all of the initial methium and initial cathium are
   * transported safely from the src to the dest.  Each movement must respect
   * the constraints specified in the requirements doc.
   *
   * @param initialMithium initial amount of mithium (in kg) at the src
   * @param initialCathium initial amount of cathium (in kg) at the src
   * @return List of "transport radioactives" movements between the src and the
   * dest (if such a sequence can be computed), or an empty List if no such
   * sequence can be computed under the specified constraints.
   */
  public static List<TransportationState>
    transportIt(final int initialMithium, final int initialCathium) {
    if(initialCathium<=0||initialMithium<=0){
      throw new IllegalArgumentException("no cath and/or no mith - I think this is error");
    }
    boolean specialCase = false;
    if(!(initialCathium == initialMithium && initialCathium == 1)){
      if(initialCathium>=initialMithium){
        return new ArrayList<TransportationState>();
      }
    }else {
      specialCase = true;
    }
    int caSrc=initialCathium;
    int miSrc=initialMithium;

//    if(initialCathium==initialMithium && initialCathium!=2){
//      return new ArrayList<TransportationState>();
//    }

    HashMap<TransportationStateImpl, TransportationStateImpl> gotTo = new HashMap<>();
    List<TransportationState> returnList = new ArrayList<>();
   // Queue<TransportationStateImpl> queue = new LinkedList<>();
    //TransportationStateImpl ts = new TransportationStateImpl(initialMithium,initialCathium, TransportationState.Location.SRC,initialMithium,initialCathium);
    returnList.add(new TransportationStateImpl(miSrc,caSrc,TransportationState.Location.SRC,initialMithium,initialCathium));
    if(specialCase){
      //TODO: Fill in
      miSrc--;
      caSrc--;
      returnList.add(new TransportationStateImpl(miSrc,caSrc,TransportationState.Location.DEST,initialMithium,initialCathium));
      return returnList;
    }
    while(caSrc>0){
      miSrc--;
      caSrc--;
      returnList.add(new TransportationStateImpl(miSrc,caSrc,TransportationState.Location.DEST,initialMithium,initialCathium));
      caSrc++;
      returnList.add(new TransportationStateImpl(miSrc,caSrc,TransportationState.Location.SRC,initialMithium,initialCathium));
      if(caSrc>0) {
        miSrc--;
        caSrc--;
        returnList.add(new TransportationStateImpl(miSrc, caSrc, TransportationState.Location.DEST, initialMithium, initialCathium));
        if(miSrc!=0) {
          miSrc++;
          returnList.add(new TransportationStateImpl(miSrc, caSrc, TransportationState.Location.SRC, initialMithium, initialCathium));
        }
      }
    }
    while (miSrc>=2){
    //  System.out.println("here");
      miSrc--;
      miSrc--;
      returnList.add(new TransportationStateImpl(miSrc, caSrc, TransportationState.Location.DEST, initialMithium, initialCathium));
      if(miSrc!=0) {
        miSrc++;
        returnList.add(new TransportationStateImpl(miSrc, caSrc, TransportationState.Location.SRC, initialMithium, initialCathium));
      }
    }
//    miSrc--;
//    returnList.add(new TransportationStateImpl(miSrc, caSrc, TransportationState.Location.DEST, initialMithium, initialCathium));

    //   queue.add(ts1);
  //  HashSet<TransportationStateImpl> alreadyChecked = new HashSet<>();
 //  boolean notSolved = true;
   // while(notSolved){
//    TransportationStateImpl last = ts1;
//    while(!queue.isEmpty()){
//      System.out.println("here");
//    TransportationStateImpl ts = queue.poll();
////      if(alreadyChecked.contains(ts)){
////        continue;
////      }
//      if (ts.truckLocation() == TransportationState.Location.SRC) {
//        // List<TransportationStateImpl> list = new ArrayList<>();
//        if (ts.getMithiumSrc() >= 2 && ts.getMithiumSrc() - 2 >= ts.getCathiumSrc()) {
//          TransportationStateImpl ts2 = new TransportationStateImpl(ts.getMithiumSrc() - 2, ts.getCathiumSrc(), TransportationState.Location.DEST, initialMithium, initialCathium);
//          queue.add(ts2);
//          gotTo.put(ts2,ts);
//          if (ts2.getMithiumSrc() == 0 && ts2.getCathiumSrc() == 0) {
//            last = ts2;
//          //  notSolved = false;
//            break;
//            //  notSolved = false;
//          }
//        }
//        if (ts.getCathiumSrc() >= 2 && (ts.getCathiumDest() + 2 <= ts.getMithiumDest() || ts.getMithiumDest() == 0)) {
//          TransportationStateImpl ts2 = new TransportationStateImpl(ts.getMithiumSrc(), ts.getCathiumSrc()-2, TransportationState.Location.DEST, initialMithium, initialCathium);
//          queue.add(ts2);
//          gotTo.put(ts2,ts);
//          if (ts2.getMithiumSrc() == 0 && ts2.getCathiumSrc() == 0) {
//            last = ts2;
//           // notSolved = false;
//            break;
//            //  notSolved = false;
//          }
//        }
//        if (ts.getCathiumSrc() >= 1 && ts.getMithiumSrc() >= 1) {
//          TransportationStateImpl ts2 = new TransportationStateImpl(ts.getMithiumSrc() - 1, ts.getCathiumSrc() - 1, TransportationState.Location.DEST, initialMithium, initialCathium);
//          queue.add(ts2);
//          gotTo.put(ts2,ts);
//          if (ts2.getMithiumSrc() == 0 && ts2.getCathiumSrc() == 0) {
//           // notSolved = false;
//            last = ts2;
//            break;
//            //  notSolved = false;
//          }
//        }
////        if (ts.getCathiumSrc() >= 1 && (ts.getMithiumDest() >= 1 + ts.getCathiumDest() || ts.getMithiumDest() == 0)) {
////          TransportationStateImpl ts2 = new TransportationStateImpl(ts.getMithiumSrc(), ts.getCathiumSrc() - 1, TransportationState.Location.DEST, initialMithium, initialCathium);
////          queue.add(ts2);
////          gotTo.put(ts2,ts);
////          if (ts2.getMithiumSrc() == 0 && ts2.getCathiumSrc() == 0) {
////            notSolved = false;
////            last = ts2;
////            break;
////            //  notSolved = false;
////          }
////        }
////        if (ts.getMithiumSrc() >= 1 && (ts.getMithiumSrc() - 1 >= ts.getCathiumSrc())) {
////          TransportationStateImpl ts2 = new TransportationStateImpl(ts.getMithiumSrc() - 1, ts.getCathiumSrc(), TransportationState.Location.DEST, initialMithium, initialCathium);
////          queue.add(ts2);
////          gotTo.put(ts2,ts);
////          if (ts2.getMithiumSrc() == 0 && ts2.getCathiumSrc() == 0) {
////            notSolved = false;
////            last = ts2;
////            break;
////            //  notSolved = false;
////          }
//       // }
//        // possibilities.put(ts, list);
//      } else {
//        // List<TransportationStateImpl> list2 = new ArrayList<>();
////        if (ts.getMithiumDest() >= 2 && ts.getMithiumDest() - 2 >= ts.getCathiumDest()) {
////          queue.add(new TransportationStateImpl(ts.getMithiumSrc() + 2, ts.getCathiumSrc(), TransportationState.Location.SRC, initialMithium, initialCathium));
////        }
////        if (ts.getCathiumDest() >= 2 && (ts.getCathiumSrc() + 2 <= ts.getMithiumSrc() || ts.getMithiumSrc() == 0)) {
////          queue.add(new TransportationStateImpl(ts.getMithiumSrc(), ts.getCathiumSrc() + 2, TransportationState.Location.SRC, initialMithium, initialCathium));
////        }
////        if (ts.getCathiumDest() >= 1 && ts.getMithiumDest() >= 1) {
////          queue.add(new TransportationStateImpl(ts.getMithiumSrc() + 1, ts.getCathiumDest() + 1, TransportationState.Location.SRC, initialMithium, initialCathium));
////        }
//        if (ts.getCathiumDest() >= 1 && (ts.getCathiumSrc() + 1 <= ts.getMithiumSrc() || ts.getMithiumSrc() == 0)) {
//          TransportationStateImpl ts3 = new TransportationStateImpl(ts.getMithiumSrc(), ts.getCathiumDest() + 1, TransportationState.Location.SRC, initialMithium, initialCathium);
//          queue.add(ts3);
//          gotTo.put(ts3,ts);
//        }
//        if (ts.getMithiumDest() >= 1 && ts.getMithiumDest() - 1 >= ts.getCathiumDest()) {
//          TransportationStateImpl ts3 = new TransportationStateImpl(ts.getMithiumSrc() + 1, ts.getCathiumDest(), TransportationState.Location.SRC, initialMithium, initialCathium);
//          gotTo.put(ts3,ts);
//          queue.add(ts3);
//        }
//
//        // possibilities.put(ts, list2`);
//      }
//
//      alreadyChecked.add(ts);
//    }
//    returnList.add(last);
//    while(gotTo.get(last) !=null){
//      System.out.println("here2");
//      last = gotTo.get(last);
//      returnList.add(last);
//      if(last.getCathiumSrc()==0&&last.getMithiumSrc()==0){
//        break;
//      }
//    }
//
//
//   // }
//    //System.out.println(ts);
//    //returnList.add(ts);
//
//
////    if (initialCathium == 0) {
////      //special case
////    }
////      int miSrc = initialMithium;
////      int caSrc = initialCathium;
////      int miDst = 0;
////      int caDst = 0;
////    System.out.println("initial mith" + initialMithium + "\n initial cath " + initialCathium);
////    if(initialCathium!=initialMithium) {
//
//
//      //miSrc--;
//
//      //ts.updateCaSrc(-1);
////      caSrc--;
////      //ts.updateCaDst(1);
////     // if(ts.getMithiumSrc()!=ts.getCathiumSrc()) {
////      boolean inTruck = false;
////      while (miSrc > caSrc + 1) {
////        inTruck = true;
////      //  ts.setTruckLoc(TransportationState.Location.SRC);
////        miSrc--;
////        //TransportationStateImpl ts = new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.SRC,initialMithium,initialCathium);
////        returnList.add(new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.SRC,initialMithium,initialCathium));
////       // System.out.println(ts);
////       // ts.setTruckLoc(TransportationState.Location.DEST);
////        miDst++;
////        caDst++;
////        TransportationStateImpl ts = new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.DEST,initialMithium,initialCathium);
////        ts.setMiDst(miDst);
////        returnList.add(ts);
////        caDst--;
////        //System.out.println(ts);
////      }
////      if (inTruck = false) {
////        miSrc--;
////        caSrc--;
////
////      }
////      while(caSrc!=0){
////       // ts.setTruckLoc(TransportationState.Location.SRC);
//////        caSrc--;
//////        returnList.add(new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.SRC,initialMithium,initialCathium));
//////       // System.out.println(ts);
//////       // ts.setTruckLoc(TransportationState.Location.DEST);
//////        caDst++;
//////        TransportationStateImpl ts1 = new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.DEST,initialMithium,initialCathium);
//////        ts1.setCaDst(caDst);
//////        returnList.add(ts1);
//////        //System.out.println(ts);
//////       // ts.setTruckLoc(TransportationState.Location.SRC);
//////        miSrc--;
//////        returnList.add(new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.SRC,initialMithium,initialCathium));
//////       // System.out.println(ts);
//////        if(caSrc!=0) {
//////          //ts.setTruckLoc(TransportationState.Location.DEST);
//////          miDst++;
//////          TransportationStateImpl ts2 = new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.DEST,initialMithium,initialCathium);
//////          ts2.setMiDst(miDst);
//////          returnList.add(ts2);
//////          //System.out.println(ts);
//////        }
////      }
////      //ts.setTruckLoc(TransportationState.Location.DEST);
////      miDst++;
////      caDst++;
////      TransportationStateImpl ts3 = new TransportationStateImpl(miSrc,caSrc, TransportationState.Location.DEST,initialMithium,initialCathium);
////      ts3.setCaDst(caDst);
////      ts3.setMiDst(miDst);
////      returnList.add(ts3);
////      //System.out.println(ts);
////
////
////     // }
////    }
//
//  // Collections.reverse(returnList);
    return returnList;
  } // transportIt

//  private class Graph {
//    private int V;          //number of verticies
//    private int E;                //number of edges
//    //private List<TransportationStateImpl>[] adj;  //adjacency lists
//    private HashMap<TransportationStateImpl, List<TransportationStateImpl>> adj= new HashMap<>();
//    private Graph() {
//     // this.V = V;
//    }
//    //    private Graph(In in){
////      this(in.readInt());
////      int E = in.readInt();
////      for(int i = 0; i < E; i++){
////        int v = in.readInt();
////        int w = in.readInt();
////        addEdge(v,w);
////      }
////    }
//
//    private int V() {
//      return V;
//    }
//
//    private int E() {
//      return E;
//    }
//
//    private void addEdge(TransportationStateImpl v, TransportationStateImpl w) {
//      if(adj.get(v) == null){
//        adj.put(v,new ArrayList<TransportationStateImpl>());
//        V++;
//      }
//      List<TransportationStateImpl> l = adj.get(v);
//      l.add(w);
//      if(adj.get(w) == null){
//        adj.put(w,new ArrayList<TransportationStateImpl>());
//      }
//      List<TransportationStateImpl> l2 = adj.get(v);
//      l2.add(v);
////      adj.put(v,w);
////      adj.put(w,v);
//      E++;
//    }
//
//    private Iterable<TransportationStateImpl> adj(TransportationStateImpl v) {
//      if(adj.get(v) == null){
//        return new ArrayList<>();
//      }
//      return adj.get(v);
//    }
//  }
} // TransportRadioactives