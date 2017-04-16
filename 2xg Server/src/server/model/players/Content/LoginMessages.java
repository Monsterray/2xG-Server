package server.model.players.Content;

import server.Config;
import server.Server;
import server.model.players.Client;

/**
* 2xG Login Messages ReWrote
* @author Monsterray
*/
public class LoginMessages {
	/**
		White = 15000000
		red = 15007744
		yellow = 15007000
		dark green = 50000
		purple = 80000000
	*/
	
	public static int onlineAdmin = 0;
	public static int onlineMod = 0;
	public static int[] nameSlotsA = {28004, 28006};
	public static int[] nameSlotsM = {28008, 28010, 28012, 28014, 280016};
	public static int[] statusSlotsA = {28005, 28007};
	public static int[] statusSlotsM = {28009, 28011, 28013, 28015, 280017};
	public static String[] backupNames = {""};
	public static String backupName;
	public static String[][] Titles = {
		{"User", "15000000", "15000000"},
		{"Moderator", "255", "255"},
		{"Administrator", "255", "200000000"},
		{"Co-Owner", "330000", "255"},
		{"Donator", "40000", "25"},
		{"Super Donator", "40000", "25"},
		{"Owner", "15007744", "25"},
		{"Lady", "15007950", "15007950"}
	};
	
	public static void handleLoginMessages(Client c) {
		if(c.playerName.equalsIgnoreCase("monsterray")
				|| c.playerName.equalsIgnoreCase("mio")
				|| c.playerName.equalsIgnoreCase("ashlynn")){
			specialLogin(c);
		}else{
			reportLogin(c);
		}
	}
	
	public static void tabLogin(Client c){ 
		if(c.playerRights == 2){
			if(onlineAdmin >= 2){
				//System.out.println("in dat shit of an if and you aint in");
			}else{
				//System.out.println("well damn you actualy got in");
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					//System.out.println("for what the fa boom".toUpperCase());
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						p.getPA().sendFrame126(c.playerName.toLowerCase(),nameSlotsA[onlineAdmin]);
						p.getPA().sendFrame126("@gre@Online",statusSlotsA[onlineAdmin]);
					}
				}
				c.tabNameLevel = onlineAdmin;
				if(onlineAdmin == 1)
					backupName = c.playerName;
				onlineAdmin++;
			}
		}else if(c.playerRights == 1){
			//System.out.println("Mod dat Shit");
			if(onlineMod >= 5){
				//System.out.println("well sheeeit he aint no MOD");
			}else{
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					//System.out.println("dont MOD my FOR");
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						p.getPA().sendFrame126(c.playerName.toLowerCase(),nameSlotsM[onlineMod]);
						p.getPA().sendFrame126("@gre@Online",statusSlotsM[onlineMod]);
					}
				}
				c.tabNameLevel = onlineMod;
				backupNames[onlineMod] = c.playerName;
				onlineMod++;
			}
		}else{
			
		}
		System.out.println();
	}
	
	public static void tabLogout(Client c){
		//System.out.println("WHY THE FUCK YOU LOG OUT BITCH");
		boolean changedA = false;
		boolean changedM = false;
		//System.out.println("His tab level: "+c.tabNameLevel);
		if(c.playerRights == 2){
			if(onlineAdmin >= 2){
			}else{
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						p.getPA().sendFrame126("",nameSlotsA[c.tabNameLevel]);
						p.getPA().sendFrame126("",statusSlotsA[c.tabNameLevel]);
					}
				}
				if(onlineAdmin == 1){
					changedA = true;
				}else{
					changedA = false;
				}
				onlineAdmin--;
			}
		}else if(c.playerRights == 1){
			//System.out.println("even worse a mod login out");
			if(onlineMod >= 5){
			}else{
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						p.getPA().sendFrame126("",nameSlotsM[c.tabNameLevel]);
						p.getPA().sendFrame126("",statusSlotsM[c.tabNameLevel]);
					}
				}
				onlineMod--;
				changedM = true;
			}
		}
		//System.out.println("ChangeM: "+changedM);
		if(changedA){
			//System.out.println("CHANGLING ADMIN");
			for (int i = 1; i < Config.MAX_PLAYERS; i++) {
				Client p = c.getClient(i);
				if (!c.validClient(i))
					continue;
				if (p.playerName != null) {
					p.getPA().sendFrame126(backupName,nameSlotsA[0]);
					p.getPA().sendFrame126("@gre@Online",statusSlotsA[0]);
				}
			}
		}
		if(changedM){
			//System.out.println("CHANGLING MODERATOR");
			String[] temp = new String[backupNames.length - 1];
			for(int i = 0; i <= backupNames.length - 1; i++){
				if(i == c.tabNameLevel)
					continue;
				else if(i > c.tabNameLevel)
					temp[i-1] = backupNames[i];
				else
					temp[i] = backupNames[i];
			}
			for(int i = 0; i <= onlineMod; i++){
				for (int g = 1; g < Config.MAX_PLAYERS; g++) {
					Client p = c.getClient(g);
					if (!c.validClient(g))
						continue;
					if (p.playerName != null) {
						//System.out.println("Curent name: "+ temp[i]);
						p.getPA().sendFrame126(temp[i],nameSlotsM[i]);
						p.getPA().sendFrame126("@gre@Online",statusSlotsM[i]);
					}
				}
			}
		}
		if(onlineAdmin < 0)
			onlineAdmin = 0;
		if(onlineMod < 0)
			onlineMod = 0; 
	}
	
	public static void reportLogin(Client c){
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c2 = (Client)Server.playerHandler.players[j];
				if (c.playerRights >= 1) {
					c2.sendMessage("<col="+Titles[c.playerRights][1] +"><shad="+Titles[c.playerRights][2] +">"+ Titles[c.playerRights][0] +" "+c.playerName.toLowerCase()+" Has Just Logged in!");
				}
			}
		}
	}
	
	public static void specialLogin(Client c){
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c2 = (Client)Server.playerHandler.players[j];
				if (c.playerName.equalsIgnoreCase("monsterray")) {
					c2.sendMessage("<col="+Titles[6][1] +"><shad="+Titles[6][2] +">"+ Titles[6][0] +" "+c.playerName.toLowerCase()+" Has Just Logged in!");
				}else if(c.playerName.equalsIgnoreCase("mio") || c.playerName.equalsIgnoreCase("ashlynn")) {
					c2.sendMessage("<col="+Titles[7][1] +"><shad="+Titles[7][2] +">"+ Titles[7][0] +" "+c.playerName.toLowerCase()+" Has Just Logged in!");
				}
			}
		}
	}
}