import java.awt.Graphics2D;
public interface MovingSprite
{
    public final int width = 50;
    public final int height = 50;
    public void updateLocation(Graphics2D graphics);
    public void drawSprite(Graphics2D graphics);
}