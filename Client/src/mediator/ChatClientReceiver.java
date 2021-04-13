package mediator;

import java.io.BufferedReader;

public class ChatClientReceiver implements Runnable{
    private ChatClient client;
    private BufferedReader in;
    private boolean running;


    public ChatClientReceiver(ChatClient client, BufferedReader in){
        this.client = client;
        this.in = in;
    }

    public void close(){
        running = false;
//        in.close();
    }

    @Override
    public void run() {
        running = true;
        while (true){
            try {
                client.receive(in.readLine());
            }
            catch (Exception e){
                //
            }
        }
    }
}
