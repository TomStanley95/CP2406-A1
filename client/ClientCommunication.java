import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientCommunication {
    final static private String multicastAddress = "228.5.6.7";
    final static private int incomingServerPort = 49152 ;
    final static private int outgoingClientPort = 49153 ;
    final static private int incomingClientPort = 49154 ;
    public static void main(String[] args) throws Exception {
//        incomingCommunication();
//        incomingMultiCommunication();
        System.out.println(InetAddress.getLocalHost());


    }
    public static void incomingCommunication() throws Exception {
        System.out.println("Waiting for Server...");
        DatagramSocket socket = new DatagramSocket(incomingClientPort);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket IncomingPacket = new DatagramPacket(messageBuffer, 1024);
        socket.receive(IncomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println(incomingMessage);
        socket.close();
    }
    public static void incomingMultiCommunication() throws Exception{
        System.out.println("Waiting for the server...");
        MulticastSocket socket = new MulticastSocket(incomingClientPort);
        InetAddress multiServer = InetAddress.getByName(multicastAddress);
        socket.joinGroup(multiServer);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket IncomingPacket = new DatagramPacket(messageBuffer, 1024);
        socket.receive(IncomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println(incomingMessage);
        socket.leaveGroup(multiServer);
        socket.close();


    }
    public static void outgoingCommunication(String message) throws Exception {
        DatagramSocket socket = new DatagramSocket(outgoingClientPort);
        InetAddress gameServer = InetAddress.getLocalHost();
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), gameServer, incomingServerPort);
        socket.send(outgoingPacket);
        socket.close();
    }

    public static void addUserToServer(String name) throws Exception{
        String clientAddress = (InetAddress.getLocalHost()).toString();
        DatagramSocket outgoingSocket = new DatagramSocket(outgoingClientPort);
        InetAddress gameServer = InetAddress.getLocalHost();
        String message = name + "," + "addUser" + ","  +  clientAddress;
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), gameServer, incomingServerPort);
        outgoingSocket.send(outgoingPacket);
        outgoingSocket.close();
        System.out.println("Waiting for Server Response...");
        DatagramSocket incomingSocket = new DatagramSocket(incomingClientPort);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket incomingPacket = new DatagramPacket(messageBuffer, 1024);
        incomingSocket.receive(incomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println(incomingMessage);
        incomingSocket.close();




    }

}
