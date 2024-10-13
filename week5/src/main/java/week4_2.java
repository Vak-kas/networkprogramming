import java.io.*;

public class week4_2 {

    public static void main(String[] args) {

        try (OutputStream outFile = new FileOutputStream("dataWriter_ms949.txt");

//             OutputStreamWriter outWriter = new OutputStreamWriter(outFile, "utf-8") ) {
            OutputStreamWriter outWriter = new OutputStreamWriter(outFile, "ms949") ) {

            outWriter.write("한밭test");

        } catch (IOException ex) {

            System.err.println(ex.getMessage());

        }


//        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out,"UTF-8))) {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out,"ms949"))) {

            out.write("한밭test");

        } catch (IOException ex) {

            System.err.println(ex.getMessage());

        }

    }

}

