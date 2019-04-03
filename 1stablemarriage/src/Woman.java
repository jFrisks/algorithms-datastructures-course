import java.util.HashMap;
import java.util.Map;

public class Woman {

    int ID;
    Man partner;

    Map<Integer, Integer> prefList = new HashMap<>();

    public Woman(int ID) {
        this.ID = ID;
    }

    public Man getPartner() {
        return partner;
    }

    public void setPartner(Man partner) {
        this.partner = partner;
    }

    public int getID() {
        return ID;
    }

    public void addToPref(int manId, int place) {
        prefList.put(manId, place);
    }

    public int getManPlacement(int manId) {
        return prefList.get(manId);
    }
}
