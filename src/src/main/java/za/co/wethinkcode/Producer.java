package za.co.wethinkcode;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private BlockingQueue<String> q;

    public Producer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter a message: ");
                String message = scanner.nextLine();

                q.put(message);

                System.out.println("Message published: " + message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Close the scanner to avoid resource leak
        }
    }
}
