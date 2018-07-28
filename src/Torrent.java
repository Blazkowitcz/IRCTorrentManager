import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

public class Torrent 
{
	private MyBot myBot;
	
	public Torrent(MyBot myBot) {
		this.myBot = myBot;
	}
	
	public void TorrentCommand(String command) throws IOException 
	{
		Connect();
	}
	
	public void Connect() 
	{
		try {
			
			Response res = Jsoup.connect("https://ww2.yggtorrent.is/user/login")
				.data("id", "Morsang", "pass", "morsang66")
				.method(Method.POST)
				.execute();
			
			Map<String, String> cookies = res.cookies();
			System.out.println(Jsoup.connect("https://ww2.yggtorrent.is").cookies(cookies).get()
		            .select("#panel-btn").first().text());
			Response resultImageResponse = Jsoup.connect("https://ww2.yggtorrent.is/engine/download_torrent?id=285633")
		            .referrer("https://ww2.yggtorrent.is/engine/download_torrent?id=285633")
		            .cookies(cookies)
		            .ignoreContentType(true)
		            .execute();
			FileOutputStream out = (new FileOutputStream(new java.io.File("toto66.torrent")));
		    out.write(resultImageResponse.bodyAsBytes());
		    out.close();
		    System.out.println("done");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
