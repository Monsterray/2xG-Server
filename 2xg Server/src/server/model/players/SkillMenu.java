package server.model.players;

/**
 * 
 * @author Monsterray
 *
 */

public class SkillMenu {

	private static final int INTERFACE_ID = 8714;
	private static final int LEVEL_LINE = 8720;
	private static final int TEXT_LINE = 8760;
	private static final int TITLE_LINE = 8716;
	private static final int[][] items = {
		{1321,1323,1325,1327,1329,1331,1333,4153,4587,4151,4718,11694,9747,9748},
		{1117,1115,1119,1125,1121,6916,1123,1127,7399,3751,2513,10348,11724,11720,4720,11283,9753,9754},
		{4153,6528,9750,9751},
		{9768,9769},
		{841,843,849,853,857,1135,861,2499,11235,6522,2501,9185,10330,4214,2503,4734,9756,9757},
		{9759,9760},
		{4099,6916,6889,7399,3387,4675,10338,4712,9762,9763},
		{317,335,331,359,377,371,383,389,395,9801,9802},
		{1351,1349,1511,1353,1521,1355,1519,1357,1359,1517,1515,6739,1513,9807,9808},
		{9783,9784},
		{317,335,331,359,377,371,383,389,395,9798,9799},
		{9804,9805},
		{9780,9781},
		{2349,2351,2355,2353,2357,2359,2361,2363,9795,9796},
		{1265,1267,1436,434,436,438,1269,440,442,1273,453,1271,444,1275,447,449,451,9792,9793},
		{9774,9775},
		{9771,9772},
		{9777,9778},
		{4133,4134,4140,4144,4145,4147,4148,4149,9786,9787},
		{9810,9811},
		{556,558,555,557,554,559,564,562,9075,561,563,560,565,9765,9766},
		{12169,12170},
		{9948,9949},
		{9789,9790},
		{18508,18509}
	};
	private static final String[][] LEVELS = {
		{"1","1","5","10","20","30","40","50","60","70","70","75","99","99"},
		{"1","1","5","10","20","25","30","40","45","60","65","65","70","70","75","99","99"},
		{"50","60","99","99"},
		{"99","99"},
		{"1","5","20","30","30","40","50","50","60","60","60","61","65","70","70","70","99","99"},
		{"99","99"},
		{"20","25","25","40","40","50","65","70","82","99","99"},
		{"1","15","25","30","40","45","80","91","99","99"},
		{"1","1","1","6","15","21","30","31","41","45","60","61","75","99","99"},
		{"99","99"},
		{"1","20","30","35","40","50","76","81","99","99"},
		{"99","99"},
		{"99","99"},
		{"1", "15", "20", "30", "40", "50", "70", "85", "99","99"},
		{"1","1","1","1","1","1","6","15","20","21","30","31","40","40","41","65","70","85","99","99"},
		{"99","99"},
		{"99","99"},
		{"99","99"},
		{"1","10","45","50","65","75","80","85","99","99"},
		{"99","99"},
		{"1","2","5","9","14","20","27","35","40","44","54","65","77","99","99"},
		{"99","99"},
		{"99","99"},
		{"99","99"},
		{"99","99"}
	};	
	private static final String[][] DESCRIPTION = {
		{"Bronze Weapons", "Iron Weapons", "Steel Weapons", "Black Weapons", "Mithril Weapons", "Adamant Weapons", "Rune Weapons", "Granite Maul", "Dragon Weapons", "Abyssal Whip", "Barrows Weapons", "Godswords", "Attack Skillcape", "Attack Skillcape(t)"},
		{"Bronze Armour", "Iron Armour", "Steel Armour", "Black Armour", "Mithril Armour", "Infinity", "Adamant Armour", "Rune Armour","Enchanted", "Fremennik Helmets", "Dragon Armour", "3rd Age Armour", "Bandos", "Armadyl", "Barrows Armour","Dragonfire Shield", "Defence Skillcape", "Defence Skillcape(t)"},
		{"Granite Items", "Obby Maul", "Strength Skillcape", "Strength Skillcape(t)"},
		{"Constitution Skillcape", "Constitution Skillcape(t)"},
		{"Normal Bows","Oak Bows", "Willow Bows", "Maple Bows","Yew Bows", "Green D'hide", "Magic Bows", "Blue D'hide","Dark Bow","Toktz-xil-ul", "Red D'hide","Rune C'bow","3rd age Range","Crystal Bow","Black D'hide","Karil's","Range Skillcape","Range Skillcape(t)"},
		{"Prayer Skillcape", "Prayer Skillcape(t)"},
		{"Mystic ","Infinity ","Mage's book","Enchanted ","Splitbark ","Ancient staff","3rd age mage","Ahrims","Magic Skillcape","Magic Skillcape(t)"},
		{"Shrimp", "Trout", "Salmon", "Tuna", "Lobster", "Swordfish", "Shark", "Manta Ray","Sea Turtle","Cooking Skillcape","Cooking Skillcape(t)"},
		{"Bronze Axe","Iron Axe","Logs","Steel Axe","Oak Logs","Mithril Axe","Willow Logs","Adamant Axe","Rune Axe","Maple Logs","Yew Logs","Dragon Axe","Magic Logs","Woodcutting Skillcape", "Woodcutting Skillcape(t)"},
		{"Normal bows", "Oak bows", "Willow bows", "Maple bows", "Yew bows", "Rune arrow", "Magic shortbow", "Magic longbow", "Fletching Skillcape", "Fletching Skillcape(t)"},
		{"Shrimp", "Trout", "Salmon", "Tuna", "Lobster", "Swordfish", "Shark", "Manta Ray","Sea Turtle","Fishing Skillcape", "Fishing Skillcape(t)"},
		{"Firemakeing Skillcape", "Firemakeing Skillcape(t)"},
		{"Crafting Skillcape", "Crafting Skillcape(t)"},
		{"Bronze", "Iron", "Silver", "Steel", "Gold", "Mithril", "Adamant", "Rune", "Smithing Skillcape", "Smithing Skillcape(t)"},
		{"Bronze pickaxe","Iron pickaxe","Rune essence","Clay","Copper","Tin Ore","steel pickaxe","Iron ore","Silver ore","Mithril pickaxe","Coal ore","Adamant pickaxe","Gold ore","Rune pickaxe","Mithril ore","Adamanetite ore","Runite ore", "Mining Skillcape", "Mining Skillcape(t)"},
		{"Herblore Skillcape", "Herblore Skillcape(t)"},
		{"Agility Skillcape", "Agility Skillcape(t)"},
		{"Thieving Skillcape", "Thieving Skillcape(t)"},
		{"Crawling Hands", "Banshee", "Infernal Mage", "Bloodveld", "Dust Devil", "Gargoyal", "Nechryael", "Abyssal Demon","Slayer Skillcape", "Slayer Skillcape(t)"},
		{"Farming Skillcape", "Farming Skillcape(t)"},
		{"Air rune","Mind rune","Water rune","Earth rune","Fire rune","Body rune","Cosmic rune","Chaos rune","Astral rune","Nature rune","Law rune","Death rune","Blood rune","Runecrafting Skillcape", "Runecrafting Skillcape(t)"},
		{"Summoning Skillcape", "Summoning Skillcape(t)"},
		{"Hunter Skillcape", "Hunter Skillcape(t)"},
		{"Construction Skillcape", "Construction Skillcape(t)"},
		{"Dungeoneering Skillcape", "Dungeoneering Skillcape(t)"}
	};

