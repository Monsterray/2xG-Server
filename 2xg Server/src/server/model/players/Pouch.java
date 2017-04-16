	package server.model.players;

	import server.util.Misc;


	public class Pouch {

	
	
	/**
	 * @author 
	 */
	
	
	
	/**/
	/*** This class is made for the pouch making interface, this class is made by Gabbe by hand, took a while to find everything!
	/*** Everything does work perfectly. I need to find correct GFX & Anim like rs has though.
	Oh and you might be wondering why i did it this way, i did it to make sure every pouch works 100%!
	***/
	
	
	
	/**/
	/***Update: Fixed one click pouch and it will create all in the inventory! - Finaly!
	/***Update 2: Fixed the 'spamming' message.
	/***Left to do: NOTHING
	/**/
		
		public static void makePouch(Client c, int packetType, int packetSize) {
			int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		 int POUCH = 12155;
		int SHARD = 18016; // 14015
		int GCHARM = 12158; // gold charm
		int GRCHARM = 12159; // green charm
		int CCHARM = 12160; // crimson charm summon
		int BCHARM = 12163; //BLUE CHARm
		
			String[][] summoningPouchData = { // This is the 'database' for the XP/ i use this for other things too. // Gabbe
				// Summoning pouch making DATA By Gabbe and creds to arrowzflame
				// Pouch id, pouch charm, item1, Shardamount, LVL, Spec scroll, NPCID
				//TODO need to add NPCDEF's
				{ "Spirit wolf pouch", "Gold Charm", "Wolf bones", "7", "1",
						"Howl scroll" , "6829"},
				{ "Dreadfowl pouch", "Gold Charm", "Raw chicken", "8", "4",
						"Dreadfowl strike scroll" , "6825"},
				{ "Spirit spider pouch", "Gold Charm", "Spider carcass", "8", "10",
						"Egg spawn scroll" , "6841"},
				{ "Thorny Snail pouch", "Gold Charm", "Thin snail", "9", "13",
						"Slime spray scroll" , "6806"},
				{ "Granite Crab pouch", "Gold Charm", "Iron ore", "7", "16",
						"Stony shell scroll" , "6796"},
				{ "Mosquito pouch", "Gold Charm", "Proboscis", "1", "17",
						"Pester scroll" , "7331"},
				{ "Desert wyrm pouch", "Green Charm", "Bucket of sand", "45", "18",
						"Electric lash scroll" , "6831"},
				{ "Spirit Scorpion pouch", "Crimson Charm", "Bronze claws", "57",
						"19", "Venom shot scroll" , "6837"},
				{ "Spirit tz-kih pouch", "crimson charm", "Obsidian charm", "64",
						"22", "Fireball assault scroll" , "7361"},
				{ "Albino rat pouch", "Blue Charm", "Raw rat meat", "75", "23",
						"Cheese feast scroll" , "6847"},
				{ "Spirit kalphite pouch", "blue Charm", "potato cactus", "51",
						"25", "Sandstorm scroll" , "6994"},
				{ "Compost mound pouch", "Green charm", "compost", "47", "28",
						"Generate compost scroll" , "6871"},
				{ "Giant chinchompa pouch", "Blue Charm", "Chinchompa", "84", "29",
						"Explode scroll" , "7353"},
				{ "Vampire bat pouch", "Crimson Charm", "Vampire dust", "81", "31",
						"Vampire touch scroll" , "6835"},
				{ "Honey badger pouch", "Crimson Charm", "Honeycomb", "84", "32",
						"Insane ferocity scroll" , "6845"},
				{ "Beaver pouch", "Green Charm", "Willow logs", "72", "33",
						"Multichop scroll" , "6807"},
				{ "Void ravager pouch", "green Charm", "Ravager Charm", "74", "34",
						"Call to arms scroll" , "7370"},
				{ "Void shifter pouch", "blue charm", "Shifter charm", "74", "34",
						"Call to arms scroll" , "7367"},
				{ "void spinner pouch", "blue Charm", "spinner Charm", "74", "34",
						"Call to arms scroll" , "7333"},
				{ "Void Torcher pouch", "blue Charm", "Torcher Charm", "74", "34",
						"Call to arms scroll" , "7351"},
				{ "Bronze minotaur pouch", "Blue Charm", "Bronze bar", "102", "36",
						"Bronze bull rush scroll" , "6853"},
				{ "Bull ant pouch", "gold Charm", "Marigolds", "11", "40",
						"Unburden scroll" , "6867"},
				{ "Macaw pouch", "green Charm", "Clean guam", "78", "41",
						"Herbcall scroll" , "6851"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Evil turnip pouch", "crimson Charm", "Carved turnip", "104",
						"42", "Evil flames scroll" , "6833"},
				{ "Iron minotaur pouch", "Blue Charm", "Iron bar", "125", "46",
						"Iron bull rush scroll" , "6855"},
				{ "Pyrelord pouch", "Crimson Charm", "Tinderbox", "111", "46",
						"Immense heat scroll" , "7377"},
				{ "Magpie pouch", "green Charm", "Gold ring", "88", "47",
						"Thieving fingers scroll" , "6824"},
							
				{ "Bloated leech pouch", "Crimson Charm", "Raw beef", "117", "49",
						"Blood drain scroll" , "6843"},
				{ "Spirit terrorbird pouch", "Gold Charm", "Raw bird meat", "12",
						"52", "Tireless run scroll" , "3596"},
				{ "Abyssal parasite pouch", "green Charm", "Abyssal charm", "106",
						"54", "Abyssal drain scroll" , "6818"},
				{ "Spirit jelly pouch", "blue Charm", "Jug of water", "151", "55",
						"Dissolve scroll" , "6922"},
				{ "Steel minotaur pouch", "blue Charm", "steel bar", "141", "56",
						"Fish rain scroll" , "6857"},
				{ "Ibis pouch", "green Charm", "Harpoon", "109", "56",
						"Steel bull rush scroll" , "6991"},
				{ "Spirit Graahk pouch", "blue Charm", "graahk fur", "154", "57",
						"Ambush scroll" , "3588"},
				{ "Spirit Kyatt pouch", "blue Charm", "Kyatt fur", "153", "57",
						"Rending scroll" , "7365"},
				{ "Spirit larupia pouch", "blue Charm", "larupia fur", "155", "57",
						"Goad scroll" , "7337"},
				{ "Karamthulhu overlord pouch", "blue Charm", "Empty fishbowl",
						"144", "58", "Doomsphere scroll" , "6809"},
				{ "Smoke devil pouch", "Crimson Charm", "Goat horn dust", "141",
						"61", "Dust cloud scroll" , "6865"},
				{ "Abyssal lurker", "green Charm", "Abyssal charm", "119", "62",
						"Abyssal stealth scroll" , "6820"},
				{ "Spirit cobra pouch", "Crimson Charm", "Snake hide", "116", "63",
						"Ophidian incubation scroll" , "6802"},
				{ "Stranger plant pouch", "Crimson Charm", "Bagged plant", "128",
						"64", "Poisonous blast scroll" , "6827"},
				{ "Mithril minotaur pouch", "Blue Charm", "Mithril bar", "152",
						"66", "Mithril bull rush scroll" , "6859"},
				{ "Barker toad pouch", "Gold Charm", "Swamp toad", "11", "66",
						"Toad bark scroll" , "6889"},
				{ "War tortoise pouch", "Gold Charm", "Tortoise shell", "1", "67",
						"Testudo scroll" , "6815"},
				{ "Bunyip pouch", "Green Charm", "Raw shark", "110", "68",
						"Swallow whole scroll" , "6813"},
				{ "Fruit bat pouch", "Green Charm", "Banana", "130", "69",
						"Fruitfall scroll" , "6817"},
				{ "Ravenous Locust pouch", "Crimson Charm", "Pot of Flour", "79",
						"70", "Famine scroll" , "7372"},
				{ "Arctic bear pouch", "Gold Charm", "Polar kebbit fur", "14",
						"71", "Arctic blast scroll" , "6839"},
			{ "Phoenix pouch", "Crimson Charm", "Phoenix Quill", "165", "72",
						"Phoenix unknown scroll" , "1911"},
					
				{ "Obsidian Golem pouch", "Blue Charm", "Obsidian Charm", "195",
						"73", "Volcanic strength scroll" , "7345"},
				{ "Granite lobster pouch", "Crimson Charm", "Granite (500g)",
						"166", "74", "Crushing claw scroll" , "6849"},
				{ "Praying mantis pouch", "Crimson Charm", "Flowers", "168", "75",
						"Mantis strike scroll" , "6798"},
				{ "Adamant minotaur pouch", "Blue Charm", "Adamant Bar", "144",
						"76", "Inferno scroll" , "6861"},
				{ "Forge Regent pouch", "Green Charm", "Ruby harvest", "141", "76",
						"Adamant bull rush scroll" , "7335"},
				{ "Talon Beast pouch", "Crimson Charm", "Talon Beast charm", "174",
						"77", "Deadly claw scroll" , "7347"},
				{ "Giant ent pouch", "Green Charm", "Willow branch", "124", "78",
						"Acorn missile scroll" , "6800"},
				{ "Fire titan pouch", "Blue Charm", "Fire talisman", "198", "79",
						"Titan's constitution scroll" , "7355"},
				{ "Ice titan pouch", "Blue Charm", "Water talisman", "198", "79",
						"Titan's constitution scroll" , "7359"},
				{ "Moss titan pouch", "Blue Charm", "Earth talisman", "202", "79",
						"Titan's constitution scroll" , "7357"},
				{ "Hydra pouch", "Green Charm", "Water orb", "128", "80",
						"Regrowth scroll" , "6811"},
				{ "Spirit dagannoth", "Crimson Charm", "Dagannoth hide", "1", "83",
						"Spike shot scroll" , "6804"},
				{ "Lava titan pouch", "Blue Charm", "Obsidian Charm", "219", "83",
						"Ebon thunder scroll" , "7341"},
				{ "Swamp titan pouch", "Blue Charm", "Swamp lizard", "150", "85",
						"Swamp plague scroll" , "7329"},
				{ "Rune minotaur pouch", "Blue Charm", "Rune bar", "1", "86",
						"Rune bull rush scroll" , "6863"},
				{ "Unicorn stallion pouch", "green Charm", "Unicorn Horn", "140",
						"88", "Healing aura scroll" , "3592"},
				{ "Geyser titan pouch", "blue Charm", "Water talisman", "222",
						"89", "Boil scroll" , "7339"},
				{ "Wolpertinger pouch", "crimson Charm", "Raw rabbit", "203", "92",
						"Magic focus scroll" , "3593"},
				{ "Abyssal titan pouch", "green Charm", "Abyssal charm", "113",
						"93", "Essence shipment scroll" , "7349"},
				{ "Iron titan pouch", "crimson Charm", "Iron platebody", "198",
						"95", "Iron within scroll" , "7375"},
				{ "Pack yak pouch", "Crimson Charm", "Yak-hide", "211", "96",
						"Winter storage scroll" , "3594"},
				{ "Steel titan pouch", "Blue Charm", "Steel platebody", "178",
						"99", "Steel of legends scroll", "3591"},

		};

	switch (actionButtonId){


					case 91184: // START OF SUMMONING POUCHES // Wolf
					c.hasSentMessage = false;
							c.hasSentMessage = false;
							for (int i = 0; i < summoningPouchData.length; i++) {
				if (c.getItems().playerHasItem(12158, 1) && c.getItems().playerHasItem(2859, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 7) && c.playerLevel[21] >= 1) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
	c.getPA().addSkillXP(2000, 21);
				//c.sendMessage("You created ; Spirit Wolf Pouch");
				c.getItems().deleteItem(2859, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 7);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12047, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 1 Summoning & 1 Gold Charm & 1 Wolf Bone & 7 Spirit Shards & 1 Pouch.");
				c.hasSentMessage = true;
				}
				} 
				break;
				
				case 91192: // Dreadfowl chicken pouch
				c.hasSentMessage = false;
				for (int i = 0; i < summoningPouchData.length; i++) {
				if (c.getItems().playerHasItem(12158, 1) && c.getItems().playerHasItem(2138, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 8) && c.playerLevel[21] >= 4) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(4000, 21);
				//c.sendMessage("You created ; DreadFowl Pouch");
				c.getItems().deleteItem(2138, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 8);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12043, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 4 Summoning & 1 Gold Charm & 1 Raw Chicken & 8 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; } } break;
				
							case 91200: // spirit spider pouch
							c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {
				if (c.getItems().playerHasItem(12158, 1) && c.getItems().playerHasItem(6291, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 8) && c.playerLevel[21] >= 10) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(7000, 21);
				//c.sendMessage("You created ; Spirit Spider Pouch");
				c.getItems().deleteItem(6291, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 8);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12059, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 10 Summoning & 1 Gold Charm & 1 Spider carcass & 8 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true;
										} 
										} break;
				
										case 91208: // thorny snail
										c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {
				if (c.getItems().playerHasItem(12158, 1) && c.getItems().playerHasItem(3369, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 9) && c.playerLevel[21] >= 13) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(9000, 21);
				//c.sendMessage("You created ; Thorny Snail Pouch");
				c.getItems().deleteItem(3369, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 9);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12019, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 13 Summoning & 1 Gold Charm & 1 Thin snail & 9 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										}
										} 
										break;
				
				
				case 91216: // Granite crab
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {									
				if (c.getItems().playerHasItem(12158, 1) && c.getItems().playerHasItem(440, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 7) && c.playerLevel[21] >= 16) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(10000, 21);
				//c.sendMessage("You created ; Granite Crab Pouch");
				c.getItems().deleteItem(440, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 7);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12009, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 16 Summoning & 1 Gold Charm & 1 Iron Ore & 7 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										}	
										}
										break;
				
				case 91224: // Mosquito
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(12158, 1) && c.getItems().playerHasItem(6319, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 20) && c.playerLevel[21] >= 17) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(10500, 21);
				//c.sendMessage("You created ; Mosquito Pouch");
				c.getItems().deleteItem(6319, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 20);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12778, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 17 Summoning & 1 Gold Charm & 1 Proboscis & 20 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										}
										} 
										break;
				
							case 91232: // Desert Wyrm
							c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(1783, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 45) && c.playerLevel[21] >= 18) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(11300, 21);
				//c.sendMessage("You created ; Desert Wyrm Pouch");
				c.getItems().deleteItem(1783, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 45);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12049, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 18 Summoning & 1 Gold Charm & 1 Bucket of Sand & 45 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
										case 91240: // Spirit Scorpion
										c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(3095, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 57) && c.playerLevel[21] >= 19) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(12000, 21);
				//c.sendMessage("You created ; Spirit Scorpion Pouch");
				c.getItems().deleteItem(3095, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 57);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12055, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 19 Summoning & 1 Crimson Charm & 1 Bronze Claw & 57 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
				
						case 91248: // Spirit tz-kih pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(12168, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 64) && c.playerLevel[21] >= 22) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(13000, 21);
				//c.sendMessage("You created ; Spirit Tz-kih Pouch");
				c.getItems().deleteItem(12168, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 64);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12808, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 22 Summoning & 1 Crimson Charm & 1 Obsidian Charm & 64 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
									case 92000: // Albino Rat pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(12168, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 64) && c.playerLevel[21] >= 23) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(14300, 21);
				//c.sendMessage("You created ; Albino Rat Pouch");
				c.getItems().deleteItem(12168, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 64);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12808, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 22 Summoning & 1 Crimson Charm & 1 Raw Rat meat & 64 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										}										
										} 
										break;
				
									case 92008: // Spirit Kalphite
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(3138, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 51) && c.playerLevel[21] >= 25) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(16000, 21);
				//c.sendMessage("You created ; Spirit Kalphite Pouch");
				c.getItems().deleteItem(3138, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 51);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12063, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 25 Summoning & 1 Blue Charm & 1 Potato Cactus & 51 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
												case 92016: // Compost Mound
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(6032, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 47) && c.playerLevel[21] >= 28) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(13200, 21);
				//c.sendMessage("You created ; Compost Mound Pouch");
				c.getItems().deleteItem(6032, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 47);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12091, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 28 Summoning & 1 Green Charm & 1 Compost & 47 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
			case 92024: // Giant ChinChompa Pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(9976, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 84) && c.playerLevel[21] >= 29) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(16600, 21);
				//c.sendMessage("You created ; Giant Chinchompa Pouch");
				c.getItems().deleteItem(9976, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 84);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12091, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 29 Summoning & 1 Blue Charm & 1 Chinchompa & 84 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
						case 92032: // Vampire Bat Pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(3325, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 81) && c.playerLevel[21] >= 31) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(14000, 21);
				//c.sendMessage("You created ; Vampire Bat Pouch");
				c.getItems().deleteItem(3325, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 81);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12053, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 31 Summoning & 1 Crimson Charm & 1 Vampire Dust & 81 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
									case 92040: // Honey Badger Pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(12156, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 84) && c.playerLevel[21] >= 32) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(14200, 21);
				//c.sendMessage("You created ; Honey Badger Pouch");
				c.getItems().deleteItem(12156, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 84);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12065, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 32 Summoning & 1 Crimson Charm & 1 HoneyComb & 84 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
												case 92048: // Beaver Pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(1519, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 72) && c.playerLevel[21] >= 33) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(14000, 21);
				//c.sendMessage("You created ; Beaver Pouch");
				c.getItems().deleteItem(1519, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 72);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12021, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 33 Summoning & 1 Green Charm	& 1 Willow Log & 72 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
										
																						case 92056: // Void ravager
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(12164, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 74) && c.playerLevel[21] >= 34) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(14200, 21);
				//c.sendMessage("You created ; Void Ravager Pouch");
				c.getItems().deleteItem(12164, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 74);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12818, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 34 Summoning & 1 Green Charm	& 1 Ravager Charm & 74 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
																																  case 92064: // Void Spinner pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(12166, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 74) && c.playerLevel[21] >= 34) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(14200, 21);
				//c.sendMessage("You created ; Void Spinner Pouch");
				c.getItems().deleteItem(12166, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 74);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12780, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 34 Summoning & 1 Blue Charm	& 1 Spinner Charm & 74 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
										  case 92072: //Void torcher pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(12167, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 74) && c.playerLevel[21] >= 34) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(14200, 21);
				//c.sendMessage("You created ; Void Torcher Pouch");
				c.getItems().deleteItem(12167, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 74);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12798, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 34 Summoning & 1 Blue Charm	& 1 Torcher Charm & 74 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
														  case 92080: //Void Shifter pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(12165, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 74) && c.playerLevel[21] >= 34) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(14200, 21);
				//c.sendMessage("You created ; Void Shifter Pouch");
				c.getItems().deleteItem(12165, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 74);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12814, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 34 Summoning & 1 Blue Charm	& 1 Shifter Charm & 74 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
				 case 92088: //Bronze minotaur
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(2349, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 102) && c.playerLevel[21] >= 36) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(16900, 21);
				//c.sendMessage("You created ; Bronze Minotaur Pouch");
				c.getItems().deleteItem(2349, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 102);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12073, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 36 Summoning & 1 Blue Charm	& 1 Bronze Bar & 102 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
														 case 92096: //Bull ant
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(6010, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 11) && c.playerLevel[21] >= 40) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(14600, 21);
				//c.sendMessage("You created ; Bull ant Pouch");
				c.getItems().deleteItem(6010, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 11);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12073, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 40 Summoning & 1 Gold Charm & 1 Marigolds & 11 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
			case 92104: //Macaw
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 78) && c.playerLevel[21] >= 41) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(15300, 21);
				//c.sendMessage("You created ; Macaw Pouch");
				c.getItems().deleteItem(249, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 78);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12071, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 41 Summoning & 1 Green Charm & 1 Clean Guam & 78 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
				case 92112: //Macaw
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(12153, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 104) && c.playerLevel[21] >= 42) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(15900, 21);
				//c.sendMessage("You created ; Macaw Pouch");
				c.getItems().deleteItem(12153, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 104);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12051, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 42 Summoning & 1 Crimson Charm & 1 Carved Evil Turnip & 104 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
										
										
										
										
										case 92120:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
											case 92128:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
											case 92136:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
											case 92144:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
											case 92152:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
											case 92160:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
											case 92168:
										c.sendMessage("Sorry, but this pouch is unavailable.");
										break;
										
										
										
										
														case 92176: //Iron minotaur
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 125) && c.playerLevel[21] >= 46) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(18300, 21);
				//c.sendMessage("You created ; Iron minotaur Pouch");
				c.getItems().deleteItem(2351, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 125);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12075, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 46 Summoning & 1 Blue Charm & 1 Iron Bar & 125 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
	case 92184: //pyrelord
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(590, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 111) && c.playerLevel[21] >= 46) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(16500, 21);
				//c.sendMessage("You created ; PyreLord Pouch");
				c.getItems().deleteItem(590, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 111);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12816, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 46 Summoning & 1 Crimson Charm & 1 Tinderbox & 111 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
										
										
										
										
										
											case 92192: //Magpie
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(1635, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 88) && c.playerLevel[21] >= 47) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(15500, 21);
				//c.sendMessage("You created ; Magipie Pouch");
				c.getItems().deleteItem(1635, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 88);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12041, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 47 Summoning & 1 Green Charm & 1 Gold Ring & 88 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
			case 92200: //Bloated Leech Pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(2132, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 117) && c.playerLevel[21] >= 49) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(17500, 21);
				//c.sendMessage("You created ; Bloated Leech Pouch");
				c.getItems().deleteItem(2132, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 117);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12061, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 49 Summoning & 1 Crimson Charm & 1 Raw beef & 117 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
				
				
							case 92208: //Spirit terrorbird
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(9978, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 12) && c.playerLevel[21] >= 52) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(16300, 21);
				//c.sendMessage("You created ; Spirit Terrorbird Pouch");
				c.getItems().deleteItem(9978, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 12);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12007, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 52 Summoning & 1 Gold Charm & 1 Raw Bird Meat & 12 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
			case 92216: //Abbysal parasite
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(12161, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 106) && c.playerLevel[21] >= 54) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(16000, 21);
				//c.sendMessage("You created ; Abyssal Parasite Pouch");
				c.getItems().deleteItem(12161, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 106);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12035, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 54 Summoning & 1 Green Charm & 1 Abyssal Charm & 106 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
		case 92224: // Spirit Jelly
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(1937, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 151) && c.playerLevel[21] >= 55) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(18700, 21);
				//c.sendMessage("You created ; Spirit Jelly Pouch");
				c.getItems().deleteItem(1937, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 151);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12027, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 55 Summoning & 1 Blue Charm & 1 Jug of water & 151 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
			case 92232: // Steel minotaur pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(2353, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 141) && c.playerLevel[21] >= 56) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19200, 21);
				//c.sendMessage("You created ; Steel Minotaur Pouch");
				c.getItems().deleteItem(2353, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 141);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12077, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 56 Summoning & 1 Blue Charm & 1 Steel Bar & 151 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
			case 92240: // Ibis pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(311, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 109) && c.playerLevel[21] >= 56) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(17500, 21);
				//c.sendMessage("You created ; Ibis Pouch");
				c.getItems().deleteItem(311, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 109);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12077, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 56 Summoning & 1 Green Charm & 1 Harpoon & 109 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
	case 92248: // Spirit Graahk pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(10099, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 154) && c.playerLevel[21] >= 57) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19400, 21);
				//c.sendMessage("You created ; Spirit Graahk Pouch");
				c.getItems().deleteItem(10099, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 154);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12810, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 57 Summoning & 1 Blue Charm & 1 Graahk Fur & 154 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
			case 93000: // Spirit Kyatt pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(10103, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 153) && c.playerLevel[21] >= 57) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19400, 21);
				//c.sendMessage("You created ; Spirit Kyatt Pouch");
				c.getItems().deleteItem(10103, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 153);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12812, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 57 Summoning & 1 Blue Charm & 1 Kyatt Fur & 153 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
													case 93008: // Spirit  larupia pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(10095, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 155) && c.playerLevel[21] >= 57) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19400, 21);
				//c.sendMessage("You created ; Spirit Larupia Pouch");
				c.getItems().deleteItem(10095, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 155);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12784, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 57 Summoning & 1 Blue Charm & 1 Larupia Fur & 155 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
