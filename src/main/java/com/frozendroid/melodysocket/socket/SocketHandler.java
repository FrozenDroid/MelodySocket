package com.frozendroid.melodysocket.socket;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.json.JSONObject;

import com.frozendroid.melodysocket.events.SocketEventFactory;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

public class SocketHandler {

	private Socket socket;
	
	public SocketHandler(Socket socket)
	{
		this.socket = socket;
	}
	
	public void emit(String event, JSONObject value)
	{
		socket.emit(event, value);
	}
	
	public void on(String event, SocketEventFactory factory)
	{
		socket.on(event, new Emitter.Listener() {
			
			@Override
			public void call(Object... arg0) {
				
				Bukkit.getServer().getPluginManager().callEvent(factory.getEvent());
			}
		});
	}
	
}
