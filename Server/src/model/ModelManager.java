package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model {
    MessageList messageList;
    private PropertyChangeSupport property;
    private UserList userList;
    private LogMultiton multiton;

    public ModelManager() throws IOException {
        this.property = new PropertyChangeSupport(this);
        this.messageList = new MessageList();
        this.userList = new UserList();
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
        property.firePropertyChange("Log" ,null, longLine);
    }

    @Override
    public boolean login(String usr, String pwd) throws Exception {
        boolean result = userList.login(usr, pwd);
        property.firePropertyChange("Login",usr,"Success");
        return result;
    }

    @Override
    public boolean registerUser(String usr, String pwd) throws Exception {
        boolean result = userList.addProfile(usr, pwd);
        property.firePropertyChange("Register",usr,"Success");
        return result;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);

    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
