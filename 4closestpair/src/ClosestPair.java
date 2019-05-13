import javafx.geometry.Point2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPair {

    final static int MINIMAL_DIVIDE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./4closestpair/data/sample/1.in"));

        List parsedPoints = parse(br);
        System.out.println("Done");

        conquerAndDivide(parsedPoints);
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

    public static void conquerAndDivide(List<Point2D> points) {
        //Sortera hela listan efter x-kordinat

        points.sort((o1, o2) -> {
            if (o1.getX() < o2.getX()) return -1;
            else if (o1.getX() > o2.getX()) return 1;
            else return 0;
        });

        closest(points);

    }

    private static int closest(List<Point2D> points) {

        // Basfall  - om points size är mindre än n^2-calc gränsens
            //DO speciial STUFF - return min dist


        if(points.size() < MINIMAL_DIVIDE){
            //TODO calc min value

            return minValue;
        }

        int median = Math.round(points.size()/2);
        List<Point2D> leftPoints = points.subList(0, median);
        List<Point2D> rightPoints = points.subList(median + 1, points.size() - 1);

        int leftMin = closest(leftPoints);
        int rightMin = closest(rightPoints);



        //Min of leftMin and rightMin
    }
}
