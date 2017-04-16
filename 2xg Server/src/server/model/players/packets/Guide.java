package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;

/**
* This is for when you click on the guide for patches.
* @Author Monsterray
*/
public class Guide implements PacketType {
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int objectId = c.getInStream().readUnsignedWordA();
		int iThinkJunk = c.getInStream().readUnsignedWordBigEndian();
		switch(objectId){
			default:
				c.sendMessage("This is a Beta item Please report the entire line below on the forum");
				c.sendMessage("Guide-junk: "+ junk +"-objectId: "+ objectId +"-iThinkJunk: "+ iThinkJunk);
			break;
		}
	}
}