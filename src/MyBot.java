import java.io.IOException;

import org.jibble.pircbot.*;

public class MyBot extends PircBot
{
	public TorrentManager torrent;
	public Commands commands;
	public ConfigurationManager configurationManager;
	public MyBot() 
	{
		this.setName("HyperBot");
		torrent = new TorrentManager(this);
		commands = new Commands(this);
		configurationManager = new ConfigurationManager();
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) 
	{
		String arr[] = message.split(" ");
		if(arr[0].equals("Torrent")) 
		{
			try {
				torrent.TorrentCommand(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void SendMessage(String message) 
	{
		sendMessage("#botJacky", message);
	}
}
