import com.sun.xml.internal.bind.v2.TODO;

import java.util.Arrays;

public class GameServer {
    public static int [][] playerLocations = new int [2][20];
    public static String[] playerNames = new String[20];
    public static void main(String[] args) {


    }
    public  static  String addUserToGame(String userName){
        int currentPlayerCount = 1;
        int emptyPlayerSlot = 0;
        for (int i = 0; i < playerNames.length - 1 ; i++) {
            if (playerNames[i] == null){
                emptyPlayerSlot = i;
                break;
            } else if (playerNames[i].equals(userName)){
                return "Failed, that user already exists";
            } else {
                currentPlayerCount ++;
            }
        }

        if (currentPlayerCount == 20){
            return "Failed, the game lobby is full";
        }
        // If we have reached this point without returning a failed message, add the user to the game lobby
        playerNames[emptyPlayerSlot] = userName;
        return ("Test.Player " + playerNames[emptyPlayerSlot] + " added successfully");

    }

}
