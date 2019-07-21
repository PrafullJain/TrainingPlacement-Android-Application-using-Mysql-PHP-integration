package com.example.abhi.trainingplacement;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SliderLayout sliderLayout;
    Button register;
    private ProgressBar pb;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private Adapter mAdapter;

    private LayoutInflater mInflater;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    List<Companies> rowListItem;

    EditText Email,user,message;
    int white = Color.parseColor("#ffffff");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        Toolbar toolbar_main = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_main);
        toolbar_main.setTitleTextColor(white);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterCompany.class);
                startActivity(i);
            }
        });

        pb=findViewById(R.id.progressBar);
        mInflater = LayoutInflater.from(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        rowListItem = new ArrayList<>();
        mLayoutManager = new GridLayoutManager(MainActivity.this,3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sliderLayout = (SliderLayout) findViewById(R.id.imageSlider1);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews ();

        Intent in=getIntent();
        if(in.hasExtra("JSONData")) {
            try {
                JSONObject jsonObj = new JSONObject(in.getStringExtra("JSONData"));
                JSONArray ar1 = jsonObj.getJSONArray("companies");
                refreshCompanyLis(ar1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
        {
            readCompanies();
        }
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
            mAdapter =new Adapter(MainActivity.this,rowListItem);
            mRecyclerView.setAdapter(mAdapter);
            pb.setVisibility(View.GONE);
        }catch (JSONException e)
        {
            Log.v("JSON Exception:  ",e.getMessage());
            Toast.makeText(MainActivity.this,"JSON Exception: "+e.getMessage(),Toast.LENGTH_LONG).show();
            pb.setVisibility(View.GONE);
        }
    }

    private void setSliderViews () {
        int i = 0;
        SliderView sliderView = new SliderView(this);
        for (i = 0; i <7; i++) {
            sliderView = new SliderView(this);
            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.pu_8);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.pu_2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.pu_1);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.pu_4);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.pu_5);
                    break;
                case 5:
                    sliderView.setImageDrawable(R.drawable.pu_6);
                    break;
                case 6:
                    sliderView.setImageDrawable(R.drawable.pu_7);
                    break;

            }
            sliderLayout.addSliderView(sliderView);
        }
        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment frag=null;
        switch (id) {
            case R.id.nav_profile:
                //frag = new ProfileFragement();
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_profile,new ProfileFragement()).commit();
                Toast.makeText(MainActivity.this,"Code not developed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_noti:
                Toast.makeText(MainActivity.this,"Code not developed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_regStu:
                startActivity(new Intent(MainActivity.this,StudentList.class));
                break;
            case R.id.nav_regcomp:
                startActivity(new Intent(MainActivity.this,RegisterComp.class));
                break;
            case R.id.logout:
                alert();
                break;
            case R.id.nav_aboutus:
                Intent i=new Intent(MainActivity.this,AboutUs.class);
                startActivity(i);
                break;
            case R.id.nav_share:
                Toast.makeText(MainActivity.this,"Code not developed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rate_us:
                communicate();
                break;
        }
        if (frag != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_main, frag);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                Intent i = new Intent(MainActivity.this, Login.class);
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
        final Dialog dialog = new Dialog(MainActivity.this);
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
                    Toast.makeText(MainActivity.this, "Message is successfully sent", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }
}
