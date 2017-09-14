import java.util.Arrays;

public class GameServer {
    public static void main(String[] args) {
        initialisePlayers();

    }
    public  static  String addUserToGame(String userMessage){
        String[] userInfo = userMessage.split(",");
        String userName = userInfo[0];
        String userAddress = userInfo[1];

// While game isnt running recevie usernames()

// Make primitive array 0-20 for users, check array for the name, if there is none add it and make an object with username + address, if there is send failed reason to the client
//        return response;
    return null;
    }
    public static void  initialisePlayers(){

        int [][] playerLocations = new int [2][20];
        System.out.println(Arrays.deepToString(playerLocations));
    }
}
