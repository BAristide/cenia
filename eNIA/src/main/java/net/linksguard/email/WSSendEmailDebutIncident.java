package net.linksguard.email;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

public class WSSendEmailDebutIncident {
	
	
	
	  public  void sendMailWebService(String recepient, String object, String serviceName, String titre, String description, String dateResolution ) { 
	      
		  sendMailNoProxyFromLinksGuardDomaine(recepient, object,serviceName, titre, description, dateResolution );
	    }
	
	
	  
	  
	  
	    private void sendMailNoProxyFromLinksGuardDomaine(String recepient, String object, String serviceName, String titre, String description,String dateResolution) {
	    	   
            Email email = EmailBuilder.startingBlank()
          .from("eNIA | LinksGuard", "alert@linksguard.net")
          .to("AlertRecepient", recepient)
        //  .withEmailAddressCriteria(EmailAddressCriteria.DEFAULT)      
          .withSubject(object)
          .withHTMLText("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"https://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
          		"<html xmlns=\"https://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\r\n" + 
          		"<!--[if gte mso 9]><xml>\r\n" + 
          		"<o:OfficeDocumentSettings>\r\n" + 
          		"<o:AllowPNG/>\r\n" + 
          		"<o:PixelsPerInch>96</o:PixelsPerInch>\r\n" + 
          		"</o:OfficeDocumentSettings>\r\n" + 
          		"</xml><![endif]-->\r\n" + 
          		"<title>Enquête de satisfaction client</title>\r\n" + 
          		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n" + 
          		"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
          		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\r\n" + 
          		"<meta name=\"format-detection\" content=\"telephone=no\">\r\n" + 
          		"<!--[if !mso]><!-->\r\n" + 
          		"<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800\" rel=\"stylesheet\">\r\n" + 
          		"<!--<![endif]-->\r\n" + 
          		"<style type=\"text/css\">\r\n" + 
          		"body {\r\n" + 
          		"	margin: 0 !important;\r\n" + 
          		"	padding: 0 !important;\r\n" + 
          		"	-webkit-text-size-adjust: 100% !important;\r\n" + 
          		"	-ms-text-size-adjust: 100% !important;\r\n" + 
          		"	-webkit-font-smoothing: antialiased !important;\r\n" + 
          		"}\r\n" + 
          		"img {\r\n" + 
          		"	border: 0 !important;\r\n" + 
          		"	outline: none !important;\r\n" + 
          		"}\r\n" + 
          		"p {\r\n" + 
          		"	Margin: 0px !important;\r\n" + 
          		"	Padding: 0px !important;\r\n" + 
          		"}\r\n" + 
          		"table {\r\n" + 
          		"	border-collapse: collapse;\r\n" + 
          		"	mso-table-lspace: 0px;\r\n" + 
          		"	mso-table-rspace: 0px;\r\n" + 
          		"}\r\n" + 
          		"td, a, span {\r\n" + 
          		"	border-collapse: collapse;\r\n" + 
          		"	mso-line-height-rule: exactly;\r\n" + 
          		"}\r\n" + 
          		".ExternalClass * {\r\n" + 
          		"	line-height: 100%;\r\n" + 
          		"}\r\n" + 
          		".em_defaultlink a {\r\n" + 
          		"	color: inherit !important;\r\n" + 
          		"	text-decoration: none !important;\r\n" + 
          		"}\r\n" + 
          		"span.MsoHyperlink {\r\n" + 
          		"	mso-style-priority: 99;\r\n" + 
          		"	color: inherit;\r\n" + 
          		"}\r\n" + 
          		"span.MsoHyperlinkFollowed {\r\n" + 
          		"	mso-style-priority: 99;\r\n" + 
          		"	color: inherit;\r\n" + 
          		"}\r\n" + 
          		" @media only screen and (min-width:481px) and (max-width:699px) {\r\n" + 
          		".em_main_table {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"}\r\n" + 
          		".em_wrapper {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"}\r\n" + 
          		".em_hide {\r\n" + 
          		"	display: none !important;\r\n" + 
          		"}\r\n" + 
          		".em_img {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"	height: auto !important;\r\n" + 
          		"}\r\n" + 
          		".em_h20 {\r\n" + 
          		"	height: 20px !important;\r\n" + 
          		"}\r\n" + 
          		".em_padd {\r\n" + 
          		"	padding: 20px 10px !important;\r\n" + 
          		"}\r\n" + 
          		"}\r\n" + 
          		"@media screen and (max-width: 480px) {\r\n" + 
          		".em_main_table {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"}\r\n" + 
          		".em_wrapper {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"}\r\n" + 
          		".em_hide {\r\n" + 
          		"	display: none !important;\r\n" + 
          		"}\r\n" + 
          		".em_img {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"	height: auto !important;\r\n" + 
          		"}\r\n" + 
          		".em_h20 {\r\n" + 
          		"	height: 20px !important;\r\n" + 
          		"}\r\n" + 
          		".em_padd {\r\n" + 
          		"	padding: 20px 10px !important;\r\n" + 
          		"}\r\n" + 
          		".em_text1 {\r\n" + 
          		"	font-size: 16px !important;\r\n" + 
          		"	line-height: 24px !important;\r\n" + 
          		"}\r\n" + 
          		"u + .em_body .em_full_wrap {\r\n" + 
          		"	width: 100% !important;\r\n" + 
          		"	width: 100vw !important;\r\n" + 
          		"}\r\n" + 
          		"}\r\n" + 
          		"</style>\r\n" + 
          		"</head>\r\n" + 
          		"\r\n" + 
          		"<body class=\"em_body\" style=\"margin:0px; padding:0px;\" bgcolor=\"#efefef\">\r\n" + 
          		"<table class=\"em_full_wrap\" valign=\"top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#efefef\" align=\"center\">\r\n" + 
          		"  <tbody><tr>\r\n" + 
          		"    <td valign=\"top\" align=\"center\"><table class=\"em_main_table\" style=\"width:700px;\" width=\"700\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\r\n" + 
          		"        <!--Header section-->\r\n" + 
          		"        <tbody><tr>\r\n" + 
          		"          <td style=\"padding:15px;\" class=\"em_padd\" valign=\"top\" bgcolor=\"#f6f7f8\" align=\"center\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\r\n" + 
          		"              <tbody><tr>\r\n" + 
          		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:12px; line-height:15px; color:#0d1121;\" valign=\"top\" align=\"center\">Nous avons un incidet sur la plateforme | "+serviceName+"</td>\r\n" + 
          		"              </tr>\r\n" + 
          		"            </tbody></table></td>\r\n" + 
          		"        </tr>\r\n" + 
          		"        <!--//Header section--> \r\n" + 
          		"       \r\n" + 
          		"        <!--Content Text Section-->\r\n" + 
          		"                 <tr>\r\n" + 
          		"          <td style=\"padding:35px 70px 30px;\" class=\"em_padd\" valign=\"top\" bgcolor=\"#efefef\" align=\"center\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\r\n" + 
          		"              <tbody><tr>\r\n" + 
          		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:16px; line-height:30px; color:#0d1121;\" valign=\"top\" align=\"center\">\r\n" + 
          		"				Titre de l'incident: "+titre+"</td>\r\n" + 
          		"              </tr>\r\n" + 
          		"              <tr>\r\n" + 
          		"                <td style=\"font-size:0px; line-height:0px; height:15px;\" height=\"15\">&nbsp;</td>\r\n" + 
          		"<!--—this is space of 15px to separate two paragraphs ---->\r\n" + 
          		"              </tr>\r\n" + 
          		"              <tr>\r\n" + 
          		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:18px; line-height:22px; color:#0d1121; letter-spacing:2px; padding-bottom:12px;\" valign=\"top\"  >\r\n" + 
          		"				Desciption: "+description+"</td>\r\n" + 
          		"              </tr>\r\n" + 
          		"              <tr>\r\n" + 
          		"                <td class=\"em_h20\" style=\"font-size:0px; line-height:0px; height:25px;\" height=\"25\">&nbsp;</td>\r\n" + 
          		"<!--—this is space of 25px to separate two paragraphs ---->\r\n" + 
          		"              </tr>\r\n" + 
          		"<tr>\r\n" + 
          		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:18px; line-height:22px; color:#0d1121; letter-spacing:2px; padding-bottom:12px;\" valign=\"top\" >\r\n" + 
          		"				Délais prévisionnel de résolution: "+dateResolution+"</td>\r\n" + 
          		"              </tr>\r\n" + 
          		"            </tbody></table></td>\r\n" + 
          		"        </tr>\r\n" + 
          		"\r\n" + 
          		"        <!--//Content Text Section--> \r\n" + 
          		"        <!--Footer Section-->\r\n" + 
          		"        <tr>\r\n" + 
          		"          <td style=\"padding:38px 30px;\" class=\"em_padd\" valign=\"top\" bgcolor=\"#f6f7f8\" align=\"center\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\r\n" + 
          		"              <tbody> \r\n" + 
          		"              <tr>\r\n" + 
          		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:11px; line-height:18px; color:#999999;\" valign=\"top\" align=\"center\">   <br>\r\n" + 
          		"                  © 2020 eNIA. All Rights Reserved.<br>\r\n" + 
          		"                  Pour ne plus recevoir les notification cliquer <a href=\"#\" target=\"_blank\" style=\"text-decoration:none; color:#999999;\">ici</a></td>\r\n" + 
          		"              </tr>\r\n" + 
          		"            </tbody></table></td>\r\n" + 
          		"        </tr>\r\n" + 
          		"        <tr>\r\n" + 
          		"          <td class=\"em_hide\" style=\"line-height:1px;min-width:700px;background-color:#ffffff;\"><img alt=\"\" src=\"images/spacer.gif\" style=\"max-height:1px; min-height:1px; display:block; width:700px; min-width:700px;\" width=\"700\" border=\"0\" height=\"1\"></td>\r\n" + 
          		"        </tr>\r\n" + 
          		"      </tbody></table></td>\r\n" + 
          		"  </tr>\r\n" + 
          		"</tbody></table>\r\n" + 
          		"<div class=\"em_hide\" style=\"white-space: nowrap; display: none; font-size:0px; line-height:0px;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div>\r\n" + 
          		"</body></html>") 
          .buildEmail();
          
          
          
          
          
          
          Mailer mailer = MailerBuilder
    .withSMTPServer("mail41.lwspanel.com",465, "alert@linksguard.net", "wA3!HHC_Xg")
          .withTransportStrategy(TransportStrategy.SMTPS)
       
       
       //   .withProperty("mail.smtp.sendpartial", true)
        // .withDebugLogging(true)
          .buildMailer();

mailer.sendMail(email);
          
 

System.out.println("Message sent...");
    }

}