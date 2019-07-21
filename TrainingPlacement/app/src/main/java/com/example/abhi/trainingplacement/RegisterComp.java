package com.example.abhi.trainingplacement;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterComp extends AppCompatActivity {
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private RegisteredCompAdap mAdapter;
    private LayoutInflater mInflater;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private List<Companies> com=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_comp);
        mInflater = LayoutInflater.from(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(RegisterComp.this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getCompanies();
    }
    public void getCompanies() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_COMPANY , null, CODE_GET_REQUEST);
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
                    // Toast.makeText(ViewCompany.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                    operation(object.getJSONArray("companies"));

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

    private void operation(JSONArray companies)throws JSONException {
        com.clear();
        try {
            for (int i = 0; i < companies.length(); i++) {
                JSONObject obj = companies.getJSONObject(i);

                com.add(new Companies(
                        obj.getInt("company_id"),
                        obj.getInt("company_post"),
                        obj.getString("company_name"),
                        obj.getString("company_detail"),
                        obj.getString("company_venue"),
                        obj.getString("company_vacancy"),
                        obj.getString("company_vision"),
                        obj.getString("company_mission"),
                        obj.getString("trash"),
                        obj.getString("date"),
                        obj.getString("gpa"),
                        obj.getString("twelveth"),
                        obj.getString("tenth")

                ));
            }

            mAdapter = new RegisteredCompAdap(RegisterComp.this,com);
            mRecyclerView.setAdapter(mAdapter);
        }catch (JSONException e)
        {
            Log.v("JSON Exception:  ",e.getMessage());
            Toast.makeText(RegisterComp.this,"Something Went Wrong,Please Contact to Developer/Admin",Toast.LENGTH_LONG).show();

        }
    }

}
