package edu.yu.da;

/** Implements the HelpFromRabbeimI interface.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

import java.util.*;

import static edu.yu.da.HelpFromRabbeimI.HelpTopics.*;
import static edu.yu.da.HelpFromRabbeimI.Rebbe;

public class HelpFromRabbeim implements HelpFromRabbeimI {

  /** No-op constructor
   */
  public HelpFromRabbeim() {
    // no-op, students may change the implementation
  }

  @Override
  public Map<Integer, HelpTopics>
  scheduleIt(List<Rebbe> rabbeim, Map<HelpTopics, Integer> requestedHelp) {
    int amntOfVertices = 2 + rabbeim.size() + 9; //2 is for source and sink, 9 is for amnt of enums
    FlowNetwork flowNetwork = new FlowNetwork(amntOfVertices);
    int counter = rabbeim.size();
    Map<HelpTopics, Integer> htToVert = new HashMap<>();
    Map<Integer,HelpTopics> vertToHT = new HashMap<>();
    htToVert.put(BAVA_KAMMA,++counter);
    vertToHT.put(counter,BAVA_KAMMA);
    htToVert.put(SANHEDRIN,++counter);
    vertToHT.put(counter,SANHEDRIN);
    htToVert.put(CHUMASH,++counter);
    vertToHT.put(counter,CHUMASH);
    htToVert.put(NACH,++counter);
    vertToHT.put(counter,NACH);
    htToVert.put(MUSSAR,++counter);
    vertToHT.put(counter,MUSSAR);
    htToVert.put(MISHNAYOS,++counter);
    vertToHT.put(counter,MISHNAYOS);
    htToVert.put(BROCHOS,++counter);
    vertToHT.put(counter,BROCHOS);
    htToVert.put(SHABBOS,++counter);
    vertToHT.put(counter,SHABBOS);
    htToVert.put(BEITZA,++counter);
    vertToHT.put(counter,BEITZA);
    counter = 0;
    Rebbe[] rebbes = new Rebbe[rabbeim.size()+1];
    for(Rebbe r : rabbeim){
      counter++;
      rebbes[counter] = r;
      FlowEdge fEdge = new FlowEdge(0,counter,1);
      flowNetwork.addEdge(fEdge); //connecting source to rabbeim
      for(HelpTopics hT : r._helpTopics){
        FlowEdge flowEdge = new FlowEdge(counter,htToVert.get(hT),1);
        flowNetwork.addEdge(flowEdge); //connecting rabbeim to topics
      }
    }
    int maxFlow = 0;
    for(HelpTopics topic : requestedHelp.keySet()){
      FlowEdge flowEdge1 = new FlowEdge(htToVert.get(topic),amntOfVertices-1,requestedHelp.get(topic));
      flowNetwork.addEdge(flowEdge1); //connecting topics to sink
      maxFlow += requestedHelp.get(topic);
    }

    FordFulkerson ff = new FordFulkerson(flowNetwork,0,amntOfVertices-1);
    if(ff.value() != maxFlow){
      return Collections.emptyMap();
    }

    Map<Integer,HelpTopics> idToTop = new HashMap<>();
    for(int j = 1; j < rabbeim.size()+ 1; j++){
      for(FlowEdge edge : flowNetwork.adj[j]){
        if(edge.w > rabbeim.size()){ //ie: this is an edge from a rebbe to a topic
          if(edge.flow == edge.capacity){
            Rebbe reb = rebbes[j];
            HelpTopics ht = vertToHT.get(edge.w);
            idToTop.put(reb._id,ht);
            break;
          }
        }
      }
//      int posOfTopic = flowNetwork.adj[j].get(0).to();
//      HelpTopics helpT = vertToHT.get(posOfTopic);
//      Rebbe reb = rebbes[flowNetwork.adj[j].get(0).from()];
//      idToTop.put(reb._id,helpT);
    }
      return idToTop;
  }

  /**
   * The Following code is from
   * @author Sedgewick (with minor tweaks):
   */

  public class FlowEdge
  {
    private final int v; // edge source
    private final int w; // edge target
    private final double capacity; // capacity
    private double flow; // flow
    public FlowEdge(int v, int w, double capacity)
    {
      this.v = v;
      this.w = w;
      this.capacity = capacity;
      this.flow = 0.0;
    }
    public int from() { return v; }
    public int to() { return w; }
    public double capacity() { return capacity; }
    public double flow() { return flow; }
    public int other(int vertex){
      if (vertex == v) return w;
      else if (vertex == w) return v;
      else throw new IllegalArgumentException();
    }
    // same as for Edge
    public double residualCapacityTo(int vertex)
    {
      if (vertex == v) return flow;
      else if (vertex == w) return capacity - flow;
      else throw new RuntimeException("Inconsistent edge");
    }
    public void addResidualFlowTo(int vertex, double delta)
    {
      if (vertex == v) flow -= delta;
      else if (vertex == w) flow += delta;
      else throw new RuntimeException("Inconsistent edge");
    }
    public String toString()
    { return String.format("%d->%d %.2f %.2f", v, w, capacity, flow); }
  }

  private class FlowNetwork
  {
    private int Ver;
    private List<FlowEdge>[] adj;

    public FlowNetwork(int Ver)
    {
      this.Ver = Ver;
      adj = (List<FlowEdge>[]) new ArrayList[Ver];
      for (int v = 0; v < Ver; v++) {
        adj[v] = new ArrayList<FlowEdge>();
      }
    }
    public void addEdge(FlowEdge ed)
    {

      adj[ed.from()].add(ed);
      adj[ed.to()].add(ed);
    }
    public Iterable<FlowEdge> adj(int a)
    { return adj[a]; }
  }

  private class FordFulkerson
  {
    private boolean[] marked; // Is s->v path in residual graph?
    private FlowEdge[] edgeTo; // last edge on shortest s->v path
    private double value; // current value of maxflow
    public FordFulkerson(FlowNetwork G, int s, int t)
    { // Find maxflow in flow network G from s to t.
      while (hasAugmentingPath(G, s, t))
      { // While there exists an augmenting path, use it.
        // Compute bottleneck capacity.
        double bottle = Double.POSITIVE_INFINITY;
        for (int v = t; v != s; v = edgeTo[v].other(v))
          bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
        // Augment flow.
        for (int v = t; v != s; v = edgeTo[v].other(v))
          edgeTo[v].addResidualFlowTo(v, bottle);
        value += bottle;
      }
    }
    public double value() { return value; }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t)
    {
      marked = new boolean[G.Ver]; // Is path to this vertex known?
      edgeTo = new FlowEdge[G.Ver]; // last edge on path
      Queue<Integer> q = new Queue<Integer>();
      marked[s] = true; // Mark the source
      q.enqueue(s); // and put it on the queue.
      while (!q.isEmpty())
      {
        int v = q.dequeue();
        for (FlowEdge e : G.adj(v))
        {
          int w = e.other(v);
          if (e.residualCapacityTo(w) > 0 && !marked[w])
          { // For every edge to an unmarked vertex (in residual)
            edgeTo[w] = e; // Save the last edge on a path.
            marked[w] = true; // Mark w because a path is known
            q.enqueue(w); // and add it to the queue.
          }
        }
      }
      return marked[t];
    }
  }

  public class Queue<Item> implements Iterable<Item>
  {
    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // number of items on the queue
    private class Node
    { // nested class to define nodes
      Item item;
      Node next;
    }
    public boolean isEmpty() { return first == null; } // Or: N == 0.
    public int size() { return N; }
    public void enqueue(Item item)
    { // Add item to the end of the list.
      Node oldlast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      if (isEmpty()) first = last;
      else oldlast.next = last;
      N++;
    }
    public Item dequeue()
    { // Remove item from the beginning of the list.
      Item item = first.item;
      first = first.next;
      if (isEmpty()) last = null;
      N--;
      return item;
    }
    // See page 155 for iterator() implementation.
    public Iterator<Item> iterator()
    { return new ListIterator(); }
    private class ListIterator implements Iterator<Item>
    {
      private Node current = first;
      public boolean hasNext()
      { return current != null; }
      public void remove() { }
      public Item next()
      {
        Item item = current.item;
        current = current.next;
        return item;
      }
    }
  }

} // HelpFromRabbeim
