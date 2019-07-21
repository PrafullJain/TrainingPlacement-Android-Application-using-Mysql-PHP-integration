package com.example.abhi.trainingplacement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RegisteredCompAdap extends RecyclerView.Adapter<RegisteredCompAdap.ViewHolder>
        {
private List<Companies> itemList;
private Context context;

public RegisteredCompAdap(Context context, List<Companies> itemList)
        {

        this.context = context;
        this.itemList = itemList;

        }


@Override
public RegisteredCompAdap.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.registeredcomprecy_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
        }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
                final Companies cmp=itemList.get(position);

                holder.RegNameCompany.setText(cmp.getCompany_name());
                holder.compElig.setText("B.Tech Average GPA: "+cmp.getGpa()+" ,12%: "+cmp.getTwelveth()+" ,10%: "+cmp.getTenth());
                holder.logo.setImageResource(R.drawable.pu_logo);
                holder.RegNameCompany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(context,ViewCompany.class);
                        context.startActivity(i);
                    }
                });
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
    TextView RegNameCompany,compElig;
    ImageView logo;


    public ViewHolder(View itemView)
    {
        super(itemView);
        RegNameCompany = (TextView) itemView.findViewById(R.id.regCompName);
        compElig = (TextView) itemView.findViewById(R.id.compyEligi);
        logo= (ImageView) itemView.findViewById(R.id.logo1);

    }
}
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

