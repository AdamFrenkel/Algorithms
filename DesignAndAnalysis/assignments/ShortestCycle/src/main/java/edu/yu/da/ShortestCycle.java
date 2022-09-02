package edu.yu.da;

/** Implements the ShortestCycleBase API.
 *  By Adam Frenkel
 *  With code from Sedgewick, properly attributed when it's implemented
 */

import java.util.*;

public class ShortestCycle extends ShortestCycleBase {
  private List<Edge> edges;
  private EdgeWeightedGraph graph;
  Edge edgeOfInterest;
  /** Constructor
   *
   * @param edges List of edges that, in toto, represent a weighted undirected
   * graph.  The client maintains ownership of the List: the implementation may
   * not modify this input parameter.  The client guarantees that the List is
   * not null, and doesn't contains any null edges.
   * @param e One of the graph's edges, the "edge of interest" since we want to
   * determine the shortest cycle containing this edge.
   */
  public ShortestCycle(final List<Edge> edges, final Edge e) {
    // base class does nothing, but let's do it right
    super(edges, e);
    this.edgeOfInterest = e;
    this.edges = edges;

    int maxEdge = Integer.MIN_VALUE;  //finding the max edge, this will be the "number" of vertexes in the graph
    for(Edge edge : edges){
      if(edge.v() > maxEdge){
        maxEdge = edge.v();
      }
      if(edge.w() > maxEdge){
        maxEdge = edge.w();
      }
    }

    graph = new EdgeWeightedGraph(maxEdge+1);
    for(Edge newEd : edges){
      if(!(newEd.equals(edgeOfInterest))){ //insures that won't add edge of interest
        graph.addEdge(newEd);
      }
    }
  } // constructor

