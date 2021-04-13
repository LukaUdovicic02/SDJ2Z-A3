package model;

import mediator.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;


public class ModelManager implements Model,PropertyChangeListener {
    public static final String HOST = "localhost";
    public static final int PORT = 2910;
    private PropertyChangeSupport property;
    private ChatClient client;
    private LogMultiton multiton;

    public ModelManager() throws IOException {
        this.property = new PropertyChangeSupport(this);
        this.client = new ChatClient(this, HOST, PORT);
        client.addListener(this);
    }

    @Override
    public void addMessage(Message messageObject) throws Exception {
        client.addMessage(messageObject);
        addLog(messageObject.toString());
    }

    public void addLog(String log) {
        multiton = LogMultiton.getInstance(new DateTime().getSortableDate());
        String logLine = multiton.addLog(log);
        property.firePropertyChange("Log" ,null, logLine);
    }

    @Override public boolean login(String name, String password)
            throws Exception
    {
        return client.login(name, password);
    }

    @Override public boolean registerUser(String name, String password)
            throws Exception
    {
        return client.registerUser(name, password);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);

    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        property.firePropertyChange(evt.getPropertyName(), null, evt.getNewValue());
    }
}