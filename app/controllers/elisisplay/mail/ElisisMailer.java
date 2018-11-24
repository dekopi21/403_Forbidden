package controllers.elisisplay.mail;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import play.libs.Mail;
import play.mvc.Mailer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Bienvenu on 25/08/2018 in demande_d_installation.
 */
public class ElisisMailer extends Mailer {

    public static final String defaultMailerHost = EmailStaticObjects.gmailHostname;

    public static final String defaultMailerPort = EmailStaticObjects.emailSmtpsPort;

    /**
     * Définit si le service de Mail est en service ou pas.
     */
    public static boolean isEnabled = true;

    /**
     * Définit si l'envoi des Emails est sécurisé ou pas.
     */
    public static boolean starttlsEnabled = true;

    public boolean sendEmail(String body, String subject, String emailAddressTo, String emailAddressFrom) {
        boolean emailSent = false;

        if (emailAddressFrom == null || StringUtils.isEmpty(emailAddressFrom)) {
            return false;
        }

        if (emailAddressTo == null || StringUtils.isEmpty(emailAddressTo)) {
            return false;
        }

        if (subject == null || StringUtils.isEmpty(subject)) {
            return false;
        }

        if (body == null || StringUtils.isEmpty(body)) {
            return false;
        }

        try {
            Email email = new SimpleEmail();
            email.setHostName(defaultMailerHost);
            email.setSmtpPort(Integer.valueOf(defaultMailerPort));
            email.setSSLOnConnect(true);
            email.setFrom(emailAddressFrom);
            email.setSubject(subject);
            email.setMsg(body);
            email.addTo(emailAddressTo);
            email.send();
            emailSent = true;
            //TODO Log
        } catch (EmailException e) {
            //TODO Log
        }
        return emailSent;
    }


    public boolean sendMail(List<String> receivers, String subject, String content, boolean isHtmlMsg,
                            String username, String password, String sender) {

        if (!isEnabled) {
            //Log this.
            return false;
        }

        Email email = new HtmlEmail();
        email.setHostName(defaultMailerHost);
        email.setStartTLSEnabled(starttlsEnabled);
        if (starttlsEnabled) {
            email.setSslSmtpPort(EmailStaticObjects.emailMsaPort);
        } else {
            email.setSmtpPort(Integer.valueOf(EmailStaticObjects.emailSmtpsPort));
        }

        if (username != null && username.trim().length() > 0) {
            email.setAuthentication(username, password);
        }

        //email.setDebug(true);
        try {
            for (String receiver : receivers) {
                email.addTo(receiver);
            }

            email.setFrom(sender);
            email.setSubject(subject);
            email.setCharset("UTF-8");
            if (isHtmlMsg) {
                ((HtmlEmail) email).setHtmlMsg(content);
            } else {
                ((HtmlEmail) email).setTextMsg(content);
            }
            email.send();
            email.getMailSession();

        } catch (EmailException e) {
            //Log this.
            return false;
        }

        return true;
    }

