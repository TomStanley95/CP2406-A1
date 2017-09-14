import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServerCommunication {
    final static private String multicastAddress = "228.5.6.7";
    final static private int outgoingServerPort = 49151 ;
    final static private int incomingServerPort = 49152 ;
    final static private int incomingClientPort = 49154 ;
    public static void main(String[] args) throws Exception {
        String message = "This is a direct message from the server";
        outgoingDirectCommunication(message);
//        outgoingMultiCommunication(message);
//        incomingCommunication();


    }
    public static void incomingCommunication() throws Exception{
        InetAddress localIP = InetAddress.getLocalHost();
        System.out.println("Waiting for client...");
        DatagramSocket socket = new DatagramSocket(incomingServerPort);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket IncomingPacket = new DatagramPacket(messageBuffer, 1024);
        socket.receive(IncomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println(incomingMessage);
        socket.close();



    }
    public static void outgoingDirectCommunication(String message) throws Exception {
        DatagramSocket socket = new DatagramSocket(outgoingServerPort);
        InetAddress gameServer = InetAddress.getLocalHost();
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), gameServer, incomingClientPort);
        socket.send(outgoingPacket);
        socket.close();

    }
    public static void outgoingMultiCommunication(String message) throws Exception {

        MulticastSocket socket = new MulticastSocket(outgoingServerPort);
        InetAddress multiServer = InetAddress.getByName(multicastAddress);
        socket.joinGroup(multiServer);
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), multiServer, incomingClientPort);
        socket.send(outgoingPacket);
        socket.leaveGroup(multiServer);
        socket.close();

    }
}
