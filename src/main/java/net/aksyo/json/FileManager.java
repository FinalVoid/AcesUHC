package net.aksyo.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.stream.Collectors;

public class FileManager {

    private File directory, jsonConfig;
    private Gson gson;

    public FileManager(String path) throws IOException {

        directory = new File(path + "/game");

        if (!directory.exists()) directory.mkdirs();

        jsonConfig = new File(directory.getPath() + "/config.json");

        if (jsonConfig.exists()) {
            jsonConfig.createNewFile();

        }

        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        InputStream is = new FileInputStream(jsonConfig);
        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));

        //crateModel = gson.fromJson(result, CrateModel.class); TODO insert GameModel
        is.close();

    }

}
