package com.example.lithetyst;

import java.util.Locale;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.CalendarView.OnDateChangeListener;

public class TabActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	private static String chosenDate = "00-00-00"; // TODO: dagens datum
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
		// Logon ska leda hem
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

	// TODO: Rätt meny
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Gör menyn redo för denna vyn
		getMenuInflater().inflate(R.menu.tab, menu);
		return true;
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
		private static enum SectionType { DAG, VECKA, MÅNAD; } // Flikar/tabbar
		
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
			case DAG:				
				 rootView = inflater.inflate(R.layout.fragment_tab,container, false);
				 
					// TODO: Parsa rätt dag - OM CalendarActivity vill det
					TextView dateTextView = (TextView) rootView
					.findViewById(R.id.section_label);
					dateTextView.setText(chosenDate);				 
				 
				 return rootView;
			case VECKA:
				 // TODO: Ta bort fragment_tab så småningom
				 rootView = inflater.inflate(R.layout.fragment_tab,container, false);
				 return rootView;
			case MÅNAD:
				 rootView = inflater.inflate(R.layout.activity_calendar,container, false);
		 				 
				 ///
				 CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
				
			       calendarView.setOnDateChangeListener(new OnDateChangeListener() {
			               @Override
			               public void onSelectedDayChange(CalendarView view,
			            		   int year, int month, int dayOfMonth) {

			            	   chosenDate = year + " " + month + " " + dayOfMonth;
			            	   System.out.println("onSelectedDayChange: " + chosenDate);
			            	   
			            	   // Byt till dagfliken, rätt datum
			            	   mViewPager.setCurrentItem(SectionType.DAG.ordinal()); 
			               }
			           });					 
				 return rootView;
			default:
				 // Hit borde vi inte komma
				 rootView = inflater.inflate(R.layout.activity_calendar,container, false);
				 return rootView;
			}
		}
	}
}