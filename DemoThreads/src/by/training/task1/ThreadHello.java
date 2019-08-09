package by.training.task1;

public class ThreadHello extends Thread {
    public void run() {
        System.out.println(currentThread().getName() + ", Hello, world");
    }
}
