package entity;

import java.awt.image.BufferedImage;

public class Entity { // This stores variables that will be used in player, monster and npc classes
    public int x;
    public int y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
