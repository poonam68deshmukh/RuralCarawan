package com.hatchers.ruralcaravane.KitchenSuitability;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hatchers.ruralcaravane.ConstructionTeamDetails.ConstructionListAdapter;
import com.hatchers.ruralcaravane.ConstructionTeamDetails.ConstructionTeamListFragment;
import com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases.ConstructionTable;
import com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases.ConstructionTableHelper;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTable;
import com.hatchers.ruralcaravane.R;
import java.util.ArrayList;
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
        ArrayList<ConstructionTable>  constructionTables= ConstructionTableHelper.getConstructionTeamList(context);
        ConstructionListAdapter constructionListAdapter=new ConstructionListAdapter(context,constructionTables);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                holder.constructionRecyclerView.setVisibility(View.VISIBLE);
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
            constructionRecyclerView = (RecyclerView) itemView.findViewById(R.id.constructionRecyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            constructionRecyclerView.setLayoutManager(layoutManager);

            this.itemView = itemView;
        }
    }
}
