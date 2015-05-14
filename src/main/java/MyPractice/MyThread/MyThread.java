package MyPractice.MyThread;

/**
 * Created by juzhou on 5/14/2015.
 */
public class MyThread extends Thread{
    @Override
    public void run(){

    }

    public static void main(String[] args){
        Thread t = new MyThread();
        t.start();
    }
}
