package edu.yu.da;

import org.junit.Test;
import edu.yu.da.ShortestCycleBase.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ShortestCycleTest {
    @Test
    public void testBasic(){
        Edge a = new Edge(1,2,1);
        Edge b = new Edge(2,3,1);
        Edge c = new Edge(3,1,1);
        List<Edge> edges = new ArrayList<>();
        edges.add(a);
        edges.add(b);
        edges.add(c);
        ShortestCycle sc = new ShortestCycle(edges,a);
        System.out.println(sc.doIt());
    }

    @Test
    public void testAdvanced() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            edges.add(new Edge(i,i+1,2));
        }
        Edge twenty = new Edge(20,1,2);
        edges.add(twenty);
        edges.add(new Edge(1,20,50));
        ShortestCycle sc = new ShortestCycle(edges,twenty);
        System.out.println(sc.doIt());

    }

    @Test
    public void testReal(){
        List<Edge> edges = new ArrayList<>();
        Edge eoi = new Edge(0,1,185);
        edges.add(eoi);
        edges.add(new Edge(1,2,2));
        edges.add(new Edge(2,3,1));
        edges.add(new Edge(3,4,3));
        edges.add(new Edge(4,5,10));
        edges.add(new Edge(5,13,5));
        edges.add(new Edge(13,7,4));
        edges.add(new Edge(3,21,1));
        edges.add(new Edge(21,7,22));
        edges.add(new Edge(7,8,0));
        edges.add(new Edge(8,0,4));
        edges.add(new Edge(0,9,0));
        edges.add(new Edge(9,10,9));
        edges.add(new Edge(10,11,21));
        edges.add(new Edge(11,71,10));
        edges.add(new Edge(71,1,10));
        ShortestCycle sc = new ShortestCycle(edges,eoi);
        List<Edge> sPath = sc.doIt();
        System.out.println(sPath);
        assertTrue(sPath.contains(new Edge(1,2,2)));
        assertTrue(sPath.contains(new Edge(2,3,1)));
        assertTrue(sPath.contains(new Edge(3,4,3)));
        assertTrue(sPath.contains(new Edge(4,5,10)));
        assertTrue(sPath.contains(new Edge(5,13,5)));
        assertTrue(sPath.contains(new Edge(13,7,4)));
        assertTrue(sPath.contains(new Edge(7,8,0)));
        assertTrue(sPath.contains(eoi));
        assertEquals(9, sPath.size());

    }


    @Test
    public void testProfessor(){
        List<Edge> edges = new ArrayList<>();
       // [{(1,2), weight=3.00}, {(1,3), weight=1.00}, {(2,3), weight=7.00}, {(2,4), weight=5.00}, {(3,4), weight=2.00}, {(2,5), weight=1.00}, {(4,5) weight=7.00}]
        //edges.add(new Edge(1,2,3));
        Edge eoi = new Edge(1,2,3);
        edges.add(eoi);
        edges.add(new Edge(1,3,1));
        edges.add(new Edge(2,3,7));
        edges.add(new Edge(2,4,5));
        edges.add(new Edge(3,4,2));
        edges.add(new Edge(2,5,1));
        edges.add(new Edge(4,5,7));
        ShortestCycle sc = new ShortestCycle(edges,eoi);
        System.out.println(sc.doIt());

    }


}