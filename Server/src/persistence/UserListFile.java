package persistence;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserListFile implements UserFilePersistence {
    private File file;
    public UserListFile(String filename){
        file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred with Profiles file.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> load() throws IOException {
        ArrayList<User> userArrayList = new ArrayList<>();
        Reader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        if (line != null) {
            List<String> userListString = new ArrayList<>(Arrays.asList(line.split(";:;")));
            for (int x = 0; x < userListString.size(); x = x + 2){
                userArrayList.add(new User(userListString.get(x), userListString.get(x+1)));
            }
        }
        bufferedReader.close();
        reader.close();
        return userArrayList;
    }

    public void addUser(User user) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(user.getName() + ";:;" + user.getPassword() + ";:;");
        writer.close();
    }
}
