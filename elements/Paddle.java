package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.lang.Math;

public class Paddle extends Rectangle
{
    private boolean isVisible = true;
    private int type = 1; // Default Type - Paddle type is defined by the brick type the paddle touches
    private Color paddleColor = Color.black;
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

    private int xVelocityD = 4, yVelocityD = 0; // Defaults
    private int xVelocity = xVelocityD, yVelocity = yVelocityD; // Current
    public void update()
    {
        int collisionDirectionWindow = GDV5.collisionDirectionWindow(this, GDV5.getMaxWindowX(), GDV5.getMaxWindowY(), xVelocity, yVelocity);

        if (GDV5.KeysPressed[KeyEvent.VK_RIGHT] && collisionDirectionWindow != 2)
        {
            this.movePaddle(xVelocity, yVelocity);
        }
        if (GDV5.KeysPressed[KeyEvent.VK_LEFT] && collisionDirectionWindow != 4)
        {
            this.movePaddle(-xVelocity, yVelocity);
        }
    }

    public void setType(int type)
    {
        this.type = type;
        if (type != 1)
        {
            xVelocity = (int)(type/1.5) * xVelocityD;
        }
        else xVelocity = xVelocityD;
    }

    public void setColor(Color color)
    {
        this.paddleColor = color;
    }
}