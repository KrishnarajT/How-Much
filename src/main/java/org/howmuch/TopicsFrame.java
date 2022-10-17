package org.howmuch;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static org.howmuch.Main.*;


// The topics can be
// Technology
// Fashion
// Health and Household
// Miscellaneous


public class TopicsFrame extends JFrame {

    BackgroundPanel backgroundPanel;
    JButton technology_btn;
    JButton fashion_btn;
    JButton household_btn;
    JButton miscellaneous_btn;
    JButton backToMenu_btn;
    JPanel options_panel;

    TopicsFrame() {
        backgroundPanel = new BackgroundPanel();

        this.setTitle("How Much?");
        if (maxmized) {
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
        createButtons();
        createPanels();
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
        this.add(options_panel);
        this.add(basicButtons_pnl);
        this.add(backgroundPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void reassignBounds() {
        Dimension screenSize = this.getSize();

        // The back to menu mode label
        backToMenu_btn.setBounds((int) (0.015 * screenSize.getWidth()), (int) (0.80 * screenSize.getHeight()), (int) (0.20 * screenSize.getWidth()), (int) (0.07 * screenSize.getHeight()));
        backToMenu_btn.setFont(buttonFont.deriveFont((float) (0.05 * getHeight())));

        // The Entire basic button panel for closing minimizing and stuff
        basicButtons_pnl.setBounds(this.getWidth() - (exit_btn.getWidth() * 3) - 40, 10, exit_btn.getWidth() * 3 + 35, exit_btn.getHeight());

        // Options panel
        options_panel.setBounds((int) (0.60 * screenSize.getWidth()), (int) (0.34 * screenSize.getHeight()), (int) (0.45 * screenSize.getWidth()), 700);

        // Buttons in the Options Panel
        technology_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 80));
        technology_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));

        fashion_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 80));
        fashion_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));

        household_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 70));
        household_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));

        miscellaneous_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 70));
        miscellaneous_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));
    }

    private void reassignColors() {

        if (Colors.DarkMode) {
            backgroundPanel.setBackground("src/main/resources/images/choose topic dark.png");
        } else {
            backgroundPanel.setBackground("src/main/resources/images/choose topic.png");
        }
        Colors.reassignColors();
        basicButtons_pnl.setBackground(Colors.bgColor);
        exit_btn.setBackground(Colors.bgColor);
        resize_btn.setBackground(Colors.bgColor);
        backToMenu_btn.setBackground(Colors.primaryColor);
        backToMenu_btn.setForeground(Colors.bgColor);
        minimize_btn.setBackground(Colors.bgColor);
        technology_btn.setBackground(Colors.bgColor);
        technology_btn.setForeground(Colors.primaryColor);
        fashion_btn.setBackground(Colors.bgColor);
        fashion_btn.setForeground(Colors.primaryColor);
        miscellaneous_btn.setBackground(Colors.bgColor);
        miscellaneous_btn.setForeground(Colors.primaryColor);
        household_btn.setBackground(Colors.bgColor);
        household_btn.setForeground(Colors.primaryColor);
    }


    private void createPanels() {
        options_panel = new JPanel();
        BoxLayout bl = new BoxLayout(options_panel, BoxLayout.Y_AXIS);
        options_panel.setLayout(bl);
        options_panel.add(technology_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(fashion_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(household_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(miscellaneous_btn);
        options_panel.setBackground(new Color(0, 0, 0, 0));

    }
    private void createButtons() {

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
            if (!Main.maxmized) {
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
            Main.maxmized = !Main.maxmized;
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


        backToMenu_btn = new JButton();
        backToMenu_btn.setText("Back to Menu ");
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


        technology_btn = new JButton();
        technology_btn.setText("Technology ");
        technology_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        technology_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        technology_btn.setFocusPainted(false);
        technology_btn.setBounds(0, 0, 500, 500);
        technology_btn.setContentAreaFilled(false);
        technology_btn.setOpaque(true);
        technology_btn.setBorder(null);
        technology_btn.addChangeListener(evt -> {
            if (technology_btn.getModel().isPressed()) {
                technology_btn.setForeground(Colors.accentColor);
            } else if (technology_btn.getModel().isRollover()) {
                technology_btn.setForeground(Colors.accentColor);
            } else {
                technology_btn.setForeground(Colors.primaryColor);
            }
        });

        technology_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(6);
        });

        fashion_btn = new JButton();
        fashion_btn.setText("Fashion ");
        fashion_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        fashion_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        fashion_btn.setFocusPainted(false);
        fashion_btn.setContentAreaFilled(false);
        fashion_btn.setOpaque(true);
        fashion_btn.setBorder(null);
        fashion_btn.addChangeListener(evt -> {
            if (fashion_btn.getModel().isPressed()) {
                fashion_btn.setForeground(Colors.accentColor);
            } else if (fashion_btn.getModel().isRollover()) {
                fashion_btn.setForeground(Colors.accentColor);
            } else {
                fashion_btn.setForeground(Colors.primaryColor);
            }
        });
        fashion_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(6);
        });

        household_btn = new JButton();
        household_btn.setText("Household ");
        household_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        household_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        household_btn.setFocusPainted(false);
        household_btn.setContentAreaFilled(false);
        household_btn.setOpaque(true);
        household_btn.setBorder(null);
        household_btn.addChangeListener(evt -> {
            if (household_btn.getModel().isPressed()) {
                household_btn.setForeground(Colors.accentColor);
            } else if (household_btn.getModel().isRollover()) {
                household_btn.setForeground(Colors.accentColor);
            } else {
                household_btn.setForeground(Colors.primaryColor);
            }
        });
        household_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(6);
        });

        miscellaneous_btn = new JButton();
        miscellaneous_btn.setText("Miscellaneous ");
        miscellaneous_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        miscellaneous_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        miscellaneous_btn.setFont(buttonFont.deriveFont(44f));
        miscellaneous_btn.setFocusPainted(false);
        miscellaneous_btn.setContentAreaFilled(false);
        miscellaneous_btn.setOpaque(true);
        miscellaneous_btn.setBorder(null);
        miscellaneous_btn.addChangeListener(evt -> {
            if (miscellaneous_btn.getModel().isPressed()) {
                miscellaneous_btn.setForeground(Colors.accentColor);
            } else if (miscellaneous_btn.getModel().isRollover()) {
                miscellaneous_btn.setForeground(Colors.accentColor);
            } else {
                miscellaneous_btn.setForeground(Colors.primaryColor);
            }
        });
        miscellaneous_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(7);
        });
    }

}
