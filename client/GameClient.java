import javax.swing.*;

public class GameClient {
    public static void main(String[] args) throws Exception {
//  Commented out to make testing easier
// String userName = getUserName();
        String userName = "Tron";
        User user = new User(userName);
    ClientCommunication.outgoingCommunication(userName);
//    String newUserName = getUserName(userName);

    }

    public static String getUserName(){
        String userName = JOptionPane.showInputDialog("Welcome to place holder game name\nEnter your user name");
        return userName;
    }
    public static String getUserName(String currentUserName){
        // Overloaded method to allow the user to change their user name
        String userName = JOptionPane.showInputDialog("Enter a new user name");
        return userName;
    }
}
