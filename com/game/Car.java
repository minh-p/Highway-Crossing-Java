import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.ArrayList;

public class Car extends Sprite implements MovingSprite
{
    public final int width = 50;
    public final int height = 50;
    public int maxSpeed;
    private BufferedImage image;

    public Car(int x, int y, int plainX, int plainY, int maxSpeed)
    {
        super(x, y, plainX, plainY, (int) (Math.random() * maxSpeed));
        this.maxSpeed = maxSpeed;
        this.setVelocityX(-this.getSpeed());
        try {
            image = ImageIO.read(new File("assets/car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLocation(Graphics2D graphics) {
        graphics.setColor(new Color(0, 0, 0));
        velocitize(graphics);
        capPlain();
        drawSprite(graphics);
    }

    public void drawSprite(Graphics2D graphics) {
        graphics.drawImage(image, getX(), getY(), this.width, this.height, null);
    }

    public void capPlain()
    {
        if (this.getX() < 0)
        {
            this.setY((int) (Math.random() * this.getPlainY()));
            this.setX(this.getPlainX());
            this.setVelocityX(-((int) (Math.random() * this.maxSpeed)));
        }
    }

    public static void generateCars(ArrayList<Car> cars, int amount, int plainX, int plainY, int maxSpeed)
    {
        while (amount >= 0)
        {
            cars.add(
                new Car(
                    plainX,
                    (int) (Math.random()*plainY),
                    plainX,
                    plainY,
                    (int) (Math.random() * maxSpeed)
                    )
                    );
            amount--;
        }
    }
}