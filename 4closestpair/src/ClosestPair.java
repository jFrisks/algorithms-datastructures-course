import javafx.geometry.Point2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPair {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./4closestpair/data/sample/1.in"));

        List parsedPoints = parse(br);
        System.out.println("Done");
    }

    static List<Point2D> parse(BufferedReader br) throws IOException {
        //TODO: Floats are slower, write our own Point-class with int or long

        List<Point2D> points;
        int numberOfPlayer;

        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + " ");
        }

        int[] indata = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        numberOfPlayer = indata[0];
        points = new ArrayList<>(numberOfPlayer);

        for (int i = 1; i <= numberOfPlayer * 2; i += 2) {
            Point2D newPoint = new Point2D(indata[i], indata[i+1]);
            points.add(newPoint);
        }

        return points;
    }


    public static void ConquerAndDivide(){
        System.out.println("TODO");
    }
}
