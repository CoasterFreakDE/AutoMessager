package de.abc.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.abc.manager.AutoBroadcastManager;



public class Main extends JavaPlugin {

	public static Main INSTANCE;
	public static String PREFIX = "§6§l┃ §eAutoBroadcast §7§o";
	
	@Override
	public void onEnable() {
		INSTANCE = this;

		new AutoBroadcastManager();
		
		Bukkit.getConsoleSender().sendMessage(PREFIX + "§a§oPlugin loaded.");
	}
	
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(PREFIX + "§c§oPlugin unloaded.");
	}
	
	
}
