package tda.darkarmy.redditclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.model.NotificationEmail;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail) throws SpringRedditException {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("jaccob@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody(),notificationEmail.getVerificationLink()), true);
        };

        try{
            javaMailSender.send(messagePreparator);
            log.info("Mail Sent!");
        }catch (MailException e){
            throw new SpringRedditException("Exception occurred when sending mail to "+notificationEmail.getRecipient()+" : "+e.getMessage());
        }
    }
}
