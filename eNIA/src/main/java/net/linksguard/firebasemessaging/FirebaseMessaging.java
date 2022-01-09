package net.linksguard.firebasemessaging;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
public class FirebaseMessaging {
	
	public void sendFireBaseMessaging(String title, String messagBody){
        
        
        
        try {
            String url="https://fcm.googleapis.com/fcm/send";
            URL object=new URL(url);
            
            HttpsURLConnection con = (HttpsURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Authorization", "key=AAAA4PVWNBo:APA91bFcSkDMX1H5K3myDlpVFrs6sQ2YiJKGHw_dKLIqANbMQjDOSIhQoeSedwvVRP_glkpcKGStInEY1LrK2F-pZHt-UUrml4dTBA8tF1MUgSx556vLX_-Bh08m68kU_00uaRY3P93j");
            con.setRequestProperty("Content-Type", "application/json"); 
            con.setRequestMethod("POST");
            
            
            JSONObject insideDataBodyMessage = new JSONObject();
            JSONObject notificationBodyMessage = new JSONObject();
            
            System.out.println("***********  FIRE INSIDE ****************");
            
            notificationBodyMessage.put("title", title);
            notificationBodyMessage.put("body", messagBody);
            
            insideDataBodyMessage.put("click_action", "FLUTTER_NOTIFICATION_CLICK");
            insideDataBodyMessage.put("id", "1");
            insideDataBodyMessage.put("status", "done");
            
            JSONObject notificationMessage = new JSONObject();
            notificationMessage.put("notification", notificationBodyMessage);        
            notificationMessage.put("priority", "high");
            notificationMessage.put("data", insideDataBodyMessage);
            notificationMessage.put("to", "/topics/all");
       
            //System.out.println("***********  MESSGE  ****************" + notificationMessage.toString());
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(notificationMessage.toString());
            wr.flush();
            
//display what returns the POST request

StringBuilder sb = new StringBuilder(); 
BufferedReader br = new BufferedReader(
        new InputStreamReader(con.getInputStream(), "utf-8"));
String line = null;
while ((line = br.readLine()) != null) {
    sb.append(line + "\n");
}
br.close();
System.out.println("" + sb.toString()); 
        } catch (Exception ex) {
        	
        	System.out.println("***********  FIRE ERROR ****************" + ex.toString());
            // mlogging.baaView("Send SMS erreur : " + ex.getMessage(), 2);
        }
        
    }

}
