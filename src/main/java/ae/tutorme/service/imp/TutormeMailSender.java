package ae.tutorme.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by almehairbi on 3/18/17.
 */

@Service
public class TutormeMailSender {

    @Autowired
    private MailSender mailSender;

    // for sending simple mails

    public void sendMail(String toAddress, String fromAddress, String subject, String msgBody) {

        SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
        crunchifyMsg.setFrom(fromAddress);
        crunchifyMsg.setTo(toAddress);
        crunchifyMsg.setSubject(subject);
        crunchifyMsg.setText(msgBody);
        mailSender.send(crunchifyMsg);
    }


}
