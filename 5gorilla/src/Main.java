import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./5gorilla/data/secret/1small.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringAlignmentParser parser = new StringAlignmentParser();
        try {
            parser.parse(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringAlignmentDP.doStringAlignments(parser);
    }
}
