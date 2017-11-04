import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

public class ServerCommunication {
    final static private String multicastAddress = "228.5.6.7";
    final static private int outgoingServerPort = 49151 ;
    final static private int incomingServerPort = 49152 ;
    final static private int incomingClientPort = 49154 ;
    public static void main(String[] args) throws Exception {
        GameServer.generateUserStartingPositions();
        GameServer.addUserToGame("John");
        GameServer.addUserToGame("William");
        GameServer.addUserToGame("Magda");
        getGameUserNames();
        getGameStates();
        while (true){
            receiveUserInput();
        }
//TODO Improve the wait command by using two threads. one for receiving and handling input, one for updating game state
        // TODO Use getGameState() then sendGameState()


    }

    public static void receiveUserInput() throws Exception {
        System.out.println("Waiting for client to send a request ...");
        DatagramSocket incomingSocket = new DatagramSocket(incomingServerPort);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket incomingPacket = new DatagramPacket(messageBuffer, 1024);
        incomingSocket.receive(incomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println("Printing the raw incoming message");
        System.out.println(incomingMessage);
        incomingSocket.close();
        handleUserInput(incomingMessage);

    }
    public static void handleUserInput(String incomingMessage) throws Exception{
        String cleanedMessage[] = cleanMessage(incomingMessage);
        String userName = cleanedMessage[0];
        String requestType = cleanedMessage[1];
        String userClientAddress = cleanedMessage[2];
        // Always catching the same message, response changes based on if statement, Split before statement

        if (requestType.equals("addUser")) {
            String response = GameServer.addUserToGame(userName);
            serverResponse(response, userClientAddress);
        }
        else if(requestType.equals("removeUser")){
            String response = GameServer.removeUserFromGame(userName);
            serverResponse(response, userClientAddress);
        }


    }
    public static void serverResponse(String response, String userClientAddress) throws Exception{
        DatagramSocket outgoingSocket = new DatagramSocket(outgoingServerPort);
        String clientHostName = resolveHostName(userClientAddress);
        InetAddress userClient = InetAddress.getByName(clientHostName);
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
    public static void  getGameStates()throws Exception{
        //TODO Need to find an easier or more efficient way to send the light cycle game state, maybe the server calculates lines and just sends the end points of the line or the direction to add 1 to the original line, update the other lines for each user client side.
        // TODO Send and end of game state update - figure out a win condition, calculate if a user crossess a line?
// String lightCycleGameState = GameServer.getLightCyclesGameState();
//      sendGameState("light cycles", lightCycleGameState);
        String playerLocationsGameState = GameServer.getPlayerLocationsGameState();
        sendGameState("player locations", playerLocationsGameState);
    }
    public static void getGameUserNames() throws Exception{
        String playerNames = GameServer.getPlayerNames();
        sendGameState("player names", playerNames);
    }
    public static void sendGameState(String type, String dataToSend) throws Exception{
        String message = type + "/" + dataToSend;
        MulticastSocket socket = new MulticastSocket(outgoingServerPort);
        InetAddress multiServer = InetAddress.getByName(multicastAddress);
        socket.joinGroup(multiServer);
        DatagramPacket outgoingPacket = new DatagramPacket(message.getBytes(), message.length(), multiServer, incomingClientPort);
        socket.send(outgoingPacket);
        socket.leaveGroup(multiServer);
        socket.close();
    }
}
