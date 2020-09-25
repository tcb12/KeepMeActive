package com.tcb12.application;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Main {
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    public static final Integer ONE_Minute = 60000;

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();

        int last_x = 0;
        Instant last_time = Instant.now();

        while (true) {
            Instant start = Instant.now();



            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();

            Duration timeElapsed = Duration.between(last_time, start);
            if (timeElapsed.toMillis() > ONE_Minute) {
                robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            }

            if (last_x != x) {
                last_time = Instant.now();
            }

            last_x = x;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
