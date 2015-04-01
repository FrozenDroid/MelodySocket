package com.frozendroid.melodysocket.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SocketChatEvent extends Event 
{
	
	private static final HandlerList handlers = new HandlerList();
	
	private String uuid;
	private String message;
	private String timestamp;
	
	public SocketChatEvent(String uuid, String message, String timestamp)
	{
		this.uuid = uuid;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String getUUID()
	{
		return uuid;
	}

	public String getTimestamp()
	{
		return timestamp;
	}
	
}
