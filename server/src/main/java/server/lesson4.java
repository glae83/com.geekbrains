package server;

public class lesson4 {
    private volatile char letter = 'A';
    private final Object lock = new Object();

    public static void main(String[] args) {
        lesson4 lesson4 = new lesson4();
        Thread threadPrintA = new Thread(() -> {
            lesson4.print('A', 'B');
        });
        Thread threadPrintB = new Thread(() -> {
            lesson4.print('B', 'C');
        });
        Thread threadPrintC = new Thread(() -> {
            lesson4.print('C', 'A');
        });
        threadPrintA.start();
        threadPrintB.start();
        threadPrintC.start();
    }

    private void print(char ch, char nextChar) {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != ch) {
                        lock.wait();
                    }
                    System.out.print(ch);
                    letter = nextChar;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
