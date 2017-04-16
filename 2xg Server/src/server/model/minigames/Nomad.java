package server.model.minigames;

import server.Server;
import server.model.players.Client;

/**
 * @author Gabbe  - Nomad's minigame , copy of fightCaves but redone to Nomad! Took 10 minutes lolz
 */

public class Nomad {
	private final int[][] WAVES =    {{8528}};
	private int[][] coordinates = {{2501,3015}};
	public void spawnNextWave(Client c) {
		if (c != null) {
			if (c.waveId >= WAVES.length) {
				c.waveId = 1;
				return;				
			}
			if (c.waveId < 0){
			return;
			}
			int npcAmount = WAVES[c.waveId].length;
			for (int j = 0; j < npcAmount; j++) {
				int npc = WAVES[c.waveId][j];
				int X = coordinates[j][0];
				int Y = coordinates[j][1];
				int H = c.heightLevel;
				int hp = getHp(npc);
				int max = getMax(npc);
				int atk = getAtk(npc);
				int def = getDef(npc);
				Server.npcHandler.spawnNpc(c, npc, X, Y, H, 0, hp, max, atk, def, true, false);				
			}
		}
	}
	
	public int getHp(int npc) {
		switch (npc) {
			case 8528:
			return 350;		
		}
		return 100;
	}
	
	public int getMax(int npc) {
		switch (npc) {
case 8528:
return 46;	
		}
		return 5;
	}
	
	public int getAtk(int npc) {
		switch (npc) {
			case 8528:
			return 350;		
		}
		return 100;
	}
	
	public int getDef(int npc) {
		switch (npc) {
			case 8528:
			return 500;		
		}
		return 100;
	}
	

}