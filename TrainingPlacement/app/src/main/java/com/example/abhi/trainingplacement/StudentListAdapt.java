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

public class StudentListAdapt extends RecyclerView.Adapter<StudentListAdapt.ViewHolder>
{
    private List<Student> itemList;
    private Context context;

    public StudentListAdapt(Context context, List<Student> itemList)
    {

        this.context = context;
        this.itemList = itemList;

    }


    @Override
    public StudentListAdapt.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlistrecy_layout, parent, false);
        StudentListAdapt.ViewHolder vh = new StudentListAdapt.ViewHolder(v);
        return vh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView regStudName,regStudEligi;

        public ViewHolder(View itemView)
        {
            super(itemView);
            regStudName=(TextView) itemView.findViewById(R.id.regStudName);
            regStudEligi=(TextView)itemView.findViewById(R.id.regStudEligi);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Student cmp=itemList.get(position);
        holder.regStudName.setText(cmp.getName());

        holder.regStudEligi.setText("B.Tech Average GPA: "+cmp.getGpa()+" ,12%: "+cmp.getTwelveth()+" ,10%: "+cmp.getTenth());

    }
@Override
    public int getItemCount() {
        return itemList.size();
    }
}

