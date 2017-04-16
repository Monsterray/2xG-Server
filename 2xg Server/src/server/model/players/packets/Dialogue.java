package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;


/**
 * Dialogue
 **/
public class Dialogue implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if(Config.DEBUG)
			if(c.playerRights==3)
				c.sendMessage("Dialogue id:"+ c.dialogueAction);
		if(c.nextChat > 0) {
			c.getDH().sendDialogues(c.nextChat, c.talkingNpc);
		} else {
			c.getDH().sendDialogues(0, -1);
		}
		
	}

}
