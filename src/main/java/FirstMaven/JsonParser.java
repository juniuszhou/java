package FirstMaven;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by juzhou on 2/26/2015.
 */
public class JsonParser {
    /*
    static JSONParser jp = new JSONParser();
    // 1 array , 2 map.
    public static int isArrayMap(Object o){
        String s = o.toString();
        if (s.charAt(0) == '{')
            return 2;
        else if (s.charAt(0) == '[')
            return 1;

        return 0;
    }
    public static void printJsonArray(Object io)throws  Exception{
        JSONArray json = (JSONArray) io;
        for(Object o : json){
            if(isArrayMap(o) == 1){
                //System.out.println(entry.getKey());
                printJsonArray(o.toString());
            }
            else if (isArrayMap(o) == 2){
                //System.out.println(entry.getKey());
                printJsonMap(o.toString());
            }
            else {
                System.out.println("  " + o.toString());
            }
        }
    }

    public static void printJsonMap(String s) throws Exception{
        Map json = (Map) jp.parse(s);
        Iterator iter = json.entrySet().iterator();
        // System.out.println("==iterate result==");
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(isArrayMap(entry.getValue()) == 1){
                System.out.println(entry.getKey());
                printJsonArray(entry.getValue());
            }
            else if (isArrayMap(entry.getValue()) == 2){
                System.out.println(entry.getKey());
                printJsonMap(entry.getValue().toString());
            }
            else {
                System.out.println(entry.getKey() + "=>" + entry.getValue());
            }
        }
    }



    public static void main(String[] args){

        try {
            String nearby = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
            String param = "json?location=-33.8670522,151.1957362&radius=500&types=lodging&name=hotel&key=AIzaSyAN0QQAg4y5pemg59FBZvDV8_x6T5hNaAs";
            URL url;

                url = new URL(nearby + param);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));

                String input;
                StringBuilder sb = new StringBuilder();

                while ((input = br.readLine()) != null){
                    sb.append(input);
                    // System.out.println(input);
                }
                br.close();

            System.out.println("==iterate result==");
            printJsonMap(sb.toString());
                // first layer, key include html_attributions , results and status.


            System.out.println("==toJSONString()==");
            //System.out.println(JSONValue.toJSONString(json));

            // System.out.println(o.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    */

}
