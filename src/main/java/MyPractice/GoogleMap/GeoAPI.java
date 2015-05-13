package GoogleMap;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.Vector;

/**
 * Created by juzhou on 3/2/2015.
 */
public class GeoAPI {
    static final String search = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    static final String inputFilePath = "D:\\NewProj\\chang.txt";
    static final String outputFilePath = "D:\\CompareResult.txt";
    static final String lat_filed = "lat";
    static final String lng_filed = "lng";

    static final String[] stop_words = {"of"};
    public static boolean ifWithinTolerance(String latLon1, String latLon2){
        if ((latLon1 == null) || (latLon2 == null))
            return true;
        if (latLon1.equals("null") || latLon2.equals("null"))
            return true;

        String one[] = latLon1.split("\t");
        double d1 = Double.parseDouble(one[0]);
        double d2 = Double.parseDouble(one[1]);
        String two[] = latLon2.split("\t");
        double d3 = Double.parseDouble(two[0]);
        double d4 = Double.parseDouble(two[1]);

        if (( Math.abs(d1-d3) < 0.02 ) && ( Math.abs(d2-d4) < 0.02 ))
            return true;
        return false;
    }
    public static String callAPI(String name){
        URL url;
        try{
            String urlStr = search + name;
            // System.out.println(urlStr);
            url = new URL(urlStr.replace(" ","%20"));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

            String input;
            String lat = "";
            String lng = "";
            boolean intoPlace = false;

            while ((input = br.readLine()) != null){
                // System.out.println(input);
                if (input.contains("location")){
                    intoPlace = true;
                }
                if(intoPlace && input.contains("lat"))
                {
                    String tmp[] = input.split(":");
                    String includeComma = tmp[1].trim();
                    lat = includeComma.substring(0,includeComma.length() - 1);

                }
                if(intoPlace && input.contains("lng"))
                {
                    String tmp[] = input.split(":");
                    lng = tmp[1].trim();
                    System.out.println(" " + lat + "\t" + lng);
                    return lat + "\t" + lng;
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    public static void main(String[] args) throws Exception {


            Vector<Vector<String>> vvs = new Vector<>();

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(inputFilePath)));
                String s = in.readLine();

                while (s != null) {
                    // System.out.println(s);
                    String[] id = s.split("\t");
                    Vector<String> v = new Vector<>();
                    if (!id[0].equals("TRUE")) {
                        s = in.readLine();
                        continue;
                    }

                    String query = id[3].trim() + "," + id[4].trim();
                    if (id[5].trim().length() > 1)
                        query += "," + id[5].trim();
                    query += "," + id[6].trim();

                    String gLanLgn = callAPI(query.replace(" ", "%20"));
                    if (gLanLgn == null) {
                        s = in.readLine();
                        continue;
                    }

                    // call geo via address + city + state
                    String query2 = id[9].trim();

                    String aLanLgn = GeoAPI.callAPI(query2.replace(" ", "%20"));
                    if (aLanLgn == null) {
                        s = in.readLine();
                        continue;
                    }

                    for(String str: id){
                        System.out.print(str + "\t");
                    }


                    System.out.print(aLanLgn + "\t");
                        System.out.print(gLanLgn + "\t");
                    System.out.println(ifWithinTolerance(aLanLgn, gLanLgn));


                    s = in.readLine();
                }
            }
            catch (ExportException e){
                e.printStackTrace();
            }

        }

*/

    public static void main(String[] args) {
        System.out.println(callAPI("Al Sabakha Street, Dubai, United Arab Emirates"));
        System.out.println(callAPI("Dubai, United Arab Emirates"));
    }

}
