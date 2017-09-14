import javax.swing.*;

public class gameClient {
    public static void main(String[] args) throws Exception {
    String userName = getUserName();
    clientCommunication.outgoingCommunication(userName);
//    String newUserName = getUserName(userName);
    }

    public static String getUserName(){
        System.out.println("Welcome to place holder game name");
        String userName = JOptionPane.showInputDialog("Enter your user name");
        return userName;
    }
    public static String getUserName(String currentUserName){
        String userName = JOptionPane.showInputDialog("Enter a new user name");
        return userName;
    }
}
