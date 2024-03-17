package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(this.getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { // this method gets called 60 times per second.

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            // In Java the upper left corner is X:0 Y:0.
            // x increases to the right
            // y increases as they go down
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++; // player image changes in every 12 frames
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
