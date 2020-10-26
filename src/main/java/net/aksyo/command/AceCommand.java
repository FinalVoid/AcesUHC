package net.aksyo.command;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AceCommand {

    private static List<AceCommand> aceCommandList = new ArrayList<>();

    private String command;
    private String description;
    private boolean admin;

    /**
     *
     * @param command The args[0] after the /ace
     * @param description The command description
     * @param admin Only accessible to admins
     */
    public AceCommand(String command, String description, boolean admin) {
        this.command = command;
        this.description = description;
        this.admin = admin;
        aceCommandList.add(this);
    }

    public final static List<AceCommand> getAceCommandList() {
        return aceCommandList;
    }

    public abstract void onCommand(Player player, String[] args);

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAdmin() {
        return admin;
    }

}
