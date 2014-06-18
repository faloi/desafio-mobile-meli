package com.cuantocuesta.android.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cuantocuesta.R;

public class LauncherActivity extends ActionBarActivity {
  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle mDrawerToggle;
  private FragmentManager fragmentManager;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_main_view);
    String[] mPlanetTitles = getResources().getStringArray(R.array.menu_options);
    System.out.println(mPlanetTitles.length);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerList = (ListView) findViewById(R.id.left_drawer);

    // Set the adapter for the list view
    mDrawerList.setAdapter(new ArrayAdapter<String>(this,
      R.layout.menu_item, R.id.menu_item_label, mPlanetTitles));

    // Set the list's click listener
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    mDrawerToggle = new ActionBarDrawerToggle(this,                  /* host Activity */
      mDrawerLayout,         /* DrawerLayout object */
      R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
      R.string.drawer_open,  /* "open drawer" description */
      R.string.drawer_close  /* "close drawer" description */) {

      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
      }

      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
      }
    };

    // Set the drawer toggle as the DrawerListener
    mDrawerLayout.setDrawerListener(mDrawerToggle);

    ActionBar mActionBar = getSupportActionBar();

    buildCustomActionBar(mActionBar);

    fragmentManager = getSupportFragmentManager();

  }

  protected void buildCustomActionBar(ActionBar actionBar) {
    actionBar.setDisplayShowHomeEnabled(false);
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayShowCustomEnabled(true);

    LayoutInflater mInflater = LayoutInflater.from(this);
    View customView = mInflater.inflate(R.layout.action_bar_fashionup, null);

    actionBar.setCustomView(customView);
  }

  private class DrawerItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
      selectItem(position);
    }
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerToggle.syncState();

    selectItem(LISTINGS_SECTION);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  /**
   * Swaps fragments in the main content view
   */

  private static final int MAIN_SECTION = 0;
  private static final int CATEGORIES_SECTION = 1;
  private static final int LISTINGS_SECTION = 2;

  private void selectItem(int position) {

    Fragment fragment;

    switch (position){
      case CATEGORIES_SECTION:
        fragment = new CategoriesActivity();
        break;
      case LISTINGS_SECTION:
        fragment = new ListingsActivity();
        break;
      default:
        fragment = new MainContentFragment();
    }

    // create a new fragment and specify the planet to show based on position
    Bundle args = new Bundle();
    args.putInt(MainContentFragment.ARG_OS, position);
    fragment.setArguments(args);

    fragmentManager.beginTransaction()
      .replace(R.id.content_frame, fragment)
      .commit();

    // Highlight the selected item, update the title, and close the drawer
    mDrawerList.setItemChecked(position, true);
    mDrawerLayout.closeDrawer(mDrawerList);
  }


}