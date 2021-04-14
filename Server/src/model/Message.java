package model;

import java.io.Serializable;

public class Message implements Serializable {
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
