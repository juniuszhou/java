package FirstMaven;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
/*
＜?xml version="1.0" encoding="GB2312"?＞
＜RESULT＞
＜VALUE＞
　　 ＜NO＞A1234＜/NO＞
　　 ＜ADDR＞XX号＜/ADDR＞
＜/VALUE＞
＜VALUE＞
　　 ＜NO＞B1234＜/NO＞
　 　＜ADDR＞XX组＜/ADDR＞
＜/VALUE＞
＜/RESULT＞
 */
public class MyXMLReader {
    // "＜?xml version=\"1.0\" encoding=\"GB2312\"?＞\n" +"＜?xml version=\"1.0\" encoding=\"utf-8\"?＞" +
    private static String xml =
            "<data><employee><name>A</name>"
                    + "<title>Manager</title></employee></data>";
    public static void main(String args[]) {

        try {
            //File f = new File("data_10k.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            Document doc = builder.parse(is);

            NodeList nl = doc.getElementsByTagName("employee");
            for (int i = 0; i < nl.getLength(); i++) {
                System.out.print("车牌号码:" + doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue());

                System.out.println("车主地址:" + doc.getElementsByTagName("title").item(i).getFirstChild().getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
