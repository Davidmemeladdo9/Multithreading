package za.co.wethinkcode;
import java.util.Queue;

import java.util.LinkedList;

class Data {
    private Queue<String> q;
    private int capacity;
    public Data(int cap) {
        q = new LinkedList<>();
        capacity=cap;
    }

    public synchronized void publish(String msg) throws InterruptedException {
        String name=Thread.currentThread().getName();
        while(q.size() == capacity){
            System.out.println("Queue Full!"+name+" waiting for message to be consumed...");
            wait();
        }
        q.add(msg);
        System.out.println("Message published:: "+msg);
        System.out.println("Queue: "+ q);
        System.out.println();
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        String name=Thread.currentThread().getName();
        while(q.size()==0){
            System.out.println(name+" waiting for new message...");
            wait();
        }
        String msg = q.poll();
        System.out.println(name+" has consumed msg:: "+msg);
        System.out.println("Queue: "+ q);
        System.out.println();
        notifyAll();
    }
}
