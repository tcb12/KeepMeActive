package com.tcb12.application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class SafeAction implements ActionInterface {
    private final int MAX_Y = 400;
    private final int MAX_X = 400;
    private int randomPercentage;

    public SafeAction() {
        randomPercentage = 50;
    }

    @Override
    public void doRandomAction() {
        // Do a random roll
        Random aRandom = new Random();
        int aRoll = aRandom.nextInt(5);

        switch (aRoll) {
            case 1:
                hitWindowsKey();
            default:
                moveMouseRandomly();
        }
    }

    @Override
    public void setRandomnessPercentage(int aPercent) {
        randomPercentage = aPercent;
    }

    private void hitWindowsKey() {
        try {
            Robot aRobot = new Robot();

            // Windows key press
            aRobot.keyPress(KeyEvent.VK_WINDOWS);
            aRobot.keyRelease(KeyEvent.VK_WINDOWS);

            // Wait one second
            Thread.sleep(1000);

            // Press key again to close start menu
            aRobot.keyPress(KeyEvent.VK_WINDOWS);
            aRobot.keyRelease(KeyEvent.VK_WINDOWS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openNotepad() {
        Runtime rs = Runtime. getRuntime();
        try {
            rs. exec("notepad");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveMouseRandomly() {
        // Create random
        Random aRandom = new Random();

        try {
            // Create robot
            Robot aRobot = new Robot();

            // Move the mouse
            aRobot.mouseMove(aRandom.nextInt(MAX_X), aRandom.nextInt(MAX_Y));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
