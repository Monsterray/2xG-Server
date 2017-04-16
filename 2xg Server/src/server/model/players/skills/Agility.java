package server.model.players.skills;

import server.model.players.Client;



public class Agility {

	public Client client;
	public int agtimer = 10;
	public boolean bonus = false;
	public boolean isBalance = false;

	public Agility(Client c) {
		client = c;
	}

	public void AgilityLog(Client c, String Object, int level, int x, int y, int a, int b, int xp){
		if (c.playerLevel[c.playerAgility] < level) {
			c.sendMessage("You need a Agility level of "+ level +" to pass this " + Object + ".");
			return;
		}
		if (c.absX == a && c.absY == b) {
			c.fmwalkto(x, y); 
			c.getPA().addSkillXP(xp, c.playerAgility);
			c.getPA().refreshSkill(c.playerAgility);
		}
		
	}

	public void AgilityNet(Client c, String net, int level, int a, int b, int h, int x, int y, int emote, int xp){
		if (c.playerLevel[c.playerAgility] < level) {
			c.sendMessage("You need a Agility level of "+ level +" to pass this " + net + ".");
			return;
		}
		if (c.absX == a && c.absY == b) {
			//Now we shall Call the X and Y points
			c.teleportToX = x;
			c.teleportToY = y;
			c.heightLevel = h;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.getPA().addSkillXP(xp, c.playerAgility);
			c.getPA().refreshSkill(c.playerAgility);
			c.turnPlayerTo(c.getX()- 1, c.getY());
		}
	}
	
	//Agility Branch make sure to set the correct exp's in the object
	public void AgilityBranch(Client c, String branch, int level, int x, int y, int h, int a, int b, int emote, int xp){
		if (c.playerLevel[c.playerAgility] < level) {
			c.sendMessage("You need a Agility level of "+ level +" to pass this " + branch + ".");
			return;
		}
			
		if (c.absX == a && c.absY == b) {
			c.teleportToX = x;
			c.teleportToY = y;
			c.heightLevel = h;
			c.getPA().addSkillXP(xp, c.playerAgility);
			c.getPA().refreshSkill(c.playerAgility);
		}
	}

	public void AgilityPipe(Client c, String pipe, int level, int a, int b, int x, int y, int add, int amount, int xp){
		if (c.playerLevel[c.playerAgility] < level) {
			c.sendMessage("You need a Agility level of "+ level +" to pass this " + pipe + ".");
			return;
		}
		if (c.absX == a && c.absY == b){
			if (bonus && c.ag1 == 1 && c.ag2 >= 1 && c.ag3 >= 1 && c.ag4 >= 1 && c.ag5 >= 1 && c.ag6 >= 1){
				c.fmwalkto(x, y);
				c.turnPlayerTo(c.getX(), c.getY()+ 1);
				c.getPA().addSkillXP(500000, c.playerAgility);
				c.getPA().refreshSkill(c.playerAgility);
				c.sendMessage("You completed the course and been rewarded with 20 Agility Points!");
				c.Wheel += 20;
				bonus = false;
				c.ag1 = 0;
				c.ag2 = 0;
				c.ag3 = 0;
				c.ag4 = 0;
				c.ag5 = 0;
				c.ag6 = 0;
			} else {
				c.fmwalkto(x, y);
				c.getPA().addSkillXP(xp, c.playerAgility);
				c.getPA().refreshSkill(c.playerAgility);
				c.turnPlayerTo(c.getX(), c.getY()+ 1);
				bonus = false;
				c.sendMessage("Next time finish the course, you are not getting any tickets nor exp.");
				c.ag1 = 0;
				c.ag2 = 0;
				c.ag3 = 0;
				c.ag4 = 0;
				c.ag5 = 0;
				c.ag6 = 0;
			}
		}
	}

	public void AgilityTicketCounter(Client c, String ticket, int remove, int amount, int xp) {
		if (c.getItems().playerHasItem(2996)) {
			if (ticket.equals("1")){
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 10)) {
			if (ticket.equals("10")){
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 25)) {
			if (ticket.equals("25")){
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 100)) {
			if (ticket.equals("100")){
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 1000)) {
			if (ticket.equals("1000")){
				c.sendMessage("This has been disabled");
			}
		}else{
			c.sendMessage("You need more agility tickets to get this Exp!");
			return;
		}
	}
}