package de.abc.manager;

import org.bukkit.ChatColor;

import mkremins.fanciful.FancyMessage;

public class AutoMessage {

	public String name;
	
	public String message;
	
	/**
	 * First Word:
	 * 			cmd - Send Command
	 * 			url - Open Url
	 */
	public String cmd;
	
	public String hover;
	
	public AutoMessage(String name, String message, String cmd, String hover) {
		this.name = name;
		this.message = ChatColor.translateAlternateColorCodes('&', message);
		this.cmd = cmd;
		this.hover = ChatColor.translateAlternateColorCodes('&', hover);
	}
	
	public FancyMessage getMessage() {
		String cmd = this.cmd;
		
		if(cmd.startsWith("cmd ")) {
			cmd = cmd.replaceFirst("cmd ", "");
			return new FancyMessage(message).command(ChatColor.translateAlternateColorCodes('&', cmd)).tooltip(hover);
		}
		else if(cmd.startsWith("url ")) {
			cmd = cmd.replaceFirst("url ", "");
			return new FancyMessage(message).link(cmd).tooltip(hover);
		}

		return new FancyMessage(message).tooltip(hover);
	}
	
}