case 93016: // Spirit Karamthulhu overlord pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(6723, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 144) && c.playerLevel[21] >= 58) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19700, 21);
				//c.sendMessage("You created ; Karamthulhu Overlord Pouch");
				c.getItems().deleteItem(6723, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 144);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12023, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 58 Summoning & 1 Blue Charm & 1 Empty fishbowl & 144 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
										
										
										
										
			case 93024: //  Smoke devil pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(9736, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 141) && c.playerLevel[21] >= 61) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(18300, 21);
				//c.sendMessage("You created ; Smoke Devil Pouch");
				c.getItems().deleteItem(9736, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 141);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12085, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 61 Summoning & 1 Crimson Charm & 1 Goat horn Dust & 141 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;			



			case 93032: //  abyssal lurker
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(12161, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 119) && c.playerLevel[21] >= 62) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(17200, 21);
				//c.sendMessage("You created ; Abyssal lurker Pouch");
				c.getItems().deleteItem(12161, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 119);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12037, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 62 Summoning & 1 Green Charm & 1 Abyssal charm & 119 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;											
										
										
										
									case 93040: //  Spirit Cobra
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(7801, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 116) && c.playerLevel[21] >= 63) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(18900, 21);
				//c.sendMessage("You created ; Spirit Cobra Pouch");
				c.getItems().deleteItem(7801, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 116);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12015, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 63 Summoning & 1 Crimson Charm & 1 Snake hide & 116 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;			

									case 93048: //  Stranger Plant pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(8431, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 128) && c.playerLevel[21] >= 64) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19100, 21);
				//c.sendMessage("You created ; Stranger plant Pouch");
				c.getItems().deleteItem(8431, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 128);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12045, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 64 Summoning & 1 Crimson Charm & 1 Bagged Plant 1 & 128 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;	


									case 93056: //  Mithril Minotaur
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(2359, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 152) && c.playerLevel[21] >= 66) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(20100, 21);
				//c.sendMessage("You created ; Mithril Minotaur Pouch");
				c.getItems().deleteItem(2359, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 152);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12079, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 66 Summoning & 1 Blue Charm & 1 Mithril Bar & 152 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;		

									case 93064: // Barker toad pouch
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(2150, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 11) && c.playerLevel[21] >= 66) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(18900, 21);
				//c.sendMessage("You created ; Barker Toad Pouch");
				c.getItems().deleteItem(2150, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 11);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12123, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 66 Summoning & 1 Gold Charm & 1 Swamp Toad & 11 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;		


									case 93072: // Spirit War tortoise
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(7939, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 131) && c.playerLevel[21] >= 67) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19000, 21);
				//c.sendMessage("You created ; War Tortoise Pouch");
				c.getItems().deleteItem(7939, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 131);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12031, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 67 Summoning & 1 Gold Charm & 1 Tortoise shell & 131 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;		

									case 93080: // Bunyip
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(383, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 110) && c.playerLevel[21] >= 68) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19800, 21);
				//c.sendMessage("You created ; Bunyip Pouch");
				c.getItems().deleteItem(383, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 110);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12029, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 68 Summoning & 1 Green Charm & 1 Raw shark & 110 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;	


									case 93088: // Fruit Bat
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(1963, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 130) && c.playerLevel[21] >= 69) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19500, 21);
				//c.sendMessage("You created ; Fruit Bat Pouch");
				c.getItems().deleteItem(1963, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 130);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12033, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 69 Summoning & 1 Green Charm & 1 Banana & 130 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;	


									case 93096: // Ravenous Locust
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(1933, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 79) && c.playerLevel[21] >= 70) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19900, 21);
				//c.sendMessage("You created ; Ravenous Locust Pouch");
				c.getItems().deleteItem(1933, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 79);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12820, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 70 Summoning & 1 Crimson Charm & 1 Pot of Flour & 79 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;					

									case 93104: // Arctic bear
			c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(GCHARM, 1) && c.getItems().playerHasItem(10117, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 80) && c.playerLevel[21] >= 71) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(19900, 21);
				//c.sendMessage("You created ; Arctic Bear Pouch");
				c.getItems().deleteItem(10117, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 80);
				 c.getItems().deleteItem(GCHARM, 1);
				 c.getItems().addItem(12057, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 71 Summoning & 1 Gold Charm & 1 Polar kebbit fur & 80 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} 
										break;	

									case 93112: // Phoenix
									c.sendMessage("Sorry, but this pouch is unavailable.");
			/*c.hasSentMessage = false;
			for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(14616, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 165) && c.playerLevel[21] >= 72) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
				c.getPA().addSkillXP(summoningPouchData[i][4]) * 900, 21);
				//c.sendMessage("You created ; Phoenix Pouch");
				c.getItems().deleteItem(14616, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 165);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(14623, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 72 Summoning & 1 Crimson Charm & 1 Phoenix Quill & 165 Spirit Shards & 1 Pouch.");
				
										c.hasSentMessage = true; 
										} 
										} */
										break;		
										
									
									case 93120: //Obsidian Golem Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(12168, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 195) && c.playerLevel[21] >= 73) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(20900, 21);
				//c.sendMessage("You created ; Obsidian Golem Pouch");
				c.getItems().deleteItem(12168, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 195);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12792, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 73 Summoning & 1 Blue Charm & 1 Obsidian Charm & 195 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
									
									
										
										
