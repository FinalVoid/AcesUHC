package net.aksyo.json;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.aksyo.json.model.GameModel;

import java.io.*;
import java.util.stream.Collectors;

public class FileManager {

    private File directory, jsonConfig;
    private Gson gson;
    private GameModel gameModel;

    public FileManager(String path) throws IOException {

        directory = new File(path + "/game");

        if (!directory.exists()) directory.mkdirs();

        jsonConfig = new File(directory.getPath() + "/config.json");

        if (!jsonConfig.exists()) {
            jsonConfig.createNewFile();
            FileOutputStream fos = new FileOutputStream(jsonConfig);
            Resources.copy(getClass().getClassLoader().getResource("default.json"), fos);
            fos.close();
        }

        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        InputStream is = new FileInputStream(jsonConfig);
        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));

        gameModel = gson.fromJson(result, GameModel.class);
        is.close();

    }

    public GameModel getGameModel() {
        return gameModel;
    }

}
