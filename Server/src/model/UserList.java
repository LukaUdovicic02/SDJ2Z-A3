package model;

import persistence.UserFilePersistence;
import persistence.UserListFile;

import java.io.*;

import java.util.List;

public class UserList
{
  private List<User> userList;
  private UserFilePersistence filePersistence;

  public UserList()
  {
    filePersistence = new UserListFile("Profiles.txt");
    try{
      userList = filePersistence.load();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public boolean addProfile(String name, String password) throws Exception
  {
    if (nameExist(name)){
      throw new Exception("Username already exists");
    }
    User user = new User(name,password);
    userList.add(user);
    filePersistence.addUser(user);
    return true;
  }

  public boolean nameExist(String name){
    for(User x: userList){
      if(x.getName().equals(name)){
        return true;
      }
    }
    return false;
  }

  public boolean userExist(String name, String password){
    for(User x: userList){
      if(x.getName().equals(name) && x.getPassword().equals(password)){
        return true;
      }
    }
    return false;
  }
}
