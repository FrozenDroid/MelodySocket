package com.frozendroid.melodysocket.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.json.JSONException;
import org.json.JSONObject;

import com.frozendroid.melodysocket.MelodySocket;
import com.palmergames.bukkit.TownyChat.channels.Channel;
import com.palmergames.bukkit.TownyChat.events.AsyncChatHookEvent;

public class ChatListener implements Listener{
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onChat(AsyncChatHookEvent event)
	{
		Channel channel = event.getChannel();
		String channelname = channel.getName();
		
		if(channelname.equalsIgnoreCase("general")){
			JSONObject msgdata = new JSONObject();
			try {
				msgdata.put("channel", "general");
				msgdata.put("message", event.getMessage());
				msgdata.put("uuid", event.getPlayer().getUniqueId().toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			MelodySocket.getSocketHandler().emit("message", msgdata);
		}
	
	}
	
}
