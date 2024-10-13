public class week4_3 {

    public static void main(String[] args) {

        Thread printA = new PrintChar('a', 100);

        Thread printB = new PrintChar('b', 100);

        Runnable print100 = new PrintNum(100);

        Runnable printYield = new PrintNumYield(100);
        Thread thread4 = new Thread(printYield);

//PrintNum print100 = new PrintNum(100);

        Thread thread3 = new Thread(print100);

        thread4.start();

//        thread3.start();

//        thread3.run();

        printA.start();

        printB.start();

    }

}

class PrintChar extends Thread {

    private char charToPrint;

    private int times;

    public PrintChar(char c, int t) {

        charToPrint = c;

        times = t;

    }

    @Override

    public void run() {

        for (int i = 0; i < times; i++) {

            System.out.print(charToPrint+" ");

        }

    }

}

class PrintNum implements Runnable {

    private int lastNum;

    public PrintNum(int n) {

        lastNum = n;

    }

    public void run() {

        for (int i = 1; i <= lastNum; i++) {

            System.out.print(" "+i);

        }

    }

}

class PrintNumYield implements Runnable {

    private int lastNum;

    public PrintNumYield(int n) {

        lastNum = n;

    }

    public void run() {

        for (int i = 1; i <= lastNum; i++) {

            System.out.print(" "+i);
            Thread.yield();

        }

    }

}

//class PrintNumSleep implements Runnable {
//
//    private int lastNum;
//
//    public PrintNumSleep(int n) {
//
//        lastNum = n;
//
//    }
//
//    public void run() {
////
////        for (int i = 1; i <= lastNum; i++) {
////
////            System.out.print(" "+i);
////
////        }
//        try{
//            for (int i=1; i<=lastNum; i++){
//                System.out.println(" " + i);
//                if(i==10){
//                    Thread.sleep(5000);
//                }
//
//            }
//            catch{
//
//            }
//        }
//
//    }
//
//}
