package com.controller.ForgetPassword;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtpSendByEmail
{
	public  static void sendMail(String recipientEmail, String otp) 
	{
		// Recipient's email ID
		//        String recipientEmail  = recipientEmail;
		
        // Sender's email ID and password
        final String senderEmail = "pushkarmodi111@gmail.com";
        final String senderPassword = "jurg dkrl clel oyjo";

        // Generate OTP
//        String otp = generateOTP(6); // 6-digit OTP
//        System.out.println("Generated OTP: " + otp);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");

  
            String htmlMessage="<!DOCTYPE html>\r\n"
            		+ "<html>\r\n"
            		+ "<head>\r\n"
            		+ "    <title>Your OTP Code</title>\r\n"
            		+ "    <style>\r\n"
            		+ "        body {\r\n"
            		+ "            font-family: Arial, sans-serif;\r\n"
            		+ "            background-color: #f9f9f9;\r\n"
            		+ "            margin: 0;\r\n"
            		+ "            padding: 0;\r\n"
            		+ "        }\r\n"
            		+ "        .container {\r\n"
            		+ "            max-width: 600px;\r\n"
            		+ "            margin: 20px auto;\r\n"
            		+ "            background-color: #ffffff;\r\n"
            		+ "            padding: 20px;\r\n"
            		+ "            border: 1px solid #dddddd;\r\n"
            		+ "            border-radius: 8px;\r\n"
            		+ "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\r\n"
            		+ "        }\r\n"
            		+ "        .header {\r\n"
            		+ "            text-align: center;\r\n"
            		+ "            color: #444444;\r\n"
            		+ "        }\r\n"
            		+ "        .otp {\r\n"
            		+ "            font-size: 24px;\r\n"
            		+ "            font-weight: bold;\r\n"
            		+ "            color: #007BFF;\r\n"
            		+ "            text-align: center;\r\n"
            		+ "            margin: 20px 0;\r\n"
            		+ "        }\r\n"
            		+ "        .footer {\r\n"
            		+ "            text-align: center;\r\n"
            		+ "            font-size: 12px;\r\n"
            		+ "            color: #888888;\r\n"
            		+ "            margin-top: 20px;\r\n"
            		+ "        }\r\n"
            		+ "        .button {\r\n"
            		+ "            display: inline-block;\r\n"
            		+ "            margin: 20px 0;\r\n"
            		+ "            padding: 10px 20px;\r\n"
            		+ "            font-size: 16px;\r\n"
            		+ "            color: #ffffff;\r\n"
            		+ "            background-color: #007BFF;\r\n"
            		+ "            text-decoration: none;\r\n"
            		+ "            border-radius: 5px;\r\n"
            		+ "        }\r\n"
            		+ "    </style>\r\n"
            		+ "</head>\r\n"
            		+ "<body>\r\n"
            		+ "    <div class=\"container\">\r\n"
            		+ "        <h2 class=\"header\">Your OTP Code</h2>\r\n"
            		+ "        <p>Dear [UserName],</p>\r\n"
            		+ "        <p>Thank you for logging into our platform. Please use the OTP below to complete your login process:</p>\r\n"
            		+ "        <div class=\"otp\">["+otp+"]</div>\r\n"
            		+ "        <p>If you did not request this OTP, please ignore this email. For any assistance, feel free to contact our support team.</p>\r\n"
            		+ "        <p>Best regards,<br>Team [Royal Technosoft Private Limited]</p>\r\n"
            		+ "        <p class=\"footer\">This is an auto-generated email. Please do not reply to this email.</p>\r\n"
            		+ "    </div>\r\n"
            		+ "</body>\r\n"
            		+ "</html>\r\n"
            		+ "";
            message.setContent(htmlMessage, "text/html");
            
            Transport.send(message);
            System.out.println("Email sent successfully with OTP: " + otp);
        } catch (MessagingException e) {
            e.printStackTrace();
        }	
	}
	
    public static void main(String[] args) 
    {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Enter Your Email id : ");
    	String recipientEmail = sc.nextLine();
    	
    	sendMail(recipientEmail, generateOTP(6));
    	
        
    }

    public static String generateOTP(int length) 
    {
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) 
        {
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }
        return otp.toString();
    }
}
