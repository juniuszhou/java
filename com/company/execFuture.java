/**
 * Created by junijun on 4/9/14.
 */
import java.util.concurrent.*;
/**
 * 新的任务执行架构。
 * 在Java 5.0之前启动一个任务是通过调用Thread类的start()方法来实现的，
 * 任务的提于交和执行是同时进行的，如果你想对任务的执行进行调度，
 * 或是控制同时执行的线程数量就需要额外编写代码来完成。
 * 5.0里提供了一个新的任务执行架构使你可以轻松地调度和控制任务的执行，
 * 并且可以建立一个类似数据库连接池的线程池来执行任务。
 * 这个架构主要有三个接口和其相应的具体类组成。
 * 这三个接口是Executor, ExecutorService和ScheduledExecutorService。
 * （1）Executor接口：是用来执行Runnable任务的，它只定义一个方法：
 * execute(Runnable command)：执行Runnable类型的任务
 * （2）ExecutorService：继承了Executor的方法，并提供了执行Callable任务和中止任务执行的服务，
 * 其定义的方法主要有：
 * submit(task)：可用来提交Callable或Runnable任务，并返回代表此任务的Future对象
 * invokeAll(collection of tasks)：批处理任务集合，并返回一个代表这些任务的Future对象集合
 * shutdown()：在完成已提交的任务后关闭服务，不再接受新任务
 * shutdownNow()：停止所有正在执行的任务并关闭服务。
 * isTerminated()：测试是否所有任务都执行完毕了。
 * isShutdown()：测试是否该ExecutorService已被关闭
 * （3）ScheduledExecutorService：继承ExecutorService，提供了按时间安排执行任务的功能、
 * schedule(task, initDelay): 安排所提交的Callable或Runnable任务在initDelay指定的时间后执行。
 * scheduleAtFixedRate()：安排所提交的Runnable任务按指定的间隔重复执行
 * scheduleWithFixedDelay()：安排所提交的Runnable任务在每次执行完后，等待delay所指定的时间后重复执行。
 *
 * 通过Executors类来获得各种服务对象。
 * callable(Runnable task): 将Runnable的任务转化成Callable的任务
 * newSingleThreadExecutor: 产生一个ExecutorService对象，这个对象只有一个线程可用来执行任务，若任务多于一个，任务将按先后顺序执行。
 * newCachedThreadPool(): 产生一个ExecutorService对象，这个对象带有一个线程池，线程池的大小会根据需要调整，线程执行完任务后返回线程池，供执行下一次任务使用。
 * newFixedThreadPool(int poolSize)：产生一个ExecutorService对象，这个对象带有一个大小为poolSize的线程池，若任务数量大于poolSize，任务会被放在一个queue里顺序执行。
 * newSingleThreadScheduledExecutor：产生一个ScheduledExecutorService对象，这个对象的线程池大小为1，若任务多于一个，任务将按先后顺序执行。
 * newScheduledThreadPool(int poolSize): 产生一个ScheduledExecutorService对象，这个对象的线程池大小为poolSize，若任务数量大于poolSize，任务会在一个queue里等待执行
 */

public class execFuture {
    public static void main(String[] args) throws Exception {
    //new executor to start a task
    ExecutorService executor = Executors.newSingleThreadExecutor();
    ExecutorService executor2 = Executors.newScheduledThreadPool(10);
    //get an async task and give a callable func object to task
    FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
                public String call() {System.out.println("future Hello world!");return "ok ";}});
    //execute it
    executor2.execute(future);
    String result = new String();
    try {
        result = future.get(5000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
        future.cancel(true);
    } catch (ExecutionException e) {
        future.cancel(true);
    } catch (TimeoutException e) {
        future.cancel(true);
    } finally {
        //shut down it, maybe its a thread pool
        executor.shutdownNow();
        executor2.shutdownNow();
    }

    System.out.println("Hello world!");
    }
}
