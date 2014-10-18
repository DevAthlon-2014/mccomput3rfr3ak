package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{

	public String prefix = DevathlonMccomput3rfr3ak.prefix;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player p = event.getPlayer();
		event.setJoinMessage(null);
		
		p.sendMessage(prefix + "Welcome, " + p.getName());
		p.getWorld().strikeLightningEffect(p.getLocation());
		
		
		
	}
}
