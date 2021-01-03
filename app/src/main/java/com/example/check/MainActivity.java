package com.example.check;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    EditText email,password;
    String HttpURL = "https://mob.odialagna.com/loginAction.php";
    Button signin;
    String stremail, strpassword;
    String finalResult ;
    Boolean CheckEditText ;
    ArrayList<String> arr=new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();
    ProgressDialog progressDialog;
    HttpParse httpParse = new HttpParse();

  //  JsonHttpParse jsonhttpParse = new JsonHttpParse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.emailcontainer);
        password=findViewById(R.id.passwordcontainer);
        signin=findViewById(R.id.loginbtn);
stremail=email.getText().toString().trim();
strpassword=password.getText().toString().trim();
    }

    public void onClickSignInBtn(View view) {
        UserLoginFunction("login",stremail,strpassword);
    }
    public void UserLoginFunction(final String act,final String username, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MainActivity.this,"Authenticating User...",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();

                if(httpResponseMsg.contains("retur")){
                    try {
                        JSONObject jsonObject = new JSONObject(httpResponseMsg);
                        JSONArray result = jsonObject.getJSONArray("ret");
                        for (int i=0; i<result.length(); i++ ){
                            JSONObject ob=result.getJSONObject(i);
                            Toast.makeText(MainActivity.this, ""+ob, Toast.LENGTH_SHORT).show();
                            //fetch image here and set to adapter
                            // Toast.makeText(Login.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
                            // homemodel history=new homemodel(R.drawable.promocodecar2,ob.getString("img"),
                            //ob.getString("service_name"),ob.getString("sch_servie_id"));
                            // androidFlavors.add(history);



                            //to check if user is already login or not


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //
                    //  Toast.makeText(Login.this, httpResponseMsg, Toast.LENGTH_SHORT).show();


                }else{
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(httpResponseMsg);
                        String messege = jsonObject.getString("msg");
                      //  Toast.makeText(MainActivity.this, messege, Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {
                 //arr.add(params[0]);
                // arr.add(params[1]);
                // arr.add(params[2]);
                hashMap.put("act",params[0]);
                hashMap.put("username",params[1]);
                hashMap.put("password",params[2]);
                //  String jsonInputString="{\"act\":\"login\",\"username\":\"memberusername\",\"password\":\"memberpassword\"}";

//                finalResult = jsonhttpParse.postRequest(method,Email,Password, HttpURL);
                finalResult = httpParse.postRequest(hashMap,HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(act,username,password);
    }

}



