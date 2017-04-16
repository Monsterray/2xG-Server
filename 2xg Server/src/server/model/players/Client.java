package server.model.players;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;

import org.apache.mina.common.IoSession;

import server.Config;
import server.Server;
import server.model.items.Banking;
import server.model.items.ItemAssistant;
import server.model.minigames.CastleWars;
import server.model.minigames.Event;
import server.model.minigames.EventContainer;
import server.model.minigames.EventManager;
import server.model.minigames.Gambling;
import server.model.minigames.WarriorsGuild;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Content.ClueScroll;
import server.model.players.Content.CycleEvent;
import server.model.players.Content.CycleEventContainer;
import server.model.players.Content.CycleEventHandler;
import server.model.players.Content.LoginMessages;
import server.model.players.Content.RequestHelp;
import server.model.players.packets.ClickItem;
import server.model.players.skills.Agility;
import server.model.players.skills.Crafting;
import server.model.players.skills.Dungeoneering;
import server.model.players.skills.Farming;
import server.model.players.skills.Firemaking;
import server.model.players.skills.Fishing;
import server.model.players.skills.FlaxStringer;
import server.model.players.skills.Fletching;
import server.model.players.skills.Herblore;
import server.model.players.skills.Prayer;
import server.model.players.skills.Prospecting;
import server.model.players.skills.Slayer;
import server.model.players.skills.Smithing;
import server.model.players.skills.SmithingInterface;
import server.model.players.skills.Summonin;
import server.model.players.skills.Summoning;
import server.model.players.skills.Thieving;
import server.model.shops.ShopAssistant;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.task.Task;
import server.util.MadTurnipConnection;
import server.util.Misc;
import server.util.SQL;
import server.util.Stream;

public class Client extends Player {
//Monsterrays Variables
	public int ChoiceNumber = 0; //this is for my choice interfaces
	public int tabNameLevel = 0; //this is for the staff tab to make it dynamic
	public long logoutRefreshIn = 0; //this is for the staff tab to make it refresh after people log out
	private ClueScroll clueScroll = new ClueScroll(this);
	public int currentClue = -1; //this is to make the clue not reset
	public int cluesCompleated = 0; //this is to make sure you don't do too many clues
	
	private Dungeoneering dungeoneering = new Dungeoneering(this);
	public byte buffer[] = null;
	public int cannonTimer = 0; 
	public int floweritem = 0;
	public int seedtimer = 0;
 	public int s;
	public Stream inStream = null, outStream = null;
	private IoSession session;
	public static PlayerSave save;
	public static Client cliento2;
	public int totalstored;
	public int currentDamage = 0;
	public int followPlayer;
	public boolean callFamilliar;
	public boolean selectStarter= false;
	public int hasFollower = -1;
	public int npcslot;
	public int summoningnpcid;
	public int timer;
	private TradeLog tradeLog = new TradeLog(this);
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private QuickCurses quickCurses = new QuickCurses(this);
	private QuickPrayer quickPrayer = new QuickPrayer(this);
	private Potions potion = new Potions(this);
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private WarriorsGuild warriorsGuild = new WarriorsGuild();
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	private Gambling gamble = new Gambling(this);
	public boolean isSearching;
	public boolean lastSearch;
	public boolean isSearching2 = true;
	public int[] items = new int[500];
	public int[] itemsN = new int[500];//the amount of the item
	public String searchName;
	/**
	 * Skill instances
	 */
	private Slayer slayer = new Slayer(this);
	private Banking bank = new Banking(this);
	//private Runecrafting runecrafting = new Runecrafting(this);
	//private Woodcutting woodcutting = new Woodcutting(this);
	//private Mining mine = new Mining(this);
	public Agility ag = new Agility(this);
	//private Cooking cooking = new Cooking(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private BankPin bankpin = new BankPin(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Curse curse = new Curse(this);
	private Fletching fletching = new Fletching(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Thieving thieving = new Thieving(this);
	private Firemaking firemaking = new Firemaking(this);
	private Summonin summonin = new Summonin();
	//private Firemaking firemaking = new Firemaking(this);
	private FlaxStringer flax = new FlaxStringer(this);
	private Herblore herblore = new Herblore(this);
	public Summoning Summoning = new Summoning(this);
//	private int somejunk;	//Said it wasn't used 4/15/17
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
	public int dungRest = 0;
	public int returnCode = 2; 
	public int clawDamage;
	public int clawIndex;
	public int clawType = 0;
	private Future<?> currentTask;
	public boolean officialClient = true;
	public boolean basket = false;
	public boolean slayerHelmetEffect;
	public String lastKilled = "";
	
	public void resetRanks() {
		for (int i = 0; i < 10; i++) {
			ranks[i] = 0;
			rankPpl[i] = "fail";
		}
	}

	public BankPin getBankPin() {
		return bankpin;
	}

	public boolean checkEmpty(Player player){
		for(int i = 0; i < player.playerEquipment.length; i++){
			if(player.playerEquipment[i] != -1)
				return false;
		}
		for(int i = 0; i < player.playerItems.length; i++){
			if(player.playerItems[i] != 0)
				return false;
		}
		return true;
	}

	public boolean checkEmpty44(Player player){
		if((getItems().freeSlots() == 28 && playerEquipment[playerHat] == -1) && (playerEquipment[playerCape] == -1) && (playerEquipment[playerAmulet] == -1) && (playerEquipment[playerChest] == -1) && (playerEquipment[playerShield] == -1) && (playerEquipment[playerLegs] == -1) && (playerEquipment[playerHands] == -1) && (playerEquipment[playerFeet] == -1) && (playerEquipment[playerWeapon] == -1)) {
			//getDungeoneering().startfloor1();
			return true;
		} else {
			sendMessage("Please bank your items if you want to enter Dungeoneering");
			return false;
		}
	}

	public boolean inCw() {
		return CastleWars.isInCwWait(this) || (CastleWars.isInCw(this));
	}
	
	public boolean checkEmpty2() {
		if((getItems().freeSlots() == 28 && playerEquipment[playerHat] == -1) && (playerEquipment[playerCape] == -1) && (playerEquipment[playerAmulet] == -1) && (playerEquipment[playerChest] == -1) && (playerEquipment[playerShield] == -1) && (playerEquipment[playerLegs] == -1) && (playerEquipment[playerHands] == -1) && (playerEquipment[playerFeet] == -1) && (playerEquipment[playerWeapon] == -1)) {
			getDungeoneering().startfloor2();
			return true;
		} else {
			sendMessage("Please bank your items if you want to enter Dungeoneering");
			return false;
		}
	}

	public void Snowball(final Client c) {
		if(playerEquipment[playerWeapon] != 11951 && !getPA().inPitsWait() && !inPits) {
			sendMessage("You need to wear a snowball first..");
			return;
		}
		Client o = (Client)Server.playerHandler.players[playerIndex];
		final int oX = c.getX();
		final int oY = c.getY();
		final int pX = o.getX();
		final int pY = o.getY();
		final int offX = (oY - pY)* -1;
		final int offY = (oX - pX)* -1;
		c.getPA().createProjectile(oX, oY, offX, offY, 50, 90, 1281, 21, 21, getId() - 1, 65);
		startAnimation(7530);
		c.turnPlayerTo(pX, pY);
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			Client o = (Client)Server.playerHandler.players[playerIndex];
			int snowballtime = 3;
			public void execute() {	
				if(playerEquipment[playerWeapon] != 11951 && !getPA().inPitsWait() && !inPits) {
					sendMessage("You need to wear a snowball first..");
					this.stop();
					return;
				}
				if(snowballtime==1){
					o.gfx100(1282);
				}
				if (this == null || snowballtime <= 0) {
					this.stop();
				return; 
				}
				if (snowballtime >= 0) {
					snowballtime--;
				}
			}
		});
		if(getItems().playerHasItem(11951,1)){
			getItems().deleteItem(11951, 1);
		} else {
			getItems().removeItem(playerEquipment[playerWeapon], playerWeapon);
			getItems().deleteItem(11951, 1);
		}
	}
	
	public void makesnow() {
		startAnimation(7528);
		gfx0(1284);
	}

	/**
	** Items kept on death
	**/
	public void StartBestItemScan() {
		if (isSkulled && !prayerActive[10]) {
			ItemKeptInfo(0);
			return;
		}
		FindItemKeptInfo();
		ResetKeepItems();
		BestItem1();
	}

	public void BestItem1() {
		int BestValue = 0;
		int NextValue = 0;
		int ItemsContained = 0;
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					WillKeepItem1 = playerItems[ITEM] - 1;
					WillKeepItem1Slot = ITEM;
					if (playerItemsN[ITEM] > 2 && !prayerActive[10]) {
						WillKeepAmt1 = 3;
					} else if (playerItemsN[ITEM] > 3 && prayerActive[10]) {
						WillKeepAmt1 = 4;
					} else {
						WillKeepAmt1 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					WillKeepItem1 = playerEquipment[EQUIP];
					WillKeepItem1Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 && !prayerActive[10]) {
						WillKeepAmt1 = 3;
					} else if (playerEquipmentN[EQUIP] > 3 && prayerActive[10]) {
						WillKeepAmt1 = 4;
					} else {
						WillKeepAmt1 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled && ItemsContained > 1
				&& (WillKeepAmt1 < 3 || (prayerActive[10] && WillKeepAmt1 < 4))) {
			BestItem2(ItemsContained);
		}
	}

	public void BestItem2(int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)) {
					BestValue = NextValue;
					WillKeepItem2 = playerItems[ITEM] - 1;
					WillKeepItem2Slot = ITEM;
					if (playerItemsN[ITEM] > 2 - WillKeepAmt1
							&& !prayerActive[10]) {
						WillKeepAmt2 = 3 - WillKeepAmt1;
					} else if (playerItemsN[ITEM] > 3 - WillKeepAmt1
							&& prayerActive[10]) {
						WillKeepAmt2 = 4 - WillKeepAmt1;
					} else {
						WillKeepAmt2 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)) {
					BestValue = NextValue;
					WillKeepItem2 = playerEquipment[EQUIP];
					WillKeepItem2Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 - WillKeepAmt1
							&& !prayerActive[10]) {
						WillKeepAmt2 = 3 - WillKeepAmt1;
					} else if (playerEquipmentN[EQUIP] > 3 - WillKeepAmt1
							&& prayerActive[10]) {
						WillKeepAmt2 = 4 - WillKeepAmt1;
					} else {
						WillKeepAmt2 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled
				&& ItemsContained > 2
				&& (WillKeepAmt1 + WillKeepAmt2 < 3 || (prayerActive[10] && WillKeepAmt1
						+ WillKeepAmt2 < 4))) {
			BestItem3(ItemsContained);
		}
	}

	public void BestItem3(int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)
						&& !(ITEM == WillKeepItem2Slot && playerItems[ITEM] - 1 == WillKeepItem2)) {
					BestValue = NextValue;
					WillKeepItem3 = playerItems[ITEM] - 1;
					WillKeepItem3Slot = ITEM;
					if (playerItemsN[ITEM] > 2 - (WillKeepAmt1 + WillKeepAmt2)
							&& !prayerActive[10]) {
						WillKeepAmt3 = 3 - (WillKeepAmt1 + WillKeepAmt2);
					} else if (playerItemsN[ITEM] > 3 - (WillKeepAmt1 + WillKeepAmt2)
							&& prayerActive[10]) {
						WillKeepAmt3 = 4 - (WillKeepAmt1 + WillKeepAmt2);
					} else {
						WillKeepAmt3 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)
						&& !(EQUIP + 28 == WillKeepItem2Slot && playerEquipment[EQUIP] == WillKeepItem2)) {
					BestValue = NextValue;
					WillKeepItem3 = playerEquipment[EQUIP];
					WillKeepItem3Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 - (WillKeepAmt1 + WillKeepAmt2)
							&& !prayerActive[10]) {
						WillKeepAmt3 = 3 - (WillKeepAmt1 + WillKeepAmt2);
					} else if (playerEquipmentN[EQUIP] > 3 - WillKeepAmt1
							&& prayerActive[10]) {
						WillKeepAmt3 = 4 - (WillKeepAmt1 + WillKeepAmt2);
					} else {
						WillKeepAmt3 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled && ItemsContained > 3 && prayerActive[10]
				&& ((WillKeepAmt1 + WillKeepAmt2 + WillKeepAmt3) < 4)) {
			BestItem4();
		}
	}

	public void BestItem4() {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)
						&& !(ITEM == WillKeepItem2Slot && playerItems[ITEM] - 1 == WillKeepItem2)
						&& !(ITEM == WillKeepItem3Slot && playerItems[ITEM] - 1 == WillKeepItem3)) {
					BestValue = NextValue;
					WillKeepItem4 = playerItems[ITEM] - 1;
					WillKeepItem4Slot = ITEM;
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)
						&& !(EQUIP + 28 == WillKeepItem2Slot && playerEquipment[EQUIP] == WillKeepItem2)
						&& !(EQUIP + 28 == WillKeepItem3Slot && playerEquipment[EQUIP] == WillKeepItem3)) {
					BestValue = NextValue;
					WillKeepItem4 = playerEquipment[EQUIP];
					WillKeepItem4Slot = EQUIP + 28;
				}
			}
		}
	}

	public void ItemKeptInfo(int Lose) {
		for (int i = 17109; i < 17131; i++) {
			getPA().sendFrame126("", i);
		}
		getPA().sendFrame126("Items you will keep on death:", 17104);
		getPA().sendFrame126("Items you will lose on death:", 17105);
		getPA().sendFrame126("Server", 17106);
		getPA().sendFrame126("Max items kept on death:", 17107);
		getPA().sendFrame126("~ " + Lose + " ~", 17108);
		getPA().sendFrame126("The normal amount of", 17111);
		getPA().sendFrame126("items kept is three.", 17112);
		switch (Lose) {
		
			case 0:
			default:
				getPA().sendFrame126("Items you will keep on death:", 17104);
				getPA().sendFrame126("Items you will lose on death:", 17105);
				getPA().sendFrame126("You're marked with a", 17111);
				getPA().sendFrame126("@red@skull. @lre@This reduces the", 17112);
				getPA().sendFrame126("items you keep from", 17113);
				getPA().sendFrame126("three to zero!", 17114);
			break;
			
			case 1:
				getPA().sendFrame126("Items you will keep on death:", 17104);
				getPA().sendFrame126("Items you will lose on death:", 17105);
				getPA().sendFrame126("You're marked with a", 17111);
				getPA().sendFrame126("@red@Skull. @lre@This reduces the", 17112);
				getPA().sendFrame126("items you keep from", 17113);
				getPA().sendFrame126("three to zero!", 17114);
				getPA().sendFrame126("However, you also have", 17115);
				getPA().sendFrame126("@red@Protect @lre@Item prayer", 17118);
				getPA().sendFrame126("active, which saves", 17119);
				getPA().sendFrame126("you one extra item!", 17120);
			break;
			
			case 3:
				getPA().sendFrame126("Items you will keep on death(if not skulled):", 17104);
				getPA().sendFrame126("Items you will lose on death(if not skulled):", 17105);
				getPA().sendFrame126("You have no factors", 17111);
				getPA().sendFrame126("affecting the items", 17112);
				getPA().sendFrame126("you keep.", 17113);
			break;
			
			case 4:
				getPA().sendFrame126("Items you will keep on death(if not skulled):", 17104);
				getPA().sendFrame126("Items you will lose on death(if not skulled):", 17105);
				getPA().sendFrame126("You have the @red@Protect", 17111);
				getPA().sendFrame126("@red@Item @lre@prayer active,", 17112);
				getPA().sendFrame126("which saves you one", 17113);
				getPA().sendFrame126("extra item!", 17114);
			break;
				
			case 5:
				getPA().sendFrame126("Items you will keep on death(if not skulled):", 17104);
				getPA().sendFrame126("Items you will lose on death(if not skulled):", 17105);
				getPA().sendFrame126("@red@You are in a @red@Dangerous", 17111);
				getPA().sendFrame126("@red@Zone, and will lose all", 17112);
				getPA().sendFrame126("@red@if you die.", 17113);
				getPA().sendFrame126("", 17114);
			break;
		}
	}
	
	public void FindItemKeptInfo(){
		if (isSkulled && prayerActive[10])
			ItemKeptInfo(1);
		else if (!isSkulled && !prayerActive[10])
			ItemKeptInfo(3);
		else if (!isSkulled && prayerActive[10])
			ItemKeptInfo(4);
		else if (inPits || inFightCaves()) {
			ItemKeptInfo(5);
			if (isInFala() || isInArd()) {
				ItemKeptInfo(6);
			}
		}
	}
	
	public void ResetKeepItems() {
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		WillKeepAmt1 = 0;
		WillKeepAmt2 = 0;
		WillKeepAmt3 = 0;
	}

    public void stopEmote() {
        playerWalkIndex = 0x333;
		agilityEmote = false;
		getPA().requestUpdates(); //this was needed to make the agility work
    }
	
	public void firstslot() {
		for (summoningslot = 0; occupied[summoningslot] == true; summoningslot += 1) {
			
		}
	}
	
	int[] randomreward2 = { 4151, 4151, 4131, 4131, 4131, 4093, 4093, 4093, 4087, 4087, 4087, 2577, 2577, 2577, 5525, 5525, 5525, 4734, 4720, 4730, 4745, 4712, 4753, 4755, 4738, 6524, 6524, 6524, 6524, 6524, 6524, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6731, 6731, 6731, 6731, 6731, 6733, 6733, 6733, 6733, 6733, 6733, 6733, 6733, 6735, 6735, 6735, 6735, 6735, 6735, 6735, 6737, 6737, 6737, 6737, 6737, 6737, 6737, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15315, 15315, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 3140, 3140, 3140, 3140, 3140, 3140, 3140, 3140 };

	public int randomreward2() {
			return randomreward2[(int) (Math.random() * randomreward2.length)];
	}
	
	public boolean healSummon(int heal) {
		int hp = playerLevel[3];
		int futurehp = hp+heal;
		if(futurehp > getPA().getLevelForXP(playerXP[3])) {
			playerLevel[3] = getPA().getLevelForXP(playerXP[3]);
			getPA().refreshSkill(3);
			sendMessage("You didn't get healed because you've alredy got Full HP!"); //added because fixed ~Monsterray//Removed cause it says you got healed then it says you didnt lmfao
			return false;
		}
		if(futurehp < getPA().getLevelForXP(playerXP[3])) {
		playerLevel[3] = playerLevel[3]+heal;
		getPA().refreshSkill(3);
		gfx0(1517);
		}
		return true;
	}

	public void healSummonW(int heal) { // WORPERTINGER MAGE HEALING AMG
		if(hasFollower != 6870) {
			return;
		}
		int mage = playerLevel[6];
		int addMage = 6;
		int futurehp = mage+addMage;
		if(futurehp > getPA().getLevelForXP(playerXP[6])+addMage) {
			//playerLevel[6] = getPA().getLevelForXP(playerXP[6]);
			getPA().refreshSkill(6);
			sendMessage("Your magic didn't increase because it alredy is boosted!");
			//sendMessage("You didn't get healed because you've alredy got Full HP!"); Removed cause it says you got healed then it says you didnt lmfao
			return;
		}
		playerLevel[6] = playerLevel[6]+addMage;
		getPA().refreshSkill(6);
		sendMessage("Your Wolpertinger has increased your Magic!");
		gfx0(1311);
	   
    }
	 
	public void specRestore() {
		if(specRestore < 0) {
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if(specRestore > 0) {
					specRestore --;		
                }
				if(disconnected) {
					this.stop();
					return;
				}
			}
              
		});
	}

	public void Gwdelay() {
		if(gwdelay < 0) {
			gwdelay = 0;
			Gwdelay();
		}
		if(gwdelay > 0) {
			Server.getTaskScheduler().addEvent(new Task(1, false) {
				public void execute() {
					if(gwdelay > 0) {
						gwdelay--;
					}
					if(gwdelay < 0) {
						gwdelay = 0;
					}
					if(disconnected) {
						this.stop();
						return;
					}
				}
			});
		}
	}

	public void frame174(int i1, int i2, int i3){ // another thing, tested doesn't logout, looks like something to do with music
		if(!soundsOn){
			return;
		}
		outStream.createFrame(174);
		outStream.writeWord(i1);
		outStream.writeByte(i2);
		outStream.writeWord(i3);
	}

	public void MonsterraysAutoSaver() {
		if(!disconnected) {
			System.out.println("Started saving accs");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer container) { 
					sendMessage("<shad=132833>Monsterray Auto Saver: Game saved succesfully for everyone!");
					//System.out.println("Automaticly Saved Game for everyone!");
					saveCharacter = true; 
					SaveGame();
					//saveGameDelay = System.currentTimeMillis();
					if(disconnected) {
						container.stop();
						return;
					}
				}   
			}, 250000);
		}
	}
