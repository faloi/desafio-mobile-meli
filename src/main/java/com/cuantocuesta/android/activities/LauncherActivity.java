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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.CustomFragment;

public class LauncherActivity extends ActionBarActivity {
  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle mDrawerToggle;
  private FragmentManager fragmentManager;
  private CustomFragment fragment;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_main_view);

    buildDrawerNavigator();

    buildCustomActionBar(getSupportActionBar());
    setUpDrawerToggle(getSupportActionBar());

    fragmentManager = getSupportFragmentManager();

  }

  public void buildDrawerNavigator() {
    mDrawerList = (ListView) findViewById(R.id.left_drawer);
    mDrawerList.setAdapter(new ArrayAdapter<String>(this,
      R.layout.menu_item, R.id.menu_item_label, getResources().getStringArray(R.array.menu_options)));
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
  }

  protected void buildCustomActionBar(ActionBar actionBar) {
    actionBar.setDisplayShowHomeEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayShowCustomEnabled(true);

    LayoutInflater mInflater = LayoutInflater.from(this);
    View customView = mInflater.inflate(R.layout.action_bar_fashionup, null);

    actionBar.setCustomView(customView);
  }

  private void setUpDrawerToggle(ActionBar actionBar){
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);

    mDrawerToggle = new ActionBarDrawerToggle(
      this,                             /* host Activity */
      mDrawerLayout,                    /* DrawerLayout object */
      R.drawable.ic_navigation_drawer,             /* nav drawer image to replace 'Up' caret */
      R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
      R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
    ) {
      @Override
      public void onDrawerClosed(View drawerView) {
        invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
      }
    };

    // Defer code dependent on restoration of previous instance state.
    // NB: required for the drawer indicator to show up!
    mDrawerLayout.post(new Runnable() {
      @Override
      public void run() {
        mDrawerToggle.syncState();
      }
    });

    mDrawerLayout.setDrawerListener(mDrawerToggle);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Pass the event to ActionBarDrawerToggle, if it returns
    // true, then it has handled the app icon touch event
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    // Handle your other action bar items...

    return super.onOptionsItemSelected(item);
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

    selectItem(CATEGORIES_SECTION);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  /**
   * Swaps fragments in the main content view
   */

  private static final int CATEGORIES_SECTION = 0;
  private static final int LISTINGS_SECTION = 1;
  private static final int MIS_PRODUCTOS = 1;

  private void selectItem(int position) {

    switch (position){
      case CATEGORIES_SECTION:
        fragment = new CategoriesActivity();
        break;
      case LISTINGS_SECTION:
        fragment = new ListingsActivity();
        break;
      default:
        fragment = new ListingsActivity();
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

  @Override
  public void onBackPressed() {
    if(this.fragment != null && !this.fragment.onBackPressed()){
      super.onBackPressed();
    }
  }
}