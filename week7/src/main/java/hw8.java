import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class hw8 extends Thread{
    private String filename; //파일 이름
    private BufferedWriter writer; //bufferedwriter
    public static final String BLACKHOLE = "zen.spamhaus.org"; //"sbl.spamhaus.org"; //www.spamhaus.org
    public hw8(String filename, BufferedWriter writer) {
        this.filename = filename;
        this.writer = writer;
    }

    @Override
    public void run(){
        try (FileInputStream fin = new FileInputStream(filename);
             Reader in = new InputStreamReader(fin);
             BufferedReader bin = new BufferedReader(in)) {

            for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                int index = entry.indexOf(' ');
                if (index != -1) {
                    String ip = entry.substring(0, index);
                    String theRest = entry.substring(index);
                    boolean isSpam = isSpammer(ip); //스팸 체크 여부

                    try {
                        InetAddress address = InetAddress.getByName(ip);

                        if(isSpam) { //스팸이면, 스팸 이라고 작성
                            System.out.println(address.getHostName() + " - SPAM" + theRest);
                            writeToFile(address.getHostName() + " - SPAM" + theRest);

                        }
                        else{
                            System.out.println(address.getHostName() + theRest);
                            writeToFile(address.getHostName() + theRest);
                        }

                    } catch (UnknownHostException ex) {
                        System.out.println("Unknown host: " + ip);
                        writeToFile("Unknown host: " + ip);
                    }
                } else {
                    System.out.println("Invalid entry: " + entry);
                    writeToFile("Invalid entry: " + entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void writeToFile(String content) { //파일 작성하기
        synchronized (writer) {
            try {
                writer.write(content);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String inputFileName = "a.txt";
        String outputFileName = "access_hosts.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            int numThreads = 4; //쓰레드 개수
            hw8[] threads = new hw8[numThreads];
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new hw8(inputFileName, writer);
                threads[i].start();
            }

            // 모든 스레드가 종료될 때까지 대기
            for (hw8 thread : threads) {
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
