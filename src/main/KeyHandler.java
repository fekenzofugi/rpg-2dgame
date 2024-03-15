package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; // The listener interface for receiving keyboard events(keystrokes)

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {

        // Returns the integer keyCode associated with the key in this event
        int code = e.getKeyCode();

        // if the user press W key
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        // if the user press S key
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        // if the user press A key
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        // if the user press D key
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // if the user press W key
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        // if the user press S key
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        // if the user press A key
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        // if the user press D key
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
