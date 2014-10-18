package com.github.mccomput3rfr3ak.DevathlonMccomput3rfr3ak;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DevathlonMccomput3rfr3ak extends JavaPlugin{

	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		
		
		PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
	
	

}
