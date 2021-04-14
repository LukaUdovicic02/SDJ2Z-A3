package model;

import mediator.ChatServer;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;
import java.rmi.RemoteException;

public class ModelManager implements Model {
    private MessageList messageList;
    private PropertyChangeHandler<String, Message> property;
    private UserList userList;
    private LogMultiton multiton;
    private ChatServer chatServer;

    public ModelManager() throws IOException {
        this.property = new PropertyChangeHandler<>(this,true);
        this.messageList = new MessageList();
        this.userList = new UserList();
        chatServer = new ChatServer(this);
    }

    @Override
    public void addMessage(Message messageObject,String ip) {
        messageList.add(messageObject);
        addLog(messageObject.toString() + " " + ip);
        property.firePropertyChange("Message",messageObject.getMsg(),messageObject);
    }

    public void addLog(String log) {
        multiton = LogMultiton.getInstance(new DateTime().getSortableDate());
        String longLine = multiton.addLog(log);
        property.firePropertyChange("Log" ,longLine, null);
    }

    @Override
    public boolean login(String usr, String pwd) throws Exception {
        boolean result = userList.login(usr, pwd);
        property.firePropertyChange("Login",usr,new Message(usr,"Success"));
        return result;
    }

    @Override
    public boolean registerUser(String usr, String pwd) throws Exception {
        boolean result = userList.addProfile(usr, pwd);
        property.firePropertyChange("Register",usr,new Message(usr,"Success"));
        return result;
    }

    @Override
    public void close() throws RemoteException {
        chatServer.close();
    }

    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.addListener(listener,propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.removeListener(listener,propertyNames);
    }
}
