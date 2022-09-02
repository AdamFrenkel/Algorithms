package edu.yu.introtoalgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntersectRectangles {

    /** This constant represents the fact that two rectangles don't intersect.
     *
     * @see # intersectRectangle
     * @warn you may not modify this constant in any way
     */
    public final static Rectangle NO_INTERSECTION =
            new Rectangle(0, 0, -1, -1);

    /** An immutable class that represents a 2D Rectangle.
     *
     * @warn you may not modify the instance variables in any way, you are
     * encouraged to add to the current set of variables and methods as you feel
     * necesssary.
     */
    public static class Rectangle {
        // safe to make instance variables public because they are final, now no
        // need to make getters
        public final int x;
        public final int y;
        public final int width;
        public final int height;
        public final List<Integer> xLst = new ArrayList<>();
        public final List<Integer> yLst = new ArrayList<>();
        /** Constructor: see the requirements doc for the precise semantics.
         *
         * @warn you may not modify the currently defined semantics in any way, you
         * may add more code if you so choose.
         */
        public Rectangle(final int x, final int y, final int width, final int height) {
//            if( width < 0 || height < 0 ) {
//                throw new IllegalArgumentException("height or width of rectangle is negative");
//            }
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            for(int i = x; i <= (x + width) ;i++){
                this.xLst.add(i);
            }
            for(int j = y; j <= (y + height) ;j++){
                this.yLst.add(j);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rectangle rectangle = (Rectangle) o;
            return x == rectangle.x && y == rectangle.y && width == rectangle.width && height == rectangle.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, width, height);
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    /** If the two rectangles intersect, returns the rectangle formed by their
     * intersection; otherwise, returns the NO_INTERSECTION Rectangle constant.
     *
     * @param r1 one rectangle
     * @param r2 the other rectangle
     * @return a rectangle representing the intersection of the input parameters
     * if they intersect, NO_INTERSECTION otherwise.  See the requirements doc
     * for precise definition of "rectangle intersection"
     */
    public static Rectangle intersect (final Rectangle r1, final Rectangle r2) {
        //List<Integer> newRecXLst = new ArrayList<>();
        /*
         * 1st Test if either rec is null
         */
        if(r1 == null || r2 == null ){
            throw new IllegalArgumentException("Null rectangle/s in intersect.");
        }
        /*
         * 2nd find where xlsts overlap form two recs(xlst and ylst already created in rec sublclass).
         * If the two recs over lap, the amount of times they overlap tells us height/ wdth for y and x respectively.
         */
        int newLowestX = Integer.MAX_VALUE;
        int newWidth = -1;  //-1 indicates that there are no x values shared in common; if this is 0, then there is one shared x value.
        for(Integer i : r1.xLst){
            if(r2.xLst.contains(i)){
                if(i<newLowestX){
                    newLowestX=i;
                }
                //newRecXLst.add(i);
                newWidth++;
            }
        }
     //   List<Integer> newRecYLst = new ArrayList<>();
        /*
         * 3rd find where ylsts overlap form two recs and get new lnth
         */
        int newLowestY = Integer.MAX_VALUE;
        int newLength = -1;  //-1 indicates that there are no x values shared in common; if this is 0, then there is one shared x value.
        for(Integer j : r1.yLst){
            if(r2.yLst.contains(j)){
                if(j<newLowestY){
                    newLowestY=j;
                }
              //  newRecYLst.add(j);
                newLength++;
            }
        }
        /*
         * If theres a new heoght and width the recs overlap
         */
        if( newWidth > -1 && newLength > -1 ){
            return new Rectangle(newLowestX,newLowestY,newWidth,newLength);
        }

//        int minXOfR1 = r1.x;
//        int minYOfR1 = r1.y;
//        int maxXOfR1 = r1.x + r1.width;
//        int maxYOfR1 = r1.y + r1. height;
//        int minXOfR2 = r2.x;
//        int minYOfR2 = r2.y;
//        int maxXOfR2 = r2.x + r2.width;
//        int maxYOfR2 = r2.y + r2. height;
//        //first check if the bottom left corner of r2 interscts r1
//        if (minXOfR1 <= minXOfR2 && minYOfR1 <= minYOfR2 && maxXOfR1>=minXOfR2 && maxYOfR1>=minYOfR2){
//            //now calculat ethe height and length of the intersecting rectangle
//            int newWidth;
//            int newPotentialWidth = r1.x + r1.width - r2.x;
//            if (newPotentialWidth<=r2.width){
//                newWidth = newPotentialWidth;
//            } else {
//                newWidth = r2.width;
//            }
//            int newHeight;
//            int newPotentialHeight = r1.y + r1.height - r2.y;
//            if (newPotentialHeight<=r2.height){
//                newHeight = newPotentialHeight;
//            } else {
//                newHeight = r2.height;
//            }
//            return new Rectangle(r2.x,r2.y,newWidth,newHeight);
//        }
//        //if bottom right corner of 2nd rec intersects
//        if (minXOfR1 <= maxXOfR2 && minYOfR1 <= minYOfR2 && maxXOfR1>=maxXOfR2 && maxYOfR1>=minYOfR2){
//            //now calculat ethe height and length of the intersecting rectangle
//            int newWidth;
//            int newPotentialWidth = r1.x + r1.width - r2.x;
//            if (newPotentialWidth<=r2.width){
//                newWidth = newPotentialWidth;
//            } else {
//                newWidth = r2.width;
//            }
//        }
        return NO_INTERSECTION;
        // supply a more useful implementation!
    }

} // class
