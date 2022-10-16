package org.howmuch;

import java.awt.*;

public class Main {

//    static GameFrame gameFrame;
    static LoginFrame loginFrame;
    static MenuFrame menuFrame;
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;

    static boolean maxmized = false;

    public static void changeFrame(int status){
        /*
        status = 1: Call Main Menu
        status = 2: Call GameFrame
        status = 3: Call GameOver
        status = 4: Game Over -> Restart Game
        status = 0: Exit Game
         */

        switch (status) {
            case 1 -> {

                // Disposing Login Window
                loginFrame.setVisible(false);
                loginFrame.dispose();

                // Showing Main Menu
                menuFrame = new MenuFrame();

            }
            case 2 -> {
                // Call GameFrame

                // Closing Option Screen
//                optionFrame.setVisible(false);
//                optionFrame.dispose();
//
//                // Starting Game
//                gameFrame = new GameFrame();
            }
//            case 3 -> {
//                // Call GameOver
//
//                // Closing Option Screen
//                gameFrame.setVisible(false);
//                gameFrame.dispose();
//
//                // Starting Game
//                gameOverFrame = new GameOverFrame();
//            }
//            case 4 -> {
//                // Game Over Starting Again
//
//                // Closing the game over frame
//                gameOverFrame.setVisible(false);
//                gameOverFrame.dispose();
//
//                // Calling Option Frame
//                optionFrame = new OptionFrame();
//            }
//
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
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

        // The Game frame is basically the window
        // and we basically just call the frame. That's almost all we have to do here.
        loginFrame = new LoginFrame();
    }
}
