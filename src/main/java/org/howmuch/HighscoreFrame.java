package org.howmuch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static org.howmuch.Main.*;
import static org.howmuch.MenuFrame.*;


public class HighscoreFrame extends JFrame{
    HighscorePanel highscorePanel;
    JButton backToMenu_btn;
    HighscoreFrame() {
        highscorePanel = new HighscorePanel();

        this.setTitle("How Much? ");
        this.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setMinimumSize(new Dimension(1280, 720));

        createFonts();
        createButtons();
        createBasicButtonPanel();
        reassignColors();
        reassignBounds();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reassignBounds();
                repaint();
            }
        });

        this.add(backToMenu_btn);
        this.add(basicButtons_pnl);
        this.add(highscorePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void reassignBounds() {
        Dimension screenSize = this.getSize();

        // The back to menu mode label
        backToMenu_btn.setBounds((int) (0.025 * screenSize.getWidth()), (int) (0.80 * screenSize.getHeight()), (int) (0.16 * screenSize.getWidth()), (int) (0.07 * screenSize.getHeight()));
        backToMenu_btn.setFont(buttonFont.deriveFont((float) (0.05 * getHeight())));

        // The Entire basic button panel for closing minimizing and stuff
        basicButtons_pnl.setBounds(this.getWidth() - (exit_btn.getWidth() * 3) - 40, 10, exit_btn.getWidth() * 3 + 35, exit_btn.getHeight());

    }

    private void reassignColors() {
        Colors.reassignColors();
        if(Colors.DarkMode)
        {
            highscorePanel.setBackground("src/main/resources/images/highscore dark.png");
        }
        else {
            highscorePanel.setBackground("src/main/resources/images/highscore.png");
        }
        backToMenu_btn.setBackground(Colors.primaryColor);
        backToMenu_btn.setForeground(Colors.bgColor);

        basicButtons_pnl.setBackground(Colors.bgColor);
        exit_btn.setBackground(Colors.bgColor);
        resize_btn.setBackground(Colors.bgColor);
        minimize_btn.setBackground(Colors.bgColor);
    }

    private void createButtons() {
        backToMenu_btn = new JButton();
        backToMenu_btn.setText("Back to Menu");
        backToMenu_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        backToMenu_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        backToMenu_btn.setFocusPainted(false);
        backToMenu_btn.setContentAreaFilled(false);
        backToMenu_btn.setOpaque(true);
        backToMenu_btn.setBorder(null);
        backToMenu_btn.addChangeListener(evt -> {
            if (backToMenu_btn.getModel().isPressed()) {
                backToMenu_btn.setForeground(Colors.bgColor);
            } else if (backToMenu_btn.getModel().isRollover()) {
                backToMenu_btn.setForeground(Colors.accentColor);
            } else {
                backToMenu_btn.setForeground(Colors.bgColor);
            }
        });
        backToMenu_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(1);
        });
    }

}
