package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class web extends Thread {
	boolean run  = true;
	String username = "rajendra";
	String password = "Rajendra@123";
	String pstatus;
	String istatus;
	int t = 2000;
	WebLogin wl;
	public web(WebLogin w )
	{
		wl = w;
	}
	public void run()  {
		/*
		Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", easyhttps);*/
		istatus = "Not Reachable";
		pstatus = "Starting";
		int scount =0;
		while(run){
			
	
		if(!isInternetReachable())
		{
			t = 2000;
			istatus = "Not Reachable";
		pstatus = "Atempting to Login";
		wl.update();
		final WebClient webClient = new WebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		// Get the first page
		HtmlPage page1 = null;
		try {
			page1 = webClient.getPage("https://1.1.1.1/login.html");
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get the form that we are dealing with and within that form, 
		// find the submit button and the field that we want to change.
		
		if (page1 != null) {
			final HtmlForm form = page1.getForms().get(0);
			final HtmlButtonInput button = form.getInputByName("Submit");
			final HtmlTextInput usernameI = form.getInputByName("username");
			final HtmlPasswordInput passwordI = form.getInputByName("password");
			// Change the value of the text field
			usernameI.setValueAttribute(username);
			passwordI.setValueAttribute(password);
			// Now submit the form by clicking the button and get back the second page.
			try {
				final HtmlPage page2 = button.click();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			istatus = "Checking";
			pstatus = "Login Attempted";
			wl.update();
			
		}
		webClient.closeAllWindows();
		}
		else
		{
			istatus = "Reachable";
			pstatus = "Checking every " + (t/1000)+ "secs";
			
			wl.update();
			
			if(scount > 20)
				scount++;
			else
				t = 4000;
				
			
		}
		
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}


	public static boolean isInternetReachable()
	{
		try {
			//make a URL to a known source
			URL url = new URL("http://www.iitr.ac.in");

			//open a connection to that source
			HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

			//trying to retrieve data from the source. If there
			//is no connection, this line will fail
			Object objData = urlConnect.getContent();
			long s = urlConnect.getContentLengthLong();
			if(s < 1000)
				return false;
			System.out.println(s + "SUCCESSFUL INTERNET CONNECTION" + objData);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block

			System.out.println("CONNECTION FAILED");
			e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("CONNECTION FAILED");
			e.printStackTrace();
			return false;
		}
		return true;
	}


}
