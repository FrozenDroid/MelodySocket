package com.frozendroid.melodysocket;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONException;
import org.json.JSONObject;

import com.frozendroid.melodysocket.chat.ChatHandler;
import com.frozendroid.melodysocket.events.SocketChatEvent;
import com.frozendroid.melodysocket.listeners.ChatListener;
import com.frozendroid.melodysocket.listeners.SocketListener;
import com.frozendroid.melodysocket.socket.SocketHandler;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.palmergames.bukkit.TownyChat.Chat;

public class MelodySocket extends JavaPlugin{

	private PluginManager pluginmanager;
	private static Chat townychat;
	private Server server;
	public static Configuration config;
	private static Plugin plugin;
	private static MelodySocket instance;
	private static Socket socket;
	private static SocketHandler sockethandler;
	@SuppressWarnings("unused")
	private static ChatHandler chathandler;
	private boolean isEnabled = true;

	public Logger logger;
	
	
	public void onEnable()
	{
		saveDefaultConfig();
		plugin = this;
		instance = this;
		config = getConfig();
		server = getServer();
		pluginmanager = server.getPluginManager();
		logger = getLogger();
		
		
		initializeSocket();
		sockethandler = new SocketHandler(socket);
		
		if(isEnabled) {
			Plugin p = pluginmanager.getPlugin("TownyChat");
			if(p != null) {
				townychat=(Chat)p;
				logger.info("Hooked into TownyChat!");
			}
			
			chathandler = new ChatHandler();
			
			pluginmanager.registerEvents(new ChatListener(), this);
			pluginmanager.registerEvents(new SocketListener(), this);
			
			socket.connect();
			
			SocketChatEvent event = new SocketChatEvent();

			sockethandler.on("message", event);
			
			JSONObject json = new JSONObject();
			try {
				json.put("data", "q");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			sockethandler.emit("test", json);
			
			logger.info("Enabled! :)");
		}else{
			logger.warning("Disabled!");
			disablePlugin();
		}
	}
	
	public static Chat getTownyChat()
	{
		return townychat;
	}
	
	public static MelodySocket getInstance()
	{
		return instance;
	}
	
	public static Plugin getPlugin()
	{
		return plugin;
	}
	
	public static SocketHandler getSocketHandler()
	{
		return sockethandler;
	}
	
	public void initializeSocket()
	{
		try {
			socket = IO.socket("http://"+config.getString("Socket.host")+":"+config.getInt("Socket.port")+"/");
			if(socket==null){
				logger.warning("The socket was not able to connect! Disabling...");
				isEnabled = false;
				disablePlugin();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.warning("The socket connection details are wrong! Disabling...");
			disablePlugin();
		}
	}
	
	public void disablePlugin()
	{
		pluginmanager.disablePlugin(this);
	}
	
}
