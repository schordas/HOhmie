package com.chordas.hohmie;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.view.ActionMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		ColorDialogFragment.NoticeDialogListener,
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnClickListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private int mCurrentColorArrayIndex;
	private TextView mTextView;
	private int bandIndex;
	//private ArrayList<ColorBand> fourBandDataArray = new ArrayList<ColorBand>();
	private ColorBand[] bandDataArray = new ColorBand[5];
	private BandSpecs bandSpecsReferenceObject = new BandSpecs();
//	private LinearLayout mLinearLayout;
	private TextView fifthBandView;
	private int mSectionNumber = 0;
	private TextView fourthBandView;
	private int screenWidth;
	private int screenHeight;
	private OrientationEventListener orientationEventListener;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	private ActionMode mActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		orientationEventListener = new OrientationEventListener(this, 
				SensorManager.SENSOR_DELAY_NORMAL) {
			
			@Override
			public void onOrientationChanged(int orientation) {
				// override orientation for accuracy
				orientation = getResources().getConfiguration().orientation;
					Log.d("MainActivity", "Orientation: " + orientation);
				
			}
		};
		orientationEventListener.enable();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		screenWidth = size.x;
		screenHeight = size.y;
		fifthBandView = (TextView) findViewById(R.id.fifth_digit_color_code);
		if (mSectionNumber != 2){
			fifthBandView.setVisibility(View.GONE);
		}

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		mCurrentColorArrayIndex = -1;
		//mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);

		for (int i = 0; i < 5; i++) {
			bandDataArray[i] = new ColorBand();
			//bandDataArray.add(new ColorBand()); //= new ColorBand();
			bandDataArray[i].setIndex(-1);
			//fourBandDataArray.get(i).setIndex(-1);// TODO: come up with a
			// better solution to
			// in initial band values
		}
		//fifthBandView = new TextView(this);
		TextView bandOneColor = (TextView) findViewById(R.id.first_digit_color_code);
		bandOneColor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				bandIndex = 0;
				mCurrentColorArrayIndex = bandDataArray[bandIndex]
						.getIndex();
				mTextView = (TextView) findViewById(R.id.first_digit_color_code);
				showNoticeDialog();
			}

		});
		TextView bandTwoColor = (TextView) findViewById(R.id.second_digit_color_code);
		bandTwoColor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				bandIndex = 1;
				mCurrentColorArrayIndex = bandDataArray[bandIndex]
						.getIndex();
				mTextView = (TextView) findViewById(R.id.second_digit_color_code);
				showNoticeDialog();

			}
		});
		TextView bandThreeColor = (TextView) findViewById(R.id.third_digit_color_code);
		bandThreeColor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				bandIndex = 2;
				mCurrentColorArrayIndex = bandDataArray[bandIndex]
						.getIndex();
				mTextView = (TextView) findViewById(R.id.third_digit_color_code);
				showNoticeDialog();

			}
		});
		fourthBandView = (TextView) findViewById(R.id.fourth_digit_color_code);
		fourthBandView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				bandIndex = 3;
				mCurrentColorArrayIndex = bandDataArray[bandIndex]
						.getIndex();
				mTextView = (TextView) findViewById(R.id.fourth_digit_color_code);
				showNoticeDialog();

			}
		});
		fifthBandView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bandIndex = 4;
				mCurrentColorArrayIndex = bandDataArray[bandIndex]
						.getIndex();
				mTextView = (TextView) findViewById(R.id.fifth_digit_color_code);
				showNoticeDialog();
			}
		});

	}
	
	

	public void onClick(View v) {

	}

	public void showNoticeDialog() { // TODO: work on passing in current color
										// of band
		// TODO: work on passing on what colors to show based on 4 band or 5
		// band
		DialogFragment dialog = new ColorDialogFragment(mCurrentColorArrayIndex);
		dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.linear_layout,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		mSectionNumber = number;
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			fourthBandView.setVisibility(View.VISIBLE);
			fifthBandView.setVisibility(View.GONE);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			if (fourthBandView.getVisibility() == View.GONE){
				fourthBandView.setVisibility(View.VISIBLE);
			}
			fifthBandView.setVisibility(View.VISIBLE);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			if (fifthBandView.getVisibility() == View.VISIBLE){
				fifthBandView.setVisibility(View.GONE);
			}
			if (fourthBandView.getVisibility() == View.VISIBLE){
				fourthBandView.setVisibility(View.GONE);
			}
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String color,
			int index) {
		Toast.makeText(this, "Position Shared: " + color, Toast.LENGTH_SHORT)
				.show();
		Log.d("Main Activity", "bandIndex: " + bandIndex);
		bandDataArray[bandIndex].setFields(
				bandSpecsReferenceObject.getColor(index),
				bandSpecsReferenceObject.getMultiplier(index),
				bandSpecsReferenceObject.getTolerance(index),
				bandSpecsReferenceObject.getSigFig(index), index);
		mTextView.setBackgroundColor(bandSpecsReferenceObject.getColor(index));
		// mTextView.get
		mCurrentColorArrayIndex = index;
		// //mTextView.setBa
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

	}

}
