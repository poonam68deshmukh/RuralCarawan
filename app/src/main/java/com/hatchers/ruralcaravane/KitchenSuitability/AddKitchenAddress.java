package com.hatchers.ruralcaravane.KitchenSuitability;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.hatchers.ruralcaravane.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class AddKitchenAddress extends Fragment implements View.OnClickListener{
    public static final int NEWADDRESS=1,UPDATEADDRESS=1,ADDMOREADDRESS=3;

   FrameLayout frameLayout;
    private int activityOpenFor;
    private  final int  MAP_LOCATION=1;
    private Toolbar toolbar_add_address;
    MapViewFragment map;
    TextView placeNameTxt,addresstxt;
    String state,city,area,postalCode;
    private Button btnpickAddress,btn_saveaddress;
    static double getlatitude =0, getlongitude = 0;

    public AddKitchenAddress()
    {

    }
    @SuppressLint("ValidFragment")
    public AddKitchenAddress(FrameLayout frameLayout) {

        // Required empty public constructor
        this.activityOpenFor=NEWADDRESS;
        this.frameLayout=frameLayout;

    }
    @SuppressLint("ValidFragment")
    public AddKitchenAddress(int activityOpenFor) {
        // Required empty public constructor
        this.activityOpenFor=activityOpenFor;

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_addnew_address, container, false);
        initialise( rootView );

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }

        return rootView;
    }


    void initialise(View rootView )
    {
        btnpickAddress=(Button)rootView.findViewById(R.id.btnpickAdress);
        btn_saveaddress=(Button)rootView.findViewById(R.id.btn_saveaddress);
        placeNameTxt=(TextView) rootView.findViewById(R.id.place_name);
        addresstxt=(TextView) rootView.findViewById(R.id.address_txt);
        toolbar_add_address = (Toolbar) rootView.findViewById(R.id.toolbar_add_address);
        map=new MapViewFragment("maharastra india");
        map.setArguments(getActivity().getIntent().getExtras());

        toolbar_add_address.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnpickAddress.setOnClickListener(this);
        if(activityOpenFor==ADDMOREADDRESS)
        {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.map_layout, map).commit();
        }
        else if(activityOpenFor==NEWADDRESS) {

            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.map_layout, map).commit();
        }

        toolbar_add_address.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_saveaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getLatitudeLongitude(getlatitude,getlongitude);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {

            case R.id.btnpickAdress:
                Intent intent=new Intent(getActivity(),FindGeoLocationActivity.class);
                startActivity(intent);
                break;
            default:

                break;

        }

    }


    public static void setLatitudeLongitude(double latitude, double longitude)
    {
        getlatitude=latitude;
        getlongitude =longitude;

    }

    private void getLatitudeLongitude(double latitude, double longitude)
    {
        LatLng latLng = new LatLng(latitude,longitude);

        Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
        try {

            List<Address> addresses = null;

            addresses = gcd.getFromLocation(latitude,longitude, 1);
            if (addresses.size() > 0) {
                //Country=addresses.get(0).getCountryName();
                state= addresses.get(0).getAdminArea();
                city=addresses.get(0).getLocality();
                area=addresses.get(0).getFeatureName();
                postalCode=addresses.get(0).getPostalCode();
                addresstxt.setText(area+","+city+",    "+state+","+postalCode);
                // etzipCode.setText(addresses.get(0).getPostalCode());
                //addresstxt.get(0).getAddressLine();
                map=new MapViewFragment(latLng,addresses.get(0).getLocality());
                map.setArguments(getActivity().getIntent().getExtras());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.map_layout, map).commit();

                placeNameTxt.setText(addresses.get(0).getFeatureName());
                // addresstxt.setText(addresses.get(0).getAddressLine(0));
                // etLandmark.setText(addresses.get(0).getFeatureName()+" "+ addresses.get(0).getSubLocality());

            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
