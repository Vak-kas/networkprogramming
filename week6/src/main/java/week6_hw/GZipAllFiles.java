package week6_hw;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GZipAllFiles {

    public static void main(String[] args) throws InterruptedException {
        // 스레드 개수와 파일을 압축하는 시간을 측정하는 코드
        int[] threadCounts = {1, 2, 3, 4,5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        String[] temp = new String[50];

        // data1.txt ~ data50.txt 파일 배열 생성
        for (int i = 1; i <= 50; i++) {
            temp[i - 1] = "data" + i + ".txt";
        }

        for (int threadCount : threadCounts) {
//            System.out.println("Testing with " + threadCount + " threads");
            long startTime = System.nanoTime();  // 시작 시간 측정
            ExecutorService pool = Executors.newFixedThreadPool(threadCount);  // 스레드 풀 생성

            // 파일 압축 시작
            for (String filename : temp) {
                File f = new File(filename);
                if (f.exists()) {
                    Runnable task = new GZipRunnable(f);  // 압축 작업 생성
                    pool.submit(task);  // 스레드 풀에 작업 제출
                }
            }

            pool.shutdown();  // 더 이상의 작업 제출 중단
            pool.awaitTermination(100, TimeUnit.SECONDS);  // 모든 작업 완료까지 대기

            long elapsedTime = System.nanoTime() - startTime;  // 총 소요 시간 측정
            System.out.println("Thread count: " + threadCount + ", Elapsed time: " + (elapsedTime) );
        }
    }
}
