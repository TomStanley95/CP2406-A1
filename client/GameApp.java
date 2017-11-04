import javax.swing.*;
import java.util.Arrays;


public class GameApp {
    public static String userName = null;
    public static void main(String[] args) throws Exception {
        String newUserName;
        int userChoice = userMenu();
        while (userChoice != 7) {
            if (userChoice == 1) {
                // Add new user
                userName = getUserName();
                ClientCommunication.addUserToServer(userName);
            } else if (userChoice == 2) {
                // Change user
                if (userName == null){
                    System.out.println("You must be a user before you can change user.");
                    userName = getUserName();
                }
                newUserName = getUserName(userName);
                ClientCommunication.removeUserFromServer(userName);
                userName = newUserName;
                ClientCommunication.addUserToServer(userName);
            } else if (userChoice == 6){
            // Play Game Choice

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

    public static void handleMultiMessage(String incomingMessage){
        // Currently can only handle usernames and locations, no light cycle bars or game status updates
        // Maybe add in a client response , sort of like tcp to make sure we dont miss any part of the gamestate update
        int [] playerLocations = new int[40];
        int [] playerXLocations = new int [20];
        int [] playerYLocations = new int [20];
        String[] playerNames;
        String cleanedMessage[] = cleanMessage(incomingMessage);
        String gameStateType = cleanedMessage[0];
        String gameState = cleanedMessage[1];
        // Always catching the same message, response changes based on if statement, Split before statement
        if (gameStateType.equals("player names")) {
            playerNames = gameState.split(",");
            GameGraphics.refreshGameState(playerNames);
        }else if (gameStateType.equals("player locations")){
            String[] playerLocationsUnf = gameState.replace("]", "").replace("[", "").replace("],[", "/").split(",");
            for (int i = 0; i < playerLocationsUnf.length - 1; i ++){
                playerLocationsUnf[i] = playerLocationsUnf[i].trim();
                playerLocations[i] = Integer.parseInt(playerLocationsUnf[i]);
            }
            for (int i = 0 ; i < 21-1; i ++ ){
                playerXLocations[i] = playerLocations[i];
            }
            int yLocationCounter = 0;
            // YLocationCounter aligns the index of the playerYLocations with it's corresponding value in playerLocations
            for (int i = 20 ; i < 40-1; i ++ ){
                playerYLocations[yLocationCounter] = playerLocations[i];
                yLocationCounter += 1;
            }
            GameGraphics.refreshGameState(playerXLocations, playerYLocations);
            // TODO Remove this
//            System.out.println(Arrays.toString(playerXLocations));
//            System.out.println(Arrays.toString(playerYLocations));
//            System.out.println(Arrays.toString(playerLocationsUnf));
//            System.out.println("This is getting reached");


        }



    }
    public static String[]  cleanMessage(String message){
        String[] userMessage = message.split("/");
        return userMessage;

    }
}
