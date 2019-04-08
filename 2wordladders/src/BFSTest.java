import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BFSTest {
    Map<String, List<String>> graph;
    String start = "AA";
    String finish = "SA";

    @Before
    public void SetUp(){
        graph = new HashMap<>();
        graph.put("AA", new ArrayList<>(Arrays.asList("RA", "BA", "CA")));
        graph.put("BA", new ArrayList<>(Arrays.asList("DA", "EA")));
        graph.put("CA", new ArrayList<>(Arrays.asList("DA", "GA")));
        graph.put("RA", new ArrayList<>(Arrays.asList("SA")));
        graph.put("EA", new ArrayList<>(Arrays.asList("FA")));
        graph.put("DA", new ArrayList<>(Arrays.asList("QA")));
        graph.put("GA", new ArrayList<>(Arrays.asList("SA")));
        graph.put("FA", new ArrayList<>());
        graph.put("QA", new ArrayList<>());
        graph.put("SA", new ArrayList<>());
        graph.put("CO", new ArrayList<>());
    }
    @After
    public void breakDown(){

    }

    @Test
    public void testRun(){
        BFS bfs = new BFS();
        assertEquals("Grafen har ej hittat den kortaste på två","2", bfs.run(graph, start, finish));
        assertEquals("Grafen har ej hittat den kortaste på två","1", bfs.run(graph, "EA", "FA"));
        assertEquals("Grafen har ej hittat den kortaste på två","Impossible", bfs.run(graph, "AA", "CO"));
    }
}