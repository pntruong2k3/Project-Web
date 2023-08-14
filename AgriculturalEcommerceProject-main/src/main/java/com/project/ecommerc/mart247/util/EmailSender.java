package com.project.ecommerc.mart247.util;

import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailSender {

//    @Value("${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}")
//    private String sendGridApiKey;
//
//    @Value("${simpthuy@gmail.com}")
//    private String senderEmail;

	/*
	 * private static final String SENDGRID_API_KEY =
	 * "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";
	 */
    public void sendEmail(String toEmail, String subject, String content) throws Exception {
        Email from = new Email("example@gmail.com");
        Email to = new Email(toEmail);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, emailContent);

        SendGrid sg = new SendGrid("example API Sendgrid");
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                System.out.println("Email sent successfully!");
            } else {
                throw new Exception("Failed to send email. Status code: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
