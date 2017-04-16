package server.model.players.packets;

import server.Connection;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Content.ReportHandler;
import server.util.Misc;

/**
 * Chat : Credits to Coder Monsterray
 **/
public class Chat implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.setChatTextEffects(c.getInStream().readUnsignedByteS());
		c.setChatTextColor(c.getInStream().readUnsignedByteS());
        c.setChatTextSize((byte)(c.packetSize - 2));
        ReportHandler.addText(c.playerName, c.getChatText(), packetSize - 2);
        c.inStream.readBytes_reverseA(c.getChatText(), c.getChatTextSize(), 0);
		String[] stuffz = { ",net", ",runescape", ",org", ",com", ",be", ",nl", ",info", "dspk",".info" , ".org", ".tk", ".net", ".com", ".co.uk", ".be", ".nl", ".dk", ".co.cz", ".co", ".us", ".biz", ".eu", ".de", ".cc", ".i n f o", ".o r g", ".t k", ".n e t", ".c o m", ".c o . u k", ".b e", ".n l", ".d k", ".c o . c z", ".c o", ".u s", ".b i z", ".e u", ".d e", ".c c", "kandarin", "o r g", "www", "w w w"};
		String term = Misc.textUnpack(c.getChatText(), c.packetSize - 2).toLowerCase();
		if(c.said >= 15000 && c.playerRights <= 3){
			c.sendMessage("You have been Automuted! -Monsterray-");
			Connection.addNameToMuteList(c.playerName);
		}
		for(int i = 0; i < stuffz.length; i++) {
			if(term.contains(stuffz[i]) && c.playerRights <= 2) {
				c.said++;
				c.sendMessage("Advertising has been disabled, SORRY DICKFACE!");
				c.sendMessage("Dont spam this or you will be automuted!");
				return;
			}
		}
		if (!Connection.isMuted(c)){
			c.setChatTextUpdateRequired(true);
		} else if (Connection.isMuted(c)){
			c.sendMessage("You are muted on 2xG Sorry!");
			return;
		}
	}
}