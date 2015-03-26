package company;

import java.util.Map;
class IfTraceOk{
    public static void printMe(){
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " " +
                Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                Thread.currentThread().getStackTrace()[1].getLineNumber());
    }
}
class Errors{
       public static void main(String [] args){
           Map m = Thread.getAllStackTraces();

        System.out.println("junius CLASS " + Thread.currentThread().getStackTrace()[1].getClassName() + "; METHOD " +
                Thread.currentThread().getStackTrace()[1].getMethodName() + ";  LINE " +
                Thread.currentThread().getStackTrace()[1].getLineNumber());
           IfTraceOk.printMe();
    }
}