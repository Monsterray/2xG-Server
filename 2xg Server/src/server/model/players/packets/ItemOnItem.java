package server.model.players.packets;

/**
 * @author Ryan / Lmctruck30
 */

import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.Firemaking;
import server.model.players.skills.Summoning;
import server.util.Misc;

public class ItemOnItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int usedWithSlot = c.getInStream().readUnsignedWord();
		int itemUsedSlot = c.getInStream().readUnsignedWordA();
		int useWith = c.playerItems[usedWithSlot] - 1;
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		int itemUsed = c.playerItems[itemUsedSlot] - 1;
		
		  Summoning.makeSummoningPouch(c, useWith, itemUsed);
		UseItem.ItemonItem(c, itemUsed, useWith);
		if (Firemaking.playerLogs(c, itemUsed, useWith)) {
			Firemaking.grabData(c,itemUsed, useWith);
			}
		switch (itemUsed) {
	/*case 1511:
				case 1521:
				case 1519:
				case 1517:
				case 1515:
				case 1513:
				case 590:
			//	c.sendMessage("Firemaking is disabled atm due to noclipping!");
			//	Firemaking.grabData(c, itemUsed, useWith);
			c.getFiremaking().checkLogType(itemUsed, useWith);   
				break;*/
	}

	}
	
	

}
