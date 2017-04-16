package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Follow Player
 **/
public class FollowPlayer implements PacketType {
	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int followPlayer = c.getInStream().readUnsignedWordBigEndian();
		if(Server.playerHandler.players[followPlayer] == null) {
			return;
		}
				if(!c.InDung() && !c.inDungBossRoom()) {
					for (int i : Config.DUNG_ARM) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
	if (Server.playerHandler.players[j] != null) {
	Client c2 = (Client)Server.playerHandler.players[j];
	if(c.getItems().playerHasItem(i, 1)) {
	c2.sendMessage("<shad=132833>"+c.playerName+" Has Dung items out of Dung! Banned!");
	c.sendMessage("Drop your fucking items first.");
								Connection.addNameToBanList(c.playerName);
					Connection.addNameToFile(c.playerName);
		c.logout();
				return;
			}		
		}
		}
		}
		}
		c.playerIndex = 0;
		c.npcIndex = 0;
		c.mageFollow = false;
		c.usingBow = false;
		c.usingRangeWeapon = false;
		c.followDistance = 1;
		c.followId = followPlayer;
	}	
}