/*
	public void shutDown() {
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer container) { 
				for(Player p : PlayerHandler.players) {
					SaveGame();
					destruct();
				}
				Server.shutdown();
				System.out.println("SERVER SHUT DOWN BY Monsterrays EVENT shutDown();");
				//saveGameDelay = System.currentTimeMillis();
			}        
		}, 7200000);
	}

	public void shutDown22() {
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer container) { 
				for(Player p : PlayerHandler.players) {
					SaveGame();
					destruct();
				}
				Server.shutdown();
				System.out.println("SERVER SHUT DOWN BY Monsterrays EVENT shutDown();");
				//saveGameDelay = System.currentTimeMillis();
			}        
		}, 20000);
	}*/

	public void VestaDelayEvent() {
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if (vestaDelay > 0) {
					vestaDelay --;
				} 
			
				if(vestaDelay <= 0) {
					this.stop();
				}
				if(disconnected) {
					this.stop();
					return;
				}
			}
			
		});
	}
	
	public void VetCape() {
		if (playerEquipment[playerCape] != 15117) {
			return;
		}
		if (playerEquipment[playerCape] == 15117) {
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					if (playerEquipment[playerCape] != 15117) {
						c.stop();
						return;
					}
					if (Misc.random(2) < 2) {
						gfx100(2219);
						sendMessage("The Veteran Cape senses that it's being worn by a bawz..");
					}
					if(disconnected) {
						c.stop();
						return;
					}
				}
			}, 10500);
		}
	}
	
	public void summonSpec() {
		if(summonSpec > 0) {
			Server.getTaskScheduler().addEvent(new Task(1, false) {
				public void execute() {
					summonSpec--;
					if(disconnected) {
						this.stop();
						return;
					}
				}
		
			});
		}
	}

	public void trade11() {
		if(trade11 > 0) {
			Server.getTaskScheduler().addEvent(new Task(1, true) {
                @Override
				public void execute() {
                    trade11--;
					if(disconnected) {               
						this.stop();
						return;
					}
					if(trade11 < 0) {
						sendMessage("You can now trade/duel/stake other people.");
						this.stop();
						return;
					}
                }
            });
		}
	}

	public boolean hasYakSummoned() {
		return hasFollower == 6874;
	}
   
	public void spinFlax() {
		turnPlayerTo(objectX, objectY);
		isWalking = false;
		if (getItems().playerHasItem(1779)) {
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				public void execute(CycleEventContainer e) {
					if (isWalking == true) {
						spinning = false;
						e.stop();
						return;
					}
					if (!getItems().playerHasItem(1779)) {
						sendMessage("You do not have any flax to string.");
						e.stop();
						return;
					}
					startAnimation(896);
					getItems()
							.deleteItem(1779, getItems().getItemSlot(1779), 1);
					getItems().addItem(1777, 1);
					getPA().addSkillXP(150, playerCrafting);
					spinning = true;
				}

				@Override
				public void stop() {

				}
			}, 3);
		} else {
			sendMessage("You don't have any flax left to string.");
		}
	}
					
	public void SendSumHeadAndNpcVanish() {
		Server.getTaskScheduler().addEvent(new Task(2, false) {
			public void execute() {
				if (hasFollower > 0 ) {
					if (summonTime <= 60 && summonTime >= 55 && stopSpam3 == 0 && hasFollower > 0) {
					sendMessage("<shad=15733302>Your familiar will vanish in 60 Seconds!");
					stopSpam3 = 1;
					}
					if (summonTime <= 30 && summonTime >= 25 && stopSpam2 == 0 && hasFollower > 0) {
						sendMessage("<shad=15733302>Your familiar will vanish in 30 Seconds!");
						stopSpam2 = 1;
					}
					if (summonTime > 0) {
						summonTime--;
						stopSpam = 1;
					}
					if (summonTime == 0 && stopSpam == 1 && hasFollower > 0) {
						firstslot();
						Summoning.removeItems();
						hasFollower = -1;
						hasFollower = -1;
						totalstored = 0;
						summoningnpcid = 0;
						summoningslot = 0;
						getPA().sendFrame126("", 17017);
						sendMessage("<shad=15733302>Your familiar has vanished & dropped any stored items.");
						summonTime = 0;
						stopSpam = 0;
						stopSpam2 = 0;
						stopSpam3 = 0;
					}
				}
				if (hasFollower > 0) {
					getPA().sendFrame200(17027, 9847);
					getPA().sendFrame75(hasFollower, 17027); // this sends head
					getPA().sendFrame126("NPC Summoned", 17017);
					getPA().sendFrame126("   " + playerLevel[21] + "/"+ getLevelForXP(playerXP[21]), 17025);
					getPA().sendFrame126(""+summonTime/120+".00 Min", 17021);
				} else {
					if (hasFollower < 0) {
						getPA().sendFrame200(17027, 9847);//might work now.
						getPA().sendFrame75(4000, 17027);
						getPA().sendFrame126("No Familiar", 17017);
						getPA().sendFrame126("   N/A", 17021);
						getPA().sendFrame126(" " + playerLevel[21] + "/"+ getLevelForXP(playerXP[21]), 17025);
					}
				}
				if(disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
   
	public void healingEventW() {
		if(hasFollower == 6870) {
			Server.getTaskScheduler().addEvent(new Task(20, false) {
				public void execute() {
					healSummonW(1);
					gfx0(1311);
					//sendMessage("Your Wolpertinger has increased your Magic!");
					if(hasFollower != 6870) {
						this.stop();
					}
					if(disconnected) {
						this.stop();
						return;
					}
				}
			});
		}
	}
	
	public void WGEvent() { //  if you leave room it will continue to remove tokens == to fix
		if(inWarriorG() && heightLevel == 2) {
			Server.getTaskScheduler().addEvent(new Task(40, false) {
				public void execute() {
					if(inWG == false) {
						this.stop();
						return;
					}
					getItems().deleteItem(8851, getItems().getItemSlot(8851), 10);
					gfx0(1358);
					sendMessage("Something happens and you notice that 10 of your tokens crumbled to dust!");
					if(!inWarriorG() && heightLevel != 2) {
						this.stop();
						return;
					} else if(!getItems().playerHasItem(8851, 10)) {
                        this.stop();
						getPA().movePlayer(2846, 3540, 2);
						sendMessage("You do not have any more tokens!");
						return;
					} else if(inWG == false) {
                        this.stop();
						return;
                    }
					if(disconnected) {
						this.stop();
						return;
					}
				}
			});
		}
   
	}

	public void healingEvent() {
		if(hasFollower != 6814) {
			return;
		}
		if(hasFollower == 6814) {
			Server.getTaskScheduler().addEvent(new Task(25, false) {
				public void execute() {
					if(hasFollower != 6814) {
						return;
					}
                    boolean cont = healSummon(2);
					if(cont){
						gfx0(1517);
						sendMessage("Your Bunyip has healed you");
						if(hasFollower != 6814) {
							this.stop();
						}
						if(disconnected) {
							this.stop();
							return;
						}
					}
                }
			});
		} else if(hasFollower == 6823) { // 3592
			if(hasFollower != 6823) {
				return;
			}
			Server.getTaskScheduler().addEvent(new Task(25, false) {
				public void execute() {
					if(hasFollower != 6823) {
						return;
					}
                    boolean cont = healSummon(3);
					if(cont){
						gfx0(1514);
						sendMessage("You got healed by your Unicorn!");
						if(hasFollower != 6823) {
							this.stop();
						}
						if(disconnected) {
							this.stop();
							return;
						}
					}
				}
			});
		}
	}
	
	public void firstSlot(int itemId) {
		boolean itemFound = false;
		if(!getItems().isStackable(itemId)) {
			for (summoningslot = 0; storeditems[summoningslot] > 0; summoningslot += 1) {
			}
		} else if(getItems().isStackable(itemId)) {
			for (summoningslot = 0; summoningslot < 28; summoningslot += 1) {
				if(storeditems[summoningslot] == itemId) {
					itemFound = true;
					break;
				}
			}
		}
		if(itemFound == false) {
			for (summoningslot = 0; storeditems[summoningslot] > 0; summoningslot += 1) {
			}
		} 
	}
	
	public void obsticle(int Emote, int Req, int newX, int newY, final int agilityTimer, int amtEXP, String message) {
		if (playerLevel[16] >= Req) {
			agilityEmote = true;
			walk(newX, newY, Emote);
			sendMessage(message);
			getPA().addSkillXP(amtEXP, playerAgility);
			int timer = (int) Math.floor(agilityTimer/600);
            Server.getTaskScheduler().addEvent(new Task(timer, false) {
				public void execute() {
					stopEmote();
					this.stop();
				}
			});
		} else {
                sendMessage("You Need " + Req + " Agility To Do This Obsticle");
		}        
	}        

	public void agilityDelay(int Emote, final int X, final int Y, final int H, int Req, int amtEXP, String message) {
		if (playerLevel[16] >= Req) {
			sendMessage(message);
			startAnimation(Emote);
			agilityEmote = true;
			getPA().addSkillXP(amtEXP, playerAgility);
			Server.getTaskScheduler().addEvent(new Task(2, false) {
				public void execute() {
					getPA().movePlayer(X, Y, H);
					agilityEmote = false;
					this.stop();
				}
			});
		} else {
			sendMessage("You Need " + Req + " Agility To Do This Obsticle");
		}
	}
		
	public int maxstore() {
		switch (npcType) {
			case 6806: // thorny snail
			case 6807:
				return 3;
				
			case 6867:
			case 6868: // bull ant
				return 6;
				
			case 3596: // terrorbird
				return 12;
				
			case 6815:
				return 18; // war tortoisee
				
			case 3590: // war tortoise
				return 18;
				
			case 3594: // yak
			case 6873: //pack yak
				return 28;
		}
		return 0;
	}
	
	public int totalstored() {
		int a = 0;
		for(int i = 0; i < 28; i++) {
			if(storeditems[i] > 0) {
				a += 1; 
			}
		}    
		return a;
	}
	
	public int getStoredAmount(int itemId) {
		if(!getItems().isStackable(itemId)) {
		int toReturn = 0;
	  
		for(int i = 0; i < 29; i++) {
			if(storeditems[i] == itemId) {
					 toReturn += 1; 
					 
				}   
		}
	   
		return toReturn;
		}
		
		if(getItems().isStackable(itemId)) {
		int toReturn = amount[findBoBslot(itemId)];
	   
		return toReturn;
		}
		
		return 0;
	}
		
	public int maxstore = 0;
		
	public int findBoBslot(int itemId) {
		for (int i = 0; i < 28; i += 1) {
			if(storeditems[i] == itemId) {
				return i;
			}
		}
		return -1;
	}

	public int summoningslot = 0;
	public int storeditems[] = new int[29];
	public int amount[] = new int[29];
	public boolean occupied[] = new boolean[29];
       
	public void highscores() {
		resetRanks();
		getPA().sendFrame126("  Massacred World Top Slayer killers", 6399);
		for(int i = 0; i < 10; i++) {
			if(ranks[i] > 0) {
				getPA().sendFrame126("Rank "+(i+1)+": "+rankPpl[i]+ "- Total Level: " +ranks[i], 6402+i);
			}
		}
		getPA().showInterface(6308);
		flushOutStream();
	}
	
	public int playerRank = 0;
	public static int[] ranks = new int[11];
    public static String[] rankPpl = new String[11];
			
	public void applyFollowing(){
		if (follow2 > 0){
			//Client p = Server.playerHandler.client[followId];
			Client p = (Client) Server.playerHandler.players[follow2];     
			if (p != null){
				if (isDead){
					follow(0, 3, 1);
					return;
				}
				if (!goodDistance(p.absX, p.absY, absX, absY, 25)){
					follow(0, 3, 1);
					return;
				}
			}else if (p == null){
				follow(0, 3, 1);
			}
		}else if (follow2 > 0){
			//NPCHandler.npcs.NPC npc = NPCHandler.npcs[followId2];
			if (NPCHandler.npcs[followId2] != null){
				if (NPCHandler.npcs[followId2].isDead){
					follow(0, 3, 1);
					return;
				}
				if (!goodDistance(NPCHandler.npcs[followId2].absX, NPCHandler.npcs[followId2].absY, absX, absY, 25)){
					follow(0, 3, 1);
					return;
				}
			}else if (NPCHandler.npcs[followId2] == null){
				follow(0, 3, 1);
			}
		}
	}

	public int followDistance = 0;

	public Client(IoSession s, int _playerId) {
		super(_playerId);
		this.session = s;
		//synchronized(this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;
		// }
		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
	}
	
	// cancels all player and npc emotes within area!
	public void frame1(){ 
        for (Player p : PlayerHandler.players) {
            if (p != null) {
                Client c = (Client) p;
                c.outStream.createFrame(1);
            }
        }
        updateRequired = true;
        appearanceUpdateRequired = true;
    }
	
	public Client getClient(String name) {
		name = name.toLowerCase();
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(validClient(i)) {
				Client client = getClient(i);
				if(client.playerName.toLowerCase().equalsIgnoreCase(name)) {
					return client;
				}
			}
		}
		return null;
	}
	
	public Client getClient(int id) {
		return (Client) Server.playerHandler.players[id];
	}
	
	public boolean validClient(int id) {
		if (id < 0 || id > Config.MAX_PLAYERS) {
			return false;
		}
		return validClient(getClient(id));
	}
	
	public boolean validClient(String name) {
		return validClient(getClient(name));
	}
	
	public boolean validClient(Client client) {
		return (client != null && !client.disconnected);
	}
		
	public boolean wearingArmor() {
		if(playerEquipment[playerHat] > 0)
			return true;
		else if (playerEquipment[playerChest] > 0)
			return true;
		else if(playerEquipment[playerLegs] > 0)
			return true;
		else if(playerEquipment[playerFeet] > 0)
			return true;
		else if(playerEquipment[playerWeapon] > 0)
			return true;
		else if(playerEquipment[playerCape] > 0)
			return true;
		else if(playerEquipment[playerArrows] > 0)
			return true;
		else if(playerEquipment[playerAmulet] > 0)
			return true;
		else if(playerEquipment[playerHands] > 0)
			return true;
		else if(playerEquipment[playerShield] > 0)
			return true;
		else if(playerEquipment[playerRing] > 0)
			return true;
		else
			return false;
	}

	public void degradeVls() {
		if(playerEquipment[playerWeapon] == 13901 && vlsLeft < 1){
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Vesta longsword crumbles to dust!");
			vlsLeft = 1000;
		}
	}
	
	public void degradeVSpear() {
		if(playerEquipment[playerWeapon] == 13907 && vSpearLeft < 1){
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Vesta spear crumbles to dust!");
			vSpearLeft = 1000;
		}
	}
	
	public void degradeStat() {
		if(playerEquipment[playerWeapon] == 13904 && statLeft < 1){
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Statius warhammer crumbles to dust!");
			statLeft = 1000;
		}
	}

	public void degradeVTop() {//vesta top
		if(playerEquipment[playerChest] == 13889 && vTopLeft < 1){
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Vesta chainbody crumbles to dust!");
			vTopLeft = 1000;
		}
	}

	public void degradeVLegs() {//vesta legs
		if(playerEquipment[playerLegs] == 13895 && vLegsLeft < 1){
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Vesta plateskirt crumbles to dust!");
			vLegsLeft = 1000;
		}
	}

	public void degradeSTop() {//statius top
		if(playerEquipment[playerChest] == 13886 && sTopLeft < 1){
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Statius platebody crumbles to dust!");
			sTopLeft = 1000;
		}
	}

	public void degradeSLegs() {//statius legs
		if(playerEquipment[playerLegs] == 13892 && sLegsLeft < 1){
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Statius platelegs crumbles to dust!");
			sLegsLeft = 1000;
		}
	}

	public void degradeSHelm() {//statius helm
		if(playerEquipment[playerHat] == 13898 && sHelmLeft < 1){
			playerEquipment[playerHat] = -1;
			playerEquipmentN[playerHat] = 0;
			getItems().wearItem(-1, 1, playerHat);
			sendMessage("Your Statius full helm crumbles to dust!");
			sHelmLeft = 1000;
		}
	}

	public void degradeZHood() {//zuriel hood
		if(playerEquipment[playerHat] == 13866 && zHoodLeft < 1){
			playerEquipment[playerHat] = -1;
			playerEquipmentN[playerHat] = 0;
			getItems().wearItem(-1, 1, playerHat);
			sendMessage("Your Zuriel hood crumbles to dust!");
			zHoodLeft = 1000;
		}
	}

	public void degradeZTop() {//zuriel top
		if(playerEquipment[playerChest] == 13860 && zTopLeft < 1){
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Zuriel robe top crumbles to dust!");
			zTopLeft = 1000;
		}
	}

	public void degradeZBottom() {//zuriel hood
		if(playerEquipment[playerLegs] == 13863 && zBottomLeft < 1){
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Zuriel robe bottom crumbles to dust!");
			zBottomLeft = 1000;
		}
	}

	public void degradeZStaff() {//zuriel staff
		if(playerEquipment[playerWeapon] == 13870 && zStaffLeft < 1){
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Zuriel staff crumbles to dust!");
			zStaffLeft = 1000;
		}
	}

	public void degradeMBody() {//morrigans body
		if(playerEquipment[playerChest] == 13872 && mBodyLeft < 1){
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Morrigans leather body crumbles to dust!");
			mBodyLeft = 1000;
		}
	}

	public void degradeMChaps() {//morrigans chaps
		if(playerEquipment[playerLegs] == 13875 && mChapsLeft < 1){
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Morrigans chaps crumbles to dust!");
			mChapsLeft = 1000;
		}
	}
	
	
	public int[] pvpHelms = new int[4];
	public int[] pvpChests = new int[4];
	public int[] pvpLegs = new int[4];
	public int[] pvpWeapons = new int[4];

	public void storesummon(int npcType) {
		switch (npcType) {
			case 6807:
				if (hasFollower > 0) {
					for (int i = 0; i < NPCHandler.maxNPCs; i++) {
						if (NPCHandler.npcs[i] != null) {
							if (NPCHandler.npcs[i].summon == true) {
								if (NPCHandler.npcs[i].spawnedBy == getId()
										&& NPCHandler.npcs[i].npcId == npcslot) {
									sendMessage("You are now storing items inside your npc");
									Summoning().store();
								}
							}
						}
					}

				}
			break;
		}
	}

	public boolean picking = false;
	public boolean storing = false;
	public int attackingplayer;
	public boolean summon;
	
	public void jadSpawn() {
		//getPA().movePlayer(absX, absY, playerId * 4);
		getDH().sendDialogues(41, 2618);
		Server.getTaskScheduler().addEvent(new Task(16, false) {
			public void execute() {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					jadSpawn = false;
					waveId = 0;
					this.stop();
			}
		});
	}
	
	public void HardWaveSpawn() {
		//getPA().movePlayer(absX, absY, playerId * 4);
		getDH().sendDialogues(41, 2618);
		Server.getTaskScheduler().addEvent(new Task(16, false) {
			public void execute() {
				Server.hardCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
				this.stop();
			}
		});
	}
	
	public void spawnCorp() {
		//getPA().movePlayer(absX, absY, playerId * 4);
		Server.getTaskScheduler().addEvent(new Task(16, false) {
			public void execute() {
				Server.hardCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
				this.stop();
			}
		});
	}
	
	public void nomadSpawn() {
		//c.getPA().movePlayer(3258,9517, c.playerId * 4);
		Server.getTaskScheduler().addEvent(new Task(16, false) {
			public void execute() {
				Server.Nomad.spawnNextWave((Client)Server.playerHandler.players[playerId]);
				this.stop();
			}
		});
	}
	
	public void ElvargSpawn() {
		//c.getPA().movePlayer(3258,9517, c.playerId * 4);
		Server.getTaskScheduler().addEvent(new Task(16, false) {
			public void execute() {
				Server.Elvarg.spawnNextWave((Client)Server.playerHandler.players[playerId]);
				this.stop();
			}
		});
	}
	
	public void GoblinSpawn() {
		//c.getPA().movePlayer(3258,9517, c.playerId * 4);
		Server.getTaskScheduler().addEvent(new Task(16, false) {
			public void execute() {
				Server.Goblin.spawnNextWave((Client)Server.playerHandler.players[playerId]);
				this.stop();
			}
		});
	}
	
	public void handCannonDestory() {
		cannonTimer = 0;
		int chance = playerLevel[playerFiremaking] * 5 + 25;
		if(specGfx)
			chance/=2;
		if(Misc.random(chance) == 1)
			Server.getTaskScheduler().addEvent(new Task(1, false) {
				public void execute() {
					if(cannonTimer <= 0) {
						gfx0(2140);
						playerEquipment[playerWeapon] = -1;
						sendMessage("Your hand cannon explodes!");
						int damage = Misc.random(15) + 1;
						setHitDiff(damage);
						setHitUpdateRequired(true);
						dealDamage(Misc.random(15) + 1);
						updateRequired = true;
						getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
						getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
						getItems().resetBonus();
						getItems().getBonus();
						getItems().writeBonus();
						getPA().requestUpdates();getOutStream().createFrame(34);
						getOutStream().writeWord(6);
						getOutStream().writeWord(1688);
						getOutStream().writeByte(playerWeapon);
						getOutStream().writeWord(0);
						getOutStream().writeByte(0);
						updateRequired = true; 
						setAppearanceUpdateRequired(true);
						this.stop();
					} else {
						cannonTimer--;
					}
				}
			});
	}
	
	public boolean specGfx = false;
	
	public void handCannonSpec() {
		cannonTimer = 0;
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				cannonTimer--;
				if(cannonTimer == 0) {
					gfx0(2141);
					specGfx = true;
				}
				if(cannonTimer == 1) {
					if (playerIndex > 0)
						getCombat().fireProjectilePlayer();
					else if (npcIndex > 0)
						getCombat().fireProjectileNpc();	
					this.stop();
				}
			}
		});
	}
	
	public void clearQInterface() {
		for(int iD = 29172; iD <= 29264;iD++){
		}
		getPA().sendFrame126("", 29155); //Tab Title
		getPA().sendFrame126("", 29161); //1st section title
		getPA().sendFrame126("", 29162); //1rd section content
		getPA().sendFrame126("", 29163); //2nd section title
	}

	public int specRestore = 0;
 
	public int getCombatLevel() {
        int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
		if (ran > attstr) {
			combatLevel = (int) (
				  ((getLevelForXP(playerXP[1])) * 0.25)
				+ ((getLevelForXP(playerXP[3])) * 0.25)
				+ ((getLevelForXP(playerXP[5])) * 0.125)
				+ ((getLevelForXP(playerXP[4])) * 0.4875)
				+ ((getLevelForXP(playerXP[24])) * 0.144));
		} else if (mag > attstr) {
			combatLevel = (int) (
				  ((getLevelForXP(playerXP[1])) * 0.25)
				+ ((getLevelForXP(playerXP[3])) * 0.25)
				+ ((getLevelForXP(playerXP[5])) * 0.125)
				+ ((getLevelForXP(playerXP[6])) * 0.4875)
				+ ((getLevelForXP(playerXP[24])) * 0.144));
		} else {
			combatLevel = (int) (
				  ((getLevelForXP(playerXP[1])) * 0.25)
				+ ((getLevelForXP(playerXP[3])) * 0.25)
				+ ((getLevelForXP(playerXP[5])) * 0.125)
				+ ((getLevelForXP(playerXP[0])) * 0.325) 
				+ ((getLevelForXP(playerXP[2])) * 0.325)
				+ ((getLevelForXP(playerXP[24])) * 0.144));
		}
		return combatLevel;
	}
	
	public void HighAndLow(){
		if (combatLevel < 15){
			int Low = 3;
			int High = combatLevel + 12;
			getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);
		}
		if (combatLevel > 15 && combatLevel < 114){
			int Low = combatLevel - 12;
			int High = combatLevel + 12;
			getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);
		}
		if (combatLevel > 114){
			int Low = combatLevel - 12;
			int High = 138;
			getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);
		}
	}

	public void flushOutStream() {	
		if(disconnected || outStream.currentOffset == 0) return;
		StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
		byte[] temp = new byte[outStream.currentOffset]; 
		System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
		out.addBytes(temp);
		session.write(out.toPacket());
		outStream.currentOffset = 0;
	}
	
	public void sendClan(String name, String message, String clan, int rights) {
		outStream.createFrameVarSizeWord(217);
		outStream.writeString(name);
		outStream.writeString(message);
		outStream.writeString(clan);
		outStream.writeWord(rights);
		outStream.endFrameVarSize();
	}
	
	public static final int PACKET_SIZES[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};
	
	public void destruct() {
		if (Dungeoneering.Dung(this)) {
			Dungeoneering.gameRoom.remove(this);
		}
		PlayerSave.saveGame(this);
		PlayerSave.saveGame(this);
		if(disconnected == true) { 
			saveCharacter = true;
		}
		if (CastleWars.isInCwWait(this)) {
			CastleWars.leaveWaitingRoom(this);
		}
		if (CastleWars.isInCw(this)) {
			CastleWars.removePlayerFromCw(this);
		}
		if(disconnected == true){
			getTradeAndDuel().declineTrade();
		}
		if(session == null) 
			return;
		PlayerSave.saveGame(this);
		if (clanId >= 0)
			Server.clanChat.leaveClan(playerId, clanId);
		if (inPits) {
			Server.fightPits.removePlayerFromPits(playerId);
		}
		LoginMessages.tabLogout(this);
		Misc.println("[Logged Out]: "+playerName+"");
		PlayerSave.saveGame(this);
		saveCharacter = true;
		HostList.getHostList().remove(session);
		disconnected = true;
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		super.destruct();
	}
	
	public void sendMessage(String s) {
		if(getOutStream() != null) {
			outStream.createFrameVarSize(253);
			outStream.writeString(s);
			outStream.endFrameVarSize();
		}
	}
	
	public void sendDelayedMessage(String s,int secsUntilDisplay) {
		if(getOutStream() != null) {
			outStream.createFrameVarSize(253);
			outStream.writeString(s);
			outStream.endFrameVarSize();
		}
	}
	
	public String[] qCS = { "Attack", "Strength", "Defence", "Ranged", "Prayer", "Magic", "Runecrafting"
						 , "Hitpoint", "Agility", "Herblore", "Thieving", "Crafting", "Fletching", "Slayer"
						 , "Mining", "Smithing", "Fishing", "Cooking", "Firemaking", "Woodcutting", "Farming" 
					};
	
	public int[][] qCAB = { {33206, 0}, 
							{33209, 2}, 
							{33212, 1}, 
							{33215, 4}, 
							{33218, 5}, 
							{33221, 6}, 
							{33224, 20}, 
							{33207, 3}, 
							{33210, 16}, 
							{33213, 15}, 
							{33216, 17}, 
							{33219, 12}, 
							{33222, 9}, 
							{47130, 18}, 
							{33208, 14}, 
							{33211, 13}, 
							{33214, 10}, 
							{33217, 7}, 
							{33220, 11}, 
							{33223, 8}, 
							{54104, 19}
	};
		
	public String qC = "[Shout] ";
	
	public void setSidebarInterface(int menuId, int form) {
		if(getOutStream() != null) {
			outStream.createFrame(71);
			outStream.writeWord(form);
			outStream.writeByteA(menuId);
		}
	}

	public void CatchimpNpc(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
		npcName = Server.npcHandler.getNpcListName(npcId);
		if (System.currentTimeMillis() - foodDelay >= 1500) { //anti spamm
			if (playerLevel[22] >= Req && getItems().playerHasItem(11260, 1)) { //first we check if he's high enough to catch
				if (playerEquipment[playerWeapon] == 10010 || playerEquipment[playerWeapon] == 11259) { //player got net?
					if (playerLevel[22] + Misc.random(10) >= Misc.random(20) + Req) { //catch chance
					if (Misc.random(1000) == 1) {
					sendMessage("You caught a GIGANTIC Impling and gained triple Experience!"); //looks like player got a net
					getItems().addItem(722, 1); //itemid is different so its defined in the method
					getItems().deleteItem(11260, 1);
					startAnimation(6999); //this always stays 6999, no need to change this
					getPA().addSkillXP(AmtExp*3, 22); //AmtExp is different so its defined in the method
					} else {
					sendMessage("You Catched an Impling!"); //looks like player got a net
					getItems().addItem(itemId, 1); //itemid is different so its defined in the method
					getItems().deleteItem(11260, 1);
					startAnimation(6999); //this always stays 6999, no need to change this
					getPA().addSkillXP(AmtExp, 22); //AmtExp is different so its defined in the method
					}
					} else {
					sendMessage("You Failed To Catch The Impling");
					startAnimation(6999);
					}
				} else { //player got net?
				sendMessage("You need to wear a butterfly net!"); //looks like he doesn't
				return;
				}	
			} else {
			sendMessage("You need at least "+ Req +" Hunter To catch that Impling!");
			sendMessage("Make sure you've got an impling jar!!! Buy from Bob behind bank!");
			return;
			}
			foodDelay = System.currentTimeMillis();// we use food timer but it really doesn't mather, this is just used for anti-spamm :)
		}
	}			

	public void CatchHunterNpc(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
		npcName = Server.npcHandler.getNpcListName(npcId);
		if (System.currentTimeMillis() - foodDelay >= 1500) { // anti spamm
			if (playerLevel[22] >= Req && getItems().playerHasItem(11260, 1)) { // first we check if he's high enough
				if (playerEquipment[playerWeapon] == 10010 || playerEquipment[playerWeapon] == 11259) {
					if (playerLevel[22] + Misc.random(10) >= Misc.random(20)+ Req) { // catch chance
						if (Misc.random(1000) == 1) {
							sendMessage("You catched a GIGANTIC butterfly and gained triple Experience!");
							getItems().addItem(722, 1); // itemid is different
							getItems().deleteItem(11260, 1);
							startAnimation(6999); // this always stays 6999
							getPA().addSkillXP(AmtExp * 3, 22); 
						} else {
							sendMessage("You Catched a Butterfly!");
							getItems().addItem(itemId, 1); // itemid is
							getItems().deleteItem(11260, 1);
							startAnimation(6999); // this always stays 6999
							getPA().addSkillXP(AmtExp, 22);
						}
					} else {
						sendMessage("You Failed To Catch The Butterfly");
						startAnimation(6999);
					}
				} else {
					sendMessage("You need to wear a butterfly net!");
					return;
				}
			} else {
				sendMessage("You need at least " + Req + " Hunter To catch that Butterfly! You also need an Impling Jar!");
				return;
			}
			foodDelay = System.currentTimeMillis();
		}
	}
	
	public void restartDuelIntsAndBooleans() {
		getTradeAndDuel().resetTrade();
		inTrade = false;
		tradeWith = 0;
		canOffer = true;
		tradeConfirmed = false;
		tradeConfirmed2 = false;
		acceptedTrade = false;
		getPA().removeAllWindows();
		tradeResetNeeded = false;
	}
			
	public void handleAllEvents() {
		Cons.SpawnAltar(this);
		Cons.SpawnBed(this);
		Cons.SpawnTree(this);
		Cons.SpawnBankChest(this);
		Cons.SpawnLecta(this);
		Cons.SpawnTeleporter(this);
		Cons.SpawnCrystal(this);
	}
	
	public void initialize() {
		MadTurnipConnection.addDonateItems(this,playerName);
		RequestHelp.sendOnlineStaff(this);
		if (inBarbDef) {
			Server.barbDefence.endGame(this, false);
		}
		if(MoneyCash > 100000 && MoneyCash <= 1000000) {
			getPA().sendFrame126(""+MoneyCash/1000+"K", 8134); 
		} else if(MoneyCash > 1000000 && MoneyCash <= 100000000) {
			getPA().sendFrame126(""+MoneyCash/1000000+"M", 8134);
		} else if(MoneyCash > 1000000000 && MoneyCash <= 2147483647) {
			getPA().sendFrame126(""+MoneyCash/1000000000+"B", 8134);
		} else {
			getPA().sendFrame126(""+MoneyCash+"", 8134);
		}
		getPA().sendFrame126(""+MoneyCash+"", 8135);
		if(hasNpc == true) {
			if (summonId > 0) {
				Server.npcHandler.spawnNpc3(this, summonId, absX, absY-1, heightLevel, 0, 120, 25, 200, 200, true, false, true);	
			}
		}
		if (InDung()) {
			getDungeoneering().handleLogOut(this);
			InDung = true;
			IsIDung = 1;
		}
		if (!InDung()) {
			InDung = false;
			dungn = 0;
			IsIDung = 0;
		}
		getDungeoneering().setDaBooleans();
	///~~~~~~~~~~~~~~~~~~~~~ALERT PART~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		sendMessage("Alert##"+Config.WELCOME_MESSAGE+"##"+Config.WELCOME_MESSAGE2+"##");
		outStream.createFrame(249);
		outStream.writeByteA(1);// 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (j == playerId)
				continue;
			if (Server.playerHandler.players[j] != null) {
				if (Server.playerHandler.players[j].playerName.equalsIgnoreCase(playerName))
					disconnected = true;
			}
		}
		for (int i = 0; i < 25; i++) {
			getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
			getPA().refreshSkill(i);
		}
		for(int p = 0; p < PRAYER.length; p++) { // reset prayer glows 
			prayerActive[p] = false;
			getPA().sendFrame36(PRAYER_GLOW[p], 0);	
		}
		for(int p = 0; p < CURSE.length; p++) { // reset curse glows 
			curseActive[p] = false;
			getPA().sendFrame36(CURSE_GLOW[p], 0);	
		}
		getPA().sendCrashFrame();
		getPA().handleWeaponStyle();
		getPA().handleLoginText();
		accountFlagged = getPA().checkForFlags();
		getPA().sendFrame36(505, 0);
		getPA().sendFrame36(506, 0);
		getPA().sendFrame36(507, 0);
		getPA().sendFrame36(508, 1);
		getPA().sendFrame36(166,4);
		getPA().sendFrame36(108, 0);	//resets autocast button
		getPA().sendFrame36(172, 1);	
		getPA().sendFrame36(287, 1);	
		getPA().sendFrame107();			//reset screen
		getPA().setChatOptions(0, 0, 0);//reset private messaging options
	///~~~~~~~~~~~~~~~~~~~~~Tab Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		setSidebarInterface(1, 638);	//Player Tab
		setSidebarInterface(2, 24999);	//NewStaffTab OLD-639
		setSidebarInterface(3, 3213);	//backpack tab
		setSidebarInterface(4, 1644);	//equipment tab
		setSidebarInterface(5, 5608);	//prayers tab
		getPA().totallevelsupdate();
		if(playerMagicBook == 0) {
			setSidebarInterface(6, 1151); //modern tab
		}
		if(playerMagicBook == 1){
			setSidebarInterface(6, 12855); //ancient tab
		}
		if(playerMagicBook == 2){
			setSidebarInterface(6, 29999); //Barrage tab
		}
		if(altarPrayed == 0) {
			setSidebarInterface(5, 5608); //prayers tab
		} else {			   
			setSidebarInterface(5, 22500); //Curses tab
		}
		correctCoordinates();
		setSidebarInterface(7, 18128); //clan tab
		setSidebarInterface(8, 5065); //friends tab
		setSidebarInterface(9, 5715); //ignore tab
		setSidebarInterface(10, 2449); //log out
		setSidebarInterface(11, 904); //settings tab
		setSidebarInterface(12, 147); //emote tab
		setSidebarInterface(13, 17011); //summoning tab             music tab 6299 for lowdetail. 962 for highdetail
		setSidebarInterface(14, 3917); //SKILL TAB
		//setSidebarInterface(15, 29999); //Lunar magic
		setSidebarInterface(16, 48500); //squeal of fortune
		setSidebarInterface(0, 2423); //Attack tab
		if(hasFollower > 0) {
			Summoning().SummonNewNPC(hasFollower);
		}
		if (hasFollower == 6874) {
			hasFollower = 6874;
			yak = true;
		}
		if (inFightCaves()) {
			getPA().movePlayer(3362, 3268, 0);
		}
		if (inArenas()) {
			getPA().movePlayer(3362, 3268, 0);
		}
