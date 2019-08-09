package by.training.task2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started");

        RunnablePerson person1 = new RunnablePerson("Alice");
        Thread t1 = new Thread(person1);
        System.out.println("t1 created");

        RunnablePerson person2 = new RunnablePerson("Bob");
        Thread t2 = new Thread(person2);
        System.out.println("t2 created");

        t1.start();
        t2.start();

        t1.join();


        System.out.println("Main finished");
    }
}
