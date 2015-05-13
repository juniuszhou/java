import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.Semaphore;


public class syncThread implements Runnable{
        private String name;
        private Object prev;
        private Object self;

        private syncThread(String name, Object prev, Object self) {
            this.name = name;this.prev = prev;this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                //sync an object equals to add lock on the object
                synchronized (prev) {
                    synchronized (self) {
                        System.out.print(name);
                        count--;
                        //tell waiting thread, self 's lock is free now.
                        self.notify();
                    }
                    try {
                        //release the lock and wait for other thread notify lock available again.
                        if (count > 0) prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static void main(String[] args) throws Exception {
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();
            syncThread pa = new syncThread("A", c, a);
            syncThread pb = new syncThread("B", a, b);
            syncThread pc = new syncThread("C", b, c);
            Thread ta = new Thread(pa);
            Thread tb = new Thread(pb);
            Thread tc = new Thread(pc);
            ta.start();tb.start();tc.start();
            ta.join();tb.join();tc.join();

            //semaphore lock
            Lock lck = new ReentrantLock();
            lck.tryLock();
            lck.unlock();

            System.out.println("  Over !!!!");
        }

}
