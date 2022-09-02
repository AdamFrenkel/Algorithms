package edu.yu.introtoalgs;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/** Implements the CountStringsFJ semantics specified in the requirements
 * document.
 *
 * @author Avraham Leff
 */

public class CountStringsFJ {
  private final String match;
  private final int threshold;
  private final String[] arr;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CountStringsFJ that = (CountStringsFJ) o;
    return threshold == that.threshold && Objects.equals(match, that.match) && Arrays.equals(arr, that.arr);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(match, threshold);
    result = 31 * result + Arrays.hashCode(arr);
    return result;
  }

  @Override
  public String toString() {
    return "CountStringsFJ{" +
            "match='" + match + '\'' +
            ", threshold=" + threshold +
            ", arr=" + Arrays.toString(arr) +
            '}';
  }

  /** Constructor.
   *
   * @param arr the array to process, can't be null or empty
   * @param str the string to match, can't be null, may be empty
   * @param threshold when the length of arr is less than threshold, processing
   * must be sequential; otherwise, processing must use a fork/join, recursive
   * divide-and-conquer strategy.  The parameter must be greater than 0.
   *
   * IMPORTANT: Students must use this constructor, they MAY NOT add another
   * constructor.
   */
  public CountStringsFJ(final String[] arr, final String str, final int threshold) {
    String[] emptArr = {};
    if(arr==null||arr==emptArr){
      throw new IllegalArgumentException("invalid array");
    }
    if(str==null){
      throw new IllegalArgumentException("invalid string");
    }
    if(threshold<=0){
      throw new IllegalArgumentException("threshold<=0");
    }
    this.match = str;
    this.threshold = threshold;
    this.arr = arr;

  }
  private class ForkJoinCount extends RecursiveTask<Integer> {
    ForkJoinCount(int threshold, int low, int high) {
// @fixme No error checking !
      if(low>high){
        throw new IllegalArgumentException("low greater than high in FJC");
      }

      this.low = low;
      this.high = high;
      this.array = arr;
// If array size is this small ,
// don ’t process recursively
      this.threshold = threshold;
    }

    private final int low;
    private final int high;
    private final String[] array;
    private final int threshold;

    public Integer compute() {
      if (high - low <= threshold) {
        return this.computeSequentialCount(array, low, high);
      } // sequential processing
      else {
        ForkJoinCount left = new ForkJoinCount(threshold,  low, (high + low) / 2);
        ForkJoinCount right = new ForkJoinCount(threshold,  ((high + low) / 2) + 1, high);
        left.fork();
        final Integer rightAnswer = right.compute();
        final Integer leftAnswer = left.join();
        return leftAnswer + rightAnswer;
      }
    }

    private Integer computeSequentialCount(String[] arr, int low, int high) {
      Integer returnInt = 0;
      for (int i = low; i <= high; i++){
        if (arr[i].equals(match)){
          returnInt++;
        }
      }
      return returnInt;
    }
  }

  
  /** Returns the number of elements in arr that ".equal" the "str" parameter
   *
   * @return Using a strategy dictated by the relative values of threshold and
   * the size of arr, returns the number of times that str appears in arr
   */
  public int doIt() {
    int parallelism = Runtime.getRuntime().availableProcessors();
    ForkJoinTask< Integer > task = new ForkJoinCount ( threshold , 0, arr. length - 1);
    final ForkJoinPool fjPool = new ForkJoinPool(parallelism);
    Integer parallelCount = fjPool.invoke(task);
    fjPool.shutdown() ;
      return parallelCount;
  }
}
