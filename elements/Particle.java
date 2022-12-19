package elements;

import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Color;

public class Particle extends Brick
{

    static Particle[] particles;

    public Particle(int x, int y, int w, int h)
    {
        super (x, y, w, h);
    }

    public static Particle[] getParticleArray()
    {
        return particles;
    }

    public static void makeParticleArray(Brick brick)
    {
        particles = new Particle[60];
        int x = (int)brick.getX();
        int y = (int)brick.getY();
        int partPerRow = 32;
        int rowPerArr = 20;
        int n = 1;
        for (int i = 0; i < particles.length; i++)
        {
            particles[i] = new Particle(x, y, (int)(brick.getWidth()/partPerRow), (int)(brick.getHeight()/rowPerArr));
            particles[i].setColor(new Color(brick.getColor().getRed(), brick.getColor().getGreen(), brick.getColor().getBlue(), 250)); 
            x += (int)(brick.getWidth()/partPerRow);
            if (n % partPerRow == 0)
            {
                x = (int)brick.getX();
                y += (int)(brick.getHeight()/rowPerArr);
            }
            n++;
        }
    }

    public void moveParticle()
    {
        Random rand = new Random();
        double dx = (rand.nextInt(100)-50)/8;
        double dy = (rand.nextInt(100)-50)/8;
        this.x += dx;
        this.y += dy;
    }

    public static boolean moveParticles()
    {
        for (Particle p: particles)
        {
            p.moveParticle();
        }
        return true;
    }

    public static void update()
    {
        
    }

    public void draw(Graphics2D win)
    {
        win.setColor(this.getColor());
        win.fill(this);
    }

    public static void drawParticles(Graphics2D win)
    {
        for (Particle p: particles)
        {
            p.draw(win);
        }
    }
}