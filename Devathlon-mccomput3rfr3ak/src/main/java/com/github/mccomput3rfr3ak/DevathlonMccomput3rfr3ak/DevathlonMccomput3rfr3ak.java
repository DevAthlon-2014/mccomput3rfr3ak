package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
								p.setExp(1.2F);
								try {
								    protocolManager.getMinecraftVersion();
								} catch (Exception e) {
								    throw new RuntimeException(
								        "Cannot send packet ", e);
								}
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
	/*!
	 * Method for giving player the doubleJumpPotion
	 * @param player Player to give the Potion
	 */
	public void giveDoubleJumpPotion(Player player) {
		ItemStack potion = new ItemStack(Material.POTION, 1, (short) 8227);
		ItemMeta potionM = potion.getItemMeta();
		potionM.setDisplayName(ChatColor.GREEN + "Doppelsprung");
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Drinke diesen Trank und");
		lore.add(ChatColor.GOLD + "erhalte die Doppelsprungfähigkeit für");
		lore.add(ChatColor.RED + "2 Minuten");
		potionM.setLore(lore);
	}

}
