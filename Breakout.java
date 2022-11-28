import utilities.GDV5;
import Elements.*;

public class Breakout extends GDV5
{
    private Brick[] bricks;

    public Breakout()
    {
        super();
        this.bircks = Brick.makeBricks();
    }

    public static void main(String[] args)
    {
        Breakout b = new Breakout();
        b.start();
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw()
    {
        for (Brick b:bricks)
        {
            b.draw(win);
        }
    }
}
