package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Trading
 */
public class Trade implements PacketType {
	public boolean inTrade;
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		
		if (c.inTrade) {
		c.sendMessage("You cannot walk while in a trade.");
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
		if(c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}
                if (c.InDung()) {
                         c.sendMessage("You cannot trade inside Dungoneering!");
                         return;
		}
		if (c.inWild()) {
                         c.sendMessage("You can't trade in the wilderness!");
                         return;
		}
		if(c.playerRights == 2 || c.playerName.equalsIgnoreCase("Tyler") && !c.playerName.equalsIgnoreCase("tommy17890")) {
			c.sendMessage("Sorry, Administrators Cant Trade!");
			return;
		}
		
		if (tradeId != c.playerId)
			c.getTradeAndDuel().requestTrade(tradeId);
	}
		
}
