package com.hatchers.ruralcaravane.payment_details.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.payment_details.database.PaymentTable;

import java.util.ArrayList;

public class PaymentListAdapter  extends RecyclerView.Adapter<PaymentListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<PaymentTable> paymentTableArrayList;

    public PaymentListAdapter(Context context, ArrayList<PaymentTable> paymentTableArrayList) {

        this.context=context;
        this.paymentTableArrayList=paymentTableArrayList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment_list_row, viewGroup, false);
        PaymentListAdapter.ViewHolder viewHolder = new PaymentListAdapter.ViewHolder(v);
        context = viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PaymentTable paymentTable = paymentTableArrayList.get(position);

    }

    @Override
    public int getItemCount() {
        try {
            return paymentTableArrayList.size();
        } catch (Exception e) {
            return 0;
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        View itemView;

        ViewHolder(View itemView) {
            super(itemView);


            this.itemView = itemView;
        }
    }
}
