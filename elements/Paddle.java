package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class Paddle extends Rectangle
{
    private boolean isVisible = true;
    private int type = 1; // Default Type - Paddle type is defined by the brick type the paddle touches
    private Color paddleColor = Color.RED;
    private int size;

    public Paddle(int xPosition, int yPosition, int size)
    {
        super(xPosition, yPosition, size, size/5);
        this.size = size;
    }

    public void draw(Graphics2D win)
    {
        if (isVisible)
        {
            Stroke prevStroke = win.getStroke();
            win.setColor(paddleColor);
            win.fill(this);
            win.setStroke(new BasicStroke(size/20));
            win.setColor(Color.WHITE);
            win.draw(this);
            win.setStroke(prevStroke);
        }
    }

    public void movePaddle(int xVelocity, int yVelocity)
    {
        // move the paddle by the xVelocity and yVelocity
        this.setLocation((int)this.getX() + xVelocity, (int)this.getY() + yVelocity);
    }

    private int xVelocity = 3, yVelocity = 0; // CHANGE static implementation
    public void update()
    {
        int collisionDirectionWindow = GDV5.collisionDirectionWindow(this, GDV5.getMaxWindowX(), GDV5.getMaxWindowY(), xVelocity, yVelocity);

        if (GDV5.KeysPressed[KeyEvent.VK_RIGHT] && collisionDirectionWindow == 0)
        {
            this.movePaddle(xVelocity, yVelocity);
        }
        if (GDV5.KeysPressed[KeyEvent.VK_LEFT] && collisionDirectionWindow == 0)
        {
            this.movePaddle(-xVelocity, yVelocity);
        }
    }

    // Brick Collision Method
    // Uses Fast Algorithm x + y - 1 in boundary
}