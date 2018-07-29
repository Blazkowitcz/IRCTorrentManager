import java.io.IOException;

import org.jibble.pircbot.*;

public class IRCTorrent {

	public static void main(String[] args) throws Exception{
		MyBot bot = new MyBot();
		bot.setVerbose(true);
		bot.connect("173.212.224.127");
		bot.joinChannel("#botJacky");
	}

}
