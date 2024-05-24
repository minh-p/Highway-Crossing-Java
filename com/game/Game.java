import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable
{
    public static final int FRAME_WIDTH = 1024;
    public static final int FRAME_HEIGHT = 1024;
    private Thread tl;
    private boolean running;
    private double second = 0;
    private Player player;
    private final int FPS = 30;
    private final KeyHandler keyH;
    private ArrayList<Car> cars;

    public synchronized void start()
    {
        running = true;
        tl = new Thread(this);
        tl.start();
    }

    public synchronized void stop()
    {
        running = false;
        try {
            tl.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        long lastF = System.nanoTime();
        double elapsedTime = 0;
        long currentF;
        while (running)
        {
            currentF = System.nanoTime();
            elapsedTime += (currentF - lastF) / ((double) (1000000000/FPS));
            lastF = currentF;
            if (elapsedTime >= 1)
            {
                update(elapsedTime);
                repaint();
                elapsedTime--;
            }
        }
    }

    public Game(int FRAME_WIDTH, int FRAME_HEIGHT)
    {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setFocusable(true);
        requestFocus();
        this.player = new Player(0, 0, 50, 50, FRAME_WIDTH, FRAME_HEIGHT, 4);
        keyH = new KeyHandler();
        addKeyListener(keyH);
        cars = new ArrayList<Car>();
        Car.generateCars(cars, 5, 50, 50, FRAME_WIDTH, FRAME_HEIGHT, 20);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        this.player.updateLocation(g2d);
        handleCars(g2d);
        evaluteCollision();
    }

    public void evaluteCollision()
    {
        for (Car car : this.cars)
        {
            int topX = car.getX() - car.getWidth() / 2;
            int bottomX = car.getX() + car.getWidth() / 2;
            int topY = car.getY() - car.getWidth() / 2;
            int bottomY = car.getY() + car.getWidth() / 2;

            if (player.getX() >= topX && player.getX() <= bottomX && player.getY() <= bottomY && player.getY() >= topY)
            {
                running = false;
            }
        }
    }

    private void handlePlayerInput()
    {
        int speed = player.getSpeed();
        if (keyH.upPressed)
        {
            this.player.setVelocityY(-speed);
        }
        else if (keyH.downPressed)
        {
            this.player.setVelocityY(speed);
        }
        else
        {
            this.player.setVelocityY(0);
        }

        if (keyH.rightPressed)
        {
            this.player.setVelocityX(speed);
        }
        else if (keyH.leftPressed)
        {
            this.player.setVelocityX(-speed);
        }
        else
        {
            this.player.setVelocityX(0);
        }
    }

    private void handleCars(Graphics2D graphics)
    {
        for (Car car : this.cars)
        {
            car.updateLocation(graphics);
        }
    }

    public void update(double elapsedTime)
    {
        this.second += elapsedTime/100;
        handlePlayerInput();
    }
}
