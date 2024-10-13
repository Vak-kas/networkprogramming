import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class hw6 extends Thread {
    private String filename;
    public hw6(String filename) {
        this.filename = filename;
    }

    public static void calc(String filename) {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while (din.read() != -1);  // 파일의 모든 바이트를 읽어 SHA-256을 업데이트
            din.close();

            byte[] digest = sha.digest();
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            result.append(byteToHex(digest));
            System.out.println(result);

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        String[] temp = {"data1.txt","data2.txt","data3.txt","data4.txt","data5.txt",
                "data6.txt","data7.txt","data8.txt","data9.txt","data10.txt"};
        long startTotalTime = System.nanoTime();
        for (String filename : temp) {
            long startTime = System.nanoTime();
            calc(filename);
            long singleThreadTime = System.nanoTime() - startTime;
            System.out.println(filename + " : " +singleThreadTime);
        }

        // 전체 실행 시간 측정
        long totalTime = System.nanoTime() - startTotalTime;
        System.out.println("total : " + totalTime);


    }

    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String st = String.format("%02X", b);
            sb.append(st);
        }
        return sb.toString();
    }
}

