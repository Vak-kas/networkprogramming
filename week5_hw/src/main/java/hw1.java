import java.io.*;

public class hw1{
    static final String dataFile = "data.bin";
    static final int[] aint = { 1, 2 };
    static final double[] afloat = { 1.0, 2.0 };
    static final String[] astring = { "abc한밭", "abc대학교" };

    public static void main(String[] args) throws IOException {
        try ( DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))){
            for (int i = 0; i < aint.length; i++) {
                out.writeInt(aint[i]);
                out.writeDouble(afloat[i]);
                out.writeUTF(astring[i]);
            }
        }
        double total = 0.0;
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
            double bfloat;
            int bint;
            String bstring;
            try {
                while (true) {
                    bint = in.readInt();
                    bfloat = in.readDouble();
                    bstring = in.readUTF();
                    System.out.format("정수 : %d%n실수 : %.2f%n문자열 : %s%n", bint, bfloat, bstring);
                }
            } catch (EOFException e) {
                }

        }

    }

}