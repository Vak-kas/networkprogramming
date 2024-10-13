public class week4_4 {
    public static void main(String[] args) {

// TODO Auto-generated method stub

// Create tasks

        Runnable printA = new PrintChar('a', 100);

        Runnable printB = new PrintChar('b', 100);

        Runnable print100 = new PrintNumJoin(100);

// Create threads

        Thread thread1 = new Thread(printA);

        Thread thread2 = new Thread(printB);

        Thread thread3 = new Thread(print100);

// Start threads

        thread3.start();

        thread1.start();

        thread2.start();

    }

}

/*

 *

 * class PrintChar implements Runnable {

 *

 * private char charToPrint; // The character to print private int times; // The

 * number of times to repeat

 *

 * public PrintChar(char c, int t) { charToPrint = c; times = t; }

 *

 * @Override // Override the run() method to tell the system public void run() {

 * for (int i = 0; i < times; i++) { System.out.print(charToPrint); } }

 *

 * }

 */

class PrintNumJoin implements Runnable {

    private int lastNum;

    public PrintNumJoin(int n) {

        lastNum = n;

    }

//    public void run() {
//
//        Thread thread4 = new Thread(new PrintChar('C', 10));
//
//        thread4.start();
//
//        try {
//
//            for (int i = 1; i <= lastNum; i++) {
//                System.out.print(" " + i);
//                if (i == 1)
//                    thread4.join();
//            }
//
//        } catch (InterruptedException ex) {
//
//        }
//
//    }
    public void run() {
        Thread thread4 = new Thread(new PrintChar('C', 10));
        thread4.start();

        try {
            // thread4가 종료될 때까지 현재 스레드를 멈춤
            thread4.join();

            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);
            }

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}

