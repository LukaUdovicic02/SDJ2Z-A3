import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class ClientApplication extends Application {
    private Model model;
    @Override
    public void start(Stage primaryStage) throws IOException {
        model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        model.close();
    }
}
