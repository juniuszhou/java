package GoogleMap;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class PlaceAPI {
    static final String search = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    static final String key = "&type=lodging&key=AIzaSyAN0QQAg4y5pemg59FBZvDV8_x6T5hNaAs";
    static final String inputFilePath = "D:\\Notes\\NewHotels.txt";
    static final String outputFilePath = "D:\\CompareResult.txt";
    static final String formatted_address_filed = "formatted_address";
    static final String[] stop_words = {"of"};

    public static double NameComparison(String s1, String s2){
        String[] s1Arr = s1.split(" ");
        String[] s2Arr = s2.split(" ");

        int i = s1Arr.length;
        if (i == 0) return 0.0;

        int j = 0;
        for(String sa : s1Arr){
            for(String sb : s2Arr){
                if (sa.equals(sb)){
                    j++;
                    continue;
                }
            }
        }

        return j / i;
    }

    public static String ExtendAbbreviation(String s){
        switch (s){
            case "ave":
                return "avenue";
            case "st":
                return "street";
            case "str":
                return "street";
            case "e":
                return "east";
            case "w":
                return "wast";
            case "n":
                return "north";
            case "s":
                return "south";
            default:
                return s;
        }
    }

    public static boolean IsStopWord(String s){
        for(String stop : stop_words){
            if (s.equals(stop))
                return true;
        }
        return false;
    }

    public static String TrimTail(String s){
        char last = s.charAt(s.length() - 1);

        if(last == ',' || last == '.'){
            return s.substring(0,s.length()-1);
        }
        return s;
    }
    public static void WriteToFile(Vector<Vector<String>> vvs){
        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFilePath)));
            for(Vector<String> vs : vvs){
                out.write(vs.get(2));
                out.write("\t");
                out.write(vs.get(3));
                out.newLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean CompareTwoAddresses(String db, String google){
        HashMap<String, Object> m = new HashMap<>();
        String[] strs = google.split(",");
        for(String s : strs){
            String words[] = s.split(" ");
            for(String s2: words){
                m.put(s2.toLowerCase(), null);
            }
        }

        String words[] = db.split(" ");
        for(String s2: words){
            String low = s2.toLowerCase();
            if(IsStopWord(low))
                continue;

            if (!m.containsKey(ExtendAbbreviation(TrimTail(low))));
                return false;
        }

        return true;
    }

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

        if (( Math.abs(d1-d3) < 0.01 ) && ( Math.abs(d2-d4) < 0.01 ))
            return true;
        return false;
    }

    public static Vector<Vector<String>> GetAddresses()throws Exception{
        Vector<Vector<String>> vvs = new Vector<>();

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inputFilePath)));
            String s = in.readLine();

            while (s != null) {
                String[] id = s.split("\t");
                Vector<String> v = new Vector<>();

                // search by name + city + state
                String query = id[2].trim() + "," + id[5].trim() + "," + id[6].trim();
                String gAddress = callAPI(query.replace(" ", "%20"));
                if (gAddress == null) {
                    s = in.readLine();
                    continue;
                }

                query = gAddress.trim();
                String gLanLgn = GeoAPI.callAPI(query.replace(" ", "%20"));
                if (gLanLgn == null) {
                    s = in.readLine();
                    continue;
                }

                // call geo via address + city + state
                String query2 = id[3].trim() + "," + id[5].trim() + "," + id[6].trim();

                String aLanLgn = GeoAPI.callAPI(query2.replace(" ", "%20"));
                // ifWithinTolerance()

                if (!ifWithinTolerance(gLanLgn,aLanLgn)) {
                    System.out.print(query2 + "\t" + gAddress + "\t");
                    System.out.println(aLanLgn + "\t" + gLanLgn);
                }

                // call Geo API to get lat/lng via address + city + state.

                // add id, name, address, address from google, city , state, postcode, country code
                v.add(id[1]);
                v.add(id[2]);
                v.add(id[3]);
                // v.add(gAddr);
                v.add(id[5]);
                v.add(id[6]);
                v.add(id[7]);

                //System.out.print(id[1] + "\t");
                //System.out.print(id[2] + "\t");
                //System.out.print(id[3] + "\t");
                //System.out.print(gAddr + "\t");
                //System.out.print(id[5] + "\t");
                //System.out.print(id[6] + "\t");
                //System.out.print(id[7] + "\t");

                //v.add(gAddr);

                // compare address from db and google.
                //System.out.println(CompareTwoAddresses(id[3], gAddr));
                s = in.readLine();
            }
        }
        catch (ExportException e){
            e.printStackTrace();
        }
        return vvs;
    }


    public static String callAPI(String name){
        URL url;
        try{
            String urlStr = search + name + key;
            //System.out.println(urlStr);
            url = new URL(urlStr.replace(" ","%20"));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

            String input;
            boolean intoPlace = false;
            String lat = "";
            String lng;

            while ((input = br.readLine()) != null){
                                /*
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
                    return lat + "\t" + lng;
                }*/
                if(input.contains(formatted_address_filed))
                {
                    int b = input.lastIndexOf("\"");
                    String tmp = input.substring(0, b);
                    int a = tmp.lastIndexOf("\"");
                    String address = input.substring(a + 1, b);
                    return address;
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        Vector<Vector<String>> vvs = GetAddresses();
        WriteToFile(vvs);
    }
}
