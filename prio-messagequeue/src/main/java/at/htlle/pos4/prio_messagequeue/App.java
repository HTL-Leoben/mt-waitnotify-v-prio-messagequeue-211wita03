package at.htlle.pos4.prio_messagequeue;

public class App {

    public static void main(String[] args) {
        PriorityMessageQueue queue = new PriorityMessageQueue(5);

        new Producer("Producer1", queue).start();
        new Producer("Producer2", queue).start();
        new Consumer("Consumer1", queue).start();
        new Consumer("Consumer2", queue).start();
    }
}
