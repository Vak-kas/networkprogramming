package week6;

public class ReturnDigestUserInterface {
    public static void main(String[] args) {
        String[] filenames = {"data.bin", "data.txt"};

        for (String filename : filenames) {
            ReturnDigest digestThread = new ReturnDigest(filename);
            digestThread.start();

            // 스레드가 종료될 때까지 대기
            try {
                digestThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            StringBuilder result = new StringBuilder(filename);
            result.append(": ");

            byte[] digest = digestThread.getDigest();
            if (digest != null) {
                result.append(byteToHex(digest));
            } else {
                result.append("Digest not calculated yet.");
            }
            System.out.println(result);
        }
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
