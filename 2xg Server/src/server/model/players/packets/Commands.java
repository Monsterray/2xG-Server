package server.model.players.packets;

import java.io.BufferedWriter;
//import org.runetoplist.*;
import java.io.FileWriter;
import java.io.IOException;

import server.Config;
import server.Connection;
import server.Server;
import server.model.items.Item;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.PlayerSave;
import server.model.players.SkillMenu;
import server.model.players.Content.SkillGuides;
import server.util.Misc;

/**
 * Commands.java All commands added by Monsterray, some/most made.
 **/
public class Commands implements PacketType {
	SkillGuides sg = new SkillGuides();
	SkillMenu test = new SkillMenu();
	public String[] playerRightName = {"Player", "Moderator", "Administrator", "Owner", "Donator"};
    
    @Override
    public void processPacket(Client c, int packetType, int packetSize) {
    String playerCommand = c.getInStream().readString();
		if (!playerCommand.startsWith("/")){
			c.getPA().writeCommandLog(playerCommand);
		}
		if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
			try {
				BufferedWriter report = new BufferedWriter(new FileWriter("./deps/Data/Reports.txt", true));
				String Report = playerCommand.substring(7);
				try {	
					report.newLine();
					report.write(c.playerName + ": " + Report);
					c.sendMessage("You have successfully submitted your report.");
				} finally {
					report.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
///~~~~~~~~~~~~~~~~~~~~~Player Rights Based Commands~~~~~~~~~~~~~~~~~~~~~~~
		if (playerCommand.equals("staffshops") && (c.playerRights >= 3)) {
			c.getPA().startTeleport(2763, 2953, 0, "modern");
			c.sendMessage("<shad=83838383><img=2><img=2>staffshops!! earn staff points by helping players!<img=2><img=2>");
		}
		if (playerCommand.equals("helpzone")) {
			c.getPA().startTeleport(1971, 5001, 0, "modern");
			c.sendMessage("Welcome to The Helpzone , For Support request in Stafflist Tab.");
		}
		if (playerCommand.equals("dicezone")) {
			c.getPA().startTeleport(1971, 5003, 0, "modern");
			c.sendMessage("<shad=83838383>DICING ZONE!! GET SCAMMED? TELL A OWNER AND WE WILL DEAL WITH IT!");
		}
		if (playerCommand.equals("staffzone") && (c.playerRights >= 1)) {
			c.getPA().startTeleport(2767, 2979, 0, "modern");
			c.sendMessage("<img=2>Welcome to the Staffzone!<img=2>");
		}
		if (playerCommand.equals("modzone") && (c.playerRights >= 1)) {
			c.getPA().startTeleport(2393, 9894, 0, "modern");
			c.sendMessage("Welcome to the Moderator HQ!");
		}
		if (playerCommand.equals("adminzone") && (c.playerRights >= 2)) {
			c.getPA().startTeleport(3156, 4820, 0, "modern");
			c.sendMessage("Welcome to the Administration HQ!");
		}
		if (playerCommand.equals("ownerzone") && (c.playerRights >= 3)) {
			c.getPA().startTeleport(2142, 4831, 0, "modern");
			c.sendMessage("<img=2><shad=838383>IF YOUR NOT OWNER GTFO OR BANNED<img=2>");
		}
		if (playerCommand.equals("ownerchill") && (c.playerRights == 3)) {
			c.getPA().startTeleport(2866, 9954, 0, "modern");
			c.sendMessage("<img=11>Welcome to the Admin Lounge, chillax and lay back!<img=11>");
		}
		if (playerCommand.equals("corp")) {
			c.getPA().startTeleport(3304, 9375, 0, "modern");
			c.sendMessage("Use a sigil with a blessed spirit shield!");
		}
		if (playerCommand.equals("zombies")) {
			c.getPA().startTeleport(3241, 3610, 0, "modern");
			c.sendMessage("Welcome to Zombiez!! IF YOU SURVIVE YOU WIN A PRIZE!");
		}
		if (playerCommand.equals("home")) {
			c.getPA().startTeleport(3086, 3494, 0, "modern");
			c.sendMessage("Home Sweet Home...");
		}
		if (playerCommand.equals("slayertower")) {
			c.getPA().startTeleport(3428, 3538, 0, "modern");
			c.sendMessage("Welcome to the Slayer Tower!");
		}
		if (playerCommand.equals("summon")) {
			c.getPA().startTeleport(3086, 3250, 0, "modern");
			c.sendMessage("TO summon use item on pouch will all Eqipment. (eg.raw chicken -pouch");
		}
		if (playerCommand.equals("funpk")) {
			c.getPA().startTeleport(2605, 3153, 0, "modern");
			c.sendMessage("Welcome to the FunPK arena!");
		}
		if (playerCommand.equals("mining")) {
			c.getPA().startTeleport(3040, 9802, 0, "modern");
			c.sendMessage("Welcome to the Mining zone");
		}
		if (playerCommand.equals("monkeyhome")) {
			c.getPA().startTeleport(2758, 2782, 0, "modern");
			c.sendMessage("Welcome to the Jungle..We got Fun and Games lol...!");
		}
		if (playerCommand.equals("mytele")) {
			c.getPA().startTeleport(3233, 2895, 0, "modern");
			c.sendMessage("<img=2><img=2>My Home!!!<img=2><img=2>");
		}
		if (playerCommand.equals("highpk")) {
			c.getPA().startTeleport(3286, 3881, 0, "modern");
			c.sendMessage("welcome to level 47 wildy, this is Multi area...Good Luck!");
		}
		if (playerCommand.equals("chill")) {
			c.getPA().startTeleport(2149, 5096, 0, "modern");
			c.sendMessage("welcome to Chill zone, simply relax and talk to people!");
		}
		if (playerCommand.equals("trippy")) {
			c.getPA().startTeleport(3683, 9889, 0, "modern");
			c.sendMessage("woah look at the ground!");
		}
		if (playerCommand.equals("frosts")) {
			c.getPA().startTeleport(3056, 9563, 0, "modern");
			c.sendMessage("Be Careful They Have lots HP!");
		}
		if (playerCommand.equals("train2")) {
			c.getPA().startTeleport(2911, 3614, 0, "modern");
			c.sendMessage("Welcome to the 2nd training are! summoning NPC's will help you in battle");
		}
		if (playerCommand.equals("random")) {
			c.getPA().startTeleport(2463, 4782, 0, "modern");
			c.sendMessage("This is my gas pocket bitches! Get the fuck out!");
		}
		if (playerCommand.equals("train")) {
			c.getPA().startTeleport(2683, 3725, 0, "modern");
			c.sendMessage("Welcome to the classic rock crab training area!");
		}
		if (playerCommand.startsWith("kdr")) {
			double KDR = ((double)c.KC)/((double)c.DC);
			c.forcedChat("My Kill/Death ratio is "+c.KC+"/"+c.DC+"; "+KDR+".");
		}
		if (playerCommand.startsWith("rank") && c.sit == false) {
			if(c.inWild()) {
				c.sendMessage("Er, it's not to smart to do this in the Wilderness.");
				return;
			}
			c.sit = false;
			if(c.playerRights == 0) {
				c.startAnimation(4117);
				c.forcedText = "I'm A N00B!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("N00B, its ok lol");
			} else if(c.playerRights == 1) {
				c.startAnimation(4117);
				c.forcedText = "I Keep Peace Around Here!!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Moderator");
			} else if(c.playerRights == 2) {
				c.startAnimation(4117);
				c.forcedText = "Don't Mess With Me!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Administrator");
			} else if(c.playerRights == 3) {
				c.gfx0(1555);
				c.startAnimation(2108);
				c.forcedText = "I OWN THIS JOINT!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("OWNER");
			} else if(c.playerRights == 4) {
				c.startAnimation(4117);
				c.forcedText = "I'm A Money Man!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Donator");
			}
		}
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
				c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;       
		}
    if (Config.DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);
    if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3)
        moderatorCommands(c, playerCommand);
    if (c.playerRights == 2 || c.playerRights == 3)
        administratorCommands(c, playerCommand);
    if (c.playerRights == 3) 
        ownerCommands(c, playerCommand);
    if (c.playerRights == 4) 
        DonatorCommands(c, playerCommand);
			
    }

