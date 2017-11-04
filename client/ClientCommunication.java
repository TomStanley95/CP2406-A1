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
        while (true){
            receiveIncomingMultiCommunication();
        }


    }

    public static void addUserToServer(String name) throws Exception{
        String message = name + "," + "addUser" + ",";
        sendMessageServer(message);
    }
    public static void removeUserFromServer(String name) throws Exception{
        String message = name + "," + "removeUser" +",";
        sendMessageServer(message);
    }
    public static void sendMessageServer(String message) throws Exception{
        String clientAddress = (InetAddress.getLocalHost()).toString();
        message = message + clientAddress;
        DatagramSocket outgoingSocket = new DatagramSocket(outgoingClientPort);
        InetAddress gameServer = InetAddress.getLocalHost();
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), gameServer, incomingServerPort);
        outgoingSocket.send(outgoingPacket);
        outgoingSocket.close();
        receiveServerResponse();
    }
    public static void receiveServerResponse() throws Exception{
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

    public static void receiveIncomingMultiCommunication() throws Exception{
        System.out.println("Waiting for the server to send out a gamestate update...");
        MulticastSocket socket = new MulticastSocket(incomingClientPort);
        InetAddress multiServer = InetAddress.getByName(multicastAddress);
        socket.joinGroup(multiServer);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket IncomingPacket = new DatagramPacket(messageBuffer, 1024);
        socket.receive(IncomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println(incomingMessage);
        GameApp.handleMultiMessage(incomingMessage);
        socket.leaveGroup(multiServer);
        socket.close();


    }

}
