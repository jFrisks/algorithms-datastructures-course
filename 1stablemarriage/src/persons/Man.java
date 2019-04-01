package persons;

import java.util.Stack;

public class Man {

    int ID;
    Stack<Woman> prefList;
    int[] prefferedByWomenList;

    public Man(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Woman getFirstWoman() {
        return prefList.pop();
    }

    public int getAtIndex(Woman w) {
        return prefferedByWomenList[w.ID];
    }

    public void setWomanPrefList(Stack<Woman> prefList){
        this.prefList = prefList;
    }

    public void setPrefferedByWomenList(int[] prefferedByWomenList) {
        this.prefferedByWomenList = prefferedByWomenList;
    }

    @Override
    public boolean equals(Object obj) {
        return this.ID == ((Man)obj).ID;
    }

}
