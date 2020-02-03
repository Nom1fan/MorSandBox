package com.mor.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

public class WriteToSameFile {

    public static void main(String[] args) throws IOException, InterruptedException {
        File threadTestFile = File.createTempFile("threadTest", ".txt");
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(threadTestFile.getAbsolutePath()));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        FileWriterWorker fileWriterWorker1 = new FileWriterWorker(bufferedWriter, "Thread1", 100, countDownLatch);
        FileWriterWorker fileWriterWorker2 = new FileWriterWorker(bufferedWriter, "Thread2", 100, countDownLatch);

        fileWriterWorker1.start();
        fileWriterWorker2.start();

        countDownLatch.countDown();

        fileWriterWorker1.join();
        fileWriterWorker2.join();
        bufferedWriter.close();
    }

    static class FileWriterWorker extends Thread {

        private final BufferedWriter bufferedWriter;
        private final String str;
        private final int iterations;
        private final CountDownLatch countDownLatch;

        public FileWriterWorker(BufferedWriter bufferedWriter, String str, int iterations, CountDownLatch countDownLatch) {
            this.bufferedWriter = bufferedWriter;
            this.str = str;
            this.iterations = iterations;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to await");
            }

            try {
                for (int i = 0; i < iterations; i++) {

                    synchronized (bufferedWriter) {
                        bufferedWriter.append(str);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
