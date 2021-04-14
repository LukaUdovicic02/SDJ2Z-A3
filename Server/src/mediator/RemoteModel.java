package mediator;

import model.Message;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,Message> {
    void addMessage(Message messageObject, String ip) throws RemoteException;
    void addLog(String log) throws RemoteException;
    boolean login(String usr,String pwd) throws Exception;
    boolean registerUser(String usr,String pwd) throws Exception;
    void close() throws RemoteException;
}
