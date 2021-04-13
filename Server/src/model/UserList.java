package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserList
{
  private ArrayList<User> userLists;
  private String filename = "Profiles.txt";
  private ArrayList<String> fileList;
  private File file;
  private FileReader reader;
  private BufferedReader bufferedReader;
  private FileWriter writer;

  public UserList() throws IOException
  {
    file = new File(filename);
    try {
      file.createNewFile();

    } catch (IOException e) {
      System.out.println("An error occurred with Profiles file.");
      e.printStackTrace();
    }
    reader = new FileReader(file);
    bufferedReader = new BufferedReader(reader);
    writer = new FileWriter(file, true);
    this.userLists = new ArrayList<>();


    readUsersFromFile();

  }


  public void readUsersFromFile() throws IOException
  {
    if (new BufferedReader(new FileReader(filename)).readLine() == null) {
      addDummyData();
    }
    fileList = new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(";:;")));
    for (int x = 0; x < fileList.size(); x = x + 2){
      userLists.add(new User(fileList.get(x), fileList.get(x+1)));
    }
  }

  public void addDummyData() throws IOException
  {
    writer.write( ";:;" +  ";:;");
    writer.close();
  }

  public boolean login(String usr, String pwd) throws Exception {
    if (userExist(usr,pwd)){
      return true;
    }
    else {
      throw new Exception("Wrong username or password");
    }
  }

  public boolean addProfile(String name, String password) throws Exception
  {
    if (nameExist(name)){
      throw new Exception("Username already exists");
    }
    System.out.println(name);
    writer = new FileWriter(file, true);
    userLists.add(new User(name, password));
    writer.write(name + ";:;" + password + ";:;");
    writer.close();
    return true;
  }
  public boolean nameExist(String name){
    for(User x:userLists){
      if(x.getName().equals(name)){
        return true;
      }
    }
    return false;
  }
  public boolean userExist(String name, String password){
    for(User x:userLists){
      if(x.getName().equals(name) && x.getPassword().equals(password)){
        return true;
      }
    }
    return false;
  }
}
