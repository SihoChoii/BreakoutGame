package elements;

import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.BasicStroke;
import elements.Score;
import java.lang.Math;
import elements.Particle;

import utilities.GDV5;

public class Brick extends Rectangle
{
    private Color brickColor;
    private int type = 1; // Default brick type
    private boolean isVisible = true;
    private int brickWidth, brickHeight;
    private int count = 0;
    private boolean breakState = false;
    // private static int brickRatio = 3; // Default brick ratio

    private static int type1Bricks = 0;
    private static int type2Bricks = 0;
    private static int type3Bricks = 0;
    private static int type4Bricks = 0;
    private static int type5Bricks = 0;

    public Brick(int xPosition, int yPosition, int width, int height)
    {  
        super(xPosition, yPosition, width, height); // Rectangle Creation
        this.type = typeGen(10000);
        this.brickColor = colorGen(this.type);
        this.brickHeight = height;
        this.brickWidth = width;
    }
    
    public void draw(Graphics2D win)
    {
        if (isVisible)
        {
            Stroke prevStroke = win.getStroke();
            win.setColor(new Color(brickColor.getRed()/3, brickColor.getGreen()/3, brickColor.getBlue()/3));
            win.fill(this);
            // CHANGE The default white color should be replaced by a color gradient
            win.setStroke(new BasicStroke(this.brickWidth/20));
            win.setColor(brickColor);
            win.draw(this);
            win.setStroke(prevStroke); // Reset Stroke

            if (breakState)
            {
                for (int i = 0; i < Particle.getParticleArray().length; i++)
                {
                    Particle.getParticleArray()[i].draw(win);
                }
            }
        }
    }

    public int getType()
    {
        return this.type;
    }

    public boolean isVisibleGet()
    {
        return isVisible;
    }

    public Color getColor()
    {
        return this.brickColor; 
    }

    public void setColor(Color color)
    {
        this.brickColor = color;
    }

    public void isVisibleSet(boolean value)
    {
        this.isVisible = value;
    }

    public boolean breakBrick()
    {
        if (this.breakParticle() && this.fade())
        {
            isVisible = false;
            Score.setScore(Score.getScore() + (int)Math.pow(this.type, 3));
            GDV5.printDebug("Score: " + Score.getScore());
            breakState = false;
            return true;
        }
        return false;
    }
    
    public boolean breakParticle()
    {
        // GDV5.printDebug("Breaking");
        if (breakState != true)
        {
            breakState = true;
            Particle.makeParticleArray(this);
        }
        else
        {
            if (Particle.moveParticles()) return true;
        }
        return true;
    }

    public boolean fade()
    {
        if (count % 2 == 0)
        {
            if (brickColor.getAlpha() > 10)
            {
                // GDV5.printDebug("Fading");
                brickColor = new Color(brickColor.getRed(), brickColor.getGreen(), brickColor.getBlue(), brickColor.getAlpha() - 10);
            }
            else if (brickColor.getAlpha() <= 10)
            {
                return true;
            }
            count = 0;
        }
        count++;
        return false;
    }

    public static void drawBirckGrid(Brick[][] brickGrid, Graphics2D win)
    {
        // brickGrid.length = cols
        for (int i = 0; i < brickGrid.length; i++)
        {
            // brickGrid[i].length = rows
            for (int o = 0; o < brickGrid[i].length; o++)
            {
                if (brickGrid[i][o].isVisibleGet()) brickGrid[i][o].draw(win);;
            }
        }
    }

    // Picks a color based on the brick type
    public static Color colorGen(int type)
    {
        if (type == 2) return Color.red;
        else if (type == 3) return Color.orange;
        else if (type == 4) return Color.green;
        else if (type == 5) return Color.yellow; // CHANGE to rgb sweep
        return Color.blue; // Default
    }

    // Generates a brick type based on specified probability
    public static int typeGen(int grain)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(grain) + 1;

        // Dirty implementation of probability
        if (randomNum <= grain * 0.75) 
        {
            type1Bricks++;
            return 1;
        }
        else if (randomNum <= grain * 0.9) 
        {
            type2Bricks++;
            return 2;
        }
        else if (randomNum <= grain * 0.95) 
        {
            type3Bricks++;
            return 3;
        }
        else if (randomNum <= grain * 0.98) 
        {
            type4Bricks++;
            return 4;
        }
        else 
        {
            type5Bricks++;
            return 5;
        }
    }

    public static Brick[][] makeBricks(int cols, int rows, int brickX, int brickY, int borderX, int borderY)
    {
        borderX -= brickX * 2; // X Centering
        borderY -= brickY * 2; // Y Centering
        int xPosition = brickX, yPosition = brickY;
        int paddingX = (borderX / cols) / 8;
        int paddingY = (borderY / rows) / 3;
        int brickWidth = (borderX / cols) - ((paddingX*(cols-1))/cols);
        int brickHeight = (borderY / rows) - ((paddingY*(rows-1))/rows);
        // int brickHeight = brickWidth/brickRatio;

        Brick[][] brickGrid = new Brick[cols][rows];
        for (int o = 0; o < rows; o++)
        {
            for (int i = 0; i < brickGrid.length; i++)
            {
                brickGrid[i][o] = new Brick(xPosition, yPosition, brickWidth, brickHeight);
                GDV5.printDebug("Row [" + o + "] Col [" + i + "]  |  X: " + xPosition + "  Y: " + yPosition);
                xPosition += brickWidth + paddingX;
            }
            xPosition = brickX;
            yPosition += brickHeight + paddingY;
        }

        // Debug
        GDV5.printDebug("Brick Distribution  |  Total Bricks: " + rows*cols);
        GDV5.printDebug("Type 1: " + type1Bricks);
        GDV5.printDebug("Type 2: " + type2Bricks);
        GDV5.printDebug("Type 3: " + type3Bricks);
        GDV5.printDebug("Type 4: " + type4Bricks);
        GDV5.printDebug("Type 5: " + type5Bricks);

        return brickGrid;
    }

        // Block Animations
        public boolean fadeAway()
        {
            if (brickColor.getAlpha() >= 0)
            {
                brickColor = new Color((float) brickColor.getRed(), (float) brickColor.getGreen(), (float) brickColor.getBlue(), (float) (brickColor.getAlpha() - 0.1));
                return false;
            }
            return true;
        }

        public boolean deathAnim()
        {
            return true;
        }
}