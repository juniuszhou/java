import java.math.BigInteger;

public class Question1 {
    public static void BigNum(String base, String repeat){
        final BigInteger biZero = new BigInteger("0",10);
        final BigInteger biOne = new BigInteger("1",10);
        final BigInteger biTen = new BigInteger("10",10);

        //deal base
        BigInteger biBase = new BigInteger(base,10);
        long len = base.length();

        //get one followed by zero
        BigInteger biMove = biOne;
        BigInteger biProdFactor = biTen;
        while(len > 0){
            if ((len & 0x01) == 1)
                biMove = biMove.multiply(biProdFactor);
            biProdFactor = biProdFactor.multiply(biProdFactor);
            len >>= 1;
        }

        //deal repeat
        BigInteger result = biBase;
        BigInteger midRes = biBase; //avoid the multiply operation.

        BigInteger biRepeat = new BigInteger(repeat,10);

        while(biRepeat.compareTo(biOne) > 0){
            result = result.multiply(biMove);
            midRes = midRes.add(biBase);
            result = result.add(midRes);
            biRepeat = biRepeat.add(biOne.negate());
        }

        System.out.println("result is " + result.toString());
    }

    public static void main(String[] args) {
        if (args.length != 2){
            System.out.println("parameter number is wrong");
            return;
        }

        System.out.println(args[0]);
        System.out.println(args[1]);
        BigNum(args[0],args[1]);
    }
}
