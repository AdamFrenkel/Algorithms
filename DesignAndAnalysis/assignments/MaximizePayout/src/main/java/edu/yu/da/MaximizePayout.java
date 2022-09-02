package edu.yu.da;

/** Implements the MaximizePayoutI API.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

import java.util.*;

public class MaximizePayout implements MaximizePayoutI {

  /** No-op constructor
   */
  public MaximizePayout() {
    // no-op, students may change the implementation
  }

  @Override
  public long max(final List<Long> A, final List<Long> B) {
    if(A.size() != B.size() || A.contains(null) || B.contains(null) || A.size() == 0 || B.size() == 0 ){
      throw new IllegalArgumentException("Bad lists");
    }
    Collections.sort(A);
    Collections.sort(B);
    Iterator<Long> IA = A.listIterator();
    Iterator<Long> IB = B.listIterator();
    long ret = 1;
    while(IA.hasNext()){
      long aCur = IA.next();
      long bCur = IB.next();
      ret *= Math.pow(aCur,bCur);
    }


    return ret;
  }

} // MaximizePayout
