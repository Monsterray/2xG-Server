package server.model.players.packets;

import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Challenge Player
 **/
public class ChallengePlayer implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {		
		switch(packetType) {
			case 128:
			int answerPlayer = c.getInStream().readUnsignedWord();
			if(Server.playerHandler.players[answerPlayer] == null) {
				return;
			}			
			Client o = (Client) Server.playerHandler.players[answerPlayer];
			if (c.duelStatus != 0 || o.duelStatus != 0) {
 				c.sendMessage("You are currently unable to challenge this player.");
 				return;
 			}
			if (o.duelStatus > 0) {
				c.sendMessage("That player is currently dueling someone else.");
				return;
			}
			if(c.arenas() || c.duelStatus == 5) {
				c.sendMessage("You can't challenge inside the arena!");
				return;
			}
			c.sendMessage("");					

			c.getTradeAndDuel().requestDuel(answerPlayer);
			break;
		}		
	}	
}