  @Override
  public String toString() {
    return "ShortestCycle{" +
            "graph=" + graph +
            ", edgeOfInterest=" + edgeOfInterest +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShortestCycle that = (ShortestCycle) o;
    return edges.equals(that.edges) && edgeOfInterest.equals(that.edgeOfInterest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(edges, edgeOfInterest);
  }

  /** Finds the shortest cycle in the graph with respect to the specified edge
   * as detailed by the requirements document.
   *
   * @return List of edges representing the shortest cyle containing the "edge
   * of interest".  The List can begin with any edge from the cycle, but must
   * be a sequence that begins and ends at the same vertex and contain the
   * "edge of interest".
   */
  @Override
  public List<Edge> doIt() {
    DijkstraUndirectedSP dij = new DijkstraUndirectedSP(graph, edgeOfInterest.v());
    List<Edge> returnList = dij.pathTo(edgeOfInterest.w());
    returnList.add(edgeOfInterest);
    return returnList;
  } // doIt


  /**
   * The remainder of this class is a slightly modified and shorter version of
   * Sedgewick's version ofDijkstra for an Undirected Graph.
   *
   *  The {@code DijkstraUndirectedSP} class represents a data type for solving
   *  the single-source shortest paths problem in edge-weighted graphs
   *  where the edge weights are non-negative.
   *  <p>
   *  This implementation uses Dijkstra's algorithm with a binary heap.
   *  The constructor takes &Theta;(<em>E</em> log <em>V</em>) time in the
   *  worst case, where <em>V</em> is the number of vertices and
   *  <em>E</em> is the number of edges.
   *  Each instance method takes &Theta;(1) time.
   *  It uses &Theta;(<em>V</em>) extra space (not including the
   *  edge-weighted graph).
   *  <p>
   *  For additional documentation,
   *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
   *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
   *  <p>
   *  This correctly computes shortest paths if all arithmetic performed is
   *  without floating-point rounding error or arithmetic overflow.
   *  This is the case if all edge weights are integers and if none of the
   *  intermediate results exceeds 2<sup>52</sup>. Since all intermediate
   *  results are sums of edge weights, they are bounded by <em>V C</em>,
   *  where <em>V</em> is the number of vertices and <em>C</em> is the maximum
   *  weight of any edge.
   *  <p>
   *  @author Robert Sedgewick
   *  @author Kevin Wayne
   *  @author Nate Liu
   */
  public class DijkstraUndirectedSP {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private Edge[] edgeTo;            // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every
     * other vertex in the edge-weighted graph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraUndirectedSP(EdgeWeightedGraph G, int s) {
      for (Edge e : edges) {
        if (e.weight() < 0)
          throw new IllegalArgumentException("edge " + e + " has negative weight");
      }

      distTo = new double[G.V()];
      edgeTo = new Edge[G.V()];

      validateVertex(s);

      for (int v = 0; v < G.V(); v++)
        distTo[v] = Double.POSITIVE_INFINITY;
      distTo[s] = 0.0;

      // relax vertices in order of distance from s
      pq = new IndexMinPQ<Double>(G.V());
      pq.insert(s, distTo[s]);
      while (!pq.isEmpty()) {
        int v = pq.delMin();
        for (Edge e : G.adj(v))
          relax(e, v);
      }

      // check optimality conditions
      assert check(G, s);
    }

    // relax edge e and update pq if changed
    private void relax(Edge e, int v) {
      int w;
      if(v == e.v()) {
        w = e.w();
      }else{
        w = e.v();
      }
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
        else                pq.insert(w, distTo[w]);
      }
    }

    /**
     * Returns true if there is a path between the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path between the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
      validateVertex(v);
      return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path between the source vertex {@code s} and vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path between the source vertex {@code s} and vertex {@code v};
     *         {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public List<Edge> pathTo(int v) {
      validateVertex(v);
      if (!hasPathTo(v)) return null;
      List<Edge> path = new ArrayList<>();
      int x = v;
      for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
        path.add(e);
        if(x == e.v()) {
          x = e.w();
        }else{
          x = e.v();
        }
      }
      return path;
    }

    // check optimality conditions:
    // (i) for all edges e = v-w:            distTo[w] <= distTo[v] + e.weight()
    // (ii) for all edge e = v-w on the SPT: distTo[w] == distTo[v] + e.weight()
    private boolean check(EdgeWeightedGraph G, int s) {

      // check that edge weights are non-negative
      for (Edge e : edges) {
        if (e.weight() < 0) {
          System.err.println("negative edge weight detected");
          return false;
        }
      }

      // check that distTo[v] and edgeTo[v] are consistent
      if (distTo[s] != 0.0 || edgeTo[s] != null) {
        System.err.println("distTo[s] and edgeTo[s] inconsistent");
        return false;
      }
      for (int v = 0; v < G.V(); v++) {
        if (v == s) continue;
        if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
          System.err.println("distTo[] and edgeTo[] inconsistent");
          return false;
        }
      }

      // check that all edges e = v-w satisfy distTo[w] <= distTo[v] + e.weight()
      for (int v = 0; v < G.V(); v++) {
        for (Edge e : G.adj(v)) {
          int w;
          if(v == e.v()) {
            w = e.w();
          }else{
            w = e.v();
          }
          if (distTo[v] + e.weight() < distTo[w]) {
            System.err.println("edge " + e + " not relaxed");
            return false;
          }
        }
      }

      // check that all edges e = v-w on SPT satisfy distTo[w] == distTo[v] + e.weight()
      for (int w = 0; w < G.V(); w++) {
        if (edgeTo[w] == null) continue;
        Edge e = edgeTo[w];
        if (w != e.v() && w != e.w()) return false;
        int v;
        if(w == e.v()) {
          v = e.w();
        }else{
          v = e.v();
        }
        if (distTo[v] + e.weight() != distTo[w]) {
          System.err.println("edge " + e + " on shortest path not tight");
          return false;
        }
      }
      return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
      int V = distTo.length;
      if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
  }

  /**
   *  The {@code EdgeWeightedGraph} class represents an edge-weighted
   *  graph of vertices named 0 through <em>V</em> – 1, where each
   *  undirected edge is of type {@link Edge} and has a real-valued weight.
   *  It supports the following two primary operations: add an edge to the graph,
   *  iterate over all of the edges incident to a vertex. It also provides
   *  methods for returning the degree of a vertex, the number of vertices
   *  <em>V</em> in the graph, and the number of edges <em>E</em> in the graph.
   *  Parallel edges and self-loops are permitted.
   *  By convention, a self-loop <em>v</em>-<em>v</em> appears in the
   *  adjacency list of <em>v</em> twice and contributes two to the degree
   *  of <em>v</em>.
   *  <p>
   *  This implementation uses an <em>adjacency-lists representation</em>, which
   *  is a vertex-indexed array of {@link List} objects.
   *  It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
   *  the number of edges and <em>V</em> is the number of vertices.
   *  All instance methods take &Theta;(1) time. (Though, iterating over
   *  the edges returned by {@link #adj(int)} takes time proportional
   *  to the degree of the vertex.)
   *  Constructing an empty edge-weighted graph with <em>V</em> vertices takes
   *  &Theta;(<em>V</em>) time; constructing a edge-weighted graph with
   *  <em>E</em> edges and <em>V</em> vertices takes
   *  &Theta;(<em>E</em> + <em>V</em>) time.
   *  <p>
   *  For additional documentation,
   *  see <a href="https://algs4.cs.princeton.edu/43mst">Section 4.3</a> of
   *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
   *
   *  @author Robert Sedgewick
   *  @author Kevin Wayne
   */
  private class EdgeWeightedGraph {
    private final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private List<Edge>[] adj;

    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedGraph(int V) {
      if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
      this.V = V;
      this.E = 0;
      adj = (List<Edge>[]) new List[V];
      for (int v = 0; v < V; v++) {
        adj[v] = new ArrayList<Edge>();
      }
    }

    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int V() {
      return V;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
      if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code V-1}
     */
    public void addEdge(Edge e) {
      int v = e.v();
      int w = e.w();
      validateVertex(v);
      validateVertex(w);
      adj[v].add(e);
      adj[w].add(e);
      E++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(int v) {
      validateVertex(v);
      return adj[v];
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(V + " " + E + NEWLINE);
      for (int v = 0; v < V; v++) {
        s.append(v + ": ");
        for (Edge e : adj[v]) {
          s.append(e + "  ");
        }
        s.append(NEWLINE);
      }
      return s.toString();
    }

  }

  /**
   *  The {@code IndexMinPQ} class represents an indexed priority queue of generic keys.
   *  It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
   *  operations, along with <em>delete</em> and <em>change-the-key</em>
   *  methods. In order to let the client refer to keys on the priority queue,
   *  an integer between {@code 0} and {@code maxN - 1}
   *  is associated with each key—the client uses this integer to specify
   *  which key to delete or change.
   *  It also supports methods for peeking at the minimum key,
   *  testing if the priority queue is empty, and iterating through
   *  the keys.
   *  <p>
   *  This implementation uses a binary heap along with an array to associate
   *  keys with integers in the given range.
   *  The <em>insert</em>, <em>delete-the-minimum</em>, <em>delete</em>,
   *  <em>change-key</em>, <em>decrease-key</em>, and <em>increase-key</em>
   *  operations take &Theta;(log <em>n</em>) time in the worst case,
   *  where <em>n</em> is the number of elements in the priority queue.
   *  Construction takes time proportional to the specified capacity.
   *  <p>
   *  For additional documentation, see
   *  <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
   *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
   *
   *  @author Robert Sedgewick
   *  @author Kevin Wayne
   *
   *  @param <Key> the generic type of key on this priority queue
   */
  private class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    /**
     * Initializes an empty indexed priority queue with indices between {@code 0}
     * and {@code maxN - 1}.
     * @param  maxN the keys on this priority queue are index from {@code 0}
     *         {@code maxN - 1}
     * @throws IllegalArgumentException if {@code maxN < 0}
     */
    public IndexMinPQ(int maxN) {
      if (maxN < 0) throw new IllegalArgumentException();
      this.maxN = maxN;
      n = 0;
      keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
      pq   = new int[maxN + 1];
      qp   = new int[maxN + 1];                   // make this of length maxN??
      for (int i = 0; i <= maxN; i++)
        qp[i] = -1;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
      return n == 0;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param  i an index
     * @return {@code true} if {@code i} is an index on this priority queue;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     */
    public boolean contains(int i) {
      validateIndex(i);
      return qp[i] != -1;
    }

    /**
     * Associates key with index {@code i}.
     *
     * @param  i an index
     * @param  key the key to associate with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if there already is an item associated
     *         with index {@code i}
     */
    public void insert(int i, Key key) {
      validateIndex(i);
      if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
      n++;
      qp[i] = n;
      pq[n] = i;
      keys[i] = key;
      swim(n);
    }

    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
      if (n == 0) throw new NoSuchElementException("Priority queue underflow");
      int min = pq[1];
      exch(1, n--);
      sink(1);
      assert min == pq[n+1];
      qp[min] = -1;        // delete
      keys[min] = null;    // to help with garbage collection
      pq[n+1] = -1;        // not needed
      return min;
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void changeKey(int i, Key key) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      keys[i] = key;
      swim(qp[i]);
      sink(qp[i]);
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @deprecated Replaced by {@code changeKey(int, Key)}.
     */
    @Deprecated
    public void change(int i, Key key) {
      changeKey(i, key);
    }

    /**
     * Decrease the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to decrease
     * @param  key decrease the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void decreaseKey(int i, Key key) {
      validateIndex(i);
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      if (keys[i].compareTo(key) == 0)
        throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
      if (keys[i].compareTo(key) < 0)
        throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
      keys[i] = key;
      swim(qp[i]);
    }

    // throw an IllegalArgumentException if i is an invalid index
    private void validateIndex(int i) {
      if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
      if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
    }

    /***************************************************************************
     * General helper functions.
     ***************************************************************************/
    private boolean greater(int i, int j) {
      return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
      int swap = pq[i];
      pq[i] = pq[j];
      pq[j] = swap;
      qp[pq[i]] = i;
      qp[pq[j]] = j;
    }

    /***************************************************************************
     * Heap helper functions.
     ***************************************************************************/
    private void swim(int k) {
      while (k > 1 && greater(k/2, k)) {
        exch(k, k/2);
        k = k/2;
      }
    }

    private void sink(int k) {
      while (2*k <= n) {
        int j = 2*k;
        if (j < n && greater(j, j+1)) j++;
        if (!greater(k, j)) break;
        exch(k, j);
        k = j;
      }
    }

    /***************************************************************************
     * Iterators.
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
      // create a new pq
      private IndexMinPQ<Key> copy;

      // add all elements to copy of heap
      // takes linear time since already in heap order so no keys move
      public HeapIterator() {
        copy = new IndexMinPQ<Key>(pq.length - 1);
        for (int i = 1; i <= n; i++)
          copy.insert(pq[i], keys[pq[i]]);
      }

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMin();
      }
    }
  }

}