/*
		if(totalstored > 0) {
			Server.itemHandler.createGroundItem(storeditems[int storeditems], getX(), getY(), 1, getId());
			storeditems[int storeditems] = -1;
			}
			//MonsterraysAutoSaver(); // automaticly saves all accounts using event
			//shutDown();
			//shutDown22();
*/
		hasYakSummoned();
		SendSumHeadAndNpcVanish();
		if (gwdelay > 0) {
			Gwdelay();
		} else {
			if (gwdelay < 0) {
				gwdelay = 0;
				Gwdelay();
			}
		}
		if (vestaDelay > 0) {
			VestaDelayEvent();
		}
		if(xpLock == true) {
			sendMessage("Your XP is <col=255>locked</col>.");
		} else {
			sendMessage("Your XP is <col=255>unlocked</col>.");
		}
		if(inWarriorG() && heightLevel == 2) {
			getPA().movePlayer(2846, 3540, 2);
		}
		if(InDung) {
			InDung = true;
		}
/*
		if(inBountyHunterCombat()) {
			BountyHunter.refreshBountyHunter(this);
		}
*/
		VetCape();
		if(inNomad()) {
			getPA().movePlayer(3211, 3422, 0);
		}
		if(inGoblin()) {
			getPA().movePlayer(3211, 3422, 0);
		}
		if(inPits()) {
			getPA().movePlayer(2399, 5177, 0);
			inPits = false;
		}
		//handleMonsterray();
		//if (vote == 1) {
			//getPA().showInterface(19050);
			//isChoosing = true;
		// }
		//MadTurnipConnection.addDonateItems(this,playerName);
		LoginMessages.tabLogin(this);
		LoginMessages.handleLoginMessages(this); // handles all login messages example: Monsterray has just logged in!
		getPA().writeTabs();
