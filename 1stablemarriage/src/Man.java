import java.util.*;

public class Man {

    int ID;
    Stack<Woman> prefList = new Stack<>();

    public Man(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Woman getFirstWoman() {
        return prefList.pop();
    }

    /*public int getAtIndex(Woman w) {
        return map.get(w.getID());
    }*/

    /*public void addPrefByWoman(Woman woman, int place) {
        //TODO: Problem is that prefferedBywomen is placing el on index id. The array is initlly empty which will cause trouble. We need to prefill it or use array :)
        //TODO: Also we should invert this array :)
        int id = woman.getID();
        map.put(id, place);
    }*/

    @Override
    public boolean equals(Object obj) {
        return this.ID == ((Man)obj).ID;
    }

    public void addToPref(Woman woman) {
        prefList.push(woman);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: " + getID() + "\n");
        stringBuilder.append("Pref: " + prefList.toString() + "\n");
        return stringBuilder.toString();
    }
}
