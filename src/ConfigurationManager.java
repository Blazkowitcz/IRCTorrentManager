import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager 
{
	MyBot myBot;
	
	public ConfigurationManager(MyBot myBot)
	{
		this.myBot = myBot;
	}
	public void CreateConfFile() throws IOException 
	{
		File configFile = new File("config.properties");
		Properties props = new Properties();
		props.setProperty("yggLogin", "hello123");
		props.setProperty("yggPassword", "hello123");
		props.setProperty("saveTorrentPath", "here");
		FileWriter writer = new FileWriter(configFile);
		props.store(writer, "host settings");
		writer.close();
	}
	
	public void SetYggtorrentInformations(String type, String value) 
	{
		try {
			File configFile = new File("config.properties");
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			reader.close();
			
			props.setProperty(type, value);
			FileWriter writer = new FileWriter(configFile);
			props.store(writer, "host settings");
			myBot.SendMessage("Conf updated !");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String GetYggtorrentInformation(String type) 
	{
		try 
		{
			File configFile = new File("config.properties");
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			return props.getProperty(type);
		}
		catch(IOException e) 
		{
			
		}
		return null;
	}
}
