package com.example.lithetyst;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.CalendarView.OnDateChangeListener;

public class TabActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	private static String chosenDate = "00-00-00"; // TODO: dagens datum
	// TODO: Dagens datum
	private static int chosenYear = 2013;
	private static int chosenMonth = 11;
	private static int chosenDay = 12;
    //public final static String YEAR = "com.example.lithetyst.YEAR";
	
	// PagerAdapter tillhandahåller Fragment för varje section 
	private SectionsPagerAdapter mSectionsPagerAdapter;

	// ViewPager är värd för innehållet i varje section 
	private static ViewPager mViewPager;  
    

	// Skapa tabbsidan
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Root-vyn/layout
		setContentView(R.layout.activity_tab);

		// Aktivera Actionbar - med tabbar
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// TODO: Logon ska leda hem
		actionBar.setHomeButtonEnabled(true);	
		// actionBar.hide(); // eller show

		// Initiera adaptern som vi deklarerade ovan som returnerar ett
		// Fragment för vart och ett av de tre sections som vi har (dag, vecka, månad).
		mSectionsPagerAdapter = 
				new SectionsPagerAdapter(getSupportFragmentManager());

		// Fånga viewPager och knyt den till Adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// När man swipar mellan olika sections, välj rätt tab
		// Det går också bra att trycka på  tabbarna - ActionBar.Tab#select() 
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For varje section (dag, vecka, månad), lägg till en tab till vår action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Skapa en tab med text som motsvarar titeln som definierats av adaptern
			// Ange för TabListener vilken acitivity vi är inne i  (med nyckelordet this).
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		// TODO:
		// Sätt static defaulttab BEROENDE PÅ SETTINGS!!!
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
	            //TODO: välj mellan second och "first" settings
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
		// När vi valt en tab, se till att byta vy med ViewPager.
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
			// för den givna sidan som användaren valt.
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
			// Visa 3 sidor totalt: dag, vecka, månad
			return 3;
		}
		
		public String getTitle(int pos) {
			String[] sectionTitles = {
					getString(R.string.title_section1), 
					getString(R.string.title_section2), 
					getString(R.string.title_section3) };
			// dag, vecka, månad
			return sectionTitles[pos];
		}

		// Namnge tabbarna/flikarna, med stora bokstäver, hämta från strings.xml
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
	public static class CalendarSectionFragment extends Fragment {
		// Fragmentets argument som representerar rubriken för dess section 
		public static final String ARG_SECTION_TITLE = "section_title";
		private static enum SectionType { DAY, WEEK, MONTH; } // Flikar/tabbar
		
		// konstruktor
		public CalendarSectionFragment() {
			// TODO
		}

		// Skapa vyn
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,	
				Bundle savedInstanceState) {	    
			
			View rootView; // ska fyllas och returneras

			// Kolla vilken tab vi ska öppna
			String chosenTab = getArguments().getString(ARG_SECTION_TITLE);
			// Översätt strängen till ett enumvärde
		    SectionType currentType = SectionType.valueOf(chosenTab.toUpperCase(Locale.getDefault()));
		    // Switcha enum-värdet, öppna rätt vy/layout till rätt flik
			switch (currentType) {
			case DAY:				
				 rootView = inflater.inflate(R.layout.activity_day,container, false);
				 
				 // TODO: Parsa rätt dag - OM CalendarActivity vill det
				 // TODO: Rätt dag finns i statiska variabeln chosenDate
					Calendar calendar = Calendar.getInstance();
					Date date = new Date(chosenYear, chosenMonth, chosenDay);
					calendar.setTime(date );
			        String[] days = new String[] { "SUNDAY", "MONDAY", 
			            		"TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
			            		"SATURDAY" };
			        String day = days[calendar.get(Calendar.DAY_OF_WEEK)];
				 
			        TextView dayTextView = (TextView) rootView
					.findViewById(R.id.textView2);
			        dayTextView.setText("Datum: " + chosenYear + "/" 
					+ chosenMonth + "/" + chosenDay + "\n" + "(" + day + ")");
				 


				 
				 return rootView;
			case WEEK:
				 // TODO: Ta bort fragment_tab så småningom ***
				 // TODO: Byt ut mot veckovy
				 rootView = inflater.inflate(R.layout.fragment_tab,container, false);
				 
					TextView dateTextView = (TextView) rootView
					.findViewById(R.id.section_label);
					dateTextView.setText("Veckovy ej implementerad!");	
				 
				 return rootView;
			case MONTH:
				 rootView = inflater.inflate(R.layout.activity_calendar,container, false);
		 				 
				 ///
				 CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
				
		         calendarView.setOnDateChangeListener(new OnDateChangeListener() {
		               @Override
		               public void onSelectedDayChange(CalendarView view,
		            		   int year, int month, int day) {

		            	   chosenYear = year;
		            	   chosenMonth = month;
		            	   chosenDay = day;
		            	   
		            	   // Byt till dagfliken, rätt datum
		            	   mViewPager.setCurrentItem(SectionType.DAY.ordinal()); 
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