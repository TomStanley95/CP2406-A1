import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class GameServer {
    // TODO Going to need to have another list for player lines, would consist of a 20 deep array, containing a list of previous light locations
    // TODO generateUserStartingPositions only currently supports 16 users, improve method/make truly random.
    public static int totalNumberPlayers = 20;
    public static int [] [] lightCycleLocations = new int[20][100];
    public static int [][] playerLocations = new int [2][20];
    public static String[] playerNames = new String[20];
    public static int gridSize = 500;
    public static int playerStartingPositions [] [] = new int[2][20];
    public static void main(String[] args) {
        generateUserStartingPositions();
//        playerLocations [0][0] = 1;
//        playerLocations [1][0] = 2;
//        System.out.println(playerLocations[0][0]);
//        System.out.println(playerLocations[1][0]);



    }
    public  static  String addUserToGame(String userName){
        int currentPlayerCount = 0;
        int emptyPlayerSlot = 0;
        for (int i = 0; i < playerNames.length - 1 ; i++) {
            if (playerNames[i] == null){
                emptyPlayerSlot = i;
                break;
            } else if (playerNames[i].equalsIgnoreCase(userName)){
                return "Failed, that user already exists";
            } else {
                currentPlayerCount ++;
            }
        }

        if (currentPlayerCount == 19){
            // currentPlayerCount will only ever == 19 due to indexing
            return "Failed, the game lobby is full";
        }
        // If we have reached this point without returning a failed message, add the user to the game lobby
        playerNames[emptyPlayerSlot] = userName;
        int playerSlot = emptyPlayerSlot;
        assignUserStartingPosition(playerSlot);
        return ("Player " + playerNames[emptyPlayerSlot] + " added successfully");
    }
    public static String removeUserFromGame(String userName) {
        int playerSlot = 0;
        int currentPlayerCount = 0;
        for (int i = 0; i < playerNames.length - 1; i++) {
            if (playerNames[i].equalsIgnoreCase(userName)) {
                playerSlot = i;
                break;
            } else {
                currentPlayerCount++;
            }
        }
        if (currentPlayerCount == 19){
            // currentPlayerCount will only ever == 19 due to indexing
            return "Failed, that player doesn't exists";
        }
        playerNames[playerSlot] = null;
        return ("Player " + userName + " removed successfully");
    }
    public static void generateUserStartingPositions(){
        int playerStartingBoundsMin = gridSize/5;
        int playerStartingBoundsMax = gridSize - playerStartingBoundsMin;
        int playerGridSpacing = (playerStartingBoundsMax - playerStartingBoundsMin) / 5 ;
//        System.out.println(playerGridSpacing);
//        System.out.println(playerStartingBoundsMin);
//        System.out.println(playerStartingBoundsMax);
        //Iterate through first five player slots, left hand side of grid assigning position
        int playerPositionX = playerGridSpacing;
        int playerPositionY = playerGridSpacing;
        for (int i = 0; i < 6 - 1; i++){
            playerStartingPositions[0][i] = playerPositionX;
            playerStartingPositions[1][i] = playerPositionY;
            playerPositionY += playerGridSpacing;
        }
        //Iterate through next 3 player slots, bottom side of the grid
        playerPositionX = playerGridSpacing * 2;
        playerPositionY = playerGridSpacing;
        for (int i = 5; i < 9 - 1; i++){
            playerStartingPositions[0][i] = playerPositionX;
            playerStartingPositions[1][i] = playerPositionY;
            playerPositionX += playerGridSpacing;
        }
        // Implement true random later, for now based on player slot
        //Iterate through next five player slots, right side of the grid
        playerPositionX = playerGridSpacing * 5;
        playerPositionY = playerGridSpacing;
        for (int i = 8; i < 14 - 1; i++){
            playerStartingPositions[0][i] = playerPositionX;
            playerStartingPositions[1][i] = playerPositionY;
            playerPositionY += playerGridSpacing;
        }
        //Iterate through next 3 player slots, Top side of the grid
        playerPositionX = playerGridSpacing;
        playerPositionY = playerGridSpacing * 5;
        for (int i = 13; i < 17 - 1; i++){
            playerStartingPositions[0][i] = playerPositionX;
            playerStartingPositions[1][i] = playerPositionY;
            playerPositionX += playerGridSpacing;
        }

    }
    public static void assignUserStartingPosition(int playerSlot){
        playerLocations[0][playerSlot] = playerStartingPositions[0][playerSlot];
        playerLocations[1][playerSlot] = playerStartingPositions[1][playerSlot];
    }
    public static String getPlayerNames(){
        String unCleanPlayerNames[] = playerNames;
        String cleanedPlayerNames = "";
        for (int i = 0; i < unCleanPlayerNames.length - 1; i++) {
            if (unCleanPlayerNames[i] != null){
                cleanedPlayerNames += unCleanPlayerNames[i] + ",";
            }else {
                break;
            }
        }
        return cleanedPlayerNames;
    }
    public static String getPlayerLocationsGameState(){return Arrays.deepToString(playerLocations); }
    public static String getLightCyclesGameState(){
        return Arrays.deepToString(lightCycleLocations);
    }


}
