import java.awt.Graphics2D;

public class Sprite
{
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private final int plainX;
    private final int plainY;
    private final int speed;
    public Sprite(int x, int y, int plainX, int plainY, int speed)
    {
        this.x = x;
        this.y = y;
        this.plainX = plainX;
        this.plainY = plainY;
        this.speed = speed;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getPlainX()
    {
        return this.plainX;
    }

    public int getPlainY()
    {
        return this.plainY;
    }

    public void velocitize(Graphics2D graphics)
    {
        this.x += this.velocityX;
        this.y += this.velocityY;
    }

    public void capPlain()
    {
        if (this.x > this.plainX)
        {
            this.x = 0;
        }
        else if (this.x < 0)
        {
            this.x = this.plainX;
        }

        if (this.y > this.plainY)
        {
            this.y = 0;
        }
        else if (this.y < 0)
        {
            this.y = this.plainY;
        }
    }

    public void setVelocityX(int velocityX)
    {
        this.velocityX = velocityX;

    }
    public void setVelocityY(int velocityY)
    {
        this.velocityY = velocityY;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setX(int x)
    {
        this.x = x;
    }
}