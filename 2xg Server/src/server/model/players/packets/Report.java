package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Content.ReportHandler;

public class Report implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		try {
			ReportHandler.handleReport(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}