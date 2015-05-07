import java.nio.charset.Charset;

/**
 * Created by juzhou on 4/1/2015.
 */
public class Enumusage {
    public enum GeoInfoSource
    {
        GOOGLE,
        NOKIA(1),
        OSM_GEO_NAME;

        private GeoInfoSource(){}
        private GeoInfoSource(int code){}
    }

    public static void main(String[] args) {
        GeoInfoSource g = GeoInfoSource.GOOGLE;
        System.out.println(Charset.defaultCharset().displayName());
        // System.out.println(GeoInfoSource.GOOGLE < GeoInfoSource.OSM_GEO_NAME);
    }
}
