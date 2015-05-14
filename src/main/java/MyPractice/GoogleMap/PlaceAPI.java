package MyPractice.GoogleMap;

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

    }
}
