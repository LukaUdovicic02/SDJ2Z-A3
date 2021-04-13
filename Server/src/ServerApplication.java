import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ServerApplication extends Application {
    private ChatServer chatServer;
    @Override public void start(Stage primaryStage) throws IOException
    {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        chatServer = new ChatServer(model);
        Thread t = new Thread(chatServer);
        t.start();
        view.start(primaryStage);
    }

    @Override public void stop() throws IOException {
        chatServer.close();
    }
}