//		if(Server.votecheck.checkVotes(playerName)) {
//			getPA().closeAllWindows();
//			vote = 1;
//		}
		if (isDonator == 1 && playerRights == 0) {
			playerRights = 4;
			disconnected = true;
		}
		//if (playerName.equalsIgnoreCase("Monsterray")){
			//getPA().loadAnnouncements();
			//getPA().showOption(4, 0,"Stalk", 4);
			//getPA().showOption(5, 0,"Rape", 3);
			//getPA().showOption(6, 0,"Rape", 3);
		// }else{
			getPA().loadAnnouncements();
			getPA().showOption(4, 0,"Follow", 4);
			getPA().showOption(5, 0,"Trade With", 3);
		// }
		safeTimer = 0;
		healingEvent();
		healingEventW();
		if(isInPrivCon()) {
			sendMessage("Starting house events - hold on..");
			handleAllEvents();
		}
		getItems().resetItems(3214);
		getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
		getItems().resetBonus();
		getItems().getBonus();
		getPA().sendFrame126("Combat Level: "+getCombatLevel()+"", 3983);
		getItems().writeBonus();
		getItems().setEquipment(playerEquipment[playerHat],1,playerHat);
		getItems().setEquipment(playerEquipment[playerCape],1,playerCape);
		getItems().setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
		getItems().setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
		getItems().setEquipment(playerEquipment[playerChest],1,playerChest);
		getItems().setEquipment(playerEquipment[playerShield],1,playerShield);
		getItems().setEquipment(playerEquipment[playerLegs],1,playerLegs);
		getItems().setEquipment(playerEquipment[playerHands],1,playerHands);
		getItems().setEquipment(playerEquipment[playerFeet],1,playerFeet);
		getItems().setEquipment(playerEquipment[playerRing],1,playerRing);
		getItems().setEquipment(playerEquipment[playerWeapon],playerEquipmentN[playerWeapon],playerWeapon);
		getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
		getPA().logIntoPM();
		getPA().sendFrame75(npcType, 17027);
		getItems().addSpecialBar(playerEquipment[playerWeapon]);
		saveTimer = Config.SAVE_TIMER;
		saveCharacter = true;
		Misc.println("[Logged In]: "+playerName+"");
