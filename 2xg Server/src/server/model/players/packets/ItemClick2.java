package server.model.players.packets;

import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.RuneCraft;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author 
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		if (!c.getItems().playerHasItem(itemId,1))
			return;
		switch (itemId) {
			case 4155:
	    	   c.sendMessage("Your current assignment is " + Server.npcHandler.getNpcListName(c.slayerTask) + "... only " + c.taskAmount + " left to go!");
	       	break;
	       	
	       	case 1438:// Air Talisman
				RuneCraft.locate(c, 2985, 3292);
			break;
			
			case 1440:// Earth Talisman
				RuneCraft.locate(c, 3306, 3474);
			break;
				
			case 1442:// Fire Talisman
				RuneCraft.locate(c, 3313, 3255);
			break;
				
			case 1444:// Water Talisman
				RuneCraft.locate(c, 3185, 3165);
			break;
				
			case 1446:// Body Talisman
				RuneCraft.locate(c, 3053, 3445);
			break;
				
			case 1448:// Mind Talisman
				RuneCraft.locate(c, 2982, 3514);
			break;
				
			case 11283:
			case 11284:
			case 11285:
				c.sendMessage("Your shield has "+c.dfsCount+" charges");
			break;

			case 15707:
			case 18817:
			case 18823:
			case 18821:
				c.getPA().showInterface(29799);
			break;

			case 11694:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			
			case 11696:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			
			case 11698:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			
			case 11700:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Item ID:"+ itemId +"     ItemClick2");
			break;
		}
	}
}