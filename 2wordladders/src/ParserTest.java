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
        assertTrue(parser.hasedge("hello", "lolem"));
        assertFalse("Not true", parser.hasedge("lolem", "hello"));
        assertFalse("Not true" ,parser.hasedge("k", "test"));
    }

}