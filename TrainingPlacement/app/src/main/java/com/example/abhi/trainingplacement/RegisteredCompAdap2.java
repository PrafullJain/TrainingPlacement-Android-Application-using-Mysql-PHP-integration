package com.example.abhi.trainingplacement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RegisteredCompAdap2 extends RecyclerView.Adapter<RegisteredCompAdap2.ViewHolder>
{

    private List<Companies> itemList;
    private Context context;
    protected  String name,regno,mobno,altmobno,gpa,twe,tenth,email1;
    protected  Double stdgpa,stdtwe,stdtenth;

    public RegisteredCompAdap2(Context context, List<Companies> itemList)
    {
        this.context = context;
        this.itemList = itemList;

        Intent i1= ((Activity) context).getIntent();
        name=(String)i1.getStringExtra("Student Name");
        email1=(String)i1.getStringExtra("Stud_Email");
        regno=(String)i1.getStringExtra("Student regno");
        mobno=(String)i1.getStringExtra("Student mobno");
        altmobno=(String)i1.getStringExtra("Student alt_mob_no");
        gpa=(String)i1.getStringExtra("Student gpa");
        twe=(String)i1.getStringExtra("Student twelveth");
        tenth=(String)i1.getStringExtra("Student tenth");

        stdgpa=Double.parseDouble(gpa.trim());
        stdtwe=Double.parseDouble(twe.trim());
        stdtenth=Double.parseDouble(tenth.trim());
    }
    @Override
    public RegisteredCompAdap2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.registercomprec2_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        final Companies cmp=itemList.get(position);
        holder.RegNameCompany.setText(cmp.getCompany_name());
        holder.compElig.setText("B.Tech Average GPA: "+cmp.getGpa()+" ,12%: "+cmp.getTwelveth()+" ,10%: "+cmp.getTenth());
        holder.logo.setImageResource(R.drawable.pu_logo);

        holder.RegCompEligBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
               if(Double.parseDouble(cmp.getGpa())<=stdgpa)
                {
                    if(Double.parseDouble(cmp.getTenth())<= stdtenth)
                    {
                        if(Double.parseDouble(cmp.getTwelveth())<=stdtwe)
                        {
                            Toast.makeText(context,name+" Eligible in the "+cmp.getCompany_name(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            Toast.makeText(context,name+" Not Eligible According to their Senior-Secondary % in the "+cmp.getCompany_name(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else
                    {
                        Toast.makeText(context,name+" Not Eligible According to their Senior % in the "+cmp.getCompany_name(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else
                {
                    Toast.makeText(context,name+" Not Eligible According to their GPA in the "+cmp.getCompany_name(),Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView RegNameCompany,compElig;
        Button RegCompEligBtn;
        ImageView logo;


        public ViewHolder(View itemView)
        {
            super(itemView);
            RegNameCompany = (TextView) itemView.findViewById(R.id.regCompName);
            compElig = (TextView) itemView.findViewById(R.id.compyEligi);
            logo= (ImageView) itemView.findViewById(R.id.logo1);
            RegCompEligBtn=(Button)itemView.findViewById(R.id.eligbtn);

        }
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

