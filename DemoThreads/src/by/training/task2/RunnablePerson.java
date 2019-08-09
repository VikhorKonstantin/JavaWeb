package by.training.task2;

import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable {
    public RunnablePerson(final String newName) {
        super(newName);
    }

    @Override
    public void run() {
        int count = 10;
        for (int i =0; i < count; i++) {
            System.out.println(getName() + ": Hello, world");
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException newE) {
                System.out.println(newE);
            }
        }
    }
}
