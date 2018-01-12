package com.hatchers.ruralcaravane.user_login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.pref_manager.PrefManager;


public class UserDetailsFragment extends Fragment
{
    PrefManager prefManager;
    private ImageButton userDetails_btnBack;
    private TextView name,age,mobile_number,village_name;
    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_user__details, container, false);

        initialization(view);
        setUserDetails();
        clickListeners();

        return view;
    }

    private void initialization(View view)
    {
        prefManager=new PrefManager(getActivity());
        /*passwordTxt=(TextView)view.findViewById(R.id.password);
        visiblePassword=(ImageView)view.findViewById(R.id.password_visible);*/
        userDetails_btnBack=(ImageButton)view.findViewById(R.id.userDetails_btnBack);
        name=(TextView)view.findViewById(R.id.name);
        age=(TextView)view.findViewById(R.id.age);
        mobile_number=(TextView)view.findViewById(R.id.mobile_number);
        village_name=(TextView)view.findViewById(R.id.village_name);

    }

    private void clickListeners()
    {
        userDetails_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setUserDetails()
    {
        name.setText(String.valueOf("  " + prefManager.getUserName()));
        age.setText(String.valueOf("  " + prefManager.getBirthday()));
        mobile_number.setText(String.valueOf("  " + prefManager.getMobile()));
        village_name.setText(String.valueOf("  " + prefManager.getVillageName()));

    }

    /*private void passwordClick()
    {
        visiblePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordTxt.getInputType()==InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    passwordTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                    visiblePassword.setImageResource(R.drawable.eye_hidden);
                }
                else
                {
                    passwordTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    visiblePassword.setImageResource(R.drawable.eye_show);

                }
            }
        });
    }*/
}
