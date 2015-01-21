import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by junijun on 6/26/14.
 */

class th extends Thread{
    private CyclicBarrier cbr;
    th(CyclicBarrier aCbr){cbr = aCbr;}

    public void run(){
        System.out.println("I am " + Thread.currentThread().getId());

        try{
            cbr.await();
        }

        catch (Exception e){
            System.out.println("general exception from " + Thread.currentThread().getId());
        }
        System.out.println("OK from " + Thread.currentThread().getId());
    }
}

class th2 extends Thread{
    private CountDownLatch cbr;
    th2(CountDownLatch aCbr){cbr = aCbr;}

    public void run(){
        System.out.println("I am " + Thread.currentThread().getId());

        try{
            cbr.await();
        }

        catch (Exception e){
            System.out.println("general exception from " + Thread.currentThread().getId());
        }
        System.out.println("OK from " + Thread.currentThread().getId());
    }
}

public class Concurrency {

    public static void main(String args[]) throws Exception{

        BlockingQueue bqi = new LinkedBlockingDeque<Integer>(5);
        BlockingDeque bdi = new LinkedBlockingDeque<Integer>(5);
        LinkedBlockingDeque<Integer> bd = new LinkedBlockingDeque<Integer>(5);

        BlockingQueue bqi2 = new ArrayBlockingQueue<Integer>(5);
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);

        //it is implemented in segment lock. since it includes a  continuous container like array
        //so it can't use lock free operation need pointer replacement mechanism.
        ConcurrentHashMap chm = new ConcurrentHashMap();

        //it is implemented in lock free.  Thanks to the GC, there is no ABA
        ConcurrentLinkedDeque clq = new ConcurrentLinkedDeque();
        ConcurrentLinkedQueue clqu = new ConcurrentLinkedQueue();

        //my guess is skip list also lock-free, impossible for segment lock.
        ConcurrentSkipListMap cslm = new ConcurrentSkipListMap();
        ConcurrentSkipListSet csls = new ConcurrentSkipListSet();


        //this BrokenBarrierException cooperate with CycliBarrier
        CyclicBarrier cbr = new CyclicBarrier(3, new Runnable() {
            // 在所有线程都到达Barrier时执行
            public void run() {
                System.out.println("testCyclicBarrier run");
            }
        });
        for (int i = 0; i < 3; ++i){
            (new th(cbr)).start();
        }

        Thread.sleep(2000);
        System.out.println("game over");

        //CountDownLatch
        CountDownLatch cdl = new CountDownLatch(3);
        for (int i = 0; i < 3; ++i){
            (new th2(cdl)).start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < 3; ++i){
            cdl.countDown();
        }

        System.out.println("game over");
    }
}
