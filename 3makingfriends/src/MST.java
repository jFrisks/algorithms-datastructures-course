import java.util.*;

public class MST {
    public Set<Edge> prim(Graph graph) throws Exception {

        Comparator<Edge> comp = new EdgeComparator();
        PriorityQueue<Edge> unvisitedQueue = new PriorityQueue<Edge>(comp);
        //System.out.println("Starting Prim's Algorithm");
        //Q <- unvisited nodes and init the first node
        List<Node<Integer>> Q = new ArrayList<>(graph.getAllNodes());
        Node startNode = Q.get(0);
        Q.remove(startNode);
        updateReachableAndUnvisited(startNode, unvisitedQueue);

        //T <- Empty Output Tree (set of Edges)
        Set<Edge> outputTree = new HashSet<Edge>();



        //System.out.println("Q: " + Q + "unvisitedQueue: " + unvisitedQueue);

        while(!Q.isEmpty()){

            Node nodeToRemove;
            Edge nextEdge;

        //select a v that mimize the w(v). u is from Q, v is from visited. relation between (u,v)
            //nextEdge <- peek next edge from UnvisitedQueue (with least weight)
            nextEdge = unvisitedQueue.peek();

            Boolean edgeFrom = null;
            Boolean edgeTo = null;

            if(nextEdge != null){
                edgeFrom = Q.contains(nextEdge.from);
                edgeTo  = Q.contains(nextEdge.to);
            }

            //If one visited and other not. Legit edge to add.
            if(edgeFrom ^ edgeTo){
                if(edgeTo) nodeToRemove = nextEdge.to;
                else nodeToRemove = nextEdge.from;

                Q.remove(nodeToRemove);
                outputTree.add(nextEdge);
                unvisitedQueue.poll();  //O(logm)
                updateReachableAndUnvisited(nodeToRemove, unvisitedQueue);      //Side effect - bad, bud fast. Adds reachable
            }else if (!edgeFrom && !edgeTo){
                //System.out.println("Tried edge between two already visited nodes");
                unvisitedQueue.poll();
            }else{
                throw new Exception("Both nodes is unvisited. Might be that PriorityHeap tried to pick edge not connected to the tree" + edgeFrom + edgeTo);
            }

            //poll nextEdge from unvisitedQueue


            //Total o(3*m+mlogm+logm)
            //Med m=500k 0> total=1500k+6*500k+6
        }
        return outputTree;
    }

    void updateReachableAndUnvisited(Node node, PriorityQueue<Edge> unvisited){
        node.setNeighboursReachable();

        List neighbourEdges = node.getNeighbourEdges();
        unvisited.addAll(neighbourEdges);
    }
}




//ODL       //How to choose u, v in right  order. Might solve by either on should be in Q and the other not in Q.
//            //Sorting with heapify ---> Priorityueue Does that
//            //Deleteing rel from possibleRels modifies the graph
//            //Adding one to UnvisitiedQueue becomes nlogn -> should be fixed with constructor thich takes a collection
//            //need to see neighboursEdges to change keys - even for first root
//                //Need to update keys when we have added chosendRel to T. Then need to update keys
//            //How to define infiinty weight??
//
//
//
//        //functions available
//            //graph = new Graph
//                //graph.getRealations(weight)
//                //graph.getWeight(relation)
//                //graph.getAllNodes()
//
//
//        //T <- Empty Output Tree
//        //UnvisitedQueue <- min priority queue. Init with allVertices-{r}. For all unvisited: keys as weight(unvisiteds_relation): int
//            //<- allWeights = getAllWeightsExcept(root) or something in graph
//            //PriorityQueu(allWeights)
//        //Q <- unvisited nodes
//
//
//        //While Q /= empty
//            //chosenNode <- null
//            //chosenRel <- null
//
//            //select a v that mimize the w(v). u is from Q, v is from visited. relation between (u,v)
//                //nextW <- get next weight from UnvisitedQueue
//                //nextPossibleRels <- graph.getRelations(nextW)
//                //for relation in nextPossibleRels
//                    //if one of nodes is visited
//                        //if other node not visited
//                            //chosenNode <- select that other not visited node
//                            //chosenRel <- relation
//            //remove chosenNode from Q
//            //remove chosenRel from nextPossibleRels
//            //add chosendRel to T
//            //TODO: Update keys for neighboursEdges of chosenNode
//        //return T


/** VERSION 2 av anteckningar*/
    // //Problems:
//
//
//
//
//        //functions available
//            //graph = new Graph
//                //graph.getNeighbours(node)
//                //graph.getWeight(relation)
//                //graph.getAllNodes()
//
//
//        //T <- Empty Output Tree (set of Edges)
//        //UnvisitedQueue <- min priority queue. Init with allVertices-{r}. For all unvisited: keys as edge, sorting by their weight
//            //allEdges <- TODO:
//            //UnvisitedQueue <- PriorityQueu(allEdges)
//        //Q <- unvisited nodes
//
//
//        //While Q /= empty
//
//
//            //updateReachableAndUnvisited(root)    //Side effect - updates edges weight which updates heap.
//
//            //nodeToRemove <- null
//            //nextEdge <- null
//
//            //select a v that mimize the w(v). u is from Q, v is from visited. relation between (u,v)
//                //nextEdge <- peek next edge from UnvisitedQueue (with least weight)
//                //for node in edge
//                //if one of nodes is visited
//                    //if other node not visited
//                        //nodeToRemove <- select that other not visited node
//                        //TODO hantera fel med ej hittad nod
//                    //else TODO felhantering
//                //else TODO felhantering
//            //updateReachableAndUnvisited(nodeToRemove)      Sideeffect - bad, bud fast
//            //remove nodeToRemove from Q
//            //poll nextEdge from unvisitedQueue
//            //add nextEdge to T
//        //return T
