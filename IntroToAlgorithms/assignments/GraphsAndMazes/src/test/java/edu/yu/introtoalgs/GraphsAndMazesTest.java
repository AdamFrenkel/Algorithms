package edu.yu.introtoalgs;

import org.junit.Test;
import edu.yu.introtoalgs.GraphsAndMazes.*;
//import edu.yu.introtoalgs.GraphsAndMazes;

import java.util.List;

import static org.junit.Assert.*;

public class GraphsAndMazesTest {
    @Test
   public void test1 () {
    final int[][] exampleMaze = {
      {1, 0, 0},
      {0, 1, 1},
      {0, 1, 0}
    };

    final GraphsAndMazes.Coordinate start = new Coordinate(2, 0);
    final Coordinate end = new Coordinate(1,0);
    final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
    //System.out.println("path="+path);
    if(path == null){
      System.out.println("no path.");
    }else {
      for (Coordinate c : path) {
        System.out.println(c.toString());
      }
    }

  }
    @Test
    public void test2 () {
        final int[][] exampleMaze = {
                {1,0,1,1,0,1,1,1,0,0},
                {1,0,1,0,1,0,0,0,0,0},
                {1,0,1,0,1,0,0,1,0,1},
                {1,0,1,0,0,0,1,1,0,1},
                {0,0,1,0,1,0,1,0,0,1},
                {1,0,1,0,0,0,1,1,0,0},
                {1,0,1,1,1,0,0,1,1,0},
                {1,0,1,0,1,1,0,1,0,0},
                {1,0,1,0,0,0,0,1,0,1},
                {1,0,0,0,1,1,1,1,0,1}
        };

        final GraphsAndMazes.Coordinate start = new Coordinate(1, 1);
        final Coordinate end = new Coordinate(8, 8);
        final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
        //System.out.println("path="+path);
        if(path == null){
            System.out.println("no path.");
        }else {
            for (Coordinate c : path) {
                System.out.println(c.toString());
            }
        }

    }
//    @Test
//    public void speedTest(){
//        int[][] exampleMaze = generateMaze(1000);
//        final GraphsAndMazes.Coordinate start = new Coordinate(0, 0);
//        final Coordinate end = new Coordinate(9, 100);
//
//        System.out.println("Buffering");
//        for(int i =1; i<=25000; i++){
//            final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
//            System.out.println(i+"/25,000");
//        }
//
//        exampleMaze = generateMaze(125);
//        long startTime = System.nanoTime();
//        GraphsAndMazes.searchMaze(exampleMaze, start, end);
//        long prev =  System.nanoTime() - startTime;
//        for (int n = 250; n <= 536870911; n += n) {
//            exampleMaze = generateMaze(n);
//            startTime = System.nanoTime();
//            GraphsAndMazes.searchMaze(exampleMaze, start, end);
//            long time =  System.nanoTime() - startTime;
//            System.out.println(n + " " + time);
//            System.out.println(time / prev);
//            prev = time;
//        }
//
//
//    }
//    public int[][] generateMaze(int n){
//        int[][] maze = new int[10][n];
//        for(int i = 0; i<10; i+=1){
//            for(int j = 0; j<n/2; j++){
//                if(Math.random()<.5){
//                    maze[i][j] = 0;
//                }else {
//                    maze[i][j] =1;
//                }
//            }
//        }
//        maze[0][0] = 0;
//        maze[9][100] = 0;
//        return maze;
//    }


}