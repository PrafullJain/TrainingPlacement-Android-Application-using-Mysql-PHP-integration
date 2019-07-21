package com.example.abhi.trainingplacement;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.net.NetworkInfo.*;

public class Login extends AppCompatActivity {
EditText emailver,passwordver;
TextView forgot,registerlink;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;
   private String em,name,regno,mobno,alt_mob_no,twel,tenth,gpa;


    Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailver=(EditText)findViewById(R.id.email);
        passwordver=(EditText)findViewById(R.id.password);
        registerlink=findViewById(R.id.registerlink);

        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,UserRegistration.class);
                startActivity(i);

            }
        });
        forgot=(TextView)findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this,"Link generation",Toast.LENGTH_SHORT).show();
            }
        });
       log=(Button)findViewById(R.id.login);
        log.setOnClickListener(new View.OnClickListener()
       {

            @Override
           public void onClick(View v) {
                String email1=emailver.getText().toString();
                String pass1=passwordver.getText().toString();
                if(TextUtils.isEmpty(email1))
                {
                    emailver.setError("Please enter valid email id");
                    emailver.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(pass1))
                {
                    passwordver.setError("Please enter valid password");
                    passwordver.requestFocus();
                    return;
                }
                if(email1.equals("admin@poornima.edu.in"))
               {
                   if(pass1.equals("123")) {
                       startActivity(new Intent(Login.this, MainActivity.class));

                   }else
                   {
                       Toast.makeText(Login.this,"Please enter the valid password",Toast.LENGTH_SHORT).show();
                       return;
                   }
               }
               else if(email1.equals("praffuljaincp@gmail.com") && pass1.equals("1234"))
                {
                    startActivity(new Intent(Login.this, StudentActivity.class));

                }
                else
                {
                    validate(email1);
                }
           }
       });

     }
    /*String pass1*/
    private void validate(String email1) {

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READRAND_STUDENT + email1, null, CODE_GET_REQUEST);
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
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(Login.this, "You're Successfully Logged in", Toast.LENGTH_SHORT).show();
                    operation(object.getJSONArray("students"));
                }
                else
                {
                    Toast.makeText(Login.this, "Something Went Wrong,Please Check Your Email-Address & Password...??", Toast.LENGTH_SHORT).show();
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

    private void operation(JSONArray students)throws JSONException {

        try {
            for (int i = 0; i < students.length(); i++) {
                JSONObject obj = students.getJSONObject(i);
                Student s=new Student(
                        obj.getString("name"),
                        obj.getString("email"),
                        obj.getString("registration_no"),
                        obj.getString("mob_no"),
                        obj.getString("alt_mob_no"),
                        obj.getString("password"),
                        obj.getString("gpa"),
                        obj.getString("twelveth"),
                        obj.getString("tenth")
                );
                em=s.getEmail();
                name=s.getName();
                regno=s.getRegistration_no();
                mobno=s.getMob_no();
                alt_mob_no=s.getAlt_mob_no();
                gpa=s.getGpa();
                twel=s.getTwelveth();
                tenth=s.getTenth();

            }
            Intent i1=new Intent(Login.this,StudentActivity.class);
            i1.putExtra("Student Email", em);
            i1.putExtra("Student Name", name);
            i1.putExtra("Student regno", regno);
            i1.putExtra("Student mobno",mobno );
            i1.putExtra("Student altmobno", alt_mob_no);
            i1.putExtra("Student gpa", gpa);
            i1.putExtra("Student twelveth", twel);
            i1.putExtra("Student tenth", tenth);

            startActivity(i1);
        }catch (JSONException e)
        {
            Log.v("JSON Exception:  ",e.getMessage());
            Toast.makeText(Login.this,"Something Went Wrong,Please Contact to Developer/Admin",Toast.LENGTH_LONG).show();
        }
    }




}
