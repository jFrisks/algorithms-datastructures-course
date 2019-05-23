import java.util.*;

public class NetworkFlow {

    private Graph graph;

    public NetworkFlow(Graph graph) {
        this.graph = graph;
    }

    public int calculateFlow() {
        int flow = 0;

        List<Node> path = getShortestPath(graph);;

        while (path != null) {
            System.out.println(path);

            Queue<Edge> edgePath = pathToEdges(path);
            System.out.println(edgePath);

            int maxCapacity = pathMaxCapacity(edgePath);
            System.out.println(maxCapacity);
            flow = flow + maxCapacity;

            updatePathFlow(edgePath, maxCapacity);

            path = getShortestPath(graph);
        }

        return flow;
    }

    private void updatePathFlow(Queue<Edge> edgePath, int maxCapacity) {
        for (Edge edge :
                edgePath) {
            edge.addFlow(maxCapacity);
            int remainingCapacity = edge.getRemainingCapacity();
            if (remainingCapacity == 0){
                edge.remove();
            }
        }
    }

    private List<Node> getShortestPath(Graph graph) {

        Set<Node> visited = new HashSet<>();
        Node startNode = graph.getStartNode();
        Node endNode = graph.getEndNode();

        Deque<Node> nodeQueue = new ArrayDeque<Node>();
        nodeQueue.push(startNode);
        visited.add(startNode);
        Map<Node, Node> predecessor = new HashMap<>();

        int lastNumberOfVisited = -1;
        //Loop will exit when no more nodes are visited
        while (!nodeQueue.isEmpty()) {
            lastNumberOfVisited = visited.size();

            Node node = nodeQueue.pop();

            for (Object neighbour :
                    node.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    visited.add((Node)neighbour);
                    nodeQueue.addLast((Node)neighbour);
                    predecessor.put((Node)neighbour, node);

                    if (neighbour == endNode) {

                        ArrayList<Node> path = new ArrayList<>();
                        Node currentNode = (Node) neighbour;
                        while (currentNode != startNode) {
                            path.add(currentNode);
                            currentNode = predecessor.get(currentNode);
                        }
                        path.add(currentNode);
                        return path;
                    }
                }
            }
        }
        return null;
    }

    private Queue<Edge> pathToEdges(List<Node> path) {

        LinkedList<Node> pathCopy = new LinkedList<>(path);
        LinkedList<Edge> edgePath = new LinkedList<>();

        while (pathCopy.size() != 0) {

            Node first = pathCopy.poll();
            List<Edge> edges = first.getNeighbourEdges();

            for (Edge edge :
                    edges) {
                if ((edge.from == first && edge.to == pathCopy.peek()) || (edge.to == first && edge.from == pathCopy.peek())) {
                    edgePath.add(edge);
                }
            }
        }

        return edgePath;
    }

    private int pathMaxCapacity(Queue<Edge> path) {

        int maxCapacity = Integer.MAX_VALUE;
        for (Edge edge :
                path) {
            int capacity = edge.getRemainingCapacity();
            if (capacity < maxCapacity) {
                maxCapacity = capacity;
            }
        }

        return maxCapacity;
    }

}
