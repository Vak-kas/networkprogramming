import java.io.*;

public class hw3 {

    public static void main(String[] args) {

        try (OutputStream outFile = new FileOutputStream("data_ascii.txt");
             OutputStreamWriter outWriter = new OutputStreamWriter(outFile, "ascii") ) {
            outWriter.write("한밭대학교test1234");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out,"ascii"))) {
            out.write("한밭대학교test1234");

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

