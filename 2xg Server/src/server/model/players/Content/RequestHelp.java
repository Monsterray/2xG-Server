package server.model.players.Content;

import server.Config;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.util.Misc;

/**
 * Request Help
 * @author 
 */

public class RequestHelp {

	public static boolean requestingHelp = false;
	public static String otherPlayer = "";
	
	public static void sendOnlineStaff(Client c) {
		for (int i = 0; i < Config.STAFF.length; i++) {
			for (int g = 1; g < Config.MAX_PLAYERS; g++) {
				Client p = c.getClient(g);
				if (!c.validClient(g))
					continue;
				if (p.playerName != null) {
					p.getPA().sendFrame126(Config.STAFF[i][0], Integer.parseInt(Config.STAFF[i][1]));
					if(PlayerHandler.isPlayerOn(Config.STAFF[i][0]))
						p.getPA().sendFrame126("@gre@Online", Integer.parseInt(Config.STAFF[i][2]));
					else
						p.getPA().sendFrame126("@red@Offline", Integer.parseInt(Config.STAFF[i][2]));
				}
			}
		}
	}
	
	public static void refreshTab(Client c){
		
	}
	
	/*public static void sendOnlineStaff(Client c) { //This is a copy of the old for if i fail
		String[][] Config.STAFF = { {"Monsterray", "28000", "28001"}, {"Monsteray", "28002", "28003"} };
		for (int i = 0; i < Config.STAFF.length; i++) {
			c.getPA().sendFrame126(Config.STAFF[i][0], Integer.parseInt(Config.STAFF[i][1]));
			if(PlayerHandler.isPlayerOn(Config.STAFF[i][0]))
				c.getPA().sendFrame126("@gre@Online", Integer.parseInt(Config.STAFF[i][2]));
			else
				c.getPA().sendFrame126("@red@Offline", Integer.parseInt(Config.STAFF[i][2]));
		}
	}*/
	
	public static void setInterface(Client c) {
		if (!requestingHelp) {
			sendOnlineStaff(c);
			c.setSidebarInterface(2, 24999);
			c.getPA().sendFrame106(3);
		} else if (requestingHelp) {
			sendOnlineStaff(c);
			c.setSidebarInterface(2, 24999);
			c.getPA().sendFrame106(3);
			requestingHelp = false;
		}
	}
	
	public static void callForHelp(Client c) {
		if (System.currentTimeMillis() - c.lastRequest < 30000) {
			c.sendMessage("It has only been "+ getTimeLeft(c) +" seconds since your last request for help!");
			c.sendMessage("Please only request help from the staff every 30 seconds!");
			if (!requestingHelp) {
				c.setSidebarInterface(2, 24999);
				c.getPA().sendFrame106(3);
			}
			return;
		}
		requestingHelp = true;
		otherPlayer = c.playerName;
		c.lastRequest = System.currentTimeMillis();
		setInterface(c);
		PlayerHandler.messageAllStaff(Misc.optimizeText(getPlayer().playerName) +" needs help, their cords are: "+ playerCoords() +".", true);
	}
	
	public static long getTimeLeft(Client c) {
		return (System.currentTimeMillis() - c.lastRequest) / 1000;
	}

	public static Client getPlayer() {
		return PlayerHandler.getPlayerByName(otherPlayer);
	}
	
	public static String playerCoords() {
		return getPlayer().getX() +", "+ getPlayer().getY() +", "+ getPlayer().heightLevel;
	}

	public static void teleportToPlayer(Client c) {
		try {
			if (otherPlayer.equalsIgnoreCase(c.playerName)) {
				c.sendMessage("You can't teleport to yourself!");
				return;
			}
			if (otherPlayer != null && !otherPlayer.equalsIgnoreCase("")) {
				c.getPA().movePlayer(getPlayer().getX(), getPlayer().getY(), getPlayer().heightLevel);
				c.sendMessage("You telelported to "+ otherPlayer +".");
				otherPlayer = "";
			} else {
				c.sendMessage("There is no player to currently teleport to!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}