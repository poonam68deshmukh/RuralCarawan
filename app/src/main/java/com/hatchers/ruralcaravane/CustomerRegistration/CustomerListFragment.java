package com.hatchers.ruralcaravane.CustomerRegistration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hatchers.ruralcaravane.Activity.CustomerActivity;
import com.hatchers.ruralcaravane.Activity.MenuFragment;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTable;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTableHelper;
import com.hatchers.ruralcaravane.KitchenSuitability.kitchenSuitabilityFragment;
import com.hatchers.ruralcaravane.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerListFragment extends Fragment {

    CustomerListAdapter customerListAdapter;
    private ListView listView;
    private FloatingActionButton fab;
    private FragmentTransaction fragmentTransaction;

    ArrayList<CustomerTable> customerTables;

    public CustomerListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_customer__list, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        onClickListeners();

        customerTables= CustomerTableHelper.getCustomerdataList(getContext());
        customerListAdapter= new CustomerListAdapter(getContext(), R.layout.list_row,customerTables);

        listView = (ListView)view.findViewById(R.id.customerListView);
        listView.setAdapter(customerListAdapter);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getActivity(), CustomerActivity.class);
                startActivity(intent);
            }
        });

    return view;
    }
/*
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            refreshList();
        } else {

        }
    }*/

  /*  private void refreshList()
    {
        customerTables= CustomerTableHelper.getCustomerdataList(getContext());
        customerListAdapter= new CustomerListAdapter(getContext(), R.layout.list_row,customerTables);
        listView.setAdapter(customerListAdapter);
        customerListAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        customerListAdapter.notifyDataSetChanged();
    }
    private void onClickListeners()
    {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerRegistrationActivity.viewPager.setCurrentItem(1);
            }
        });

    }



    public class CustomerListAdapter extends ArrayAdapter<CustomerTable> {

        private Context context;
        private ArrayList<CustomerTable> customerTableArrayList;

        public CustomerListAdapter(Context context, int textViewResourceId, ArrayList<CustomerTable> customerTableArrayList) {

            super(context, textViewResourceId, customerTableArrayList);

            this.context = context;
            this.customerTableArrayList = customerTableArrayList;

        }

        private class ViewHolder {
            TextView customer_name,address,mobile,age;
        }

        @Override
        public int getCount() {
            try {
                return customerTableArrayList.size();
            } catch (Exception e) {
                return 0;
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row, null);

                holder = new ViewHolder();
                holder.customer_name = (TextView) convertView.findViewById(R.id.customer_name);
                holder.address = (TextView) convertView.findViewById(R.id.customer_address);
                holder.mobile = (TextView) convertView.findViewById(R.id.customer_mobileno);
                holder.age = (TextView) convertView.findViewById(R.id.customer_age);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CustomerTable customerTable = customerTableArrayList.get(position);
            holder.customer_name.setText(String.valueOf(customerTable.getCustomerNameValue() + ""));
            holder.address.setText(String.valueOf(customerTable.getCustomerAddressValue() + ""));
            holder.mobile.setText(String.valueOf("Mobile-"+customerTable.getCustomerMobilenoValue() + ""));
            holder.age.setText(String.valueOf("Age-"+customerTable.getCustomerAgeValue()+ ""));

            return convertView;
        }

    }



}
