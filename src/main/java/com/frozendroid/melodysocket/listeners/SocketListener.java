package com.frozendroid.melodysocket.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.frozendroid.melodysocket.chat.ChatHandler;
import com.frozendroid.melodysocket.events.SocketChatEvent;

public class SocketListener implements Listener{

	@EventHandler
	public void onSocketChat(SocketChatEvent event)
	{
		System.out.println("Channel = "+event.getChannel()+"\nMessage = "+event.getMessage());
		ChatHandler chathandler = new ChatHandler();
		chathandler.sendMessage(event);
	}
	
}
