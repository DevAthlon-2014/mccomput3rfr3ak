package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{

	public String prefix = DevathlonMccomput3rfr3ak.prefix;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(prefix + "Welcome, " + event.getPlayer().getName());
		event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());
		
	}
}
