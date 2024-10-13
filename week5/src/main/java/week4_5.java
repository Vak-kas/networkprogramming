class SharedResource {

    private boolean isNumberTurn = true;

// 숫자를 출력하는 메소드

    public synchronized void printNumber(int number) {

        while (!isNumberTurn) {

            try {

                wait(); // 문자를 출력하는 스레드가 실행될 때까지 대기

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }

        }

        System.out.print(number+" ");

        isNumberTurn = false; // 다음은 문자를 출력해야 함

        notify(); // 문자 스레드에 알림

    }

// 문자를 출력하는 메소드

    public synchronized void printChar(char character) {

        while (isNumberTurn) {

            try {

                wait(); // 숫자를 출력하는 스레드가 실행될 때까지 대기

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }

        }

        System.out.print(character+" ");

        isNumberTurn = true; // 다음은 숫자를 출력해야 함

        notify(); // 숫자 스레드에 알림

    }

}

class NumberThread extends Thread {

    private SharedResource resource;

    public NumberThread(SharedResource resource) {

        this.resource = resource;

    }

    public void run() {

        for (int i = 1; i <= 5; i++) {

            resource.printNumber(i); // 숫자를 출력

        }

    }

}

class CharThread extends Thread {

    private SharedResource resource;

    public CharThread(SharedResource resource) {

        this.resource = resource;

    }

    public void run() {

        for (char c = 'A'; c <= 'E'; c++) {

            resource.printChar(c); // 문자를 출력

        }

    }

}

public class week4_5 {

    public static void main(String[] args) {

        SharedResource resource = new SharedResource();

        Thread charThread = new CharThread(resource);

        Thread numberThread = new NumberThread(resource);

        numberThread.start();

        charThread.start();

    }

}

