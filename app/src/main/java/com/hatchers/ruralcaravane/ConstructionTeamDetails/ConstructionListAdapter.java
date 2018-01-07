package com.hatchers.ruralcaravane.ConstructionTeamDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases.ConstructionTable;
import com.hatchers.ruralcaravane.R;

import java.util.ArrayList;

/**
 * Created by Nikam on 07/01/2018.
 */

public class ConstructionListAdapter extends RecyclerView.Adapter<ConstructionListAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<ConstructionTable> constructionTableArrayList;

    public ConstructionListAdapter(Context context,ArrayList<ConstructionTable> constructionTableArrayList) {
        this.context = context;
        this.constructionTableArrayList = constructionTableArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.construction_list_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        context = viewGroup.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ConstructionTable constructionTable = constructionTableArrayList.get(position);

        holder.member_name.setText(String.valueOf(constructionTable.getTechnicianNameValue() + ""));
        holder.construction_mobile_number.setText(String.valueOf(constructionTable.getTechnicianMobileNoValue() + ""));


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
            return constructionTableArrayList.size();
        } catch (Exception e) {
            return 0;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView member_name,construction_mobile_number;

        View itemView;

        ViewHolder(View itemView)
        {
            super(itemView);
            member_name = (TextView) itemView.findViewById(R.id.member_name);
            construction_mobile_number = (TextView) itemView.findViewById(R.id.construction_mobile_number);

            this.itemView = itemView;
        }
    }

}