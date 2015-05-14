package MyPractice.MyThread;

class MyRun implements Runnable{
    private int a = 100;
    @Override
    public void run(){
        while(a > 0){
            try {
                a = a - 1;
                System.out.println(Thread.currentThread().getName() + " " + a);
                Thread.sleep(1000);
            }
            catch (Exception e){

            }
        }
    }

}
public class MyRunnable {

    public static void main(String[] args){
        MyRun r = new MyRun();
        //Thread tt = new Thread(r);
        for(int i = 0; i < 10; ++i){
            Thread t = new Thread(r);
            t.start();
        }
    }
}
