package com.hatchers.ruralcaravane.KitchenSuitability;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hatchers.ruralcaravane.Activity.CustomerMenus;
import com.hatchers.ruralcaravane.CustomerRegistration.CustomerListFragment;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTable;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTableHelper;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTable;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTableHelper;
import com.hatchers.ruralcaravane.R;

import java.util.ArrayList;
import java.util.List;


public class KitchenSuitabilityList extends Fragment {

    private FloatingActionButton kitchenList;
    private Toolbar kitchenListToolbar;
    private ImageButton kitchenList_btnBack;
    private RecyclerView kitchenRecyclerView;
    KitchenListAdapter kitchenListAdapter;
    FragmentTransaction fragmentTransaction;

    ArrayList<KitchenTable> kitchenTables;


    public KitchenSuitabilityList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kitchen_suitability_list, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(kitchenListToolbar);
        initializations(view);
        onClickListeners();

        kitchenRecyclerView = (RecyclerView) view.findViewById(R.id.kitchenRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        kitchenRecyclerView.setLayoutManager(layoutManager);

        kitchenTables= KitchenTableHelper.getKitchenDataList(getContext());
        kitchenListAdapter=new KitchenListAdapter(getContext(),kitchenTables);

        kitchenRecyclerView.setAdapter(kitchenListAdapter);
        kitchenListAdapter.notifyDataSetChanged();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }
         return view;
    }

    private void initializations(View view)
    {
        kitchenList=(FloatingActionButton)view.findViewById(R.id.kitchenList);
        kitchenListToolbar=(Toolbar)view.findViewById(R.id.kitchenListToolbar);
        kitchenList_btnBack=(ImageButton)view.findViewById(R.id.kitchenList_btnBack);


    }

    private void onClickListeners()
    {

        kitchenList_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        kitchenList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                kitchenSuitabilityFragment kitchenSuitabilityFragment=new kitchenSuitabilityFragment();
                fragmentTransaction.replace(R.id.frame_layout,kitchenSuitabilityFragment).addToBackStack(null).commit();

            }
        });
    }


    public class KitchenListAdapter extends RecyclerView.Adapter<KitchenListAdapter.ViewHolder>
    {
        private Context context;
        private ArrayList<KitchenTable> kitchenTableArrayList;

        KitchenListAdapter(Context context,ArrayList<KitchenTable> kitchenTableArrayList) {
            this.context = context;
            this.kitchenTableArrayList = kitchenTableArrayList;
        }

        @Override
        public KitchenListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kitchen_list_row, viewGroup, false);
            KitchenListAdapter.ViewHolder viewHolder = new KitchenListAdapter.ViewHolder(v);
            context = viewGroup.getContext();
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final KitchenListAdapter.ViewHolder holder, int position) {
            final KitchenTable kitchenTable = kitchenTableArrayList.get(position);

            holder.houseType.setText(String.valueOf(kitchenTable.getHouse_typeValue() + ""));
            holder.roofType.setText(String.valueOf(kitchenTable.getRoof_typeValue() + ""));


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                }
            });


        }

        @Override
        public int getItemCount() {
            try {
                return kitchenTableArrayList.size();
            } catch (Exception e) {
                return 0;
            }

        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView houseType,roofType;

            View itemView;

            ViewHolder(View itemView)
            {
                super(itemView);
                houseType = (TextView) itemView.findViewById(R.id.houseType);
                roofType = (TextView) itemView.findViewById(R.id.roofType);

                this.itemView = itemView;
            }
        }

    }



}
