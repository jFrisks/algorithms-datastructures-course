package persons;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Man {

    int ID;
    Stack<Woman> prefList;
    List<Integer> prefferedByWomenList;

    public Man(int ID, int Nsize) {
        this.ID = ID;
        this.prefferedByWomenList = new ArrayList<>(Nsize);
    }

    public int getID() {
        return ID;
    }

    public Woman getFirstWoman() {
        return prefList.pop();
    }

    public int getAtIndex(Woman w) {
        return prefferedByWomenList.get(w.ID);
    }

    public void setWomanPrefList(Stack<Woman> prefList){
        this.prefList = prefList;
    }

    public void addPrefByWoman(Woman woman, int place) {
        //TODO: Problem is that prefferedBywomen is placing el on index id. The array is initlly empty which will cause trouble. We need to prefill it or use array :)
        int id = woman.getID();
        prefferedByWomenList.add(id, place);
    }

    @Override
    public boolean equals(Object obj) {
        return this.ID == ((Man)obj).ID;
    }

    public void addToPref(Woman woman) {
        prefList.push(woman);
    }

}
