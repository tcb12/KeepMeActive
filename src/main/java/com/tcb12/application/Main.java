package com.tcb12.application;

import java.awt.*;

public class Main {
    public static final Integer One_Minute = 60000;

    public static void main(String[] args) throws AWTException {
        System.out.println("Starting...");

        // Enable safe mode temporarily
        Boolean safeMode = true;

        ActionInterface theAction;
        if (safeMode) {
            theAction = new SafeAction();
        } else {
            theAction = new NonSecureAction();
        }

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
                System.out.println("Movement not detected");

                // Do action
                theAction.doRandomAction();
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
