package server.world;

import java.util.ArrayList;

/**
 * 
 * @author Monsterray
 *
 */

public class InfractionManager {
	
	public ArrayList<String> banList = new ArrayList<String>();
	public ArrayList<String> ipBanList = new ArrayList<String>();
	public ArrayList<String> muteList = new ArrayList<String>();
	public ArrayList<String> ipMuteList = new ArrayList<String>();
	public ArrayList<String> authsList = new ArrayList<String>();
	
/*	public InfractionManager() {
		Scanner s = null;
		try {
			s = new Scanner(new File("./deps/Data/punish/mutes.txt"));
			while (s.hasNextLine()) {
				muteList.add(s.nextLine());
			}
			s = new Scanner(new File("./deps/Data/punish/auths.txt"));
			while (s.hasNextLine()) {
				authsList.add(s.nextLine());
			}
			s = new Scanner(new File("./deps/Data/punish/ipmutes.txt"));
			while (s.hasNextLine()) {
				ipMuteList.add(s.nextLine());
			}
			s = new Scanner(new File("./deps/Data/punish/bans.txt"));
			while (s.hasNextLine()) {
				banList.add(s.nextLine());
			}
			s = new Scanner(new File("./deps/Data/punish/ipbans.txt"));
			while (s.hasNextLine()) {
				ipBanList.add(s.nextLine());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}*/

	
/*	public static void jailPlayer(Client c, String command) {
		String[] args = command.split("-");
		//command-name-amount-reason
		if (args.length < 4) {
			c.sendMessage("Command must be: ::jail-playerName-jailAmount-reason");
			return;
		}
		Client o = null;
		for (Player p : PlayerHandler.players) {
			if (p == null)
				continue;
			if (p.playerName.equalsIgnoreCase(args[1])) {
				o = (Client)p;
				break;
			}
		}
		if (o == null) {
			c.sendMessage("You could not jail the player.");
			return;
		}
		o.jailedBy = c.playerName;
		o.jailAmount = Integer.parseInt(args[2]);
		o.jailedReason = args[3];		
	}
	
	public static void unjailPlayer(Client c, String name) {
		
	}**/
	
	/*public void appendPlayer(String playerName, int type) {
		playerName = playerName.toLowerCase();
		switch (type) {
		case 1: //mute
			muteList.add(playerName);
			writeToFile("./deps/Data/punish/mutes.txt", playerName);
		break;
		case 2: //ipmute
			for (Player p : PlayerHandler.players) {
				if (p != null)
					if (p.playerName.equalsIgnoreCase(playerName)) {
						ipMuteList.add(p.connectedFrom);
						writeToFile("./deps/Data/punish/ipmutes.txt", p.connectedFrom);
						break;
					}
			}
		break;
		case 5: //auths
						authsList.add(playerName);
						writeToFile("./deps/Data/punish/auths.txt", playerName);
				break;
		case 3: //ban
			banList.add(playerName);
			writeToFile("./deps/Data/punish/bans.txt", playerName);
			for (Player p : PlayerHandler.players) {
				if (p != null)
					if (p.playerName.equalsIgnoreCase(playerName)) {
						p.disconnected = true;
						break;
					}
			}
		break;
		case 4: //ipban
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					if (p.playerName.equalsIgnoreCase(playerName)) {
						ipBanList.add(p.connectedFrom);
						writeToFile("./deps/Data/punish/ipbans.txt", p.connectedFrom);
						p.disconnected = true;
						break;
					}
				}	
			}
		break;
		}
	}
	
	public void removePlayer(String name, int type) {
		name = name.toLowerCase();
		switch (type) {
		case 1: //mute
			if (muteList.contains(name)) {
				muteList.remove(name);
				removeFromFile("./deps/Data/punish/mutes.txt", name);
				
			}
		break;
		case 2: //ipmute
			for (Player p : PlayerHandler.players) {
				if (p != null)
					if (p.playerName.equalsIgnoreCase(name)) {
						if (ipMuteList.contains(p.connectedFrom)) {
							ipMuteList.remove(p.playerName.toLowerCase());
							removeFromFile("./deps/Data/punish/bans.txt", p.connectedFrom);
						}
						break;
					}
			}
		break;
		case 3: //ban
			if (banList.contains(name)) {
				banList.remove(name);
				removeFromFile("./deps/Data/punish/bans.txt", name);
			}
		break;
		}
	}
	
	public void removeFromFile(String file, String text) {
		try {
			Scanner s = new Scanner(new File(file));
			ArrayList<String> lines = new ArrayList<String>();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.equalsIgnoreCase(text))
					continue;
				lines.add(line);
			}
			s.close();
			FileWriter fw = new FileWriter(new File(file));
			for (String str : lines) {
				fw.write(str + "\r\n");
			}
			fw.close();
		} catch (IOException ioe){ioe.printStackTrace();}
	}
	
	public void writeToFile(String file, String text) {
		try {
			FileWriter fw = new FileWriter(new File(file),true);
			fw.write(text + "\r\n");
			fw.close();
		} catch (IOException e) {e.printStackTrace();}
	}*/
	
	
	
}
