package com.cuantocuesta.android.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cuantocuesta.R;

public class LauncherActivity extends ActionBarActivity {
  private String[] mPlanetTitles;
  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle mDrawerToggle;
  private CharSequence title;
  private FragmentManager fragmentManager;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_main_view);
    title = getSupportActionBar().getTitle();
    mPlanetTitles = getResources().getStringArray(R.array.menu_options);
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

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setIcon(R.drawable.logo_fashionup);
    getSupportActionBar().setTitle("");

    fragmentManager = getSupportFragmentManager();

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
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  /**
   * Swaps fragments in the main content view
   */

  private void selectItem(int position) {
    // create a new fragment and specify the planet to show based on position
    Fragment fragment = new MainContentFragment();
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