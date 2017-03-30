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
        HashSet<Integer> visited = new HashSet<Integer>();
        int current, next;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        Iterator<Integer> iter;
        while(!queue.isEmpty()) {
          current = queue.remove();
          if(!visited.contains(current))
            visited.add(current);
          iter = adjList[current].iterator();
          while(iter.hasNext()) {
            next = iter.next();
            if(visited.contains(next) || queue.contains(next))
              continue;
            queue.add(next);
          }
        }
        return visited.size() == numVertices;
    }

    public boolean IsCycle()
    {
    	if(!IsConnected())
    		return false;
    	for(int i = 0; i < numVertices; i++) {
    		if(adjList[i].size() != 2) {
    			return false;
    		}
    	}
        return true;
    }
}
