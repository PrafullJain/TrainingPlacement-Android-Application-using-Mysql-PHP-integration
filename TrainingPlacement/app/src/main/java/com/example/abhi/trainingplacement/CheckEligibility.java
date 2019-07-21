package com.example.abhi.trainingplacement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckEligibility extends AppCompatActivity {
    TextView texteli,check;
    ImageView cut;
    Button formbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_eligibility);
        cut=findViewById(R.id.cut);
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CheckEligibility.this,StudentActivity.class);
                startActivity(i);
                finish();
            }
        });
        texteli=findViewById(R.id.texteli);
        check=findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckEligibility.this, "Will check eligibility and activate buttons", Toast.LENGTH_SHORT).show();
            }
        });
        formbtn=findViewById(R.id.formbtn);
        formbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CheckEligibility.this,RegisterForDrive.class);
                startActivity(i);
            }
        });
    }
}
