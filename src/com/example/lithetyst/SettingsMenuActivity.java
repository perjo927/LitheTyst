package com.example.lithetyst;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsMenuActivity extends Activity {
	
	private Spinner spinner1, spinner2;
	private ToggleButton togglebutton;
	//private Button btnSubmit;
	
	 private TextView settings, view_options, vibration_options, language, edit;	
	 private Locale myLocale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 System.out.println("menuactivity#1");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_menu);
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
		
		Intent intent = getIntent();
			
		//När man trycker på tillbakapilen högst upp t höger ska den backa hemåt
	    //getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    /* ****** För att ändra språk, ej klart
	    this.settings = (TextView)findViewById(R.id.textView1);
	    this.view_options = (TextView)findViewById(R.id.textView2);
	    this.vibration_options = (TextView)findViewById(R.id.textView3);
	    this.language = (TextView)findViewById(R.id.textView4);
	    this.edit = (TextView)findViewById(R.id.textView5);
	    this.spinner1 = (Spinner)findViewById(R.id.spinner1);
	    this.spinner2 = (Spinner)findViewById(R.id.spinner2);
		
		  loadLocale();
		  */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.settings_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
        	case R.id.action_calendar_top:
        		startTabActivity();
        		return true;
	        case R.id.action_calendar:
	        	startTabActivity();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void startTabActivity() {
		Intent intent = new Intent(this, TabActivity.class);
		startActivity(intent);
	}
	
	public void startSyncActivity() {
		Intent intent = new Intent(this, SyncActivity.class);
		startActivity(intent);
	}
	
	  public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		//spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		//spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	  }
	 
	  // get the selected dropdown list value
	  public void addListenerOnButton() {
	 
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		togglebutton = (ToggleButton) findViewById(R.id.togglebutton);
		//btnSubmit = (Button) findViewById(R.id.btnSubmit);
//	 
//		btnSubmit.setOnClickListener(new OnClickListener() {
//	
//		  public void onClick(View v) {
//	 
//		    Toast.makeText(SettingsMenuActivity.this,
//			"OnClickListener : " + 
//	                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
//	                "\nToggle : " + (togglebutton.isChecked()) + 
//	                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
//				Toast.LENGTH_SHORT).show();
//		    
//		   /* ***** För att ändra språk - ej klart
//		    String lang = "";
//		    if (String.valueOf(spinner1.getSelectedItem()).equals("Engelska")){
//		    	lang = "en";
//		    	changeLang(lang);
//		    }
//		    else if (String.valueOf(spinner1.getSelectedItem()).equals("Svenska")){
//		    	lang = "sv";
//		    	changeLang(lang);
//		    } */
//		  }
//	 
//		});

	  }
	  
	  public void onToggleClicked(View view) {	  
		  ToggleButton toggle = (ToggleButton) findViewById(R.id.togglebutton);
		  toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				  if (isChecked) {
					  	// The toggle is enabled
						 //Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		          } else {
		              // The toggle is disabled
		          }
		      }
		  });
		    // Is the toggle on?
		    boolean on = ((ToggleButton) view).isChecked();
		    
		    if (on) {
		        // Enable vibrate    	
		    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	// Vibrate for 500 milliseconds
		    	v.vibrate(500);
		    } else {
		        // Disable vibrate
		}
	}
	  
	  /* ****** För att ändra språk - ej klart
	  public void changeLang(String lang)
	  {
	      if (lang.equalsIgnoreCase(""))
	       return;
	      myLocale = new Locale(lang);
	      saveLocale(lang);
	      Locale.setDefault(myLocale);
	      android.content.res.Configuration config = new android.content.res.Configuration();
	      config.locale = myLocale;
	      getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	      updateTexts();
	  }
	  public void saveLocale(String lang)
	  {
	      String langPref = "Language";
	      SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
	      SharedPreferences.Editor editor = prefs.edit();
	      editor.putString(langPref, lang);
	      editor.commit();
	  }
	  
	  public void loadLocale()
	  {
	      String langPref = "Language";
	      SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
	      String language = prefs.getString(langPref, "");
	      changeLang(language);
	  }
	  
	  private void updateTexts()
	  {
		  settings.setText(R.string.title);
		  view_options.setText(R.string.view_options_sve);
		  vibration_options.setText(R.string.view_options_sve);
		  view_options.setText(R.string.vibration_options);
		  language.setText(R.string.language);
		  edit.setText(R.string.edit);
		  
		  /*
		  spinner2.setText(R.string.day);
		  
		  
		  //String day = spinner2.getItemAtPosition(0).toString();
		  //System.out.println(day);
		  String [] views_array;
		  Resources r = getResources();
		    views_array = r.getStringArray(R.array.views_array);
		  setContentView(R.layout.activity_settings_menu);
		  views_array[0].setText(R.string.day);
		  /*
		  spinner2.setText(R.string.spinner2.getItemAtPosition(0).toString());
		
		  spinner2.setText(R.string.view_options.getItemAtPosition(0).toString());
		  
		  TextView detail = (Spinner)findViewById(R.id.spinner2);
		  detail.setText(views_array[0]);
		 */
		  //spinner2.setText(R.string.[0]);
		  
	
	  //}  
	
	  
	} //End of class
	
	


