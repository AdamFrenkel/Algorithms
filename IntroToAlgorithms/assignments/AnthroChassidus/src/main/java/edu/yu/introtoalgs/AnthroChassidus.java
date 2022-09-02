package edu.yu.introtoalgs;

import java.util.ArrayList;
import java.util.List;

/** Defines and implements the AnthroChassidus API per the requirements
 * documentation.
 *
 * @author Avraham Leff
 */

public class AnthroChassidus {
  private WeightedQuickUnionPCUF unionF;
  private int[] roots;
  /** Constructor.  When the constructor completes, ALL necessary processing
   * for subsequent API calls have been made such that any subsequent call will
   * incur an O(1) cost.
   *
   * @param n the size of the underlying population that we're investigating:
   * need not correspond in any way to the number of people actually
   * interviewed (i.e., the number of elements in the "a" and "b" parameters).
   * Must be greater than 2.
   * @param a interviewed people, element value corresponds to a unique "person
   * id" in the range 0..n-1
   * @param b interviewed people, element value corresponds to a unique "person
   * id" in the range 0..n-1.  Pairs of a_i and b_i entries represent the fact
   * that the corresponding people follow the same Chassidus (without
   * specifying what that Chassidus is).
   */
  public AnthroChassidus(final int n, final int[] a, final int[] b) {
      if (n<=2 | a.length != b.length){
          throw new IllegalArgumentException("Invalid input.");
      }
    /*
     * Plan:
     * First make quick find
     * then put all elements in and do union finds
     * then go through the list of elements (which may collect in some type of efficent if deem it
     * neccesary, and beieve it is possible.
     */
    unionF = new WeightedQuickUnionPCUF(n);

    for(int i = 0; i<a.length; i++){
        if(a[i] > (n-1) | a[i] < 0 | b[i] > (n-1) | b[i] < 0){
            throw new IllegalArgumentException("Invalid input in array.");
        }
      unionF.union(a[i],b[i]);
    }

    roots = new int[n];
    for(int j = 0; j<n;j++){
      roots[j] = unionF.find(j);
    }
  }

  /** Return the tightest value less than or equal to "n" specifying how many
   * types of Chassidus exist in the population: this answer is inferred from
   * the interviewers data supplied to the constructor
   *
   * @return tightest possible lower bound on the number of Chassidus in the
   * underlying population.
   */
  public int getLowerBoundOnChassidusTypes() {
    return unionF.count;
  }

  /** Return the number of interviewed people who follow the same Chassidus as
   * this person.
   *
   * @param id uniquely identifies the interviewed person
   * @return the number of interviewed people who follow the same Chassidus as
   * this person.
   */
  public int nShareSameChassidus(final int id) {
//    if(roots[id] == id){ //need this because sedgewick says that
//      return 0;
//    }
      if(id > (roots.length - 1) | id < 0){
          throw new IllegalArgumentException("Invalid input.");
      }
    return (unionF.sz[roots[id]]);
  }

private class WeightedQuickUnionPCUF {
    private int id[];  //parent link (site indexed)
    private int sz[];    //size of components for roots (site indexed)
    private int count;  //# of components (meaning number of tress)

    public WeightedQuickUnionPCUF(int n){
      count = n;
      id = new int[n];
      for(int i =0; i<n; i++){
        id[i] = i;
      }
      sz = new int[n];
      for(int j = 0; j<n; j++){
        sz[j] = 1;
      }

    }
  public int find(int i){
      //add list for path compression
    List<Integer> prev = new ArrayList<>();
        while (i != id[i]) {
          prev.add(i);
          i = id[i];
        }
        for(Integer p : prev){
          id[p] = i;
        }
      return i;
  }
  public void union(int m, int n){
      int a = find(m);
      int b = find(n);
      if(a == b){
        return;
      }
      if(sz[a] < sz[b]){
        id[a] =b;
        sz[b] += sz[a];
      }else{
        id[b] =a;
        sz[a] += sz[b];
      }
      count--;
  }

}







} // class

