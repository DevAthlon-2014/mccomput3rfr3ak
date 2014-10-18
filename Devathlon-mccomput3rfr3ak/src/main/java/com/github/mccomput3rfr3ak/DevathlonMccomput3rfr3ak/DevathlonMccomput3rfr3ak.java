package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.*;

public class DevathlonMccomput3rfr3ak extends JavaPlugin{

	public static String prefix = ChatColor.GREEN + "[DevAhtlon-2014] " + ChatColor.RESET;
	int countdown;
	public boolean activeGame = false;
	private ProtocolManager protocolManager;
	
	public void onLoad() {
		protocolManager = ProtocolLibrary.getProtocolManager();
	}
	
	@Override
	public void onDisable() {
	
	}

	@Override
	public void onEnable() {
		timer();
		
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
	
	public void startGame() {
		countdown = 60;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(!activeGame) {
					if(!Events.preparing) {
						if (Events.actualPlayers < 1) {
							activeGame = false;
							Events.preparing = true;
							timer();
						}
						if (countdown == 0) {
							getServer().broadcastMessage(prefix + ChatColor.GOLD + "M\u00F6gen die Spiele beginnen!");
							for (Player p : getServer().getOnlinePlayers()) {
								p.playSound(p.getLocation(), Sound.NOTE_PLING, 100, 2);
							}
							activeGame = true;
							this.cancel();
						}
						if (countdown == 60 || countdown == 30 || countdown == 15 || countdown == 5){
							getServer().broadcastMessage(prefix + "Start in " + countdown + "!");
							for (Player p : getServer().getOnlinePlayers()) {
								
//								try {
//								    protocolManager.sendServerPacket(p,new PacketContainer(P));
//								} catch (Exception e) {
//								    throw new RuntimeException(
//								        "Cannot send packet ", e);
//								}
							}
						}
						countdown--;
					}
				}
				
			}
		}.runTaskTimerAsynchronously(this, 0L, 20L );
	}
	
	public void timer() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (Events.preparing) {
					if (Events.actualPlayers < Events.minPlayers) 
						getServer().broadcastMessage(prefix + ChatColor.BLUE + "Waiting for Players");
					else {
						Events.preparing = false;
						startGame();
					}
				} else this.cancel();
				
			}
		}.runTaskTimerAsynchronously(this, 0L, 200L);
	}

}
