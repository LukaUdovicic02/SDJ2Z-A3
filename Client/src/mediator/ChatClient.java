package mediator;

import model.Message;
import model.Model;
import com.google.gson.Gson;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClient implements Model
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private PropertyChangeSupport property;
  private ArrayList<MessagePackage> messagePackages;
  private Model model;
  private boolean waiting;

  public ChatClient(Model model, String host, int port) throws IOException
  {
    socket = new Socket(host, port);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    gson = new Gson();
    this.model = model;
    messagePackages = new ArrayList<>();
    property = new PropertyChangeSupport(this);

    ChatClientReceiver ccr = new ChatClientReceiver(this, in);
    Thread t = new Thread(ccr);
    t.setDaemon(true);
    t.start();
  }

  public void disconnect() throws IOException
  {
    waiting = false;
    socket.close();
    in.close();
    out.close();
  }

  public synchronized void receive(String json) throws Exception
  {
    System.out.println("Received " + json);
    MessagePackage msg = gson.fromJson(json, MessagePackage.class);
    if (waiting)
    {
      messagePackages.add(msg);
      notify();
    }
    else
    {
      property.firePropertyChange(msg.getType(), null, msg.getMessage());
    }
  }

  private synchronized MessagePackage waitingForReply()
  {
    waiting = true;
    while (messagePackages.isEmpty())
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waiting = false;
    return messagePackages.remove(0);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override public void addMessage(Message messageObject) throws Exception
  {
    System.out
            .println("Client just sent message : " + messageObject.toString());
    out.println(gson.toJson(new MessagePackage(messageObject, "Message")));
    MessagePackage msg = waitingForReply();
    if ((msg.getType().equals("Error")))
    {
      throw new Exception(msg.getMessage().getUsr());
    }
    property.firePropertyChange(msg.getType(), null, msg.getMessage());
  }

  @Override
  public void addLog(String log) {

  }

  @Override public boolean login(String name, String password) throws Exception
  {
    System.out.println("Client requests if User exist");
    out.println(gson.toJson(
            new MessagePackage(new Message(name, null), "Login", password)));
    MessagePackage messagePackage = waitingForReply();
    if ((messagePackage.isError()))
    {
      throw new Exception(messagePackage.getMessage().getMsg());
    }
    property.firePropertyChange(messagePackage.getType(),
            messagePackage.getMessage().getMsg(),
            messagePackage.getMessage().getUsr());
    return messagePackage.getMessage().getMsg().equals("Success");
  }

  @Override public boolean registerUser(String name, String password)
          throws Exception
  {
    System.out.println("Client is registering");
    out.println(gson.toJson(
            new MessagePackage(new Message(name, null), "Register", password)));
    MessagePackage messagePackage = waitingForReply();
    if ((messagePackage.isError()))
    {
      throw new Exception(messagePackage.getMessage().getMsg());
    }
    property.firePropertyChange(messagePackage.getType(),
            messagePackage.getMessage().getMsg(),
            messagePackage.getMessage().getUsr());
    return messagePackage.getMessage().getMsg().equals("Success");
  }
}
