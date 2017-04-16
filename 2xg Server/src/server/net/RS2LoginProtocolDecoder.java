package server.net;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoFuture;
import org.apache.mina.common.IoFutureListener;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PlayerSave;
import server.util.ISAACRandomGen;

/**
 * Login protocol decoder.
 * @author Graham
 * @author Ryan / Lmctruck30 <- login Protocol fixes
 *
 */
public class RS2LoginProtocolDecoder extends CumulativeProtocolDecoder {
	
	@Override
	protected boolean doDecode(IoSession session, ByteBuffer in, ProtocolDecoderOutput out) {
			synchronized(session) {
				Object loginStageObj = session.getAttribute("LOGIN_STAGE");
				int loginStage = 0;
				if(loginStageObj != null) {
					loginStage = (Integer)loginStageObj;
				}
				//Logger.log("recv login packet, stage: "+loginStage);
				switch(loginStage) {
				case 0:
					if(2 <= in.remaining()) {
						int protocol = in.get() & 0xff;
						@SuppressWarnings("unused")
						int nameHash = in.get() & 0xff;
						if(protocol == 14) {
							long serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32) + (long) (java.lang.Math.random() * 99999999D);
						    StaticPacketBuilder s1Response = new StaticPacketBuilder();
						    s1Response.setBare(true).addBytes(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 }).addByte((byte) 0).addLong(serverSessionKey);
						    session.setAttribute("SERVER_SESSION_KEY", serverSessionKey);
						    session.write(s1Response.toPacket());
						    session.setAttribute("LOGIN_STAGE", 1);
						}
						return true;
					} else {
						in.rewind();
						return false;
					}
				case 1:
					@SuppressWarnings("unused")
					int loginType = -1, loginPacketSize = -1, loginEncryptPacketSize = -1;
					if(2 <= in.remaining()) {
						loginType = in.get() & 0xff; //should be 16 or 18
						loginPacketSize = in.get() & 0xff;
						loginEncryptPacketSize = loginPacketSize-(36+1+1+2);
						if(loginPacketSize <= 0 || loginEncryptPacketSize <= 0) {
							System.out.println("Zero or negative login size.");
							session.close();
							return false;
						}
					} else {
						in.rewind();
						return false;
					}
					if(loginPacketSize <= in.remaining()) {
						int magic = in.get() & 0xff;
						int version = in.getUnsignedShort();
						//System.out.println(magic+"+"+version);
						//if(magic != Config.CLIENT_MAGIC) {
							//System.out.println("Wrong magic id.");
						//	session.close();
						//	return false;
						//}
						if(version != 1) {
							//Dont Add Anything
						}
						int lowMem = in.get() & 0xff;
						for(int i = 0; i < 9; i++) {
							in.getInt();
						}
						loginEncryptPacketSize--;
						if(loginEncryptPacketSize != (in.get() & 0xff)) {
							System.out.println("Encrypted size mismatch.");
							
                                                        session.close();
							return false;
						}
						if((in.get() & 0xff) != 10) {
							System.out.println("Encrypted id != 10.");
							session.close();
							return false;
						}
						
						long clientSessionKey = in.getLong();
						long serverSessionKey = in.getLong();
 int uid = in.getInt();
						if(uid != 26342) {
							session.close();
							return false;
						}
						
						String name = readRS2String(in);
						String pass = readRS2String(in);
						int sessionKey[] = new int[4];
						sessionKey[0] = (int)(clientSessionKey >> 32);
						sessionKey[1] = (int)clientSessionKey;
						sessionKey[2] = (int)(serverSessionKey >> 32);
						sessionKey[3] = (int)serverSessionKey;
						ISAACRandomGen inC = new ISAACRandomGen(sessionKey);
						for(int i = 0; i < 4; i++) sessionKey[i] += 50;
						ISAACRandomGen outC = new ISAACRandomGen(sessionKey);
							load(session, uid, name, pass, inC, outC, version, lowMem);
						// WorkerThread.load(session, name, pass, inC, outC);
						session.getFilterChain().remove("protocolFilter");
						session.getFilterChain().addLast("protocolFilter", new ProtocolCodecFilter(new GameCodecFactory(inC)));
						return true;
					} else {
						in.rewind();
						return false;
					}
				}
			}
		return false;
	}

	private synchronized void load(final IoSession session, final int uid, String name, String pass, final ISAACRandomGen inC, ISAACRandomGen outC, final int version, final int lowMem) {
		session.setAttribute("opcode", -1);
		session.setAttribute("size", -1);
		int loginDelay = 1;
		int returnCode = 2;
		boolean close = false;
		
		//if(!Config.SERVER_OFFICAL) {
		//	if(!name.contains("lmc")) {
		//		returnCode = 14;
		//	}
		//}
		
		name = name.trim();
		name = name.toLowerCase();
		pass = pass.toLowerCase();
		
		if(name.contains("  ") || name.contains("   ") || name.contains("    ") || name.contains("     ") || name.contains("      ") || name.contains("       ") || name.contains("        ") || name.contains("         ") || name.contains("          ") || name.contains("           ") || name.contains("            ")) {
			returnCode = 3;
		}
		
		/*for (String s : Server.bannedNames) {
			if (s == "")
				break;
			if (name.contains(s.toLowerCase())) {
				returnCode = 3;
				break;
			}
		}*/
		
		if(!name.matches("[A-Za-z0-9 ]+")) {
			returnCode = 3;
		}
		
		if(name.length() > 12) {
			returnCode = 8;
		}
		
		Client cl = new Client(session, -1);
	//	cl.verified = true;
		cl.playerName = name;
		cl.playerName2 = cl.playerName;
		cl.playerPass = pass;
		cl.setInStreamDecryption(inC);
		cl.setOutStreamDecryption(outC);
		cl.outStream.packetEncryption = outC;
				
		cl.saveCharacter = false;
		
		//cl.properName = Misc.capitalize(name);
		
	/*	if(Server.infractionManager.banList.contains(cl.playerName.toLowerCase())) {
			returnCode = 4;
		}
		*/
		if(Server.playerHandler.isPlayerOn(name)) {
			returnCode = 5;
		}
	/*	if(Config.CLIENT_UID != uid) {
			returnCode = 6;
		}
		/*
                	if(LoginCheck.isLoggedIn(cl)) {
			returnCode = 5;
		}
                        */
                
		//if(Config.CLIENT_VERSION != version) {
		//	returnCode = 6;
		//}
		/*
		if(Config.CLIENT_UID != uid) {
			returnCode = 6;
		}
		*/
		//if(Config.CLIENT_HEX != lowMem) {
		//	returnCode = 6;
		//}
		
		if(Server.playerHandler.getPlayerCount() >= Config.MAX_PLAYERS) {
			returnCode = 7;
		}
		
//		Login Limit Exceeded
//		if() {
//			returnCode = 9;
//		}
		
		if(Server.UpdateServer) {
			returnCode = 14;
		}
		
		if(Connection.isNamedBanned(cl.playerName)) {
			returnCode = 4;
		}
		
/*	if(Connection.checkLoginList(loginIp)) {
			returnCode = 16;
		}
	*/	
//		Just Left World Login Delay Included
//		if() {
//			returnCode = 21;
//		}
		
		if(returnCode == 2) {
			int load = PlayerSave.loadGame(cl, cl.playerName, cl.playerPass);
			if (load == 0) {
				if(name.contains("admin") || name.contains("mod")) {
					load = 3;
				} else {
					cl.addStarter = true;
				}
			}
			if(load == 3) {
				returnCode = load;
				cl.saveFile = false;
				close = true;
			} else {
				for(int i = 0; i < cl.playerEquipment.length; i++) {
					if(cl.playerEquipment[i] == 0) {
						cl.playerEquipment[i] = -1;
						cl.playerEquipmentN[i] = 0;
					}
				}
				if(!Server.playerHandler.newPlayerClient(cl)) {
					returnCode = 7;
					cl.saveFile = false;
					close = true;
				} else {
					cl.saveFile = true;
				}
				
			}
                        
		} else {
			close = true;
		}
		
		StaticPacketBuilder bldr = new StaticPacketBuilder();
		bldr.setBare(true);
		bldr.addByte((byte) returnCode);
		if(returnCode == 2) {
			cl.saveCharacter = true;
			if(cl.playerRights > 0) {
				if(cl.playerRights != 3)
					bldr.addByte((byte) cl.playerRights);
				else
					bldr.addByte((byte) 2);
			//} else if(cl.donator) {
			//	bldr.addByte((byte) 4);
			} else {
				bldr.addByte((byte) 0);
			}
		} else if(returnCode == 21) {
			bldr.addByte((byte) loginDelay);
		} else {
			bldr.addByte((byte) 0);
		}
		bldr.addByte((byte) 0);
		Packet pkt = bldr.toPacket();
		session.setAttachment(cl);
		final Client fcl = cl;
		final boolean fclose = close;
		session.write(pkt).addListener(new IoFutureListener() {
			@Override
			public void operationComplete(IoFuture arg0) {
				if(fclose) {
					if(fcl != null) {
						Server.playerHandler.players[fcl.playerId] = null;
					}
					session.close();
				} else {
					fcl.isActive = true;
					session.getFilterChain().remove("protocolFilter");
					session.getFilterChain().addFirst("protocolFilter", new ProtocolCodecFilter(new GameCodecFactory(inC)));				
				}
			}
		});
               
	}

	private synchronized String readRS2String(ByteBuffer in) {
		StringBuilder sb = new StringBuilder();
		byte b;
		while((b = in.get()) != 10) {
			sb.append((char) b);
		}
		return sb.toString();
	}



	/**
	 * Releases the buffer used by the given session.
	 *
	 * @param session The session for which to release the buffer
	 * @throws Exception if failed to dispose all resources
	 */
	@Override
	public void dispose(IoSession session) throws Exception {
		super.dispose(session);
	}

}
