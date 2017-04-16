package server.model.minigames;

import server.Server;
import server.model.players.Client;

/**
 * @author Gabbe, new jad!!!
 */

public class HardCaves {
	private final int[][] WAVES =    {{8528}, {3663}, {6260}, {3200}, {8133}};
	private int[][] coordinates = {{2398,5086},{2387,5095},{2407,5098},{2417,5082},{2390,5076},{2410,5090}};
	//2743 = 360, 2627 = 22, 2630 = 45, 2631 = 90, 2741 = 180
	public void spawnNextWave(Client c) {
		if (c != null) {
			if (c.waveId >= WAVES.length) {
				c.waveId = 0;
				return;				
			}
			if (c.waveId < 0){
			return;
			}
			//if (c.waveId == 1) {
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
				//c.waveId = 1;
				c.waveId += 1;
			c.tzhaarToKill = npcAmount;
			c.tzhaarKilled = 0;
		//}
	}
}

}
	
	public int getHp(int npc) {
		switch (npc) {
		case 6260:
		return 200;
			case 3663: // angry gobl
			return 150;
			case 2745:
			return 250;		
			case 8528:
			return 150;
			case 8133:
			return 130;			
			case 3200:
			return 200;	
		}
		return 100;
	}
	
	public int getMax(int npc) {
		switch (npc) {
				case 6260:
		return 35;
			case 3663: // angry gobl
			return 25;
			case 2745:
			return 97;	
			case 8528:
			case 50:
						case 3200:
			return 40;	
			case 8133:
			return 40;			
		}
		return 5;
	}
	
	public int getAtk(int npc) {
		switch (npc) {
				case 6260:
				case 50:
		return 250;
					case 3200:
			return 370;	
			case 8528:
			return 300;
			case 3663: // angry gobl
			return 190;
			case 2745:
			return 650;		
						case 8133:
			return 350;	
		}
		return 100;
	}
	
	public int getDef(int npc) {
		switch (npc) {
			case 8528:
			case 50:
			return 250;
						case 3200:
			return 200;	
					case 6260:
		return 250;
			case 3663: // angry gobl
			return 200;
			case 8133:
			return 450;	
			case 2745:
			return 500;		
		}
		return 100;
	}
	

}