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
        setX(100);
        setY(100);
        setSpeed(4);
        setDirection("down");
    }

    public void getPlayerImage() {
        try {

            setUp1(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_up_1.png")));
            setUp2(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_up_2.png")));
            setDown1(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_down_1.png")));
            setDown2(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_down_2.png")));
            setLeft1(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_left_1.png")));
            setLeft2(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_left_2.png")));
            setRight1(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_right_1.png")));
            setRight2(ImageIO.read(this.getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { // this method gets called 60 times per second.

        if (keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
            // In Java the upper left corner is X:0 Y:0.
            // x increases to the right
            // y increases as they go down
            if (keyHandler.isUpPressed()) {
                setDirection("up");
                setY(getY() - getSpeed());
            } else if (keyHandler.isDownPressed()) {
                setDirection("down");
                setY(getY() + getSpeed());

            } else if (keyHandler.isLeftPressed()) {
                setDirection("left");
                setX(getX() - getSpeed());
            } else if (keyHandler.isRightPressed()) {
                setDirection("right");
                setX(getX() + getSpeed());
            }

            setSpriteCounter(getSpriteCounter() + 1); // player image changes in every 12 frames
            if (getSpriteCounter() > 12) {
                if (getSpriteNumber() == 1) {
                    setSpriteNumber(2);
                } else if (getSpriteNumber() == 2) {
                    setSpriteNumber(1);
                }
                setSpriteCounter(0);
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (getDirection()) {
            case "up":
                if (getSpriteNumber() == 1) {
                    image = getUp1();
                }
                if (getSpriteNumber() == 2) {
                    image = getUp2();
                }
                break;
            case "down":
                if (getSpriteNumber() == 1) {
                    image = getDown1();
                }
                if (getSpriteNumber() == 2) {
                    image = getDown2();
                }
                break;
            case "left":
                if (getSpriteNumber() == 1) {
                    image = getLeft1();
                }
                if (getSpriteNumber() == 2) {
                    image = getLeft2();
                }
                break;
            case "right":
                if (getSpriteNumber() == 1) {
                    image = getRight1();
                }
                if (getSpriteNumber() == 2) {
                    image = getRight2();
                }
                break;
        }
        g2.drawImage(image, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
    }
}
