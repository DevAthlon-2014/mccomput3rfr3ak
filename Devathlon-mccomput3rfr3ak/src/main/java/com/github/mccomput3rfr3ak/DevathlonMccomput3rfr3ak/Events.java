package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener{

	public String prefix = DevathlonMccomput3rfr3ak.prefix;
	
	/*!
	 * Prepares the effectgame for the player
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player p = event.getPlayer();
		event.setJoinMessage(null);
		
		p.sendMessage(prefix + "Welcome, " + p.getName());
		p.getWorld().strikeLightningEffect(p.getLocation());
		
		ItemStack startGame = new ItemStack(Material.BLAZE_ROD);
		ItemMeta startGameM = startGame.getItemMeta();
		startGameM.setDisplayName(ChatColor.GOLD + "Starte das Effekt-Spiel");
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Klicke mit diesem Item");
		lore.add(ChatColor.AQUA + "und lasse das Spiel beginnen");
		
		startGameM.setLore(lore);
		startGame.setItemMeta(startGameM);
		p.getInventory().clear();
		p.getInventory().setItem(4, startGame);
		
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

	@EventHandler
	public void onItemKlick(PlayerInteractEvent event) {
		if(event.getItem().getType() == Material.BLAZE_ROD && (event.getAction() == Action.RIGHT_CLICK_AIR)) {
			
		}
	}
}
