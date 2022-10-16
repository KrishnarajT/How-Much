package org.howmuch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MenuFrame extends JFrame {

    MenuPanel menuPanel;
    JButton exit_btn, resize_btn, minimize_btn, chooseTopic_btn, viewHighscore_btn, helpAndCredits_btn, updateDatabase_btn;
    JToggleButton darkMode_tglbtn;
    JPanel basicButtons_pnl;
    JLabel darkMode_lbl, background_lbl;
    Font buttonFont;

    MenuFrame() {
        menuPanel = new MenuPanel();
        menuPanel.setBackground("src/main/resources/images/Main_Menu_bg.png");

        this.setTitle("How Much? ");
        this.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setMinimumSize(new Dimension(1280, 720));

        createFonts();
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
        darkMode_tglbtn.setBounds((int) (0.17 * screenSize.getWidth()), (int) (0.80 * screenSize.getHeight()), 60, 40);
    }

    private void reassignColors() {
        Colors.resetColors();
        basicButtons_pnl.setBackground(Colors.bgColor);
        darkMode_tglbtn.setBackground(Colors.bgColor);
        darkMode_lbl.setBackground(Colors.primaryColor);
        darkMode_lbl.setForeground(Colors.bgColor);
        exit_btn.setBackground(Colors.bgColor);
        resize_btn.setBackground(Colors.bgColor);
        minimize_btn.setBackground(Colors.bgColor);
    }

    private void createPanels() {
        basicButtons_pnl = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 10, 0);
        basicButtons_pnl.setLayout(fl);
        basicButtons_pnl.add(minimize_btn);
        basicButtons_pnl.add(resize_btn);
        basicButtons_pnl.add(exit_btn);
    }

    public void createFonts() {
        try {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/MomcakeBold-WyonA.otf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(buttonFont);

//
//            textFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/MomcakeBold-WyonA.otf")).deriveFont(50f);
//            //register the font
//            ge.registerFont(buttonFont);
//
//            password_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/CaeciliaLTPro45Light.TTF")).deriveFont(35f);
//            //register the font
//            ge.registerFont(buttonFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createToggles() {
        ImageIcon toggle_on = new ImageIcon("src/main/resources/icons/toggle_on.png");
        ImageIcon toggle_off = new ImageIcon("src/main/resources/icons/toggle_off.png");

        darkMode_tglbtn = new JToggleButton();
        darkMode_tglbtn.setOpaque(false);
        darkMode_tglbtn.setBorder(null);
        darkMode_tglbtn.setFocusPainted(false);
        darkMode_tglbtn.setContentAreaFilled(false);
        darkMode_tglbtn.setIcon(toggle_off);

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


        ImageIcon exit = new ImageIcon("src/main/resources/icons/circle_delete.png");
        Image exit_image = exit.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon minimize = new ImageIcon("src/main/resources/icons/circle_minus.png");
        Image minimize_image = minimize.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizeUp = new ImageIcon("src/main/resources/icons/resize_3.png");
        Image resizeUp_image = resizeUp.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizeDown = new ImageIcon("src/main/resources/icons/resize_4.png");
        Image resizeDown_image = resizeDown.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        exit_btn = new JButton();
//        exit_btn.setText("-");
        exit_btn.setIcon(new ImageIcon(exit_image));
        exit_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        exit_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        exit_btn.setBounds(new Rectangle(25, 25));
        exit_btn.setFont(buttonFont.deriveFont(44f));
        exit_btn.setFocusPainted(false);
        exit_btn.setContentAreaFilled(false);
        exit_btn.setOpaque(true);
        exit_btn.setBorder(null);
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

        resize_btn = new JButton();
        resize_btn.setIcon(new ImageIcon(resizeUp_image));
        resize_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        resize_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        resize_btn.setBounds(new Rectangle(25, 25));
        resize_btn.setFont(buttonFont.deriveFont(44f));
        resize_btn.setFocusPainted(false);
        resize_btn.setContentAreaFilled(false);
        resize_btn.setOpaque(true);
        resize_btn.setBorder(null);
        resize_btn.addChangeListener(evt -> {
            if (exit_btn.getModel().isPressed()) {
//                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                exit_btn.setForeground(Colors.primaryColor);
            } else if (exit_btn.getModel().isRollover()) {
                exit_btn.setForeground(Colors.secondaryColor);
            } else {
                exit_btn.setForeground(Colors.primaryColor);
            }
        });

        resize_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Main.maxmized) {
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    resize_btn.setIcon(new ImageIcon(resizeDown_image));

                } else {
                    setExtendedState(JFrame.NORMAL);
                    setLocationRelativeTo(null);
                    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (int) ((dimension.getWidth() - Main.WIDTH) / 2);
                    int y = (int) ((dimension.getHeight() - Main.HEIGHT) / 2);
                    setBounds(x, y, Main.WIDTH, Main.HEIGHT);
                    resize_btn.setIcon(new ImageIcon(resizeUp_image));
                }
                Main.maxmized = !Main.maxmized;
            }
        });

        minimize_btn = new JButton();
        minimize_btn.setIcon(new ImageIcon(minimize_image));
        minimize_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        minimize_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        minimize_btn.setBounds(new Rectangle(25, 25));
        minimize_btn.setFont(buttonFont.deriveFont(44f));
        minimize_btn.setFocusPainted(false);
        minimize_btn.setContentAreaFilled(false);
        minimize_btn.setOpaque(true);
        minimize_btn.setBorder(null);
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
