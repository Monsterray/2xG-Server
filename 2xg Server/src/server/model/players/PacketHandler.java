package server.model.players;

import server.model.players.packets.AttackPlayer;
import server.model.players.packets.Bank10;
import server.model.players.packets.Bank5;
import server.model.players.packets.BankAll;
import server.model.players.packets.BankX1;
import server.model.players.packets.BankX2;
import server.model.players.packets.ChallengePlayer;
import server.model.players.packets.ChangeAppearance;
import server.model.players.packets.ChangeRegions;
import server.model.players.packets.Chat;
import server.model.players.packets.ClanChat;
import server.model.players.packets.ClickItem;
import server.model.players.packets.ClickNPC;
import server.model.players.packets.ClickObject;
import server.model.players.packets.ClickingButtons;
import server.model.players.packets.ClickingInGame;
import server.model.players.packets.ClickingStuff;
import server.model.players.packets.Commands;
import server.model.players.packets.Dialogue;
import server.model.players.packets.DropItem;
import server.model.players.packets.FollowPlayer;
import server.model.players.packets.Guide;
import server.model.players.packets.IdleLogout;
import server.model.players.packets.ItemClick2;
import server.model.players.packets.ItemClick3;
import server.model.players.packets.ItemOnGroundItem;
import server.model.players.packets.ItemOnItem;
import server.model.players.packets.ItemOnNpc;
import server.model.players.packets.ItemOnObject;
import server.model.players.packets.MagicOnFloorItems;
import server.model.players.packets.MagicOnItems;
import server.model.players.packets.MoveItems;
import server.model.players.packets.PickupItem;
import server.model.players.packets.PrivateMessaging;
import server.model.players.packets.RemoveItem;
import server.model.players.packets.Report;
import server.model.players.packets.SilentPacket;
import server.model.players.packets.Trade;
import server.model.players.packets.Walking;
import server.model.players.packets.WearItem;


public class PacketHandler{

	private static PacketType packetId[] = new PacketType[256];
	
	static {
		SilentPacket u = new SilentPacket();
		packetId[3] = u; //click on or off the client
		packetId[202] = u; //happens every 2 min
		packetId[77] = u; //idk but its sent every 25 seconds
		packetId[86] = u; //turn your view in any direction
		packetId[78] = u; //
		packetId[36] = u; //
		packetId[226] = u; //idk repeats every min im loged in
		packetId[246] = u; //
		packetId[148] = u; //sent when there is an update going on
		packetId[183] = u; //
		packetId[230] = u; //
		packetId[136] = u; //
		packetId[189] = u; //
		packetId[152] = u; //
		packetId[200] = u; //
		packetId[85] = u; //
		packetId[165] = u; //
		packetId[238] = u; //
		packetId[150] = u; //
		packetId[40] = new Dialogue(); //click continue in diolog
		ClickObject co = new ClickObject();
		packetId[132] = co; //clicking objects
		packetId[252] = co; //second option of an object
		packetId[70] = co;
		packetId[57] = new ItemOnNpc(); //use item on a npc
		ClickNPC cn = new ClickNPC();
		packetId[72] = cn; //attack npc
		packetId[131] = cn; //use magic on a npc
		packetId[155] = cn; //click npc
		packetId[17] = cn; //mark npc, also trigers second click npc
		packetId[21] = cn;
		//packetId[14] = new ItemOnPlayer();
		packetId[16] = new ItemClick2(); //clicking on first special chioce of an item
		packetId[75] = new ItemClick3(); //click on second special choice of an item
		packetId[122] = new ClickItem(); //clicking on items in your inventory
		packetId[241] = new ClickingInGame(); //click any part of the client
		packetId[4] = new Chat(); //chat packets
		packetId[236] = new PickupItem(); //pick up an item on the ground
		packetId[87] = new DropItem(); //drop an item on the ground
		packetId[185] = new ClickingButtons(); //click choices in dialog or buttons
		packetId[130] = new ClickingStuff(); //close window, and other buttons
		packetId[103] = new Commands(); //commands, also useing clan chat
		packetId[214] = new MoveItems(); //moveing around items
		packetId[237] = new MagicOnItems();
		packetId[181] = new MagicOnFloorItems();
		packetId[202] = new IdleLogout(); //idle packet sent every 10 seconds
		AttackPlayer ap = new AttackPlayer();
		packetId[73] = ap; //attack player
		packetId[249] = ap; //be attacked by player
		packetId[128] = new ChallengePlayer();
		packetId[39] = new Trade(); //trade stuff
		packetId[139] = new FollowPlayer(); //follow someone
		packetId[41] = new WearItem();
		packetId[145] = new RemoveItem(); //take items off or move back and forth from the bank
		packetId[117] = new Bank5(); //take 5 bank-item//buy one thing
		packetId[43] = new Bank10(); //take 10 bank-item//buy 5 things
		packetId[129] = new BankAll(); //take all bank-item//buy 10 things
		packetId[101] = new ChangeAppearance(); //sent when clicking accept at changeing apearenc
		PrivateMessaging pm = new PrivateMessaging();
		packetId[188] = pm; //add a friend
		packetId[126] = pm; // sending a pm to a friend
		packetId[215] = pm; //remove a friend
		packetId[95] = pm;
		packetId[133] = pm;
		packetId[135] = new BankX1(); //send click x, and buy 50 things
		packetId[208] = new BankX2(); //sent when done typeing in x
		Walking w = new Walking();
		packetId[98] = w; //walk to something
		packetId[164] = w; //walk somewhere
		packetId[248] = w; //click on the map
		packetId[53] = new ItemOnItem(); //use an item on an item
		packetId[192] = new ItemOnObject(); //use an item on an object
		packetId[25] = new ItemOnGroundItem(); //use an item on an item on the ground
		ChangeRegions cr = new ChangeRegions();
		packetId[121] = cr; //request more map to be loaded
		packetId[210] = cr; //big request in land
		packetId[60] = new ClanChat(); //happens only when you start a clan
		//monsterrays packets
		packetId[234] = new Guide(); //packet sent by clicking guide on farming patches
		//packetId[218] = new ReportAbuse(); // sent when useing report abuse
		packetId[218] = new Report();
	}


	public static void processPacket(Client c, int packetType, int packetSize) {	
		if(packetType == -1) {
			return;
		}
		PacketType p = packetId[packetType];
		if(p != null) {
			try {
				///This is Packets that should never be shown
				if((packetType == 98) || (packetType == 148) || (packetType == 4) || (packetType == 210) || (packetType == 121) || (packetType == 117) || (packetType == 103) || (packetType == 202) || (packetType == 130) || (packetType == 248) || (packetType == 129) || (packetType == 164) || (packetType == 77) || (packetType == 86) || (packetType == 241)){
				}///This is some that are not so needed
				else if((packetType == 40) || (packetType == 185) || (packetType == 3) || (packetType == 218)){
					
				}else{
					System.out.println(c.playerName +" packet: " + packetType);
				}
				p.processPacket(c, packetType, packetSize);
			} catch(Exception e) {
				e.printStackTrace();
				c.disconnected = true; //this is for when the isaac gets out of wack the player will d
			}
		} else {
			System.out.println("Unhandled packet type: "+packetType+ " - size: "+packetSize);
		}
	}
	

}
