import utilities.GDV5;
import elements.Brick;
import java.awt.Graphics2D;

public class Breakout extends GDV5
{
    Brick[] bricks;

    public Breakout()
    {
        // super();
        bricks = Brick.makeBricks();
    }

    public static void main(String[] args)
    {
        Breakout game = new Breakout();
        game.start();
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw(Graphics2D win)
    {
        for (Brick b:bricks)
        {
            b.draw(win);
        }
    }
}
