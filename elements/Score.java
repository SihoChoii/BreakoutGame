package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import utilities.GDV5;

// Can be non static but will need to be instantiated in the main class
// which is redundant for now
public class Score 
{
    private static int score = 0;
    private static int deaths = 0;

    public static void setScore(int num)
    {
        score = num;
    }

    public static int getScore()
    {
        return score;
    }

    public static void setDeath(int num)
    {
        deaths = num;
    }
    
    public static int getDeath()
    {
        return deaths;
    }

    // Score drawer
    public void draw(Graphics2D win)
    {
        win.setColor(Color.white);
        win.setFont(new Font("Arial", Font.BOLD, 50));
        //                                                             Font & Letters
        win.drawString("Score: " + score + " | Deaths: " + deaths, 0 + GDV5.getPadding(), GDV5.getMaxWindowY() - GDV5.getPadding());
    }
}
