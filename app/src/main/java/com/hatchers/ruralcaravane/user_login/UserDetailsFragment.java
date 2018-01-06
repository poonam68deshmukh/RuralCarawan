package com.hatchers.ruralcaravane.user_login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hatchers.ruralcaravane.R;


public class UserDetailsFragment extends Fragment {


    private ImageButton userDetails_btnBack;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_user__details, container, false);

        initialization(view);

        //passwordClick();

        userDetails_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private void initialization(View view)
    {
        /*passwordTxt=(TextView)view.findViewById(R.id.password);
        visiblePassword=(ImageView)view.findViewById(R.id.password_visible);*/
        userDetails_btnBack=(ImageButton)view.findViewById(R.id.userDetails_btnBack);
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
