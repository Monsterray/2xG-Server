package server.world;

import java.util.ArrayList;

import server.Server;
import server.model.objects.Object;
import server.model.objects.Objects;
import server.model.players.Client;
import server.util.Misc;

/**
 * 
 */

public class ObjectManager {
	
	private int[][] customObjects = {{}};
	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			//c.getFarming().updateHerbPatch();
		}
	}
	
	public void loadCustomSpawns(Client c) {
		Objects[] stuffsObject = Server.objectHandler.fileObjects;
		for(int i = 0; i <= stuffsObject.length - 1; i++){
			System.out.println(stuffsObject.length);
			if(stuffsObject[i] != null)
				c.getPA().checkObjectSpawn(stuffsObject[i].objectId, stuffsObject[i].objectX, stuffsObject[i].objectY, stuffsObject[i].objectFace, stuffsObject[i].objectType);
			else{
				//System.out.println("lol null");
			}
		}
			
	///~~~~~~~~~~~~~~~~~~~~~~~Edgeville Object Section~~~~~~~~~~~~~~~~~~~~~~
		c.getPA().checkObjectSpawn(-1, 3084, 3502, 1, 10);  //REMOVE town: well
		c.getPA().checkObjectSpawn(-1, 3090, 3503, -1, 10); //REMOVE town: tree
		c.getPA().checkObjectSpawn(-1, 3090, 3494, -1, 10); //REMOVE bank: CHAIR
		c.getPA().checkObjectSpawn(-1, 3090, 3496, 0, 10);  //REMOVE bank: Chair
		c.getPA().checkObjectSpawn(-1, 3092, 3496, 0, 10);  //REMOVE bank: Chair
		c.getPA().checkObjectSpawn(-1, 3091, 3495, 0, 10);  //REMOVE bank: Table
		c.getPA().checkObjectSpawn(-1, 3095, 3499, -1, 10); //REMOVE bank: chair
		c.getPA().checkObjectSpawn(-1, 3095, 3498, -1, 10); //REMOVE bank: table
		c.getPA().checkObjectSpawn(-1, 3089, 3494, 0, 0);   //REMOVE bank: wall    yes it takes all of these to take out 4 peices of wall
		c.getPA().checkObjectSpawn(-1, 3089, 3495, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3089, 3496, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3089, 3497, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3494, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3495, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3495, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3496, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3497, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3497, 0, 10);  //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3090, 3497, 0, 0);   //REMOVE bank: wall
		c.getPA().checkObjectSpawn(-1, 3078, 3510, -1, 10); //REMOVE general shop: left table
		c.getPA().checkObjectSpawn(-1, 3080, 3510, -1, 10); //REMOVE general shop: boxes center
		c.getPA().checkObjectSpawn(-1, 3081, 3510, -1, 10); //REMOVE general shop: right table
		c.getPA().checkObjectSpawn(-1, 3077, 3512, -1, 10); //REMOVE general shop: stepladder
		//c.getPA().checkObjectSpawn(-1, 3098, 3496, -1, 10); //REMOVE Bank Deposit Box
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10); //REMOVE fenced building: stove
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10); //REMOVE fenced building: stove
		c.getPA().checkObjectSpawn(7315, 3083, 3499, 1, 10);//ADDED town: funpk teleporter
		c.getPA().checkObjectSpawn(411, 3095, 3506, 2, 10); //ADDED town: Curse Prayers
		c.getPA().checkObjectSpawn(409, 3092, 3506, 2, 10); //ADDED town: Normal praying altar
		c.getPA().checkObjectSpawn(410, 3098, 3503, 0, 10); //ADDED town: lunar altar
		c.getPA().checkObjectSpawn(6552, 3095, 3500, 0, 10);//ADDED town: ancient altar
		c.getPA().checkObjectSpawn(4874, 3089, 3483, 1, 10);//ADDED town: thiev crafting stall
		c.getPA().checkObjectSpawn(4875, 3088, 3483, 1, 10);//ADDED town: thiev food stall
		c.getPA().checkObjectSpawn(4876, 3087, 3483, 0, 10);//ADDED town: thiev general stall
		c.getPA().checkObjectSpawn(4877, 3086, 3483, 0, 10);//ADDED town: thiev magic stall
		c.getPA().checkObjectSpawn(4878, 3085, 3483, 0, 10);//ADDED town: thiev scimitar stall
		//c.getPA().checkObjectSpawn(-1, 3085, 3502, 1, 10);//REMOVE edge well
		
        //home removes + lunar isle
        c.getPA().checkObjectSpawn(-1, 2104, 3911, 1, 10);
        c.getPA().checkObjectSpawn(-1, 3088, 3509, 0, 10);
        c.getPA().checkObjectSpawn(-1, 3092, 3488, 0, 10);
        c.getPA().object(6951, 2583, 9896, 2, 10);
		c.getPA().object(6951, 2578, 9899, 2, 10);
		c.getPA().object(6951, 2572, 9895, 2, 10);
		c.getPA().object(6951, 2577, 9894, 2, 10);
		c.getPA().object(6951, 2580, 9894, 2, 10);
		c.getPA().object(6951, 2577, 9893, 2, 10);
		c.getPA().object(6951, 2578, 9893, 2, 10);
		c.getPA().object(6951, 2579, 9893, 2, 10);
		c.getPA().object(6951, 2580, 9893, 2, 10);
		c.getPA().object(6951, 2585, 9895, 2, 10);
		c.getPA().checkObjectSpawn(27254, 3214, 3212, 2, 10); //score
		//c.getPA().checkObjectSpawn(27256, 2861, 3547, 2, 10); //block
		c.getPA().checkObjectSpawn(16152, 2861, 3546, 1, 10); //block
		//dungeoneering
		c.getPA().checkObjectSpawn(4412, 3251, 9335, 1, 10);
		c.getPA().checkObjectSpawn(4412, 3233, 9334, 1, 10);
		c.getPA().checkObjectSpawn(4412, 3220, 9328, 1, 10);
		c.getPA().checkObjectSpawn(1, 3212, 9334, 1, 10);
		c.getPA().checkObjectSpawn(7318, 3212, 9333, 1, 10);
		c.getPA().checkObjectSpawn(1, 3207, 9329, 1, 10);
		c.getPA().checkObjectSpawn(1, 3207, 9328, 1, 10);
		c.getPA().checkObjectSpawn(1, 3230, 9332, 1, 10);
		c.getPA().checkObjectSpawn(1, 3229, 9332, 1, 10);
		c.getPA().checkObjectSpawn(1, 3223, 9334, 1, 10);
		c.getPA().checkObjectSpawn(1, 3223, 9333, 1, 10);
		c.getPA().checkObjectSpawn(6553, 3218, 9328, 2, 10);
		c.getPA().checkObjectSpawn(6553, 3218, 9327, 2, 10);
		c.getPA().checkObjectSpawn(-1, 3261, 9329, -1, 10);
		c.getPA().checkObjectSpawn(-1, 3233, 9322, -1, 10);
		c.getPA().checkObjectSpawn(409, 3238, 9315, 3, 10); // Normal praying altar
		c.getPA().checkObjectSpawn(410, 3229, 9315, 0, 10); //lunar altar
		c.getPA().checkObjectSpawn(-1, 3232, 9314, 2, 10); // Removing that lion thing in lobby
		c.getPA().checkObjectSpawn(-1, 3233, 9314, 2, 10); // Removing that chair thing in lobby
		c.getPA().checkObjectSpawn(-1, 3234, 9314, 2, 10); // Removing that lion thing in lobby
		c.getPA().checkObjectSpawn(6553, 3261, 9330, 1, 10);
		c.getPA().checkObjectSpawn(6553, 3260, 9330, 1, 10);
		c.getPA().checkObjectSpawn(6553, 3215, 9311, 2, 10);
		c.getPA().checkObjectSpawn(6553, 3215, 9310, 2, 10);
		c.getPA().checkObjectSpawn(6553, 3245, 9333, 2, 10);
		c.getPA().checkObjectSpawn(6553, 3245, 9334, 2, 10);
		c.getPA().checkObjectSpawn(411, 3230, 9320, 2, 10);
		c.getPA().checkObjectSpawn(4641, 3239, 9293, 2, 10);
		c.getPA().checkObjectSpawn(4641, 3239, 9294, 2, 10);
		c.getPA().checkObjectSpawn(4641, 3233, 9289, 2, 10);
		c.getPA().checkObjectSpawn(4641, 3232, 9289, 2, 10);
		c.getPA().checkObjectSpawn(4641, 3218, 9302, 2, 10);
		c.getPA().checkObjectSpawn(4641, 3218, 9303, 2, 10);
	//alkharid furnace and construction
		c.getPA().checkObjectSpawn(1277, 2048, 3244, 0, 10);
		c.getPA().checkObjectSpawn(13405, 3352, 3348, 0, 10);//home portal
		c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3244, 0, 10);
		c.getPA().checkObjectSpawn(2561, 2667, 3310, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2048, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2049, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3243, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2071, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3263, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2072, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3263, 0, 10);
		//end of trees
         
       
		//empty bulding spaces
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3247, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3248, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3249, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3250, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3251, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3252, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3253, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3254, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3255, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3256, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3257, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3258, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3270, 3161, 0, 10); // Mining Gold ore
		c.getPA().checkObjectSpawn(-1, 3271, 3161, 0, 10); // Mining Gold ore
		c.getPA().checkObjectSpawn(-1, 3270, 3163, 0, 10); // Mining Gold ore
		c.getPA().checkObjectSpawn(-1, 3271, 3163, 0, 10); // Mining Gold ore
		c.getPA().checkObjectSpawn(3994, 3270, 3162, 0, 10); // Mining Gold ore
	
	
	// end of al kharid bank furnaces
		c.getPA().checkObjectSpawn(2098, 3296, 3287, 0, 10); // Mining Gold ore
		c.getPA().checkObjectSpawn(2098, 3297, 3288, 0, 10); // Mining Gold ore
		
		c.getPA().checkObjectSpawn(2092, 3300, 3286, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3300, 3287, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3301, 3301, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3302, 3302, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3302, 3309, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3303, 3310, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3297, 3310, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2092, 3296, 3312, 0, 10); // Mining iron ore
		c.getPA().checkObjectSpawn(2100, 3303, 3313, 0, 10); // Mining silver ore
		c.getPA().checkObjectSpawn(2100, 3293, 3300, 0, 10); // Mining silver ore
		c.getPA().checkObjectSpawn(2100, 3294, 3301, 0, 10); // Mining silver ore
		c.getPA().checkObjectSpawn(2100, 3295, 3303, 0, 10); // Mining silver ore
		c.getPA().checkObjectSpawn(2100, 3303, 3312, 0, 10); // Mining silver ore
		c.getPA().checkObjectSpawn(2100, 3303, 3312, 0, 10); // Mining silver ore
		c.getPA().checkObjectSpawn(6551, 3297, 3311, 0, 10); // Mining teleport to eccense
		c.getPA().checkObjectSpawn(-1, 2574, 4850, -1, 10); //REMOVE fire altar teleport rc
	
	// START OF FARMIMG REMOVING PATCHES
	/*	c.getPA().checkObjectSpawn(-1, 2809, 3463, 0, 10); // Farming patch remove THE SINGLE ONE
		c.getPA().checkObjectSpawn(-1, 2806, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2819, 3463, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3466, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3466, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2803, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2803, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2809, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2809, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2814, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2814, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2813, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2813, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2812, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2812, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2810, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2810, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3461, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3461, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2812, 3459, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2812, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2809, 3460, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2813, 3460, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2813, 3459, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2810, 3460, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2810, 3459, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2814, 3460, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2814, 3459, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2809, 3459, 0, 10); // Farming patch remove
		c.getPA().checkObjectSpawn(-1, 2819, 3462, 0, 10); // Farming table remove
		*/
	//END OF FARMING
	
		//c.getPA().checkObjectSpawn(2644, 2737, 3445, 0, 10); //spinning wheel
		c.getPA().checkObjectSpawn(2152, 3451, 3514, 0, 10); //SUMMON OBELISK
	//DUEL ARENA FIX
	/*	c.getPA().checkObjectSpawn(1, 3360, 3268, 1, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3360, 3268, 0, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3360, 3269, 1, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3360, 3269, 0, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3374, 3269, 1, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3374, 3269, 0, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3374, 3268, 1, 10); //CRATES AIR ALTAR FIX
		c.getPA().checkObjectSpawn(1, 3374, 3268, 1, 10); //CRATES AIR ALTAR FIX*/

	//varrock
		c.getPA().checkObjectSpawn(14367, 3212, 3439, 2, 10); // VARROCK HOMEBANK 1
		c.getPA().checkObjectSpawn(14367, 3213, 3439, 2, 10); // VARROCK HOMEBANK 2
		c.getPA().checkObjectSpawn(411, 3215, 3438, 2, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(-1, 3217, 3436, -1, 10); //remove portal
		c.getPA().checkObjectSpawn(-1, 3220, 3437, -1, 10); //remove  stall varrock
		c.getPA().checkObjectSpawn(2403, 3091, 3487, 2, 10); //RFD CHEST
		c.getPA().checkObjectSpawn(12356, 3092, 3487, -1, 10); //RFD PORTAL
		c.getPA().checkObjectSpawn(8972, 3201, 3430, -1, 10); //RFD PORTAL
		c.getPA().checkObjectSpawn(409, 3209, 3438, 0, 10); // Normal praying altar
		c.getPA().checkObjectSpawn(410, 3226, 3428, 0, 10);
		c.getPA().checkObjectSpawn(6552, 3228, 3432, 0, 10);
		//thiev stalls
		c.getPA().checkObjectSpawn(4874, 2657, 3313, 1, 10);
		c.getPA().checkObjectSpawn(4875, 2658, 3313, 1, 10);
		c.getPA().checkObjectSpawn(4876, 2659, 3313, 0, 10);
		c.getPA().checkObjectSpawn(4877, 2660, 3313, 0, 10);
		c.getPA().checkObjectSpawn(4878, 2661, 3313, 0, 10);
	//end of thiev stalls continue of varrock city spawns
	
		c.getPA().checkObjectSpawn(7273, 3204, 3857, -1, 10); //remove portal

	//dung remove rope
		c.getPA().checkObjectSpawn(-1, 3508, 9494, -1, 10); //remove portal
		c.getPA().checkObjectSpawn(-1, 3508, 9494, -2, 10); //remove portal
		
	//start of RC altar fix
		c.getPA().checkObjectSpawn(-1, 2841, 4828, -1, 10); //remove portal
		//c.getPA().checkObjectSpawn(1, 2842, 4829, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(1, 2842, 4828, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(1, 2842, 4827, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(1, 2841, 4827, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(1, 2840, 4827, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(1, 2840, 4828, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(1, 2840, 4829, 1, 10); //CRATES AIR ALTAR FIX
		//c.getPA().checkObjectSpawn(-1, 2841, 4828, -1, 10); //CRATES AIR ALTAR FIX
	// END OF RUNECRAFT
	
	//START OF NOMAD MINIGAME
		c.getPA().checkObjectSpawn(400, 3487, 9948, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3488, 9948, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3493, 9938, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3493, 9937, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3482, 9937, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3481, 9937, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3480, 9937, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3477, 9945, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3477, 9944, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3477, 9943, 1, 10); //Nomad gravestones
		c.getPA().checkObjectSpawn(1, 2933, 9654, 1, 10); 	//Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2933, 9655, 1, 10); 	//Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2934, 9648, 1, 10); 	//Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2500, 3018, 1, 10); 	//Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2501, 3018, 1, 10); 	//Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2505, 3012, 1, 10); 	//Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2505, 3011, 1, 10); 	//Nomad CRATE
		//c.getPA().checkObjectSpawn(11, 2936, 9647, 1, 10); //Nomad ladder
		//c.getPA().checkObjectSpawn(11, 2936, 9655, 1, 10); //Nomad ladder
		//c.getPA().checkObjectSpawn(11, 2937, 9655, 1, 10); //Nomad ladder
		//c.getPA().checkObjectSpawn(11, 2938, 9655, 1, 10); //Nomad ladder
		//c.getPA().checkObjectSpawn(11, 2935, 9655, 1, 10); //Nomad ladder
		//c.getPA().checkObjectSpawn(11, 2934, 9655, 1, 10); //Nomad ladder
		c.getPA().checkObjectSpawn(11, 2498, 3014, 1, 10); //Nomad ladder
	//END OF NOMAD MINIGAME
	
	//START OF GOBLIN MINIGAME ANGRY
		c.getPA().checkObjectSpawn(672, 2539, 3032, 1, 10); //GOBLIN BARREL
		c.getPA().checkObjectSpawn(718, 2539, 3033, 1, 10); //GOBLIN BARREL
		c.getPA().checkObjectSpawn(2024, 2540, 3036, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2541, 3036, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2540, 3029, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2541, 3029, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2541, 3029, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2542, 3029, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2543, 3029, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2544, 3029, 1, 10); //GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2545, 3029, 1, 10); //GOBLIN ESCAPE
	//END OF GOBLIN MINIGAME
	
		c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2536, 4778, 0, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2537, 4777, 1, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2536, 4776, 2, 10); //warning sign donor
		c.getPA().checkObjectSpawn(7315, 2536, 4777, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(7316, 2605, 3153, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(4008, 2851, 2965, 1, 10); //spec alter
		c.getPA().checkObjectSpawn(195, 2980, 5111, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); //escape ladder
	//start of dungkill
		c.getPA().checkObjectSpawn(3379, 2410, 3531, 0, 10); //dung kill cave
	//end of dung kill portal
		c.getPA().checkObjectSpawn(4412, 2790, 9328, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3060, 5209, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3229, 9312, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2864, 9950, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2805, 4440, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2744, 4453, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3017, 5243, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2427, 9411, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2465, 2426, 9415, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3043, 9836, 0, 10);
		c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
		c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);
		c.getPA().checkObjectSpawn(12356, 2845, 2957, 1, 10);
		c.getPA().checkObjectSpawn(2403, 2844, 2957, 2, 10);
		c.getPA().checkObjectSpawn(2996, 2854, 2962, 1, 10);//al key chest
		c.getPA().checkObjectSpawn(12356, 2724, 3498, 0, 10);//rfd portal
		c.getPA().checkObjectSpawn(2403, 2725, 3498, 0, 10);//rfd chest
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	    c.getPA().checkObjectSpawn(14859, 2520, 4773, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4775, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 3298, 3308, 0, 10);//runite ore  mining spot
		c.getPA().checkObjectSpawn(14859, 2518, 4774, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(13617, 2527, 4770, 2, 10); //Barrelportal donor	
		c.getPA().checkObjectSpawn(13615, 2525, 4770, 2, 10); // hill giants donor
		c.getPA().checkObjectSpawn(13620, 2523, 4770, 2, 10); // steel drags donor
		c.getPA().checkObjectSpawn(13619, 2521, 4770, 2, 10); // tormented demons donor
		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);
		c.getPA().checkObjectSpawn(410, 2864, 2955, 1, 10); 
		
	// CAMELOT WOODCUTTING
		c.getPA().checkObjectSpawn(1281, 2712, 3465, 0, 10);//OAK TREE CAMELOT WC
		c.getPA().checkObjectSpawn(1277, 2709, 3466, 0, 10);//NORMAL TREE CAMELOT WC
		c.getPA().checkObjectSpawn(1306, 2710, 3459, 0, 10);//MAGIC TREE CAMELOT WC
	//end of camelot woodcutting
	
		c.getPA().checkObjectSpawn(4874, 2849, 2995, 1, 10);
		c.getPA().checkObjectSpawn(4875, 2849, 2996, 1, 10);
		c.getPA().checkObjectSpawn(4876, 2849, 2997, 0, 10);
		c.getPA().checkObjectSpawn(4877, 2849, 2998, 0, 10);
		c.getPA().checkObjectSpawn(4878, 2849, 2999, 0, 10);
		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 2842, 2954, 1, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 2852, 2950, 2, 10);
		c.getPA().checkObjectSpawn(409, 2530, 4779, 3, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4780, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4775, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);
							     //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1278, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2429, 9416, 0, 10);//cooking range dung!
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
		c.getPA().checkObjectSpawn(104, 2522, 4780, 1, 10); //Donatorchest
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
	/*	c.getPA().checkObjectSpawn(11356, 2836, 2977, 0, 10); 	//frost dragon portals
		c.getPA().checkObjectSpawn(1032, 2382, 4714, 1, 10); 	//warning sign FunPk
	//	c.getPA().checkObjectSpawn(11356, 3087, 3483, 0, 10); 	//Frost drags portal
		c.getPA().checkObjectSpawn(79, 3044, 5105, 1, 10); 		//dungie blocker
		c.getPA().checkObjectSpawn(4412, 1919, 4640, 0, 10); 	//escape ladder
		c.getPA().checkObjectSpawn(4412, 3048, 5233, 0, 10); 	//escape ladder
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
		c.getPA().checkObjectSpawn(411, 3374, 9806, 2, 10); 	//Curse Prayers 
		c.getPA().checkObjectSpawn(194, 2423, 3525, 0, 10);		//Dungeoneering Rock
		c.getPA().checkObjectSpawn(23, 2385, 4719, 0, 10);		//Dungeoneering sack
		c.getPA().checkObjectSpawn(23, 2384, 4719, 0, 10);		//Dungeoneering sack
		c.getPA().checkObjectSpawn(16081, 1879, 4620, 0, 10); 	//Dungeoneering lvl 1 tele
		c.getPA().checkObjectSpawn(2014, 1921, 4640, 0, 10); 	//Dungeoneering Money
		c.getPA().checkObjectSpawn(16078, 1869, 4622, 0, 10); 	//Dungeoneering Rope
		c.getPA().checkObjectSpawn(2930, 2383, 4714, 3, 10); 	//Dungeoneering Boss 1 door
		c.getPA().checkObjectSpawn(9767, 2000, 4636, 0, 10); 	//slave blocker floor 1
		c.getPA().checkObjectSpawn(9767, 2001, 4636, 0, 10); 	//slave blocker floor 1
		c.getPA().checkObjectSpawn(9767, 1999, 4637, 0, 10); 	//slave blocker floor 1
		c.getPA().checkObjectSpawn(9767, 2002, 4636, 0, 10); 	//slave blocker floor 1
		c.getPA().checkObjectSpawn(10778, 2867, 9530, 1, 10); 	//dung floor 4 portal
		c.getPA().checkObjectSpawn(7272, 3233, 9316, 1, 10); 	//dung floor 5 portal
		c.getPA().checkObjectSpawn(4408, 2869, 9949, 1, 10); 	//dung floor 6 portalEND
		c.getPA().checkObjectSpawn(410, 1860, 4625, 1, 10); 	//dung floor 6 portalEND
		c.getPA().checkObjectSpawn(9947, 1914, 4639, 0, 10); 	//pillar dung floor 1
		c.getPA().checkObjectSpawn(6552, 1859, 4617, 1, 10); 	//dung floor 6 portalEND
		c.getPA().checkObjectSpawn(7318, 2772, 4454, 1, 10); 	//dung floor 7 portalEND */
		
		if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		}else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
		}
	}
	
	public final int IN_USE_ID = 14825;
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}
}