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
        int[][] OPTTable = new int[m + 1][n + 1];
        wordX = " " + wordX;
        wordY = " " + wordY;

        for(int y = 0; y <= m; y++){
            for(int x = 0; x <= n; x++){
                //Handle outOfbOunds - if one index == 0 -> give starCost
                if(x == 0 && y == 0) OPTTable[y][x] = 0;
                else if(x <= 0) OPTTable[y][x] = (y)*parseCost.getCost("*");
                else if(y <= 0) OPTTable[y][x] = (x)*parseCost.getCost("*");
                else{
                    int upCost = parseCost.getCost("*") + OPTTable[y-1][x];
                    int leftCost = parseCost.getCost("*") + OPTTable[y][x-1];
                    int diagonalCost = cost(y, x, parseCost, wordX, wordY) + OPTTable[y-1][x-1];
                    int thisTotalBestPoints = Integer.max(diagonalCost, Integer.max(leftCost, upCost));
                    OPTTable[y][x] = thisTotalBestPoints;
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

            for (int y = 0; y < costTable.length; y++) {
                for (int x = 0; x < costTable[y].length; x++) {
                    System.out.print("[" + costTable[y][x] + "] \t");
                }
                System.out.println();
            }

            Pair bestPair = buildBestPair(pair, new Pair("", ""), pair.getY().length(), pair.getX().length(), costTable, parser);
            System.out.println(bestPair.getX() + " " + bestPair.getY());
        }
    }

    private static Pair BestPair(Pair inputPair, Pair buildPair, int j, int i, int[][] costTable, StringAlignmentParser sap) {
        /*

        int goDiagonalCost = Integer.MIN_VALUE;

        if(j >= 1 && i >= 1) goDiagonalCost = costTable[j-1][i-1];

        String oldX = buildPair.getX();
        String oldY = buildPair.getY();

        if(goDiagonalCost >= goLeftCost && goDiagonalCost >= goUpCost) {
            buildPair = new Pair( inputPair.getX().charAt(i - 1) + oldX, inputPair.getY().charAt(j - 1) + oldY);
            return buildBestPair(inputPair, buildPair, j-1, i-1, costTable);
        }
        else if(goUpCost >= goLeftCost && goUpCost >= goDiagonalCost) {
            buildPair = new Pair("*" + oldX, inputPair.getY().charAt(j - 1) + oldY);
            return buildBestPair(inputPair, buildPair, j-1, i, costTable);
        }
        else {
            buildPair = new Pair(inputPair.getX().charAt(i - 1) + oldX, "*" + oldY);
            return buildBestPair(inputPair, buildPair, j, i - 1, costTable);
        }
        */

        int goLeftCost = Integer.MIN_VALUE;
        int goUpCost = Integer.MIN_VALUE;

        char currentXChar = '*';
        char currentYChar = '*';

        if(i >= 1 && j >= 0) {
            currentXChar = inputPair.getX().charAt(i - 1);
            goLeftCost = costTable[j][i - 1];
        }
        if(j >= 1 && i >= 0) {
            currentYChar = inputPair.getY().charAt(j - 1);
            goUpCost = costTable[j-1][i];
        }
        if(i <= 1 && j <= 1) {
            return buildPair.prependX(currentXChar).prependY(currentYChar);
        }

        String key = "" + currentXChar + currentYChar;

        if (costTable[j][i] == costTable[j-1][i-1] + sap.getCost(key)) {
            buildPair.prependX(currentXChar).prependY(currentYChar);
            return buildBestPair(inputPair, buildPair, j - 1, i - 1, costTable, sap);
        }

        if (goLeftCost >= goUpCost) {
            buildPair.prependX(currentXChar).prependY('*');
            return buildBestPair(inputPair, buildPair, j, i - 1, costTable, sap);
        } else {
            buildPair.prependX('*').prependY(currentYChar);
            return buildBestPair(inputPair, buildPair, j - 1, i, costTable, sap);
        }
    }

    private static Pair buildBestPair(Pair inputPair, Pair buildPair, int y, int x, int[][] costTable, StringAlignmentParser sap) {

        while (!(x == 0 && y == 0)) {
            int goLeftCost = Integer.MIN_VALUE;
            int goUpCost = Integer.MIN_VALUE;
            int diagCost = Integer.MIN_VALUE;
            int currentCost = Integer.MIN_VALUE;
            boolean atEdge = (x <= 0 || y <= 0);

            char currentXChar = ' ';
            char currentYChar = ' ';

            if(x > 0 && y >= 0) {
                currentXChar = inputPair.getX().charAt(x-1);
                goLeftCost = costTable[y][x - 1];
            }
            if(y > 0 && x >= 0) {
                currentYChar = inputPair.getY().charAt(y-1);
                goUpCost = costTable[y-1][x];
            }

            String key = "" + currentXChar + currentYChar;
            boolean isDiagonal = false;
            if (!atEdge) {
                isDiagonal = costTable[y][x] == costTable[y-1][x-1] + sap.getCost(key);
                diagCost = costTable[y-1][x-1];
            }

            if (x >= 0 && y >= 0 ) currentCost = costTable[y][x];

            boolean isLeft = currentCost - goLeftCost == sap.getCost("*");
            boolean isUp =  currentCost - goUpCost == sap.getCost("*");

            int max = Math.max(diagCost, Math.max(goUpCost, goLeftCost));

            if (isDiagonal) {
                buildPair.prependX(currentXChar).prependY(currentYChar);
                x--;
                y--;
            } else if (goLeftCost == max) {
                buildPair.prependX(currentXChar).prependY('*');
                x--;
            }else if (diagCost == max) {
                buildPair.prependX(currentXChar).prependY(currentYChar);
                x--;
                y--;
            } else if (goUpCost == max) {
                buildPair.prependX('*').prependY(currentYChar);
                y--;
            }
        }

        return buildPair;
    }
}
