import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testHasEdge() {
        assertTrue(parser.hasEdge("hello", "lolem"));
        assertFalse("Not true", parser.hasEdge("lolem", "hello"));
        assertFalse("Not true" ,parser.hasEdge("k", "test"));
    }

    @Test
    public void testContainedIn() {
        String s1 = "ello";
        String s2 = "lolem";

        assertTrue("Should be contained in", parser.isContainedIn(s1.toCharArray(), s2.toCharArray()));
        assertFalse("Should not be contained in", parser.isContainedIn(s2.toCharArray(), s1.toCharArray()));

        s1 = "ello";
        s2 = "loe";

        assertFalse("Should not be contained in", parser.isContainedIn(s1.toCharArray(), s2.toCharArray()));
        assertTrue("Should be contained in", parser.isContainedIn(s2.toCharArray(), s1.toCharArray()));
    }
}