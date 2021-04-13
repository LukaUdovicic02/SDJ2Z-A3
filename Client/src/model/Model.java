package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    void addMessage(Message messageObject) throws Exception;
    void addLog(String log);
    boolean login(String usr,String pwd) throws Exception;
    boolean registerUser(String usr,String pwd) throws Exception;
}