//		int size = playerListSize;	//Said it wasn't used 4/15/17
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
		//restartDuelIntsAndBooleans();
		getPA().clearClanChat();
		if (addStarter)
			getPA().addStarter();
		if (autoRet == 1)
			getPA().sendFrame36(172, 1);
		else
			getPA().sendFrame36(172, 0);
		// }
        if (acceptAid) {
			acceptAid = false;
			getPA().sendFrame36(503, 0);
			getPA().sendFrame36(427, 0);
        } else {
			acceptAid = true;
			getPA().sendFrame36(503, 1);
			getPA().sendFrame36(427, 1);
			if(this.trade11 > 0) {
				trade11();
			}
		}
    }
	
	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
	}

	public void saveHighscores() {
		SQL.saveHighScores(this); // OUR HIGHSCORES SAVES TO THEM
	}
	
	public void logout() {
		if (Dungeoneering.Dung(this)) {
			Dungeoneering.gameRoom.remove(this);
		}
		if (CastleWars.isInCw(this)) {
			CastleWars.removePlayerFromCw(this);
		}
		if (CastleWars.isInCwWait(this)) {
			CastleWars.leaveWaitingRoom(this);
		}
		//synchronized (this) {
		//HiscoresHandler.hiscoresHandler(this);
		if(System.currentTimeMillis() - logoutDelay > 10000) {
			outStream.createFrame(109);
			//CycleEventHandler.getSingleton().stopEvents(this);
			properLogout = true;
			PlayerSave.saveGame(this);
			/*if (playerRights == 1) {
				SQL.createConnection();
				SQL.saveHighScores(this);
				SQL.destroyConnection();
			}
			if (playerRights == 0) {
				SQL.createConnection();
				SQL.saveHighScores(this);
				SQL.destroyConnection();
			}
			if (playerRights == 4) {
				SQL.createConnection();
				SQL.saveHighScores(this);
				SQL.destroyConnection();
			}*/
			if (hasNpc == true)
				getSummon().pickUpClean(this, summonId);
			if (hasFollower > 0) {
				for (int i = 0; i < NPCHandler.maxNPCs; i++) {
					if (NPCHandler.npcs[i] != null) {
						if (NPCHandler.npcs[i].summon == true) {
							if (NPCHandler.npcs[i].spawnedBy == getId()) {
								NPCHandler.npcs[i].isDead = true;
								NPCHandler.npcs[i].applyDead = true;
								NPCHandler.npcs[i].summon = false;
							}
						}
					}
				}
			}
			saveCharacter = true;
		} else {
			sendMessage("You must wait a few seconds from being out of combat before you can do this.");
		}
		logoutRefreshIn = System.currentTimeMillis();
		RequestHelp.sendOnlineStaff(this);
		// }
	}

	public void SaveGame() {
		PlayerSave.saveGame(this);
	}
