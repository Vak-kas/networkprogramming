package week6;
import java.io.*;
import java.security.*;

public class ReturnDigest extends Thread {
    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ; // 파일을 끝까지 읽기
            din.close();
            digest = sha.digest();
        } catch (IOException ex) {
            System.err.println("IOException: " + ex);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("NoSuchAlgorithmException: " + ex);
        }
    }

    public byte[] getDigest() {
        return digest;
    }
}
