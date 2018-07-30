import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager 
{
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
