package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.RuneCraft;
import server.util.Misc;

/**
 * Item Click 3 Or Alternative Item Option 1
 * 
 * @author
 * 
 * Proper Streams
 */

public class ItemClick3 implements PacketType {

	public void summon(Client c, int npcid){
		if(c.hasFollower < 1){
			c.Summoning().SummonNewNPC(npcid);
			c.itsover = false;
			c.deletedone = false;
		} else {
			c.sendMessage("You already have a NPC summoned");
			c.sendMessage("To dismiss it you need to click on the summoning Stat icon");
			c.itsover = false;
			c.deletedone = false;
		}
	}

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId11 = c.getInStream().readSignedWordBigEndianA();
		int itemId1 = c.getInStream().readSignedWordA();
		int itemId = c.getInStream().readSignedWordA();
		if(!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		c.s = itemId;     
		switch (itemId) {
			case 4155:
				c.sendMessage("You currently have "+c.pcPoints+" 2xG points.");
			break;
			
			case 995:
				int cashAmount = c.getItems().getItemAmount(995);
				if (c.inWild()) {
					c.sendMessage("You cannot do this in the wilderness");
					c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
					return;
				}
				if(c.MoneyCash == 2147483647) {
					c.sendMessage("Your pouch is full!");
					return;
				}
				if ((c.MoneyCash + cashAmount) <= Config.MAXITEM_AMOUNT && (c.MoneyCash + cashAmount) > -1) {
					if(cashAmount == 1) {
						c.sendMessage("You add 1 coin to your pouch.");
					} else  {
						c.sendMessage("You add "+cashAmount+" coins to your pouch.");
					}
					c.MoneyCash += cashAmount;
					c.getItems().deleteItem(995, cashAmount);
					if(c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
						c.getPA().sendFrame126(""+c.MoneyCash/1000+"K", 8134);
					} else if(c.MoneyCash > 999999 && c.MoneyCash <= 100000000) {
						c.getPA().sendFrame126(""+c.MoneyCash/1000000+"M", 8134);
					} else if(c.MoneyCash > 1000000000 && c.MoneyCash <= 2147483647) {
						c.getPA().sendFrame126(""+c.MoneyCash/1000000000 +"."+c.MoneyCash/1000000+"B", 8134);
					} else {
						c.getPA().sendFrame126(""+c.MoneyCash+"", 8134); 
					}
					c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
					return;
				}
				int Joker = c.MoneyCash-2147483647-cashAmount;
				int DisIs = c.MoneyCash+cashAmount-2147483647;
				int cash = c.MoneyCash;
				if((c.MoneyCash + cashAmount) < 2147483647) {
					cash += cashAmount;
					c.getItems().deleteItem(995, cashAmount);
					c.getItems().addItem(995, c.MoneyCash+cashAmount-2147483647);
					cash = c.MoneyCash;
					if(DisIs == 1) {
						c.sendMessage("You add 1 coin to your pouch.");
					} else  {
						c.sendMessage("You add "+Joker+" coins to your pouch.");
					}
					c.MoneyCash = 2147483647;
					if(c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
						c.getPA().sendFrame126(""+c.MoneyCash/1000+"K", 8134); 
					} else if(c.MoneyCash > 999999 && c.MoneyCash <= 100000000) {
						c.getPA().sendFrame126(""+c.MoneyCash/1000000+"M", 8134); 
					} else if(c.MoneyCash > 1000000000 && c.MoneyCash <= 2147483647) {
						c.getPA().sendFrame126(""+c.MoneyCash/1000000000 +"."+c.MoneyCash/1000000+"B", 8134);
					} else {
						c.getPA().sendFrame126(""+c.MoneyCash+"", 8134);
					}
					c.getPA().sendFrame126(""+(c.MoneyCash*-1)+"", 8135);
					return;
				}
			break;
		
			case 1710:
				c.getPA().handleGlory(itemId);
				c.gloryValue = 2;
			break;
			
			case 1708:
				c.getPA().handleGlory(itemId);
				c.gloryValue = 1;
			break;
			
			case 1706:
				c.getPA().handleGlory(itemId);
				c.gloryValue = 0;
			break;
			
			case 1712:
				c.getPA().handleGlory(itemId);
				c.gloryValue = 3;
			break;
			
			case 1438:// Air Talisman
				RuneCraft.locate(c, 2985, 3292);
				c.getPA().startTeleport(2841, 4830, 0, "modern");
			break;
			
			case 1440:// Earth Talisman
				RuneCraft.locate(c, 3306, 3474);
				c.getPA().startTeleport(2655, 4830, 0, "modern");
			break;
				
			case 1442:// Fire Talisman
				RuneCraft.locate(c, 3313, 3255);
				c.getPA().startTeleport(2574, 4848, 0, "modern");
			break;
				
			case 1444:// Water Talisman
				RuneCraft.locate(c, 3185, 3165);
				c.getPA().startTeleport(2727, 4833, 0, "modern");
			break;
			
			case 1446:// Body Talisman
				RuneCraft.locate(c, 3053, 3445);
				c.getPA().startTeleport(2522, 4825, 0, "modern");
			break;
			
			case 1448:// Mind Talisman
				RuneCraft.locate(c, 2982, 3514);
				c.getPA().startTeleport(2792, 4827, 0, "modern");
			break;
			
			case 15707:
			case 18817:
			case 18823:
			case 18821:
				c.getPA().showInterface(29799);
			break;
			
			case 12007:
				summon(c, 6795);
			break;
			
			case 12009:
				summon(c, 6797);
			break;

			case 12011:

			summon(c, 6799);
			break;

			case 12013:

			summon(c, 6801);
			break;

			case 12015:

			summon(c, 6803);
			break;

			case 12017:

			summon(c, 6805);
			break;


			case 12019:

			summon(c, 6807);
			break;


			case 12021:

			summon(c, 6808);
			break;

			case 12023:

			summon(c, 6810);
			break;

			case 12025:

			summon(c, 6812);
			break;

			case 12027:

			summon(c, 6993);
			break;

			case 12029:

			summon(c, 6814);
			break;

			case 12031:

			summon(c, 6816);
			break;


			case 12033:

			summon(c, 6817);
			break;

			case 12035:

			summon(c, 6819);
			break;


			case 12037:

			summon(c, 6821);
			break;

			case 12039:

			summon(c, 6823);
			break;

			case 12041:

			summon(c, 6824);
			break;


			case 12043:
				summon(c, 6826);
			break;

			case 12045:
				summon(c, 6828);
			break;

			case 12047:
				summon(c, 6830);
			break;

			case 12049:
				summon(c, 6832);
			break;
			
			case 12051:
				summon(c, 6834);
			break;	

			case 12053:
				summon(c, 6836);
			break;

			case 12055:
				summon(c, 6838);
			break;

			case 12057:
				summon(c, 6840);
			break;

			case 12059:
				summon(c, 6842);
			break;
			
			case 12061:
				summon(c, 6844);
			break;

			case 12063:
				summon(c, 6995);
			break;
			
			case 12065:
				summon(c, 6846);
			break;

			case 12067:
				summon(c, 6848);
			break;

			case 12069:
				summon(c, 6850);
			break;


			case 12071:

			summon(c, 6852);
			break;


			case 12073:

			summon(c, 6854);
			break;

			case 12075:

			summon(c, 6856);
			break;
			case 12077:

			summon(c, 6858);
			break;
			case 12079:

			summon(c, 6860);
			break;

			case 12081:

			summon(c, 6862);
			break;
			case 12083:

			summon(c, 6864);
			break;
			case 12085:

			summon(c, 6866);
			break;
			case 12087:

			summon(c, 6868);
			break;
			case 12089:

			summon(c, 6870);
			break;
			case 12091:

			summon(c, 6872);
			break;
			case 12093:

			summon(c, 6874);
			break;
			case 12123:

			summon(c, 6890);
			break;
			case 12776:

			summon(c, 7330);
			break;

			case 12778:

			summon(c, 7332);
			break;

			case 12780:

			summon(c, 7334);
			break;
			case 12782:

			summon(c, 7336);
			break;
			case 12784:

			summon(c, 7338);
			break;
			case 12786:

			summon(c, 7340);
			break;
			case 12788:

			summon(c, 7342);
			break;
			case 12790:

			summon(c, 7344);
			break;
			case 12792:

			summon(c, 7346);
			break;
			case 12794:

			summon(c, 7348);
			break;
			case 12796:

			summon(c, 7350);
			break;
			
			case 12798:
				summon(c, 7352);
			break;
			
			case 12800:
				summon(c, 7354);
			break;
			
			case 12802:
				summon(c, 7356);
			break;
			
			case 12804:
				summon(c, 7358);
			break;
			
			case 12806:
				summon(c, 7360);
			break;
			
			case 12808:
				summon(c, 7362);
			break;
			
			case 12810:
				summon(c, 7364);
			break;
			
			case 12812:
				summon(c, 7366);
			break;
			
			case 12814:
				summon(c, 7368);
			break;
			
			case 12816:
				summon(c, 7378);
			break;
			
			case 12818:
				summon(c, 7371);
			break;

			case 12820:
				summon(c, 7373);
			break;
			
			default:
				if (c.playerRights == 3)
					Misc.println(c.playerName+ " - Item3rdOption: "+itemId+" : "+itemId11+" : "+itemId1);
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Item ID:"+ itemId +"     ItemClick3");
			break;
		}

	}

}
