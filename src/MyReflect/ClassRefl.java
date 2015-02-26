package MyReflect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Vector;

/**
 * Created by juzhou on 1/29/2015.
 */

class ClassToFile implements Serializable{
    private String name;
    private String id;
    public String getName() {name = "name"; System.out.println(name); return name;}
    public String getId() {id = "id"; System.out.println(id);return id;}
}

public class ClassRefl {
    public static void main(String[] args)throws Exception{
        Class c = ClassToFile.class;
        ClassToFile ctf = new ClassToFile();
        // c = ctf.getClass();
        System.out.println(c.toGenericString() + " shortname " + c.toString());

        Method m = c.getMethod("getName");
        m.invoke(ctf);

        Field f = c.getDeclaredField("name");
        f.getType();

    }
}
