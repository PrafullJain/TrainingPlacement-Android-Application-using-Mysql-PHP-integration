package com.example.abhi.trainingplacement;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserRegistration extends AppCompatActivity {
    EditText name,email,regisNo,contact,altcontact,password,gpa,twelveth,tenth;
    Button registerbtn;
    TextView loginlink;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        regisNo=findViewById(R.id.regisNo);
        contact=findViewById(R.id.contact);
        altcontact=findViewById(R.id.altcontact);
        password=findViewById(R.id.password);
        registerbtn=findViewById(R.id.registerbtn);
        gpa=findViewById(R.id.gpa);
        twelveth=findViewById(R.id.twelveth);
        tenth=findViewById(R.id.tenth);

        registerbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name1=name.getText().toString();
                String email1=email.getText().toString();
                String regisNo1=regisNo.getText().toString();
                String contact1=contact.getText().toString();
                String altcontact1=altcontact.getText().toString();
                String password1=password.getText().toString();
                String gpa1=gpa.getText().toString();
                String twelveth1=twelveth.getText().toString();
                String tenth1=tenth.getText().toString();


                if(name1.isEmpty())
                {
                    name.setError("Please enter your name");
                    name.requestFocus();
                    return;
                }
                if(email1.isEmpty())
                {
                    email.setError("Please enter your email address");
                    email.requestFocus();
                    return;

                }
               if(regisNo1.isEmpty())
                {
                    regisNo.setError("Please enter your registration no");
                    regisNo.requestFocus();
                    return;
                }
                 if(contact1.isEmpty())
                {
                    contact.setError("Please enter your contact no");
                    contact.requestFocus();
                    return;
                }
                if(altcontact1.isEmpty())
                {
                    altcontact.setError("Please enter your alternate contact no");
                    altcontact.requestFocus();
                    return;
                }
                if(password1.isEmpty())
                {
                    password.setError("Please set a password");
                    password.requestFocus();
                    return;
                }
                if(gpa1.isEmpty())
                {
                    gpa.setError("Please provide your GPA%");
                    gpa.requestFocus();
                    return;
                }
                if(twelveth1.isEmpty())
                {
                    twelveth.setError("Please provide your 12%");
                    twelveth.requestFocus();
                    return;
                }
                if(tenth1.isEmpty())
                {
                    tenth.setError("Please provide your 10%");
                    tenth.requestFocus();
                    return;
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("name", name1);
                params.put("email",email1);
                params.put("registration_no", regisNo1);
                params.put("mob_no", contact1);
                params.put("alt_mob_no", altcontact1);
                params.put("password", password1);
                params.put("gpa", gpa1);
                params.put("twelveth", twelveth1);
                params.put("tenth", tenth1);
                PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_STUDENT, params,CODE_POST_REQUEST);
                request.execute();

                name.setText("");
                email.setText("");
                contact.setText("");
                altcontact.setText("");
                password.setText("");
                regisNo.setText("");

            }
        });

        loginlink=findViewById(R.id.loginlink);
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserRegistration.this,Login.class);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                startActivity(i);
            }
        });
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

                        Toast.makeText(UserRegistration.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent i1=new Intent(UserRegistration.this,Login.class);
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
