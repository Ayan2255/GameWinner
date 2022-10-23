package Models;

public class Event_notification {
    String name="",date="",time="",auth_uid="",uid="",current_date="";
    public Event_notification(){}

    public Event_notification(String name, String date, String time, String auth_uid, String uid, String current_date) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.auth_uid = auth_uid;
        this.uid = uid;
        this.current_date = current_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuth_uid() {
        return auth_uid;
    }

    public void setAuth_uid(String auth_uid) {
        this.auth_uid = auth_uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
}
