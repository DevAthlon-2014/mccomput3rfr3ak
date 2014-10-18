package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*!
 * Only for stuffmembers
 */
public class Commands implements CommandExecutor{

	public String prefix = DevathlonMccomput3rfr3ak.prefix;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = null;
		
		if (sender instanceof Player) {
			
			player = (Player) sender;
			/*!
			 * Maybe it will be later a function for the plugin. Not used now!
			 */
			if (cmd.getName().equalsIgnoreCase("setSpawn")) {
				if (args.length < 1 || args.length > 1)
					player.sendMessage(prefix + ChatColor.RED + "There has to be 1 argument!"); 
				else {
					player.sendMessage(prefix + "Function not used now!");
				}
				return true;	
			}
		} else sender.sendMessage(prefix + "You have to be a player!");
		return false;
	}
	
}
