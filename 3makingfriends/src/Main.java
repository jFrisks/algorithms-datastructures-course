import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String eliasFilePath = "";
        //BufferedReader br = new BufferedReader(new FileReader("/Users/eliasvernersson/IdeaProjects/algorithms-datastructures-course/3makingfriends/data/sample/2.in"));
        BufferedReader br = new BufferedReader(new FileReader("./3makingfriends/data/sample/2.in"));

        graph.parse(br);
        MST mst = new MST();
        mst.prim(graph);
    }
}