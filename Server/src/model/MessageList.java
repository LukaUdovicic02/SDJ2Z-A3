package model;

import java.util.ArrayList;

public class MessageList {
    private ArrayList<Message> messages;

    public MessageList(){
        messages = new ArrayList<>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public int size(){
        return messages.size();
    }

    public void add(Message message){
        messages.add(message);
    }

    public void remove(Message message){
        messages.remove(message);
    }

    public ArrayList<Message> getMessagesByUsr(String usr){
        ArrayList<Message> byUsr = new ArrayList<>();
        for (Message m : messages){
            if (m.getUsr().equals(usr)){
                byUsr.add(m);
            }
        }
        return byUsr;
    }

    public int getMessageCountByUsr(String usr){
        return getMessagesByUsr(usr).size();
    }

    public int getUniqueUsrCount(){
        ArrayList<String> users = new ArrayList<>();
        for (Message m : messages){
            if (!users.contains(m.getUsr())){
                users.add(m.getUsr());
            }
        }
        return users.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Messages:\n");
        for (Message m : messages){
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
}
