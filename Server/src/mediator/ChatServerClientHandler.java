package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatServerClientHandler implements Runnable, PropertyChangeListener {
    private Model model;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;

    public ChatServerClientHandler(Socket socket, Model model) throws IOException {
        this.socket = socket;
        this.model = model;
        this.model.addListener(this);
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        running = true;
        while (running){
            try {

                MessagePackage request = gson.fromJson(in.readLine(),MessagePackage.class);
                switch (request.getType()){
                    case "Message":
                        try {
                            model.addMessage(new Message(request.getMessage().getUsr(),request.getMessage().getMsg()),socket.getRemoteSocketAddress().toString());
                        }
                        catch (Exception e){
                            System.out.println("sending error");
                            out.println(gson.toJson(new MessagePackage(new Message(null,e.getMessage()),"Error")));
                        }
                        break;
                    case "Login":
                        try {

                            model.login(request.getMessage().getUsr(),request.getPassword());
                        }
                        catch (Exception e){
                            System.out.println("sending error");
                            out.println(gson.toJson(new MessagePackage(new Message(null,e.getMessage()),"Error")));
                        }
                        break;
                    case "Register":
                        try {
                            model.registerUser(request.getMessage().getUsr(),request.getPassword());
                        }
                        catch (Exception e){
                            System.out.println("sending error");
                            out.println(gson.toJson(new MessagePackage(new Message(null,e.getMessage()),"Error")));
                        }
                        break;
                }
            }
            catch (Exception e){
                e.printStackTrace();
                try {
                    in.close();
                    out.close();
                    socket.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                running = false;
                //close()
            }
        }
    }

    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()){
            case "Message":
                System.out.println("Server sending > "+event.getNewValue());
                out.println(gson.toJson(new MessagePackage((Message) event.getNewValue(),"Message")));
                break;
            case "Login":
                System.out.println("Server sending > " + event.getNewValue());
                out.println(gson.toJson(new MessagePackage(new Message(null, (String)event.getNewValue()),event.getPropertyName())));
                break;
            case "Register":
                System.out.println("Server sending > \"Message\"");
                out.println(gson.toJson(new MessagePackage(new Message(null, "Success"),event.getPropertyName())));
                break;
        }
    }
}
