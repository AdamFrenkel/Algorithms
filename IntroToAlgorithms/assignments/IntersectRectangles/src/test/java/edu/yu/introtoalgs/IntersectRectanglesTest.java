package edu.yu.introtoalgs;

import edu.yu.introtoalgs.IntersectRectangles.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntersectRectanglesTest {
    @Test
    public void test2ndRecsBotLCornerIntersects1stRec() {
        //test when 2nd rec goes out of 1st rec in both direcs
        final Rectangle A = new Rectangle(0, 0, 4, 4);
        final Rectangle B = new Rectangle(2,2,3,3);
        final Rectangle test1 = new Rectangle(2, 2, 2, 2);
        assertEquals(test1,IntersectRectangles.intersect(A,B));
        //test when 2nd rec doesn't go out of 1st rec in either direcs
        final Rectangle C = new Rectangle(2,2,1,1);
        final Rectangle test2 = new Rectangle(2, 2, 1, 1);
        assertEquals(test2,IntersectRectangles.intersect(A,C));
        //test when 2nd rec goes out height
        final Rectangle D = new Rectangle(2,2,4,1);
        final Rectangle test3 = new Rectangle(2,2,2,1);
        assertEquals(test3,IntersectRectangles.intersect(A,D));
        //test when 2nd rec goes out width & 0
        final Rectangle E = new Rectangle(2,2,0,4);
        final Rectangle test4 = new Rectangle(2,2,0,2);
        assertEquals(test4,IntersectRectangles.intersect(A,E));
        //test when 2nd rec just touches point
        final Rectangle F = new Rectangle(4,4,6,4);
        final Rectangle test5 = new Rectangle(4,4,0,0);
        assertEquals(test5,IntersectRectangles.intersect(A,F));
        //test when dont intersect
        final Rectangle G = new Rectangle(10,7,6,4);
        assertEquals(IntersectRectangles.NO_INTERSECTION,IntersectRectangles.intersect(A,G));
        //test when edges intersect
        final Rectangle H = new Rectangle(4,1,5,2);
        final Rectangle test7 = new Rectangle(4,1,0,2);
        assertEquals(test7,IntersectRectangles.intersect(A,H));
    }
    @Test
    public void someRandomTests(){
        final Rectangle A = new Rectangle(-4, -1, 2, 1);
        final Rectangle B = new Rectangle(2,2,1000000,878787);
        assertEquals(IntersectRectangles.NO_INTERSECTION,IntersectRectangles.intersect(A,B));
        final Rectangle C = new Rectangle(-1,-1,1000,200);
        final Rectangle D = new Rectangle(0,0,2000,999);
        final Rectangle test1 = new Rectangle(0,0,999,199);
        assertEquals(test1,IntersectRectangles.intersect(D,C));
        boolean pass = false;
        try{
            IntersectRectangles.intersect(D,null);
        } catch (IllegalArgumentException e){
            pass = true;
        }
        assertTrue(pass);
        boolean pass2 = false;
        try{
            IntersectRectangles.intersect(null,null);
        } catch (IllegalArgumentException e){
            pass2 = true;
        }
        assertTrue(pass2);
        boolean pass3 = false;
        try{
            IntersectRectangles.intersect(null,C);
        } catch (IllegalArgumentException e){
            pass3 = true;
        }
        assertTrue(pass3);
        final Rectangle D2 = new Rectangle (0 , 0 , 2 , 5) ;
        final Rectangle F = new Rectangle ( 2 , 0 , 2 , 5) ;
        assertEquals (
                new Rectangle ( 2 , 0 , 0 , 5) ,
                IntersectRectangles.intersect(D2 , F ) ) ;
        final Rectangle D3 = new Rectangle(2,5,6,9);
        final Rectangle D4 = new Rectangle(2,5,0,0);
        assertEquals(D4,IntersectRectangles.intersect(D2,D3));
    }

}