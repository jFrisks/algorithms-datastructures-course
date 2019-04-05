import java.util.*;

public class BFS {
    public String run(Map<String, List<String>> V, String s, String t){
        Map<String, Boolean> visited = new HashMap<>();

        Deque<String> q = new ArrayDeque<String>();
        q.addFirst(s);

        //Making every node unvisited except s
        for(String v: V.keySet()){
            visited.put(v, false);
        }
        visited.put(s, true);

        while(!q.isEmpty()){
            String current = q.pop();

            //Loop through neighbours
            for(String neighbour: V.get(current)){
                if(!visited.get(neighbour)){
                    visited.put(neighbour, true);
                    q.addLast(neighbour);
                    predecessor(neighbour) = current;
                }
            }
        }

        return "";
    }


}
