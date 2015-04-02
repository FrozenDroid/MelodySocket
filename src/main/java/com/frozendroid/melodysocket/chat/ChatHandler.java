package com.frozendroid.melodysocket.chat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.frozendroid.melodysocket.MelodySocket;
import com.frozendroid.melodysocket.events.SocketChatEvent;

public class ChatHandler {
	
	private List<Player> recipients;
	
	public void sendMessage(SocketChatEvent event)
	{	
		recipients = new ArrayList<Player>();
		String message = event.getMessage();
		
		
		for(Player player : Bukkit.getOnlinePlayers()){
			recipients.add(player);
		}
		
		String format = MelodySocket.config.getString("Chat.channels."+event.getChannel()+".format");
		
		if(format!=null){
			format.replace("{player}", Bukkit.getPlayer(UUID.fromString(event.getUUID())).getName());
			format.replace("{message}", message);
			format = ChatColor.translateAlternateColorCodes('&', message);
		}

	
		for(Player recipient : recipients){
			recipient.sendMessage(format);
		}
	
	}
	
}
