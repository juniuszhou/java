package Google;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyQueue {
    public static void main(String[] args) {
        // implement with heap
        Queue<String> queue = new PriorityQueue<String>();
        queue.peek();
        queue.poll(); // remove first one, null if empty
        //queue.remove(); // remove first one, exception if empty
        queue.offer("11"); // return false if full
        queue.add("11"); // exception if full

        // implement with list.
        queue = new LinkedList<String>();

        // set the size
        BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(100);
        bqueue.add("1");
        bqueue.offer("1");
        // bqueue.put("1"); // exception if size is overflow
        // bqueue.take(); // exception if empty

        // set the size
        //bqueue = new LinkedBlockingQueue<>();

        // deque
        Deque<String> deque = new ArrayDeque<String>();
        System.out.println(deque.size());
        deque.addFirst("1");
        deque.addLast("1");
        deque.add("1"); // add last but check if queue if full
        deque.offer("1");
        deque.offerFirst("q");
        deque.offerLast("1");

        deque.getFirst();
        deque.getLast();

        deque.peek();
        deque.poll();

        deque.push("1");
        deque.pop();

        deque = new LinkedList<String>();
    }
}
