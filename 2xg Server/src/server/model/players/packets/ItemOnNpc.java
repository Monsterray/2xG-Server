package server.model.players.packets;

import server.model.items.UseItem;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.PacketType;


public class ItemOnNpc implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		int i = c.getInStream().readSignedWordA();
		int slot = c.getInStream().readSignedWordBigEndian();
		int npcId = NPCHandler.npcs[i].npcType;
		UseItem.ItemonNpc(c, itemId, npcId, slot);
		if(c.getClue().getClueLevel(itemId) != 0){
			c.getClue().isCorrectChoice(npcId, itemId);
			return;
		}
		
		switch(npcId){
			default:
				c.sendMessage("Idk why you are doing this to me but stop");
			break;
		}
	}
}