package GoogleMap;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by juzhou on 3/17/2015.
 */
public class SuggetAPI {

    public static void main(String[] args) throws Exception {
        callAPI("amazn");
    }

    /*
    http://clients1.google.com/complete/search?hl=en&output=toolbar&q=YOURSEARCHTERM
    http://suggestqueries.google.com/complete/search?output=toolbar&hl=en&q=YOURSEARCHTERM
     */
    private static String PREFIX = "http://suggestqueries.google.com/complete/search?output=toolbar&hl=en&q=";

    public static String callAPI(String query) {
        URL url;
        try {
            String urlStr = PREFIX + query;
            url = new URL(urlStr.replace(" ", "%20"));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

            String input;
            boolean intoPlace = false;
            String lat = "";
            String lng;

            while ((input = br.readLine()) != null) {
                if (input.contains("formatted_address_filed")) {
                    int b = input.lastIndexOf("\"");
                    String tmp = input.substring(0, b);
                    int a = tmp.lastIndexOf("\"");
                    String address = input.substring(a + 1, b);
                    return address;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
