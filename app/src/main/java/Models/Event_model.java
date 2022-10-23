package Models;

public class Event_model {
    String e_name="",e_join_coin="",e_prize_coin="",e_date="",e_pic="",e_time="";

    Event_model(){}

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_join_coin() {
        return e_join_coin;
    }

    public void setE_join_coin(String e_join_coin) {
        this.e_join_coin = e_join_coin;
    }

    public String getE_prize_coin() {
        return e_prize_coin;
    }

    public void setE_prize_coin(String e_prize_coin) {
        this.e_prize_coin = e_prize_coin;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_pic() {
        return e_pic;
    }

    public void setE_pic(String e_pic) {
        this.e_pic = e_pic;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    public Event_model(String e_name, String e_join_coin, String e_prize_coin, String e_date, String e_pic, String e_time) {
        this.e_name = e_name;
        this.e_join_coin = e_join_coin;
        this.e_prize_coin = e_prize_coin;
        this.e_date = e_date;
        this.e_pic = e_pic;
        this.e_time = e_time;
    }
}
