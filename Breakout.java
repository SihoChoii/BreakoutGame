import utilities.GDV5;
import elements.Brick;
import java.awt.Graphics2D;

public class Breakout extends GDV5
{
    Brick[][] brickGrid;

    public Breakout()
    {
        // super();
        // Number of Cols and Rows should be set by method
        // This method should take in the brick size and border size and calculate
        // the number of bricks that can fit within the specified boreder size
        // The method will return a struct that contains the padding, cols, and rows
        // This method will be contained in the brick java file
        // This system should also be dynamic, meaning that it should be reactive to changes
        // This is needed for the dynamic border sizing
        // The bricks should also be cetnered on the border
        // The border position and size in defined by aboslute pixel position 
        
        brickGrid = Brick.makeBricks(11,10);
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
        // brickGrid.length = cols
        for (int i = 0; i < brickGrid.length; i++)
        {
            // brickGrid[i].length = rows
            for (int o = 0; o < brickGrid[i].length; o++)
            {
                brickGrid[i][o].draw(win);
            }
        }
    }
}
