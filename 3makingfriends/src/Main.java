import java.io.*;

public class Main{

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("/Users/eliasvernersson/IdeaProjects/algorithms-datastructures-course/3makingfriends/data/sample/2.in"));

        try {
            graph.parse(br);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}