import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public abstract class GameGraphics implements ActionListener {
    public static int [] playerXLocations;
    public static int [] playerYLocations;
    public static String[] playerNames;
    public static int playerSlot = 0;
    public static String userName = "";

    // Add implements ActionListener in when you need to listen to events
    public static void main(String[] args) throws Exception{
        initialiseGameWindow();

    }
    public static int getPlayerSlot(){
        for(int i=0; i < playerNames.length-1; i ++){
            if (userName.equals(playerNames[i])){
                playerSlot = i;
                break;
            }
        }
        return playerSlot;
    }
    public static void getUserName(String playerName){
        userName = GameApp.userName;

    }
    public static void refreshGameState(String[] playerNamesState){
        playerNames = playerNamesState;
    }
    public static void refreshGameState(int[] playerXLocationsState, int[] playerYLocationsState){
        // Overloaded method to allow the playerNames to be refreshed only once at the start of a game and these to be refreshed on demand
        playerXLocations = playerXLocationsState;
        playerYLocations = playerYLocationsState;

    }
    public static void initialiseGameWindow() throws Exception{
        int gridSize = 500;
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame gameWindow = new JFrame("Light cycles");
        gameWindow.setSize(gridSize, gridSize);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(3);
        Font genericFont = new Font("Arial", Font.PLAIN, 18) ;
        String welcomeText = "Welcome to the light cycles game!\n Please make a menu selection";
        JLabel welcomeMessage = new JLabel(welcomeText);
        welcomeMessage.setFont(genericFont);
        gameWindow.add(welcomeMessage);
        // TODO Customise a menu
    }


    public void playGameWindow() throws  Exception{
//        Integer
        // Final version will be dependant on a game finished message from server
        Timer gameTimer = new Timer(5,this);
        double x, y;


    }
     public static int getRandomGridLocation(int MaxGridSize){
        Random value = new Random();
        int locationValue = value.nextInt(MaxGridSize - 1 + 1) + 1;
        return locationValue;
    }

}
