import java.util.*;

public class BFS {
    public String run(Map<String, List<String>> graph, String start, String finish){
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, String> predecessor = new HashMap<>();

        Deque<String> q = new ArrayDeque<String>();
        q.addFirst(start);

        //Making every node unvisited except s
        for(String v: graph.keySet()){
            visited.put(v, false);
        }

        visited.put(start, true);

        while(!q.isEmpty()){
            String current = q.pop();

            //Loop through neighbours
            for(String neighbour: graph.get(current)){
                if(!visited.get(neighbour)){
                    visited.put(neighbour, true);
                    q.addLast(neighbour);
                    //predecessor(neighbour) = current;
                    predecessor.put(neighbour, current);
                    if(neighbour.matches(finish)){
                        return String.valueOf(getPathLength(predecessor, start, finish));
                    }
                }
            }
        }

        return "Nothing found";
    }

    public int getPathLength(Map<String,String> predecessor, String start, String finish){
        int count = 0;

        String next = predecessor.get(finish);
        while(next != null){
            count++;
            if(next == start) return count;
            next = predecessor.get(next);
        }
        return -1;
    }



}
