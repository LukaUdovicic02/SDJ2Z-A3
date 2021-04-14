package mediator;


import model.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements LocalClientModel, RemoteListener<String, Message>
{
    private RemoteModel server;
    private Model localModel;

    public ChatClient(String host, Model localModel) throws RemoteException {
        super();
        this.localModel = localModel;
        try{
            server = (RemoteModel) Naming.lookup("rmi://"+host+":1099/chat");
        }catch (Exception e){
            System.err.println("Client exception: "+e);
            e.printStackTrace();
        }
        server.addListener(this);
    }

    @Override
    public void addMessage(Message messageObject, String ip) throws RemoteException {
        server.addMessage(messageObject, ip);
    }

    @Override
    public void addLog(String log) throws RemoteException {
        server.addLog(log);
    }

    @Override
    public boolean login(String usr, String pwd) throws Exception {
        return server.login(usr,pwd);
    }

    @Override
    public boolean registerUser(String usr, String pwd) throws Exception {
        return server.registerUser(usr, pwd);
    }

    @Override
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this,true);
    }

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) throws RemoteException {
        localModel.receivedRemoteEvent(event);
    }
}
