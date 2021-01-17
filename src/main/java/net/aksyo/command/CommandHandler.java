package net.aksyo.command;

import net.aksyo.command.game.JokerPacteCommand;
import net.aksyo.command.game.RoleCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    public CommandHandler() {
        new DebugCommand();
        new RoleCommand();
        new JokerPacteCommand();
        new FreezeGameCommand();
        new BugCommand();
        new SetGameModeCommand();
        new SetFoodCommand();
        new StartGameCommand();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length < 1) {
                //player.sendMessage(Constant.getCommandHelpMessage());
                return true;
            }

            for(AceCommand aceCommand : AceCommand.getAceCommandList()) {
                if(args[0].equalsIgnoreCase(aceCommand.getCommand())) {
                    if(aceCommand.isAdmin() && player.isOp()) {
                        aceCommand.onCommand(player, args);
                        return true;
                    } else if(!aceCommand.isAdmin()) {
                        aceCommand.onCommand(player, args);
                        return true;
                    }
                }
            }

        } else {
            sender.sendMessage("§cYou need to be a player to execute this command");
            return true;
        }

        return false;
    }

}
