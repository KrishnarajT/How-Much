package org.howmuch;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static org.howmuch.Main.*;


public class GameOverFrame extends JFrame {
    BackgroundPanel backgroundPanel;
    JButton backtoTopic_btn;
    JLabel score_lbl;

    GameOverFrame() {
        backgroundPanel = new BackgroundPanel();

        this.setTitle("How Much? ");
        if (maximized) {
            this.setExtendedState(MAXIMIZED_BOTH);
        } else {
            this.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setMinimumSize(new Dimension(1280, 720));

        createFonts();
        createBasicButtonPanel();
        createLabels();
        createButtons();
        reassignColors();
        reassignBounds();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reassignBounds();
                repaint();
            }
        });

        this.add(score_lbl);
        this.add(backtoTopic_btn);
        this.add(basicButtons_pnl);
        this.add(backgroundPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void reassignBounds() {
        Dimension screenSize = this.getSize();

        // The back to menu mode label
        backtoTopic_btn.setBounds((int) (0.001 * screenSize.getWidth()), (int) (0.80 * screenSize.getHeight()), (int) (0.20 * screenSize.getWidth()), (int) (0.07 * screenSize.getHeight()));
        backtoTopic_btn.setFont(buttonFont.deriveFont((float) (0.06 * getHeight())));

        // The Entire basic button panel for closing minimizing and stuff
        basicButtons_pnl.setBounds(this.getWidth() - (exit_btn.getWidth() * 3) - 40, 10, exit_btn.getWidth() * 3 + 35, exit_btn.getHeight());

        // Score Label
        score_lbl.setBounds((int) (0.76 * screenSize.getWidth()), (int) (0.80 * screenSize.getHeight()), 400, 100);
        score_lbl.setFont(buttonFont.deriveFont((float) (0.14 * getHeight())));
    }

    private void reassignColors() {
        Colors.reassignColors();
        if (Colors.DarkMode) {
            backgroundPanel.setBackground("src/main/resources/images/game over dark.png");
        } else {
            backgroundPanel.setBackground("src/main/resources/images/game over.png");
        }
        backtoTopic_btn.setBackground(Colors.primaryColor);
        backtoTopic_btn.setForeground(Colors.bgColor);

        score_lbl.setBackground(Colors.bgColor);
        score_lbl.setForeground(Colors.accentColor);

        basicButtons_pnl.setBackground(Colors.bgColor);
        exit_btn.setBackground(Colors.bgColor);
        resize_btn.setBackground(Colors.bgColor);
        minimize_btn.setBackground(Colors.bgColor);
    }
    private void createLabels() {
        score_lbl = new JLabel();
        score_lbl.setText(String.valueOf(DataBaseManager.currentScore));
        score_lbl.setAlignmentY(Box.CENTER_ALIGNMENT);
        score_lbl.setAlignmentX(Box.CENTER_ALIGNMENT);
        score_lbl.setOpaque(true);
        score_lbl.setBorder(null);
    }

    private void createButtons() {
        backtoTopic_btn = new JButton();
        backtoTopic_btn.setText("Try Again");
        backtoTopic_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        backtoTopic_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        backtoTopic_btn.setFocusPainted(false);
        backtoTopic_btn.setContentAreaFilled(false);
        backtoTopic_btn.setOpaque(true);
        backtoTopic_btn.setBorder(null);
        backtoTopic_btn.addChangeListener(evt -> {
            if (backtoTopic_btn.getModel().isPressed()) {
                backtoTopic_btn.setForeground(Colors.bgColor);
            } else if (backtoTopic_btn.getModel().isRollover()) {
                backtoTopic_btn.setForeground(Colors.accentColor);
            } else {
                backtoTopic_btn.setForeground(Colors.bgColor);
            }
        });
        backtoTopic_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            grantAccess = true;
            Main.changeFrame(2);
        });

        // Removing Change and Action Listeners.
        for (ActionListener listener : exit_btn.getActionListeners()) {
            exit_btn.removeActionListener(listener);
        }
        for (ChangeListener listener : exit_btn.getChangeListeners()) {
            exit_btn.removeChangeListener(listener);
        }

        for (ActionListener listener : resize_btn.getActionListeners()) {
            resize_btn.removeActionListener(listener);
        }
        for (ChangeListener listener : resize_btn.getChangeListeners()) {
            resize_btn.removeChangeListener(listener);
        }

        for (ActionListener listener : minimize_btn.getActionListeners()) {
            minimize_btn.removeActionListener(listener);
        }
        for (ChangeListener listener : minimize_btn.getChangeListeners()) {
            minimize_btn.removeChangeListener(listener);
        }


        exit_btn.addChangeListener(evt -> {
            if (exit_btn.getModel().isPressed()) {
                exit_btn.setForeground(Colors.primaryColor);
                Main.changeFrame(0);
            } else if (exit_btn.getModel().isRollover()) {
                exit_btn.setForeground(Colors.secondaryColor);
            } else {
                exit_btn.setForeground(Colors.primaryColor);
            }
        });
        resize_btn.addActionListener(e -> {
            if (!Main.maximized) {
                this.setExtendedState(MAXIMIZED_BOTH);
                resize_btn.setIcon(new ImageIcon(resizeDown_image));
            } else {
                this.setExtendedState(JFrame.NORMAL);
                this.setLocationRelativeTo(null);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - Main.WIDTH) / 2);
                int y = (int) ((dimension.getHeight() - Main.HEIGHT) / 2);
                this.setBounds(x, y, Main.WIDTH, Main.HEIGHT);
                resize_btn.setIcon(new ImageIcon(resizeUp_image));
            }
            Main.maximized = !Main.maximized;
        });

        minimize_btn.addChangeListener(evt -> {
            if (minimize_btn.getModel().isPressed()) {
                this.setState(JFrame.ICONIFIED);
                minimize_btn.setForeground(Colors.primaryColor);
            } else if (minimize_btn.getModel().isRollover()) {
                minimize_btn.setForeground(Colors.secondaryColor);
            } else {
                minimize_btn.setForeground(Colors.primaryColor);
            }
        });
    }
}
