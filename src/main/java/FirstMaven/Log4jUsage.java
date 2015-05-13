import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Simple Logging Facade for Java
public class Log4jUsage {
    private static final Logger LOGGER = LoggerFactory.getLogger("Log4jUsage");
    private static final String LOGGER_FILE_PATH = "D:\\Wall-E\\Log.txt";
    public static void main(String[] args){
        String str = null;
        LOGGER.info("{}", str);
    }
}
