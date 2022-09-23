// A Java program for Dijkstra's 
// single source shortest path, to every other node
class Main { 
  
    private static final int NO_PARENT = -1; 
  
    private static void dijkstra(int[][] adjacencyMatrix, int startVertex) { 

        // Size 
        int size = adjacencyMatrix[0].length; 
  
        // shortestDistanceFromSrc[i] will hold the 
        // shortest distance from src to i 
        // shortest distance from src to i
        int[] shortestDistanceFromSrc = new int[size]; 
  
        // visited[i] will true if vertex i is 
        // included / in shortest path tree 
        // or shortest distance from src to  
        // i is finalized 
        boolean[] visited = new boolean[size]; 
  
        // Initialize all distances as  
        // INFINITE and visited[] as false 
        for (int i = 0; i < size; i++) { 
            shortestDistanceFromSrc[i] = Integer.MAX_VALUE; 
            visited[i] = false; 
        } 
          
        // Distance of source vertex from 
        // itself is always 0 
        shortestDistanceFromSrc[startVertex] = 0; 
  
        // Parent array to store shortest 
        // path tree 
        int[] parents = new int[size]; 
  
        // The starting vertex does not  
        // have a parent 
        parents[startVertex] = NO_PARENT; 
  
        // Find shortest path for all  
        // vertices 
        for (int i = 1; i < size; i++) { 
  
            // Pick the minimum distance vertex 
            // from the set of vertices not yet 
            // processed. nearestVertex is  
            // always equal to startNode in  
            // first iteration. 
            int nearestVertex = -1; 
            int shortestDistance = Integer.MAX_VALUE; 
            for (int vertexIndex = 0; vertexIndex < size; vertexIndex++) { 
                if (
                    !visited[vertexIndex] && 
                    shortestDistanceFromSrc[vertexIndex] < shortestDistance
                ){ 
                    nearestVertex = vertexIndex; 
                    shortestDistance = shortestDistanceFromSrc[vertexIndex]; 
                } 
            } 
  
            // Mark the picked vertex as 
            // processed 
            visited[nearestVertex] = true; 
  
            // Update dist value of the 
            // adjacent vertices of the 
            // picked vertex. 
            for (int vertexIndex = 0;  vertexIndex < size; vertexIndex++) { 
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 
                  
                if (
                    edgeDistance > 0 &&
                     ((shortestDistance + edgeDistance) < shortestDistanceFromSrc[vertexIndex])
                ){ 
                    parents[vertexIndex] = nearestVertex; 
                    shortestDistanceFromSrc[vertexIndex] = shortestDistance + edgeDistance; 
                } 
            } 
        } 
  
        printSolution(startVertex, shortestDistanceFromSrc, parents); 
    } 
  
    // A utility function to print  
    // the constructed distances 
    // array and shortest paths 
    private static void printSolution(int startVertex, 
                                      int[] distances, 
                                      int[] parents) 
    { 
        int size = distances.length; 
        System.out.print("Vertex\t Distance\tPath"); 
          
        for (int vertexIndex = 0;  
                 vertexIndex < size;  
                 vertexIndex++)  
        { 
            if (vertexIndex != startVertex)  
            { 
                System.out.print("\n" + startVertex + " -> "); 
                System.out.print(vertexIndex + " \t\t "); 
                System.out.print(distances[vertexIndex] + "\t\t"); 
                printPath(vertexIndex, parents); 
            } 
        } 
    } 
  
    // Function to print shortest path 
    // from source to currentVertex 
    // using parents array 
    private static void printPath(int currentVertex, 
                                  int[] parents) 
    { 
          
        // Base case : Source node has 
        // been processed 
        if (currentVertex == NO_PARENT) 
        { 
            return; 
        } 
        printPath(parents[currentVertex], parents); 
        System.out.print(currentVertex + " "); 
    } 
  
        // Driver Code 
    public static void main(String[] args) 
    { 
        int[][] adjacencyMatrix = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
                                    { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
                                    { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
                                    { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
                                    { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
                                    { 0, 0, 4, 0, 10, 0, 2, 0, 0 }, 
                                    { 0, 0, 0, 14, 0, 2, 0, 1, 6 }, 
                                    { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
                                    { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
        dijkstra(adjacencyMatrix, 0); 
    } 
} 
  
