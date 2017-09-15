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
        System.out.println("Printing the servers response");
        System.out.println(incomingMessage);
        incomingSocket.close();




    }

}
