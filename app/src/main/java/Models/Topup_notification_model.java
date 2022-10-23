package Models;

public class Topup_notification_model {
    String date="";
    String diamond="";
    String taka="";

    public Topup_notification_model(String date, String diamond, String taka, String uid) {
        this.date = date;
        this.diamond = diamond;
        this.taka = taka;
        this.uid = uid;
    }

    String uid="";

    public Topup_notification_model(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
