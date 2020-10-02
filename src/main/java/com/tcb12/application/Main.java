package com.tcb12.application;

import java.awt.*;
import java.util.Random;

public class Main {
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    public static final Integer One_Minute = 60000;

    public static void main(String[] args) throws AWTException {
        System.out.println("Starting...");

        Robot robot = new Robot();
        Random random = new Random();

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
                robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
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
}
