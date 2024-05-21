import java.awt.Graphics;

public class Player
{
    private int x;
    private int y;
    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void updateLocation(Graphics graphics)
    {
        graphics.draw3DRect(x, y, 50, 50, true);
        x += 1;
    }
}