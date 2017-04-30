package de.abc.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.abc.main.Main;
import de.abc.utils.FileConfig;

public class AutoBroadcastManager {

	public List<AutoMessage> messages;
	public long interval;
	
	public AutoBroadcastManager() {
		FileConfig config = new FileConfig("AutoBroadcaster", "config.yml");
		
		if(config.getKeys(false).isEmpty()) {
			config.set("Enabled", true);
			config.set("Interval", 20*60*10);
			
			config.set("Messages.Default.message", "&6&l┃ &eAutoBroadcast &7&oThis is the default message. You can change it in the &e&oconfig.yml");
			config.set("Messages.Default.hover", "&7&oThis is the hover Text");
			config.set("Messages.Default.command", "url https://www.spigotmc.org/resources/authors/f1b3r.381584/");
			config.SaveConfig();
		}
		
		messages = new ArrayList<AutoMessage>();
		
		for(String mes : config.getConfigurationSection("Messages").getKeys(false)) {
			messages.add(new AutoMessage(mes, config.getString("Messages." + mes + ".message"), config.getString("Messages." + mes + ".command"), config.getString("Messages." + mes + ".hover")));
		}
		
		if(config.getBoolean("Enabled")) {
			this.interval = config.getLong("Interval");
			if(messages.size() > 0) {
				start();
			}
		}
	}
	
	
	public void start() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(Main.INSTANCE, new Runnable() {
			
			int numb = 0;
			int size = messages.size();
			
			@Override
			public void run() {
				
				if(numb < size-1) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						messages.get(numb).getMessage().send(p);
					}
					numb++;
				}
				else {
					for(Player p : Bukkit.getOnlinePlayers()) {
						messages.get(numb).getMessage().send(p);
					}
					numb = 0;
				}
			}
		}, interval, interval);
	}
	
}
