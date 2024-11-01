package week6_hw;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {

    private final File inputFile;

    public GZipRunnable(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public void run() {
        try {
            // 압축 파일 경로 생성
            FileOutputStream fos = new FileOutputStream(inputFile.getPath() + ".gz");
            GZIPOutputStream gos = new GZIPOutputStream(fos);
            FileInputStream fis = new FileInputStream(inputFile);

            // 파일 읽고 압축
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                gos.write(buffer, 0, len);
            }

            // 리소스 정리
            fis.close();
            gos.finish();
            gos.close();
//            System.out.println("Compressed: " + inputFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
