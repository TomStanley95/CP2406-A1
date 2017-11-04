import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Anon implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent keyPress){

    }
}

public abstract class GameGraphics implements ActionListener {
    public static int [] playerXLocations;
    public static int [] playerYLocations;
    public static String[] playerNames;
    public static int playerSlot = getPlayerSlot();
    public static String userName = "";
    public static JFrame gameGrid = new JFrame("Light Cycles");

    // Add implements ActionListener in when you need to listen to events
    public static void main(String[] args) throws Exception{
        initialiseWindowObj();

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
    public static void initialiseWindowObj() throws Exception{
        int gridSize = 500;
        JFrame.setDefaultLookAndFeelDecorated(true);
        gameGrid.setSize(gridSize, gridSize);
        gameGrid.setVisible(true);
        gameGrid.setDefaultCloseOperation(3);
    }
    // TODO Customise a menu window

//        String welcomeText = "Welcome to the light cycles game!\n Please make a menu selection";
//        JLabel welcomeMessage = new JLabel(welcomeText);
//        welcomeMessage.setFont(genericFont);
//        gameWindow.add(welcomeMessage);

    public static void playGameWindow() throws  Exception{
        // Final version will be dependant on a game finished message from server
        clearGrid(gameGrid);
        for (int i=0; i < playerNames.length - 1; i++){
            if (playerNames[playerSlot].equals(userName)){
                LightCycle playerLightCycle = new LightCycle(playerXLocations[playerSlot],playerYLocations[playerSlot],Color.blue);
                gameGrid.add(playerLightCycle);
            }else{ LightCycle enemyLightCycle = new LightCycle(playerXLocations[i], playerYLocations[i],Color.red);

        }



    }
    public static void clearGrid(JFrame grid) throws Exception{
        grid.removeAll();
    }

}
