import java.util.Random;
import java.net.ServerSocket;

interface interA{
    void callBack();
}
public class random{
    public static void main(String [] args){

        random rd = new random();
        rd.setCallBack(new interA() {
            @Override
            public void callBack() {
                int i = 0;
            }
        });
        //random.this.clone();
               // this.

        //generate lots of int
        int i = 0;
        while (i < 100){
           // int j = rd.nextInt();
            byte [] buf = new byte[10];
            //rd.nextBytes(buf);
            i++;
        }

        System.out.println("hello");
    }

    public void setCallBack(interA ia){interA myIa = ia;}
}

