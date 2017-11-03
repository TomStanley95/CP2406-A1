import javax.swing.*;


public class GameApp {

    public static void main(String[] args) throws Exception {
        String userName = null;
        String newUserName;
        int userChoice = userMenu();
        while (userChoice != 7) {
            if (userChoice == 1) {
                userName = getUserName();
                ClientCommunication.addUserToServer(userName);
            } else if (userChoice == 2) {
                if (userName == null){
                    System.out.println("You must be a user before you can change user.");
                    userName = getUserName();
                }
                newUserName = getUserName(userName);
                ClientCommunication.removeUserFromServer(userName);
                userName = newUserName;
                ClientCommunication.addUserToServer(userName);
            } else if (userChoice == 6){


                // send player ready message to the server, and draw the game board
            }
            userChoice = userMenu();
            //TODO Send vote for grid size method

            //TODO get game status method

            //TODO save score method

            //TODO play game method
        }
        System.out.println("Exiting, thanks for playing");
    }

    public static String getUserName(){
        String userName = JOptionPane.showInputDialog("Welcome to Light Cycles\nEnter your user name");
        while (userName.equals("")){
            userName = JOptionPane.showInputDialog("Enter a valid username please");
        }
        return userName;
    }
    public static String getUserName(String UserName){
        // Overloaded method to allow the user to change/remove their user name
        String newUserName = JOptionPane.showInputDialog("Enter a new user name");
        while (newUserName.equals("")){
            newUserName = JOptionPane.showInputDialog("Enter a valid username please");
        }
        return newUserName;
    }
    public static int userMenu()throws Exception{
        System.out.println("What would you like to do?\n1.Add new User\n2.Change User\n3.Vote for Grid Size\n4.Game Status");
        System.out.println("5.Save Score\n6.Play Game\n7. Exit");
        String userChoiceString = JOptionPane.showInputDialog("");
        int userChoice = Integer.parseInt(userChoiceString);
        while (userChoice <= 0 || userChoice >= 8){
            System.out.println("Invalid selection, please pick again");
            userChoiceString = JOptionPane.showInputDialog("");
            userChoice = Integer.parseInt(userChoiceString);
        }
        return userChoice;
    }

}
