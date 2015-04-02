package com.frozendroid.melodysocket.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.JSONException;
import org.json.JSONObject;

import com.frozendroid.melodysocket.interfaces.SocketEvent;

public class SocketChatEvent extends Event implements SocketEvent
{
	
	private static final HandlerList handlers = new HandlerList();
	
	private String channel;
	private String uuid;
	private String message;
	private Long timestamp;
	
	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	public String getChannel()
	{
		return channel;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String getUUID()
	{
		return uuid;
	}

	public Long getTimestamp()
	{
		return timestamp;
	}

	@Override
	public void setData(JSONObject data)
	{
		try {
			message = data.getString("message");
			channel = data.getString("channel");
			uuid = data.getString("uuid");
			timestamp = data.getLong("timestamp");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	
}
