package week6;

public class ReturnDigestUserInterfacePolling {

    public static void main(String[] args) {

        String[] files = { "data.bin", "data.txt" };

        ReturnDigest[] digests = new ReturnDigest[files.length];

        for (int i = 0; i < files.length; i++) { // Calculate the digest

            digests[i] = new ReturnDigest(files[i]);

            digests[i].start();

        }

        for (int i = 0; i < files.length; i++) {

            while (true) {

// Now print the result

                byte[] digest = digests[i].getDigest();
//                System.out.print("");

                if (digest != null) {

                    StringBuilder result = new StringBuilder(files[i]);

                    result.append(": ");

// result.append(DatatypeConverter.printHexBinary(digest));

                    result.append(byteToHex(digest));

                    System.out.println(result);

                    break;

                }

            }

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