import javax.swing.*;
import java.util.Arrays;

public class GameApp {
    public static void main(String[] args) throws Exception {
        userMenu();


        // TODO put this in Test Client/Server
//        String userName = "Tron";
//        ClientCommunication.addUserToServer(userName);
//        ClientCommunication.addUserToServer(userName);


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
    public static void userMenu()throws Exception{
        System.out.println("What would you like to do\n1.Add User\n2.Remove User\n3.Vote for Grid Size\n4.Game Status");
        System.out.println("5.Save Score\n6.Play Game");
        String userChoiceString = JOptionPane.showInputDialog("");
        int userChoice = Integer.parseInt(userChoiceString);
        while (!Arrays.asList(1,2,3,4,5,6).contains(userChoice)){
            System.out.println("Invalid selection, please pick again");
            userChoiceString = JOptionPane.showInputDialog("");
            userChoice = Integer.parseInt(userChoiceString);
        }
        if (userChoice == 1){
            ClientCommunication.addUserToServer(getUserName());
        }
    }
}
