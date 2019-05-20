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

    int[][] makeTable(String wordX, String wordY, StringAlignmentParser parseCost) {
        int n = wordX.length();
        int m = wordY.length();
        int[][] OPTTable = new int[m][n];
        wordX = " " + wordX;
        wordY = " " + wordY;

        for(int j = 0; j <= m; j++){
            for(int i = 0; i <= n; i++){
                //Handle outOfbOunds - if one index == 0 -> give starCost
                if(i == 0 && j == 0) OPTTable[j][i] = parseCost.getCost("*");
                else if(i == 0) OPTTable[j][i] = (j+1)*parseCost.getCost("*");
                else if(j == 0) OPTTable[j][i] = (i+1)*parseCost.getCost("*");
                else{
                    int downCost = parseCost.getCost("*") + OPTTable[j-i][i];
                    int leftCost = parseCost.getCost("*") + OPTTable[j][i-1];
                    int diagonalCost = cost(i, j, parseCost, wordX, wordY) + OPTTable[j-1][i-1];
                    int thisTotalBestPoints = Integer.max(diagonalCost, Integer.max(leftCost, downCost));
                    OPTTable[i][j] = thisTotalBestPoints;
                }
            }
        }
        return OPTTable;
    }

    private int cost(int i, int j, StringAlignmentParser parseCost, String wordX, String wordY) {
        String keyValue = Character.toString(wordX.charAt(i)) + wordY.charAt(j);
        return parseCost.getCost(keyValue);
    }

    void doStringAlignments(StringAlignmentParser parser) {
        List<Pair> wordPairs = parser.getWordPairs();

        for(Pair pair : wordPairs){
            int[][] costTable = makeTable(pair.getX(), pair.getY(), parser);
            String bestWordX = "";
            String bestWordY = "";

            //TODO: See comments below
            //Gå igenom bästa väg
                //skapa stringen undertiden
            //printa strängarna
        }
    }
}
