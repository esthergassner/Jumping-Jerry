package JumpingJerry;

import javax.swing.*;
import java.awt.*;


public class GameComponent extends JComponent
{
    //TODO
    //make it so that the game gets harder when the score reaches certain checkpoints
    //make the game over check more precise
    //restart button
    //enhance animation - running legs, changing when hit, change at jump, smoother jump
    //beautify aesthetics - pretty floor and background, more elements in the scene (maybe furniture?)
    //fix font and size of score count
    //introductory dialogue box (or splash screen of some sort) with welcome, rules and button to begin play

    private double tomMovement = 0.0;
    ImageIcon tomImage = new ImageIcon("tom.png");
    Image tom = tomImage.getImage();
    ImageIcon jerryImage = new ImageIcon("jerry.png");
    Image jerry = jerryImage.getImage();
    private int floorHeight = 600;
    private int x = 100, y = 600;
    private boolean end = false;
    private int score = 0;


    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawSky(graphics, new Color(0x347FE7));
        drawGround(graphics, new Color(0xFE2D4D));
        drawJerry(graphics);
        drawToms(graphics);
        displayScore(graphics);
        if (!end) {
            repaint();
        }
    }

    private void drawSky(Graphics graphics, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawGround(Graphics graphics, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(this.getX(), floorHeight, this.getWidth(), getHeight() - floorHeight);
    }

    private void displayScore(Graphics graphics)
    {
        graphics.setColor(new Color(0xBC0C65));
        graphics.drawString("Score: " + score, 25, 25);
    }

    private void drawToms(Graphics graphics)
    {
        if (tomMovement > getWidth() + 100)
        {
            tomMovement = 0.0;
        }
            int tomLocX = (int) (getWidth() - tomMovement);
            int tomLocY = floorHeight - jerry.getHeight(null);
            graphics.drawImage(
                tom,
                tomLocX,
                tomLocY,
                null);
            if (tomLocX == x && tomLocY == y - jerry.getHeight(null))
            {
                end = gameOver(graphics);
            }
            if (tomLocX == x && tomLocY != y - jerry.getHeight(null))
            {
                score++;
            }
            tomMovement += 3;
    }

    private boolean gameOver(Graphics graphics)
    {
        ImageIcon end = new ImageIcon("pow.png");
        graphics.drawImage(end.getImage(),
                getWidth()/2 - end.getIconWidth()/2,
                getHeight()/2 - end.getIconHeight(),
                null);
        graphics.setColor(new Color(1));
        graphics.drawString("GAME OVER. SCORE: " + score, getWidth()/2 -60,
                getHeight()/2 - 120);
        return true;
    }

    private void drawJerry(Graphics graphics)
    {
        graphics.drawImage(jerry, x, y - jerry.getHeight(null), null);
    }

    void jumpJerry()
    {
        y -= 250;
        new java.util.Timer().schedule(
            new java.util.TimerTask()
            {
                @Override
                public void run()
                {
                    y += 250;
                }
            },
            750
        );
    }
}
