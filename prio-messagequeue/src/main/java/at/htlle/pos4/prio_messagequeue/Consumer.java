package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

public class Consumer extends Thread {
    private final String name;
    private final PriorityMessageQueue queue;
    private final Random random = new Random();

    public Consumer(String name, PriorityMessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1500));
                Message msg = queue.receiveMessage();
                System.out.println(name + " received: " + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
