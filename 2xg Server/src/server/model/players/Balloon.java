package server.model.players;

import java.awt.Point;
import java.util.Random;

import server.Server;
import server.model.objects.Objects;

public class Balloon extends Objects {
	static Random r = new Random();
	public int item, amount;
	public int x, y;

	public Balloon(int id, int x, int y, int height, int face, int type, int ticks, int item, int amount){
		super(id, x, y, height, face, type, ticks);
		this.x = x;
		this.y = y;
		this.item = item;
		this.amount = amount;
	}

	public void pop(Client c){
		PartyRoom.coords.remove(getCoords());
		Balloon empty = remove(x, y);
		Server.itemHandler.createGroundItem(c, item, x, y, amount, c.playerId);
		item = 0;
		amount = 0;
		Server.objectHandler.addObject(empty);
		Server.objectHandler.placeObject(empty);
		c.startAnimation(794);
	}

	public Point getCoords(){
		return new Point(x, y);
	}

	public static Balloon getBalloon(int item, int amount){
		return new Balloon(115+r.nextInt(5), (2730+r.nextInt(13)), (3462+r.nextInt(13)), 0, 0, 10, 0, item, amount);
	}

	public static Balloon getEmpty(){
		return new Balloon(115+r.nextInt(5), (2730+r.nextInt(13)), (3462+r.nextInt(13)), 0, 0, 10, 0, 0, 0);
	}

	public static Balloon remove(int x, int y){
		return new Balloon(-1, x, y, 0, 0, 10, 0, 0, 0);
	}
	

}