///~~~~~~~~~~~~~~~~~~~~~Packet Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public int packetSize = 0, packetType = -1;
	public long saveGameDelay;
	public static int processCounter = 0;
		
	public void sendInfo() {
/*
	   EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer container) {
				if (inBarbDef) {
					boolean victory = false;
					int pointsToAdd = ((int)barbDamage/80) + (victory ? 40 : 0);
					sendMessage("<>You can end the game at any time, by typing ::endgame.");
					sendMessage("<shad>[BARB ASSAULT BOT]</shad> You're currently on Wave <shad=6081134>"+(barbWave+1)+"/25</shad>!");
					sendMessage("<shad>[BARB ASSAULT BOT]</shad> You've dealt <shad=6081134>"+barbDamage+"</shad> Damage so far!");
					sendMessage("You will receive "+pointsToAdd+" points for you're damage. (SO FAR)");
					if(disconnected) {
						container.stop();
						return;
					}
					if(!inBarbDef) {
						container.stop();
						return;
					}
				}
			}
		}, 50000); //NO NEED FOR THIS CRAP
*/
   }
   
   	public void queueMessage(Packet arg1) {
		synchronized (queuedPackets) {
			// if (arg1.getId() != 41)
			queuedPackets.add(arg1);
			// else
			// processPacket(arg1);
		}
	}
	
	public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized (queuedPackets) {
			p = queuedPackets.poll();
		}
		if (p == null) {
			return false;
		}
		inStream.currentOffset = 0;
		packetType = p.getId();
		packetSize = p.getLength();
		inStream.buffer = p.getData();
		if (packetType > 0) {
			// sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, packetType, packetSize);
			processPackets++;
		}
		timeOutCounter = 0;
		if (processPackets > Config.MAX_PROCESS_PACKETS) {
			return false;
		}
		return true;
	}
	
	public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
			if (p == null) {
				return false;
			}
			inStream.currentOffset = 0;
			packetType = p.getId();
			packetSize = p.getLength();
			inStream.buffer = p.getData();
			if (packetType > 0) {
				// sendMessage("PacketType: " + packetType);
				PacketHandler.processPacket(this, packetType, packetSize);
			}
			timeOutCounter = 0;
			return true;
		}
	}

	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			Server.getTaskScheduler().addEvent(new Task(18, false) {
				public void execute() {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					this.stop();
				}
			});
		}
