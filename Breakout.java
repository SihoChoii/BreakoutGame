// Dynamic Breakout Game
// By: Siho Choi
// Date: 12/5/2022
// Version: 1.0.6

import utilities.GDV5;
import elements.Brick;
import java.awt.Graphics2D;
import elements.Ball;
import elements.Paddle;

public class Breakout extends GDV5
{
    Brick[][] brickGrid;
    Ball ball;
    Paddle paddle;

    public Breakout()
    {
        // super();        
        brickGrid = Brick.makeBricks(10,11, 30, 20, GDV5.getMaxWindowX(), (int)(GDV5.getMaxWindowY()/2.5));
        ball = new Ball(500, 500, 20);
        paddle = new Paddle(500, 800, 60);
    }

    public static void main(String[] args)
    {
        Breakout game = new Breakout();
        setDebugMessage(true);
        game.start();
    }

    @Override
    public void update()
    {
        ball.update(brickGrid);
        paddle.update();
    }

    @Override
    public void draw(Graphics2D win)
    {
        Brick.drawBirckGrid(brickGrid, win);
        ball.draw(win);
        paddle.draw(win);
    }
}
