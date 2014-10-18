package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

//import com.comphenix.protocol.ProtocolLibrary;
//import com.comphenix.protocol.ProtocolManager;

//import com.comphenix.protocol.ProtocolManager;

public class DevathlonMccomput3rfr3ak extends JavaPlugin{

	public static String prefix = ChatColor.GREEN + "[DevAhtlon-2014] " + ChatColor.RESET;
//	private ProtocolManager protocolManager;
//	
//	public void onLoad() {
//		protocolManager = ProtocolLibrary.getProtocolManager();
//	}
	
	@Override
	public void onDisable() {
	
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
	
	public void timer() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (Events.preparing) {
					if (Events.actualPlayers < Events.minPlayers) getServer().broadcastMessage(prefix + ChatColor.BLUE + "Waiting for Players")
				}
				
			}
		};
	}

}
