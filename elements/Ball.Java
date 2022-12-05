import java.awt.Rectangle;
import java.awt.Graphics2D;
import utilities.collisionDirectionWindow;

public class Ball extends Rectangle
{
    private boolean isVisible = true;
    private int type = 1; // Default Type - Ball type is defined by the brick type the ball touches

    public Ball(int xPosition, int yPosition, int size)
    {
        super(xPosition, yPosition, size, size);
    }

    public void draw(Graphics2D win)
    {
        if (isVisible)
        {
            win.setColor(brickColor);
            win.drawOval((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
        }
    }

    public void move(int xVelocity, int yVelocity)
    {
        setLocation((int)this.getX + xVelocity, (int)this.getY + yVelocity);
    }

    public void update()
    {
        int xVelocity = 5, yVelocity = 5; // CHANGE static implementation
        if (collisionDirectionWindow(this) % 2 == 0) xVelocity *= -1;
        if (collisionDirectionWindow(this) % 2 != 0) yVelocity *= -1;
        this.move(xVelocity, yVelocity);
    }

    // Brick Collision Method
    // Uses Fast Algorithm x + y - 1 in boundary
}