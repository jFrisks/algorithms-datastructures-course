import java.io.*;
import java.util.*;

public class ClosestPair {
    //TODO: Change arraylist to array. This will


    final static int MINIMAL_DIVIDE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./4closestpair/data/secret/4larger.in"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        //points.sort(Comparator.comparingInt(PointInt::getX));
        List<PointInt> pointsX = new ArrayList<>(points);
        List<PointInt> pointsY = new ArrayList<>(points);
        pointsX.sort(Comparator.comparingInt(PointInt::getX));
        pointsY.sort(Comparator.comparingInt(PointInt::getY));
        return closest(pointsX, pointsY);
    }

    private static double closest(List<PointInt> pointsX, List<PointInt> pointsY) {
        //BASE CASE
        if(pointsX.size() < MINIMAL_DIVIDE){
            //TODO calc min value of remaining
            double minDist = Float.MAX_VALUE;

            for(int p1I = 0; p1I < pointsX.size(); p1I++){
                for(int p2I = p1I; p2I < pointsX.size(); p2I++){
                    PointInt p1 = pointsX.get(p1I);
                    PointInt p2 = pointsX.get(p2I);
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
        int medianXCoordIndex = Math.round(pointsX.size()/2);
        double medianXCoord = pointsX.get(medianXCoordIndex).getX();

        List<PointInt> leftPoints = pointsX.subList(0, medianXCoordIndex);
        List<PointInt> rightPoints = pointsX.subList(medianXCoordIndex, pointsX.size());
        Set<PointInt> leftPointSet = new HashSet<>(leftPoints); //O(n)
        Set<PointInt> rightPointSet = new HashSet<>(rightPoints); //O(n)
        List<PointInt> leftPointsY = new ArrayList<>(leftPoints.size());
        List<PointInt> rightPointsY = new ArrayList<>(leftPoints.size());
        for(PointInt point: pointsY){
            if(leftPointSet.contains(point)) leftPointsY.add(point);
            if(rightPointSet.contains(point)) rightPointsY.add(point);
        }

        double leftMin = closest(leftPoints, pointsY);
        double rightMin = closest(rightPoints, pointsY);
        double partitionsMin = Double.min(leftMin, rightMin);

        //S.add - loop leftArrays element from median until dist-elemen.getX < partitionMin
        int leftBound = medianXCoordIndex;
        while(pointsX.get(leftBound).getX() > medianXCoord - partitionsMin + 1 && leftBound > 0) {
            leftBound -= 1;
        }

        //S add - Same for right array
        int rightBound = medianXCoordIndex;
        while(pointsX.get(rightBound).getX() < medianXCoord + partitionsMin - 1 && rightBound < pointsX.size() - 1) {
            rightBound += 1;
        }

        double stripMin = getStripMin(pointsX, pointsY, leftBound, rightBound);

        return Double.min(partitionsMin, stripMin);
    }

    private static double getStripMin(List<PointInt> pointsX, List<PointInt> pointsY, int leftBound, int rightBound) {
        /** OPTIMIZATION: Sort in the beginning of divide and conquer, not for each strip. We get extra log factor */
        double stripMin = Float.MAX_VALUE;

        List<PointInt> Sx = pointsX.subList(leftBound, rightBound + 1);
        Set<PointInt> SxSet = new HashSet<>(Sx);
        List<PointInt> Sy = new ArrayList<>(Sx.size());
        for(PointInt point: pointsY){
            if(SxSet.contains(point)) Sy.add(point);
        }

        /* OLD Creating of Sy
        List<PointInt> S = points.subList(leftBound, rightBound + 1);
        S.sort(Comparator.comparingInt(PointInt::getY));
        */


        if (Sy.size() < 2) stripMin = Double.MAX_VALUE;

        //for each S -> check closest 15 points to the right of the array. Can only exist one point in each "partitionsMinbox". 4 boxes in width, makes 15 boxes to check
        for (int i = 0; i < Sy.size() - 1; i++) {
            int stripRightBound = (i + 15 > Sy.size() - 1) ? Sy.size() - 1 : i + 15;
            List<PointInt> box = Sy.subList(i + 1, stripRightBound);

            for (PointInt other : box) {
                double tmpDist = Sy.get(i).distance(other);
                if (tmpDist < stripMin) stripMin = tmpDist;
            }
        }
        return stripMin;
    }
}
