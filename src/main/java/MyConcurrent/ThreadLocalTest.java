package MyConcurrent;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    public static void main(String[] args) throws Exception {
        Path p = Paths.get("D:/");
        List<Path> roots = (List<Path>) FileSystems.getDefault().getRootDirectories();
        List<Path> result = new ArrayList<Path>();
        List<MyTask> tasks = new ArrayList<MyTask>();
        ForkJoinPool pool = new ForkJoinPool();
        for(Path root:roots) {
            MyTask t = new MyTask(root, "pdf");
            // asynchronous execution of the given task.
            pool.execute(t);
            tasks.add(t);
        }

        System.out.print("正在处理中");
        while(isAllDone(tasks) == false) {
            System.out.print(". ");
            TimeUnit.SECONDS.sleep(3);
        }

        for(MyTask t:tasks) {
            result.addAll(t.get());
        }

        for(Path pp:result) {
            System.out.println(pp);
        }
    }

    private static boolean isAllDone(List<MyTask> tasks) {
        boolean result = true;
        for(MyTask t:tasks) {
            if(t.isDone() == false) {
                result = false;
                break;
            }
        }
        return result;
    }
}

class MyTask extends RecursiveTask<List<Path>> {

    private static final long serialVersionUID = 1L;

    private Path path;
    private String fileExtention;

    public MyTask(Path path, String fileExtention) {
        super();
        this.path = path;
        this.fileExtention = fileExtention;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<Path>();
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);

            //record all tasks.
            List<MyTask> subTasks = new ArrayList<MyTask>();
            for(Path p:paths) {
                if(Files.isDirectory(p)) {
                    MyTask t = new MyTask(p, fileExtention);
                    t.fork();
                    subTasks.add(t);
                }else if(Files.isRegularFile(p)) {
                    if(p.toString().toLowerCase().endsWith("."+fileExtention)) {
                        result.add(p);
                    }
                }
            }

            for(MyTask t:subTasks) {
                result.addAll(t.join());
            }
        } catch (IOException e) {
        }
        return result;
    }
}