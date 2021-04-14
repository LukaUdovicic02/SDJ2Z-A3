package model;


import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface Model extends LocalSubject<String,Message> {
    void addMessage(Message messageObject,String ip);
    void addLog(String log);
    boolean login(String usr,String pwd) throws Exception;
    boolean registerUser(String usr,String pwd) throws Exception;
    void close() throws RemoteException;
}
