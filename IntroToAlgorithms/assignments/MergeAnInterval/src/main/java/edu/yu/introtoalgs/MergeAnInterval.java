package edu.yu.introtoalgs;

import java.util.*;

/** Implements the "Add an Interval To a Set of Intervals" semantics defined in
 * the requirements document.
 * 
 * @author Avraham Leff 
 */

public class MergeAnInterval {

  /** An immutable class, holds a left and right integer-valued pair that
   * defines a closed interval
   *
   * IMPORTANT: students may not modify the semantics of the "left", "right"
   * instance variables, nor may they use any other constructor signature.
   * Students may (are encouraged to) add any other methods that they choose,
   * bearing in mind that my tests will ONLY DIRECTLY INVOKE the constructor
   * and the "merge" method.
   */
  public static class Interval implements Comparable<Interval>{
    public final int left;
    public final int right;

    /** Constructor
     * 
     * @param l the left endpoint of the interval, may be negative
     * @param r the right endpoint of the interval, may be negative
     * @throws IllegalArgumentException if left is >= right
     */
    public Interval(int l, int r) {
      if(l>=r){
        throw new IllegalArgumentException("Left must be smaller than right.");
      }
      this.left = l;
      this.right = r;
    }

    @Override
    public int	compareTo(Interval o) {
      if((o.right == this.right && o.left == this.left)){
        return 0;
      }
	if((o.left <= this.right && o.left >= this.left) || (o.right <= this.right && o.right >= this.left)){
	  return 1;
    }
	if((this.left <= o.right && this.left >= o.left) || (this.right <= o.right && this.right >= o.left)){
	  return 1;
	}
	return -1;
    }

  } // Interval class

  /** Merges the new interval into an existing set of disjoint intervals.
   *
   * @param intervals an set of disjoint intervals (may be empty)
   * @param newInterval the interval to be added
   * @return a new set of disjoint intervals containing the original intervals
   * and the new interval, merging the new interval if necessary into existing
   * interval(s), to preseve the "disjointedness" property.
   * @throws IllegalArgumentException if either parameter is null
   */
  public static Set<Interval> merge(final Set<Interval> intervals, Interval newInterval) {
    if(intervals == null || newInterval == null){
      throw new IllegalArgumentException("null argument");
    }
    Set<Interval> intersToBeMerged = new HashSet<>();
    Set<Interval> returnSet = new HashSet<>();//need this because cant remove from intervals, bc that takes n time
    for(Interval i : intervals){
      if(newInterval.compareTo(i)>=0){
        intersToBeMerged.add(i);
      }
      else{
        returnSet.add(i);
      }
    }
    if(intersToBeMerged.isEmpty()){
      intervals.add(newInterval);
      return intervals;
    }


    int minLeft = Integer.MAX_VALUE;
    int maxRight = Integer.MIN_VALUE;
    intersToBeMerged.add(newInterval);
    for(Interval i : intersToBeMerged){
      if(i.left < minLeft){
        minLeft = i.left;
      }
      if(i.right>maxRight){
        maxRight = i.right;
      }
    }
    returnSet.add(new Interval(minLeft,maxRight));

    return returnSet;
  }
}