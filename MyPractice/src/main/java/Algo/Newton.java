package Algo;

/**
 * Created by juzhou on 12/2/2014.
 */
public class Newton {
    public static double squareRoot(double a, double b){
        if (Math.abs(b*b -a) < 0.0000000001)
            return b;
        else {
            b = (b + a / b) / 2;
            System.out.println(b);
            return squareRoot(a, b);
        }
    }
    public static void main(String[] args) {
        double a = 81;
        double b = 32;
        squareRoot(81, 32);
    }
}