	private static final String[] SKILLS = {"Attack","Defence","Strength","Hitpoints","Ranged","Prayer","Magic","Cooking", "Woodcutting", "Fletching", "Fishing", "Firemakeing", "Crafting", "Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting", "Summoning", "Hunter", "Construction", "Dungeoneering"};
	
	public static void openInterface(Client c, int skillType) {
		removeSidebars(c);
		writeItems(c,skillType);
		writeText(c,skillType);
		c.getPA().showInterface(INTERFACE_ID);
	}
	
	private static void removeSidebars(Client c) {
		int[] temp = {8849,8846,8823,8824,8827,8837,8840,8843,8859,8862,8865,15303,15306,15309};
		for (int j = 0; j < temp.length; j++) {
			c.getPA().sendFrame126("",temp[j]);
		}
	}
	
	private static void writeItems(Client c, int skillType) {
		synchronized (c) {
			c.outStream.createFrameVarSizeWord(53);
			c.outStream.writeWord(8847);
			c.outStream.writeWord(items[skillType].length);		
			for (int j = 0; j < items[skillType].length; j++) {
				c.outStream.writeByte(1);
				if (items[skillType][j] > 0) {
					c.outStream.writeWordBigEndianA(items[skillType][j] + 1);
				} else {
					c.outStream.writeWordBigEndianA(0);
				}
			}
			c.outStream.endFrameVarSizeWord();
			c.flushOutStream();
		}
	}
	
	private static void writeText(Client c, int skillType) {
		//int skillCapeLevel = LEVEL_LINE;
		c.getPA().sendFrame126("One", 8846);
		c.getPA().sendFrame126("Two", 8823);
		c.getPA().sendFrame126("Three", 8824);
		c.getPA().sendFrame126("Four", 8827);
		c.getPA().sendFrame126("Five", 8837);
		c.getPA().sendFrame126("Six", 8840);
		c.getPA().sendFrame126("Seven", 8843);
		c.getPA().sendFrame126("Eight", 8859);
		c.getPA().sendFrame126("Nine", 8862);
		c.getPA().sendFrame126("Ten", 8865);
		c.getPA().sendFrame126("Eleven", 15303);
		c.getPA().sendFrame126("Twelve", 15306);
		c.getPA().sendFrame126("Thirteen", 15309);
		c.getPA().sendFrame126(SKILLS[skillType], TITLE_LINE);
		for (int j = 0; j < LEVELS[skillType].length; j++) {
			//skillCapeLevel = LEVEL_LINE + j;
			c.getPA().sendFrame126(LEVELS[skillType][j], LEVEL_LINE + j);
		}
		//for(int i = 0; i < (c.getPA().skillCapes.length / 2); i++) {
			//c.getPA().sendFrame126("99", skillCapeLevel);
		// }
		for (int j = 0; j < DESCRIPTION[skillType].length; j++) {
			c.getPA().sendFrame126(DESCRIPTION[skillType][j], TEXT_LINE + j);
		}
		
		for (int j = DESCRIPTION[skillType].length; j < 30; j++) {
			c.getPA().sendFrame126("", LEVEL_LINE + j);
		}
		for (int j = LEVELS[skillType].length; j < 30; j++) {
			c.getPA().sendFrame126("", TEXT_LINE + j);
		}
	}
}