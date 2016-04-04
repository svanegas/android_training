package com.example.svanegas.training.controller.person;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.svanegas.training.R;
import com.example.svanegas.training.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_ITEM_ID = "selected_item_id";

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton mFab;
    private int mDrawerSelectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawer(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.content.peopleList.setLayoutManager(new LinearLayoutManager(this));
        binding.content.peopleList.setAdapter(new PersonAdapter(getLayoutInflater()));

        if (savedInstanceState == null) mDrawerSelectedItemId = R.id.drawer_item_1;
        else mDrawerSelectedItemId = savedInstanceState.getInt(SELECTED_ITEM_ID);

        navigateTo(mDrawerSelectedItemId);
        setNavItemCount(R.id.drawer_item_4, 10);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null) mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawer(Toolbar toolbar) {
        mNavigationView = (NavigationView) findViewById(R.id.drawer_nav_view);
        if (mNavigationView != null) mNavigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void navigateTo(int destination) {

    }

    private void setNavItemCount(int itemId, int count) {
        if (mNavigationView != null && mNavigationView.getMenu() != null) {
            MenuItem menuItem = mNavigationView.getMenu().findItem(itemId);
            if (menuItem != null) {
                TextView view = (TextView) menuItem.getActionView();
                if (view != null) view.setText(count > 0 ? String.valueOf(count) : null);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item == null || mDrawerLayout == null || mFab == null) return false;
        item.setChecked(true);
        mDrawerSelectedItemId = item.getItemId();
        if (item.getItemId() == R.id.drawer_item_1) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Snackbar.make(mFab, "Clicked first", Snackbar.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == R.id.drawer_item_2) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Snackbar.make(mFab, "Clicked second", Snackbar.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mDrawerSelectedItemId);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
