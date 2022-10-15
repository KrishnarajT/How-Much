package org.howmuch;

public class Main {

//    static GameFrame gameFrame;
    static LoginFrame loginFrame;
    static Boolean DarkMode = false;

    public static void changeFrame(int status){
        /*
        status = 1: Call Options
        status = 2: Call GameFrame
        status = 3: Call GameOver
        status = 4: Game Over -> Restart Game
        status = 0: Exit Game
         */

        switch (status) {
            case 1 -> {
                // Call options
//
//                menuFrame.setVisible(false);
//                menuFrame.dispose();
//
//                // Showing Options
//                optionFrame = new OptionFrame();
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
