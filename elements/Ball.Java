package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;

public class Ball extends Rectangle
{
    private boolean isVisible = true;
    private int type = 1; // Default Type - Ball type is defined by the brick type the ball touches
    private Color ballColor = Color.WHITE;

    public Ball(int xPosition, int yPosition, int size)
    {
        super(xPosition, yPosition, size, size);
    }

    public void draw(Graphics2D win)
    {
        if (isVisible)
        {
            win.setColor(ballColor);
            win.drawOval((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
        }
    }

    public void moveBall(int xVelocity, int yVelocity)
    {
        // move the ball by the xVelocity and yVelocity
        this.setLocation((int)this.getX() + xVelocity, (int)this.getY() + yVelocity);
    }

    private int xVelocity = 3, yVelocity = 3; // CHANGE static implementation
    public void update()
    {
        int collisionDirectionWindow = GDV5.collisionDirectionWindow(this, xVelocity, yVelocity);
        
        if (collisionDirectionWindow != 0 && collisionDirectionWindow % 2 == 0) xVelocity *= -1;
        if (collisionDirectionWindow != 0 && collisionDirectionWindow % 2 == 1) yVelocity *= -1;

        this.moveBall(xVelocity, yVelocity);
    }

    // Brick Collision Method
    // Uses Fast Algorithm x + y - 1 in boundary
    
}