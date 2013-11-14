package com.example.lithetyst;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.CalendarView.OnDateChangeListener;

import java.util.HashMap;
import java.util.Map;

public class TabActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	// Dagens datum	, eller valbart
	private static int chosenYear;
	private static int chosenMonth;
	private static int chosenDay; 
	
	// PagerAdapter tillhandah�ller Fragment f�r varje section 
	private SectionsPagerAdapter mSectionsPagerAdapter;

	// ViewPager �r v�rd f�r inneh�llet i varje section 
	private static ViewPager mViewPager;
	private static Map<Integer, Integer> schedule_rows;
    

	// Skapa tabbsidan
	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();	
		
		chosenYear = today.year;
		chosenMonth = today.month+1;
		chosenDay = today.monthDay;
		
		// Root-vyn/layout
		setContentView(R.layout.activity_tab);

		// Aktivera Actionbar - med tabbar
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// TODO: Logon ska leda hem
		actionBar.setHomeButtonEnabled(true);	
		// actionBar.hide(); // eller show

		// Initiera adaptern som vi deklarerade ovan som returnerar ett
		// Fragment f�r vart och ett av de tre sections som vi har (dag, vecka, m�nad).
		mSectionsPagerAdapter = 
				new SectionsPagerAdapter(getSupportFragmentManager());
		
		
		schedule_rows = new HashMap<Integer, Integer>();
		schedule_rows.put(8, R.id.tableRow1);
		schedule_rows.put(9, R.id.tableRow2);
		schedule_rows.put(10, R.id.tableRow3);
		schedule_rows.put(11, R.id.tableRow4);
		schedule_rows.put(12, R.id.tableRow5);
		schedule_rows.put(13, R.id.tableRow6);
		schedule_rows.put(14, R.id.tableRow7);
		schedule_rows.put(15, R.id.tableRow8);
		schedule_rows.put(16, R.id.tableRow9);
		schedule_rows.put(17, R.id.tableRow10);
		schedule_rows.put(18, R.id.tableRow11);
		schedule_rows.put(19, R.id.tableRow12);
		schedule_rows.put(20, R.id.tableRow13);
		schedule_rows.put(21, R.id.tableRow14);
		
		
		

		// F�nga viewPager och knyt den till Adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// N�r man swipar mellan olika sections, v�lj r�tt tab
		// Det g�r ocks� bra att trycka p�  tabbarna - ActionBar.Tab#select() 
		
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});
		 
		// For varje section (dag, vecka, m�nad), l�gg till en tab till v�r action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Skapa en tab med text som motsvarar titeln som definierats av adaptern
			// Ange f�r TabListener vilken acitivity vi �r inne i  (med nyckelordet this).
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		// TODO:
		// S�tt static defaulttab BEROENDE P� SETTINGS!!!
		int defaultTab = 2;
		mViewPager.setCurrentItem(defaultTab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	// TODO: ev. fler ikoner
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    /*
        	case R.id.action_about:
        		System.out.println("About");
        		return true;
        	case R.id.action_help:
        		System.out.println("Help");
        		return true;
       */
	        case R.id.action_settings_top:
	            //TODO: v�lj mellan second och "first" settings
	        	startSettingsActivity();
	            return true;
	        case R.id.action_settings:
	        	startSettingsActivity();
	            return true;
	        case R.id.action_settings_b:
	        	startSecondSettingsActivity();
	            return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void startSettingsActivity() {
		//
		Intent intent = new Intent(this, SettingsMenuActivity.class);
		startActivity(intent);
	}
	public void startSecondSettingsActivity() {
		//
		Intent intent = new Intent(this, SecondSettingsActivity.class);
		startActivity(intent);
	}
	

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// N�r vi valt en tab, se till att byta vy med ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// TODO
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// TODO
	}
	       
	// Returnerar ett Fragment som motsvarar en tab/section/vy/sida 
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
			// getItem skapar en instans av ett Fragment 
			// f�r den givna sidan som anv�ndaren valt.
			// Returnera ett CalendarSectionFragment (som definieras nedan)
			// med sidonumret som argument.
			Fragment fragment = new CalendarSectionFragment();
			Bundle args = new Bundle();
			args.putString(CalendarSectionFragment.ARG_SECTION_TITLE, getTitle(position));
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Visa 3 sidor totalt: dag, vecka, m�nad
			return 3;
		}
		
		public String getTitle(int pos) {
			String[] sectionTitles = {
					getString(R.string.title_section1), 
					getString(R.string.title_section2), 
					getString(R.string.title_section3) };
			// dag, vecka, m�nad
			return sectionTitles[pos];
		}

		// Namnge tabbarna/flikarna, med stora bokst�ver, h�mta fr�n strings.xml
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getTitle(position).toUpperCase(l);
			case 1:
				return getTitle(position).toUpperCase(l);
			case 2:
				return getTitle(position).toUpperCase(l);
			}
			return null;
		}
	}

	// TODO	
	// Ett Fragment representerar en section i appen	 
	@SuppressLint("ResourceAsColor")
	public static class CalendarSectionFragment extends Fragment {
		// Fragmentets argument som representerar rubriken f�r dess section 
		public static final String ARG_SECTION_TITLE = "section_title";
		private static enum SectionType { DAG, VECKA, MANAD; } // Flikar/tabbar
		
		// konstruktor
		public CalendarSectionFragment() {
			// TODO
		}

		// Skapa vyn
		@SuppressWarnings("deprecation")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,	
				Bundle savedInstanceState) {	    
			
			View rootView; // ska fyllas och returneras

			// Kolla vilken tab vi ska �ppna
			String chosenTab = getArguments().getString(ARG_SECTION_TITLE);
			// �vers�tt str�ngen till ett enumv�rde
		    SectionType currentType = SectionType.valueOf(chosenTab.toUpperCase(Locale.getDefault()));
		    // Switcha enum-v�rdet, �ppna r�tt vy/layout till r�tt flik
			switch (currentType) {
			case DAG:
				
				rootView = inflater.inflate(R.layout.activity_day,container, false);
				Events event_handler = new Events();
				ArrayList< Map<String, String> > events = event_handler.get_events(rootView.getContext());
				ArrayList<TableRow> tables = new ArrayList<TableRow>();
				TableRow.LayoutParams params = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT);
				
				for (int i = 0; i < events.size(); i++)
				{
					Map <String, String> event = events.get(i);
					if (event.get("start-year").equals(Integer.toString(chosenYear)) && 
						event.get("start-month").equals(Integer.toString(chosenMonth)) && 
						event.get("start-day").equals(Integer.toString(chosenDay)))
					{
						System.out.println("Ny event");
						tables.clear();
						Integer hours = Integer.parseInt(event.get("end-hour")) - Integer.parseInt(event.get("start-hour"));
						for (int y = 0; y < hours; y++)
						{
							tables.add((TableRow) rootView.findViewById( schedule_rows.get( Integer.parseInt(event.get("start-hour")) +y )));
						}
						
						TextView info = new TextView(rootView.getContext());
						
						info.setText(event.get("summary"));
						info.setBackgroundResource(R.color.redorange);
						info.setLayoutParams(params);
						info.setSingleLine(false);
						tables.get(0).addView(info);
						
						for (int y = 1; y < tables.size(); y++)
						{
							TextView block = new TextView(rootView.getContext());
							block.setBackgroundResource(R.color.redorange);
							block.setLayoutParams(params);
							
							tables.get(y).addView(block);
						}
					}
				
				}
						
				

				 // TODO: Parsa r�tt dag - OM CalendarActivity vill det
				 // TODO: R�tt dag finns i statiska variabeln chosenDate
				Calendar calendar = Calendar.getInstance();
				Date date = new Date(chosenYear, chosenMonth, chosenDay);
				calendar.setTime(date );
			    String[] days = new String[] { "THURSDAY", "FRIDAY", "SATURDAY",
			    		"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY"	};
			    String day = days[calendar.get(Calendar.DAY_OF_WEEK)-1];
				 
			    TextView dayTextView = (TextView) rootView.findViewById(R.id.textView2);
			    dayTextView.setText("Datum: " + chosenYear + "/" + chosenMonth
			    		+ "/" + chosenDay + "\n" + "(" + day + ")");
				 


				 
				 return rootView;
			case VECKA:
				 // TODO: Ta bort fragment_tab s� sm�ningom ***
				 // TODO: Byt ut mot veckovy
				 rootView = inflater.inflate(R.layout.fragment_tab,container, false);
				 
					TextView dateTextView = (TextView) rootView
					.findViewById(R.id.section_label);
					dateTextView.setText("Veckovy ej implementerad!");	
				 
				 return rootView;
			case MANAD:
				 rootView = inflater.inflate(R.layout.activity_calendar,container, false);
		 				 
				 ///
				 CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
				
		         calendarView.setOnDateChangeListener(new OnDateChangeListener() {
		               @Override
		               public void onSelectedDayChange(CalendarView view,
		            		   int year, int month, int day) {

		            	   chosenYear = year;
		            	   chosenMonth = month+1;
		            	   chosenDay = day;
		            	   
		            	   // Byt till dagfliken, r�tt datum
		            	   mViewPager.setCurrentItem(SectionType.DAG.ordinal()); 
		               }
		               });					 
				 return rootView;
			default:
				 // Hit borde vi inte komma 
				 // TODO: throw error?
				 rootView = inflater.inflate(R.layout.activity_calendar,container, false);
				 return rootView;
			}
		}
	}
}