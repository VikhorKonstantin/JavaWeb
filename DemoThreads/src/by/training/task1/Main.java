package by.training.task1;

public class HelloWorld extends Thread {
    public void run() {
        System.out.println("Hello, world");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.start();
    }
}

