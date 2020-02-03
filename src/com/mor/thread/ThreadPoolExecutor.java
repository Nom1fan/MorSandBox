package com.mor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutor {

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        for (short i = 0; i < 5; i++) {
            tasks.add(new Task(i, (long) i));
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        threadPoolExecutor.executeTasks(tasks);
    }

    public void executeTasks(List<Task> taskList) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Task task : taskList) {
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
            System.out.println("[" + Thread.currentThread() + "] Executing task:" + id);
            try {
                Thread.sleep(timeToExecute * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + Thread.currentThread() + "] Finished executing task:" + timeToExecute);
        }
    }


}