///~~~~~~~~~~~~~~~~~~~~~Player Commands~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void playerCommands(Client c, String playerCommand){
	///~~~~~~~~~~~~~~~~~~~~~Monsterrays Commands~~~~~~~~~~~~~~~~~~~~~~~~~~
		if(playerCommand.startsWith("forum")) {
			c.getPA().sendFrame126(Config.FORUMS, 12000);
		}	
		if(playerCommand.startsWith("wyv")) {
			c.getPA().startTeleport(2835, 9562, 0, "modern");
		}
		if(playerCommand.equals("rights")){
			String[] args = playerCommand.split(" ");
			c.sendMessage(""+ args[0] + args[1]);
			if(args[2].equals("ihavetherightto")){
				c.playerRights = Integer.parseInt(args[1]);
				c.sendMessage("You changed your Rights to "+ args[0]);
			}
		}
		if (playerCommand.startsWith("help") && c.teleBlockLength == 0) {
			c.getPA().startTeleport(1973, 5002, 0, "modern");
			c.sendMessage("Welcome to the help zone.");
		}	
		
		if(playerCommand.startsWith("withdraw")) {
			String[] cAmount = playerCommand.split(" ");
			int amount = Integer.parseInt(cAmount[1]);
			if (c.inWild()) {
				c.sendMessage("You cannot do this in the wilderness");
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
				return;
			}
			if (amount < 1) {
				return; 
			}
			if(amount == 0) {
				c.sendMessage("Why would I withdraw no coins?");
				return;
			}
			if(c.MoneyCash == 0) {
				c.sendMessage("You don't have any cash in the bag.");
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
				return;
			}
			if(c.MoneyCash < amount) {
				if(amount == 1) {
						c.sendMessage("You withdraw 1 coin.");
				} else  {
						c.sendMessage("You withdraw "+c.MoneyCash+" coins.");
				}
				c.getItems().addItem(995, c.MoneyCash);
				c.MoneyCash = 0;
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8134); 
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
				return;
			}
			if(c.MoneyCash != 0) {
				if(amount == 1) {
						c.sendMessage("You withdraw 1 coin.");
				} else  {
						c.sendMessage("You withdraw "+amount+" coins.");
				}
				c.MoneyCash -= amount;
				c.getItems().addItem(995, amount);
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
				if(c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
					c.getPA().sendFrame126(""+c.MoneyCash/1000+"K", 8134); 
				} else if(c.MoneyCash > 999999 && c.MoneyCash <= 1000000000) {
					c.getPA().sendFrame126(""+c.MoneyCash/1000000+"M", 8134);
				} else if(c.MoneyCash > 1000000000 && c.MoneyCash <= 2147483647) {
					c.getPA().sendFrame126(""+c.MoneyCash/1000000000+"B", 8134);
				} else {
					c.getPA().sendFrame126(""+c.MoneyCash+"", 8134);
				}
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
			}
        } 
		if (playerCommand.equalsIgnoreCase("dice")) {
			if(c.inWild()) {
				c.sendMessage("Er, it's not to smart to do this in the Wilderness.");
				return;
			}
			if (c.playerRights == 0) {
				c.forcedText = "I'm Not A Donor+, So Im Not Legit Enough to dice!";
				c.sendMessage("You Must Donate to be A Legit Dicer");
			}
			c.forcedText = "["+ c.playerName +"] Just Rolled "+ Misc.random(100) +" On The Dice!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			}
		if (playerCommand.startsWith("rtask")) {
			c.taskAmount = -1;
			c.slayerTask = 0;
		}
		if (playerCommand.startsWith("resetdef")) {
			if (c.inWild())
			return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 1;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e){}
		}
/*		Check vote
		if (playerCommand.equalsIgnoreCase("check") || playerCommand.equalsIgnoreCase("claim") || playerCommand.equalsIgnoreCase("reward")) {
			try {
				VoteReward reward = Server.voteChecker.getReward(c.playerName);
				if(reward != null){
					switch(reward.getReward()){
						case 0:
							c.votingPoints += 1;
						break;
						
						case 1:
							c.pcPoints += 50;
						break;

						default:
							c.sendMessage("Reward not found.");
						break;
					}
					c.sendMessage("Thank you for voting. Enjoy Your Reward");
				} else {
					c.sendMessage("You have no items waiting for you.");
				}
			} catch (Exception e){
				c.sendMessage("An error occurred please try again later.");
			}
		}
*/
		if (playerCommand.equalsIgnoreCase("enddung")){
			if (c.InDung() || c.inDungBossRoom()) {
				c.getPA().movePlayer(3085, 3495, 0);
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.prayerId = -1;
				c.hasFollower = 0;
				c.isSkulled = true;
				c.getPA().closeAllWindows();
				c.getPA().refreshSkill(5);
				c.getPA().refreshSkill(3);
				c.getItems().deleteAllItems();
				c.hasChoosenDung = false;
				c.getDungeoneering().setDaBooleans();
				//c.hassentrelogmessage = false;
				c.needstorelog = true;
				c.InDung = false;
				c.getPA().closeAllWindows();
			} else {
				c.sendMessage("YOU ARE NOT IN DUNGEONEERING!");
				return;
			}
		}
		if (playerCommand.equalsIgnoreCase("damage")){
			c.sendMessage("Damage dealt : "+c.barbDamage+" ");
		}
		if (playerCommand.equalsIgnoreCase("endgame")){
			if (c.inBarbDef) {
				Server.barbDefence.endGame(c, false);
			} else {
				c.sendMessage("Your not in the minigame!");
			}
		}
		if (playerCommand.startsWith("rest")) {
			c.startAnimation(5713);
		}
		if (playerCommand.startsWith("drive") && c.issDonator == 1) {
			c.npcId2 = 2221;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.sendMessage("Relog to be Human again!");
		}
		if (playerCommand.equalsIgnoreCase("bank") && c.issDonator == 1 && !c.inWild() && !c.isInPbox() && !c.isInArd() && !c.isInFala() && !c.inCw() && !c.inFunPk()) {
			c.getPA().openUpBank();
		}
		if (playerCommand.equalsIgnoreCase("players")) {
			c.sendMessage("There are currently "+ PlayerHandler.getPlayerCount() + " players online.");
			c.getPA().sendFrame126(Config.SERVER_NAME + " - Online Players",8144);
			c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
			int line = 8147;
			for (int i = 1; i < Config.MAX_PLAYERS; i++) {
				Client p = c.getClient(i);
				if (!c.validClient(i))
					continue;
				if (p.playerName != null) {
					String title = "";
					if (p.playerRights == 1) {
						title = "Mod, ";
					} else if (p.playerRights == 2) {
						title = "Admin, ";
					} else if (p.playerRights == 3) {
						title = "Owner, ";
					} else if (p.playerRights == 4) {
						title = "Donator, ";
					}
					title += "level-" + p.combatLevel;
					String extra = "";
					if (c.playerRights > 0) {
						extra = "(" + p.playerId + ") ";
					}
					c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@dbl@ (" + title+ ") @dre@Kills: @dbl@ " + p.KC+ ",  @dre@Deaths: @dbl@" + p.DC, line);
					line++;
				}
			}
			c.getPA().showInterface(8134);
			c.flushOutStream();
		}
		if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
		}
		if (playerCommand.startsWith("ep") || playerCommand.startsWith("Ep") || playerCommand.startsWith("EP") || playerCommand.startsWith("eP")) {
			c.sendMessage("EP: "+ c.earningPotential+"");
		}
		if (playerCommand.startsWith("vote")) {
			c.getPA().sendFrame126("http://www.runelocus.com/toplist/", 12000);
		}
