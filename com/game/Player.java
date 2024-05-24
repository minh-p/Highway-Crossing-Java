import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    private final int speed;
    private boolean isLeft;
    private BufferedImage imageRight, imageLeft;

    public Player(int x, int y, int plainX, int plainY, int speed)
    {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.plainX = plainX;
        this.plainY = plainY;
        this.speed = speed;

       try {
            imageRight = ImageIO.read(new File("assets/player.png"));
            imageLeft = ImageIO.read(new File("assets/playerleft.png"));
       } catch (IOException e) {
            e.printStackTrace();
       }
    }

    public int getSpeed()
    {
        return this.speed;
    }
    
    public void drawPlayerSprite(Graphics2D graphics)
    {
        BufferedImage image = imageRight;
        if (isLeft) image = imageLeft;

        graphics.drawImage(image, this.x, this.y, this.width, this.height, null);
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
        // this.x += (int) (this.velocityX * ((second - lastSecond) / lastSecond+1));
        // this.y += (int) (this.velocityY * ((second - lastSecond) / lastSecond+1));
        this.x += this.velocityX;
        this.y += this.velocityY;
    }

    public void capPlain(Graphics2D graphics)
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

    public void updateLocation(Graphics2D graphics, double second)
    {
        graphics.setColor(new Color(0, 0, 0));
        velocitize(graphics, second);
        capPlain(graphics);
        drawPlayerSprite(graphics);
    }

    public void setVelocityX(int velocityX)
    {
        this.velocityX = velocityX;
        if (velocityX < 0)
        {
            isLeft = true;
        }
        else {
            isLeft = false;
        }
    }
    public void setVelocityY(int velocityY)
    {
        this.velocityY = velocityY;
    }
}
