package org.howmuch;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

import static org.howmuch.Main.*;


// The topics can be
// Technology
// Fashion
// Health and Household
// Miscellaneous


public class GameFrame extends JFrame {

    BackgroundPanel backgroundPanel;
    GamePanel productImagePanel;
    JButton option_1_btn, option_2_btn, option_3_btn, option_4_btn;
    JPanel options_panel;
    JLabel time_lbl;
    JTextArea productName_txtArea;
    public static int randomIndex = 0;
    String[] currentData;

    GameFrame() {
        backgroundPanel = new BackgroundPanel();

        this.setTitle("How Much?");
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
        createButtons();
        createPanels();
        createLabels();


        // Main stuff
        findRandomIndex();
        assignCurrentData();

        // Important Updates
        reassignColors();
        reassignBounds();


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reassignBounds();
                repaint();
            }
        });

        this.add(productName_txtArea);
        this.add(productImagePanel);
        this.add(time_lbl);
        this.add(options_panel);
        this.add(basicButtons_pnl);
        this.add(backgroundPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void assignCurrentData() {
        currentData = new String[]{"", "", ""};
        if (!usingMongo) {
            currentData = DataBaseManager.readFromLocalDatabase(currentTopic, randomIndex);
        } else {
            currentData = DataBaseManager.fetchDataFromMongo(currentTopic, randomIndex);
        }
    }

    private void findRandomIndex() {
        int max = DataBaseManager.findLength(currentTopic);
        Random random = new Random();
        random.setSeed(random.nextLong(1000030045));
        // Generates random integers 0 to 49
        randomIndex = random.nextInt(max);
    }

    private void loadGameDataOnScreen() {
        System.out.println(Arrays.toString(currentData));
        Image productImage = new ImageIcon(currentData[2]).getImage();
        int maxWidth = (int) (0.4 * this.getWidth());
        int maxHeight = (int) (0.4 * this.getWidth());

        int[] imageSize = calculateImageSize(maxWidth, maxHeight, productImage.getWidth(productImagePanel), productImage.getHeight(productImagePanel));
        System.out.println(Arrays.toString(imageSize));
        productImagePanel.setBounds((int) (0.07 * this.getWidth()) + maxWidth / 2 - imageSize[0] / 2, (int) (0.07 * this.getHeight()) + maxHeight/ 2 - imageSize[1] / 2, imageSize[0], imageSize[1]);
        productImagePanel.setBackground(currentData[2]);

        productName_txtArea.setBounds((int) (0.07 * this.getWidth()), (int) (0.8 * this.getHeight()), (int) (0.5 * this.getWidth()), (int) (0.2 * this.getHeight()));
        productName_txtArea.setText(currentData[0]);

        // setting price
        option_1_btn.setText(currentData[1]);
    }

    private int[] calculateImageSize(int maxWidth, int maxHeight, double width, double height) {
        int wt = 600, ht = 550;
        double aspectRatio = width / height;
        System.out.println(aspectRatio);
        if (aspectRatio > 1) {
            // landscape
            wt = maxWidth;
            ht = (int) (wt / aspectRatio);
        } else {
            // portrait image
            ht = maxHeight;
            wt = (int) (ht * aspectRatio);
        }

        return new int[]{wt, ht};
    }

    private void createLabels() {
        time_lbl = new JLabel();
        time_lbl.setAlignmentY(Box.CENTER_ALIGNMENT);
        time_lbl.setAlignmentX(Box.CENTER_ALIGNMENT);
        time_lbl.setOpaque(true);
        time_lbl.setBorder(null);
        time_lbl.setText("9");

        productName_txtArea = new JTextArea();
        productName_txtArea.setAlignmentY(Box.CENTER_ALIGNMENT);
        productName_txtArea.setAlignmentX(Box.LEFT_ALIGNMENT);
        productName_txtArea.setOpaque(true);
        productName_txtArea.setBorder(null);
        productName_txtArea.setLineWrap(true);
    }

    private void reassignBounds() {
        Dimension screenSize = this.getSize();

        // The Entire basic button panel for closing minimizing and stuff
        basicButtons_pnl.setBounds(this.getWidth() - (exit_btn.getWidth() * 3) - 40, 10, exit_btn.getWidth() * 3 + 35, exit_btn.getHeight());

        // Options panel
        options_panel.setBounds((int) (0.70 * screenSize.getWidth()), (int) (0.58 * screenSize.getHeight()), (int) (0.60 * screenSize.getWidth()), 700);

        // Buttons in the Options Panel
        option_1_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 80));
        option_1_btn.setFont(options_font.deriveFont((float) (0.05 * getHeight())));

        option_2_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 80));
        option_2_btn.setFont(options_font.deriveFont((float) (0.05 * getHeight())));

        option_3_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 70));
        option_3_btn.setFont(options_font.deriveFont((float) (0.05 * getHeight())));

        option_4_btn.setBounds(new Rectangle((int) (0.45 * screenSize.getWidth()), 70));
        option_4_btn.setFont(options_font.deriveFont((float) (0.05 * getHeight())));

        productName_txtArea.setFont(password_font.deriveFont((float) (0.05 * getHeight())));

        // The Score label
        time_lbl.setBounds((int) (0.890 * screenSize.getWidth()), (int) (0.14 * screenSize.getHeight()), (int) (0.05 * screenSize.getWidth()), (int) (0.11 * screenSize.getHeight()));
        time_lbl.setFont(buttonFont.deriveFont((float) (0.09 * getHeight())));

        loadGameDataOnScreen();
    }

    private void reassignColors() {

        if (Colors.DarkMode) {
            backgroundPanel.setBackground("src/main/resources/images/gamescreen.png");
        } else {
            backgroundPanel.setBackground("src/main/resources/images/gamescreen.png");
        }
        Colors.reassignColors();
        basicButtons_pnl.setBackground(Colors.primaryColor);
        exit_btn.setBackground(Colors.primaryColor);
        resize_btn.setBackground(Colors.primaryColor);
        minimize_btn.setBackground(Colors.primaryColor);
        option_1_btn.setBackground(Colors.primaryColor);
        option_1_btn.setForeground(Colors.bgColor);
        option_2_btn.setBackground(Colors.primaryColor);
        option_2_btn.setForeground(Colors.bgColor);
        option_4_btn.setBackground(Colors.primaryColor);
        option_4_btn.setForeground(Colors.bgColor);
        option_3_btn.setBackground(Colors.primaryColor);
        option_3_btn.setForeground(Colors.bgColor);
        productName_txtArea.setBackground(Color.WHITE);
        productName_txtArea.setForeground(Colors.primaryColor);
        if (Colors.DarkMode) {
            time_lbl.setBackground(Colors.bgColor);
        } else {
            time_lbl.setBackground(Colors.secondaryColor);
        }
        time_lbl.setForeground(Colors.primaryColor);
    }

    private void createPanels() {
        options_panel = new JPanel();
        BoxLayout bl = new BoxLayout(options_panel, BoxLayout.Y_AXIS);
        options_panel.setLayout(bl);
        options_panel.add(option_1_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(option_2_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(option_3_btn);
        options_panel.add(Box.createRigidArea(new Dimension(0, 25)));
        options_panel.add(option_4_btn);
        options_panel.setBackground(new Color(0, 0, 0, 0));

        productImagePanel = new GamePanel();
//        productImagePanel.setBackground(new Color(44, 44, 244));
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


        option_1_btn = new JButton();
        option_1_btn.setText("");
        option_1_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        option_1_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        option_1_btn.setFocusPainted(false);
        option_1_btn.setBounds(0, 0, 500, 500);
        option_1_btn.setContentAreaFilled(false);
        option_1_btn.setOpaque(true);
        option_1_btn.setBorder(null);
        option_1_btn.addChangeListener(evt -> {
            if (option_1_btn.getModel().isPressed()) {
                option_1_btn.setForeground(Colors.accentColor);
            } else if (option_1_btn.getModel().isRollover()) {
                option_1_btn.setForeground(Colors.accentColor);
            } else {
                option_1_btn.setForeground(Colors.bgColor);
            }
        });

        option_1_btn.addActionListener(e -> {
            // check if this is the right option from getting text or from a preassigned variable
            // if it is correct then go to changing menu and call this class again,
            // if its wrong then changeframe to lost.
            // add an option to go back on this screen.
            // turn off the timer clock thing if you did something like that.

            this.setVisible(false);
            this.dispose();
            Main.changeFrame(6);
        });

        option_2_btn = new JButton();
        option_2_btn.setText("123600");
        option_2_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        option_2_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        option_2_btn.setFocusPainted(false);
        option_2_btn.setContentAreaFilled(false);
        option_2_btn.setOpaque(true);
        option_2_btn.setBorder(null);
        option_2_btn.addChangeListener(evt -> {
            if (option_2_btn.getModel().isPressed()) {
                option_2_btn.setForeground(Colors.accentColor);
            } else if (option_2_btn.getModel().isRollover()) {
                option_2_btn.setForeground(Colors.accentColor);
            } else {
                option_2_btn.setForeground(Colors.bgColor);
            }
        });
        option_2_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(6);
        });

        option_3_btn = new JButton();
        option_3_btn.setText("37000");
        option_3_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        option_3_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        option_3_btn.setFocusPainted(false);
        option_3_btn.setContentAreaFilled(false);
        option_3_btn.setOpaque(true);
        option_3_btn.setBorder(null);
        option_3_btn.addChangeListener(evt -> {
            if (option_3_btn.getModel().isPressed()) {
                option_3_btn.setForeground(Colors.accentColor);
            } else if (option_3_btn.getModel().isRollover()) {
                option_3_btn.setForeground(Colors.accentColor);
            } else {
                option_3_btn.setForeground(Colors.bgColor);
            }
        });
        option_3_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(6);
        });

        option_4_btn = new JButton();
        option_4_btn.setText("₹80350");
        option_4_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        option_4_btn.setAlignmentX(Box.LEFT_ALIGNMENT);
        option_4_btn.setFont(buttonFont.deriveFont(44f));
        option_4_btn.setFocusPainted(false);
        option_4_btn.setContentAreaFilled(false);
        option_4_btn.setOpaque(true);
        option_4_btn.setBorder(null);
        option_4_btn.addChangeListener(evt -> {
            if (option_4_btn.getModel().isPressed()) {
                option_4_btn.setForeground(Colors.accentColor);
            } else if (option_4_btn.getModel().isRollover()) {
                option_4_btn.setForeground(Colors.accentColor);
            } else {
                option_4_btn.setForeground(Colors.bgColor);
            }
        });
        option_4_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(7);
        });
    }

}
