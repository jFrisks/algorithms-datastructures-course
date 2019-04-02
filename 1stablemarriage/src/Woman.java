public class Woman {

    int ID;
    Man partner;

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
}
