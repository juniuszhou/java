import java.util.regex.Pattern;

public class Chinese {

    public static void main(String[] args) {
        /*Pattern pattern;
        String patternStr = "(?:(?<digits>\\d+(?:,\\d\\d\\d)*(?:\\.\\d*)?|\\.\\d+))";
                // "(?:(?<digits>\\d+(?:,\\d\\d\\d)*(?:\\.\\d*)?|\\.\\d+)|((?<ordinals>\\d+)(?:st|nd|rd|th)))";
                // "((<digits>\\d)*(.\\d*)?)";
        String number = "1.11";
        pattern = Pattern.compile(patternStr);
        System.out.println(pattern.matcher(number).matches());*/
        Pattern pattern;
        String patternStr = "^s_[a-z][a-zA-Z0-9]*$";
        String number = "m_reporterHtml";
        pattern = Pattern.compile(patternStr);
        System.out.println(pattern.matcher(number).matches());

    }
}
