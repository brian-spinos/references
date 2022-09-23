// DIJKSTRA (unfinished....)

class Graph {
  int size;
  int[][] graph;
  public static final int NODES_NOT_CONNECTED_VALUE = -1;

  public Graph(int size){
    this.size = size;
    graph = new int[size][size];
    for(int i=0;i<size;i++){
      for(int j=0;j<size;j++){
        graph[i][j] = NODES_NOT_CONNECTED_VALUE;
      }
    }
  }

  public int getSize(){
    return size;
  }

  public int[][] getGraph(){
    return graph;
  }

  public int getWeight(int x, int y){
    return graph[x][y];
  }

  public void setWeight(int x, int y, int w){
    graph[x][y] = w;
  }

  public void connect(int x, int y, int w){
    setWeight(x, y, w);
  }
}


class Dijkstra {
  public static final int INFINITY = Integer. MAX_VALUE;
  
  public static int[] exec(Graph graph, int sourceNode){
    int size = graph.getSize();
    int[] distancefromSrcArr = new int[size];
    boolean[] visitedArr = new boolean[size];
    for(int i=0;i<size;i++){
      distancefromSrcArr[i] = INFINITY;
      visitedArr[i] = false;
      // System.out.println("distancefromSrcArr[i]: " + distancefromSrcArr[i]);
      // System.out.println("visitedArr[i]: " + visitedArr[i]);
    }

    // the node we choose has 0 distance from itself
    distancefromSrcArr[sourceNode] = 0; 

    for(int currentNeighbor=0;currentNeighbor<size;currentNeighbor++){
      // get closest node from src, it will be src in first iteration
      int currentNode = minDistance(graph.getSize(), distancefromSrcArr, visitedArr);

      System.out.println("currentNode: " + currentNode);

      if(
        graph.getWeight(currentNode, currentNeighbor) != Graph.NODES_NOT_CONNECTED_VALUE && // if nodes are connected
        distancefromSrcArr[currentNode] != INFINITY && // distance is not infinity
        visitedArr[currentNeighbor] == false && // node was not previously visited
        (distancefromSrcArr[currentNode] + graph.getWeight(currentNode, currentNeighbor)) < distancefromSrcArr[currentNeighbor]
      ){
        System.out.println("1.distancefromSrcArr[currentNeighbor]: " + distancefromSrcArr[currentNeighbor]);
        distancefromSrcArr[currentNeighbor] = distancefromSrcArr[currentNode] + graph.getWeight(currentNode, currentNeighbor);
        System.out.println("2.distancefromSrcArr[currentNeighbor]: " + distancefromSrcArr[currentNeighbor]);
      }
    }

    return distancefromSrcArr;
  }

  /**
   * This should have been a minHeap...
   */
  public static int minDistance(int graphSize, int[] dist, boolean[] visited){
    int currentMinValue = INFINITY;
    int indexWithMinDist = -1; // start with an invalid index!

    // Loop through the nodes
    for(int i=0;i<graphSize;i++){
      // System.out.println("visited[i]: " + visited[i]);
      // System.out.println("dist[i]: " + dist[i]);
      if(visited[i] == false && dist[i] <= currentMinValue){
        currentMinValue = dist[i];
        indexWithMinDist = i;
        // System.out.println("indexWithMinDist: " + indexWithMinDist);
        // System.out.println("currentMinValue: " + currentMinValue);
      }
    }

    return indexWithMinDist;
  }
}

class Main {
  public static void main(String[] args){
    Graph graph = new Graph(3);
    graph.connect(0, 1, 10);
    graph.connect(0, 2, 100);
    graph.connect(1, 2, 10);
    int[] result = Dijkstra.exec(graph, 0); // {0,10,20} <=== should be

    for(int i=0;i<result.length;i++){
      System.out.println(i + " : " + result[i]);
    }
  }
}

