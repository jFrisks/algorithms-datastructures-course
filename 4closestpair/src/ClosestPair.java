import javafx.geometry.Point2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPair {
    /** PROBLEMS to tackle
     * - Dela
     *
     *
     *
     * OPTIMIZING
     * - Fix n^2-func in basecase for calc minvaues.
     * */


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

    private static double closest(List<Point2D> points) {
        double stripMin = Float.MAX_VALUE;

        if(points.size() < MINIMAL_DIVIDE){
            //TODO calc min value of remaining
            double minDist = Float.MAX_VALUE;
            for(Point2D point : points){
                for(Point2D otherPoint : points){
                    double tmpDist = point.distance(otherPoint);
                    if (tmpDist < minDist) minDist = tmpDist;
                }
            }
            return minDist;
        }

        //NOMRAL CASE
        int medianXCoordIndex = Math.round(points.size()/2);
        double medianXCoord = points.get(medianXCoordIndex).getX();

        List<Point2D> leftPoints = points.subList(0, medianXCoordIndex);
        List<Point2D> rightPoints = points.subList(medianXCoordIndex + 1, points.size() - 1);

        double leftMin = closest(leftPoints);
        double rightMin = closest(rightPoints);
        double partitionsMin = Double.min(leftMin, rightMin);


        //S.add - loop leftArrays element from median until dist-elemen.getX < partitionMin
        //S add - Same for right array
        //Sort S

        //for each S -> check closest 15 points to the right of the array. Save minValue if

        return Double.min(partitionsMin, stripMin);
    }
}