/*
		if (inNomad()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
			}, 10000);
		
		}
*/
		if (inRFD()) {
			getPA().movePlayer(1899,5363, playerId * 4+2);
			sendMessage("Your wave will start in 10 seconds.");
			Server.getTaskScheduler().addEvent(new Task(18, false) {
				public void execute() {
					Server.rfd.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					this.stop();
				}
			});
		}
	}
   
	public void process() {
		 if(ClickItem.flowerTime == 0) {
			getPA().removeObject(ClickItem.flowerX, ClickItem.flowerY);
			getPA().objectToRemove(ClickItem.flowerX, ClickItem.flowerY); 
			ClickItem.flowerTime = -1;
			ClickItem.flowerX = 0;
			ClickItem.flowerY = 0;
		}
		if (ClickItem.flowers == 2980) {
			floweritem = 2460;
		} else if (ClickItem.flowers == 2981) {
			floweritem = 2462;
		} else if (ClickItem.flowers == 2982) {
			floweritem = 2464;
		} else if (ClickItem.flowers == 2983) {
			floweritem = 2466;
		} else if (ClickItem.flowers == 2984) {
			floweritem = 2468;
		} else if (ClickItem.flowers == 2985) {
			floweritem = 2470;
		} else if (ClickItem.flowers == 2986) {
			floweritem = 2472;
		} else if (ClickItem.flowers == 2987) {
			floweritem = 2474;
		}
		if(seedtimer > 0) {
			seedtimer --;
		}
		if(ClickItem.flowerTime > 0) {
			ClickItem.flowerTime --;
		}
		LoyaltyScore += 1;
			
		if(LoyaltyScore == 200 || LoyaltyScore == 400) {
			sendMessage("You Will Receive 100 Loyalty Points In 35 Minuites");
		}
		if(LoyaltyScore == 600) {
			sendMessage("30mins Left Till Next Loyalty Point Payout for you!");
		}
		if(LoyaltyScore == 1800) {
			sendMessage("10mins Left Till Next Loyalty Point Payout for you!");
		}
		if(LoyaltyScore == 4800 || LoyaltyScore > 4800) {
			LoyaltyPoints += 100;
			LoyaltyScore = 0;
			sendMessage("You've just gained 100 Loyalty Points! Continue to play for more.");
		}
		if(System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if(duelCount != 1) {
				forcedChat(""+(--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}
		if(OverloadCounter > 0) {
			OverloadCounter--;
		}
		int PCTicker = 0;
		if(inPcBoat()) {
			getPA().walkableInterface(27119);
			getPA().sendFrame126("Next Departure: Within 7 Seconds if a game isn't in process.", 27120);
			getPA().sendFrame126("Players Ready: "+playersInBoat()+"", 27121);
			getPA().sendFrame126("(Need 3 to 25 players)", 27122);
			getPA().sendFrame126(Config.SERVER_NAME +"  Points: "+pcPoints+"", 27123);
		}
		if (playersInBoat() > 5) {
			PCTicker++;
		} else {
			if(playersInBoat() > 5 && PCTicker == 30) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client server = (Client)Server.playerHandler.players[j];
						server.sendMessage("<shad=6081134>[PEST CONTROL] There are currently: "+playersInBoat()+" players at PC!");
						PCTicker = 0;
						return;
					}
				}
			}
		}
		if (CastleWars.isInCw(this)) {
			getPA().showOption(3, 0, "Attack", 1);
		} else if (!CastleWars.isInCwWait(this)) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);
		} else if (CastleWars.isInCwWait(this)) {
			CastleWars.updatePlayers();
		}
		if(!inBarbDef) {
			getPA().sendFrame126(""+spinsLe+"", 48508);
		}
		if(dfsCounter > 0) {
			dfsCounter--;
		}
		handlePvpInterface();
		int totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[16]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));;
		for (int d = 0; d <= 10; d++) {
			if (totalz >= ranks[d]) {
				if (d == 0) {
					if (d == 0) {
						playerRank = d+1;
						ranks[d] = totalz;
						rankPpl[d] = playerName;
					}else if (d < 10){
						if (totalz < ranks[d-1]) {
							playerRank = d+1;
							ranks[d] = totalz;
							rankPpl[d] = playerName;
						}
					}else{
						if (totalz < ranks[d-1]) {
							playerRank = 0;	
						}
					}
				}
			}
		}

		if(inWild()) {
			safeTimer = 10;
		}
		if(safeTimer > 0 && !inWild()) {
			safeTimer--;
		}			
		if(clawDelay > 0) {
			clawDelay--;
		}
		if(clawDelay == 1) {
		    delayedDamage = clawDamage/4;
		    delayedDamage2 = (clawDamage/4)+1;
			if(clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 1, clawDamage/4);
			}
			if(clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 1, clawDamage/4);
			}
			if(clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 2, (clawDamage/4) + 1);
			}
			if(clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 2, (clawDamage/4) + 1);
			}
			clawDelay = 0;
			specEffect = 0;
			previousDamage = 0;
			usingClaws = false;
			clawType = 0;
		}
		if (smeltTimer > 0 && smeltType > 0) {
			smeltTimer--;
		} else if (smeltTimer == 0 && smeltType > 0) {
			getSmithing().smelt(smeltType);
			getSmithing().smelt(smeltType);
		}
	/*	if (miningTimer > 0 && mining[0] > 0) {
			miningTimer--;
		} else if (miningTimer == 0 && mining[0] > 0) {
			getMining().mineOre();
		}
	*/
		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage/2;
			if (damage > 0) {
				if (!getHitUpdateRequired()) {
					setHitUpdateRequired(true);
					setHitDiff(damage);
					updateRequired = true;
					poisonMask = 1;
				} else if (!getHitUpdateRequired2()) {
					setHitUpdateRequired2(true);
					setHitDiff2(damage);
					updateRequired = true;
					poisonMask = 2;
				}
				lastPoison = System.currentTimeMillis();
				poisonDamage--;
				dealDamage(damage);
			} else {
				poisonDamage = -1;
				sendMessage("You are no longer poisoned.");
			}	
		}
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if(specAmount < 10) {
				specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}
		if(followId > 0) {
			getPA().followPlayer();
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		getFishing().FishingProcess();
		getCombat().handlePrayerDrain();
		if(System.currentTimeMillis() - singleCombatDelay >  3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}
		if (System.currentTimeMillis() - restoreStatsDelay >  60000) {
			restoreStatsDelay = System.currentTimeMillis();
			if (playerLevel[3] < 1)
				return;
			for (int level = 0; level < playerLevel.length; level++)  {
				if (playerLevel[level] < getLevelForXP(playerXP[level])) {
					if(level != 5 && level != 23) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > getLevelForXP(playerXP[level])) {
					if (level == 0 || level == 1 || level == 2 || level == 4 || level == 6) {
						if (hasOverloadBoost)
							continue;
					}
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}
		if (barbLeader > 0 && inBarbDef) {
			NPC n = NPCHandler.npcs[barbLeader];
			if (n != null) {
				n.facePlayer(playerId);
				if (Misc.random(50) == 0) {
					n.requestAnimation(6728, 0);
					n.forceChat(n.barbRandom(this, Misc.random(5)));
				} else if (barbLeader > 0 && inBarbDef && lastBDWave && sentDatMsg == false) {
					n.requestAnimation(6728, 0);
					n.forceChat("I SHALL KILL YOU MYSELF!");
					n.forceChat("I SHALL KILL YOU MYSELF!");
					n.forceChat("I SHALL KILL YOU MYSELF!");
					sentDatMsg = true;
				} else if (barbLeader > 0 && inBarbDef && lastBDWave && sentDatMsg == true) {
					if (Misc.random(100) < 3) {
						n.requestAnimation(6728, 0);
						n.forceChat("Ghosts, ATTACK HIM!!");
						n.forceChat("Ghosts, ATTACK HIM!!");
						n.forceChat("Ghosts, ATTACK HIM!!");	
						sentDatMsg = true;
					}
				}
			}
		}
		if(!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}
		if(hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}
		if(skullTimer > 0) {
			skullTimer--;
			if(skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}	
		}
		if (playerLevel[3] == 0 && InDung) {
			getPA().moveDung();
		} else if (playerLevel[3] == 0 && inPcGame()) {
			getPA().movePc();
		} else if (playerLevel[3] == 0 && inBarbDef) {
			getPA().moveBarb();
		} else if(isDead && respawnTimer == -6 && !inFightCaves()) {
			getPA().applyDead();
			return;
		} else if (playerLevel[3] == 0 && inFightCaves()) {
			getPA().moveSafe();
		} else if (playerLevel[3] == 0 && inPits()) {
			getPA().movePits();
		}
		if(respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if(respawnTimer == 12) {
			respawnTimer--;
			startAnimation(836);
			poisonDamage = -1;
		}
		if(respawnTimer > -6) {
			respawnTimer--;
		}
		if(freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (Server.playerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY, Server.playerHandler.players[frozenBy].absX, Server.playerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}
		if(hitDelay > 0) {
			hitDelay--;
		}
		if(teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if(teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if(teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if(teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					gfx100(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}
		if(hitDelay == 1) {
			if(oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if(oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);				
			}		
		}
		if(attackTimer > 0) {
			attackTimer--;
		}
		if(attackTimer == 1){
			if(npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if(playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}
		timeOutCounter++;
		if(inTrade && tradeResetNeeded){
			Client o = (Client) Server.playerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
/*  The commented
		/*
		 * Curse Bonuses
		 *//*

		/*
		 * Defence bonus percentage
		 *//*
		if (curseDefence == 0 || prayerPoint == 0) {
			getPA().sendFrame126("0%", 690);
		} else {
			getPA().sendFrame126("@gr2@"+curseDefence+"%", 690);
		}
		/*
		 * Attack bonus percentage
		 *//*
		if (curseAttack == 0 || prayerPoint == 0) {
			getPA().sendFrame126("0%", 691);
		} else {
			getPA().sendFrame126("@gr2@"+curseAttack+"%", 691);
		}
		/*
		 * Strength bonus percentage
		 *//*
		if (curseStrength == 0 || prayerPoint == 0) {
			getPA().sendFrame126("0%", 692);
		} else {
			getPA().sendFrame126("@gr2@"+curseStrength+"%", 692);
		}
		/*
		 * Ranged bonus percentage
		 *//*
		if (curseRange == 0 || prayerPoint == 0) {
			getPA().sendFrame126("0%", 693);
		} else {
			getPA().sendFrame126("@gr2@"+curseRange+"%", 693);
		}
		/*
		 * Magic bonus percentage
		 *//*
		if (curseMagic == 0 || prayerPoint == 0) {
			getPA().sendFrame126("0%", 694);
		} else {
			getPA().sendFrame126("@gr2@"+curseMagic+"%", 694);
		}
		if (playerTitle == 2)
			getPA().sendFrame126("@lre@Title: @or2@Sir", 29177);
		else if (playerTitle == 1)
			getPA().sendFrame126("@lre@Title: @or2@Lord", 29177);
		else if (playerTitle == 3)
			getPA().sendFrame126("@lre@Title: @or2@Lionheart", 29177);
		else if (playerTitle == 4)
			getPA().sendFrame126("@lre@Title: @or2@Desperado", 29177);
		else if (playerTitle == 5)
			getPA().sendFrame126("@lre@Title: @or2@Bandito", 29177);
		else if (playerTitle == 6)
	   getPA().sendFrame126("@lre@Title: @or2@King", 29177);
			  else if (playerTitle == 7)
	   getPA().sendFrame126("@lre@Title: @or2@Big Cheese", 29177);
			  else if (playerTitle == 8)
	   getPA().sendFrame126("@lre@Title: @or2@WunderKind", 29177);
			  else if (playerTitle == 9)
	   getPA().sendFrame126("@lre@Title: @or2@Crusader", 29177);
			  else if (playerTitle == 10)
	   getPA().sendFrame126("@lre@Title: @or2@Overlord", 29177);
			  else if (playerTitle == 11)
	   getPA().sendFrame126("@lre@Title: @or2@Bigwig", 29177);
			  else if (playerTitle == 12)
	   getPA().sendFrame126("@lre@Title: @or2@Count", 29177);
			  else if (playerTitle == 13)
	   getPA().sendFrame126("@lre@Title: @or2@Duderino", 29177);
			  else if (playerTitle == 14)
	   getPA().sendFrame126("@lre@Title: @or2@Hell Raiser", 29177);
			  else if (playerTitle == 15)
	   getPA().sendFrame126("@lre@Title: @or2@Baron", 29177);
			  else if (playerTitle == 16)
	   getPA().sendFrame126("@lre@Title: @or2@Duke", 29177);
			  else if (playerTitle == 17)
			getPA().sendFrame126("@lre@Title: @or2@Lady", 29177);
		else if (playerTitle == 18)
			getPA().sendFrame126("@lre@Title: @or2@Dame", 29177);
		else if (playerTitle == 19)
			getPA().sendFrame126("@lre@Title: @or2@Dudette", 29177);
		else if (playerTitle == 20)
			getPA().sendFrame126("@lre@Title: @or2@Baroness", 29177);
		else if (playerTitle == 21)
			getPA().sendFrame126("@lre@Title: @or2@Countess", 29177);
		else if (playerTitle == 22)
			getPA().sendFrame126("@lre@Title: @or2@Overlordess", 29177);
		else if (playerTitle == 23)
			getPA().sendFrame126("@lre@Title: @or2@Duchess", 29177);
		else if (playerTitle == 24)
			getPA().sendFrame126("@lre@Title: @or2@Queen", 29177);
		else if (playerTitle == 25)
			getPA().sendFrame126("@lre@Title: @or2@Donator", 29177);
		else if (playerTitle == 26)
			getPA().sendFrame126("@lre@Title: @or2@Extreme Donator", 29177);
		else if (playerTitle == 27)
			getPA().sendFrame126("@lre@Title: @or2@Veteran", 29177);
		else if (playerTitle == 28)
			getPA().sendFrame126("@lre@Title: @or2@Owner", 29177);
		else if (playerTitle == 29)
			getPA().sendFrame126("@lre@Title: @or2@Co-Owner", 29177);
		else if (playerTitle == 30)
			getPA().sendFrame126("@lre@Title: @or2@Trusted-Dicer", 29177);
		else if (playerTitle == 31)
			getPA().sendFrame126("@lre@Title: @or2@Master", 29177);
		else if (playerTitle == 32)
			getPA().sendFrame126("@lre@Title: @or2@Dicer", 29177);
		else if (playerTitle == 33)
			getPA().sendFrame126("@lre@Title: @or2@Mistress", 29177);
		else if (playerTitle == 0)  
			getPA().sendFrame126("@lre@Title: @gre@Non-Set", 29177);
		else
			getPA().sendFrame126("@lre@Title: @or2@UNDEFINED", 29177);
	  }

		if(underAttackBy != 0 || underAttackBy2 > 0) {
			inCombat = true;
			if(curseActive[19]) {
				getCurse().turmBonus();
		}
		updateRequired = true;
		setAppearanceUpdateRequired(true);

		} else if (underAttackBy == 0 && underAttackBy2 == 0) {
		inCombat = false;
		if(curseActive[19]) {
			getCurse().turmBonus();
		}
		updateRequired = true;
		setAppearanceUpdateRequired(true);
		}
		if (inBarbDef) {
			Server.barbDefence.sendInterface(this);
		}
		//processCounter++; 
		if(processCounter >= 4){
			getPA().writeTabs();
			processCounter = 0;
		}
		if(dungRest > 0) {
			dungRest --;
		}
		//getPA().sendFrame126(""+dungPoints+"", 18071);
		//FetchDice();
		if (getItems().updateInventory)
			getItems().updateInventory();
		if (SpeDelay > 0) {
			startAnimation(3170);//if loading 602 (3170)
			dealDamage(10);
			handleHitMask(10);
			SpeDelay -= 1;
			getPA().refreshSkill(3);	
		}
		if(trade11 > 0) {
			trade11--;
		}
		if(vestaDelay > 0) {
			vestaDelay--;
		}
		//if(gwdelay > 0) {
			//gwdelay--;
		// }
		if(summonSpec > 0) {
			summonSpec--;
		}
		if(System.currentTimeMillis() - saveGameDelay > Config.SAVE_TIMER && !disconnected) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client)Server.playerHandler.players[j];
					//Server.clanChat.playerMessageToClan(c.playerId, "I Have Rolled A "+ Misc.random(100) +" On The Percentile Dice ", c.clanId);
					c2.sendMessage("<shad=132833> Monsterray`s Auto Saver: Game saved succesfully for everyone.");
				// }
				saveCharacter = true; 
				PlayerSave.saveGame(this);
				saveGameDelay = System.currentTimeMillis();
				System.out.println("Automaticly Saved Game for everyone!");
			}
		}
		// }
		}
		if(System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if(duelCount != 1) {
				forcedChat(""+(--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT_WITH_RING && playerEquipment[playerRing] == 19669) {
			specDelay = System.currentTimeMillis();
				if(specAmount < 10) {
					specAmount += .5;
					if (specAmount > 10)
						specAmount = 10;
					getItems().addSpecialBar(playerEquipment[playerWeapon]);
				}
		
		if(clickObjectType > 0 && goodDistance(objectX + objectXOffset, objectY + objectYOffset, getX(), getY(), objectDistance)) {
			if(clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}
		if((clickNpcType > 0) && NPCHandler.npcs[npcClickIndex] != null) {			
			if(goodDistance(getX(), getY(), NPCHandler.npcs[npcClickIndex].getX(), NPCHandler.npcs[npcClickIndex].getY(), 1)) {
				if(clickNpcType == 1) {
					turnPlayerTo(NPCHandler.npcs[npcClickIndex].getX(), NPCHandler.npcs[npcClickIndex].getY());
					NPCHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if(clickNpcType == 2) {
					turnPlayerTo(NPCHandler.npcs[npcClickIndex].getX(), NPCHandler.npcs[npcClickIndex].getY());
					NPCHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if(clickNpcType == 3) {
					turnPlayerTo(NPCHandler.npcs[npcClickIndex].getX(), NPCHandler.npcs[npcClickIndex].getY());
					NPCHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		if(walkingToItem) {
			if(getX() == pItemX && getY() == pItemY || goodDistance(getX(), getY(), pItemX, pItemY,1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX, pItemY, true);
			}
		}	
		if(System.currentTimeMillis() - teleGrabDelay >  1550 && usingMagic) {
			usingMagic = false;
			if(Server.itemHandler.itemExists(teleGrabItem, teleGrabX, teleGrabY)) {
				Server.itemHandler.removeGroundItem(this, teleGrabItem, teleGrabX, teleGrabY, true);
			}
		}
		if(specRestore > 0) {
			specRestore --;
		}
*/
	}
	
	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}

	public void handlePvpInterface() {
		if(inWild() && !isInPbox() && !isInArd() && !isInFala() && !inCw() && !inFunPk()) {
		int modY = absY > 6400 ?  absY - 6400 : absY;
		wildLevel = (((modY - 3520) / 8) + 1);
		EarningPotential.checkPotential(this);
		getPA().walkableInterface(197);
		if(Config.SINGLE_AND_MULTI_ZONES) {
			if(inMulti()) {
				getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
			} else {
				getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
			}
		} else {
			getPA().multiWay(-1);
			getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
		}
		getPA().showOption(3, 0, "Attack", 1);
/*
		} else if (!inWild() && !inDuelArena() && !getPA().inPitsWait() && !inPits && safeTimer <= 0 && !inGWD() && !inPcBoat() && !inPcGame()){ //this makes it so attack option is visible on wild and challenge in duel =)
			getPA().showOption(3, 0, "Throw at", 1);
			getPA().walkableInterface(-1);
		} else if (!inWild() && playerEquipment[playerWeapon] != 11951 && !inDuelArena() && safeTimer <= 0 && !inGWD() && !inPcBoat() && !inPcGame()){ //this makes it so attack option is visible on wild and challenge in duel =)
			getPA().showOption(3, 0, "View shop", 1);
			getPA().walkableInterface(-1);
*/
		} else if (!inWild() && safeTimer > 0){
			getPA().walkableInterface(197);
			wildLevel = (60);
			getPA().showOption(3, 0, "Attack", 1);
			getPA().sendFrame126("@or1@"+safeTimer, 199);
		} else if(inPcBoat()) {
			getPA().walkableInterface(27119);		
		} else if(inFunPk()) {
			getPA().walkableInterface(197);
			getPA().sendFrame126("@yel@FunPk", 199);
			getPA().showOption(3, 0, "Attack", 1);
			wildLevel = 55;	
		} else if(inPcGame()) {
			getPA().walkableInterface(21100);
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if(duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}
		} else if (inFunPk()) {
			getPA().walkableInterface(197);
			getPA().sendFrame126("@yel@FunPk", 199);
			getPA().showOption(3, 0, "Attack", 1);
		} else if(inBarrows()){
			getPA().sendFrame99(2);
			getPA().sendFrame126("Kill Count: "+barrowsKillCount, 4536);
			getPA().walkableInterface(4535);
/*
		} else if (InDung()){
			getPA().sendFrame126("@gre@Dungeoneering", 199);
*/
		} else if(inGWD()){
			getPA().GWKC();
		} else if(safeZone()){
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@gre@SafeZone", 199);
				} else {
					getPA().sendFrame126("@gre@SafeZone", 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@gre@SafeZone", 199);
			}
		} else if(isInFala()){
//			int modY = absY > 6400 ?  absY - 6400 : absY;	//Said it wasn't used 4/15/17
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			}
/*	
		} else if(isInPbox()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			}
*/
		} else if(isInArd()){
//			int modY = absY > 6400 ?  absY - 6400 : absY;	//Said it wasn't used 4/15/17
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			} else {
				getPA().multiWay(-1);
				HighAndLow();
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inCwGame || inPits) {
			getPA().showOption(3, 0, "Attack", 1);
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Null", 1);
		} else if (!inCwWait) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);
		}
	}
///~~~~~~~~~~~~~~~~~~~~~Dice Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void FetchDice(){
		int rnd;
		String Message = "";
		if (cDice == 0 || (System.currentTimeMillis() - diceDelay <= 1000)) {
			return;
		}
		switch (cDice) {
		//Dice
			case 15096: rnd = Misc.random(19)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a twenty-sided die."); break;
			case 15094: rnd = Misc.random(11)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a twelve-sided die."); break;
			case 15092: rnd = Misc.random(9)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a ten-sided die."); break;
			case 15090: rnd = Misc.random(7)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on an eight-sided die."); break;
			case 15100: rnd = Misc.random(3)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a four-sided die."); break;
			case 15086: rnd = Misc.random(5)+1;	Message = ("rolled <col=16711680>"+ rnd +"</col> on a six-sided die."); break;
			case 15088: rnd = Misc.random(11)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on two six-sided dice."); break;
			case 15098: rnd = Misc.random(99)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on the percentile dice."); break;
		}
		sendMessage("You " + Message);
			if (clanDice){
				if (clanId >= 0) {
					Server.clanChat.messageToClan("Clan Chat channel-mate <col=16711680>"+playerName+"</col> "+Message, clanId);
				}
			}
		cDice = 0;
	}

	public void useDice(int itemId, boolean clan){
		if (System.currentTimeMillis() - diceDelay >= 3000) {
			sendMessage("Rolling...");
			startAnimation(11900);
			diceDelay = System.currentTimeMillis();
			cDice = itemId;
			clanDice = clan;
			switch (itemId) {
				//Gfx's
				case 15086: gfx0(2072); break;
				case 15088: gfx0(2074); break;
				case 15090: gfx0(2071); break;
				case 15092: gfx0(2070); break;
				case 15094: gfx0(2073); break;
				case 15096: gfx0(2068); break;
				case 15098: gfx0(2075); break;
				case 15100: gfx0(2069); break;
			}
		}

	}

///~~~~~~~~~~~~~~~~~~~~~Walking Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public int tmpNWCY[] = new int[walkingQueueSize];
	public int tmpNWCX[] = new int[walkingQueueSize];
		
	public void fmwalkto(int i, int j){
        newWalkCmdSteps = 0;
        if(++newWalkCmdSteps > 50)
            newWalkCmdSteps = 0;
        int k = absX + i;
        k -= mapRegionX * 8;
        newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
        int l = absY + j;
        l -= mapRegionY * 8;
		isRunning2 = false;
		isRunning = false;
        //for(this.i = 0; this.i < newWalkCmdSteps; this.i++){
            newWalkCmdX[0] += k;
            newWalkCmdY[0] += l;
        // }
	//lastWalk = System.currentTimeMillis();
	//walkDelay = 1;
        poimiY = l;
        poimiX = k;
    }
	
	public void fmwalkto22(int i, int j){
		//startAnimation(733);
        newWalkCmdSteps = 0;
        if(++newWalkCmdSteps > 50)
            newWalkCmdSteps = 0;
        int k = absX + i;
        k -= mapRegionX * 8;
        newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
        int l = absY + j;
        l -= mapRegionY * 8;
		isRunning2 = false;
		isRunning = false;
        //for(this.i = 0; this.i < newWalkCmdSteps; this.i++){
            newWalkCmdX[0] += k;
            newWalkCmdY[0] += l;
        // }
		//lastWalk = System.currentTimeMillis();
		//walkDelay = 1;
        poimiY = l;
        poimiX = k;
		isRunning2 = true;
		isRunning = true;
    }
		
	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x+y)));
		if (newWalkCmdSteps % 1 != 0)
			newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo command contains too many steps (" + newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		firstStepX -= mapRegionX*8;
		for (int i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
		}
		newWalkCmdX[0] = newWalkCmdY[0];
		int firstStepY = absY;
		firstStepY -= mapRegionY*8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1));
		for (int q = 0; q < newWalkCmdSteps; q++) {
			newWalkCmdX[q] += firstStepX;
			newWalkCmdY[q] += firstStepY;
		}
	}
		
	public void walk(int EndX, int EndY, int Emote) {
		walkToEmote(Emote);
		getPA().walkTo2(EndX, EndY);
	}
	
	public void walkToEmote(int id) {
		isRunning2 = false;
        playerWalkIndex = id;
		getPA().requestUpdates();
    }
	
	public void follow(int slot, int type, int distance){
		if (slot > 0 && slot == follow2 && type == 1 && follow2 > 0 && followDistance != distance && (/*usingOtherRangeWeapons || */usingBow || usingMagic))
			return;
		else if (slot > 0 && slot == followId2 && type == 0 && followId2 > 0 && followDistance >= distance && distance != 1)
			return;
		//else if (type == 3 && followId2 == 0 && follow2 == 0)
			//return;
		outStream.createFrame(174);
		if (freezeTimer > 0) {
			outStream.writeWord(0);
		} else {
			outStream.writeWord(slot);
			if (type == 0) {
				follow2 = 0;
				followId2 = slot;
				faceUpdate(followId2);
			} else if (type == 1) {
				followId2 = 0;
				follow2 = slot;
				faceUpdate(32768 + follow2);
			} else if (type == 3) {
				followId2 = 0;
				follow2 = 0;
				followDistance = 0;
				faceUpdate(65535);
			}
			followDistance = distance;
		}
		outStream.writeByte(type);
		outStream.writeWord(distance);
	}
	
	public synchronized Stream getInStream() {
		return inStream;
	}
	
	public synchronized int getPacketType() {
		return packetType;
	}
	
	public synchronized int getPacketSize() {
		return packetSize;
	}
	
	public synchronized Stream getOutStream() {
		return outStream;
	}
	
	public ItemAssistant getItems() {
		return itemAssistant;
	}
		
	public PlayerAssistant getPA() {
		return playerAssistant;
	}
		
	public ClueScroll getClue() {
		return clueScroll;
	}
	
	public DialogueHandler getDH() {
		return dialogueHandler;
	}
	
	public TradeLog getTradeLog() {
		return tradeLog;
	}

	public WarriorsGuild getWarriorsGuild() {
		return warriorsGuild;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	public Crafting getCrafting() {
		return crafting;
	}
	
	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}
	
	public CombatAssistant getCombat() {
		return combatAssistant;
	}
	
	public ActionHandler getActions() {
		return actionHandler;
	}

	public Summonin getSummon() {
		return summonin;
	}
  
	public PlayerKilling getKill() {
		return playerKilling;
	}
	
	public IoSession getSession() {
		return session;
	}
	
	public Potions getPotions() {
		return potion;
	}
	
	public PotionMixing getPotMixing() {
		return potionMixing;
	}
	
	public Food getFood() {
		return food;
	}
///~~~~~~~~~~~~~~~~~~~~~Skill Constructors~~~~~~~~~~~~~~~~~~~~~~
	public Slayer getSlayer() {
		return slayer;
	}
	
	public QuickCurses getQC() {
		return quickCurses;
	}
	
	public QuickPrayer getQP() {
		return quickPrayer;
	}
	
	public Banking getBank() {
		return bank;
	}
	
	public Summoning Summoning() {
		return Summoning;
	}
	
	public FlaxStringer getFlaxStringer() {
		return flax;
	}
	
	public Gambling getGamble() {
		return gamble;	
	}	
	
	public Agility getAgil() {
		return ag;
	}
	
	public Fishing getFishing() {
		return fish;
	}
	
	public Smithing getSmithing() {
		return smith;
	}
	
	public Farming getFarming() {
		return farming;
	}
	
	public Thieving getThieving() {
		return thieving;
	}
	
	public Herblore getHerblore() {
		return herblore;
	}
	
	public SmithingInterface getSmithingInt() {
		return smithInt;
	}
	
	public Prayer getPrayer() { 
		return prayer;
	}

	public Curse getCurse() { 
		return curse;
	}

	public Fletching getFletching() { 
		return fletching;
	}
	
	public Firemaking getFiremaking() {
		return firemaking;
	}
	
	public Dungeoneering getDungeoneering() {
		return dungeoneering;
	}

	/**
	* Gets the prospecting class.
	* @return The prospecting class.
	*/
	public Prospecting getProspecting() {
		return prospecting;
	}

	private Prospecting prospecting = new Prospecting();
}