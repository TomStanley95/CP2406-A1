public class GameServer {
    public static int [][] playerLocations = new int [2][20];
    public static String[] playerNames = new String[20];
    public static void main(String[] args) {


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
}
