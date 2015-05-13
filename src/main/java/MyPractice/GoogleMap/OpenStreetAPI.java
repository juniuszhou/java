package GoogleMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by juzhou on 3/24/2015.
 */
public class OpenStreetAPI {
    private static final String s_url = "http://nominatim.openstreetmap.org/search?";

    public static void main(String[] args)throws Exception{
        String urlStr = s_url;
        urlStr += "street=" + "changan";
        urlStr += "&city=" + "beijing";
        urlStr += "&state=" + "beijing";
        urlStr += "&country" + "china";
        urlStr += "&format=json&polygon=1&addressdetails=1";

        URL url = new URL(urlStr.replace(" ","%20"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

        String input = null;
        StringBuffer sb = new StringBuffer();

        while ((input = br.readLine()) != null) {
            sb.append(input);
            System.out.println(input);
        }

        // MyXMLReader.getLatLong(sb.toString());
    }
}
