package com.mor.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskExercise {


    public static void main(String[] args) {
        MyTimerTask myTimerTask = new MyTimerTask();

        Timer timer = new Timer();
        // Starting from now runs ever 1 sec
        timer.scheduleAtFixedRate(myTimerTask, 0, 1000);
    }

    private static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("Doing stuffffffff....");
        }
    }
}
