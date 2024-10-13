package week6;

public class InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }
    public void calculateDigest() {

        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);

        Thread t = new Thread(cb);

        t.start();

    }

    void receiveDigest(byte[] digest) {

        this.digest = digest;

        System.out.println(this);

    }

    @Override

    public String toString() {

        String result = filename + ": ";

        if (digest != null) {

//result += DatatypeConverter.printHexBinary(digest);

            result += byteToHex(digest);

        } else {

            result += "digest not available";

        }

        return result;

    }

    public static void main(String[] args) {

        String[] temp = {"data.bin", "data.txt"};

        for (String filename : temp) {

// Calculate the digest

            InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(filename);

            d.calculateDigest();

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