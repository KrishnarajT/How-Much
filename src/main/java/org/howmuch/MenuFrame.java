package org.howmuch;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static org.howmuch.Main.*;

public class MenuFrame extends JFrame {

    MenuPanel menuPanel;
    JButton chooseTopic_btn;
    JButton viewHighscore_btn;
    JButton helpAndCredits_btn;
    JButton updateDatabase_btn;
    JToggleButton darkMode_tglbtn;
    JPanel options_panel;
    JLabel darkMode_lbl;

    MenuFrame() {
        menuPanel = new MenuPanel();

        this.setTitle("How Much? ");
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
        createLabels();
        createToggles();
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

        this.add(options_panel);
        this.add(darkMode_tglbtn);
        this.add(darkMode_lbl);
        this.add(basicButtons_pnl);
        this.add(menuPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void reassignBounds() {
        Dimension screenSize = this.getSize();

        // The Entire basic button panel for closing minimizing and stuff
        basicButtons_pnl.setBounds(this.getWidth() - (exit_btn.getWidth() * 3) - 40, 10, exit_btn.getWidth() * 3 + 35, exit_btn.getHeight());

        // The Dark mode label
        darkMode_lbl.setBounds((int) (0.017 * screenSize.getWidth()), (int) (0.80 * screenSize.getHeight()), 400, 50);
        darkMode_lbl.setFont(buttonFont.deriveFont((float) (0.05 * getHeight())));

        // The Dark Mode toggle button
        darkMode_tglbtn.setBounds((int) (0.17 * screenSize.getWidth()), (int) (0.805 * screenSize.getHeight()), 60, 40);

        // Options panel
        options_panel.setBounds((int) (0.63 * screenSize.getWidth()), (int) (0.34 * screenSize.getHeight()), (int) (0.4 * screenSize.getWidth()), 700);

        // Buttons in the Options Panel
        chooseTopic_btn.setBounds(new Rectangle(300, 70));
        chooseTopic_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));
        viewHighscore_btn.setBounds(new Rectangle(300, 70));
        viewHighscore_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));
        helpAndCredits_btn.setBounds(new Rectangle(300, 70));
        helpAndCredits_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));
        updateDatabase_btn.setBounds(new Rectangle((int) (0.23 * screenSize.getWidth()), 70));
        updateDatabase_btn.setFont(buttonFont.deriveFont((float) (0.07 * getHeight())));
    }

    private void reassignColors() {

        if (Colors.DarkMode) {
            menuPanel.setBackground("src/main/resources/images/Main_Menu_bg_dark.png");
        } else {
            menuPanel.setBackground("src/main/resources/images/Main_Menu_bg.png");
        }
        Colors.reassignColors();
        basicButtons_pnl.setBackground(Colors.bgColor);
        darkMode_tglbtn.setBackground(Colors.bgColor);
        darkMode_lbl.setBackground(Colors.primaryColor);
        darkMode_lbl.setForeground(Colors.bgColor);
        exit_btn.setBackground(Colors.bgColor);
        resize_btn.setBackground(Colors.bgColor);
        minimize_btn.setBackground(Colors.bgColor);
        chooseTopic_btn.setBackground(Colors.bgColor);
        chooseTopic_btn.setForeground(Colors.primaryColor);
        viewHighscore_btn.setBackground(Colors.bgColor);
        viewHighscore_btn.setForeground(Colors.primaryColor);
        updateDatabase_btn.setBackground(Colors.bgColor);
        updateDatabase_btn.setForeground(Colors.primaryColor);
        helpAndCredits_btn.setBackground(Colors.bgColor);
        helpAndCredits_btn.setForeground(Colors.primaryColor);
    }


    private void createPanels() {
        options_panel = new JPanel();
        BoxLayout bl = new BoxLayout(options_panel, BoxLayout.Y_AXIS);
        options_panel.setLayout(bl);
        options_panel.add(chooseTopic_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(viewHighscore_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(helpAndCredits_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(updateDatabase_btn);
        options_panel.setBackground(new Color(0, 0, 0, 0));

    }


    private void createToggles() {
        ImageIcon toggle_on = new ImageIcon("src/main/resources/icons/toggle_on.png");
        ImageIcon toggle_off = new ImageIcon("src/main/resources/icons/toggle_off.png");

        darkMode_tglbtn = new JToggleButton();
        darkMode_tglbtn.setOpaque(false);
        darkMode_tglbtn.setBorder(null);
        darkMode_tglbtn.setFocusPainted(false);
        darkMode_tglbtn.setContentAreaFilled(false);
        if (Colors.DarkMode) {
            darkMode_tglbtn.setIcon(toggle_on);
        } else {
            darkMode_tglbtn.setIcon(toggle_off);
        }

        darkMode_tglbtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (darkMode_tglbtn.isSelected()) {
                    System.out.println("yes");
                    darkMode_tglbtn.setIcon(toggle_on);

                    Colors.DarkMode = true;
                    menuPanel.setBackground("src/main/resources/images/Main_Menu_bg_dark.png");
                    reassignColors();
                    repaint();

                } else {
                    System.out.println("no");
                    darkMode_tglbtn.setIcon(toggle_off);

                    Colors.DarkMode = false;
                    menuPanel.setBackground("src/main/resources/images/Main_Menu_bg.png");
                    reassignColors();
                    repaint();
                }
            }
        });

    }

    private void createLabels() {
        darkMode_lbl = new JLabel();
        darkMode_lbl.setAlignmentY(Box.CENTER_ALIGNMENT);
        darkMode_lbl.setAlignmentX(Box.CENTER_ALIGNMENT);
        darkMode_lbl.setFont(buttonFont.deriveFont((float) (0.05 * getHeight())));
        darkMode_lbl.setOpaque(true);
        darkMode_lbl.setBorder(null);

        darkMode_lbl.setText("Dark Mode: ");
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

        chooseTopic_btn = new JButton();
        chooseTopic_btn.setText("Choose Topic");
        chooseTopic_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        chooseTopic_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        chooseTopic_btn.setFocusPainted(false);
        chooseTopic_btn.setBounds(0, 0, 500, 500);
        chooseTopic_btn.setContentAreaFilled(false);
        chooseTopic_btn.setOpaque(true);
        chooseTopic_btn.setBorder(null);
        chooseTopic_btn.addChangeListener(evt -> {
            if (chooseTopic_btn.getModel().isPressed()) {
                chooseTopic_btn.setForeground(Colors.accentColor);
            } else if (chooseTopic_btn.getModel().isRollover()) {
                chooseTopic_btn.setForeground(Colors.accentColor);
            } else {
                chooseTopic_btn.setForeground(Colors.primaryColor);
            }
        });

        chooseTopic_btn.addActionListener(e -> {
            Main.changeFrame(0);
        });

        viewHighscore_btn = new JButton();
        viewHighscore_btn.setText("View Highscore");
        viewHighscore_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        viewHighscore_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        viewHighscore_btn.setFocusPainted(false);
        viewHighscore_btn.setContentAreaFilled(false);
        viewHighscore_btn.setOpaque(true);
        viewHighscore_btn.setBorder(null);
        viewHighscore_btn.addChangeListener(evt -> {
            if (viewHighscore_btn.getModel().isPressed()) {
                viewHighscore_btn.setForeground(Colors.accentColor);
            } else if (viewHighscore_btn.getModel().isRollover()) {
                viewHighscore_btn.setForeground(Colors.accentColor);
            } else {
                viewHighscore_btn.setForeground(Colors.primaryColor);
            }
        });
        viewHighscore_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(4);
        });

        helpAndCredits_btn = new JButton();
        helpAndCredits_btn.setText("Help and Credits");
        helpAndCredits_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        helpAndCredits_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        helpAndCredits_btn.setFocusPainted(false);
        helpAndCredits_btn.setContentAreaFilled(false);
        helpAndCredits_btn.setOpaque(true);
        helpAndCredits_btn.setBorder(null);
        helpAndCredits_btn.addChangeListener(evt -> {
            if (helpAndCredits_btn.getModel().isPressed()) {
                helpAndCredits_btn.setForeground(Colors.accentColor);
            } else if (helpAndCredits_btn.getModel().isRollover()) {
                helpAndCredits_btn.setForeground(Colors.accentColor);
            } else {
                helpAndCredits_btn.setForeground(Colors.primaryColor);
            }
        });
        helpAndCredits_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(3);
        });

        updateDatabase_btn = new JButton();
        updateDatabase_btn.setText("Update Database");
        updateDatabase_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        updateDatabase_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        updateDatabase_btn.setFont(buttonFont.deriveFont(44f));
        updateDatabase_btn.setFocusPainted(false);
        updateDatabase_btn.setContentAreaFilled(false);
        updateDatabase_btn.setOpaque(true);
        updateDatabase_btn.setBorder(null);
        updateDatabase_btn.addChangeListener(evt -> {
            if (updateDatabase_btn.getModel().isPressed()) {
                updateDatabase_btn.setForeground(Colors.accentColor);
            } else if (updateDatabase_btn.getModel().isRollover()) {
                updateDatabase_btn.setForeground(Colors.accentColor);
            } else {
                updateDatabase_btn.setForeground(Colors.primaryColor);
            }
        });
        updateDatabase_btn.addActionListener(e -> {
            Main.changeFrame(0);
        });
    }

}