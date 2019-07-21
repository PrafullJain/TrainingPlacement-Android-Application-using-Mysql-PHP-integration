package com.example.abhi.trainingplacement;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentActivity extends AppCompatActivity{
    EditText Email,user,message;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private Adapter mAdapter;
    private LayoutInflater mInflater;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    List<Companies> rowListItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar_main = (Toolbar) findViewById(R.id.toolbar_stu);
        setSupportActionBar(toolbar_main);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.stud_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));



        mInflater = LayoutInflater.from(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.stud_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        rowListItem = new ArrayList<>();
        mLayoutManager = new GridLayoutManager(StudentActivity.this,3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent i1=getIntent();
        final String name=(String)i1.getStringExtra("Student Name");
        final String email=(String)i1.getStringExtra("Student Email");
        final String regno=(String)i1.getStringExtra("Student regno");
        final String mobno=(String)i1.getStringExtra("Student mobno");
        final String altmobno=(String)i1.getStringExtra("Student alt_mob_no");
        final String gpa=(String)i1.getStringExtra("Student gpa");
        final String twe=(String)i1.getStringExtra("Student twelveth");
        final String tenth=(String)i1.getStringExtra("Student tenth");

        NavigationView navigationView = (NavigationView) findViewById(R.id.stud_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_profile:
                        Intent i=new Intent(StudentActivity.this,StudentProfile.class);
                        i.putExtra("Stud_Email",email);
                        i.putExtra("Student Name", name);
                        i.putExtra("Student regno", regno);
                        i.putExtra("Student mobno",mobno );
                        i.putExtra("Student altmobno", altmobno);
                        i.putExtra("Student gpa", gpa);
                        i.putExtra("Student twelveth", twe);
                        i.putExtra("Student tenth", tenth);
                        startActivity(i);
                        break;
                    case R.id.nav_noti:
                        Toast.makeText(StudentActivity.this,"Code not developed",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_regComp:
                        Intent i1=new Intent(StudentActivity.this,RegisterComp2.class);
                        i1.putExtra("Stud_Email",email);
                        i1.putExtra("Student Name", name);
                        i1.putExtra("Student regno", regno);
                        i1.putExtra("Student mobno",mobno );
                        i1.putExtra("Student altmobno", altmobno);
                        i1.putExtra("Student gpa", gpa);
                        i1.putExtra("Student twelveth", twe);
                        i1.putExtra("Student tenth", tenth);
                        startActivity(i1);
                        break;
                    case R.id.form:
                        Intent intt=new Intent(StudentActivity.this,CheckEligibility.class);
                        startActivity(intt);
                        break;
                    case R.id.logout:
                        alert();
                        break;
                    case R.id.nav_aboutus:
                        Intent in=new Intent(StudentActivity.this,AboutUs.class);
                        startActivity(in);
                        break;
                    case R.id.nav_share:
                        Toast.makeText(StudentActivity.this,"Code not developed",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_rate_us:
                        communicate();
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.stud_drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        readCompanies();
    }
    private void readCompanies() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_COMPANY, null, CODE_GET_REQUEST);
        request.execute();

    }
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(View.GONE);
            //Toast.makeText(RegisterCompany.this,"Company Registered Successfully",Toast.LENGTH_SHORT).show();
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshCompanyLis(object.getJSONArray("companies"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);
            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    private void refreshCompanyLis(JSONArray companies) throws JSONException {

        rowListItem.clear();
        try {
            // JSONObject obj = new JSONObject(jsarrycomp);
            //JSONArray companies=obj.getJSONArray("company");
            for (int i = 0; i < companies.length(); i++) {
                JSONObject obj1 = companies.getJSONObject(i);

                rowListItem.add(new Companies(
                        obj1.getInt("company_id"),
                        obj1.getInt("company_post"),
                        obj1.getString("company_name"),
                        obj1.getString("company_detail"),
                        obj1.getString("company_venue"),
                        obj1.getString("company_vacancy"),
                        obj1.getString("company_vision"),
                        obj1.getString("company_mission"),
                        obj1.getString("trash"),
                        obj1.getString("date"),
                        obj1.getString("gpa"),
                        obj1.getString("twelveth"),
                        obj1.getString("tenth")
                ));
            }
            mAdapter =new Adapter(StudentActivity.this,rowListItem);
            mRecyclerView.setAdapter(mAdapter);
        }catch (JSONException e)
        {
            Log.v("JSON Exception:  ",e.getMessage());
            Toast.makeText(StudentActivity.this,"JSON Exception: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.stud_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void alert()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle("Wait a moment");
        dialog.setIcon(R.drawable.sure);
        dialog.setMessage("Are you sure you want to logout?");
        dialog.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Intent i = new Intent(StudentActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent();
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    public void communicate()
    {
        final Dialog dialog = new Dialog(StudentActivity.this);
        dialog.setContentView(R.layout.communicate);
        dialog.setTitle("Reach out to us");

        //Her add your textView and ImageView if you want

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        // if button is clicked, close the custom dialog
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        Email=(EditText)dialog.findViewById(R.id.text_email);
        user=(EditText)dialog.findViewById(R.id.text_name);
        message=(EditText)dialog.findViewById(R.id.message);
        Button btn_send=(Button)dialog.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String msg=message.getText().toString();
                String sEmail=Email.getText().toString();
                String sUser=user.getText().toString();
                if(sUser.isEmpty())
                {
                    user.setError("Please enter your Username");
                }
                else if(sEmail.isEmpty())
                {
                    Email.setError("Please enter your email");
                }

                else if(msg.isEmpty())
                {
                    message.setError("Please write a message");
                }
                else
                {
                    Toast.makeText(StudentActivity.this, "Message is successfully sent", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }
}
