package org.howmuch;

/*
 TODO scrap amazon and store images in their respective folders,
 todo design a naming scheme for those images.
 todo run the game from it
 todo transfer to mongodb
*/


import org.apache.commons.lang3.ObjectUtils;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.crypto.Data;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.round;

public class Main extends Thread {

    public static String[] Topics = new String[] {"Technology", "Fashion", "Household", "Miscellaneous"};
    public static String currentTopic = Topics[0];
    static LoginFrame loginFrame;
    static MenuFrame menuFrame;
    static HelpFrame helpFrame;
    static HighscoreFrame highscoreFrame;
    static TopicsFrame topicsFrame;
    static GameFrame gameFrame;
    static GameOverFrame gameOverFrame;
    static DataBaseManager dataBaseManager;
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static Font buttonFont, textFont, password_font, options_font, emoji_font;

    static boolean maximized = false;
    static boolean isGuest = true;
    static boolean grantAccess = false;
    static boolean isLocalDatabaseUpToDate = false;
    static boolean isMongoUpToDate = false;
    static boolean usingMongo = false;
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

            emoji_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/NotoEmoji-VariableFont_wght.ttf")).deriveFont(35f);
            //register the font
            ge.registerFont(password_font);


            options_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/ProductSans-Regular.ttf")).deriveFont(35f);
            //register the font
            ge.registerFont(options_font);


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
        if (Main.maximized) {
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
     * @param status = 1: Call Main Menu <br>
     * status = 2: Call Topic Selection<br>
     * status = 3: Call Help and Credits<br>
     * status = 4: View Highscores<br>
     * status = 5: Update Database<br>
     * status = 6: Start Game<br>
     * status = 7: Game over Screen<br>
     * status = 0: Exit Game<br>
     **/
    public static void changeFrame(int status) {
        if(status == 0){
            DataBaseManager.createLocalDatabaseBackupOfUsers();
            if(DataBaseManager.USER_INDEX < 0){
                System.out.println("Guest, not updating");
            } else {
                DataBaseManager.updateUserScore();
            }

            String lastUpdateDate = "";
            File dateFile = new File(DataBaseManager.LOCAL_BACKUP_DATEFILE);
            if(dateFile.exists()){
                try(BufferedReader br = new BufferedReader(new FileReader(dateFile))) {
                    lastUpdateDate = br.readLine();
                    System.out.println(lastUpdateDate);
                    if(lastUpdateDate.equals(String.valueOf(LocalDate.now()))){
                        System.out.println("Backup DataBases are Up to Date!");
                    }
                    else{
                        DataBaseManager.createLocalDatabaseBackup();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (NullPointerException exception){
                    System.out.println("Nothing in the Date File. ");
                }
            } else {
                DataBaseManager.createLocalDatabaseBackup();
            }
            // Exit game
            System.out.println("Thanks for Playing! ");

            // User Wants to Exit
            System.exit(0);
        }
        if (grantAccess) {
            System.out.println("Access Granted!");
            switch (status) {
                case 1 -> {
                    // Showing Main Menu
                    grantAccess = false;
                    menuFrame = new MenuFrame();
                }
                case 2 -> {
                    // Showing the TopicsFrame
                    grantAccess = false;
                    topicsFrame = new TopicsFrame();
                }
                case 3 -> {
                    // Showing the Help Screen
                    grantAccess = false;
                    helpFrame = new HelpFrame();
                }
                case 4 -> {
                    // Showing Highscores
                    grantAccess = false;
                    highscoreFrame = new HighscoreFrame();
                }
                case 5 -> {
                    System.out.println("Updating Database");

                    DataBaseManager.clearLocalDatabase();
                    AmazonScrapper obj = new AmazonScrapper();
                    try {
                        AmazonScrapper.scrapAndSave();
                    } catch (ParserConfigurationException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SAXException e) {
                        throw new RuntimeException(e);
                    }
                    DataBaseManager.createLocalDatabaseBackup();

                    // delete local database by a class function from the assigndatabase class or sth
                    // Scrap Data so that would be a function in the webscrapper class
                    // within that function make sure to call the local database allocation function from the assigndatabase class or something
                    // establish connection with mongodb with another class
                    // clear mongodb database.
                    // update mongodb database once connections are made.
                }
                case 6 -> {
                    // Showing Game Screen
                    grantAccess = false;
                    gameFrame = new GameFrame();
                }
                case 7 -> {
                    gameFrame.setVisible(false);
                    gameFrame.dispose();
                    // Show GameOverScreen
                    gameOverFrame = new GameOverFrame();
                }
                default -> {
                    DataBaseManager.createLocalDatabaseBackup();

                    // Exit game
                    System.out.println("Thanks for Playing! ");

                    // User Wants to Exit
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Access Denied");
            System.exit(0);
        }
    }

    public void run()
    {
        String lastUpdateDate = "";
        File dateFile = new File(DataBaseManager.LOCAL_DATEFILE);
        if(dateFile.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader(dateFile))) {
                lastUpdateDate = br.readLine();
                System.out.println(lastUpdateDate);
                if(lastUpdateDate.equals(String.valueOf(LocalDate.now()))){
                    System.out.println("DataBases are Up to Date!");
                    isLocalDatabaseUpToDate = true;
                    isMongoUpToDate = true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException exception){
                System.out.println("Nothing in the Date File. ");
            }
        }

        if(!isLocalDatabaseUpToDate){
            System.out.println("Already Started Downloading DataBase bro...");
            DataBaseManager.clearLocalDatabase();
            AmazonScrapper obj = new AmazonScrapper();
            try {
                AmazonScrapper.scrapAndSave();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }

            // writing to the date file
            try(FileWriter f = new FileWriter(dateFile, false)){
                f.write(String.valueOf(LocalDate.now()));
            } catch (IOException e){
                throw new RuntimeException(e);
            }

            System.out.println("Updated the local database, no need to depend on the backup anymore");
            isLocalDatabaseUpToDate = true;
        }
        if(!isMongoUpToDate){
            System.out.println("Updaing Mongodb");
            // update mongodb
//            isMongoUpToDate = true;
//            usingMongo = true;

            // writing to the date file
            try(FileWriter f = new FileWriter(dateFile, false)){
                f.write(String.valueOf(LocalDate.now()));
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        Main t1 = new Main();
        t1.start();
        loginFrame = new LoginFrame();
//        System.out.println(String.format("%,.0f", (double) round((double) 57114/100) * 100));
//        DataBaseManager.createLocalDatabaseBackup();
//        System.out.println(LocalDate.now());
//        gameFrame = new GameFrame();
//
//        DataBaseManager.clearLocalDatabase();
//        AmazonScrapper obj = new AmazonScrapper();
//        try {
//            AmazonScrapper.scrapAndSave();
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        }
    }
}
