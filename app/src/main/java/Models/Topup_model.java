package Models;

public class Topup_model {

    String diamond="",taka="",name="";
    public Topup_model() {

    }

    public Topup_model(String diamond, String taka, String name) {
        this.diamond = diamond;
        this.taka = taka;
        this.name = name;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public String getTaka() {
        return taka;
    }

    public void setTaka(String taka) {
        this.taka = taka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
