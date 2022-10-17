package org.howmuch;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    //    static GameFrame gameFrame;
    static LoginFrame loginFrame;
    static MenuFrame menuFrame;
    static HelpFrame helpFrame;
    static HighscoreFrame highscoreFrame;
    static TopicsFrame topicsFrame;
    static GameFrame gameFrame;
    static GameOverFrame gameOverFrame;
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static Font buttonFont, textFont, password_font;

    static boolean maxmized = false;


    static JButton exit_btn;
    static JButton resize_btn;
    static JButton minimize_btn;
    static JPanel basicButtons_pnl;

    static ImageIcon exit = new ImageIcon("src/main/resources/icons/circle_delete.png");
    static Image exit_image = exit.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    static ImageIcon minimize = new ImageIcon("src/main/resources/icons/circle_minus.png");
    static Image minimize_image = minimize.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    static ImageIcon resizeUp = new ImageIcon("src/main/resources/icons/resize_3.png");
    static Image resizeUp_image = resizeUp.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    static ImageIcon resizeDown = new ImageIcon("src/main/resources/icons/resize_4.png");
    static Image resizeDown_image = resizeDown.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

    public static void createFonts() {
        try {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/BelgradoItalic-OVArd.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(buttonFont);

//
            textFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/MomcakeBold-WyonA.otf")).deriveFont(50f);
            //register the font
            ge.registerFont(textFont);
//
            password_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/CaeciliaLTPro45Light.TTF")).deriveFont(35f);
            //register the font
            ge.registerFont(password_font);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBasicButtonPanel() {
        basicButtons_pnl = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 10, 0);
        basicButtons_pnl.setLayout(fl);

        exit_btn = new JButton();
        exit_btn.setIcon(new ImageIcon(exit_image));
        exit_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        exit_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        exit_btn.setBounds(new Rectangle(25, 25));
        exit_btn.setFont(buttonFont.deriveFont(44f));
        exit_btn.setFocusPainted(false);
        exit_btn.setContentAreaFilled(false);
        exit_btn.setOpaque(true);
        exit_btn.setBorder(null);

        resize_btn = new JButton();
        if (Main.maxmized) {
            resize_btn.setIcon(new ImageIcon(resizeDown_image));
        } else {
            resize_btn.setIcon(new ImageIcon(resizeUp_image));
        }
        resize_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        resize_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        resize_btn.setBounds(new Rectangle(25, 25));
        resize_btn.setFont(buttonFont.deriveFont(44f));
        resize_btn.setFocusPainted(false);
        resize_btn.setContentAreaFilled(false);
        resize_btn.setOpaque(true);
        resize_btn.setBorder(null);


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

        basicButtons_pnl.add(minimize_btn);
        basicButtons_pnl.add(resize_btn);
        basicButtons_pnl.add(exit_btn);
    }

    /**
     * status = 1: Call Main Menu <br>
     * status = 2: Call Topic Selection<br>
     * status = 3: Call Help and Credits<br>
     * status = 4: View Highscores<br>
     * status = 5: Update Database<br>
     * status = 6: Start Game<br>
     * status = 7: Game over Screen<br>
     * status = 0: Exit Game<br>
     **/
    public static void changeFrame(int status) {


        switch (status) {
            case 1 -> {
                // Showing Main Menu
                menuFrame = new MenuFrame();
            }
            case 2 -> {
                // Showing the TopicsFrame
                topicsFrame = new TopicsFrame();
            }
            case 3 -> {
                // Showing the Help Screen
                helpFrame = new HelpFrame();
            }
            case 4 -> {
                // Showing Highscores
                highscoreFrame = new HighscoreFrame();
            }
            case 5 -> {
                System.out.println("Updating Database");
            }
            case 6 -> {
                // Showing Game Screen
                gameFrame = new GameFrame();
            }
            case 7 -> {
                // Show GameOverScreen
                gameOverFrame = new GameOverFrame();
            }
            default -> {
                // Exit game
                System.out.println("Thanks for Playing! ");

                // User Wants to Exit
                System.exit(0);
            }
        }

    }

    public static void main(String[] args) {

        // So fonts render properly
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        // The Game frame is basically the window
        // and we basically just call the frame. That's almost all we have to do here.
        loginFrame = new LoginFrame();
    }
}
