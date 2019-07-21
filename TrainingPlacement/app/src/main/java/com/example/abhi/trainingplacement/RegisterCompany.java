package com.example.abhi.trainingplacement;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class RegisterCompany extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11;
    private Button b1;
    List<Companies> rowListItem;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        rowListItem=new ArrayList<>();

        ed1=findViewById(R.id.companyId);
        ed2=findViewById(R.id.companyName);
        ed3=findViewById(R.id.companyDetail);
        ed4=findViewById(R.id.companyVenue);
        ed5=findViewById(R.id.companyVacancy);
        ed6=findViewById(R.id.companyPost);
        ed7=findViewById(R.id.companyVision);
        ed8=findViewById(R.id.companyMission);
        ed9=findViewById(R.id.cmpGpa);
        ed10=findViewById(R.id.cmptwelveth);
        ed11=findViewById(R.id.cmptenth);



        b1=findViewById(R.id.buttonCreateCompany);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCompany();
            }
        });

        progressBar=findViewById(R.id.progressBar);


    }

    private void createCompany() {
        String id = ed1.getText().toString().trim();
        String name = ed2.getText().toString().trim();
        String detail = ed3.getText().toString().trim();
        String venue = ed4.getText().toString().trim();
        String vacancy = ed5.getText().toString().trim();
        String post = ed6.getText().toString().trim();
        String vision = ed7.getText().toString().trim();
        String mission = ed8.getText().toString().trim();
        String gpa=ed9.getText().toString().trim();
        String twelveth=ed10.getText().toString().trim();
        String tenth=ed11.getText().toString().trim();

        String trash="1";
        String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        Log.v("Date: ",date);
        if (TextUtils.isEmpty(id)) {
            ed1.setError("Please provide the Company-Id");
            ed1.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(name)) {
            ed2.setError("Please Enter Company Name");
            ed2.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(detail)) {
            ed3.setError("Please Enter the Company Detail");
            ed3.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(venue)) {
            ed4.setError("Please provide Venue for Company Drive");
            ed4.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(vacancy)) {
            ed5.setError("Please provide the Available Company Vacancy");
            ed5.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(post)) {
            ed6.setError("Please provide the Available Company Post");
            ed6.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(vision)) {
            ed7.setError("Please provide the Company Vision");
            ed7.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mission)) {
            ed8.setError("Please provide the Company Mission");
            ed8.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(gpa)) {
            ed9.setError("Please provide the Company GPA% Criteria");
            ed9.requestFocus();
            return;
        }if (TextUtils.isEmpty(twelveth)) {
            ed10.setError("Please provide the Company 12% Criteria");
            ed10.requestFocus();
            return;
        }if (TextUtils.isEmpty(tenth)) {
            ed11.setError("Please provide the Company 10% Criteria");
            ed11.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("detail",detail);
        params.put("venue", venue);
        params.put("vacancy", vacancy);
        params.put("post", post);
        params.put("vision", vision);
        params.put("mission", mission);
        params.put("trash", trash);
        params.put("date", date);
        params.put("gpa", gpa);
        params.put("twelveth",twelveth);
        params.put("tenth", tenth);


        progressBar.setVisibility(View.VISIBLE);
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_COMPANY, params,CODE_POST_REQUEST);
        request.execute();

        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");
        ed8.setText("");
        ed9.setText("");
        ed10.setText("");
        ed11.setText("");

    }



    public void readCompanies() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_COMPANY, null, CODE_GET_REQUEST);
        request.execute();
    }


    public void getCompany(String n1) {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READRAND_COMPANY + n1, null, CODE_GET_REQUEST);
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

                         Toast.makeText(RegisterCompany.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent i1=new Intent(RegisterCompany.this,MainActivity.class);
                        i1.putExtra("JSONData",object.toString());
                        startActivity(i1);
                       // startActivity(new Intent(RegisterCompany.this, MainActivity.class));
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

}
