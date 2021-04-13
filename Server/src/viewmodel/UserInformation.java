package viewmodel;

public class UserInformation
{
  private String user;

  public UserInformation(){
   user = "";
  }

  public void setUser(String name){
    user = name;
  }

  public String getUser(){
    return user;
  }
}
