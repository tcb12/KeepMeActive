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
        // Roll dice
        int aDiceRoll = rollDice(1);

        switch (aDiceRoll) {
            case 0:
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

    private int rollDice(int maxDiceValue) {
        // Create random
        Random aRandom = new Random();

        // Create return
        int aReturn = 0;

        // Generate random chance
        int aRoll = aRandom.nextInt(100);

        // Chance won
        if (aRoll < randomPercentage) {
            // Roll again, but a value that will land in the dice amount
            aReturn = aRandom.nextInt(maxDiceValue);
            System.out.println("Rolled: " + aReturn);
        }

        return aReturn;
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
