import java.awt.Color;
import java.awt.Graphics2D;

public class Player
{
    private int x;
    private int y;
    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void updateLocation(Graphics2D graphics)
    {
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawRect(50, 50, 50, 50);
        System.out.println("hello");
    }
}