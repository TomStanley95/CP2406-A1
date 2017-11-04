import java.awt.*;
public class LightCycle {
    public int x,y;
    public Color color;
    public int size = 5;


    LightCycle(int x, int y, Color color ){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    void draw(Graphics graphics){
        Graphics localGraphics = graphics.create();
        localGraphics.setColor(color);
        localGraphics.fillOval(x, y, size, size );
        localGraphics.drawOval(x, y, size, size);
        localGraphics.dispose();
    }
}
