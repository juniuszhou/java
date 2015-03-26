package GoogleMap;

import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;

public class MyXMLReader {
    private static String xml =
            "<data><employee><name>A</name>"
                    + "<title>Manager</title></employee></data>";

    public static void main(String args[]) {
        getLatLong(xml);
    }

    public static void getLatLong(String lines) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(lines));
            Document doc = builder.parse(is);
/*
NavigationPosition>
<Latitude>47.6058884</Latitude>
<Longitude>-122.2856064</Longitude>
</NavigationPosition>
 */
            NodeList results = doc.getElementsByTagName("Result");

            for (int i = 0; i < results.getLength(); i++) {
                NodeList n = doc.getElementsByTagName("NavigationPosition").item(i).getChildNodes();
                for(int j = 0; j < n.getLength(); j++){
                    Node nn = n.item(j);
                    System.out.println(nn.getNodeName());
                    // nn.getFirstChild()
                    System.out.println(nn.getFirstChild().getNodeValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
