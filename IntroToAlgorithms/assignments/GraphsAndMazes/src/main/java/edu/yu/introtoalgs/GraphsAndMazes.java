package edu.yu.introtoalgs;

import java.util.*;

public class GraphsAndMazes {

  /** A immutable coordinate in 2D space.
   *
   * Students must NOT modify the constructor (or its semantics) in any way,
   * but can ADD whatever they choose.
   */
  public static class Coordinate { 
    public final int x, y;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coordinate that = (Coordinate) o;
      return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public String toString() {
      return "(" + x +
              "," + y +
              ')';
    }

    /** Constructor, defines an immutable coordinate in 2D space.
     *
     * @param x specifies x coordinate
     * @param y specifies x coordinate
     */
    public Coordinate(final int x, final int y) {
      this.x = x;
      this.y = y;

    }

    /** Add any methods, instance variables, static variables that you choose
     */
  } // Coordinate class



  /** Given a maze (specified by a 2D integer array, and start and end
   * Coordinate instances), return a path (beginning with the start
   * coordinate, and terminating wih the end coordinate), that legally
   * traverses the maze from the start to end coordinates.  If no such
   * path exists, returns an empty list.  The path need need not be a
   * "shortest path".
   *
   * @param maze 2D int array whose "0" entries are interpreted as
   * "coordinates that can be navigated to in a maze traversal (can be
   * part of a maze path)" and "1" entries are interpreted as
   * "coordinates that cannot be navigated to (part of a maze wall)".
   * @param start maze navigation must begin here, must have a value
   * of "0"
   * @param end maze navigation must terminate here, must have a value
   * of "0"
   * @return a path, beginning with the start coordinate, terminating
   * with the end coordinate, and intervening elements represent a
   * legal navigation from maze start to maze end.  If no such path
   * exists, returns an empty list.  A legal navigation may only
   * traverse maze coordinates, may not contain coordinates whose
   * value is "1", may only traverse from a coordinate to one of its
   * immediate neighbors using one of the standard four compass
   * directions (no diagonal movement allowed).  A legal path may not
   * contain a cycle.  It is legal for a path to contain only the
   * start coordinate, if the start coordinate is equal to the end
   * coordinate.
   */
  public static List<Coordinate> searchMaze(final int[][] maze, final Coordinate start, final Coordinate end) {
    if(maze == null){
      throw new IllegalArgumentException("null maze");
    }
    if(start.x<0 || start.x>=maze.length ||start.y>=maze[0].length ||start.y<0){
      throw new IllegalArgumentException("out of bounds start");
    }
    if(end.x<0 || end.x>=maze.length ||end.y>=maze[0].length ||end.y<0){
      throw new IllegalArgumentException("out of bounds end");
    }
    if (maze[start.x][start.y] != 0 || maze[end.x][end.y] !=0) {
      throw new IllegalArgumentException("start or end != 0");
    }
    if(start.equals(end)){
      List<Coordinate> retList = new ArrayList<>();
      retList.add(start);
      return retList;
    }
    HashMap<Coordinate,List<Coordinate>> adj = initAdj(maze);//new HashMap<>();
    DepthFirstPaths dfp =  new DepthFirstPaths(adj,end);


    // fill me in
    return dfp.pathTo(start);           // fix this!
  }
//  private static int verticies;
//  private static float edges;
  private static HashMap<Coordinate, List<Coordinate>> initAdj(int[][] maze) {
    HashMap<Coordinate,List<Coordinate>> adj = new HashMap<>();
    int r = -1;
    for (int[] row: maze) {
      r++;
      int c = -1;
      for (int val : row) {
        c++;
        if (val == 0) {
        //  verticies++;
          List<Coordinate> neighbors = new ArrayList<>();
          if (c - 1 >= 0) {
            if (maze[r][c - 1] == 0) {
            //  edges+=.5;
              neighbors.add(new Coordinate(r, c - 1));
            }
          }
          if ((r - 1) >= 0) {
            if (maze[r - 1][c] == 0) {
             // edges+=.5;
              neighbors.add(new Coordinate(r - 1, c));
            }
          }
          if (r + 1 < maze.length) {
            if (maze[r + 1][c] == 0) {
              //edges+=.5;
              neighbors.add(new Coordinate(r + 1, c));
            }
          }
          if (c + 1 < row.length) {
            if (maze[r][c + 1] == 0) {
             // edges+=.5;
              neighbors.add(new Coordinate(r, c + 1));
            }
          }
          if (neighbors.size() != 0) {
            adj.put(new Coordinate(r, c), neighbors);
          }
          //adj.put(new Coordinate(r,c));
        }
      }
    }
      return adj;
  }
  /**
   * DFS to find paths - mainly from Sedgewick
   */
  private static class DepthFirstPaths {
    private HashMap<Coordinate,Boolean> marked = new HashMap<>(); // Had dfs() been called for this verttex?
    private HashMap<Coordinate,Coordinate> edgeTo = new HashMap<>(); // last vertex on known path to this vertex
    private final Coordinate s; // source

    private DepthFirstPaths(HashMap<Coordinate, List<Coordinate>> adj, Coordinate s) {
     // marked = new boolean[verticies];
     // edgeTo = new int[verticies];
      this.s = s;
      dfs(adj,s);
    }
    private void dfs(HashMap<Coordinate, List<Coordinate>> adj, Coordinate v){
      marked.put(v,true);
      if(adj.get(v)!=null) {
        for (Coordinate w : adj.get(v)) {
          if (marked.get(w) == null) {
            edgeTo.put(w, v);
            dfs(adj, w);
          }
        }
      }
    }
    private boolean hasPathTo(Coordinate v){
      if(marked.get(v)==null){
        return false;
      }
      return true;
    }
    private List<Coordinate> pathTo(Coordinate v){
      if(!hasPathTo(v)){
        return null;
      }
      List<Coordinate> path = new ArrayList<>();
      for (Coordinate x = v; x!=s; x = edgeTo.get(x)){
        path.add(x);
      }
      path.add(s);
      return path;
    }
  }


  /** minimal main() demonstrates use of APIs
   */
  public static void main (final String[] args) {
    final int[][] exampleMaze = {
      {0, 0, 0},
      {0, 1, 1},
      {0, 1, 0}
    };

    final Coordinate start = new Coordinate(2, 0);
    final Coordinate end = new Coordinate(0, 2);
    final List<Coordinate> path = searchMaze(exampleMaze, start, end);
    //System.out.println("path="+path);
    if(path == null){
      System.out.println("no path.");
    }else {
      for (Coordinate c : path) {
        System.out.println(c.toString());
      }
    }
    System.out.println(path);
  }

}