case 93128: //Granite Lobster Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(6979, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 166) && c.playerLevel[21] >= 74) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(21100, 21);
				//c.sendMessage("You created ; Granite Lobster Pouch");
				c.getItems().deleteItem(6979, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 166);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(20500, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 74 Summoning & 1 Crimson Charm & 1 Granite(500G) & 166 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
										
										
		case 93136: //Praying mantis pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(2460, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 168) && c.playerLevel[21] >= 75) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(21250, 21);
				//c.sendMessage("You created ; Praying Mantis Pouch");
				c.getItems().deleteItem(2460, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 168);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12011, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 75 Summoning & 1 Crimson Charm & 1 Flowers & 168 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;		
								
											case 93144: //Adamant Minotaur pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(2361, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 144) && c.playerLevel[21] >= 76) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(22200, 21);
				//c.sendMessage("You created ; Adamant Minotaur Pouch");
				c.getItems().deleteItem(2361, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 144);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12081, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 76 Summoning & 1 Blue Charm & 1 Adamant Bar & 144 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
								
								
								
			case 93152: //Forge Regent pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(10020, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 141) && c.playerLevel[21] >= 76) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(21000, 21);
				//c.sendMessage("You created ; Forge Regent Pouch");
				c.getItems().deleteItem(10020, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 141);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12782, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 76 Summoning & 1 Green Charm & 1 Ruby harvest & 141 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
										
								
												case 93160: //Talon Beast pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(12162, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 174) && c.playerLevel[21] >= 77) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(22000, 21);
				//c.sendMessage("You created ; Talon Beast Pouch");
				c.getItems().deleteItem(12162, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 174);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12794, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 77 Summoning & 1 Crimson Charm & 1 Talon Beast charm & 174 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
										

				case 93168: //Giant ent Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(5933, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 124) && c.playerLevel[21] >= 78) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(21500, 21);
				//c.sendMessage("You created ; Giant Ent Pouch");
				c.getItems().deleteItem(5933, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 124);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12013, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 78 Summoning & 1 Green Charm & 1 Willow branch & 124 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	



								
										
				case 93176: //Fire Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(1442, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 198) && c.playerLevel[21] >= 79) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(22900, 21);
				//c.sendMessage("You created ; Fire Titan Pouch");
				c.getItems().deleteItem(1442, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 198);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12802, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 79 Summoning & 1 Blue Charm & 1 Fire talisman & 198 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
										
												
											case 93184: //Moss Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(1440, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 198) && c.playerLevel[21] >= 79) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(23100, 21);
				//c.sendMessage("You created ; Moss Titan Pouch");
				c.getItems().deleteItem(1440, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 198);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12804, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 79 Summoning & 1 Blue Charm & 1 Earth talisman & 198 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
						
						
					case 93192: //Ice Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(1444, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 198) && c.playerLevel[21] >= 79) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(23100, 21);
				//c.sendMessage("You created ; Ice Titan Pouch");
				c.getItems().deleteItem(1444, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 198);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12806, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 79 Summoning & 1 Blue Charm & 1 Water talisman & 198 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
						
						
						
								case 93200: //Hyda pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(571, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 128) && c.playerLevel[21] >= 80) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(22100, 21);
				//c.sendMessage("You created ; Hydra Pouch");
				c.getItems().deleteItem(571, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 128);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12025, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 80 Summoning & 1 Green Charm & 1 Water orb & 128 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	

						
										
								case 93208: //spirit dagannoth
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(6155, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 122) && c.playerLevel[21] >= 83) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(22900, 21);
				//c.sendMessage("You created ; Sprit Dagannoth Pouch");
				c.getItems().deleteItem(6155, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 122);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12017, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 83 Summoning & 1 Crimson Charm & 1 Dagannoth hide & 122 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;					
										
			case 93216: //Lava Titan pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(12168, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 219) && c.playerLevel[21] >= 83) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(23500, 21);
				//c.sendMessage("You created ; Lava Titan Pouch");
				c.getItems().deleteItem(12168, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 219);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12788, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 83 Summoning & 1 Blue Charm & 1 Obsidian Charm & 219 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
										
			case 93224: //Swamp Titan pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(10149, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 150) && c.playerLevel[21] >= 85) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(23700, 21);
				//c.sendMessage("You created ; Swamp Titan Pouch");
				c.getItems().deleteItem(10149, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 150);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12776, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 85 Summoning & 1 Blue Charm & 1 Swamp lizard & 150 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
										
										case 93232: //Rune minotaur pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(2363, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 111) && c.playerLevel[21] >= 86) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(24100, 21);
				//c.sendMessage("You created ; Rune Minotaur Pouch");
				c.getItems().deleteItem(2363, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 111);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12083, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 86 Summoning & 1 Blue Charm & 1 Rune Bar & 111 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;				
										

			case 93240: //Unicorn stallion pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(237, 1) && c.getItems().playerHasItem(POUCH, 1) && c.getItems().playerHasItem(SHARD, 140) && c.playerLevel[21] >= 88) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(22900, 21);
				//c.sendMessage("You created ; Unicorn Stallion Pouch");
				c.getItems().deleteItem(237, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 140);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12039, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 88 Summoning & 1 Green Charm & 1	Unicorn Horn & 140 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	
										
										
										
										
										
										
										
										
										
										
									case 93248: //Geyser Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(1444, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 222) && c.playerLevel[21] >= 89) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(26900, 21);
				//c.sendMessage("You created ; Geyser Titan Pouch");
				c.getItems().deleteItem(1444, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 222);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12786, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 89 Summoning & 1 Blue Charm & 1 Water Talisman & 222 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;											
					
									case 94000: //Wolpertinger Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(3226, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 203) && c.playerLevel[21] >= 92) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(25900, 21);
				//c.sendMessage("You created ; Wolpertinger Pouch");
				c.getItems().deleteItem(3226, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 203);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12089, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 92 Summoning & 1 Crimson Charm & 1 Raw Rabbit & 203 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;	

					
									case 94008: // abyssal Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(GRCHARM, 1) && c.getItems().playerHasItem(12161, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 113) && c.playerLevel[21] >= 93) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(24900, 21);
				//c.sendMessage("You created ; Abyssal Titan Pouch");
				c.getItems().deleteItem(12161, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 113);
				 c.getItems().deleteItem(GRCHARM, 1);
				 c.getItems().addItem(12796, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 93 Summoning & 1 Green Charm & 1 Abyssal Charm & 113 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;		
					
				case 94016: // Iron Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(1115, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 198) && c.playerLevel[21] >= 95) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(26300, 21);
				//c.sendMessage("You created ; Iron Titan Pouch");
				c.getItems().deleteItem(1115, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 198);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12822, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 95 Summoning & 1 Crimson Charm & 1 Iron PlateBody & 198 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;					
										
										
						case 94024: // Pack Yak Pouch
			c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {	
				if (c.getItems().playerHasItem(CCHARM, 1) && c.getItems().playerHasItem(10818, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 211) && c.playerLevel[21] >= 96) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(40900, 21);
				//c.sendMessage("You created ; Pack Yak Pouch");
				c.getItems().deleteItem(10818, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 211);
				 c.getItems().deleteItem(CCHARM, 1);
				 c.getItems().addItem(12093, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 96 Summoning & 1 Crimson Charm & 1 Yak-Hide & 211 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
				case 94032: // Steel Titan Pouch
				c.hasSentMessage = false;for (int i = 0; i < summoningPouchData.length; i++) {			
				if (c.getItems().playerHasItem(BCHARM, 1) && c.getItems().playerHasItem(1119, 1) && c.getItems().playerHasItem(12155, 1) && c.getItems().playerHasItem(18016, 178) && c.playerLevel[21] >= 99) {
				//c.getPA().closeAllWindows();
				c.gfx0(2128);
					c.getPA().addSkillXP(50900, 21);
				//c.sendMessage("You created ; Steel Titan Pouch");
				c.getItems().deleteItem(1119, 1);
				 c.getItems().deleteItem(POUCH, 1);
				 c.getItems().deleteItem(SHARD, 178);
				 c.getItems().deleteItem(BCHARM, 1);
				 c.getItems().addItem(12790, 1);
				} else {
				if (c.hasSentMessage == true) {
				return; 
				}
				c.sendMessage("To create this pouch you need to have:");
				c.sendMessage("Lvl 99 Summoning & 1 Blue Charm & 1 Steel PlateBody & 178 Spirit Shards & 1 Pouch.");
										c.hasSentMessage = true; 
										} 
										} 
										break;
				
				
				}
	}			
	}	