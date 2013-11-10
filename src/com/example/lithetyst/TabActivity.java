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
	
	// PagerAdapter tillhandah�ller Fragment f�r varje section 
	private SectionsPagerAdapter mSectionsPagerAdapter;

	// ViewPager �r v�rd f�r inneh�llet i varje section 
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
		// Fragment f�r vart och ett av de tre sections som vi har (dag, vecka, m�nad).
		mSectionsPagerAdapter = 
				new SectionsPagerAdapter(getSupportFragmentManager());

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

	// TODO: R�tt meny
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// G�r menyn redo f�r denna vyn
		getMenuInflater().inflate(R.menu.tab, menu);
		return true;
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
	public static class CalendarSectionFragment extends Fragment {
		// Fragmentets argument som representerar rubriken f�r dess section 
		public static final String ARG_SECTION_TITLE = "section_title";
		private static enum SectionType { DAG, VECKA, M�NAD; } // Flikar/tabbar
		
		// konstruktor
		public CalendarSectionFragment() {
			// TODO
		}

		// Skapa vyn
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
				 rootView = inflater.inflate(R.layout.fragment_tab,container, false);
				 
					// TODO: Parsa r�tt dag - OM CalendarActivity vill det
					TextView dateTextView = (TextView) rootView
					.findViewById(R.id.section_label);
					dateTextView.setText(chosenDate);				 
				 
				 return rootView;
			case VECKA:
				 // TODO: Ta bort fragment_tab s� sm�ningom
				 rootView = inflater.inflate(R.layout.fragment_tab,container, false);
				 return rootView;
			case M�NAD:
				 rootView = inflater.inflate(R.layout.activity_calendar,container, false);
		 				 
				 ///
				 CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
				
			       calendarView.setOnDateChangeListener(new OnDateChangeListener() {
			               @Override
			               public void onSelectedDayChange(CalendarView view,
			            		   int year, int month, int dayOfMonth) {

			            	   chosenDate = year + " " + month + " " + dayOfMonth;
			            	   System.out.println("onSelectedDayChange: " + chosenDate);
			            	   
			            	   // Byt till dagfliken, r�tt datum
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