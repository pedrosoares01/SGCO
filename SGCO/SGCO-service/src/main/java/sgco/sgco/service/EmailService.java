package sgco.sgco.service;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    public void enviarEmail(String destino, String assunto, String mensagemTexto) throws Exception {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String usuarioEmail = "seuemail@gmail.com";
        final String senhaEmail = "senhaApp";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioEmail, senhaEmail);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(usuarioEmail));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
        msg.setSubject(assunto);
        msg.setText(mensagemTexto);

        Transport.send(msg);
    }
}