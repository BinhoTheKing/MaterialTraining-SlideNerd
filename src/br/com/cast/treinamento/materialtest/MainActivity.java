package br.com.cast.treinamento.materialtest;

import br.com.cast.treinamento.tabs.SlidingTabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private Toolbar toolbarView;
	private SlidingTabLayout mTabs;
	private ViewPager mPager;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			return super.onCreateView(parent, name, context, attrs);
		} else {
			return parent;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbarView = (Toolbar) findViewById(R.id.app_bar);
		setSupportActionBar(toolbarView);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_navigation_drawer);
		drawerFragment.setUp(R.id.fragment_navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout), toolbarView);
		mTabs = (SlidingTabLayout) findViewById(R.id.main_tabs);
		mPager = (ViewPager) findViewById(R.id.main_tabs_view_pager);
		mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		mTabs.setDistributeEvenly(true);
		mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
		mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
		mTabs.setSelectedIndicatorColors(new int[] { getResources().getColor(
				R.color.accentColor) });
		mTabs.setViewPager(mPager);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
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
		if (id == R.id.navigate) {
			startActivity(new Intent(this, SubActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	static class MyFragment extends Fragment {
		private TextView textView;

		public static MyFragment getInstance(int position) {
			MyFragment myFragment = new MyFragment();
			Bundle args = new Bundle();
			args.putInt("pos", position);
			myFragment.setArguments(args);
			return myFragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View layout = inflater.inflate(R.layout.fragment_my, container,
					false);
			textView = (TextView) layout.findViewById(R.id.position);
			Bundle bundle = getArguments();
			if (bundle != null) {
				textView.setText("The page is " + bundle.getInt("pos"));
			}
			return layout;
		}
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		private String[] tabs;
		int icons[] = { R.drawable.tab_icon_1, R.drawable.tab_icon_2,
				R.drawable.tab_icon_3 };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			tabs = getResources().getStringArray(R.array.tabs);
		}

		@SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		@Override
		public CharSequence getPageTitle(int position) {
			/*
			 * Drawable drawable; int density = (int)
			 * getResources().getDisplayMetrics().density; if
			 * (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) { drawable
			 * = getResources().getDrawable(icons[position]); } else { drawable
			 * = getResources().getDrawable(icons[position], null); }
			 * drawable.setBounds(0, 0, 24 * density, 24 * density); ImageSpan
			 * imageSpan = new ImageSpan(drawable); SpannableString
			 * spannableString = new SpannableString(position + "");
			 * spannableString.setSpan(imageSpan, 0, spannableString.length(),
			 * SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
			 */
			 return String.valueOf(icons[position]);
//			return String.valueOf("teste");
		}

		@Override
		public Fragment getItem(int position) {
			MyFragment myFragment = MyFragment.getInstance(position);
			return myFragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

	}
}
