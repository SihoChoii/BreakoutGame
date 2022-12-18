package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class Ball extends Rectangle
{
    private boolean isVisible = true;
    private int type = 1; // Default Type - Ball type is defined by the brick type the ball touches
    private Color ballColor = Color.RED;
    private int size;

    public Ball(int xPosition, int yPosition, int size)
    {
        super(xPosition, yPosition, size, size);
        this.size = size;
    }

    public void draw(Graphics2D win)
    {
        if (isVisible)
        {
            Stroke prevStroke = win.getStroke();
            win.setColor(Color.white);
            win.setStroke(new BasicStroke(size/5));
            win.drawOval((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
            win.setStroke(prevStroke);
            win.setColor(ballColor);
            win.fillOval((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
        }
    }

    public void moveBall(int xVelocity, int yVelocity)
    {
        // move the ball by the xVelocity and yVelocity
        this.setLocation((int)this.getX() + xVelocity, (int)this.getY() + yVelocity);
    }

    private int xVelocity = 3, yVelocity = 3; // CHANGE static implementation
    public void update(Brick[][] brickGrid)
    {
        int collisionDirectionWindow = GDV5.collisionDirectionWindow(this, GDV5.getMaxWindowX(), GDV5.getMaxWindowY(), xVelocity, yVelocity);
        
        if (collisionDirectionWindow != 0 && collisionDirectionWindow % 2 == 0) xVelocity *= -1;
        if (collisionDirectionWindow != 0 && collisionDirectionWindow % 2 == 1) yVelocity *= -1;

        // Brick Collision
        for (int i = 0; i < brickGrid.length; i++)
        {
            // brickGrid[i].length = rows
            for (int o = 0; o < brickGrid[i].length; o++)
            {
                if(brickGrid[i][o].isVisibleGet() &&  this.intersects(brickGrid[i][o]))
                {
                    brickGrid[i][o].breakBrick();
                    if (GDV5.collisionDirection(brickGrid[i][o], this, xVelocity, yVelocity) % 2 == 0) xVelocity *= -1;
                    if (GDV5.collisionDirection(brickGrid[i][o], this, xVelocity, yVelocity) % 2 == 1) yVelocity *= -1;
                }
            }
        }

        this.moveBall(xVelocity, yVelocity);
    }

    // Brick Collision Method
    // Uses Fast Algorithm x + y - 1 in boundary
    
}