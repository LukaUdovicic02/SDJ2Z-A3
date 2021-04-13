package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogMultiton {
    private static Map<String, LogMultiton> map = new HashMap<>();
    private ArrayList<LogLine> list;
    private String filename;


    private LogMultiton(String key) {
        list = new ArrayList<>();
        filename = key;
    }

    public static LogMultiton getInstance(String key) {
        LogMultiton instance = map.get(key);
        if (instance == null) {
            synchronized (map) {
                instance = map.get(key);
                if (instance == null) {
                    instance = new LogMultiton(key);
                    map.put(key, instance);
                }
            }
        }
        return instance;
    }

    public String addLog(String log) {
        LogLine logLines = new LogLine(log);
        list.add(logLines);
        addToFile(logLines);
        System.out.println(logLines);
        return logLines.toString();
    }

    public ArrayList<LogLine> getAll() {
        return list;
    }

    public String toString() {
        return " " + list + " " + map;
    }

    private void addToFile(LogLine log) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;

        try
        {
            out = new BufferedWriter(new FileWriter(filename+"-server-log", true));
            out.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}