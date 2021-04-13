package model;

public class Message {
    private String usr;
    private String msg;

    public Message(String usr, String msg){
        this.usr = usr;
        this.msg = msg;
    }

    public String getUsr() {
        return usr;
    }

    public String getMsg() {
        return msg;
    }

    @Override public String toString(){
        return usr + ": "+msg;
    }
}
