package server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.model.players.Client;

public class MadTurnipConnection extends Thread {

	public static Connection con = null;
	public static Statement stm;

	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://SITENAME:3306/DATABASE", "USERNAME", "USERPASS");
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
	}
	
	public MadTurnipConnection(){
		
	}
	
	public void run() {
		while(true) {		
			try {
				if(con == null)
					createConnection(); 
				else
					ping();
				Thread.sleep(10000);//10 seconds
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ping(){
		try {
			String query = "SELECT * FROM donation WHERE username = 'null'";
			query(query);
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
	}
	
	public static void addDonateItems(final Client c,final String name){
		if(con == null){
			if(stm != null){
				try {
					stm = con.createStatement();
				} catch(Exception e){
					con = null;
					stm = null;
					//put a sendmessage here telling them to relog in 30 seconds
					return;
				}
			} else {
				//put a sendmessage here telling them to relog in 30 seconds
				return;
			}
		}
		new Thread(){
			@Override
			public void run(){
				try {
					String name2 = name.replaceAll(" ","_");
					String query = "SELECT * FROM donation WHERE username = '"+name2+"'";
					ResultSet rs = query(query);
					boolean b = false;
					while(rs.next()){
						int prod = Integer.parseInt(rs.getString("productid"));
						int price = Integer.parseInt(rs.getString("price"));
						if(prod == 1 && price == 6){ //DonatorRank
							c.playerRights = 4;
							c.isDonator = 1;
							b = true;
						} else if(prod == 2 && price == 10){//Superdonor
							c.playerRights = 4;
							c.isDonator = 1;
							c.issDonator = 1;
							b = true;
						} else if(prod == 3 && price == 5){ //AuraWngs
							c.getItems().addItem(13495,1);
							c.getItems().addItem(13492,1);
							b = true;
                                                } else if(prod == 4 && price == 25){ //ownerrank                                                                               } else if(prod == 4 && price == 100){ //Owner rank
							c.playerRights = 1;
							c.isDonator = 1;
							b = true;
						} else if(prod == 5 && price == 10){ //Phats
							c.getItems().addItem(1038,1);
							c.getItems().addItem(1040,1);
							c.getItems().addItem(1042,1);
							c.getItems().addItem(1044,1);
							c.getItems().addItem(1046,1);
							c.getItems().addItem(1048,1);
							b = true;
						} else if(prod == 6 && price == 5){ //Hween Masks
							c.getItems().addItem(1053,1);
							c.getItems().addItem(1055,1);
							c.getItems().addItem(1057,1);
							b = true;
						} else if(prod == 7 && price == 2){ // Hack3r santa :D
							c.getItems().addItem(1050,1);
							b = true;
						} else if(prod == 8 && price == 2){
							c.pcPoints += 500;
							b = true;
						} else if(prod == 9 && price == 1){ // Spin Tokens
							c.spinsLe += 10;
							b = true;
						} else if(prod == 10 && price == 2){//Spin tokens
							c.spinsLe += 25;
							b = true;
						} else if(prod == 11 && price == 4){//SpinTokens
							c.spinsLe += 100;
							b = true;
						} else if(prod == 12 && price == 5){ // void range
							c.getItems().addItem(8839,1);
							c.getItems().addItem(8840,1);
							c.getItems().addItem(8842,1);
							c.getItems().addItem(11664,1);
							b = true;
						} else if(prod == 13 && price == 5){//void meele
							c.getItems().addItem(8839,1);
							c.getItems().addItem(8840,1);
							c.getItems().addItem(8842,1);
							c.getItems().addItem(11665,1);
							b = true;
						} else if(prod == 14 && price == 5){//void mage
							c.getItems().addItem(8839,1);
							c.getItems().addItem(8840,1);
							c.getItems().addItem(8842,1);
							c.getItems().addItem(11663,1);
							b = true;
						} else if(prod == 15 && price == 2){//fightertorso
							c.getItems().addItem(10551,1);
							b = true;
						} else if(prod == 16 && price == 2){//redphat
							c.getItems().addItem(1038,1);
							b = true;
						} else if(prod == 17 && price == 2){//bluephat
							c.getItems().addItem(1042,1);
							b = true;
						} else if(prod == 18 && price == 2){//greenphat
							c.getItems().addItem(1044,1);
							b = true;
						} else if(prod == 19 && price == 2){//yellowphat
							c.getItems().addItem(1040,1);
							b = true;
						} else if(prod == 20 && price == 2){//purplephat
							c.getItems().addItem(1046,1);
							b = true;
						} else if(prod == 21 && price == 2){//whitephat
							c.getItems().addItem(1048,1);
							b = true;
						} else if(prod == 22 && price == 2){//double xp ring
							c.getItems().addItem(15017,1);
							b = true;
						}
					}
					if(b){
						query("DELETE FROM `donation` WHERE `username` = '"+name2+"';");
							c.sendMessage("You have received your donation set.");
							c.sendMessage("Thank-you for donating!");
							c.SaveGame();
							c.saveCharacter = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
					con = null;
					stm = null;
				}
			}
		}.start();
	}
	
	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stm.executeQuery(s);
				return rs;
			} else {
				stm.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
		return null;
	}
}