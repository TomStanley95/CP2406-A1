import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class clientCommunication {
    final static int outgoingServerPort = 49151 ;
    final static int incomingServerPort = 49152 ;
    final static int outgoingClientPort = 49153 ;
    final static int incomingClientPort = 49154 ;
    public static void main(String[] args) throws Exception {


    }
    public static void incomingCommunication() {
        System.out.println("This is incoming comms");


    }
    public static void outgoingCommunication(String message) throws Exception {
        DatagramSocket socket = new DatagramSocket(outgoingClientPort);
        InetAddress gameServer = InetAddress.getLocalHost();
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), gameServer, incomingServerPort);
        socket.send(outgoingPacket);
        socket.close();
    }
}
