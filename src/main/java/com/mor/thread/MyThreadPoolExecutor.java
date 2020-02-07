package com.mor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyThreadPoolExecutor {

    public static void main(String[] args) {
        int numTasks = 100;
        List<Task> tasks = new ArrayList<>(numTasks);

        for (short i = 0; i < numTasks; i++) {
            tasks.add(new Task(i, (long) i % 3));
        }

        MyThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor();
        threadPoolExecutor.executeTasks(tasks);
    }

    public void executeTasks(List<Task> taskList) {
        TaskWorker[] taskWorkers = new TaskWorker[5];
        BlockingQueue<Task> blockingQueue = new LinkedBlockingDeque<>();

        for (int i = 0; i < 5; i++) {
            taskWorkers[i] = new TaskWorker(blockingQueue);
            taskWorkers[i].start();
        }

        blockingQueue.addAll(taskList);
    }

    static class TaskWorker extends Thread {

        private final BlockingQueue<Task> tasks;

        public TaskWorker(BlockingQueue<Task> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {

            Task task;
            Thread currentThread = Thread.currentThread();

            while (!tasks.isEmpty()) {
                try {
                    System.out.println(currentThread + " Fetching next task...");
                    task = tasks.take();
                    System.out.println(currentThread + " Executing:" + task);
                    task.doTask();
                    System.out.println(currentThread + " Finished executing:" + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Task {

        private final short id;
        private final long timeToExecute;

        public Task(short id, long timeToExecute) {
            this.id = id;
            this.timeToExecute = timeToExecute;
        }

        public void doTask() {
            try {
                Thread.sleep(timeToExecute * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    '}';
        }
    }


}
