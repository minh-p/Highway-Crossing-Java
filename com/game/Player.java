import java.awt.Color;
import java.awt.Graphics2D;

public class Player
{
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private final int width = 50;
    private final int height = 50;
    private double lastSecond;
    private final int plainX;
    private final int plainY;

    public Player(int x, int y, int plainX, int plainY)
    {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 10;
        this.plainX = plainX;
        this.plainY = plainY;
    }
    
    public void drawPlayerSprite(Graphics2D graphics)
    {
        graphics.drawRect(x, y, width, height);
    }

    public void velocitize(Graphics2D graphics, double second)
    {
        if ((second - lastSecond)>= 1)
        {
            this.lastSecond = second;
        }
        if (this.lastSecond == 0)
        {
            this.lastSecond = second;
            return;
        }
        this.x = (int) (this.velocityX * ((second - lastSecond) / lastSecond+1));
        this.y = (int) (this.velocityY * ((second - lastSecond) / lastSecond+1));
    }

    public void capPlain(Graphics2D graphics)
    {
        if (this.x > this.plainX)
        {
            this.x = 0;
        }
        else if (this.x < this.plainX)
        {
            this.x = this.plainX;
        }

        if (this.y > this.plainY)
        {
            this.y = 0;
        }
        else if (this.y < this.plainY)
        {
            this.y = this.plainY;
        }
        System.out.println(y);
    }

    public void updateLocation(Graphics2D graphics, double second)
    {
        graphics.setColor(new Color(0, 0, 0));
        velocitize(graphics, second);
        //capPlain(graphics);
        drawPlayerSprite(graphics);
    }

    public void setVelocityX(int velocityX)
    {
        this.velocityX = velocityX;
    }
    public void setVelocityY(int velocityY)
    {
        this.velocityY = velocityY;
    }
}