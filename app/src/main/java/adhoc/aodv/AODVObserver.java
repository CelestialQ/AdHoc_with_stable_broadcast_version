package adhoc.aodv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Observable;
import java.util.Observer;
//import java.util.Timer;

import adhoc.aodv.Node.MessageToObserver;
import adhoc.aodv.Node.PacketToObserver;
import android.util.Log;

public class AODVObserver implements Observer {
	
	private final static String MSG_TAG = "AdHoc --> AODVObserver";
	private boolean flag = true;
	private File file = null;
	private RandomAccessFile ranfile = null;
	private int counts = 0;
	
	public AODVObserver(Node node) {
		node.addObserver(this);
	}

	@Override
	public void update(Observable o, Object org) {
		// TODO Auto-generated method stub
		MessageToObserver msg = (MessageToObserver)org;
//		int userPacketID;
//		int destination;
		int type = msg.getMessageType();
		switch(type) {
			case ObserverConst.ROUTE_ESTABLISHMENT_FAILURE:
				Log.d(MSG_TAG, "route establishment failure !!!");
//				System.out.println("route establishment failure !!!");
				break;
			case ObserverConst.DATA_RECEIVED:
//				Log.d(MSG_TAG, "data received ...");
//				counts ++;
//				System.out.println(counts);
				PacketToObserver packet = (PacketToObserver)org;
				int senderNode = packet.getSenderNodeAddress();
				byte[] data = (byte[])packet.getContainedData();
				
				if(data.length <= 5120) {
					if(flag) {
						String filename = new String(data);
						File file = new File("/sdcard/", filename);
						try {
							System.out.println(file.createNewFile());
							ranfile = new RandomAccessFile(file, "rw");
							flag = false;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					else {
						try {
							ranfile.write(data);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						flag = true;
					}
				}
				else {
					try {
						ranfile.write(data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				System.out.println("data received ...");
				break;
				
			case ObserverConst.BROADCAST_DATA_RECEIVED:
				PacketToObserver broadcastPacket = (PacketToObserver)org;
				int broadcastSenderNode = broadcastPacket.getSenderNodeAddress();
				byte[] broadcastData = (byte[])broadcastPacket.getContainedData();
				System.out.println(new String(broadcastData) + " message from node : " + broadcastSenderNode);
				break;
			case ObserverConst.INVALID_DESTINATION_ADDRESS:
				Log.d(MSG_TAG, "invalid destination address");
//				System.out.println("invalid destination address");
				break;
			case ObserverConst.DATA_SIZE_EXCEEDES_MAX:
				Log.d(MSG_TAG, "data size exceedes max ...");
//				System.out.println("data size exceedes max ...");
				break;
			case ObserverConst.ROUTE_INVALID:
				Log.d(MSG_TAG, "route invalid");
//				System.out.println("route invalid");
				break;
			case ObserverConst.ROUTE_CREATED:
				Log.d(MSG_TAG, "route created ...");
//				System.out.println("route created ...");
				break;
			default:
				break; 
		}
	}

}
