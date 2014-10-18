package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener{
	
	public static boolean preparing = true;
	public static int maxPlayers = 10;
	public static int minPlayers = 2;
	public static int actualPlayers = 0;
	public String prefix = DevathlonMccomput3rfr3ak.prefix;
	public Map<Player, Boolean> memberOfGame = new HashMap<>();
	public static Map<Player, Boolean> doubleJump = new HashMap<>();
	public static Map<Player, Integer> dJreamingtime = new HashMap<>();

	/*!
	 * This method handles the doubleJump effect
	 */
	@EventHandler
	public void onToggleFlight(PlayerToggleFlightEvent event) {
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		if ((p.getGameMode() != GameMode.CREATIVE) && (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!p.isFlying()) ) {
			p.setAllowFlight(true);
		}
	}
	
	/*!
	 * Allows player to use doublejump - potion
	 */
	@EventHandler
	public void onItemClick(PlayerInteractEvent event){
		if(event.getItem().getType() == Material.POTION && ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
			event.setCancelled(true);
			Player p = event.getPlayer();
			p.getInventory().remove(event.getItem());
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 1), false);
			doubleJump.put(p, true);
			dJreamingtime.put(p, 120);
		}
	}
	
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
				p.setExp(0);
				giveDoubleJumpPotion(p);
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
		
		potion.setItemMeta(potionM);
		player.getInventory().setItemInHand(potion);
	}
}
