package cal;
/* returns the current temperature at your location. If the user's zipcode 
 * passed in (as a string) it does the same thing but about 2 seconds quicker
 * this is a heavily modified version of Professor Iacona's WebPageGetter 
 */

/* Here is an ideal point to indicate that we absolutely made portions of 
 * this with intent of greater funcionality. In this case, we would only 
 * get a user's location the first time or maybe store it in the database
 * but the point is we have two getTemp() methods and we only really need 
 * to use the slower version one time, when a user creates their account
 */

import java.io.*;
import java.net.*;

public class getTemp{  
	public static void main(String[] args){
		System.out.println(getTemperature());
	}
	public static String getTemperature(String zip){
			StringBuffer buffer = new StringBuffer();
			String theUrl = "https://www.bing.com/search?q=" + zip + "+weather&form=EDGEAR&qs=WA&cvid=8921203ba63844f99e376494cc5fe5f6&pq=" + zip + "%20weather";
		try {
			 URL gotoUrl = new URL(theUrl);
			 InputStreamReader isr = new InputStreamReader(gotoUrl.openStream());
			 BufferedReader inStream = new BufferedReader(isr);		            
			 String inputLine;

			 while ((inputLine = inStream.readLine()) != null && !buffer.toString().contains("on Bing")){
				 buffer.append(inputLine + "\n");
			 }

			 StringBuilder loc = new StringBuilder();
			 loc.append(buffer.charAt(buffer.indexOf("on Bing") + 12)); 
			 loc.append(buffer.charAt(buffer.indexOf("on Bing") + 13));

			 return new String(loc + "\u00B0 F");

		} catch (IOException e) {
			e.printStackTrace();
		}	

		return "error";
	}      

	private static String getUserCity(){
		System.setProperty("http.agent", "Chrome");
		StringBuffer buffer = new StringBuffer();
		String theUrl = "https://www.iplocation.net";
    	try {
	         URL gotoUrl = new URL(theUrl);
	         InputStreamReader isr = new InputStreamReader(gotoUrl.openStream());
	         BufferedReader inStream = new BufferedReader(isr);		            
	         String inputLine;
	         
	         while ((inputLine = inStream.readLine()) != null && !buffer.toString().contains("</td></tr></tbody><thead><tr><th>ISP")){
	        	 buffer.append(inputLine + "\n");
	         }
	         
	         StringBuilder loc = new StringBuilder();
	         
	         for(int i = 94; buffer.charAt(buffer.indexOf("</td><td>United States") + i) != '<'; i++)
	        		 loc.append(buffer.charAt(buffer.indexOf("</td><td>United States") + i));

	         return new String(loc);
	         
    	} catch (IOException e) {
    		e.printStackTrace();
    	}	
    	
    	return "error";
    }      
	
	public static String getTemperature(){
		System.setProperty("http.agent", "Chrome");
		StringBuffer buffer = new StringBuffer();
		String theUrl;
		if (getUserCity().contains(" "))
			 theUrl = "https://www.wunderground.com/cgi-bin/findweather/getForecast?query=" + getUserCity().substring(0, getUserCity().indexOf(' ')) + "+" + getUserCity().substring(getUserCity().indexOf(' ') + 1, getUserCity().length());
		else
			 theUrl = "https://www.wunderground.com/cgi-bin/findweather/getForecast?query=" + getUserCity();
		System.out.println("theurl = " + theUrl);
    	try {
	         URL gotoUrl = new URL(theUrl);
	         InputStreamReader isr = new InputStreamReader(gotoUrl.openStream());
	         BufferedReader inStream = new BufferedReader(isr);		            
	         String inputLine;
	         
	         while ((inputLine = inStream.readLine()) != null && !buffer.toString().contains("&deg")){
	        	 buffer.append(inputLine + "\n");
	         }
	         
	         StringBuilder cur = new StringBuilder();
	         cur.append(buffer.charAt(buffer.indexOf("&deg") - 4));
	         cur.append(buffer.charAt(buffer.indexOf("&deg") - 3));
	         System.out.println(cur);
	         return new String(cur + "\u00B0 F");
	         
    	} catch (IOException e) {
    		e.printStackTrace();
    	}	
    	
    	return "error";
	}
}



