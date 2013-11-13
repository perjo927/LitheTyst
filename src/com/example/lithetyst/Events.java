package com.example.lithetyst;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;


public class Events
{
	
	public ArrayList< Map<String, String> > get_events(Context ctx)
	{
		if (!fileExists("schema.ical", ctx))
		{
			return null;
		}	
    
        try {
        	ArrayList< Map<String, String> > events = new ArrayList< Map<String, String> >();
        	Map <String, String> event = null;
        	
			
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(ctx.openFileInput("schema.ical")));
		    String line;
		    String data = "";
		    while ((line = inputReader.readLine()) != null) 
		    {
		        data += line;
		    }
		    inputReader.close();
		    
		    String pattern = "([A-Z-]+:)(.*)";
		    String last_entry = null;
		    Pattern reg = Pattern.compile(pattern);
		    Matcher m;
		    
		    String[] temp = data.split("!SEP");
		    for (int i = 0; i < temp.length; i++)
		    {
		    	m = reg.matcher(temp[i]);
		    	if (m.find())
		    	{
		    		String id = m.group(1).trim();
		    		if (id.equals("BEGIN:"))
		    		{
		    			String type = m.group(2).trim();
		    			if (type.equals("VCALENDAR"))
		    			{
		    			}
		    			else if (type.equals("VEVENT"))
		    			{
		    				event = new HashMap<String, String>();
		    			}
		    		}
		    		else if (id.equals("DTSTART:"))
		    		{
		    			String time = m.group(2).trim();
		    			event.put("start-year",time.substring(0,4));
		    			event.put("start-month",time.substring(4,6));
		    			event.put("start-day",time.substring(6,8));
		    			event.put("start-hour",Integer.toString(Integer.parseInt(time.substring(9,11))+1));
		    			event.put("start-minute",time.substring(11,13));
		    		}
		    		else if (id.equals("DTEND:"))
		    		{
		    			String time = m.group(2).trim();
		    			event.put("end-year",time.substring(0,4));
		    			event.put("end-month",time.substring(4,6));
		    			event.put("end-day",time.substring(6,8));
		    			event.put("end-hour",Integer.toString(Integer.parseInt(time.substring(9,11))+1));
		    			event.put("end-minute",time.substring(11,13));
		    		}
		    		else if (id.equals("SUMMARY:"))
		    		{
		    			String summery = m.group(2).trim().replaceAll("\\\\","");
		    			event.put("summary", summery);
		    			last_entry = "summary";
		    		}
		    		else if (id.equals("LOCATION:"))
		    		{
		    			event.put("location", m.group(2).trim());
		    			last_entry = "location";
		    		}
		    		else if (id.equals("END:"))
		    		{
		    			String type = m.group(2).trim();
		    			if (type.equals("VCALENDAR"))
		    			{
		    			}
		    			else if (type.equals("VEVENT"))
		    			{
		    				boolean inserted = false;
		    				//events.add(event);
		    				for (int y = 0; y < events.size(); y++)
		    				{
		    					long event1 = Long.parseLong(event.get("start-year")+event.get("start-month")+event.get("start-day")+event.get("start-hour")+event.get("start-minute"));
		    					long event2 = Long.parseLong(events.get(y).get("start-year")+events.get(y).get("start-month")+events.get(y).get("start-day")+events.get(y).get("start-hour")+events.get(y).get("start-minute"));
		    					
		    					if (event1 < event2)
		    					{
		    						events.add(y,event);
		    						inserted = true;
		    						break;
		    					}
		    				}
		    				if (!inserted)
		    				{
		    					events.add(event);
		    				}
		    			}
		    		}
		    	}
		    	
		    	else
		    	{
		    		event.put(last_entry, event.get(last_entry) + temp[i].trim().replaceAll("\\\\", ""));
		    	}
		    }
		    return events;
		    
		} 
		catch (MalformedURLException e) 
		{
		} 
		catch (IOException e) 
		{
		}
        return null;
	}
	
	private boolean fileExists(String fname, Context ctx)
	{
	    File file = ctx.getFileStreamPath(fname);
	    return file.exists();
	}
}
