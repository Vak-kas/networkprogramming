package week6_hw;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateFiles {

    private static final int FILE_SIZE = 100; // 각 파일의 크기를 100바이트로 설정
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // 랜덤 문자열에 사용할 문자들
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            try (FileWriter writer = new FileWriter("data" + i + ".txt")) {
                String randomContent = generateRandomString(FILE_SIZE);
                writer.write(randomContent); // 파일에 랜덤한 문자열 작성
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("50 files created successfully.");
    }

    // 랜덤한 문자열을 생성하는 메소드
    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
