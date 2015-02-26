package GoogleMap;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Created by juzhou on 2/26/2015.
 * https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=cruise&key=AddYourOwnKeyHere
 */
public class PlaceAPI {
    public static void main(String[] args){

        String nearby = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
        String param = "json?location=-33.8670522,151.1957362&radius=500&types=lodging&name=hotel&key=AIzaSyAN0QQAg4y5pemg59FBZvDV8_x6T5hNaAs";
        URL url;
        try{
            url = new URL(nearby + param);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

            String input;

            while ((input = br.readLine()) != null){
                System.out.println(input);
            }
            br.close();
        }
        catch (Exception e){

        }

    }
}
