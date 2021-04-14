package mediator;

import model.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer extends UnicastRemoteObject implements RemoteModel, LocalListener<String, Message> {
    private Model localModel;
    private PropertyChangeHandler<String, Message> property;

    public ChatServer(Model model) throws RemoteException, MalformedURLException {
        super();
        startRegistry();
        this.localModel = model;
        localModel.addListener(this,"Message","Log","Login","Register");
        startServer();
        property = new PropertyChangeHandler<>(this,true);
    }

    private void startRegistry() throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started");
        }catch (java.rmi.server.ExportException e){
            System.err.println("Server exception: "+e);
            e.printStackTrace();
        }
    }

    private void startServer() throws MalformedURLException, RemoteException {
        Naming.rebind("chat",this);
        System.out.println("Server ready");
    }



    @Override
    public void addMessage(Message messageObject, String ip) throws RemoteException, ServerNotActiveException {
        localModel.addMessage(messageObject, getClientHost());
    }

    @Override
    public void addLog(String log) throws RemoteException {
        localModel.addLog(log);
    }

    @Override
    public boolean login(String usr, String pwd) throws Exception {
        return localModel.login(usr, pwd);
    }

    @Override
    public boolean registerUser(String usr, String pwd) throws Exception {
        return localModel.registerUser(usr, pwd);
    }

    @Override
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this,true);
    }

    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener,propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener,propertyNames);
    }

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }
}
