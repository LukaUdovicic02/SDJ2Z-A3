package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable{
    private final static int PORT = 2910;
    private boolean running;
    private ServerSocket welcomeSocket;
    private Model model;

    public ChatServer(Model model){
        this.model = model;
    }

    public void close() throws IOException {
        running = false;
        welcomeSocket.close();
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting server");
            welcomeSocket = new ServerSocket(PORT);
            running = true;

            while (running){
                System.out.println("Waiting for client");
                Socket socket = welcomeSocket.accept();
                ChatServerClientHandler chatServerClientHandler = new ChatServerClientHandler(socket,model);
                Thread clientHandler = new Thread(chatServerClientHandler);
                clientHandler.setDaemon(true);
                clientHandler.start();
            }
        }
        catch (Exception e){
            //
        }
    }
}
