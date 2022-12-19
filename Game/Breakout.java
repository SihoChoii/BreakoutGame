// Dynamic Breakout Game
// By: Siho Choi
// Date: 12/5/2022
// Version: 1.0.7

package Game;

import utilities.GDV5;
import elements.Brick;
import java.awt.Graphics2D;
import elements.Ball;
import elements.Paddle;
import utilities.AudioPlayer;
import elements.Score;

public class Breakout extends GDV5
{
    private Brick[][] brickGrid;
    private Ball ball;
    private Paddle paddle;
    private Score score = new Score();
    private int count = 0;

    public Breakout()
    {
        // super();        
        brickGrid = Brick.makeBricks(8,9, 30, 20, GDV5.getMaxWindowX(), (int)(GDV5.getMaxWindowY()/2.5));
        ball = new Ball(GDV5.getMaxWindowX()/2-10, GDV5.getMaxWindowY()-(GDV5.getMaxWindowY()/2), 20);
        paddle = new Paddle(500, 800, 60);
    }

    public static void main(String[] args)
    {
        Breakout game = new Breakout();
        setDebugMessage(true);
        AudioPlayer.playAudio("utilities\\Sounds\\Song1.wav");
        game.start();
    }

    @Override
    public void update()
    {
        ball.update(brickGrid, paddle);
        paddle.update();
    }

    @Override
    public void draw(Graphics2D win)
    {
        Brick.drawBirckGrid(brickGrid, win);
        ball.draw(win);
        paddle.draw(win);
        score.draw(win);
    }

    // public void playSound(int num)
    // {
    //     audioPlayer.play(num);
    // }
}
