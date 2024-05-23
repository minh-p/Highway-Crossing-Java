import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
    public static final int FRAME_WIDTH = 1024;
    public static final int FRAME_HEIGHT = 1024;
    private Thread tl;
    private boolean running;
    private double second = 0;
    private Player player;
    private final int FPS = 60;

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
        this.player = new Player(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        // addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        this.player.updateLocation(g2d, this.second);
    }

    public void update(double elapsedTime)
    {
        this.second += elapsedTime/100;
    }
}