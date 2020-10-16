package com.tcb12.application;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class NonSecureAction implements ActionInterface {
    public final int MAX_Y = 400;
    public final int MAX_X = 400;
    private int randomPercentage;
    private Robot theRobot;

    public NonSecureAction() throws AWTException {
        theRobot = new Robot();
        randomPercentage = 50;
    }

    @Override
    public void doRandomAction() {
        // Roll dice
        int aDiceRoll = rollDice(2);

        switch (aDiceRoll) {
            case 0:
                doRandomRightMouseClick();
            case 1:
                hitStartButton();
            default:
                moveMouseRandomly();
        }
    }

    @Override
    public void setRandomnessPercentage(int aPercent) {
        randomPercentage = aPercent;
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
        }

        return aReturn;
    }

    private void moveMouseRandomly() {
        Random aRandom = new Random();

        // Move the mouse
        theRobot.mouseMove(aRandom.nextInt(MAX_X), aRandom.nextInt(MAX_Y));
    }

    private void doRandomRightMouseClick() {
        // Right click
        theRobot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        theRobot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

        // Wait one second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exit right click by clicking somewhere not dangerous
        theRobot.mouseMove(0, 0);
        theRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        theRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void hitStartButton() {
        // Move mouse to start button
        theRobot.mouseMove(0, 9999);

        // Right click
        theRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        theRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // Wait briefly
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Move mouse back to start just in case the mouse moved
        theRobot.mouseMove(0, 9999);

        // Exit start menu
        theRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        theRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
