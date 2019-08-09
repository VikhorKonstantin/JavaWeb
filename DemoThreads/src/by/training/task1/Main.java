package by.training.task1;

public class Main {
    public static void main(String[] args) {
        ThreadHello helloWorld = new ThreadHello();
        System.out.println("state = " + helloWorld.getState());
        helloWorld.start();
        System.out.println("state = " + helloWorld.getState());
    }
}
