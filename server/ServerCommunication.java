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
        receiveUserInput();



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
            serverResponse(response, userClientAddress);
        }
            else{
            String response = "This is a test, we didn't trigger an add user response";

        }


    }
    public static void serverResponse(String response, String userClientAddress) throws Exception{
        DatagramSocket outgoingSocket = new DatagramSocket(outgoingServerPort);
        String destinationAddress = resolveHostName(userClientAddress);
        InetAddress userClient = InetAddress.getByName(destinationAddress);
        DatagramPacket outgoingPacket = new DatagramPacket(response.getBytes(), response.length(), userClient, incomingClientPort);
        outgoingSocket.send(outgoingPacket);
        outgoingSocket.close();
    }

    public static String resolveHostName(String userClientAddress){
        String[] cleanedUserAddress = userClientAddress.split("/");
        return cleanedUserAddress[0];
    }
    public static String[]  cleanMessage(String message){
        String[] userMessage = message.split(",");
        return userMessage;

    }

}
