package JumpingJerry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame
{
    public GameFrame()
    {
        setTitle("Jumping Jerry");
        setSize(1800, 1200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        GameComponent gameComponent = new GameComponent();
        root.add(gameComponent, BorderLayout.CENTER);

        JButton button = createFireButton(gameComponent);

        root.add(button, BorderLayout.SOUTH);
        this.getRootPane().setDefaultButton(button);
        button.requestFocus();
        setContentPane(root);


    }
    private JButton createFireButton(GameComponent gameComponent)
    {
        JButton fire = new JButton("JUMP");
        fire.setBackground(new Color(0xFE2D4D));
        fire.addActionListener(e -> gameComponent.jumpJerry());
        return fire;
    }
}