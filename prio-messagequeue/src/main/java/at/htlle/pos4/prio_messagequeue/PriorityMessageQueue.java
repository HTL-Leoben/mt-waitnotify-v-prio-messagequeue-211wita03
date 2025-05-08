package at.htlle.pos4.prio_messagequeue;

import java.util.LinkedList;
import java.util.Queue;

public class PriorityMessageQueue {
    private final Queue<Message> priorityQueue = new LinkedList<>();
    private final Queue<Message> normalQueue = new LinkedList<>();
    private final int maxSize;

    public PriorityMessageQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void sendMessage(Message msg) throws InterruptedException {
        while (size() >= maxSize) {
            wait();
        }

        if (msg.isPriority()) {
            priorityQueue.offer(msg);
        } else {
            normalQueue.offer(msg);
        }

        System.out.println("Produced: " + msg);
        notifyAll();
    }

    public synchronized Message receiveMessage() throws InterruptedException {
        while (size() == 0) {
            wait();
        }

        Message msg;
        if (!priorityQueue.isEmpty()) {
            msg = priorityQueue.poll();
        } else {
            msg = normalQueue.poll();
        }

        System.out.println("Consumed: " + msg);
        notifyAll();
        return msg;
    }

    private int size() {
        return priorityQueue.size() + normalQueue.size();
    }
}
