package Chinese;

import java.io.File;

/**
 * Created by juzhou on 2/3/2015.
 */
public class GenerateDir {
    public static  void main(String[] args){
        // String remote = "hadoop fs -copyToLocal /user/olinda/ESS_SEARCH_SESSION/site_name=HOTELS.ZH_CN/local_date=20";
        String remote = "hadoop fs -copyFromLocal";
        String rPath = " /user/juzhou/ESS_SEARCH_SESSION/site_name=HOTELS.ZH_CN/local_date=20";

        String filename = "data.txt";
        String local =  " /home/juzhou/data/";
        int i = 11;
        int j = 1;
        while (i < 15){
            j = 1;
            while (j < 13){
                String path ;
                if (j < 10)
                    path = "" + i + "_0" + j;
                else
                    path = "" + i + "_" + j;
                System.out.println(remote + local + filename + path   + rPath + path + "/" + filename);
                // new File(local + filename + path).renameTo(local + path + filename );
                j++;
            }
            i++;
        }
    }
}
