package elements;

import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Rectangle
{
    private Color color;
    private static int w = 60;
    private static int h = 20;

    public Brick(int x, int y, Color c)
    {
        super(x, y, w, h);
        color = c;
    }

    public void draw(Graphics2D win)
    {
        win.setColor(color);
        win.fill(this);
        win.setColor(Color.white);
        win.draw(this);
    }

    public static Brick[] makeBricks()
    {
        int rows = 1;
        int x = 10, y = 10;
        int cols = 8;
        int numBricks = rows * cols;

        Brick[] b = new Brick[cols];
        for (int o = 0; o < rows; o++)
        {
            for (int i = 0; i < b.length; i++)
            {
                b[i] = new Brick(x, y, Color.green);
                x += w + 10;
            }
            y -= h + 10;
        }
        return b;
    }
}