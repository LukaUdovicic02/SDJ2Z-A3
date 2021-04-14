package mediator;

import model.Message;

import java.rmi.RemoteException;

public interface LocalClientModel {
    void addMessage(Message messageObject, String ip) throws RemoteException;
    void addLog(String log) throws RemoteException;
    boolean login(String usr,String pwd) throws Exception;
    boolean registerUser(String usr,String pwd) throws Exception;
    void close() throws RemoteException;
}
