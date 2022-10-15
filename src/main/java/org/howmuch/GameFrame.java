package org.howmuch;

import javax.swing.*;
import java.awt.*;

// The frame is the window basically, it has the title bar and everything.
// Panels are like frames in tkinter. They exist inside the frames, surrounded by the frames.

public class GameFrame extends JFrame {

    GamePanel gamePanel;


    // This frame has a constructor, and every time we call the constructor, we want it to do some stuff.

    GameFrame() {
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(new Color(36, 134, 205));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // this is a mode where the frame will adjust according to the size of the panel.

        // and finally
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



}