    private void sendEmail(String hostName, int smtpPort,
                           DefaultAuthenticator authenticator, String sender, String subject,
                           String message, String receiver) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthenticator(authenticator);
        email.setFrom(sender);
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(receiver);
        email.send();
    }

    public void sendEmail(final EmailData emailData, String username, String password) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(defaultMailerHost);
            email.setSmtpPort(Integer.parseInt(defaultMailerPort));
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(starttlsEnabled);
            email.setFrom(emailData.getAddressFrom());
            email.setSubject(emailData.getSubject());
            email.setMsg(emailData.getMessageContent());
            email.addTo(emailData.getAddressTo());
            email.send();
        } catch (EmailException e) {
            //Log it.
        }
    }

    protected void send(String mailAddress, String title, String content, String username,
                        String password, String from, String fromname, String mailAddressEndSeparator) {
        if (StringUtils.isBlank(mailAddress)) {
            return;
        }

        try {
            Email email = new HtmlEmail();
            email.setHostName(defaultMailerHost);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSmtpPort(Integer.parseInt(defaultMailerPort));
            email.setFrom(from, fromname);
            email.setSubject(title);
            email.setMsg(content);
            email.addTo(mailAddress.split(mailAddressEndSeparator));
            email.send();
        } catch (Exception e) {
            //Log it.
        }
    }

    //Examples

    public static void sendSimpleTextEmail(String host, int port, String from, String username,
                                           String password, String subject, String msg, String addTo) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(true);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(addTo);
            email.send();
        } catch (EmailException e) {
            //Do something.
        }
    }

    public static void sendEmailWithLocalAttachment(String host, int port, String from, String username,
                                                    String password, String subject, String msg, String addTo,
                                                    String attachmentPath, String attachmentDescription,
                                                    String attachmentName, String addToName, String fromName) {
        try {
            // Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(attachmentPath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(attachmentDescription);
            attachment.setName(attachmentName);

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(host);
            email.addTo(addTo, addToName);
            email.setFrom(from, fromName);
            email.setSubject(subject);
            email.setMsg(msg);

            // add the attachment
            email.attach(attachment);

            // send the email
            email.send();
        } catch (EmailException e) {
            //Do something.
        }
    }

    /*
    When the message is sent, the file will be downloaded and attached to the message automatically.
     */
    public static void sendEmailWithOnlineAttachment(String host, int port, String from, String username,
                                                     String password, String subject, String msg, String addTo,
                                                     String attachmentUrl, String attachmentDescription,
                                                     String attachmentName, String addToName, String fromName) {
        try {
            // Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setURL(new URL(attachmentUrl));
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(attachmentDescription);
            attachment.setName(attachmentName);

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(host);
            email.addTo(addTo, addToName);
            email.setFrom(from, fromName);
            email.setSubject(subject);
            email.setMsg(msg);

            // add the attachment
            email.attach(attachment);

            // send the email
            email.send();
        } catch (EmailException | MalformedURLException e) {
            //Do something.
        }
    }

    public static void sendHtmlFormattedEmail(String host, int port, String from, String username,
                                              String password, String subject, String msg, String addTo,
                                              String attachmentUrl, String attachmentDescription,
                                              String attachmentName, String addToName, String fromName,
                                              String alternativeMsg) {
        try {
            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setHostName(host);
            email.addTo(addTo, addToName);
            email.setFrom(from, fromName);
            email.setSubject(subject);

            // embed the image and get the content id
            URL url = new URL(attachmentUrl);
            String cid = email.embed(url, attachmentName);

            // set the html message
            email.setHtmlMsg("<html> " + attachmentName + " - <img src=\"cid:" + cid + "\"></html>");

            // set the alternative message
            email.setTextMsg(alternativeMsg);

            // send the email
            email.send();
        } catch (EmailException | MalformedURLException e) {
            //Do something.
        }
    }

    /*
    The previous example showed how to create a HTML email with embedded images but you need to
    know all images upfront which is inconvenient when using a HTML email template.
    The ImageHtmlEmail helps you solving this problem by converting all external images to inline images.
     */

    public static void sendHtmlTemplatedEmail(String host, int port, String from, String username,
                                              String password, String subject, String msg, String addTo,
                                              String attachmentUrl, String attachmentDescription,
                                              String attachmentName, String addToName, String fromName,
                                              String alternativeMsg, String applicationBaseUrl,
                                              String imageRelativeUrl) {
        try {
            // load your HTML email template
            String htmlEmailTemplate = ".... <img src=\" " + applicationBaseUrl + imageRelativeUrl + "\"> ....";

            // define you base URL to resolve relative resource locations
            URL url = new URL(applicationBaseUrl);

            // create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName(host);
            email.addTo(addTo, addToName);
            email.setFrom(from, fromName);
            email.setSubject(subject);

            // set the html message
            email.setHtmlMsg(htmlEmailTemplate);

            // set the alternative message
            email.setTextMsg(alternativeMsg);

            // send the email
            email.send();
        } catch (EmailException | MalformedURLException e) {
            //Do something.
        }
    }


    /*
    Debugging

    The JavaMail API supports a debugging option that will can be very useful if you run into problems.
    You can activate debugging on any of the mail classes by calling setDebug(true). The debugging
    output will be written to System.out.

    Sometimes you want to experiment with various security setting or features of commons-email.
    A good starting point is the test class EmailLiveTest and EmailConfiguration which are used
    for testing commons-email with real SMTP servers.

    Authentication

    If you need to authenticate to your SMTP server, you can call the
    setAuthentication(userName,password) method before sending your email.
    This will create an instance of DefaultAuthenticator which will be used by the JavaMail
    API when the email is sent. Your server must support RFC2554 in order for this to work.

    You can perform a more complex authentication method such as displaying a dialog box
    to the user by creating a subclass of the javax.mail.Authenticator object. You will
    need to override the getPasswordAuthentication() method where you will handle collecting
    the user's information. To make use of your new Authenticator class, use the Email.setAuthenticator method.

    Security

    Nowadays you should not use plain SMTP protocol when using public SMTP servers
    but there is a some confusion regarding the available options.

    Two commons options are using

    STARTTLS on port 25
    SSL on port 465

    The following definitions were taken from Wikipedia

    STARTTLS is an extension to plain text communication protocols, which offers a way to
    upgrade a plain text connection to an encrypted (TLS or SSL) connection instead of using a
    separate port for encrypted communication.
    Transport Layer Security (TLS) and its predecessor, Secure Sockets Layer (SSL), are
    cryptographic protocols that provide communication security over the Internet.TLS and
    SSL encrypt the segments of network connections above the Transport Layer, using asymmetric
    cryptography for key exchange, symmetric encryption for privacy, and message authentication
    codes for message integrity.

    In addition you can force the following security checks (which are disabled by default)

    When using a secured transport (STARTTLS or SSL) you can force validating the server's
    certificate by calling Email.setSSLCheckServerIdentity(true).
    Enforce using STARTTLS by calling Email.setStartTLSRequired(true)

    Handling Bounced Messages

    Normally, messages which cannot be delivered to a recipient are returned to the sender
    (specified with the from property). However, in some cases, you'll want these to be sent
    to a different address. To do this, simply call the setBounceAddress(emailAddressString)
    method before sending your email.

    Technical notes: When SMTP servers cannot deliver mail, they do not pay any attention to the contents
    of the message to determine where the error notification should be sent. Rather, they refer to the SMTP
    "envelope sender" value. JavaMail sets this value according to the value of the mail.smtp.from property
    on the JavaMail Session. (Commons Email initializes the JavaMail Session using System.getProperties())
    If this property has not been set, then JavaMail uses the "from" address. If your email bean has the
    bounceAddress property set, then Commons Email uses it to set the value of mail.smtp.from when the
    Session is initialized, overriding any other value which might have been set.

    Note: This is the only way to control the handling of bounced email. Specifically, the "Errors-to:"
    SMTP header is deprecated and cannot be trusted to control how a bounced message will be handled.
    Also note that it is considered bad practice to send email with an untrusted "from" address unless
    you also set the bounce address. If your application allows users to enter an address which is used
    as the "from" address on an email, you should be sure to set the bounce address to a known good address.

     */

    public static void sendPlaySimpleEmail() {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setFrom("sender@zenexity.fr");
            email.addTo("recipient@zenexity.fr");
            email.setSubject("subject");
            email.setMsg("Message");
            Mail.send(email);
        } catch (EmailException e) {
            //Do something.
        }
    }

    public static void sendPlayHtmlEmail() {
        try {
            HtmlEmail email = new HtmlEmail();
            email.addTo("info@lunatech.com");
            email.setFrom("sender@lunatech.com", "Nicolas");
            email.setSubject("Test email with inline image");
            // embed the image and get the content id
            URL url = new URL("http://www.zenexity.fr/wp-content/themes/images/logo.png");
            String cid = email.embed(url, "Zenexity logo");
            // set the html message
            email.setHtmlMsg("<html>Zenexity logo - <img src=\"cid:" + cid + "\"></html>");
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML, too bad :(");
        } catch (EmailException | MalformedURLException e) {
            //Do something.
        }
    }
}
