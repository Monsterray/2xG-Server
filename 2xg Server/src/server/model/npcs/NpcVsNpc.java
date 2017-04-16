package server.model.npcs;

import server.Server;
import server.util.Misc;


public class NpcVsNpc {
	/**
	 * @author Dragonkk 100%
	 *
	 */

	//Attack starts here
	public static void Attack(NPC p, NPC n) {
		try {

			//checks if we can att enemy
			if(p == null || n == null) {
				System.out.println("p/n = null");
				return;
			}
	
	
			if(p.isDead == true) {
				//p.Attacking = false;
				return;
			}
			if (n.isDead == true) {
				return;
			}
	
			MeleeAttack(p, n);
			
		} catch(Exception e) {
			
		}
	}
	
	//Attack Finish here
	public static void MeleeAttack(NPC p, NPC n) {
		try {
			if(n.attackTimer == 0) {
				n.animNumber = NPCHandler.getAttackEmote(n.npcId); // need attack
				n.hitDiff = Misc.random(Server.npcHandler.getMaxHit(n.npcId));	
				n.attackTimer = Server.npcHandler.getNpcDelay(n.npcId);			
			}
		} catch(Exception e) {
			
		}
	}
	
	public boolean IsRanging(int npcId) {
		switch (npcId){
			case 1158:
			case 8160:
			case 8133:
			case 8127:
			case 50:	
				return true;
		}
		return false;
	}
	
	public int getNpcMeleeAttack(int npcId) {
		switch (npcId){
			case 8324:
			case 8325:
			case 8326:
			case 8327:
				return 83+99;
				
			default:
				return 50;
		}
	}
	
	public int getNpcRangeAttack(int npcId) {
		switch (npcId){
			case 8324:
			case 8325:
			case 8326:
			case 8327:
				return 83;
			default:
				return 60;
		}
	}
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
//finishclass









