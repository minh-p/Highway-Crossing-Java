import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
    public static final int FRAME_WIDTH = 1024;
    public static final int FRAME_HEIGHT = 1024;
    private Thread tl;
    private boolean running;
    private double seconds = 0;
    private final int FPS = 10;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("HighWay Crossing");
        frame.setSize(Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
    }

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
            System.out.println("The game stopped");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        double lastF = System.nanoTime();
        double elapsedTime = 0;
        while (running)
        {
            double currentF = System.nanoTime();
            elapsedTime += (currentF - lastF) / (1000000000/FPS);
            lastF = currentF;
            System.out.println(elapsedTime);
            if (elapsedTime >= 1)
            {
                load();
                System.out.println("The game runs");
                repaint();
                elapsedTime--;
            }
        }
    }

    public Game()
    {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setFocusable(true);
        requestFocus();
        // addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
    }

    // Load Images in memory
    public void load()
    {
        return;
    }
}