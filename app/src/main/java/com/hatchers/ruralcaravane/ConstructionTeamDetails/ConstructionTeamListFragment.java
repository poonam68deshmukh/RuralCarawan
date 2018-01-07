package com.hatchers.ruralcaravane.ConstructionTeamDetails;

import android.content.Context;
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

import com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases.ConstructionTable;
import com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases.ConstructionTableHelper;
import com.hatchers.ruralcaravane.R;

import java.util.ArrayList;


public class ConstructionTeamListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toolbar constructionTeamListToolbar;
    private FloatingActionButton add_construction;
    private RecyclerView constructionRecyclerView;
    private FragmentTransaction fragmentTransaction;
    private ConstructionListAdapter constructionListAdapter;

    ArrayList<ConstructionTable> constructionTables;

    public ConstructionTeamListFragment() {
        // Required empty public constructor
    }

      // TODO: Rename and change types and number of parameters
    public static ConstructionTeamListFragment newInstance(String param1, String param2) {
        ConstructionTeamListFragment fragment = new ConstructionTeamListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_construction_team_list, container, false);

        initializations(view);
        onClickListeners();

        constructionRecyclerView = (RecyclerView) view.findViewById(R.id.constructionRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        constructionRecyclerView.setLayoutManager(layoutManager);

        constructionTables= ConstructionTableHelper.getConstructionTeamList(getContext());
        constructionListAdapter=new ConstructionListAdapter(getContext(),constructionTables);

        constructionRecyclerView.setAdapter(constructionListAdapter);
        constructionListAdapter.notifyDataSetChanged();

        return  view;
    }

    private void initializations(View view)
    {
        constructionTeamListToolbar=(Toolbar)view.findViewById(R.id.constructionTeamListToolbar);
        add_construction=(FloatingActionButton)view.findViewById(R.id.add_construction);
        constructionRecyclerView=(RecyclerView)view.findViewById(R.id.constructionRecyclerView);
    }

    private void onClickListeners()
    {
        add_construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                ConstructionTeamRegistrationFragment constructionTeamRegistrationFragment=new ConstructionTeamRegistrationFragment();
                fragmentTransaction.replace(R.id.frame_layout,constructionTeamRegistrationFragment).addToBackStack(null).commit();
            }
        });


    }



    public class ConstructionListAdapter extends RecyclerView.Adapter<ConstructionListAdapter.ViewHolder>
    {
        private Context context;
        private ArrayList<ConstructionTable> constructionTableArrayList;

        ConstructionListAdapter(Context context,ArrayList<ConstructionTable> constructionTableArrayList) {
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
}
