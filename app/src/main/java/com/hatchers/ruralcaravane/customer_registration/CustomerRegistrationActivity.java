package com.hatchers.ruralcaravane.customer_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.customer_registration.adapter.CustomerAdapter;
import com.hatchers.ruralcaravane.pref_manager.PrefManager;
import com.hatchers.ruralcaravane.user_login.LoginActivity;
import com.hatchers.ruralcaravane.user_login.UserDetailsFragment;


public class CustomerRegistrationActivity extends AppCompatActivity
{
    private Toolbar customer_toolbar;
    private TabLayout tabLayout;
    public static ViewPager viewPager;
    PrefManager prefManager;
    FragmentTransaction fragmentTransaction;

    private int[] tabIcons = {
            R.drawable.customer_list,
            R.drawable.add_customer
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        customer_toolbar = (Toolbar)findViewById(R.id.customer_toolbar);
        setSupportActionBar(customer_toolbar);

        initializations();

        SetupViewPager();

        setupTabIcons();

        tabListener();

        viewPagerListener();

        clickListener();
    }

    private void setupTabIcons()
    {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void initializations()
    {
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tabs_layout);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        prefManager=new PrefManager(getApplicationContext());

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window =getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

    }

    private void SetupViewPager()
    {
        CustomerAdapter adapter = new CustomerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CustomerListFragment(), "Customer List");
        adapter.addFragment(new AddCustomerFragment(), "Add Customer");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
}

    private void tabListener()
    {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void viewPagerListener()
    {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void clickListener()
    {
        /*customer_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.profile:
                UserDetailsFragment userDetailsFragment=new UserDetailsFragment();
                fragmentTransaction.replace(R.id.frame_layout2,userDetailsFragment).addToBackStack(null).commit();
                break;

            case R.id.logout:
                prefManager.setLogOut();
                Intent i= new Intent(CustomerRegistrationActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
