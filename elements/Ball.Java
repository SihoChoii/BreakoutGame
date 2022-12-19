package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import utilities.GDV5;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.BasicStroke;
import utilities.AudioPlayer;

public class Ball extends Rectangle
{
    private int xPosition, yPosition;
    private boolean isVisible = true;
    private int type = 1; // Default Type - Ball type is defined by the brick type the ball touches
    private Color ballColor = Color.black;
    private int size;
    private Brick brokenBrick = null;

    public Ball(int xPosition, int yPosition, int size)
    {
        super(xPosition, yPosition, size, size);
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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

    public void reset()
    {
        this.x = xPosition;
        this.y = yPosition;
    }

    private int xVelocityD = 3, yVelocityD = 4; // CHANGE static implementation
    private int xVelocity = xVelocityD, yVelocity = yVelocityD; // CHANGE static implementation
    public void update(Brick[][] brickGrid, Paddle paddle)
    {   
        // Window Collision
        int collisionDirectionWindow = GDV5.collisionDirectionWindow(this, GDV5.getMaxWindowX(), GDV5.getMaxWindowY(), xVelocity, yVelocity);
        
        if (collisionDirectionWindow != 0 && collisionDirectionWindow % 2 == 0) 
        {
            xVelocity *= -1;
            AudioPlayer.playAudio("utilities\\Sounds\\WallHit.wav");
        }
        if (collisionDirectionWindow != 0 && collisionDirectionWindow  == 3) 
        {
            yVelocity *= -1;
            AudioPlayer.playAudio("utilities\\Sounds\\WallHit.wav");
        }
        else if (collisionDirectionWindow != 0 && collisionDirectionWindow  == 5)
        {
            reset();
            AudioPlayer.playAudio("utilities\\Sounds\\Death.wav");
            Score.setDeath(Score.getDeath() + 1);
        }

        // Paddle Collision
        if (this.intersects(paddle))
        {
            if (GDV5.collisionDirection(paddle, this, xVelocity, yVelocity) % 2 == 0) 
            {
                xVelocity *= -1;
                yVelocity *= -1;
                AudioPlayer.playAudio("utilities\\Sounds\\PaddleHit.wav");
            }
            if (GDV5.collisionDirection(paddle, this, xVelocity, yVelocity) % 2 == 1) 
            {
                yVelocity *= -1;
                AudioPlayer.playAudio("utilities\\Sounds\\PaddleHit.wav");
            }
            paddle.setColor(new Color(ballColor.getRed(), ballColor.getGreen(), ballColor.getBlue(), 255));
            paddle.setType(this.type);
        }

        // Brick Collision
        for (int i = 0; i < brickGrid.length; i++)
        {
            // brickGrid[i].length = rows
            for (int o = 0; o < brickGrid[i].length; o++)
            {
                if(brickGrid[i][o].isVisibleGet() &&  this.intersects(brickGrid[i][o]))
                {
                    brokenBrick = brickGrid[i][o];
                    setType(brickGrid[i][o].getType());
                    ballColor = new Color(brickGrid[i][o].getColor().getRed(), brickGrid[i][o].getColor().getGreen(), brickGrid[i][o].getColor().getBlue(), 255);
                    if (GDV5.collisionDirection(brickGrid[i][o], this, xVelocity, yVelocity) % 2 == 0) xVelocity *= -1;
                    if (GDV5.collisionDirection(brickGrid[i][o], this, xVelocity, yVelocity) % 2 == 1) yVelocity *= -1;
                    AudioPlayer.playAudio("utilities\\Sounds\\Bounce.wav");
                }
            }
        }
        if (brokenBrick != null)
        {
            if(brokenBrick.breakBrick())
            {
                brokenBrick = null;
            }
        }

        this.moveBall(xVelocity, yVelocity);
    }

    public void setType(int type)
    {
        this.type = type;
        if (type != 1)
        {
            xVelocity = (int)(type/1.5) * xVelocityD;
            yVelocity = (int)(type/1.5) * yVelocityD;
        }
        else 
        {
            xVelocity = xVelocityD;
            yVelocity = yVelocityD;
        }
    }

    public Color getColor()
    {
        return ballColor;
    }

    public int getType()
    {
        return type;
    }

    // Brick Collision Method
    // Uses Fast Algorithm x + y - 1 in boundary
    
}