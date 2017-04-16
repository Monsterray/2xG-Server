package server.model.players;

import server.Config;
public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	 
	public void sendDialogues(int dialogue, int npcId) {
		if(Config.DEBUG)
			if(c.playerRights==3)
				c.sendMessage("Dialogue id:"+ dialogue +" with NPC:"+ npcId);
		c.talkingNpc = npcId;
		switch(dialogue) {
			case 4444:
				c.getDH().sendNpcChat4("Well Done You have Acheived", "Level 99 in all stats, i can sell", "You a milestone cape, called the Completionist Cape.", "It will only cost you 250K!", c.talkingNpc, "Brother Kojo");
				c.nextChat = 4445;
			break;
			
			case 4445:
				sendOption2("Buy Milestone Cape", "Don't buy Milestone Cape");
				c.dialogueAction = 4445;
			break;
			
			case 4433:
				c.getDH().sendNpcChat4("Hey, Im King Milestone", "Monsterray`s Custom coded milestone guy", "If You Right Click Me", "And Click Milestone", c.talkingNpc, "King Milestone");
				c.nextChat = 4434;
			break;
			
			case 4434:
				c.getDH().sendNpcChat4("Then You Have Acess to Milestone", "Once Your Level 10+ in All stats", "This Ingame Feature Will Be Available", "Thanks, Bye!", c.talkingNpc, "King Milestone");
				c.nextChat = 0;
			break;
			
			case 4439:
				c.getDH().sendNpcChat4("Hey, Play pest control now!!", "Monsterray Personaly Loves this game", "If you would like to play", "then you should", c.talkingNpc, "Pest Control Server Manager");
				c.nextChat = 4438;
			break;
			
			case 4438:
				c.getDH().sendNpcChat4("Click the gangplank nexto me", "Once Your at the island", "Follow the path and jump in boat with ", "Three freinds", c.talkingNpc, "Pest Control Server Manager");
				c.nextChat = 0;
			break;
			
			case 400:
				sendOption4("Floor 1 - Level 1 (Floor 2 coming soon)","Killcave (Level 49)","Dungeoneering Statistics","Dungeoneering Shop");
				c.dialogueAction = 90;
				c.teleAction = -1;
			break;

			case 125:
				sendOption2("Re-do Dungeon", "Quit Dungeon");
				c.dialogueAction = 1327;
			break;
			
			//new jad
			case 3332:
				c.getDH().sendNpcChat2("Hello, your not skilled enough to talk to me.", "You need atleast level 30 Def and 50 Prayer.", c.talkingNpc, "Brother Kojo");
				c.nextChat = 3334;
			break;
		
			case 3333:
				c.getDH().sendNpcChat4("Hello, i see that your over", "30 Defence and 50 Prayer.", "Would you want me to take you to the", "Chaos altar where you can unleash the power of Zaros?", c.talkingNpc, "Brother Kojo");
				c.nextChat = 3334;
			break;
			
			case 3334:
				c.getDH().sendOption2("Yes", "No");
				c.dialogueAction = 333;
				c.nextChat = 0;
			break;
			
			case 2222:
				c.getDH().sendNpcChat4("If you wish to go in here you will have to", "Risk a Fire-cape. You will not get it back! Even if you die!", "These caves are really hard to complete!", "Many people have died! Proceed with caution", c.talkingNpc, "Warning");
				c.nextChat = 2223;
			break;
			
			case 2223:
				c.getDH().sendOption2("Go in", "Don't go in");
				c.dialogueAction = 112;
			break;
			
		///~~~~~~~~~~~~~~~~~~~~~CONSTRUCTION~~~~~~~~~~~~~~~~~~~~~~~~~~
		 
			 case 29164:
				c.getDH().sendNpcChat2("Hello, i'm selling this House", "Would you want to buy it for 2147m?", c.talkingNpc, "Estate Agent");
				c.nextChat = 29165;
			break;
			
			case 29165:
				c.getDH().sendPlayerChat2("FUCK HOW MUCH?!", "Have You got Anything else for 25m");
				c.nextChat = 29166;
			break;
			
			case 29166:
				c.getDH().sendNpcChat4("yes, i could sell you the rights to", "Construct objects and", "Have Your Own Personal House", "You will gain Construction EXP,", c.talkingNpc, "Estate Agent");
				c.nextChat = 29167;
			break;
			
			case 29167:
				c.getDH().sendOption2("Yeh sure, here you go!", "Sorry, Not at the moment.");
				c.dialogueAction = 77;
			break;
			
			case 29169:
				c.getDH().sendNpcChat2("Okay Then, Maybe Next Time!", "Talk to me if you change you're mind.", c.talkingNpc, "Estate Agent");
				c.nextChat = 0;
			break;
			
			case 29170:
				c.getDH().sendNpcChat4("Great! Now that you're able to", "Construct, you should will need to know", "A few things. First of all, when creating objects.", "You will gain Construction EXP,", c.talkingNpc, "Estate Agent");
				c.nextChat = 29171;
			break;
			
			case 29172:
				c.getDH().sendNpcChat2("Wich will eventually make you able to create", "Even better objects! If you need more Help ask A Staff!", c.talkingNpc, "Estate Agent");
				c.nextChat = 0;
			break;
			
			case 29171:
				c.getDH().sendNpcChat4("Right Click Me to Teleport To House", "Buy Equipment From The House LOBBY", "Go In public to gain XP", "Creating objects will give you XP in Construction.", c.talkingNpc, "Estate Agent");
				c.nextChat = 29172;
			break;
			
			case 28164:
				c.constructionID = 2854;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28165:
				c.constructionID = 61;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28166:
				c.constructionID = 1276;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28167:
				c.constructionID = 1281;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28168:
				c.constructionID = 1088;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28169:
				c.constructionID = 594;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 57;
			break;
			
			case 29111:
				c.constructionID = 13411;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 58;
			break;
			
			case 29112:
				c.constructionID = 13584;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 29113:
				c.constructionID = 13598;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 59;
			break;
			
			case 29114:
				c.constructionID = 13571;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 60;
			break;
			
			case 28170:
				c.constructionID = 8503;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28171:
				c.constructionID = 13417;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			
			case 28172:
				c.constructionID = 0;
			break;
			
			case 28173:
				c.constructionID = 0;
			break;
			
			case 28174:
				c.constructionID = 0;
			break;
			
			case 28175:
				c.constructionID = 0;
			break;
			
			case 28176:
				c.constructionID = 0;
			break;
			
			case 28177:
				c.constructionID = 0;
			break;
			
			case 28178:
			c.constructionID = 13432;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 56;
			break;
			case 28179:
				c.constructionID = 593;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			case 28180:
				c.constructionID = 1088;
				c.getDH().sendOption4("North", "East", "South", "West");
				c.dialogueAction = 55;
			break;
			case 5555:
			sendNpcChat4("WARNING:", "Nex Is Extrememly Hard to Defeate", "It requires ATLEAST 5 Players", "Range is recommended. -Monsterray-", c.talkingNpc, "WARNING");
			c.nextChat = 5556;
			break;
					case 5556:
			sendNpcChat4("NOTE:", "Music WILL be played", "When teleporting to the boss.", "To turn it off, reopen the client.", c.talkingNpc, "WARNING");
			c.nextChat = 5557;
			break;
			
							case 5557:
			sendOption2("Yes, teleport me!", "Woah Hell No.");
			c.nextChat = 0;
			c.teleAction = 966;
			break;
			
			
									case 2196:
					sendNpcChat4("Hello! I'm here to teach you about Cooking.", "Cooking is used to warm up raw fish so you can eat it.", "When you eat a cooked item you will receive HP.", "To cook a raw fish simply use", c.talkingNpc, "Cook");
				c.nextChat = 2197;
			break;
											case 2197:
					sendNpcChat4("The raw fish on a cooking range or a fire.", "If you have the required level", "You will automaticly start cooking the fish.", "At level 1 cooking you can only cook Shrimp.", c.talkingNpc, "Cook");
				c.nextChat = 2198;
			break;
													case 2198:
					sendNpcChat4("Buy a raw shrimp from my store", "and use it on the range.", "If you need anymore information", "Ask a staff member!", c.talkingNpc, "Cook");
				c.nextChat = 0;
			break;
							case 1196:
					sendNpcChat4("Hello! I'm here to help you train Fishing.", "Fishing is very easy but it's also very slow.", "At level 1 you can only catch shrimps.", "First, buy a small net from my store", c.talkingNpc, "Fisherman");
				c.nextChat = 1197;
			break;
									case 1197:
					sendNpcChat4("Then when having a fishing net in you're inventory", "Simply start fishing! After a short time you", "Will receive fish.", "Fish is mostly cooked using the cooking skill", c.talkingNpc, "Fisherman");
				c.nextChat = 1198;
			break;
								case 1198:
					sendNpcChat4("For more information about cooking", "Talk to Charlie The Cook.", "Well now that you know the basics go train!", "More fish will be unlocked for you to fish when you level up.", c.talkingNpc, "Fisherman");
				c.nextChat = 0;
			break;
			case 1185:
					sendNpcChat4("Hi, i know fletching pretty good.", "It's really simple! To fletch you will always have to have", "A knife and a log of you're level. For example:", "Let's say you're level 1 in fletching. You will have to", c.talkingNpc, "Fletcher");
				c.nextChat = 1186;
			break;
					case 1186:
					sendNpcChat4("Buy a knife from my store, and a log.", "Then simply use the knife on the log.", "At level 1 you can only fletch shafts.", "Now that you've got the knowledge, any questions?", c.talkingNpc, "Fletcher");
				c.nextChat = 1187;
			break;
							case 1188:
					sendNpcChat2("......", "When you're level 99 i might.", c.talkingNpc, "Fletcher");
				c.nextChat = 0;
			break;
							case 1187:
				sendPlayerChat1("Please eat dinner with me?");
				c.nextChat = 1188;
			break;
											case 1175:
			sendNpcChat4("Hi, im here to recruit thiefs.", "The problem is, there are so many guards!", "But that is what makes it fun, right?", "Anyway, pickpocket the NPC's till", c.talkingNpc, "Thief");
				c.nextChat = 1176;
			break;
			
													case 1176:
			sendNpcChat4("You get around level 40 Thieving, then", "Start stealing from the stalls!", "When stealing from stalls you will receive", "items, that you can sell to my store for money.", c.talkingNpc, "Thief");
				c.nextChat = 1177;
			break;
															case 1177:
			sendNpcChat4("Well that's about everything.", "Don't forget to ask a staff member", "If you need further assistance in the thieving skill.", "Oh and i also sell Skillcapes for master thiefs. Go get 99!", c.talkingNpc, "Thief");
				c.nextChat = 0;
			break;
									case 1155:
			sendNpcChat4("What are you waiting for??", "There's a butterfly over there, catch it!", "If you don't have a net", "Buy one from my shop!", c.talkingNpc, "Tamayu");
				c.nextChat = 0;
			break;
							case 1137:
			sendNpcChat1("Hi, i'm the master of the FireMaking Skill...", c.talkingNpc, "FireMaker");
				c.nextChat = 1138;
			break;
					case 1138:
				sendPlayerChat1("Weird. You don't look like a FireMaker...");
				c.nextChat = 1139;
			break;
			
									case 1139:
			sendNpcChat4("Would you atleast let me finnish?", "I was going to tell you that i've retired.","Well I'm going to tell you a little about FireMaking.", "All you need to do is use a tinderbox on a log.", c.talkingNpc, "FireMaker");
				c.nextChat = 1140;
			break;
			
											case 1140:
			sendNpcChat1("The logs will begin to burn, and create a fire...", c.talkingNpc, "FireMaker");
				c.nextChat = 1141;
			break;
			
							case 1141:
				sendPlayerChat1("Noshit");
				c.nextChat = 1142;
			break;
			
													case 1142:
			sendNpcChat3("LET ME FINNISH!.", "Well there isn't anything more to say.", "Just remember to buy a skillcape when you reach lvl 99!", c.talkingNpc, "FireMaker");
				c.nextChat = 0;
			break;
			
					case 1130:
			sendNpcChat4("Hello, i'm the master of the Herblore Skill.", "There are 2 ways to raise you're herblore level.", "1. Make Potions or 2. Clean herbs (Slow XP).", "To make potions, buy first an vial of water then add", c.talkingNpc, "Master Potion Maker");
				c.nextChat = 1131;
			break;
			case 1131:
					sendNpcChat4("The required ingridient & Leaf. For example,", "Let's say you're level 1 herblore.", "We will be making a Attack Potion (3), for beginners.", "First get a vial of water, and a clean Guam (From Farming)", c.talkingNpc, "Master Potion Maker");
				c.nextChat = 1132;
			break;
					case 1132:
					sendNpcChat4("When you've gotten the clean guam, use it", "on the vial of water. You will now receive,", "A Guam Potion (Unf). To turn it into,", "An Attack Potion (3) you must add the last ingridient.", c.talkingNpc, "Master Potion Maker");
				c.nextChat = 1133;
			break;
							case 1133:
					sendNpcChat4("An attack Potion (3)'s last ingridient is", "'Eye of Newt'. You can buy it from my shop!", "When you've bought 1 Eye Of Newt", "Use it on the Guam Potion (Unf)!", c.talkingNpc, "Master Potion Maker");
				c.nextChat = 1134;
			break;
			case 1134:
							sendNpcChat4("Now that you've gotten Attack Potion (3)", "You can easily repeat this process!", "When you reach higher levels, better potions will be", "Unlocked for you to make.", c.talkingNpc, "Master Potion Maker");
							c.nextChat = 1135;
							break;
							case 1135:
						sendNpcChat4("Now i've told you everything.", "If you need further assistance", "Please ask a staff member.", "Oh and don't forget to buy a skillcape at level 99!", c.talkingNpc, "Master Potion Maker");
							c.nextChat = 0;
							break;
			case 1115:
			sendNpcChat4("Welcome my friend!", "Here you can train Farming and Herblore!", "First, buy a rake, a seed dibber", "And a watering can from me. Then buy", c.talkingNpc, "Master Farmer");
				c.nextChat = 1116;
			break;
			case 1116:
					sendNpcChat4("A seed for you're level, let's say", "you're level 1 in Farming. Then you have to buy", "a Guam Seed. When you got the required items,", "Go outside, first rake the patch", c.talkingNpc, "Master Farmer");
				c.nextChat = 1117;
				break;
				case 1117:
						sendNpcChat4("Then use you're seed on it.", "Finaly Water the patch and observe the Magic!", "When the plant has grown You can 'Pick it'.", "This is the part that will get you the farming XP.", c.talkingNpc, "Master Farmer");
				c.nextChat = 1120;
				break;
				case 1120:
				sendNpcChat1("You will also Receive Herblore 'leafs'.", c.talkingNpc, "Master Farmer");
				c.nextChat = 1118;
				break;
				case 1118:
									sendNpcChat3("Cleaning the dirt of the leafs will", "grant you Herblore XP! Well, make sure to", "Come and buy a skillcape when you're level 99 Farming!", c.talkingNpc, "Master Farmer");
				c.nextChat = 0;
				break;
				
			case 1111://crafter man
	sendNpcChat4("Welcome my friend!", "Here you can train Crafting!", "Either pick flax and spin it or just buy uncuts from me.", "You can craft the uncut stones into amulets with a chisel", c.talkingNpc, "Crafter");
				c.nextChat = 1114;
			break;
			
					case 1114://crafter man
	sendNpcChat3("Don't forget to buy a skillcape from", "Me when you reach a crafting level of 99! The", "Cape proves that you're a master of the crafting skill.", c.talkingNpc, "Crafter");
				c.nextChat = 0;
			break;
			
			
					case 1112://Woodcutter
	sendNpcChat3("Welcome my friend!", "Here you can train Woodcutting", "Buy your axe from me and get started!", c.talkingNpc, "Woodcutter");
				c.nextChat = 1113;
			break;
			case 1113:
			sendNpcChat4("When you cut wood you will receive logs.", "Make sure you burn these for free FM XP!", "When you reach level 99 Woodcutting", "Make sure to come and buy a skillcape from me! ", c.talkingNpc, "Woodcutter");
				c.nextChat = 0;
			break;
			
			
					/* Barb assault start */
			
			case 365:
			sendStatement("You Currently Have "+c.barbPoints+" Barbarian Points.");
			c.nextChat = 0;
			break;

			case 1054:
			sendOption2("What is this place?", "Who are you?");
			c.dialogueAction = 510;
			c.nextChat = 1055;
			break;
			
			case 1055:
				sendNpcChat4("This place is the Barbarian Coluseum. We hold combat",
							"related events within the arena you see to the west.", 
							"Most importantly, you can play the Barbarian Defence mini-",
							"game here. To start, enter the trapdoor to the west.", c.talkingNpc, "Barbarian Master");
				c.nextChat = 1056;
			break;

			case 1056:
				sendPlayerChat3("Interesting, How do i begin?",
							"And what rewards do you recieve for participating,", 
							"In the events?");
				c.nextChat = 1057;
			break;

			case 1057:
				sendNpcChat4("here, let me explain a few things for you.",
							"The points are damage", 
							"dealt generated so the more damage",
							"you deal the more points you recieve", c.talkingNpc, "Barbarian Master");
				c.nextChat = 1058;
			break;

			case 1058:
				sendNpcChat4("Say for example i done 5431 Damage total,",
							"And i died, i would recieve around 70 Points for my efforts,", 
							"All Class Types can be used,",
							"Range/Mage/Melee", c.talkingNpc, "Barbarian Master");
				c.nextChat = 1059;
			break;
			
			case 1059:
				sendNpcChat3("Best of all you can bring your most valuable",
							"Armor into the arena,", 
							"If you were to die nothing will be lost,",
							 c.talkingNpc, "Barbarian Master");
				c.nextChat = 1060;
			break;
					case 1060:
				sendNpcChat3("Oh and, you can also bring familiar",
							"into the arena!", 
							"They will help you complete the waves.",
							 c.talkingNpc, "Barbarian Master");
				c.nextChat = 1061;
			break;
			
							case 1061:
				sendNpcChat3("You can leave the arena anytime",
							"by typing ::endgame", 
							"You will receive points for you're efforts.",
							 c.talkingNpc, "Barbarian Master");
				c.nextChat = 1062;
			break;
									case 1062:
				sendNpcChat2("This command can also be used",
							"If your character get's stuck.", c.talkingNpc, "Barbarian Master");
				c.nextChat = 1063;
			break;
											case 1063:
				sendNpcChat4("Remember to spend you're points in osman's shop!", "He's sitting over there, watching", "The fights. He has alot of good items", "For sale. Good luck!", c.talkingNpc, "Barbarian Master");
				c.nextChat = 1064;
			break;
			case 1064:
				sendPlayerChat2("Sounds good, I'm going to try it out sometime.",
							"Thanks for the help.");
				c.nextChat = 0;
			break;
			case 1065:
			sendStatement("I don't think it would be fair to interrupt a busy trainer.");
			c.nextChat = 0;
			break;

			/* Barb End */
			
				case 119:
			sendNpcChat2("Good day to you " + c.playerName +"", "Would you like a bank pin?", c.talkingNpc, "Bank server");
				c.nextChat = 120;	
		break;
		case 120:
			sendOption2("Yes please! I like my account secure.", "No thanks I'm good for now.");
				c.dialogueAction = 120;
				c.nextChat = -1;
		break;
			case 1552:
			sendNpcChat3("Ho, ho ho!","Christmas is around the corner!", "Here take this snow globe!", c.talkingNpc, "Santa");
			c.nextChat = 0;
			//c.santaPrize = 1;
		break;
			
					case 256:
				sendNpcChat1("Are you sure you wish to quit Dungeoneering?", c.talkingNpc, "Dungeoneering");
				c.nextChat = 257;
				break;
				
				case 257:
				sendOption2("NO I MISSCLICKED", "Yes take me out of here!");
				c.dialogueAction = 257;
				c.dialogueId = 71;
				c.teleAction = -1;
			break;
			
							case 258:
				sendNpcChat2("Are you sure you wish to drop this item?", "It will Dissapear, and you can't get it back!", c.talkingNpc, "2xG Protection System");
				c.nextChat = 259;
				break;
				case 259:
				sendOption2("No, i missclicked.", "Yes, drop the item");
				c.dialogueAction = 259;
				c.teleAction = -1;
			break;
			//HERBLORE QUEST
					case 2000:
				sendNpcChat1("Hi, did you need something?", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2001;
				break;
			case 2001:
				sendPlayerChat1("Nice cape you got there.");
				c.nextChat = 2002;
				break;
				case 2002:
				sendNpcChat2("Thanks, this cape proves that im a", "Excellent Potion Maker!", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2003;
				break;
				
				case 2003:
				sendNpcChat2("I'll teach you how to make potions", "if you do me one favour.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2004;
				break;
				
						case 2004:
				sendPlayerChat1("Really? What's the favour?");
				c.nextChat = 2005;
				break;
				
				case 2005:
				sendNpcChat4("I'm trying to create a potion that will", "Make me able to one hit noobs like justin", "In the Wilderness, but to do this i need you to", "Get me these items.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2006;
				break;
				
				case 2006:
				sendNpcChat3("Raw bear meat - Kill bears", "Raw Chicken - Kill chickens", "Raw Rat Meat - Kill Rats (Varrock Wild lvl: 1)", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2007;
				break;

				case 9999:
				sendOption2("Pick", "Leave");
				c.dialogueAction = 1000;
				c.dialogueId = 999;
				c.teleAction = -1;
				break;
							case 2007:
				sendNpcChat2("So, do you want to start the Quest?", "I'll teach you the arts of Pot Making!", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2008;
				break;
				
						case 2008:
				sendPlayerChat1("Yeh sure..But you better own that justin guy!");
				//c.hBQ = 1;
				c.nextChat = 2009;
			break;
			
			case 2009:
				sendNpcChat2("Okay, you've started the Quest.", "Here are some locations of the hunts:", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2010;
				break;
				
				case 2010:
				sendNpcChat3("Raw bear meat - South of Rats (Varrock).", "Raw Chicken - Kill chickens - Camelot", "Raw Rat Meat - Kill Rats (Varrock Wild lvl: 1)", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2011;
				break;
				
				case 2011:
				sendNpcChat2("Come back when you've got the", "Items for new instructions.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 0;
				c.bMQ = 1;
				break;
				
				case 2012:
				sendNpcChat2("Holy shit you actually got me the items!", "Now, all you need to do is Enchant them for me.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2013;
				break;
				
				case 2013:
				sendPlayerChat1("Enchant them?! How.. I suck at HOKUS POKUS..");
				c.nextChat = 2014;
			break;
				case 2014:
				sendNpcChat4("All you need to do is dip them in the", "Taverly Dungeon, use each item on the cauldron", "And they will be enchanted.", "Use Monster teleport to get to Taverly Dungeon.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2015;
				break;
				
				case 2015:
				sendPlayerChat2("So, teleport to Taverly dungeon, run north, go through gates,", "To the Cauldron thingy and use the raw items on it?");
				c.nextChat = 2016;
			break;
						case 2016:
				sendNpcChat2("Correct. Bring the me the enchanced items when your done", "And i'll reward you.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 0;
				c.bMQ = 2;
				break;
				
				case 2017:
				sendNpcChat2("Sweet, you got the enchanted meats!", "Now i'll teach you about Herblore.", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2018;
				break;
				
				case 2018:
				sendNpcChat4("Herblore is a very powerful skill,", "When your Herblore level is around 82,", "You will be able to make Powerful Potions", "Like Extreme Pots, they raise your stats ALOT!", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2019;
				break;
				
				case 2019:
				sendNpcChat4("To make a potion, you first need the UNF Potion.", "Using a cleaned herb on a vial of water", "Will create an UNF potion, then", "All you need to do is add the Final Ingridient!", c.talkingNpc, "Kaqemeex");
				c.nextChat = 2020;
				break;
				
				case 2020:
			sendNpcChat3("When you've completed this Quest,", "I'll open my personal Ingridients Shop just for you!", "So you will easily be able to train Herblore.", c.talkingNpc, "Kaqemeex");
			//c.bMQ = 3;
			c.nextChat = 2021;
			break;
			
						case 2021:
				sendPlayerChat2("Dude i got it seriously stop talking", "My head will blow up soon...");
				c.nextChat = 2023;
			break;
			
						case 2023:
			sendNpcChat1("Sorry, just remember to check out my shop later!", c.talkingNpc, "Kaqemeex");
			c.getItems().deleteItem(523, 1);
			c.getItems().deleteItem(524, 1);
			c.getItems().deleteItem(525, 1);
			c.getItems().deleteItem(522, 1);
			c.sendMessage("You've completed the quest! You can now train Herblore.");
			c.getPA().addSkillXP(50000, 15);
			c.nextChat = 0;
			c.bMQ = 3;
			break;
			
			//end of herb quest
			//Razgar
			case 50:
				sendNpcChat1("THE DRAGON IS BACK!", c.talkingNpc, "Razgar");
				c.nextChat = 51;
				break;
			case 51:
				sendPlayerChat1("What? Where?!");
				c.nextChat = 52;
				break;
			case 52:
				sendNpcChat1("It's you're job to find it and kill it!", c.talkingNpc, "Razgar");
				c.nextChat = 53;
				break;
			case 53:
				sendPlayerChat2("I'm not afraid of Dragons!", "Where should i start looking?");
				c.nextChat = 54;
				break;
			case 54:
				sendNpcChat3("Well, Gypsy Aris has some information", "You should go talk to her in varrock", "This quest won't be easy. I wish you best of luck!", c.talkingNpc, "Razgar");
				c.kingQuest = 1;
				c.nextChat = 0;
				break;
				//Gypsy
			case 55:
				sendNpcChat2("What are you doing here???", "I'M TRYING TO MASTURBATE", c.talkingNpc, "Gypsy");
				c.nextChat = 56;
				break;
			case 56:
				sendPlayerChat2("Lmao stop raging, Razgar sent me here", "He said you have some information about a quest");
				c.nextChat = 57;
				break;
			case 57:
				sendNpcChat4("Oh yes, there's a huge Dragon,", "It's Summoned by a Dark Wizard", "The dragon needs to be slain, we are all in danger", "It will soon attack Varrock!",  c.talkingNpc, "Gypsy");
				c.nextChat = 58;
				break;
			case 58:
				sendPlayerChat1("Ok, so how do I get to the dragon and how do I kill it?");
				c.nextChat = 59;
				break;
			case 59:
				sendNpcChat2("Go talk to the Wizard in lumbridge graveyard", "I don't slay dragons, i'm to old!", c.talkingNpc, "Gypsy");
				c.nextChat = 60;
				break;
			case 60:
				sendPlayerChat1("Rofl noob.. I'll go see the Wizard.");
				c.kingQuest = 2;
				c.nextChat = 0;
				break;
				//Sedridor
			case 61:
				sendNpcChat1("Wasup?", c.talkingNpc, "Sedridor");
				c.nextChat = 62;
				break;
			case 62:
				sendPlayerChat2("Im on a quest to slay an evil Dragon", "and the Gypsy told me to come talk to you");
				c.nextChat = 63;
				break;
			case 63:
				sendNpcChat2("Ok, i know how to slay the dragon but...", "Bring me 2 Dragon Bones.", c.talkingNpc, "Sedridor");
				c.nextChat = 64;
				break;
			case 64:
				sendPlayerChat1("ffs dude... I'll be back with the items");
				c.kingQuest = 3;
				c.nextChat = 0;
				break;
			case 65:
				sendPlayerChat1("Here are the items you asked for...");
				c.kingQuest = 4;
				c.nextChat = 66;
				break;
			case 66:
				sendNpcChat2("Ok thanks, go talk to horvik to get prepared", "He is located in varrock, near fountain.", c.talkingNpc, "Sedridor");
				c.kingQuest = 5;
				c.nextChat = 67;
				break;
			case 67:
				sendPlayerChat1("Ok i will do that");
				c.nextChat = 0;
				break;
			case 77:
				sendPlayerChat2("I talked to Horvik and he gave me some items", "so what do i do now?");
				c.nextChat = 78;
				break;
			case 78:
				sendNpcChat4("You can go ahead and kill the dragon now", "I will teleport you to him, be careful", "Make sure you equip ur anti shield and bring food", "You might need prayer potions also", c.talkingNpc, "Sedridor");
				c.nextChat = 73;
				break;
			case 73:
				sendOption2("Im ready to kill the dragon", "Im not ready to kill the dragon");
				c.dialogueAction = 200;
				break;
			case 74:
				sendPlayerChat1("I slayed the dragon!");
				c.nextChat = 75;
				break;
			case 75:
				sendNpcChat4("Congratulations on completing the quest!", "Here is your reward:", "400K Strength XP, 7M GP", "And a Green Dragon Mask!", c.talkingNpc, "Sedridor");
				c.getPA().addSkillXP(40000, 2);
				c.getItems().addItem(19281, 1);
				c.getItems().addItem(995, 7000000);
				c.kingQuest = 8;
				c.nextChat = 0;
				c.sendMessage("You can now wear Rune Platebody and open Horvik's Shop in edgeville!");
				break;
				//Horvik
			case 68:
				sendNpcChat1("Hello, what do you need?", c.talkingNpc, "Horvik");
				c.nextChat = 69;
				break;
			case 69:
				sendPlayerChat2("Im going to slay the dragon", "and sedridor said you can help me get prepared");
				c.nextChat = 82;
				break;
			case 82:
				sendNpcChat1("Ok, take these items then go talk to sedridor", c.talkingNpc, "Horvik");
				c.getItems().addItem(1113, 1);
				c.getItems().addItem(1079, 1);
				c.getItems().addItem(1540, 1);
				c.kingQuest = 6;
				c.nextChat = 0;
				break;
	case 90:
	sendNpcChat1("W'sup "+c.playerName+".", c.talkingNpc, "Grim Reaper");
	c.nextChat = 91;
	c.grimPrize = 0;
	break;
	case 91:
			sendPlayerChat1("Honestly im boored like shit");
			c.nextChat = 92;
			break;
			case 92:
	sendNpcChat1("Oo... Okay well..", c.talkingNpc, "Grim Reaper");
	c.nextChat = 93;
	break;
	case 93:
	sendPlayerChat1("I WANT FREE HALLOWEEN STUFF PLZ");
	c.nextChat = 97;
	break;
			case 97:
	sendNpcChat1("Hmm...I'll give you some items IF", c.talkingNpc, "Grim Reaper");
	c.nextChat = 98;
	break;
	case 98:
			sendPlayerChat1("Damn there's always a 'if'...");
			c.nextChat = 99;
			break;
			case 99:
	sendNpcChat1("Listen you moron, i want these items:", c.talkingNpc, "Grim Reaper");
	c.nextChat = 100;
	break;
			case 100:
	sendNpcChat2("1 Raw Shrimp, 1 Dragon Imp Jar, 1 Magic Log.","1 Amulet of Magic, And 3 Runite ores.", c.talkingNpc, "Grim Reaper");
	c.nextChat = 101;
	break;
	case 101:
			sendPlayerChat1("Holy shit dude you better gimmie bandos after this..");
			c.nextChat = 103;
			break;
					case 103:
	sendNpcChat1("Just talk to me when u got the ST0FF..", c.talkingNpc, "Grim Reaper");
	c.nextChat = 0;
	c.grimPrize = 1;
	break;
	case 104:
			sendPlayerChat1("Yo man i got the stuff!");
			c.nextChat = 105;
			break;
			case 105:
	sendNpcChat1("Sweet, Here take these items..", c.talkingNpc, "Grim Reaper");
	c.nextChat = 0;
			if(c.grimPrize == 1) 
			c.getItems().deleteItem(317,1);
			c.getItems().deleteItem(11256,1);
			c.getItems().deleteItem(451,3);
			c.getItems().deleteItem(1727,1);
			c.getItems().deleteItem(1513,1);
			c.getItems().addItem(10724,1);
			c.getItems().addItem(10725,1);
			c.getItems().addItem(10726,1);
			c.getItems().addItem(10727,1);
			c.getItems().addItem(10728,1);
			c.getItems().addItem(10735,1);
			c.grimPrize = 2;
	break;
			
			
			case 109:
					sendOption5("One 6-sided die", "Two 6-sided dice", "One 4-sided die", "One 8-sided die", "More...");
					c.dialogueAction = 106;
					c.teleAction = 0;
					c.nextChat = 0;
					break;
					/**/
					/*** Start of Rune Mysteries Quest by Gabbe & Samy, removing this cred makes you a fag
					/**/
					case 553:
							sendNpcChat4("Hi, i've lost my Ancient Talisman", "Could you help me find it?", "I'll reward you in the art of RuneCrafting", "I'll teach you how to RuneCraft!", c.talkingNpc, "Aubury");
					c.nextChat = 554;
					break;
					case 554:
				sendPlayerChat2("Uhm yeh sure...", "what do you need me to do?");
				c.nextChat = 556;
			break;
				case 556:
				sendNpcChat4("Sweet, Go talk to Distentor in Wizards Tower.", "He's located south of Draynor Village.", "He might know where it is!", "Cuz i lost the Talisman when i was there.", c.talkingNpc, "Aubury");
				c.nextChat = 557;
			break;
					case 557:
				sendPlayerChat2("K but you better teach me", "RuneCrafting after this..");
				c.nextChat = 0;
				c.rMQ = 1;
				c.sendMessage("You've now started Rune Mysteries Quest.");
				break;
				case 558:
				sendNpcChat1("WAAAAAAZUPPPPPPPPPP.", c.talkingNpc, "Distentor");
				c.nextChat = 559;
				break;
					case 559:
				sendPlayerChat2("Looking for a freaking Talisman", "Have you seen it?");
				c.nextChat = 560;
				break;
				case 560:
				sendNpcChat4("Yeh i think it was in the basement", "Somewhere.. I guess you will have to find it!", "I can teleport you to the basement if you'd like..", "Right click on me, and choose teleport.", c.talkingNpc, "Distentor");
			c.nextChat = 561;
			break;
							case 561:
				sendPlayerChat2("Can't you do some HOKUS POKUS and get me it?", "I don't feel like searching a basement dude..");
				c.nextChat = 562;
				break;
				case 562:
				sendNpcChat2("Well, if you change your mind,", "Right click on me and choose teleport.", c.talkingNpc, "Distentor");
				c.nextChat = 563;
			break;
				case 563:
				sendPlayerChat1("*COUGH* Great Wizard my ass *COUGH*");
				c.nextChat = 0;
			c.rMQ = 2;
				break;
				case 564:
				sendNpcChat2("Come back when you have the talisman!", "You should have gotten it by now!", c.talkingNpc, "Aubury");
				c.nextChat = 0;
				break;
				case 565:
				sendNpcChat2("Woah is that my talisman??", "You've done an excellent job so far.", c.talkingNpc, "Aubury");
				c.nextChat = 566;
				break;
				case 566:
				sendPlayerChat2("But..?", "TEACH ME RUNECRAFTING");
				c.nextChat = 567;
				break;
				case 567:
				sendNpcChat2("I'll take that talisman pal.", "It's not over yet...", c.talkingNpc, "Aubury");
				c.nextChat = 568;
				c.getItems().deleteItem(681, 1);
				c.rMQ = 4;
				break;
				case 568:
				sendPlayerChat2("Dude are you kidding?", "What's left to do??");
				c.nextChat = 569;
				break;
				case 569:
				sendNpcChat1("Go talk to Distentor", c.talkingNpc, "Aubury");
				c.nextChat = 0;
				break;
				
				case 570:
				sendNpcChat1("Hey man :D", c.talkingNpc, "Distentor");
				c.nextChat = 571;
				break;
				
				case 571:
				sendPlayerChat2("...Not you again", "So what did you need me for?");
				c.nextChat = 573;
				break;
				case 573:
				sendNpcChat2("I'd like you to Bring me:", "10 air and fire runes, and 2 Magic Potion's (4).", c.talkingNpc, "Distentor");
				c.nextChat = 574;
				break;
				case 574:
				sendNpcChat2("I've heard you can get Mage pots,", "From Giles shop in EdgeVille..", c.talkingNpc, "Distentor");
				c.rMQ = 5;
				c.nextChat = 0;
				break;
				
				case 575:
				sendNpcChat2("Woot you brought me the st0ff!", "I'm going to own so many noobs in wild..", c.talkingNpc, "Distentor");
				c.nextChat = 576;
				c.getItems().deleteItem(554, 10);
				c.getItems().deleteItem(3040, 2);
				c.getItems().deleteItem(556, 10);
				c.rMQ = 6;
				break;
				
				case 576:
				sendNpcChat1("Go talk to Aubury now.", c.talkingNpc, "Distentor");
				c.nextChat = 0;
				break;
				
				case 577:
				sendNpcChat4("Thanks for all your help!", "I will now teach you RuneCrafting", "To RuneCraft, buy a talisman from my store", "Then Right click it and push Locate", c.talkingNpc, "Aubury");
				c.nextChat = 578;
				break;
				
				case 578:
				sendNpcChat4("Make sure you have Rune Essence's", "And the required RuneCrafting level before", "Trying to RuneCraft,", "Then you will start crafting Runes!", c.talkingNpc, "Aubury");
				c.nextChat = 579;
				break;
				
				case 579:
				sendNpcChat1("If you want your reward, talk to me again.", c.talkingNpc, "Aubury");
				c.nextChat = 0;
				c.rMQ = 7;
				break;
				
				case 580:
				sendNpcChat3("Here's your reward:", "50K RuneCrafting XP, 5M GP", "And an Air RuneCrafting Staff!", c.talkingNpc, "Aubury");
				c.getItems().addItem(13630, 1);
				c.getItems().addItem(995, 5000000);
				c.getPA().addSkillXP(50000, 20);
				c.sendMessage("Quest complete, reward: Air RuneCrafting Staff, 5M GP, and 50k RuneCrafting XP!");
				c.rMQ = 8;
				c.nextChat = 581;
				break;
				
				case 581:
				sendPlayerChat2("Holy shit man", "Thanks!");
				c.nextChat = 0;
				break;
			
			case 1283: // 1283
				sendNpcChat2("Check out our new Boss called Nex!", "He drops hand cannons! Type ::nex",c.talkingNpc, "Swensen");
				c.nextChat = 0;
			break;
			
			case 209:
				sendNpcChat4("Hello there "+c.playerName+"!","If your level 80+ herblore you can buy","Herbs from me and mix your own","Extreme pots! Or at 96 overloads!", c.talkingNpc, "Potion mixer");
				c.nextChat = 0;
			break;
			
			case 540: // scrolls seller
				sendNpcChat4("Hi, i sell summoning scrolls For the NPC's:", "Bunyip, Pack yak, Unicorn stallion", "And Wolpertinger! To find out", "What NPC uses what scroll type ::scrolls",c.talkingNpc, "Summoning scroll seller");
				c.nextChat = 0;
			break;
			
			case 545: // titan sroll seller
				sendNpcChat4("Hi, i sell summoning scrolls For the NPC's:", "Fire, Steel, Geyser and Moss titan!", "They're scrolls make them stronger! To find out", "What NPC uses what scroll type ::titans",c.talkingNpc, "Summoning Scroll seller");
				c.nextChat = 0;
			break;
			
			case 3789: // 1283
				sendNpcChat2("Hello! I sell stuff for 2xG Points.", "You have " + c.pcPoints + " 2xG Points Atm!",c.talkingNpc, "2xG Points Shop!");
				c.nextChat = 0;
			break;
			
			case 211: // 1283
				sendNpcChat4("Hi, i just discovered this Portal,", "When i enetered it i saw a huge icy dragon!", "I litterly shit my pants and teleported home!", "If you want to visit them just enter the white portal ",c.talkingNpc, "Mr. Shit He's Pants.");
				c.dialogueAction = 211;
				c.nextChat = 0;
			break;

			case 110:
				sendOption5("One 10-sided die", "One 12-sided die", "One 20-sided die", "Two 10-sided dice for 1-100", "Back...");
				c.dialogueAction = 107;
				c.teleAction = 0;
				c.nextChat = 0;
			break;
			
		///~~~~~~~~~~~~~~~~~~~~~Town Cryer~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 200: //town cryer
				sendNpcChat4("Hello there "+c.playerName+"!"," I have the ability to reset your combat stats for free!","But remember, this is irreversable!","What would you like me to do?", c.talkingNpc, "Town Crier");
				c.nextChat = 210;
			break;
			
			case 210: //next town cryer chat
				sendOption4("Reset Defence", "Reset Prayer", "Reset Attack", "Reset All Combat Stats");
				c.dialogueAction = 42;
			break;
			
			case 230:
				sendNpcChat2("Congratulations!", "Your defence has been completely reset!",c.talkingNpc, "Town Crier");
				c.nextChat = 0;
			break;
			
			case 240:
				sendNpcChat2("Congratulations!", "Your attack has been completely reset!",c.talkingNpc, "Town Crier");
				c.nextChat = 0;
			break;
			
			case 250:
				sendNpcChat2("Congratulations!", "Your combat stats have been completely reset!",c.talkingNpc, "Town Crier");
				c.nextChat = 0;	
			break;
			
			case 260:
				sendNpcChat2("Congratulations!","Your prayer have been completely reset!",c.talkingNpc, "Town Crier");
				c.nextChat = 0;
			break;
			
			case 420:
				sendNpcChat2("Failure!","You must take off all your equipment!",c.talkingNpc, "Town Crier");
				c.nextChat = 0;
			break;
			
			case 1337:
				sendOption2("PK'er Scoreboard", "Skiller Scoreboard");
				c.dialogueAction = 1337;
			break;

			case 80:
				sendStatement("Should I tele you ?");
				c.nextChat = 81;
			break;

			case 81:
				sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
				c.dialogueAction = 27;
				c.nextChat = 0;
			break;
			
			case 38:
				sendStatement("Congratulations, you open the chest and got a reward!");
				c.nextChat = 0;
			break;

			case 37:
				sendStatement("You need atleast 1 free inventory spaces! and a crystal key!");
				c.nextChat = 0;
			break;		
			
			case 30:
				sendNpcChat4("Congratulations!","You have killed 20 monkeys hope you learned something..", "would you like to escape?","Do not break anymore rules!", c.talkingNpc, "Mosol Rei");
				c.dialogueAction = 26;
				c.nextChat = 31;
			break;
				
			case 31:
				sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
				c.dialogueAction = 27;
				c.nextChat = 0;	
			break;
				
			case 76:
				sendOption2("Castle Pk (14)",  "Clan War Zone (34)");
				c.dialogueAction = 51;
			break;
				
			case 32:
				sendNpcChat4("You cannot Escape yet!","You've killed "+c.monkeyk0ed+" out of 20 monkeys!","Come back when you have killed 20","Kthxbai", c.talkingNpc, "Mosol Rei");
				c.dialogueAction = 30;
				c.nextChat = 0;
			break;
				
			case 0:
				c.talkingNpc = -1;
				c.getPA().removeAllWindows();
				c.nextChat = 0;
				break;
			case 20:
				sendOption4("Information", "Black Jack","Five", "Maybe later...");
				c.dialogueAction = 100;
				break;

			case 25:
				sendOption4("","Black Jack", "Five","");
				c.dialogueAction = 101;
				break;

			case 21:
				sendNpcChat4("The way we play this game is simple. The way you win is", 
						"You need to get a higher number than me and you win the", 
						"500,000 coins. You need to bet 250,000 coins per round.",
						"If you get over 22 you bust and you lose.", 
						c.talkingNpc, "~ Black Jack ~");
						c.nextChat = 22;
						break;

			case 22:
				sendNpcChat4("", 
						"If i get 22+ I bust and I lose. If you get 21 then you have black", 
						"jack and you win double of what you bet.",
						"", 
						c.talkingNpc, "~ Black Jack ~");
						c.nextChat = 0;
						break;

			case 23:
				sendNpcChat4("This is my own game which I made. It's pretty simple", 
						"and resembles poker but it's a lot different. The aim of this", 
						"game is to get the same number like the random number",
						"You got 2 numbers if both hit the same you win.", 
						c.talkingNpc, "~ Five ~");
						c.nextChat = 24;
						break;
			case 24:
				sendNpcChat4("", 
						"To play this game you need to bet 1,000,000 coins. You", 
						"can win a lot of good items but also lose a lot of cash.",
						"", 
						c.talkingNpc, "~ Five ~");
						c.nextChat = 0;
						break;
			case 1:
				sendStatement("You found a hidden tunnel! Do you want to enter it?");
				c.dialogueAction = 1;
				c.nextChat = 2;
				break;
			case 45:
				sendNpcChat2("Since you haven't shown me a defender to", "prove your prowess as a warrior.", 4289, "Kamfreena");
				c.nextChat = 46;
				break;
			case 46:
				sendNpcChat3("I'll release some Cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.", 4289, "Kamfreena");
				c.nextChat = -1;
				break;
			case 47:
				sendNpcChat2("The cyclops will now drop:", "" + c.getWarriorsGuild().getCyclopsDrop126(c) + " defenders.", 4289, "Kamfreena");
				c.nextChat = -1;
				break;
			case 2:
				sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
				c.dialogueAction = 1;
				c.nextChat = 0;
				break;
			case 3:
				sendNpcChat4("Hello!", "My name is kuradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
				"Would you like a slayer task?", c.talkingNpc, "kuradel");
				c.nextChat = 4;
			break;
			case 5:
				sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
							"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
				c.nextChat = 6;
			break;
			case 6:
				sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
				"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
				c.nextChat = 15;
			break;
			case 11:
				sendNpcChat4("Hello!", "My name is kuradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
				"Would you like a slayer task?", c.talkingNpc, "kuradel");
				c.nextChat = 12;
			break;
			case 12:
				sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
				c.dialogueAction = 5;
			break;
			case 13:
				sendNpcChat4("Hello!", "My name is kuradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
				"Would you like me to give you an easier task?", c.talkingNpc, "kuradel");
				c.nextChat = 14;
			break;
			case 14:
				sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
				c.dialogueAction = 6;
			break;
			case 15:
				sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
				c.dialogueAction = 7;
			break;
			case 16:
				sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
				c.dialogueAction = 8;
			break;
			case 17:
				sendOption5("Air", "Mind", "Water", "Earth", "More");
				c.dialogueAction = 10;
				c.dialogueId = 17;
				c.teleAction = -1;
			break;
			case 18:
				sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
				c.dialogueAction = 11;
				c.dialogueId = 18;
				c.teleAction = -1;
			break;
			case 19:
				sendOption5("Nature", "Law", "Death", "Blood", "More");
				c.dialogueAction = 12;
				c.dialogueId = 19;
				c.teleAction = -1;
			break;
						   case 8591:
				sendNpcChat4("Evening Im nomad,", "Would you like to fight me?", "If You Win I will Give You two Amazing Capes.", 
				"So Would You like to Fight?", c.talkingNpc, "Monsterray`s Nomad");
				c.nextChat = 8592;
			break;
			case 8592:
				sendOption2("No Sorry", "Yes, Im Going To Own You");
				c.dialogueAction = 8591;
				c.dialogueId = 8592;
				c.teleAction = -1;
			break;
								   case 3663:
				sendNpcChat4("Yo, i saw this angry goblin", "Want me to show you where?", "He had a slayer helmet and a dwarf item", 
				"I'm guessing he will be a tough challenge!", c.talkingNpc, "Ticket Goblin");
				c.nextChat = 3664;
			break;
			case 3664:
				sendOption2("No thanks", "Yeh Sure i'm boored anyway");
				c.dialogueAction = 3664;
				c.dialogueId = 3664;
				c.teleAction = -1;
			break;
				   case 70:
				sendNpcChat4("I have Found A secret area.", "I saw some Red Dragons!", "Will You Help me kill them?", 
				"i will bring you there.", c.talkingNpc, "Monsterray`s Hoe");
				c.nextChat = 71;
			break;
			case 71:
				sendOption2("No thanks", "Sure!(LVL43WILDY)");
				c.dialogueAction = 13;
				c.dialogueId = 71;
				c.teleAction = -1;
			break;
		}
	}
	
	/*
	 * Information Box
	 */
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
//	private void sendOption(String s, String s1) {	//Said it wasn't used 4/15/17
//		c.getPA().sendFrame126("Select an Option", 2470);
//	 	c.getPA().sendFrame126(s, 2471);
//		c.getPA().sendFrame126(s1, 2472);
//		c.getPA().sendFrame126("Click here to continue", 2473);
//		c.getPA().sendFrame164(13758);
//	}	
	
	public void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame126(s2, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}

	public void Kill() {	
		c.nextChat = 999;
		c.getPA().sendFrame126("Dismiss your monster", 2460);
		c.getPA().sendFrame126("Yes", 2461);
		c.getPA().sendFrame126("No", 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	public void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	public void sendNpcChat1(String s, int ChatNpc, String name) {
		c.getPA().sendFrame200(4883, 591);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame126("Click here to continue.", 4886);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4882);
	}
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public void talk(int face, String line1, String line2, String line3, String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126(""+line1, 4903);
		c.getPA().sendFrame126(""+line2, 4904);
		c.getPA().sendFrame126(""+line3, 4905);
		c.getPA().sendFrame126(""+line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}
	
//	private void sendPlayerChat4(String s, String s1, String s2, String s3) {	//Said it wasn't used 4/15/17
//		c.getPA().sendFrame200(987, 591);
//		c.getPA().sendFrame126(c.playerName, 988);
//		c.getPA().sendFrame126(s, 989);
//		c.getPA().sendFrame126(s1, 990);
//		c.getPA().sendFrame126(s2, 991);
//		c.getPA().sendFrame126(s3, 992);
//		c.getPA().sendFrame185(987);
//		c.getPA().sendFrame164(986);
//	}
}
