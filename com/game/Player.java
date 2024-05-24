import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Sprite implements MovingSprite
{
    private final int width = 50;
    private final int height = 50;
    private boolean isLeft;
    private BufferedImage imageRight, imageLeft;

    public Player(int x, int y, int plainX, int plainY, int speed)
    {
        super(x, y, plainX, plainY, speed);

        try {
            imageRight = ImageIO.read(new File("assets/player.png"));
            imageLeft = ImageIO.read(new File("assets/playerleft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSprite(Graphics2D graphics)
    {
        BufferedImage image = imageRight;
        if (isLeft) image = imageLeft;

        graphics.drawImage(image, getX(), getY(), this.width, this.height, null);
    }

    public void setVelocityX(int velocityX)
    {
        super.setVelocityX(velocityX);
        if (velocityX < 0)
        {
            isLeft = true;
        }
        else {
            isLeft = false;
        }
    }

    public void updateLocation(Graphics2D graphics)
    {
        graphics.setColor(new Color(0, 0, 0));
        velocitize(graphics);
        capPlain();
        drawSprite(graphics);
    }
}
