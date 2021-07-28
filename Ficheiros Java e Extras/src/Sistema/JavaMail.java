package Sistema;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;

/**
 * Classe responsavel pelo envio de email para o utilizador ao seu registo
 * @author Jorge Martins e Rodrigo Duro
 *
 */
public class JavaMail {
    /**
     * Metodo estatico da classe de envio do email
     * @param loginConta login da conta
     */
    public static void sendMail(String loginConta){
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        String accEmail = "testeJavaProjecto3@gmail.com";
        String password = "borpdtvyxkksjghs";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accEmail, password);
            }
        });

        Message message = prepareMessage(session, accEmail, loginConta);

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo estatico de preparação da mensagem para enviar por email
     * @param session sessao
     * @param accEmail email
     * @param loginConta login
     * @return Mensagem já preparada para envio
     */
    private static Message prepareMessage(Session session, String accEmail, String loginConta) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(accEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("jorge.martins2323@gmail.com"));
            message.setSubject("Registo de Conta em Java !");
            message.setText("Registo de Conta "+loginConta+" efectuado com sucesso!");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
