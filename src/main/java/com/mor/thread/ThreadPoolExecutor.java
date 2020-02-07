package com.mor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutor {

    public static void main(String[] args) {
        int numTasks = 100;
        List<Task> tasks = new ArrayList<>(numTasks);

        for (short i = 0; i < numTasks; i++) {
            tasks.add(new Task(i, (long) i % 3));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Task task : tasks) {
            executorService.execute(task);
        }
        executorService.shutdown();
    }

    static class Task implements Runnable {


        private final short id;
        private final long timeToExecute;

        public Task(short id, long timeToExecute) {
            this.id = id;
            this.timeToExecute = timeToExecute;
        }

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread + " Executing task:" + id);
            try {
                Thread.sleep(timeToExecute * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread + " Finished executing task:" + id);
        }
    }


}
