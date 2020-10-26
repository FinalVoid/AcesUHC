package net.aksyo.json.model;

import java.util.List;
import java.util.Map;

public class GameModel {

    public String gameName;
    public String host;
    public boolean randomTeams;
    public Map<String, String> roles;
    public Map<String, String> teamModelList;

    public GameModel(String gameName, String host, boolean randomTeams, Map<String, String> roles, Map<String, String> teamModelList) {
        this.gameName = gameName;
        this.host = host;
        this.randomTeams = randomTeams;
        this.roles = roles;
        this.teamModelList = teamModelList;
    }
}
