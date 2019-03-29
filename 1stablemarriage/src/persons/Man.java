package persons;

import java.util.Stack;

public class Man {

    int ID;
    Stack<Woman> prefList;
    int[] prefferedByWomenList;

    public Man(int ID, Stack<Woman> prefList, int[] prefferedByWomenList) {
        this.ID = ID;
        this.prefList = prefList;
        this.prefferedByWomenList = prefferedByWomenList;
    }

    public Woman getFirstWoman() {
        return prefList.pop();
    }

    public int getAtIndex(Woman w) {
        return prefferedByWomenList[w.ID];
    }
}
