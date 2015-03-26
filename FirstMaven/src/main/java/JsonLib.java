/**
 * Created by juzhou on 3/5/2015.
 */

import net.sf.json.JSONString;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Objects;
import java.util.Set;

public class JsonLib {
    public static void main(String[] args){
        String enabled= "{\n" +
                "results: [\n" +
                "{\n" +
                "address_components: [\n" +
                "{\n" +
                "long_name: \"1600\",\n" +
                "short_name: \"1600\",\n" +
                "types: [\n" +
                "\"street_number\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "long_name: \"Amphitheatre Parkway\",\n" +
                "short_name: \"Amphitheatre Pkwy\",\n" +
                "types: [\n" +
                "\"route\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "long_name: \"Mountain View\",\n" +
                "short_name: \"Mountain View\",\n" +
                "types: [\n" +
                "\"locality\",\n" +
                "\"political\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "long_name: \"Santa Clara County\",\n" +
                "short_name: \"Santa Clara County\",\n" +
                "types: [\n" +
                "\"administrative_area_level_2\",\n" +
                "\"political\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "long_name: \"California\",\n" +
                "short_name: \"CA\",\n" +
                "types: [\n" +
                "\"administrative_area_level_1\",\n" +
                "\"political\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "long_name: \"United States\",\n" +
                "short_name: \"US\",\n" +
                "types: [\n" +
                "\"country\",\n" +
                "\"political\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "long_name: \"94043\",\n" +
                "short_name: \"94043\",\n" +
                "types: [\n" +
                "\"postal_code\"\n" +
                "]\n" +
                "}\n" +
                "],\n" +
                "formatted_address: \"1600 Amphitheatre Parkway, Mountain View, CA 94043, USA\",\n" +
                "geometry: {\n" +
                "location: {\n" +
                "lat: 37.4224879,\n" +
                "lng: -122.08422\n" +
                "},\n" +
                "location_type: \"ROOFTOP\",\n" +
                "viewport: {\n" +
                "northeast: {\n" +
                "lat: 37.4238368802915,\n" +
                "lng: -122.0828710197085\n" +
                "},\n" +
                "southwest: {\n" +
                "lat: 37.4211389197085,\n" +
                "lng: -122.0855689802915\n" +
                "}\n" +
                "}\n" +
                "},\n" +
                "place_id: \"ChIJ2eUgeAK6j4ARbn5u_wAGqWA\",\n" +
                "types: [\n" +
                "\"street_address\"\n" +
                "]\n" +
                "}\n" +
                "],\n" +
                "status: \"OK\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.fromObject(enabled);

        Object obj = jsonObject.get("results");

        Set keys = jsonObject.keySet();

        if (obj instanceof JSONArray){
            System.out.println(" json array");
            JSONArray jarr = (JSONArray) obj;
            JSONObject firstAddress = (JSONObject) jarr.get(0);
            JSONObject geometry = (JSONObject) firstAddress.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            Double lat = (Double) location.get("lat");
            Double lng = (Double) location.get("lng");
            System.out.println(" json array");

        } else if (obj instanceof JSONObject){
            System.out.println(" json array");
            JSONObject jobj = (JSONObject) obj;
            Object sub_obj = jobj.get("useBuildDateAsDomain");
            System.out.println(sub_obj.toString());
        } else if (obj instanceof JSONString){
            // JSONObject jobj = (JSONObject) obj;
            System.out.println(obj.toString() + "string");
        }
    }
}
