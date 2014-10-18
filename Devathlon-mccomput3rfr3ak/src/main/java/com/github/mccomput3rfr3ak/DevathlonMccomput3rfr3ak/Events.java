package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener{
	
	public static boolean preparing = true;
	public static int maxPlayers = 10;
	public static int minPlayers = 2;
	public static int actualPlayers = 0;
	public String prefix = DevathlonMccomput3rfr3ak.prefix;
	public Map<Player, Boolean> memberOfGame = new HashMap<>();
	
	/*!
	 * Prepares the effectgame for the player
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player p = event.getPlayer();
		event.setJoinMessage(ChatColor.GRAY + "--> " + p.getName());
		
		if (preparing) {
			if (actualPlayers < maxPlayers) {
				actualPlayers++;
				memberOfGame.put(p, true);
				p.sendMessage(prefix + "Welcome, " + p.getName());
				p.getInventory().clear();
				p.setGameMode(GameMode.SURVIVAL);
				p.teleport(p.getWorld().getSpawnLocation());
				p.getWorld().strikeLightningEffect(p.getLocation());
				
			} else {
				p.kickPlayer(ChatColor.RED + "Leider ist der Server schon voll!\nVersuche es später doch noch einmal!");
			}
		}
		
	}
	
	/*!
	 * Prevents players who aren't in gamemode 1 from InventoryInteract
	 */
	@EventHandler
	public void onPlayerInventory(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if(p.getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
	}
	
	/*!
	 * Prevents players from dropping items
	 */
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Player p = event.getPlayer();
		if(p.getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
	}

	/*!
	 * Prevents players from breaking blocks
	 */
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
	}

	/*!
	 * Handles PlayerQuitEvent
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		event.setQuitMessage(ChatColor.GRAY + "<-- " + p.getName());
		
		if(memberOfGame.containsKey(p)){
			memberOfGame.put(p, false);
			actualPlayers--;
		}
	}
}
