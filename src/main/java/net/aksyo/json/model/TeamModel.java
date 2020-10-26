package net.aksyo.json.model;

import java.util.Map;

public class TeamModel {

    public String name;
    public String message;

    public TeamModel(Map<String, String> map) {
        this.name = map.get("name");
        this.message = map.get("message");
    }


}