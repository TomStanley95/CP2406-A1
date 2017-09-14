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
//        String message = "This is a direct message from the server";
//        outgoingDirectCommunication(message);
//        outgoingMultiCommunication(message);
//        incomingCommunication();
        receiveUserInput();



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
    public static void receiveUserInput() throws Exception {
        // Make server wait until the game starts
        System.out.println("Waiting for client to send a request ...");
        DatagramSocket incomingSocket = new DatagramSocket(incomingServerPort);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket incomingPacket = new DatagramPacket(messageBuffer, 1024);
        incomingSocket.receive(incomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println("Printing the raw incoming message");
        System.out.println(incomingMessage);
        incomingSocket.close();
        String cleanedMessage[] = cleanMessage(incomingMessage);
        String userName = cleanedMessage[0];
        String requestType = cleanedMessage[1];
        String userClientAddress = cleanedMessage[2];
        // Always catching the same message, response changes based on if statement, Split before statement
        if (requestType.equals("addUser")) {
            String response = GameServer.addUserToGame(userName);
            // This works
        }
            else{
            String response = "This is a test, we didn't trigger an add user response";
            DatagramSocket outgoingSocket = new DatagramSocket(outgoingServerPort);
            InetAddress userClient = InetAddress.getByName(userClientAddress);
            DatagramPacket outgoingPacket = new DatagramPacket(response.getBytes(), response.length(), userClient, incomingClientPort);
            outgoingSocket.send(outgoingPacket);
            outgoingSocket.close();
        }


    }
    public static String[]  cleanMessage(String message){
        String[] userMessage = message.split(",");
        return userMessage;

    }

}
