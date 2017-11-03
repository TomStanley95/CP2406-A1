import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public abstract class GameGraphics implements ActionListener {

    // Add implements ActionListener in when you need to listen to events
    public static void main(String[] args) throws Exception{
        initialiseGameWindow();

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
    }
//    public static String getXAndYValues(){
//        int gridSize = 500;
//        double x, y;
//        x = getRandomGridLocation(gridSize);
//        y = getRandomGridLocation(gridSize);
//        System.out.println(x + "" + y);
//        int playerLocation [] [] = new int[1][1];
////        playerLocation[]
////        return int[x][y];
//
//    }

    public void playGameWindow(String XAndY) throws  Exception{
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
