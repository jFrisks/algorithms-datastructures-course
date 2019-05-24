import java.util.*;

public class Graph {

    private Node startNode;
    private Node endNode;
    private List<Edge> removeRoutes = new ArrayList<>();
    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;
    private int flow;

    public void setStartNode(Node node) {
        startNode = node;
    }

    public void setEndNode(Node node) {
        endNode = node;
    }

    public void addRemoveRoute(Edge edge) {
        removeRoutes.add(edge);
    }

    public Graph copy() {
        Graph graph = new Graph();

        Map<Integer, Node> newNodes = new HashMap<>();
        for (Node node :
                nodes.values()) {
            newNodes.put((int)node.ID, new Node(node.ID));
        }

        /*--------------------------------------------*/

        Map<Integer, Edge> newEdges = new HashMap<>();
        for (Edge edge :
                edges.values()) {
            int fromID = (int)edge.from.ID;
            int toID = (int)edge.to.ID;
            Node from = newNodes.get(fromID);
            Node to = newNodes.get(toID);
            Edge newEdge = new Edge(edge.getID(), from, to, edge.getCapacity(), edge.getFlow());
            newEdges.put(newEdge.getID(), newEdge);
            from.addNeighbour(newEdge);
            to.addNeighbour(newEdge);
        }

        /*--------------------------------------------*/

        for (Edge route :
                removeRoutes) {
            graph.addRemoveRoute(newEdges.get(route.getID()));
        }

        /*--------------------------------------------*/

        Node startNode = newNodes.get(0);
        Node endNode = newNodes.get(newNodes.size() - 1);
        graph.setStartNode(startNode);
        graph.setEndNode(endNode);
        graph.mapRelations(newNodes, newEdges);

        return graph;
    }

    public void mapRelations(Map<Integer, Node> nodes, Map<Integer, Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Set<Node> getAllNodes() {
        return new HashSet<>(nodes.values());
    }

    public Node getEndNode() {
        return endNode;
    }

    public List<Edge> getRemoveRoutes() {
        return removeRoutes;
    }

    public int getMinimalFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
