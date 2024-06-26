import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.ArrayList;

public class Car extends Sprite implements MovingSprite
{
    public int maxSpeed;
    private BufferedImage image;

    public Car(int x, int y, int width, int height, int plainX, int plainY, int maxSpeed, int minSpeed)
    {
        super(x, y, width, height, plainX, plainY, Math.min((int) (Math.random() * maxSpeed), minSpeed));
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
        graphics.drawImage(image, getX(), getY(), this.getWidth(), this.getHeight(), null);
    }

    public void capPlain()
    {
        if (this.getX() < 0)
        {
            this.setY((int) (Math.random()*(this.getPlainY()-2) + 2));
            this.setX(this.getPlainX());
            this.setVelocityX(-((int) (Math.random() * this.maxSpeed) + 1));
        }
    }

    public static void generateCars(ArrayList<Car> cars, int amount, int width, int height, int plainX, int plainY, int maxSpeed, int minSpeed)
    {
        while (amount >= 0)
        {
            cars.add(
                new Car(
                    plainX,
                    (int) (Math.random()*(plainY-2) + 2),
                    width,
                    height,
                    plainX,
                    plainY,
                    maxSpeed,
                    minSpeed
                    )
                    );
            amount--;
        }
    }
}