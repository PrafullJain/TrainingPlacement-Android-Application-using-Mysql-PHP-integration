package com.example.abhi.trainingplacement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentProfile extends AppCompatActivity {
private TextView studname,studem,studregno,studmobno,studaltmobno,studgpa,studtwel,studtent;
private Student st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentprofile);
        Intent i1=getIntent();
        String name=(String)i1.getStringExtra("Student Name");
        String email1=(String)i1.getStringExtra("Stud_Email");
         String regno=(String)i1.getStringExtra("Student regno");
         String mobno=(String)i1.getStringExtra("Student mobno");
         String altmobno=(String)i1.getStringExtra("Student alt_mob_no");
         String gpa=(String)i1.getStringExtra("Student gpa");
         String twe=(String)i1.getStringExtra("Student twelveth");
         String tenth=(String)i1.getStringExtra("Student tenth");

        studname=findViewById(R.id.name);
        studem=findViewById(R.id.emailaddress);
        studregno=findViewById(R.id.registerno);
        studmobno=findViewById(R.id.mobno);
        studaltmobno=findViewById(R.id.altmobileNumber);
        studgpa=findViewById(R.id.gpa);
        studtwel=findViewById(R.id.twelveth);
        studtent=findViewById(R.id.tenth);

        studname.setText(name);
        studem.setText(email1);
        studregno.setText(regno);
        studmobno.setText(mobno);
        studaltmobno.setText(altmobno);
        studgpa.setText(gpa+"%");
        studtwel.setText(twe+"%");
        studtent.setText(tenth+"%");
    }
}
