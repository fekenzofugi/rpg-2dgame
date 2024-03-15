package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 pixels tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 pixels tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        // improve performance
        // if set to true all the drawing from this component will be done in an
        // offScreen painting buffer.
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);

        // With this, this GamePanel can be "focused" to receive a key input
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//
//        // 1 Second = 1 BILLION Nanoseconds
//        // We draw at the screen every 0.016 seconds
//        double drawInterval = (double) 1000000000 / FPS; // 0.01666666 seconds
//
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//
//        // game loop
//        while (gameThread != null) {
//
//            // Returns the current value of the running Java Virtual Machine's high-resolution
//            // time source, in nanoseconds.
//            long currentTime = System.nanoTime();
//            System.out.println("Current time: " + currentTime);
//
//            // 1: UPDATE: update information such as character positions
//            update();
//            // 2: DRAW: draw the screen with the updated information
//            repaint(); // this calls paintComponent()
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000; // milliseconds
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            // IF the FPS is 60 the program to this(UPDATE & DRAW) 60 times per second.
//        }
//    }
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        // In Java the upper left corner is X:0 Y:0.
        // x increases to the right
        // y increases as they go down
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D has more functionality than Graphics
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(this.playerX, this.playerY, this.tileSize, this.tileSize);

        // Dispose of this graphics context and release any system resources that it is using
        g2.dispose();
    }
}
