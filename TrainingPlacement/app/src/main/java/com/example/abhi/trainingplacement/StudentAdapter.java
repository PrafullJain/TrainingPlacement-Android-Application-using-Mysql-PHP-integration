package com.example.abhi.trainingplacement;




import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>
{
    private List<Companies> itemList;
    private Context context;

    public StudentAdapter(Context context, List<Companies> itemList)
    {

        this.context = context;
        this.itemList = itemList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studenthomerecycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NameCompany;
        ImageView logo;


        public ViewHolder(View itemView)
        {
            super(itemView);
            NameCompany = (TextView) itemView.findViewById(R.id.NameCompany);
            logo= (ImageView) itemView.findViewById(R.id.logo);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Companies cmp=itemList.get(position);

        holder.NameCompany.setText(cmp.getCompany_name());
        holder.logo.setImageResource(R.drawable.pu_logo);
        holder.NameCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ViewCompany.class);
                i.putExtra("Company name",cmp.getCompany_name());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}


