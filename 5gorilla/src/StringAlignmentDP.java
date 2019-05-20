
public class StringAlignmentDP {
    /** PROBLEMS:
     * - Init value == 0, check so you replace correclty
     *
     *
     *
     * */

    //MAP: cost.get(String.concat(wordX.charAt(i), wordY.charAt(j)))
    //M

    int[][] makeTable(int n, int m, StringAlignmentParser parsed) {
        int[][] OPTTable = new int[m][n];

        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                //Handle outOfbOunds - if one index == 0 -> give starCost
                if(i == 0 || j == 0) OPTTable[j][i]
                int leftCost = parsed.starCost() + OPTTable[j-i][i];
                int downCost = parsed.starCost() + OPTTable[j][i-1];
                int diagonalCost = parsed.cost(i,j) + OPTTable[j-1][i-1];
                int thisTotalBestPoints = Integer.max(diagonalCost, Integer.max(leftCost, downCost));
                OPTTable[i][j] = thisTotalBestPoints;
            }
        }
        return OPTTable;
    }

    int cost(int i, int j){

    }
}
