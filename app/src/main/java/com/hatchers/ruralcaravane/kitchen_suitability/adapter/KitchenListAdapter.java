package com.hatchers.ruralcaravane.kitchen_suitability.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hatchers.ruralcaravane.construction_team.adapter.ConstructionListAdapter;
import com.hatchers.ruralcaravane.construction_team.database.ConstructionTable;
import com.hatchers.ruralcaravane.construction_team.database.ConstructionTableHelper;
import com.hatchers.ruralcaravane.kitchen_suitability.KitchenConstuctionFragment;
import com.hatchers.ruralcaravane.kitchen_suitability.KitchenSuitabilityFragment;
import com.hatchers.ruralcaravane.kitchen_suitability.database.KitchenTable;
import com.hatchers.ruralcaravane.R;
import java.util.ArrayList;
public class KitchenListAdapter extends RecyclerView.Adapter<KitchenListAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<KitchenTable> kitchenTableArrayList;
    public KitchenListAdapter(Context context, ArrayList<KitchenTable> kitchenTableArrayList) {
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
                FragmentTransaction fragmentTransaction =((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                KitchenConstuctionFragment kitchenConstuctionFragment=KitchenConstuctionFragment.newInstance(kitchenTable);
                fragmentTransaction.replace(R.id.frame_layout,kitchenConstuctionFragment).addToBackStack(null).commit();

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
        RecyclerView constructionRecyclerView;
        ViewHolder(View itemView)
        {
            super(itemView);
            houseType = (TextView) itemView.findViewById(R.id.houseType);
            roofType = (TextView) itemView.findViewById(R.id.roofType);

            this.itemView = itemView;
        }
    }
}
