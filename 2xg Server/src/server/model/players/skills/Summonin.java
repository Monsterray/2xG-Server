package server.model.players.skills;

import server.model.npcs.NPCHandler;
import server.model.players.Client;

/**
 *	@author 
**/

public class Summonin {

//	private Client c;	//Said it wasn't used 4/15/17
	
//	public Summonin(Client Client) {	//Said it wasn't used 4/15/17
//		this.c = Client;
//	}
	
	public void pickUp(Client c, int Type) {
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] == null)
				continue;	
		}
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] != null) {
				if (NPCHandler.npcs[i].npcType == Type) {
					if (NPCHandler.npcs[i].spawnedBy == c.playerId && NPCHandler.npcs[i].spawnedBy > 0) {
				NPCHandler.npcs[i].absX = 0;
				NPCHandler.npcs[i].absY = 0;
					NPCHandler.npcs[i] = null;
						break;
					
					}
				}
			}			
		}
	}
	
	int[][] catArray = { {3505, 7583}, {6968, 12523}, {6959, 12513}, {6961, 12515},
						 {6963, 12517}, {6965, 12519}, {6967, 12521}, {6901, 12470},
						 {6903, 12472}, {6905, 12474}, {6907, 12476}, {3506, 7584},
						 {766, 1560}, {3507, 7585},  {765, 1559}, {764, 1558},
						 {763, 1557}, {762, 1556}, {761, 1555}, {768, 1561},
						 {769, 1562}, {770, 1563}, {771, 1564}, {772, 1565}, {773, 1566},
						 {3589, 12007}, {6909, 12482} };
	
	public void pickUpClean(Client c, int id) {
		for (int i = 0; i < catArray.length; i++)
			if (catArray[i][0] == id)
				c.getItems().addItem(catArray[i][1], 1);
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] == null)
				continue;
			if (NPCHandler.npcs[i].npcType == id) {
				NPCHandler.npcs[i].absX = 0;
				NPCHandler.npcs[i].absY = 0;
			}
		}
		c.hasNpc = false;
	}
}