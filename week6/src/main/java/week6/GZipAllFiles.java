package week6;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GZipAllFiles {

    public final static int THREAD_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        String[] temp = {"data.bin", "data.txt"};
        for (String filename : temp) {
            File f = new File(filename);
            if (f.exists()) {
                if (f.isDirectory()) {
                    File[] files = f.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        if (!files[i].isDirectory()) { // don't recurse directories
                            Runnable task = new GZipRunnable(files[i]);
                            pool.submit(task);
                        }
                    }
                } else {
                    Runnable task = new GZipRunnable(f);
                    pool.submit(task);
                }
            }
        }
        pool.shutdown();
        pool.awaitTermination(100, TimeUnit.SECONDS);

        long elapstedTime = System.nanoTime()- startTime;
        System.out.println("elapsed time="+elapstedTime);

    }

}
