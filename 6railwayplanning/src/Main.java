import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    static int bestFlow = 5;
    static int bestAmountDeleted = 5;
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();

        BufferedReader br;

        br = new BufferedReader(new FileReader("./6railwayplanning/data/secret/4huge.in"));
        //br = new BufferedReader(new InputStreamReader(System.in));

        Graph graph = parser.parse(br);
        int bestFlow = 0;
        int bestAmountDeleted = 0;

        int rangeSize = graph.getRemoveRoutes().size();
        int[] rangeArr = new int[rangeSize];
        for(int i = 0; i < rangeSize; i++){
            rangeArr[i] = i;
        }

        binarysearchWithNetoworflow(rangeArr, 0, rangeArr.length-1, graph);
    }


    private static int binarysearchWithNetoworflow(int[] arr, int left, int right, Graph graph){
        //returning amountofroadsdeleted, mid.
        //TODO: fixa bättre basfall - när listan är tom
        if(right >= left){
            int mid = left + (right-left)/2;
            int flow = flowWithoutRoutes(graph, mid);
            int minimalFlow = graph.getMinimalFlow();
            /*if(flow  == minimalFlow) {
                bestFlow = flow;
                bestAmountDeleted = mid;
                //TODO: is mid right or mid+1?
                System.out.println(bestAmountDeleted + " " + bestFlow);
                return bestAmountDeleted;
            }*/
            if(flow >= minimalFlow){
                //spara undan de bästa flow och deleted vi fått
                bestFlow = flow;
                bestAmountDeleted = mid;
                //Ta högre halvan av arr och prova
                System.out.println(bestAmountDeleted + " " + bestFlow);
                return binarysearchWithNetoworflow(arr, mid+1, right, graph);

            }
            if(flow < minimalFlow){
                //ta vänster halva
                System.out.println(bestAmountDeleted + " " + bestFlow);
                return binarysearchWithNetoworflow(arr, left, mid -1, graph);
            }
        }

        System.out.println(bestAmountDeleted + " " + bestFlow);
        return bestAmountDeleted;
    }

    private static int flowWithoutRoutes(Graph graph, int range) {
        Graph graphCopy = graph.copy();
        List<Edge> removeRoutes = graphCopy.getRemoveRoutes();
        NetworkFlow nf = new NetworkFlow(graphCopy);

        for (int i = 0; i < range; i++) {
            removeRoutes.get(i).remove();
        }

        return  nf.calculateFlow();
    }

}
