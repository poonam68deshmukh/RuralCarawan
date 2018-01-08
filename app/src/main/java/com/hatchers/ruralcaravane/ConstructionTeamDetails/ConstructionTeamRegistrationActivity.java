package com.hatchers.ruralcaravane.ConstructionTeamDetails;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hatchers.ruralcaravane.Activity.MenuFragment;
import com.hatchers.ruralcaravane.R;

public class ConstructionTeamRegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_team_registration);

        callFragment();
    }

    private void callFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ConstructionTeamRegistrationFragment constructionTeamFragment = new ConstructionTeamRegistrationFragment();
        fragmentTransaction.replace(R.id.ConstructionTeam_FrameLayout,constructionTeamFragment).commit();
    }


}
