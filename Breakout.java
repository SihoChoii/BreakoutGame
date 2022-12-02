// Dynamic Breakout Game
// By: Siho Choi
// Date: 12/1/2022
// Version: 1.0.31

import utilities.GDV5;
import elements.Brick;
import java.awt.Graphics2D;

public class Breakout extends GDV5
{
    Brick[][] brickGrid;

    public Breakout()
    {
        // super();        
        brickGrid = Brick.makeBricks(14,15, 30, 20, GDV5.getMaxWindowX(), (int)(GDV5.getMaxWindowY()/2.5));
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
        Brick.drawBirckGrid(brickGrid, win);
    }
}
