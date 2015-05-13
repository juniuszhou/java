package Syntax;

import java.util.List;

public class GenericUsage {
    public class A{}
    // 参数作为生产者使用<? extends E>，作为消费者时使用<? super E>
    // 其实意思很好理解，如果你把一个定义成泛型的容器的对象取出来，那么这个
    // 它的类型有一个上界就可以了，我们可以把子类型定义的值取出来赋给父类型。
    // 当我们要用值取更新一个泛型的容器，那么正好相反了，我们需要下届。
    // 这样可以赋值成功。其实归根到底，只有一个原则，可以把子类对象赋值给
    // 父类类型，反过来不可以，因为很有可能赋值失败。需要向下转型。
    //  Upper Bound
    public static void f1(List<? extends Number> l){
        Number n = l.get(0);
        // l.add(new Integer(1)); // means give a integer to ?. neve be sure.
    }

    // Lower Bound
    public static void f2(List<? super Number> l){
        l.add(new Integer(1));
    }

    // just ? equals to ? extends object
    public static void f3(List<?> l){
        Object obj = l.get(0);
        // l.set(0, 1);
        // l.set(0, obj); // compile error. we can get but can't set.
    }

    //combine upper bound
    // not supported yet??? not sure.
    //public static void f4(List<? extends Number, A> l){
        // l.add(new Integer(1));
    //}

    public static void main(String[] args){

    }
}