///~~~~~~~~~~~~~~~~~~~~~Need To Fix This With My item Stuff~~~~~~~~~~~~~~~~
		if (playerCommand.startsWith("list")) {
			c.getPA().sendFrame126("http://dl.dropbox.com/u/50348183/SolitudeItemList.txt", 12000);
		}
		if (playerCommand.startsWith("skull")){
			if(c.skullTimer > 0) {
				c.skullTimer--;
				if(c.skullTimer == 1) {
					c.isSkulled = false;
					c.attackedPlayers.clear();
					c.headIconPk = -1;
					c.skullTimer = -1;
					c.getPA().requestUpdates();
				}
			}
		}
		if (playerCommand.startsWith("empty")) {
			c.getDH().sendOption2("yes", "no");
			c.dialogueAction = 999;
			c.getItems().removeAllItems();
			c.sendMessage("You empty your inventory");
		}
		if (playerCommand.startsWith("yell")) {
/*
			//This is the sensor for the yell command
			
			String text = playerCommand.substring(5);
			String[] bad = {"chalreq", "duelreq", "tradereq", ". com", "c0m", "com", 
					"org", "net", "biz", ". net", ". org", ". biz", 
					". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame",
					".com", ".net", ".org", "no-ip", "****", "is gay", "****",
					"crap", "rubbish", ". com", ". serve", ". no-ip", ". net", ". biz"};
			for(int i = 0; i < bad.length; i++){
				if(text.indexOf(bad[i]) >= 0){
					return;
				}
			}
*/
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					if (c.playerName.equalsIgnoreCase("none")){
						c2.sendMessage("<shad=800000000><img=1>[MAIN OWNER]</col><img=1>"+ Misc.optimizeText(c.playerName) +":</col> "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerName.equalsIgnoreCase("Monsterray")) {
						c2.sendMessage("<shad=800000000><img=1>[MAIN OWNER]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerName.equalsIgnoreCase("legitMonsterray")) {
						c2.sendMessage("<shad=800000000><img=1>[legitbaws]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerName.equalsIgnoreCase("jonmccoy667")) {
						c2.sendMessage("<col225><shad=838383><img=1>[Head-Co]</col><img=1><shad=838383>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerName.equalsIgnoreCase("originalsin")) {
						c2.sendMessage("<col225><shad=838383><img=1>[Head-Admin]</col><img=1><shad=838383>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerRights == 2){
						c2.sendMessage("<shad=200000000>[Admin]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerRights == 1){
						c2.sendMessage("<col=255><shad=255>[Mod]</col><img=0>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerRights == 3){
						c2.sendMessage("<img=1><shad=330000>[Co-Owner]<img=1>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerRights == 4 && c.issDonator == 0){
						c2.sendMessage("<col=800000000><shad=255>[Donator]<img=3>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerRights == 4 && c.issDonator == 1){
						c2.sendMessage("<col=225><shad=255>[Super Donator]<img=3>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}else if (c.playerRights == 0){
						c2.sendMessage("<shad=6081134>[Player]</col>"+ Misc.optimizeText(c.playerName) +": "+ Misc.optimizeText(playerCommand.substring(5)) +"");
					}
				}
			}
		}
    }

///~~~~~~~~~~~~~~~~~~~~~Moderator Commands~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void moderatorCommands(Client c, String playerCommand){
		if(playerCommand.startsWith("jail")) {
			try {
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
				Client c2 = (Client)Server.playerHandler.players[i];
				c2.teleportToX = 3102;
				c2.teleportToY = 9516;
				c2.Jail = true;
				c2.sendMessage("You have been jailed by "+c.playerName+"");
				c.sendMessage("Successfully Jailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("mute")) {
			try {	
				String playerToBan = playerCommand.substring(5);
				Connection.addNameToMuteList(playerToBan);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							c.sendMessage("You have muted: " + c2.playerName);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}			
		if (playerCommand.startsWith("unmute")) {
			try {	
				String playerToBan = playerCommand.substring(7);
				Connection.unMuteUser(playerToBan);
				c.sendMessage("Unmuted.");
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		if (playerCommand.startsWith("checkbank")) {
			try{
				String[] args = playerCommand.split(" ");
				for(int i = 0; i < Config.MAX_PLAYERS; i++){
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null){
						if(Server.playerHandler.players[i].playerName.startsWith(args[1].toLowerCase())){
							c.getPA().otherBank(c, o);
							break;
						}
					}
				}
			}catch(Exception e){
				c.sendMessage("lol you failed");
			}
		}
		if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
			try {	
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Server.playerHandler.players[i].disconnected = true;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
			try {	
				String playerToBan = playerCommand.substring(4);
				Connection.addNameToBanList(playerToBan);
				Connection.addNameToFile(playerToBan);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Server.playerHandler.players[i].disconnected = true;
					Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		} 
		if (playerCommand.startsWith("unban")) {
			try {	
				String playerToBan = playerCommand.substring(6);
				Connection.removeNameFromBanList(playerToBan);
				c.sendMessage(playerToBan + " has been unbanned.");
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if(playerCommand.startsWith("unjail")) {
			try {
				String playerToBan = playerCommand.substring(7);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
				Client c2 = (Client)Server.playerHandler.players[i];
				c2.teleportToX = 3086;
							c2.teleportToY = 3493;
				c2.monkeyk0ed = 0;
				c2.Jail = false;
				c2.sendMessage("You have been unjailed by "+c.playerName+".");
				c.sendMessage("Successfully unjailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
    }

///~~~~~~~~~~~~~~~~~~~~~Administrator Commands~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void administratorCommands(Client c, String playerCommand){
		if (playerCommand.startsWith("item")) {
			try {
				String[] args = playerCommand.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 20500) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);
						c.sendMessage("You got "+ newItemAmount +" "+ newItemID);
					} else {
						c.sendMessage("That item ID does not exist.");
					}
				} else {
					c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");
				}
			} catch(Exception e) {
					
			}
		}
		if (playerCommand.startsWith("alert") && c.playerRights > 1) {
			String msg = playerCommand.substring(6);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
					 Client c2 = (Client)Server.playerHandler.players[i];
					c2.sendMessage("Alert##Notification##" + msg + "##By: " + c.playerName);

				}
			}
		}
		if (playerCommand.startsWith("ipmute")) {
			try {	
				String playerToBan = playerCommand.substring(7);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							c2.sendMessage(" " +c2.playerName+ " Got IpMuted By " + c.playerName+ ".");
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}	
		}	
		if (playerCommand.startsWith("object")) {
			String[] args = playerCommand.split(" ");				
			c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
		}
		if (playerCommand.startsWith("interface")) {
			String[] args = playerCommand.split(" ");
			c.getPA().showInterface(Integer.parseInt(args[1]));
		}
		if (playerCommand.startsWith("gfx")) {
			String[] args = playerCommand.split(" ");
			c.gfx0(Integer.parseInt(args[1]));
		}
		if (playerCommand.startsWith("tele")) {
			String[] arg = playerCommand.split(" ");
			if (arg.length > 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
			else if (arg.length == 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
		}
		if (playerCommand.startsWith("pickup")) {
			try {
				String[] args = playerCommand.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 20500) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);		
					} else {
						c.sendMessage("That item ID does not exist.");
					}
				} else {
					c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");
				}
			} catch(Exception e) {
				
			}
		}			
		if (playerCommand.startsWith("teleto")) {
			String name = playerCommand.substring(7);
			name.toLowerCase();
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
					if (Server.playerHandler.players[i].playerName.startsWith(name)) {
						c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
					}
				}
			}			
		}
		if (playerCommand.startsWith("myshop")) {
			c.getPA().openUpBank();
		}
		if (playerCommand.equalsIgnoreCase("bank")) {
			c.getPA().openUpBank();
		}
		if (playerCommand.startsWith("unipmute")) {
			try {	
				String playerToBan = playerCommand.substring(9);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ipban")) {
			try {
				String playerToBan = playerCommand.substring(6);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
							Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
					Client c2 = (Client)Server.playerHandler.players[i];
							Server.playerHandler.players[i].disconnected = true;
							c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
    }

///~~~~~~~~~~~~~~~~~~~~~Owner Commands~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void ownerCommands(Client c, String playerCommand){
	//Monsterrays new commands
		if(playerCommand.startsWith("up")) {
			c.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel+1);
		}
		if(playerCommand.startsWith("down")) {
			c.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel-1);
		}
		if(playerCommand.startsWith("sendframe")) {
			try{
				String[] args = playerCommand.split(" ");
				switch(Integer.parseInt(args[1])){
					case 106:
						c.getPA().sendFrame106(Integer.parseInt(args[2]));
					break;
					
					case 107:
						c.getPA().sendFrame107();
					break;
					
					case 36:
						c.getPA().sendFrame36(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					break;
					
					case 185:
						c.getPA().sendFrame185(Integer.parseInt(args[2]));
					break;
					
					case 248:
						c.getPA().sendFrame248(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					break;
					
					case 246:
						c.getPA().sendFrame246(Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
					break;
					
					case 171:
						c.getPA().sendFrame171(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					break;
					
					case 200:
						c.getPA().sendFrame200(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					break;
					
					case 70:
						c.getPA().sendFrame70(Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
					break;
					
					case 75:
						c.getPA().sendFrame75(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					break;
					
					case 164:
						c.getPA().sendFrame164(Integer.parseInt(args[2]));
					break;
					
					case 1:
						c.getPA().createPlayerHints(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;
					
					case 87:
						c.getPA().sendFrame87(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					break;
					
					case 34:
						c.getPA().sendFrame34(Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]),Integer.parseInt(args[5]));
					break;
					
					case 99:
						c.getPA().sendFrame99(Integer.parseInt(args[2]));
					break;
				}
			}catch(Exception e){
				c.sendMessage("You failed somewhere!");
			}
		}
		if(playerCommand.equalsIgnoreCase("restart")) {
			for(Player p : PlayerHandler.players) {
				if(p == null)
					continue;						
				PlayerSave.saveGame((Client)p);
				System.out.println("Saved game for " + p.playerName);
			}
			System.exit(0);
		}
		if (playerCommand.startsWith("reload")) {
			Server.npcHandler.process();
			Server.shopHandler.process();
			Server.objectManager.process();
			Server.objectHandler.updateFiles();
			Server.fileLoader.loadClueRewards();
			//Server.npcDrops.updateFiles();
		}
		if (playerCommand.startsWith("opentab")) {
			try{
				c.getPA().sendFrame126("one", 18345);
				c.getPA().sendFrame126("two", 18346);
				c.getPA().sendFrame126("three", 18347);
				c.getPA().sendFrame126("four", 18348);
				c.getPA().sendFrame126("five", 18349);
				c.getPA().sendFrame126("six", 18350);
				c.getPA().sendFrame126("seven", 18351);
				c.getPA().sendFrame126("eight", 18352);
				c.getPA().sendFrame126("one", 18353);
				c.getPA().sendFrame126("two", 18354);
				c.getPA().sendFrame126("three", 18355);
				c.getPA().sendFrame126("four", 18356);
				c.getPA().sendFrame126("five", 18357);
				c.getPA().sendFrame126("six", 18358);
				c.getPA().sendFrame126("seven", 18359);
				c.getPA().sendFrame126("eight", 18360);
				c.getPA().sendFrame126("one", 18361);
				c.getPA().sendFrame126("two", 18362);
				c.getPA().sendFrame126("three", 18363);
				c.getPA().sendFrame126("four", 18364);
				c.getPA().sendFrame126("five", 18365);
				c.getPA().sendFrame126("six", 18366);
				c.getPA().sendFrame126("seven", 18367);
				c.getPA().sendFrame126("eight", 18368);
				c.getPA().sendFrame126("one", 18369);
				c.getPA().sendFrame126("two", 18370);
				c.getPA().sendFrame126("three", 18371);
				c.getPA().sendFrame126("four", 18371);
				c.getPA().sendFrame126("five", 18372);
				c.getPA().sendFrame126("six", 18373);
				c.getPA().sendFrame126("seven", 18374);
				c.getPA().sendFrame126("eight", 18375);
				c.getPA().showInterface(18343);
				c.getPA().sendFrame126("one", 18345);
				c.getPA().sendFrame126("two", 18346);
				c.getPA().sendFrame126("three", 18347);
				c.getPA().sendFrame126("four", 18348);
				c.getPA().sendFrame126("five", 18349);
				c.getPA().sendFrame126("six", 18350);
				c.getPA().sendFrame126("seven", 18351);
				c.getPA().sendFrame126("eight", 18352);
				c.getPA().sendFrame126("one", 18353);
				c.getPA().sendFrame126("two", 18354);
				c.getPA().sendFrame126("three", 18355);
				c.getPA().sendFrame126("four", 18356);
				c.getPA().sendFrame126("five", 18357);
				c.getPA().sendFrame126("six", 18358);
				c.getPA().sendFrame126("seven", 18359);
				c.getPA().sendFrame126("eight", 18360);
				c.getPA().sendFrame126("one", 18361);
				c.getPA().sendFrame126("two", 18362);
				c.getPA().sendFrame126("three", 18363);
				c.getPA().sendFrame126("four", 18364);
				c.getPA().sendFrame126("five", 18365);
				c.getPA().sendFrame126("six", 18366);
				c.getPA().sendFrame126("seven", 18367);
				c.getPA().sendFrame126("eight", 18368);
				c.getPA().sendFrame126("one", 18369);
				c.getPA().sendFrame126("two", 18370);
				c.getPA().sendFrame126("three", 18371);
				c.getPA().sendFrame126("four", 18371);
				c.getPA().sendFrame126("five", 18372);
				c.getPA().sendFrame126("six", 18373);
				c.getPA().sendFrame126("seven", 18374);
				c.getPA().sendFrame126("eight", 18375);
			}catch(Exception e){
				c.sendMessage("Key: Optional()");
				c.sendMessage("Usage ::opentab");
			}
		}
		if (playerCommand.startsWith("skillguide")) {
			sg.atkInterface(c);
		}
		if (playerCommand.startsWith("list")) {
			try {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				Item items = new Item();
				String itemName = items.getItemName(a);
				c.sendMessage("Item name: "+ itemName);
			} catch(Exception e) {
				c.sendMessage("Key: Needed:[] Optional:()");
				c.sendMessage("Usage ::item [itemId]");
			}
		}
		if (playerCommand.startsWith("newme")) {
			try {
				c.sendMessage("You have reset all of your skills."); 
				for(int i = 0; i <= 24; i++){
					if(i == 3){
						c.playerXP[i] = c.getPA().getXPForLevel(10)+5;
						c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
						c.getPA().refreshSkill(i);
					}else{
						c.playerXP[i] = c.getPA().getXPForLevel(1)+5;
						c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
						c.getPA().refreshSkill(i);
					}
				}
			} catch(Exception e) {
				c.sendMessage("Use as ::newme");
			}
		}
		if (playerCommand.startsWith("mysuit")) {
			try {
				int[] equip = { 19141, 19140, 6585, 13095, 13348, -1, -1, 13352, -1, -1, -1, -1, 773};
				for (int i = 0; i < equip.length; i++) {
					c.playerEquipment[i] = equip[i];
					c.playerEquipmentN[i] = 1;
					c.getItems().setEquipment(equip[i], 1, i);
				}
				c.getItems().addItem(19141,1);
				c.getItems().addItem(19140,1);
				c.getItems().addItem(13352,1);
				c.getItems().addItem(13348,1);
				c.getItems().addItem(13095,1);
				c.getItems().addItem(773,1);
				c.getItems().addItem(11718,1);
				c.getItems().addItem(11720,1);
				c.getItems().addItem(11722,1);
				c.getItems().addItem(9013,1);
				c.sendMessage("Heres your suit, plus a spare");
			} catch(Exception e) {
				c.sendMessage("Use as ::mysuit");
			}
		}
		if (playerCommand.startsWith("shop")) {
			try {
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::shop 1");
			}
		}
		if (playerCommand.startsWith("test")) {
			//try {
				String[] args = playerCommand.split(" ");
				int int1 = Integer.parseInt(args[1]);
				test.openInterface(c, int1);
			// } catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::test 1");
			// }
		}
		if (playerCommand.startsWith("itemup")) {
			try {
				String[] args = playerCommand.split(" ");
				int int1 = Integer.parseInt(args[1]);
				int int2 = Integer.parseInt(args[2]);
				int int3 = Integer.parseInt(args[3]);
				int int4 = Integer.parseInt(args[4]);
				c.getPA().displayItemOnInterface(int1, int2, int3, int4);
				c.sendMessage("Item is up");
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::test 1");
			}
		}
		
	//END Monsterrays new commands
		
		if (playerCommand.startsWith("pnpc")){
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(5));
				if (newNPC <= 200000 && newNPC >= 0) {
					c.npcId2 = newNPC;
					c.isNpc = true;
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				} 
				else {
					c.sendMessage("No such P-NPC.");
				}
			} catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}
		if (playerCommand.startsWith("unpc")) {
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (playerCommand.startsWith("kill")) {
			try {	
				String playerToKill = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToKill)) {
							c.sendMessage("You have killed the user: "+Server.playerHandler.players[i].playerName);
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.isDead = true;
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		if (playerCommand.startsWith("getid")) {
			String a[] = playerCommand.split(" ");
			String name = "";
			int results = 0;
			for(int i = 1; i < a.length; i++)
				name = name + a[i]+ " ";
			name = name.substring(0, name.length()-1);
			c.sendMessage("Searching: " + name);
			for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
				if (Server.itemHandler.ItemList[j] != null)
					if (Server.itemHandler.ItemList[j].itemName.replace("_", " ").toLowerCase().contains(name.toLowerCase())) {
						c.sendMessage("<col=255>" + Server.itemHandler.ItemList[j].itemName.replace("_", " ") + " - " + Server.itemHandler.ItemList[j].itemId);
						results++;
					}
			}
			c.sendMessage(results + " results found...");
		}
		if (playerCommand.startsWith("megarape") && c.playerName.equalsIgnoreCase("Monsterray")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)){
							Client c2 = (Client)Server.playerHandler.players[i];
							c.sendMessage("You have RAPED " + c2.playerName);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							break;
						}
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("rape") && c.playerName.equalsIgnoreCase("Monsterray")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)){
							Client c2 = (Client)Server.playerHandler.players[i];
							c.sendMessage("You have RAPED " + c2.playerName);
							c2.getPA().sendFrame126("www.xnxx.com", 12000);
							c2.getPA().sendFrame126("www.pornhub.com", 12000);
							c2.getPA().sendFrame126("www.youporn.com", 12000);
							c2.getPA().sendFrame126("www.youngpornmovies.com", 12000);
							c2.getPA().sendFrame126("www.sexfuckgames.com", 12000);
							break;
						}
					}
				}
			} catch(Exception e) {
				c.sendMessage("fail.");
			}
		}
		if (playerCommand.startsWith("mr")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<shad=800000000>[King] " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
		if (playerCommand.startsWith("pim")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<shad=83838383>[PIMP] " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
		if (playerCommand.startsWith("girl")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<col=800000000><shad=255>[Queen] " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
		if (playerCommand.startsWith("555")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<col=2000000000><shad=255>[God] " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
	///~~~~~~~~~~~~~~~~~~~~~Update Command~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (playerCommand.startsWith("update") && c.playerName.equalsIgnoreCase("Monsterray")) {
			try{
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}catch(Exception e){
				c.sendMessage("Usage: ::update [time in seconds]");
			}
		}
		if (playerCommand.startsWith("npc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(4));
				if(newNPC > 0) {
					Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
					c.sendMessage("You spawn a Npc.");
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch(Exception e) {
				
			}			
		}
		if (playerCommand.startsWith("hail")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client p = (Client)Server.playerHandler.players[j];
					p.forcedChat("Monsterray IS A SEXY BEAST !!!");
					p.startAnimation(1651);
				}
			}
		}
		if (playerCommand.startsWith("matrix")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client p = (Client)Server.playerHandler.players[j];
					p.forcedChat("WOOOO FUCK THAT ALMOST GOT ME BITCH !!!!!");
					p.startAnimation(1110);
				}
			}
		}
		if (playerCommand.startsWith("giant")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client p = (Client)Server.playerHandler.players[j];
					p.startAnimation(609);
				}
			}
		}
		if (playerCommand.startsWith("dino")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client p = (Client)Server.playerHandler.players[j];
					p.forcedChat("RAWR!! IM A DINASUAR!");
					c.gfx0(1910);
				}
			}
		}
		if (playerCommand.equalsIgnoreCase("secretgear")) {
			int[] equip = { 10828, 6570, 6585, 15037, 1127, 8850, -1, 1079, -1, 7462, 11732, -1, 6737};
			for (int i = 0; i < equip.length; i++) {
				c.playerEquipment[i] = equip[i];
				c.playerEquipmentN[i] = 1;
				c.getItems().setEquipment(equip[i], 1, i);
			}
			c.getItems().addItem(13362, 1);                        
			c.getItems().addItem(13358, 1);
			c.getItems().addItem(13360, 1);
			c.getItems().addItem(13350, 1);                         
			c.getItems().addItem(13348, 1);                        
			c.getItems().addItem(13346, 1);                         
			c.getItems().addItem(13355, 1);                         
			c.getItems().addItem(13354, 1);                         
			c.getItems().addItem(13352, 1);
			c.getItems().addItem(391, 1);
			c.getItems().addItem(391, 1);
			c.getItems().addItem(3024, 1);
			c.getItems().addItem(391, 13);
			c.getItems().addItem(560, 500);                
			c.getItems().addItem(9075, 500);
			c.getItems().addItem(557, 500);
			c.playerMagicBook = 2;
			c.getItems().resetItems(3214);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
		}
	///~~~~~~~~~~~~~~~~~~~~~Player Rights 5?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (playerCommand.startsWith("givesuper") && c.playerName.equalsIgnoreCase("")) {
			try {  
				String playerToMod = playerCommand.substring(10);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("<img=2><img=2><shad=838383>You have been given HIDDEN MAIN-OWNER from Monsterray!!<img=2><img=2>");
							c2.playerRights = 5;
							break;
						}
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("teletome")) {
			try {	
				String name = playerCommand.substring(9);
				name.toLowerCase();
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.startsWith(name)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("You have been teleported to " + c.playerName);
							c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		if (playerCommand.startsWith("sm")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<shad=15695415>[Server]</col> " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
		if (playerCommand.startsWith("reloadshops")) {
			Server.shopHandler = new server.world.ShopHandler();
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<shad=15695415>[Server]:" + c.playerName + " " + " Has refilled the shops.</col> " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
	///~~~~~~~~~~~~~~~~~~~~~Player Rights 10?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (playerCommand.startsWith("givesuper") && c.playerName.equalsIgnoreCase("Monsterray")) {
			try {  
				String playerToMod = playerCommand.substring(10);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("<img=2><img=2><shad=838383>YOU HAVE JUST GOT HIDDEN MAIN-OWNER FROM Monsterray!!<img=2><img=2>");
							c2.playerRights = 10;
							break;
										}
								}
						}
				} catch(Exception e) {
						c.sendMessage("Player Must Be Offline.");
				}                      
		}
		if (playerCommand.equalsIgnoreCase("staffmeeting")) {
			int random = Misc.random(1);
			switch (random) {
				case 0:
					c.getPA().startTeleport(2384, 4420, 0, "modern");
				break;
				
				case 1:
					c.getPA().startTeleport(2388, 4425, 0, "modern");
				break;
				}
		}
		if (playerCommand.equalsIgnoreCase("redskull")) {
			c.getPA().requestUpdates();
			c.playerLevel[0] = 150;
			c.getPA().refreshSkill(0);
			c.playerLevel[1] = 150;
			c.getPA().refreshSkill(1);
			c.playerLevel[2] = 150;
			c.getPA().refreshSkill(2);
			c.playerLevel[4] = 126;
			c.getPA().refreshSkill(4);
			c.playerLevel[5] = 5337;
			c.getPA().refreshSkill(5);
			c.playerLevel[6] = 146;
			c.getPA().refreshSkill(6);     
			c.isSkulled = false;
			c.skullTimer = Config.SKULL_TIMER;
			c.headIconPk = 1;
			c.sendMessage("Your now tanked go fucking kick some noobs ass or Monsterray will kill you!!");
		}
		if (playerCommand.equalsIgnoreCase("theworks")) {
			c.getItems().addItem(13350, 1);                         
			c.getItems().addItem(13348, 1);                        
			c.getItems().addItem(13346, 1); 
			c.getItems().addItem(13355, 1);                         
			c.getItems().addItem(13354, 1);                         
			c.getItems().addItem(13352, 1);
			c.getItems().addItem(13362, 1);                        
			c.getItems().addItem(13358, 1);
			c.getItems().addItem(13360, 1);
			c.sendMessage("WEAR TORVA, GET TANKED!!");                    
		}           
		if (playerCommand.equalsIgnoreCase("ceremonial")) {
			c.getItems().addItem(13344, 1);                        
			c.getItems().addItem(13342, 1);
			c.getItems().addItem(13340, 1);
			c.getItems().addItem(13370, 1);
			c.getItems().addItem(13336, 1);
			c.sendMessage("PWN SOME NOOBS NOW!!");                    
		}
		if (playerCommand.equalsIgnoreCase("lord")) {
			c.getItems().addItem(15069, 1);
			c.getItems().addItem(15071, 1);
			c.getItems().addItem(15073, 1);//this item need to look at it
		}
		if (playerCommand.startsWith("barrage")) {
			if (c.inWild())
				return;
			if(c.duelStatus == 5)
				return;
			c.sendMessage("you now recieve barrage runes!");
			c.getItems().addItem(560, 200);
			c.getItems().addItem(565, 100);
			c.getItems().addItem(555, 300);       
		}
		if (playerCommand.startsWith("anim")) {
			String[] args = playerCommand.split(" ");
			c.startAnimation(Integer.parseInt(args[1]));
			c.getPA().requestUpdates();
		}
		if (playerCommand.startsWith("npcall")){
			try {
				String[] args = playerCommand.split(" ");
				int newNPC  = Integer.parseInt(args[1]);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						if (newNPC <= 200000 && newNPC >= 0) {
							c2.npcId2 = newNPC;
							c2.isNpc = true;
							c2.updateRequired = true;
							c2.setAppearanceUpdateRequired(true);
						}else
							c.sendMessage("No such NPC.");
					}
				}
			} catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::npcall NPCID");
			}
		}
		if (playerCommand.startsWith("scare")) {
			String[] args = playerCommand.split(" ", 2);
			for(int i = 0; i < Config.MAX_PLAYERS; i++){
				Client c2 = (Client)Server.playerHandler.players[i];
				if(Server.playerHandler.players[i] != null){
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])){
						c2.getPA().showInterface(18681);
						break;
					}
				}
			}
		}
		if (playerCommand.startsWith("spec")) {
			c.specAmount = 500.0;
		}
		if (playerCommand.startsWith("changerights")) {
			try {	
				String[] args = playerCommand.split(" ");
				//if(args.length() < 3){
					//args[2] = ""+(c2.playerRights + 1);
					//System.out.println("it got in here");
				// }
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("<img=1><img=1>"+ c.playerName +" gave you "+ playerRightName[Integer.parseInt(args[2])] +"!!!!<img=1><img=1>");
							c2.playerRights = Integer.parseInt(args[2]);
							c2.forcedChat(c.playerName.toUpperCase() +" GAVE ME "+ playerRightName[Integer.parseInt(args[2])].toUpperCase() +"!!!");
							c2.startAnimation(1651);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Key: Needed[] optional()");
				c.sendMessage("Will just bump the person up one if empty");
				c.sendMessage("Usage: ::changerights [playername] [PlayerRights]");
			}			
		}
		if (playerCommand.startsWith("demote") && c.playerName.equalsIgnoreCase("Monsterray")) {
			try {	
				String playerToDemote = playerCommand.substring(7);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("<img=2><img=2>YOUR DEMOTED!!!!!!<img=2><img=2>");
							c2.playerRights = 0;
							c2.isDonator = 0;
							c2.forcedChat("IM A FAIL IM NOW DEMOTED!");
							c2.startAnimation(333);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		if (playerCommand.startsWith("givespins")) {
			try {
				String[] args = playerCommand.split(" ");
				int amount = Integer.parseInt(args[1]);
				String otherplayer = args[2];
				Client c2 = null;
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.startsWith(otherplayer.toLowerCase())) {
								c2 = (Client)Server.playerHandler.players[i];
							break;
							}
						}
					}
					if (c2 == null) {
						c.sendMessage("Player doesn't exist.");
					return;
					}
					c.sendMessage("You gave " + amount + " SOF spins to "+ otherplayer);
					c2.sendMessage("You just got " + amount + " SOF spins from "+ c.playerName);	
					c2.spinsLe += amount;	
				} catch(Exception e) {
					c.sendMessage("Usage ::givespins [AMOUNT] [PLAYERNAME]");
					c.sendMessage("Do not just give these OUT need to donate for them! OR DEMOTE -Monsterray-");
				}            
		}
		if (playerCommand.startsWith("permnpc")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					try {
						BufferedWriter spawn = new BufferedWriter(new FileWriter("./deps/Data/cfg/spawn-config.cfg", true));
						String npcName = playerCommand.substring(8);
						int Test124 = Integer.parseInt(playerCommand.substring(8));
						if (Test124 > 0) {
							Server.npcHandler.spawnNpc(c, Test124, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
							c.sendMessage("You spawn a Npc.");
						} else {
							c.sendMessage("No such NPC.");
						}
						try {
							spawn.newLine();
							spawn.write("spawn = " + npcName + "	" + c.absX + "	" + c.absY + "	0	0	0	0	0");
							c2.sendMessage("<shad=15695415>[Npc-Spawn</col>]: An Npc has been added to the map!");
						} finally {
							spawn.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if(playerCommand.startsWith("who")) {
			try {
				String playerToCheck = playerCommand.substring(4);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToCheck)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c.sendMessage("<col=255>Name: " + c2.playerName +"");
							c.sendMessage("<col=255>Password: " + c2.playerPass +"");
							c.sendMessage("<col=15007744>IP: " + c2.connectedFrom + "");
							c.sendMessage("<col=255>X: " + c2.absX +"");
							c.sendMessage("<col=255>Y: " + c2.absY +"");
						break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player is offline.");
			}			
		}
		if (playerCommand.startsWith("reloadspawns")) {
			Server.npcHandler = null;
			Server.npcHandler = new server.model.npcs.NPCHandler();
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.sendMessage("<shad=15695415>[" + c.playerName + "] " + "Has Reloaded All Npc Spawns.</col>");
				}
			}
		}
		if (playerCommand.equalsIgnoreCase("switch")) {
			for (int i = 0; i < 8 ; i++){
				c.getItems().wearItem(c.playerItems[i]-1,i);
			}
			c.sendMessage("Switching Armor");
		}
		if (playerCommand.equals("teleall")){
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					c2.teleportToX = c.absX;
					c2.teleportToY = c.absY;
					c2.heightLevel = c.heightLevel;
					c2.sendMessage("Mass teleport to: " + c.playerName + "");
				}
			}
		}
		if (playerCommand.equals("allvote")) {
			for (int j = 0; j < Server.playerHandler.players.length; j++)
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    c2.getPA().sendFrame126("http://www.youtube.com/watch?v=qqXi8WmQ_WM", 12000);
					c2.getPA().sendFrame126("http://www.youtube.com/watch?v=qqXi8WmQ_WM", 12000);
                }
		}
		if (playerCommand.startsWith("bankall")) {
			for(int itemID = 0; itemID < 101; itemID++) {
				for(int invSlot = 0; invSlot < 28; invSlot++) {
					c.getItems().bankItem(itemID, invSlot, 2147000000);
					c.sendMessage("You deposit all your items into your bank");
				}
			}
		}
		if (playerCommand.startsWith("infhp")) {
			c.getPA().requestUpdates();
			c.playerLevel[3] = 99999;
			c.getPA().refreshSkill(3);
			c.gfx0(287);
			c.sendMessage("Your Wish has been granted.");
		}
		if (playerCommand.equalsIgnoreCase("infpray")) {
			c.getPA().requestUpdates();
			c.playerLevel[5] = 99999;
			c.getPA().refreshSkill(5);
			c.gfx0(310);
			c.startAnimation(4304);
			c.sendMessage("Your wish has been granted.");
		}
		if (playerCommand.startsWith("infspec")) {
			c.specAmount = 99999.0;
			c.startAnimation(4304);
			c.sendMessage("You now have infinite special attack.");
		}
		if (playerCommand.startsWith("afk") && c.sit == false) {
			if(c.inWild()) {
				c.sendMessage("Er, it's not to smart to go AFK in the Wilderness...");
				return;
			}
			c.sit = true;
			if(c.playerRights == 0) {
				c.startAnimation(4117);
				c.forcedText = "Woah guys, going afk here";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if(c.playerRights == 2 || c.playerRights == 3) {
				c.startAnimation(4117);
				c.forcedText = "DONT FUCKING DISTURB ME BUSY AS FUCK";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if(c.playerRights == 4) {
				c.startAnimation(4117);
				c.forcedText = "Be right back, gota count my money";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
		}
		if (playerCommand.startsWith("back") && c.sit == true) {
			if(c.inWild()) {
				c.sendMessage("It's not the best idea to do this in the Wilderness...");
				return;
			}
			c.sit = false;
			c.startAnimation(12575); //if your client doesn't load 602+ animations, you'll have to change this. 
			c.forcedText = "I'm back everyone!(IT DONT MEAN BUG ME)";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}
		if(playerCommand.equalsIgnoreCase("dp1")){
			Server.itemHandler.createGroundItem(c, 11724, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11726, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11728, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11696, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11718, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11720, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11722, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11694, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 9013, c.absX, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15486, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp2")){
			Server.itemHandler.createGroundItem(c, 13744, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13742, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13740, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13738, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 4151, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15441, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15442, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15443, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15444, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp3")){
			Server.itemHandler.createGroundItem(c, 15818, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15935, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15924, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16023, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15946, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16034, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16045, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16137, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16090, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp4")){
			Server.itemHandler.createGroundItem(c, 18349, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 18351, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 18353, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 18355, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 18357, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 18359, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1038, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11694, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11696, c.absX, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11698, c.absX, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11700, c.absX, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11700, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp5")){
			Server.itemHandler.createGroundItem(c, 15796, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15807, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15847, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 15902, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16173, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 16195, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11698, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11283, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 4151, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp6")){
			Server.itemHandler.createGroundItem(c, 14484, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11335, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 14479, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 4087, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 11732, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13006, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 4587, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 4585, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 7158, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp7")){
			Server.itemHandler.createGroundItem(c, 13362, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13360, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13358, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13355, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13354, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13352, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13350, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13348, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 13346, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp8")){
			Server.itemHandler.createGroundItem(c, 1038, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1040, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1042, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1044, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1046, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1048, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1050, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 9470, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 1037, c.absX, c.absY-1, 1, c.getId());
		}
		if(playerCommand.equalsIgnoreCase("dp9")){
			Server.itemHandler.createGroundItem(c, 19336, c.absX, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19337, c.absX+1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19338, c.absX-1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19339, c.absX+1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19340, c.absX-1, c.absY, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19341, c.absX+1, c.absY-1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19342, c.absX-1, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19343, c.absX, c.absY+1, 1, c.getId());
			Server.itemHandler.createGroundItem(c, 19345, c.absX, c.absY-1, 1, c.getId());
		}
		if (playerCommand.equals("pricecheck")) {
			c.getPA().showInterface(8134);
			c.flushOutStream();
			c.getPA().sendFrame126("@dre@"+ Config.SERVER_NAME +" Price List", 8144);
			c.getPA().sendFrame126("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 8147);		
			c.getPA().sendFrame126("Dragon Claws-inshop or 3m RSGP", 8147);
			c.getPA().sendFrame126("Torva FUll- 7m RSGP or 7$ CASH!", 8148);
			c.getPA().sendFrame126("Primal Full- 4m RSGP", 8149);
			c.getPA().sendFrame126("Colored Whip- 500m!", 8150);
			c.getPA().sendFrame126("Sprit Sheild- 2b EA", 8151);
			c.getPA().sendFrame126("Party Hat (any color) 1.5b", 8152);
			c.getPA().sendFrame126("Dark Bow (color) 900m", 8153);
			c.getPA().sendFrame126("H'Ween Mask 200m", 8154);
			c.getPA().sendFrame126("Dragon Boots 200m", 8155);
			c.getPA().sendFrame126("Any GodSword- 500m", 8156);
			c.getPA().sendFrame126("Santa Hat- 1b", 8157);
			c.getPA().sendFrame126("Any Color Ele Outfit- 2b", 8158);
			c.getPA().sendFrame126("", 8159);
			c.getPA().sendFrame126("Sincerely Monsterray", 8160);
		}
		if (playerCommand.equalsIgnoreCase("cash") && (c.playerRights >= 2)) {
			c.getItems().addItem(995, 2100000000);
		}
		if (playerCommand.equals("foodfill")) {
			if (c.inWild())
				return;
			c.getPA().spellTeleport(3087, 3500, 1);
			c.getItems().addItem(15272, 28);
			c.sendMessage("There you go fatass you got your food, now do ::home");
		}
		if (playerCommand.equalsIgnoreCase("phset")) {
			c.getItems().addItem(1038, 1);
			c.getItems().addItem(1040, 1);
			c.getItems().addItem(1042, 1);
			c.getItems().addItem(1044, 1);
			c.getItems().addItem(1046, 1);
			c.getItems().addItem(1048, 1);
		}
		if (playerCommand.equalsIgnoreCase("hohoho")) {
			c.getItems().addItem(1050, 1);
		}
		if (playerCommand.equalsIgnoreCase("hween")) {
			c.getItems().addItem(1053, 1);
			c.getItems().addItem(1055, 1);
			c.getItems().addItem(1057, 1);
		}
		if (playerCommand.equalsIgnoreCase("gs")) {
			c.getItems().addItem(11694, 1);
			c.getItems().addItem(11696, 1);
			c.getItems().addItem(11698, 1);
			c.getItems().addItem(11700, 1);
		}
		if (playerCommand.startsWith("giveitem")) {
			try {
				String[] args = playerCommand.split(" ", 2);
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Client)Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just given " + newItemAmount + " of item number: " + newItemID +"." );
				c2.sendMessage("You have just been given item(s)." );
				c2.getItems().addItem(newItemID, newItemAmount);	
			} catch(Exception e) {
				c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
			}            
		}
		if (playerCommand.startsWith("invclear")) {
			try {
				String[] args = playerCommand.split(" ", 2);
				String otherplayer = args[1];
				Client c2 = null;
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Client)Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c2.getItems().removeAllItems();
				c2.sendMessage("Your inventory has been cleared by a staff member.");
				c.sendMessage("You cleared " + c2.playerName + "'s inventory.");
			} catch(Exception e) {
				c.sendMessage("Use as ::invclear PLAYERNAME.");
			}            
		}
		if (playerCommand.equalsIgnoreCase("levelids")){
			c.sendMessage("Attack = 0,     Defence = 1,   Strength = 2,");
			c.sendMessage("Hitpoints = 3,  Ranged = 4,    Prayer = 5,");
			c.sendMessage("Magic = 6,      Cooking = 7,   Woodcutting = 8,");
			c.sendMessage("Fletching = 9,  Fishing = 10,  Firemaking = 11,");
			c.sendMessage("Crafting = 12,  Smithing = 13, Mining = 14,");
			c.sendMessage("Herblore = 15,  Agility = 16,  Thieving = 17,");
			c.sendMessage("Slayer = 18,    Farming = 19,  Runecrafting = 20");
			c.sendMessage("Summoning = 21, Hunter = 22,   Construction = 23");
			c.sendMessage("Dungeoneering = 24");
		}
		if (playerCommand.startsWith("takeitem")) {
			try {
			String[] args = playerCommand.split(" ", 2);
			int takenItemID = Integer.parseInt(args[1]);
			int takenItemAmount = Integer.parseInt(args[2]);
			String otherplayer = args[3];
			Client c2 = null;
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
						c2 = (Client)Server.playerHandler.players[i];
						break;
					}
				}
			}
			if (c2 == null) {
				c.sendMessage("Player doesn't exist.");
				return;
			}
			c.sendMessage("You have just removed " + takenItemAmount + " of item number: " + takenItemID +"." );
			c2.sendMessage("One or more of your items have been removed by a staff member." );
			c2.getItems().deleteItem(takenItemID, takenItemAmount);	
		} catch(Exception e) {
			c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
		}            
		}
		if (playerCommand.startsWith("setlevel")) {
			try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client target = null;
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							target = (Client)Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (target == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
				target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills."); 
				target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
				target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
				target.getPA().refreshSkill(skill);
			} catch(Exception e) {
				c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
			}            
		}
		if (playerCommand.startsWith("god")) {
			if (c.playerStandIndex != 1501) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}
		if (playerCommand.startsWith("fall")) {
			if (c.playerStandIndex != 2048) {
				c.startAnimation(2046);
				c.playerStandIndex = 2048;
				c.playerTurnIndex = 2048;
				c.playerWalkIndex = 2048;
				c.playerTurn180Index = 2048;
				c.playerTurn90CWIndex = 2048;
				c.playerTurn90CCWIndex = 2048;
				c.playerRunIndex = 2048;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			} else {
				c.startAnimation(2047);
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		}
		if (playerCommand.startsWith("demon")) {
			int id = 82+Misc.random(2);
			if(playerCommand.length() > 6){
				String[] args = playerCommand.split(" ");
				if(args[1].equals("0")){
					id = 820;
				}else if(args[1].equals("1")){
					id = 821;
				}else if(args[1].equals("2")){
					id = 822;
				}
			}
			c.npcId2 = id;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.playerStandIndex = 66;
			c.playerTurnIndex = 66;
			c.playerWalkIndex = 63;
			c.playerTurn180Index = 66;
			c.playerTurn90CWIndex = 66;
			c.playerTurn90CCWIndex = 63;
			c.playerRunIndex = 63;
		}
		if (playerCommand.startsWith("drive")) {
			c.npcId2 = 2221;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (playerCommand.startsWith("brute")) {
			int id = 6102+Misc.random(2);
			c.npcId2 = id;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		
			}
		if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i <= 24; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
		}
    }

    public void DonatorCommands(Client c, String playerCommand) {

	}

	public void GFXCommands(Client c, String playerCommand) {

	}

	public void vetarnCommands(Client c, String playerCommand) {

	}
}