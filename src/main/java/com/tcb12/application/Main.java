package com.tcb12.application;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class Main {
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    public static final Integer One_Minute = 5000;
    private static Robot theRobot;

    public static void main(String[] args) throws AWTException {
        System.out.println("Starting...");

        theRobot = new Robot();
        Random aRandom = new Random();

        // Declare variables
        int last_x = 0;
        int last_y = 0;

        // Stay alive
        while (true) {
            // Get mouse location
            PointerInfo aPointInfo = MouseInfo.getPointerInfo();
            Point aPoint = aPointInfo.getLocation();
            int x = (int) aPoint.getX();
            int y = (int) aPoint.getY();

            // Check if the mouse has not moved
            if (x == last_x && y == last_y) {
                System.out.println("Movement not detected, moving mouse");

                // Move the mouse
                theRobot.mouseMove(aRandom.nextInt(MAX_X), aRandom.nextInt(MAX_Y));
                hitStartButton();
            }

            // Set last mouse position
            last_x = x;
            last_y = y;

            // Wait one minute
            try {
                Thread.sleep(One_Minute);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doRandomAction() {
        // Do a random roll
        Random aRandom = new Random();
        int aRoll = aRandom.nextInt(2);

        switch (aRoll) {
            case 1:
                doRandomRightMouseClick();
            case 2:
                hitStartButton();
            default:
                System.out.println("Bad roll, skipping...");
        }
    }

    private static void doRandomRightMouseClick() {
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

    private static void hitStartButton() {
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
