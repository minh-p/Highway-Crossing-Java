import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class Game extends JFrame
{
    private Player player;
    private final SwingWorker gameLooper;
    private boolean stop;
    private final int sizeX = 800;
    private final int sizeY = 600;
    public final long FPS = 10;
    
    private long seconds;
    
    public Game()
    {       
        this.player = new Player(200, 400);
        setSize(this.sizeX, this.sizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        seconds = 0;
        stop = false;
        
        this.gameLooper = new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception
            {
                // while (!stop)
                // {
                //     update();
                //     repaint();
                //     Thread.sleep(200);
                // }
                
                long lastFrameTime = System.currentTimeMillis();
                long elapsedTime = 0;
                while (!stop)
                {
                    long currentFrameTime = System.currentTimeMillis();
                    elapsedTime += (currentFrameTime - lastFrameTime);
                    if (elapsedTime >= 1)
                    {
                        update(elapsedTime);
                        repaint();
                        elapsedTime--;
                    }
                    lastFrameTime = currentFrameTime;
                }
                return null;
            }
        };
        this.gameLooper.execute();
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        getGraphics().setColor(Color.black);
        graphics.drawString("second: " + seconds, 200, 200);
        this.player.updateLocation(graphics);
    }
    
    public void update(long elapsedTime) // ONLY FOR GAME LOGIC NOT UI
    {
        seconds += elapsedTime;
    }
}