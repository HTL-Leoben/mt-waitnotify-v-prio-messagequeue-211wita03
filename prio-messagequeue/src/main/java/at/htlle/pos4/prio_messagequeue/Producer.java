package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

public class Producer extends Thread {
    private final String name;
    private final PriorityMessageQueue queue;
    private final Random random = new Random();

    public Producer(String name, PriorityMessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        int count = 1;
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                boolean isPriority = random.nextBoolean();
                String content = name + ": Message " + count++;
                Message msg = new Message(isPriority, content);
                queue.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
