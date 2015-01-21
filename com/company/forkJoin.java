import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class forkJoin {
    private class MyAction extends RecursiveAction{
        private static final int a = 10;
        @Override
        protected void compute() {
            if (a < 10){
                return ;
            }
            else{
                this.fork(); //run upper bound
                this.compute(); //reuse this thread for right part.
            }
        }
    }

    private class Calculator extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 100;
        private int start;
        private int end;

        public Calculator(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if((start - end) < THRESHOLD){
                for(int i = start; i< end;i++){
                    sum += i;
                }
            }else{
                int middle = (start + end) /2;
                Calculator left = new Calculator(start, middle);
                Calculator right = new Calculator(middle + 1, end);

                left.fork();
                right.fork();

                sum = left.join() + right.join();
            }
            return sum;
        }
    }

    public static void main(String args[]) throws Exception{
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //Future<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));

        //assertEquals(new Integer(49995000), result.get());
    }
}
