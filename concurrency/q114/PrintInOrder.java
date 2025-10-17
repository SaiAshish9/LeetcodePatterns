package concurrency.q114;

public class PrintInOrder {
    private final Object lock = new Object();
    private boolean firstExecuted;
    private boolean secondExecuted;

    public PrintInOrder() {
        firstExecuted = false;
        secondExecuted = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            firstExecuted = true;
            lock.notifyAll(); // Notify waiting threads that first() has completed
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!firstExecuted) {
                lock.wait(); // Wait until first() has completed
            }
            printSecond.run();
            secondExecuted = true;
            lock.notifyAll(); // Notify waiting threads that second() has completed
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!secondExecuted) {
                lock.wait(); // Wait until second() has completed
            }
            printThird.run();
        }
    }

    public static void main(String[] args) {
        PrintInOrder printInOrder = new PrintInOrder();

        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");
        Thread t1 = new Thread(() -> {
            try {
                printInOrder.first(printFirst);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                printInOrder.second(printSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                printInOrder.third(printThird);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        t2.start();
        t1.start();

        // Output should be "firstsecondthird"
    }
}

// Complexity Analysis
// Time Complexity: O(1) for each method call since each method performs a constant amount of work.
// Space Complexity: O(1) as we are using a fixed amount of extra space regardless of input size.
