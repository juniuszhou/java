package jieba.service.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class JiebaTokenizerTest {
	private static final String ENCODING = "utf-8";
	private String m_etsHostUrl;
	
	public JiebaTokenizerTest(String hostUrl) {
		m_etsHostUrl = hostUrl;
	}
	
	public String[] tokenizeChineseQuery(String query) {
		query = query.trim().replace(" ", "");
		String[] tokens = null;
		System.out.println("Input: " + query);
		try {
			String getURL = (m_etsHostUrl + "?key=" + URLEncoder.encode(query.replace("\"", "\\\""), ENCODING));
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING));
			String lines;
			while ((lines = reader.readLine()) != null)
			{
				break;
			}
			reader.close();
			connection.disconnect();
			
			System.out.println(lines);
			lines = lines.substring(2, lines.length() - 2);		// Remove [" and "] at both ends
			tokens = lines.split("\", \"");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tokens;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
    	String hostUrl = "http://10.208.40.208:8081/";
    	JiebaTokenizerTest t = new JiebaTokenizerTest(hostUrl);
    	String[] tokens = t.tokenizeChineseQuery("北京  海淀区,上地附近的6 号汽车旅馆");
    	String lines = "";
    	for(String token : tokens) {
    		if(!token.trim().equals(""))
    			lines += token + "/";
    	}
    	lines = lines.substring(0, lines.length() - 1);
    	System.out.println("Output: " + lines);
    }
}
