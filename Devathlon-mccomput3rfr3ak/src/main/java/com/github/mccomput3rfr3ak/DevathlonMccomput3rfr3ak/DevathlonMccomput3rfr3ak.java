package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;


import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

//import com.comphenix.protocol.*;

public class DevathlonMccomput3rfr3ak extends JavaPlugin{

	public static String prefix = ChatColor.GREEN + "[DevAhtlon-2014] " + ChatColor.RESET;
	int countdown;
	int timer;
	public boolean activeGame = false;
//	private ProtocolManager protocolManager;
//	
//	public void onLoad() {
//		protocolManager = ProtocolLibrary.getProtocolManager();
//	}
	
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelAllTasks();
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
								p.setExp(1.2F);
								
							}
						}
						countdown--;
					}
				}
				
			}
		}.runTaskTimerAsynchronously(this, 0L, 20L );
	}
	
	/*!
	 * Handles 'waiting for players' and doubleJump potion effect!
	 */
	public void timer() {
		
		timer = 0;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				timer++;
				if (Events.preparing && timer == 11) {
					if (Events.actualPlayers < Events.minPlayers) 
						getServer().broadcastMessage(prefix + ChatColor.BLUE + "Waiting for Players");
					else {
						Events.preparing = false;
						startGame();
					}
					timer = 0;
				}
				
				for (Player p : getServer().getOnlinePlayers()) {
					if (Events.doubleJump.get(p)) {
						if (Events.dJreamingtime.get(p) > 0) {
							Events.dJreamingtime.put(p, (Events.dJreamingtime.get(p) - 1));
						} else {
							Events.doubleJump.put(p, false);
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 0L, 20L);
	}
	
	

}
