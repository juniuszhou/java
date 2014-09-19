import java.io.*;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.*;
import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.Object;
import java.util.Scanner;
import java.util.Collection;
import java.util.*;
import java.math.*;
import java.util.concurrent.Semaphore;
import java.io.File;
import java.lang.reflect.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

interface myset{
    public void getString();
}

class concreteSet implements myset{
    public void getString(){System.out.println("concreteset ");};
}


class mytem<Object> {

}

public class Hello {
    public static void main(String args[]) {
        try {
           mytem<Integer> mm = new mytem<Integer>();
        }
        catch (Throwable e) {
            System.err.println(e);
        }

        System.out.println("hello ");
    }
}
