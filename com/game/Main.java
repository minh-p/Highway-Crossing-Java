import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        Game game = new Game(1024, 768);

        JFrame frame = new JFrame("Highway Crossing");
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);

        game.start();
        game.run();
    }
}