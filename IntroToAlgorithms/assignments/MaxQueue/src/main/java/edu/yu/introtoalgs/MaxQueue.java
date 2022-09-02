package edu.yu.introtoalgs;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/** Enhances the Queue enqueue() and dequeue() API with a O(1) max()
 * method and O(1) size().  The dequeue() method is O(1), the enqueue
 * is amortized O(1).  The implementation is O(n) in space.
 *
 * @author Avraham Leff
 * @author Adam Frenkel
 */



public class MaxQueue {
    /**
     *  ArrayDeque queue acts as regular queue would.
     *  IntPointer head is the head of a list that keeps track of the current max in the list.
     */
    private ArrayDeque<Integer> queue = new ArrayDeque<>();
    private int size = 0;
    private int max = Integer.MIN_VALUE;
    private IntPointer head = new IntPointer(Integer.MAX_VALUE);

    /**
     *  IntPointer class is used to create the max list.
     */
private class IntPointer{
    private int i;
    private IntPointer next = null;
    private IntPointer(int i){
        this.i = i;
    }
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public IntPointer getNext() {
        return next;
    }

    public void setNext(IntPointer next) {
        this.next = next;
    }
}
    /**
     * No-argument constructor: students may not add any other constructor for
     * this class
     */
    public MaxQueue() {
        // students may insert whatever code they please, but the code may not
        // throw an exception

    }

    /**
     * Insert the element with FIFO semantics
     *
     * @param x the element to be inserted.
     */
    public void enqueue(int x) {
        if (x > max) {
            max = x;
        }
        size++;
        queue.addFirst(x);
        /*
         *  This is where the magic happens.  Ints are added to the max list, and any Int that's lower than
         *  the current int is no longer part of the list.
         */
        IntPointer prev = head;
        IntPointer current = head.getNext();
        boolean didntFindSpot = true;
        while(didntFindSpot){
            if(current == null || current.getI() < x){
                prev.setNext(new IntPointer(x));
                didntFindSpot = false;
            } else{
                prev = current;
                current = current.next;
            }
        }






    }

    /**
     * Dequeue an element with FIFO semantics.
     *
     * @return the element that satisfies the FIFO semantics if the queue is not
     * empty.
     * @throws NoSuchElementException if the queue is empty
     */
    public int dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int current = queue.removeLast();
        size--;
        /*
         *  The next max is always the third element in the max list (head, current max, next max)
         */
        if (current == max) {
            IntPointer newMax = head.next.next;
            if(newMax == null){
                max = Integer.MIN_VALUE; //ie: the table is empty, so there is no max
            } else{
                max = newMax.i;
            }
            head.next = newMax;
        }
        return current;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return number of elements in the queue
     */
    public int size() {
        return size;
    }


    /**
     * Returns the element with the maximum value
     *
     * @return the element with the maximum value
     * @throws NoSuchElementException if the queue is empty
     */
    public int max() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return max;

    }
}