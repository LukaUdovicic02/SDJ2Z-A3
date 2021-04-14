package model;

import mediator.ChatClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;


public class ModelManager implements Model {
    private PropertyChangeHandler<String, Message> property;
    private LogMultiton multiton;
    private ChatClient chatClient;

    public ModelManager() throws IOException {
        this.property = new PropertyChangeHandler<>(this,true);
        this.chatClient = new ChatClient("localhost",this);
    }

    @Override
    public void addMessage(Message messageObject, String ip) throws Exception {
        chatClient.addMessage(messageObject, ip);
        addLog(messageObject.toString());
    }

    public void addLog(String log) {
        multiton = LogMultiton.getInstance(new DateTime().getSortableDate());
        String logLine = multiton.addLog(log);
        property.firePropertyChange("Log" ,logLine, null);
    }

    @Override public boolean login(String name, String password)
            throws Exception
    {
        return chatClient.login(name, password);
    }

    @Override public boolean registerUser(String name, String password)
            throws Exception
    {
        return chatClient.registerUser(name, password);
    }

    @Override
    public void receivedRemoteEvent(ObserverEvent<String, Message> event) {
        property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }

    @Override
    public void close() throws RemoteException {
        chatClient.close();
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