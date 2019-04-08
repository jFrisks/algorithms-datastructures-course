import java.util.*;

public class BFS {
    public String run(Map<String, List<String>> graph, String s, String t){
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, String> predecessor = new HashMap<>();

        Deque<String> q = new ArrayDeque<String>();
        q.addFirst(s);

        //Making every node unvisited except s
        for(String v: graph.keySet()){
            visited.put(v, false);
        }

        visited.put(s, true);

        while(!q.isEmpty()){
            String current = q.pop();

            //Loop through neighbours
            for(String neighbour: graph.get(current)){
                if(!visited.get(neighbour)){
                    visited.put(neighbour, true);
                    q.addLast(neighbour);
                    //predecessor(neighbour) = current;
                    predecessor.put(neighbour, current);
                }
            }
        }

        return "";
    }

    public int getPathLength(){
        return -1;
    }


}
