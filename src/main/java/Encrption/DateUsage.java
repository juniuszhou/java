package Encrption;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by juzhou on 6/26/2015.
 */
public class DateUsage {
    public static void main(String [] args) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        System.out.println(str);
    }
}
