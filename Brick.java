package Elements;

import utilities.GDV5;

public class Brick 
{
    private Color col;

    public Brick(int x, int y, Color c)
    {
        super(x, y, GDV5.getMaxWindowX()/12, 25);
        col = c;
    }

    public static Brick[] makeBricks(int bx, int by)
    {
        // Array Size x, y
        

        // Brick Params
        

        Brick[] bricks = new Brick[bx];

        // Rows
        for (int i = 0; i < by; i++)
        {
            // Bricks
            for (int p = 0; p < bricks.length; p++)
            {
                bricks[p] = new Brick(x, y, Color.blue);
                x += bricks[p].length() + xPadding;
            }
            x = xOrigin;
            y += yPadding;
        } 
        return bricks;
    }
}