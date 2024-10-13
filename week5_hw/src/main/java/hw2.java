import java.io.*;

public class hw2 {
    static final String dataFile = "data.bin";

    public static void main(String[] args) throws IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
            double bfloat;
            int bint;
            String bstring;
            try {
                while (true) {
                    bfloat = in.readDouble();
                    bint = in.readInt();
                    bstring = in.readUTF();
                    System.out.format("실수 : %.2f%n정수 : %d%n문자열 : %s%n", bfloat, bint, bstring);
                }
            } catch (EOFException e) {
                }

        }

    }

}