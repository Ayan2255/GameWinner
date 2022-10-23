package Models;

import java.net.URI;

public class User_model {

    String name="";
    String Phone="";
    String emali="";
    String pass="";
    String uid="";
    String pic="";
    String sms="";
    String coin="00";

    public User_model(String name, String phone, String emali, String pass, String uid, String pic, String sms, String coin) {
        this.name = name;
        Phone = phone;
        this.emali = emali;
        this.pass = pass;
        this.uid = uid;
        this.pic = pic;
        this.sms = sms;
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmali() {
        return emali;
    }

    public void setEmali(String emali) {
        this.emali = emali;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public User_model(){}
}
