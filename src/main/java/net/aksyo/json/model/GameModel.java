package net.aksyo.json.model;

import java.util.List;
import java.util.Map;

public class GameModel {

    public String gameName;
    public String host;
    public Map<String, String> roles;
    public List<TeamModel> teamModelList;

    public GameModel(String gameName, String host, Map<String, String> roles, List<TeamModel> teamModelList) {
        this.gameName = gameName;
        this.host = host;
        this.roles = roles;
        this.teamModelList = teamModelList;
    }
}
