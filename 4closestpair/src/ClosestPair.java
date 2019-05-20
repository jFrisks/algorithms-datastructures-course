import java.io.*;
import java.util.*;

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
        //BufferedReader br = new BufferedReader(new FileReader("./4closestpair/data/secret/6huger.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List parsedPoints = parse(br);

        double result = conquerAndDivide(parsedPoints);
        String formattedResult = String.format(Locale.US, "%.6f", result);
        System.out.println(formattedResult);
    }

    static List<PointInt> parse(BufferedReader br) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<PointInt> points;
        int numberOfPlayer;

        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + " ");
        }

        int[] indata = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        numberOfPlayer = indata[0];
        points = new ArrayList<>(numberOfPlayer);

        for (int i = 1; i <= numberOfPlayer * 2; i += 2) {
            PointInt newPoint = new PointInt(indata[i], indata[i+1]);
            points.add(newPoint);
        }

        return points;
    }

    public static double conquerAndDivide(List<PointInt> points) {
        points.sort(Comparator.comparingInt(PointInt::getX));
        //List<PointInt> pointsX = new ArrayList<>(points);
        //List<PointInt> pointsY = new ArrayList<>(points);
        //pointsX.sort(Comparator.comparingInt(PointInt::getX));
        //pointsY.sort(Comparator.comparingInt(PointInt::getY));
        return closest(points);
    }

    private static double closest(List<PointInt> points) {
        //BASE CASE
        if(points.size() < MINIMAL_DIVIDE){
            //TODO calc min value of remaining
            double minDist = Float.MAX_VALUE;

            for(int p1I = 0; p1I < points.size(); p1I++){
                for(int p2I = p1I; p2I < points.size(); p2I++){
                    PointInt p1 = points.get(p1I);
                    PointInt p2 = points.get(p2I);
                    double tmpDist = p1.distance(p2);
                    if (tmpDist < minDist && !p2.equals(p1)) minDist = tmpDist;
                }
            }

            /* WHY IS THIS NOT SLOWER THAN THE ABOVE? -> Because Compiler...
            for(PointInt point : points){
                for(PointInt otherPoint : points){
                    double tmpDist = point.distance(otherPoint);
                    if (tmpDist < minDist && !otherPoint.equals(point)) minDist = tmpDist;
                }
            }
            */

            return minDist;
        }

        //NOMRAL CASE
        int medianXCoordIndex = Math.round(points.size()/2);
        double medianXCoord = points.get(medianXCoordIndex).getX();

        List<PointInt> leftPoints = points.subList(0, medianXCoordIndex);
        List<PointInt> rightPoints = points.subList(medianXCoordIndex, points.size());

        double leftMin = closest(leftPoints);
        double rightMin = closest(rightPoints);
        double partitionsMin = Double.min(leftMin, rightMin);

        //S.add - loop leftArrays element from median until dist-elemen.getX < partitionMin
        int leftBound = medianXCoordIndex;
        while(points.get(leftBound).getX() > medianXCoord - partitionsMin + 1 && leftBound > 0) {
            leftBound -= 1;
        }

        //S add - Same for right array
        int rightBound = medianXCoordIndex;
        while(points.get(rightBound).getX() < medianXCoord + partitionsMin - 1 && rightBound < points.size() - 1) {
            rightBound += 1;
        }

        double stripMin = getStripMin(points, leftBound, rightBound);

        return Double.min(partitionsMin, stripMin);
    }

    private static double getStripMin(List<PointInt> points, int leftBound, int rightBound) {
        /** OPTIMIZATION: Sort in the beginning of divide and conquer, not for each strip. We get extra log factor */
        double stripMin = Float.MAX_VALUE;

        List<PointInt> S = points.subList(leftBound, rightBound + 1);
        S.sort(Comparator.comparingInt(PointInt::getY));

        if (S.size() < 2) stripMin = Double.MAX_VALUE;

        //for each S -> check closest 15 points to the right of the array. Can only exist one point in each "partitionsMinbox". 4 boxes in width, makes 15 boxes to check
        for (int i = 0; i < S.size() - 1; i++) {
            int stripRightBound = (i + 15 > S.size() - 1) ? S.size() - 1 : i + 15;
            List<PointInt> box = S.subList(i + 1, stripRightBound);

            for (PointInt other : box) {
                double tmpDist = S.get(i).distance(other);
                if (tmpDist < stripMin) stripMin = tmpDist;
            }
        }
        return stripMin;
    }
}
