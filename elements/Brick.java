package elements;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Brick extends Rectangle
{
    private Color brickColor;
    private int type = 1; // Default brick type
    // private static int brickRatio = 3; // Default brick ratio

    public Brick(int xPosition, int yPosition, int width, int height)
    {  
        super(xPosition, yPosition, width, height); // Rectangle Creation
        this.type = typeGen(10000);
        System.out.println(this.type);
        this.brickColor = colorGen(this.type);
    }

    // Picks a color based on the brick type
    public Color colorGen(int type)
    {
        if (type == 2) return Color.red;
        else if (type == 3) return Color.blue;
        else if (type == 4) return Color.green;
        else if (type == 5) return Color.yellow;
        return Color.blue; // Default
    }

    // Generates a brick type based on specified probability
    public int typeGen(int grain)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(grain) + 1;

        // Dirty implementation of probability
        if (randomNum <= grain * 0.75) return 1;
        else if (randomNum <= grain * 0.9) return 2;
        else if (randomNum <= grain * 0.95) return 3;
        else if (randomNum <= grain * 0.98) return 4;
        return 5;
    }

    public void draw(Graphics2D win)
    {
        win.setColor(brickColor);
        win.fill(this);
        win.setColor(Color.white);
        win.draw(this);
    }

    public static Brick[][] makeBricks(int cols, int rows, int brickX, int brickY, int borderX, int borderY)
    {
        borderX -= brickX * 2;
        borderY -= brickY * 2;
        int xPosition = brickX, yPosition = brickY;
        int paddingX = (borderX / cols) / 8;
        int paddingY = (borderY / rows) / 5;
        int brickWidth = (borderX / cols) - ((paddingX*(cols-1))/cols);
        int brickHeight = (borderY / rows) - ((paddingY*(rows-1))/rows);
        // int brickHeight = brickWidth/brickRatio;

        Brick[][] brickGrid = new Brick[cols][rows];
        for (int o = 0; o < rows; o++)
        {
            for (int i = 0; i < brickGrid.length; i++)
            {
                brickGrid[i][o] = new Brick(xPosition, yPosition, brickWidth, brickHeight);
                xPosition += brickWidth + paddingX;
            }
            xPosition = brickX;
            yPosition += brickHeight + paddingY;
        }
        return brickGrid;
    }
}