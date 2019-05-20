import java.util.List;
import java.util.Map;

public class StringAlignmentDP {
    /** PROBLEMS:
     * - Init value == 0, check so you replace correclty
     *
     *
     *
     * */

    /** REFACORS:
     * - Sorting in cost or getCost?
     *
     * */

    private static int[][] makeTable(String wordX, String wordY, StringAlignmentParser parseCost) {
        int n = wordX.length();
        int m = wordY.length();
        int[][] OPTTable = new int[m][n];
        wordX = " " + wordX;
        wordY = " " + wordY;

        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                //Handle outOfbOunds - if one index == 0 -> give starCost
                if(i <= 0 && j <= 0) OPTTable[j][i] = 0;
                else if(i <= 0) OPTTable[j][i] = (j)*parseCost.getCost("*");
                else if(j <= 0) OPTTable[j][i] = (i)*parseCost.getCost("*");
                else{
                    int upCost = parseCost.getCost("*") + OPTTable[j-1][i];
                    int leftCost = parseCost.getCost("*") + OPTTable[j][i-1];
                    int diagonalCost = cost(j, i, parseCost, wordX, wordY) + OPTTable[j-1][i-1];
                    int thisTotalBestPoints = Integer.max(diagonalCost, Integer.max(leftCost, upCost));
                    OPTTable[j][i] = thisTotalBestPoints;
                }
            }
        }
        return OPTTable;
    }

    private static int cost(int j, int i, StringAlignmentParser parseCost, String wordX, String wordY) {
        String keyValue = Character.toString(wordX.charAt(i)) + wordY.charAt(j);
        return parseCost.getCost(keyValue);
    }

    static void doStringAlignments(StringAlignmentParser parser) {
        List<Pair> wordPairs = parser.getWordPairs();

        for(Pair pair : wordPairs){
            int[][] costTable = makeTable(pair.getX(), pair.getY(), parser);
            Pair bestPair = buildBestPair(pair, new Pair("", ""), pair.getY().length()-1, pair.getX().length()-1, costTable);
            System.out.println(bestPair.getX() + " " + bestPair.getY());
        }
    }

    private static Pair buildBestPair(Pair inputPair, Pair buildPair, int j, int i, int[][] costTable) {
        int goLeftCost = Integer.MIN_VALUE;
        int goUpCost = Integer.MIN_VALUE;
        int goDiagonalCost = Integer.MIN_VALUE;

        if(i < 0 && j < 0) return buildPair;
        if(i >= 1 && j >= 0) goLeftCost = costTable[j][i-1];
        if(j >= 1 && i >= 0) goUpCost = costTable[j-1][i];
        if(j >= 1 && i >= 1) goDiagonalCost = costTable[j-1][i-1];

        String oldX = buildPair.getX();
        String oldY = buildPair.getY();

        if(goDiagonalCost >= goLeftCost && goDiagonalCost >= goUpCost) {
            buildPair = new Pair( inputPair.getX().charAt(i) + oldX, inputPair.getY().charAt(j) + oldY);
            return buildBestPair(inputPair, buildPair, j-1, i-1, costTable);
        }
        else if(goUpCost >= goLeftCost && goUpCost >= goDiagonalCost) {
            buildPair = new Pair("*" + oldX, inputPair.getY().charAt(j) + oldY);
            return buildBestPair(inputPair, buildPair, j-1, i, costTable);
        }
        else {
            buildPair = new Pair(inputPair.getX().charAt(i) + oldX, "*" + oldY);
            return buildBestPair(inputPair, buildPair, j, i - 1, costTable);
        }
    }
}
