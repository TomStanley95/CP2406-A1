import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class serverCommunication {
    final static int outgoingServerPort = 49151 ;
    final static int IncomingServerPort = 49152 ;
    final static int outgoingClientPort = 49153 ;
    final static int IncomingClientPort = 49154 ;
    public static void main(String[] args) throws Exception {
        InetAddress localIP = InetAddress.getLocalHost();
        System.out.println("My ip is: " + localIP.getHostAddress());
        DatagramSocket socket = new DatagramSocket(IncomingServerPort);
        byte[] messageBuffer = new byte[1024];
        DatagramPacket IncomingPacket = new DatagramPacket(messageBuffer, 1024);
        socket.receive(IncomingPacket);
        String incomingMessage = new String(messageBuffer).trim();
        System.out.println(incomingMessage);
        socket.close();
    }
    public static void incomingCommunication() {


    }
    public static void outgoingCommunication() {

    }
}
