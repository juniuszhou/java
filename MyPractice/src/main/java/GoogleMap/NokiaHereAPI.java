package GoogleMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NokiaHereAPI {
    private static final String s_url = "http://geocoder.cit.api.here.com/6.2/geocode.json?";
    private static final String NOKIA_APP_ID = "miVH2FAxD3wx7OF1lhQ7";
    private static final String NOKIA_APP_CODE = "j_VaNixii-ylv88588Rf6Q";
    private static final String ADDRESS = "&searchtext=Randolph St";

    public static void main(String[] args)throws Exception{
        String urlStr = s_url + "app_id=" + NOKIA_APP_ID;
        urlStr += "&app_code=" + NOKIA_APP_CODE;
        urlStr += "&gen=8";
        urlStr += ADDRESS;
        urlStr += "&city=Chicago";
        urlStr += "&country=USA";

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
