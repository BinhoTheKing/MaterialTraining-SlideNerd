package br.com.cast.treinamento.materialtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NavigationDrawerFragment extends Fragment {

	public static final String PREF_FILE_NAME = "testpref";
	public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
	private RecyclerView recyclerView;
	private RecyclerAdapter recyclerAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private boolean mUserLearnedDrawer;
	private boolean mFromSavedInstanceState;
	private View containerView;

	public NavigationDrawerFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View layout = inflater.inflate(R.layout.fragment_navigation_drawer,
				container, false);
		recyclerView = (RecyclerView) layout
				.findViewById(R.id.recycler_navigation_drawer);
		recyclerAdapter = new RecyclerAdapter(getActivity(), getData());
		recyclerView.setAdapter(recyclerAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		return layout;
	}

	public static List<ItemData> getData() {
		List<ItemData> items = new ArrayList<ItemData>();
		int[] images = { R.drawable.ic_action_image_tag_faces,
				R.drawable.ic_action_maps_directions_ferry,
				R.drawable.ic_action_florist, R.drawable.ic_action_hotel };
		String[] titles = { "Smiling Face", "Iron Ferry", "Little Flowers",
				"Time to Bed" };
		for (int i = 0; i < titles.length && i < images.length; i++) {
			ItemData current = new ItemData();
			current.iconId = images[i];
			current.title = titles[i];
			items.add(current);
		}
		return items;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(),
				KEY_USER_LEARNED_DRAWER, "false"));
		if (savedInstanceState != null) {
			mFromSavedInstanceState = true;
		}

	}

	public void setUp(int fragmentId, DrawerLayout drawerLayout,
			final Toolbar toolbar) {
		containerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout,
				toolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!mUserLearnedDrawer) {
					mUserLearnedDrawer = true;
					saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,
							mUserLearnedDrawer + "");
				}
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				// float alphaOffsetFast = (float) (slideOffset * 0.63);
				// toolbar.setAlpha(1 - alphaOffsetFast);
			}
		};
		if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
			mDrawerLayout.openDrawer(containerView);
		}
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.post(new Runnable() {

			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});
	}

	public static void saveToPreferences(Context context,
			String preferenceName, String preferenceValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				PREF_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(preferenceName, preferenceValue);
		editor.apply();
	}

	public static String readFromPreferences(Context context,
			String preferenceName, String preferenceValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				PREF_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(preferenceName, preferenceValue);
	}

}
