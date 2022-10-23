package Models;

public class Withdraw_model {
    String namw="",uid="",pic="",taka="",number="",operator="",date="";
    public Withdraw_model(){}

    public Withdraw_model(String namw, String uid, String pic, String taka, String number, String operator, String date) {
        this.namw = namw;
        this.uid = uid;
        this.pic = pic;
        this.taka = taka;
        this.number = number;
        this.operator = operator;
        this.date = date;
    }

    public String getNamw() {
        return namw;
    }

    public void setNamw(String namw) {
        this.namw = namw;
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

    public String getTaka() {
        return taka;
    }

    public void setTaka(String taka) {
        this.taka = taka;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
