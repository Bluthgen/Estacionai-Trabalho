package com.projeto.estacionai.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Email {
	
	String email = "estacionaiuem@gmail.com";
	String senha = "estacionai123";
	Session secao;
	
	public Email() {
		// TODO Auto-generated constructor stub
		inicializar(this.email, this.senha);
	}
	
	public void inicializar(String email, String senha)
	{
		Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        this.secao = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication(email, senha);
                         }
                    });
        
	}
	
	public void enviarMensagem(String destinatario, String assunto, String mensagem)
	{
		try {
			 
            Message message = new MimeMessage(this.secao);
            message.setFrom(new InternetAddress(this.email)); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                       .parse(destinatario);  

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);//Assunto
            message.setText(mensagem);
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

       } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
	}

}
