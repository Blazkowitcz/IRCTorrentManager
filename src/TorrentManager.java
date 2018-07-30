
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.Torrent;

public class TorrentManager 
{
	private MyBot myBot;
	private Map <String, String> cookies;
	private List<Torrent> listTorrent= new ArrayList<Torrent>();
	
	public TorrentManager(MyBot myBot) {
		this.myBot = myBot;
	}
	
	public void TorrentCommand(String command) throws IOException 
	{
		
		String arr[] = command.split(" ");
		if(arr[1].equals("search") && arr[2] != "") 
		{
			Connect();
			RetrieveTorrents(arr[2].replace(" ", "+"));
		}
		if(arr[1].equals("set-conf") && arr[2] != "") 
		{
			if(arr[2].equals("login") && arr[3] != null) 
			{
				myBot.configurationManager.SetYggtorrentInformations("yggLogin", arr[3]);
			}
			if(arr[2].equals("password") && arr[3] != null) 
			{
				myBot.configurationManager.SetYggtorrentInformations("yggPassword", arr[3]);
			}
			if(arr[2].equals("path") && arr[3] != null) 
			{
				myBot.configurationManager.SetYggtorrentInformations("saveTorrentPath", arr[3]);
			}
			if(arr[2].equals("create")) 
			{
				myBot.configurationManager.CreateConfFile();
			}
		}
		ShowTorrent();
	}
	
	public void Connect() 
	{
		try {
			
			Response res = Jsoup.connect("https://ww2.yggtorrent.is/user/login")
				.data("id", "Morsang", "pass", "morsang66")
				.method(Method.POST)
				.execute();
			
			cookies = res.cookies();
			
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DownloadTorrentFile(String idTorrent) 
	{
		Response resultImageResponse;
		try {
			resultImageResponse = Jsoup.connect("https://ww2.yggtorrent.is/engine/download_torrent?id=" + idTorrent)
			        .referrer("https://ww2.yggtorrent.is/engine/download_torrent?id=" + idTorrent)
			        .cookies(cookies)
			        .ignoreContentType(true)
			        .execute();
			FileOutputStream out = (new FileOutputStream(new java.io.File("toto66.torrent")));
		    out.write(resultImageResponse.bodyAsBytes());
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RetrieveTorrents(String research) 
	{
		listTorrent.clear();
		Document doc;
		List<Element> tbody = new ArrayList<Element>();
		List<Element> torrents = new ArrayList<Element>();
		List<Element> infos = new ArrayList<Element>();
	    try {
			doc = (Document) Jsoup.connect("https://ww2.yggtorrent.is/engine/search?name="+ research + "&do=search&order=desc&sort=publish_date").get();
			Elements links = doc.getElementsByTag("tbody");
		    for (Element link: links) 
		    {
		    	tbody.add(link);
		    	
		    }
		    Elements tors = tbody.get(1).getElementsByTag("tr");
		    
		    for(Element torrent: tors) 
		    {
		    	torrents.add(torrent);
		    }
		    
		    for(Element tor : torrents) 
		    {
		    	Torrent torrent = new Torrent();
		    	Elements inf = tor.getElementsByTag("td");
		    	infos.clear();
		    	for (Element info : inf) { infos.add(info); }
		    	torrent.Name = infos.get(1).getElementsByTag("a").text();
		    	torrent.Age = infos.get(4).text().substring(infos.get(4).text().indexOf(" ")+1);
		    	torrent.Taille = infos.get(5).text();
		    	torrent.Id = infos.get(2).getElementsByAttribute("target").toString().split("\\s+")[1].replace("target=","").replaceAll("\"", "");
		    	torrent.Seed = infos.get(7).text();
		    	torrent.Leech = infos.get(8).text();
		    	listTorrent.add(torrent);
		    }
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	public void ShowTorrent() 
	{
		for(Torrent tor : listTorrent) 
		{
			myBot.SendMessage(tor.Name + " [" + tor.Taille + "] [" + tor.Seed + "] [" + tor.Leech + "]");
		}
	}
	
	
	
	
}
