import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.Vector;

public class NokiaJsonParser {

    public Vector<Double> getLatLngFromJson(JSONObject obj)
    {
        if(obj == null)
        {
            return null;
        }

        JSONObject response = obj.getJSONObject("Response");
        if (response == null)
        {
            return null;
        }

        JSONArray view = response.getJSONArray("View");
        if (view == null)
        {
            return null;
        }

        JSONObject firstView = view.getJSONObject(0);
        if (firstView == null)
        {
            return null;
        }

        JSONArray jarr = firstView.getJSONArray("Result");
        if (jarr == null)
        {
            return null;
        }

        Vector<Double> res = new Vector<>();

        for(Object j : jarr)
        {
            JSONObject location = ((JSONObject)j).getJSONObject("Location");
            if(location == null)
            {
                continue;
            }

            JSONArray navigationPosition = location.getJSONArray("NavigationPosition");
            if(navigationPosition == null || navigationPosition.size() < 1)
            {
                continue;
            }

            JSONObject firstNavigation = navigationPosition.getJSONObject(0);
            if(firstNavigation == null)
            {
                continue;
            }

            System.out.println("lat is " +
                    Double.valueOf(firstNavigation.getString("Latitude")));
            System.out.println("lng is " +
                    Double.valueOf(firstNavigation.getString("Longitude")));
        }

        return res;
    }
}

