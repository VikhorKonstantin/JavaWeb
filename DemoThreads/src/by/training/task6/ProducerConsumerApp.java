package by.training.task6;

public class ProducerConsumerApp {
    public static void main(String[] args) {
        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}

class Store {
    private int product = 0;
    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
    // метод для покупателей
    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}
//поток производителя
class Producer extends Thread {
    Store store; // объект склада, куда кладем товар
    int product = 5; // количество товаров, которые надо добавить
    Producer(Store store) {
        this.store = store;
    }
    public void run() {
        for (int i = 1; i < product; i++) {
            store.put();
        }
    }
}
class Consumer extends Thread {
    Store store; // объект склада, с которого покупатель будет брать товар
    final int N = 6; // максимально допустимое число
    Consumer(Store store) {
        this.store = store;
    }
    public void run() {
        for (int i = 1; i < N; i++) {
            store.get();
        }
    }
}