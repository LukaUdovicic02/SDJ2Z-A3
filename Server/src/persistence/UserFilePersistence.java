package persistence;

import model.User;



import java.io.IOException;
import java.util.ArrayList;

public interface UserFilePersistence {
    ArrayList<User> load() throws IOException;
    void addUser(User user) throws IOException;
}
