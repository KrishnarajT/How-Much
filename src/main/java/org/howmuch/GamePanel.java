package org.howmuch;

import java.awt.event.*;
import java.awt.*;
import java.util.*;;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable{
//
//    static final int GAME_WIDTH = 1000;
//    static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.555);
//    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
//    static final int BALL_DIAMETER = 20; // in pixels
//    static final int PADDLE_WIDTH = 25;
//    static final int PADDLE_HEIGHT = 100;
//
//    public boolean first_time = true;
//
//
//    // declaring some objects, but not initializing them just yet.
//
//    Thread gameThread; // coz its runnable
//    Image image;
//    Graphics graphics;
//    Random random;
//    Paddle paddle1; // player 1
//    Paddle paddle2; // player 2
//    Ball ball;
//    Score score;
//    boolean running = true;
//
//    GamePanel(){
//        System.out.println(Score.sets);
//        System.out.println(Score.setCounter);
//        newPaddles();
//        newBall();
//
//        // finishing to instantiate the objects
//        score = new Score(GAME_WIDTH, GAME_HEIGHT);
//        this.setFocusable(true);
//        this.addKeyListener(new AL());
//        this.setBackground(new Color(35, 145, 239));
//        this.setPreferredSize(SCREEN_SIZE);
////        this.startThread();
//
//    }
//
//
//    public void newBall() {
//        random = new Random();
//        ball = new Ball(GAME_WIDTH/2 - BALL_DIAMETER/2, random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
//
//    }
//
//    public void newPaddles() {
//        paddle1 = new Paddle(0, (GAME_HEIGHT/2 - PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
//        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2 - PADDLE_HEIGHT/2),
//                PADDLE_WIDTH, PADDLE_HEIGHT, 2);
//
//    }
//
//    public void paint(Graphics g) {
//        image = createImage(getWidth(), getHeight());
//        graphics = image.getGraphics();
//        draw(graphics);
//        g.drawImage(image, 0, 0, this);
//    }
//
//    public void draw(Graphics g) {
//        paddle1.draw(g);
//        paddle2.draw(g);
//        ball.draw(g);
//        score.draw(g);
//    }
//
//    public void move() {
//        paddle1.move();
//        paddle2.move();
//        ball.move();
//    }
//
//    public void checkCollision() {
//
//        // Bounces the ball off the top and bottom of the window edges
//
//        if (ball.y <= 0){
//            ball.setYDirection(-ball.yVelocity);
//        }
//        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER){
//            ball.setYDirection(-ball.yVelocity);
//        }
//
//
//        // bounces ball off the paddles
//
//        if (ball.intersects(paddle1)){
//            ball.xVelocity = -(ball.xVelocity - 1); // this just reverses the x dir
//
//            // now you can also increase the y speed, thereby now causing a change in the 2 vectors, and allowing
//            // some more asymmetry
//            if (ball.yVelocity >= 0 && ball.yVelocity <= 6) {
//                // ball is moving up and left
//                ball.yVelocity += 2;
//            }
//            if (ball.yVelocity <= 0 && ball.yVelocity >= -6) {
//                ball.yVelocity -= 2;
//
//            }
//        }
//
//        if (ball.intersects(paddle2)){
//            ball.xVelocity = - (ball.xVelocity + 1);
//
//            if (ball.yVelocity >= 0 && ball.yVelocity <= 10) {
//                // ball is moving up and right
//                ball.yVelocity += 2;
//            }
//            if (ball.yVelocity <= 0 && ball.yVelocity >= -10) {
//                ball.yVelocity -= 2;
//            }
//
//        }
//
//        // stops paddles at window edges
//        if (paddle1.y <= 0){
//            paddle1.y = 0;
//        }
//        if (paddle1.y >= GAME_HEIGHT-PADDLE_HEIGHT){
//            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
//        }
//
//        if (paddle2.y <= 0){
//            paddle2.y = 0;
//        }
//        if (paddle2.y >= GAME_HEIGHT-PADDLE_HEIGHT){
//            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
//        }
//
//        // Give the player 1 point, and create a new ball
//        if (ball.x <= 0){
//            score.player2Score++;
//            if(score.player2Score >= 11)
//            {
//                Score.setCounter++;
//                Score.player2SetsWon++;
//                score.player2Score = 0;
//                score.player1Score = 0;
//            }
//            if(Score.setCounter == Score.sets)
//            {
//                running = false;
//                Main.changeFrame(3);
//            }
//            newPaddles();
//            newBall();
//            System.out.println("Player 2 Score: " + score.player2Score);
//        }
//
//        else if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
//            score.player1Score++;
//            if(score.player1Score >= 11)
//            {
//                Score.setCounter++;
//                Score.player1SetsWon++;
//                score.player2Score = 0;
//                score.player1Score = 0;
//            }
//
//            if(Score.setCounter == Score.sets)
//            {
//                running = false;
//                Main.changeFrame(3);
//            }
//            newPaddles();
//            newBall();
//            System.out.println("Player 1 Score: " + score.player1Score);
//        }
//    }

    public void run() {
        // Game Loop

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.00;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        boolean running = true;
        while(running){
            // basic game loop logic to ensure 60 fps, dont think too much about it, it makes sense.
            // you can reuse it for consistancy, or make a new one.

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1){
//                move();
//                checkCollision();
                repaint();
                delta--;
            }

        }

    }
//
//    public void startThread(){
//
//        // Creating the Game Thread
//        gameThread = new Thread(this);
//        gameThread.start();
//
//    }
//
//    public static void setSets(int sets) {
//        Score.sets = sets;
//    }
//    // short for the action listener or listening to keys in this case coz it extends keyadapter
//    public class AL extends KeyAdapter {
//
//        public void keyPressed(KeyEvent e) {
//            if (e.getKeyCode() == KeyEvent.VK_SPACE && first_time) {
//                System.out.println("Starting Game upon Pressing Space");
//                first_time = false;
//                startThread();
//            }
//            paddle1.keyPressed(e);
//            paddle2.keyPressed(e);
//        }
//
//        public void keyReleased(KeyEvent e) {
//            paddle1.keyReleased(e);
//            paddle2.keyReleased(e);
//        }
//    }
}
