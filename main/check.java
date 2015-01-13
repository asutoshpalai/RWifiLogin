package main;

import java.io.IOException;
import java.net.*;

class check {
	public static void main(String[] args) {
		/*boolean connectivity;
try {
URL url = new URL("http://www.wowmoron.wordpress.com/");
URLConnection conn = url.openConnection();
conn.connect();
connectivity = true;
System.out.println("Dude its Working Fine ! ");
} catch (Exception e) {
connectivity = false;
System.out.println("Not Working");


}*/
		for(int i = 0; i < 3; i++)
			System.out.println(isInternetReachable());

	}

	public static boolean isInternetReachable()
	{
		try {
			//make a URL to a known source
			URL url = new URL("www.google.co.in);

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
