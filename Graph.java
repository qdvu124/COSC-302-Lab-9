import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;

class Graph
{
    int numVertices;
    LinkedList<Integer>[] adjList;

    public Graph()
    {
      numVertices = 0;
    }

    public void SetNumVertices(int n)
    {
      numVertices = n;
      adjList = new LinkedList[n];
      for(int i = 0; i < adjList.length; i++) {
        adjList[i] = new LinkedList<Integer>();
      }
    }

    public void AddEdge(int u, int v)
    {
      if(!adjList[u].contains(v))
        adjList[u].add(v);
      if(!adjList[v].contains(u))
        adjList[v].add(u);
    }

    public boolean IsConnected()
    {
    	// Perform BFS to check if the graph is connected
    	// Set to keep track of visited nodes
        HashSet<Integer> visited = new HashSet<Integer>();
        // Keeping track of the current node and the next node from the adjacency list
        int current, next;
        // Queue to perform BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
        // Adding an arbitrary start point. Which point doesn't matter since if the graph is connected, all nodes will be added to the visited set
        queue.add(0);
        // Iterator for each list
        Iterator<Integer> iter;
        while(!queue.isEmpty()) {
		// Still unvisited nodes in the queue
		  current = queue.remove();
		  // Adding the node to the list of visited nodes
		  if(!visited.contains(current))
			visited.add(current);
		  // Adding the neighbors of the current node to the queue
		  iter = adjList[current].iterator();
		  while(iter.hasNext()) {
			next = iter.next();
			// Only if the neighbor has not been visited or if the neighbor is not yet in the queue
			if(visited.contains(next) || queue.contains(next))
			  continue;
			queue.add(next);
		  }
        }
        return visited.size() == numVertices;
    }

    public boolean IsCycle()
    {
    	// Automatically fail if condition one is not met
    	if(!IsConnected())
    		return false;
    	// Check that the sizes of all lists are exactly 2
    	for(int i = 0; i < numVertices; i++) {
    		if(adjList[i].size() != 2) {
    			return false;
    		}
    	}
    	// Return true since both conditions are met
        return true;
    }
}
