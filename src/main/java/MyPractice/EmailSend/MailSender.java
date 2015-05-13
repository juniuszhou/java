package EmailSend;

/*
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


mail.smtp.auth=true
mail.host=CHELSMTP01
mail.transport.protocol=smtp
subject=Wall-E Report for %date%
from=Wall-E@expedia.com
username=pcworkos@expedia.com
password=
to=ChineseNLPWG@expedia.com;
 */

public class MailSender {
}

    /*
    private Properties createMailProperties() throws Exception
    {
        Properties props = new Properties();
        return props;
    }

    public void sendMail(String content, String... filePath) throws Exception
    {
        Properties props = createMailProperties();
        Session session = Session.getInstance(props);
        Message msg = new MimeMessage(session);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        String dateStr = sdf.format(date);
        msg.setSubject(ConfigParser.getValue("subject").replace("%date%", dateStr));

        attachFileToMail(msg, content, filePath);
        msg.setFrom(new InternetAddress(ConfigParser.getValue("from")));

        Transport transport = session.getTransport();
        transport.connect(ConfigParser.getValue("username"), ConfigParser.getValue("password").trim());

        transport.sendMessage(msg, parseStringToAddresses(ConfigParser.getValue("to")));
        transport.close();
    }
*/

    /*
    private Address[] parseStringToAddresses(String string) throws Exception
    {
        String[] receivers = string.split(";");
        Address[] addresses = new Address[receivers.length];
        int count = 0;
        for (String receiver : receivers)
        {
            addresses[count++] = new InternetAddress(receiver);
        }
        return addresses;
    }
*/
    /*
    private void attachFileToMail(Message msg, String content, String... filePath) throws Exception
    {
        if (filePath == null || filePath.length == 0)
        {
            return;
        }
        Multipart multipart = new MimeMultipart();

        MimeBodyPart mbpContent=new MimeBodyPart();
        mbpContent.setContent(content, "text/plain");
        multipart.addBodyPart(mbpContent);

        for (String eachFilePath : filePath)
        {
            MimeBodyPart mbp=new MimeBodyPart();
            FileDataSource fds=new FileDataSource(eachFilePath);
            mbp.setDataHandler(new DataHandler(fds));
            mbp.setFileName(fds.getName());

            multipart.addBodyPart(mbp);
        }
        msg.setContent(multipart);
    }
*/