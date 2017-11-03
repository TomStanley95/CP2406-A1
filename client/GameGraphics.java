import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameGraphics  {
    // Add implements ActionListener in when you need to listen to events
    public static void main(String[] args) throws Exception{
        initialiseGameWindow();
    }
    public static void initialiseGameWindow() throws Exception{
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame gameWindow = new JFrame("Light cycles");
        gameWindow.setSize(400, 400);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(3);
        Font genericFont = new Font("Arial", Font.PLAIN, 18) ;
        String welcomeText = "Welcome to the light cycles game!\n Please make a menu selection";
        JLabel welcomeMessage = new JLabel(welcomeText);
        welcomeMessage.setFont(genericFont);
        gameWindow.add(welcomeMessage);


    }


}
