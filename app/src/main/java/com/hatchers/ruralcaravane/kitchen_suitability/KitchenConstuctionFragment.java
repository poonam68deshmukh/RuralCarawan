package com.hatchers.ruralcaravane.kitchen_suitability;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.construction_team.ConstructionTeamRegistrationFragment;
import com.hatchers.ruralcaravane.construction_team.adapter.ConstructionListAdapter;
import com.hatchers.ruralcaravane.construction_team.database.ConstructionTable;
import com.hatchers.ruralcaravane.construction_team.database.ConstructionTableHelper;
import com.hatchers.ruralcaravane.kitchen_suitability.database.KitchenTable;

import java.util.ArrayList;

public class KitchenConstuctionFragment extends Fragment {

    private KitchenTable kitchenTable;
    ArrayList<ConstructionTable> constructionTables;
    private RecyclerView constructionRecyclerView;
    private ConstructionListAdapter constructionListAdapter;
    private FloatingActionButton add_construction;
    private TextView houseTypeTxt, roofTypeTxt, heightTxt;


    public KitchenConstuctionFragment()
    {
        // Required empty public constructor
    }

    public static KitchenConstuctionFragment newInstance(KitchenTable kitchenTable) {
        KitchenConstuctionFragment fragment = new KitchenConstuctionFragment();
        Bundle args = new Bundle();
        args.putParcelable(KitchenTable.KITCHEN_TABLE, kitchenTable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kitchenTable = getArguments().getParcelable(KitchenTable.KITCHEN_TABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kitchen_constuction, container, false);

        initializations(view);

        clickListners();

        setKitchenData();


        return view;
    }

    private void initializations(View view)
    {
        add_construction=(FloatingActionButton)view.findViewById(R.id.add_const);
        constructionRecyclerView=(RecyclerView)view.findViewById(R.id.const_list);
        houseTypeTxt = (TextView)view.findViewById(R.id.housetype_txt);
        roofTypeTxt = (TextView)view.findViewById(R.id.roogtype_txt);
        heightTxt = (TextView)view.findViewById(R.id.height_txt);

        constructionRecyclerView = (RecyclerView) view.findViewById(R.id.const_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        constructionRecyclerView.setLayoutManager(layoutManager);

        constructionTables= ConstructionTableHelper.getConstructionTeamListByKitchen(getContext(),kitchenTable.getKitchenUniqueIdValue());
        constructionListAdapter=new ConstructionListAdapter(getContext(),constructionTables);

        constructionRecyclerView.setAdapter(constructionListAdapter);
        constructionListAdapter.notifyDataSetChanged();

    }

    private void setKitchenData()
    {
        houseTypeTxt.setText(String.valueOf("House Type : "+kitchenTable.getHouse_typeValue()));
        roofTypeTxt.setText(String.valueOf("Roof Type : "+kitchenTable.getRoof_typeValue()));
        heightTxt.setText(String.valueOf("Height : "+kitchenTable.getKitchen_heightValue()));
    }

    private void clickListners()
    {
        add_construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                ConstructionTeamRegistrationFragment constructionTeamRegistrationFragment= ConstructionTeamRegistrationFragment.newInstance(kitchenTable);
                fragmentTransaction.replace(R.id.frame_layout,constructionTeamRegistrationFragment).addToBackStack(null).commit();
            }
        });
    }

}
