package mediator;

import model.Message;

public class MessagePackage {
    private String type;
    private Message message;
    private boolean error;
    private String password;

    public MessagePackage(Message message,String type){
        this.type = type;
        this.message = new Message(message.getUsr(),message.getMsg());
        error = false;
        password = null;
    }

    public MessagePackage(Message message, String type, boolean error){
        this.message = message;
        this.type = type;
        this.error = error;
        password = null;
    }

    public MessagePackage(Message message, String type,String password){
        this.type = type;
        this.message = message;
        this.password = password;
        error = false;
    }

    public String getType() {
        return type;
    }

    public Message getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

    public String getPassword() {
        return password;
    }
}
