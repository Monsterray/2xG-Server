package server.model.players.skills;


import server.Config;
import server.model.players.Client;

public class RuneCraft {

	public static void locate(Client c, int xPos, int yPos) {
		String X = "";
		String Y = "";
		if (c.absX >= xPos) {
			X = "west";
		}
		if (c.absY > yPos) {
			Y = "South";
		}
		if (c.absX < xPos) {
			X = "east";
		}
		if (c.absY <= yPos) {
			Y = "North";
		}
		//c.sendMessage("You need to travel "+Y+"-"+X+".");
		c.sendMessage("You teleport to the ruins..");
	}
	public static void craftRunes(Client c, int itemID)
	{
		int index = -1;
		int essence;
		int multiplier = 1;
		int multiplier2 = 0;
		for (int i1 = 0; i1 < runeInfo.length; i1++)
			if (runeInfo[i1][0] == itemID)
				index = i1;
		if (c.getPA().getXPForLevel(20) < runeInfo[index][1])

		{
			c.sendMessage("You need at least "+runeInfo[index][1]+" to runecraft this.");
			return;
		}
		if (c.getItems().getItemAmount(1436) > 0)
			essence = c.getItems().getItemAmount(1436);
		else 
			return;
		if (index == 0)
			multiplier = 11;
		else if (index <= 5)
			multiplier2 = 18+2*(index-2);
		else if (index <= 9)
			multiplier2 = 63+2*(index-2);
		if (index <= 9)
			for (int i2 = 1; i2 < 11; i2++)
				if (c.getPA().getXPForLevel(20) >= multiplier2*i2)
					multiplier = i2+1;
		for (int i = 0; i < essence; i++)
		c.getItems().deleteItem(1436, c.getItems().getItemSlot(1436), 1);
		c.getItems().addItem(itemID, essence*multiplier);
		c.getPA().addSkillXP(Config.RUNECRAFTING_EXPERIENCE * runeInfo[index][2], 20);
		c.getLevelForXP(20);
		c.sendMessage("You bind the temple's power into "+c.getItems().getItemName(itemID)+"s.");
		c.gfx0(186);
		c.startAnimation(791, 0);
	}
	
	public static int[][] runeInfo = {
	//	{itemID, LevelReq, XP per}
		{556, 1, 500},//air
		{558, 2, 1000},//mind
		{555, 5, 1000},//water
		{557, 9, 2000},//earth
		{554, 14, 2000},//fire
		{559, 20, 3500}//body
	};
}