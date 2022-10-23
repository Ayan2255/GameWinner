package Models;

public class Sms_model {
    Sms_model(){}
String sms="",date="",name="",game_date="",game_time="";

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }

    public Sms_model(String sms, String date, String name, String game_date, String game_time) {
        this.sms = sms;
        this.date = date;
        this.name = name;
        this.game_date = game_date;
        this.game_time = game_time;
    }
}
