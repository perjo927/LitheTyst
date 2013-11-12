package com.example.lithetyst;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.content.Context;
import android.os.StrictMode;

public class DownloadIcal 
{
	
	public static String get_ical_link(String group)
	{
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		try {
			String url = "https://se.timeedit.net/web/liu/db1/schema/s/s.html?tab=3&object=Group_" + group + "&type=studentgroup&p=0.n%2C12.n";
			Document document = Jsoup.connect(url).get();
			
			Elements ns = document.select("a#icalurl2");
			
			String ical_link = ns.get(0).attributes().get("href");
			return ical_link;
		}
		catch (MalformedURLException e) 
		{
			return "fail_url";
		}
		catch (IOException e) 
		{
			return "fail_io";
		} 
	}
	
	
	public static boolean save_file(String url_string,Context ctx)
	{
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		try
		{
			FileOutputStream fos = ctx.openFileOutput("schema.ical", Context.MODE_PRIVATE);
		    
		    // Create a URL for the desired page
		    
		    URL url = new URL(url_string);
	
		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String line;
		    while ((line = in.readLine()) != null) 
		    {
		    	fos.write((line+"!SEP").getBytes());
		    }
		    fos.close();
		    in.close();
		    return true;
		}
		catch (MalformedURLException e) 
		{
			return false;
		} 
		catch (IOException e) 
		{
			return false;
		}	
	}
}
