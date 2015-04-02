package com.frozendroid.melodysocket.events;

import org.bukkit.event.Event;

public class SocketEventFactory {

	private Object data;
	private Event event;
	
	public SocketEventFactory(Event event)
	{
		this.event = event;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public Event getEvent()
	{
		return event;
	}
	
}
