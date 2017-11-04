import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel {
    public LightCycle lightCycle;
    public static Font genericFont = new Font("Arial", Font.PLAIN, 18) ;
    public int gridSize = 500;

    GameDisplay() {
        setFocusable(true);
        setSize(gridSize,gridSize);
    }
    void setLightCycles(LightCycle lightCycle){this.lightCycle = lightCycle;}
}
