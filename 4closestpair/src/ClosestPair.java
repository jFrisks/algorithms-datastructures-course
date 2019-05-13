import java.io.*;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
        BufferedReader br = new BufferedReader(new FileReader("./4closestpair/data/secret/1small.in"));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List parsedPoints = parse(br);

        double result = conquerAndDivide(parsedPoints);
        DecimalFormat df = new DecimalFormat("#.######");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
	    df.setMinimumFractionDigits(6);
        df.setMaximumFractionDigits(6);
    	df.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println(df.format(result));
    }

    static List<PointInt> parse(BufferedReader br) throws IOException {
        //TODO: Floats are slower, write our own PointInt-class with int or long

        List<PointInt> points;
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
            PointInt newPoint = new PointInt(indata[i], indata[i+1]);
            points.add(newPoint);
        }

        return points;
    }

    public static double conquerAndDivide(List<PointInt> points) {
        //Sortera hela listan efter x-kordinat

        points.sort((o1, o2) -> {
            if (o1.getX() < o2.getX()) return -1;
            else if (o1.getX() > o2.getX()) return 1;
            else return 0;
        });

       return closest(points);

    }

    private static double closest(List<PointInt> points) {


        if(points.size() < MINIMAL_DIVIDE){
            //TODO calc min value of remaining
            double minDist = Float.MAX_VALUE;
            for(PointInt point : points){
                for(PointInt otherPoint : points){
                    double tmpDist = point.distance(otherPoint);
                    if (tmpDist < minDist && !otherPoint.equals(point)) minDist = tmpDist;
                }
            }
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
        double stripMin = Float.MAX_VALUE;
        //Sort S
        List<PointInt> S = points.subList(leftBound, rightBound + 1);
        S.sort((o1, o2) -> {
            if (o1.getY() < o2.getY()) return -1;
            else if (o1.getY() > o2.getY()) return 1;
            else return 0;
        });

        if (S.size() < 2) stripMin = Double.MAX_VALUE;

        //for each S -> check closest 15 points to the right of the array. Save minValue if
        for (int i = 0; i < S.size() - 1; i++) {
            //Loop through next 15 points in array and check their distance
            //Problems: Index out of bounds. 2nd - keep track of minVal

            int stripRightBound = (i + 15 > S.size() - 1) ? S.size() - 1 : i + 15;
            List<PointInt> box = S.subList(i + 1, stripRightBound);

            for (PointInt other : box) {
                double tmpDist = S.get(i).distance(other);
                if (tmpDist < stripMin) stripMin = tmpDist;
            }
        }
        ;
        return stripMin;
    }
}
