package com.example.abhi.trainingplacement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ViewCmpAdap extends RecyclerView.Adapter<ViewCmpAdap.ViewHolder>
{
    private List<Companies> itemList;
    private Context context;

    public ViewCmpAdap(Context context, List<Companies> itemList)
    {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewcmprecy_layout, parent, false);
        ViewHolder vh = new ViewCmpAdap.ViewHolder(v);
        return vh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cmpname,cmpvis,cmpmiss,cmpdet,cmpeligi,cmpvenue,cmpvac;


        public ViewHolder(View itemView)
        {
            super(itemView);
            cmpname=(TextView) itemView.findViewById(R.id.cmpname);
            cmpvis=(TextView)itemView.findViewById(R.id.companyVision);
            cmpmiss=(TextView)itemView.findViewById(R.id.miss);
            cmpdet=(TextView)itemView.findViewById(R.id.companyDetail);
            cmpeligi=(TextView)itemView.findViewById(R.id.cmpGpa);
            cmpvenue=(TextView)itemView.findViewById(R.id.companyVenue);
            cmpvac=(TextView)itemView.findViewById(R.id.companyVacancy);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        final Companies cmp=itemList.get(position);

        holder.cmpname.setText(cmp.getCompany_name());
        holder.cmpvis.setText(cmp.getCompany_vision());
        holder.cmpmiss.setText(cmp.getCompany_mission());
        holder.cmpdet.setText(cmp.getCompany_detail());
        holder.cmpeligi.setText(cmp.getGpa()+"%");
        Log.v("MSG",cmp.getGpa());
        holder.cmpvenue.setText(cmp.getCompany_venue());
        holder.cmpvac.setText(cmp.getCompany_vacancy());
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

