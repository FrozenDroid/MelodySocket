package com.frozendroid.melodysocket.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.TownyChat.channels.Channel;
import com.palmergames.bukkit.TownyChat.events.AsyncChatHookEvent;

public class ChatListener implements Listener{

	private Plugin plugin;
	
	public ChatListener(Plugin plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onChat(AsyncChatHookEvent event)
	{
		Channel channel = event.getChannel();
		String channelname = channel.getName();
		
		plugin.getLogger().info("Somebody said something");
		
		if(channelname.equalsIgnoreCase("general")){
			plugin.getLogger().info("Somebody said something in Global");
		}
	
	}
	
}
