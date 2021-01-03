package com.example.check;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonHttpParse {
    String FinalHttpData = "";
    String Result ;
    BufferedWriter bufferedWriter ;
    OutputStream outputStream ;
    BufferedReader bufferedReader ;
    StringBuilder stringBuilder = new StringBuilder();
    URL url;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String postRequest(String jsonInputString, String HttpUrlHolder) {

        try {
            url = new URL(HttpUrlHolder);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(14000);

            httpURLConnection.setConnectTimeout(14000);

            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");

            httpURLConnection.setRequestProperty("Accept", "application/json");

//<<<<<<< HEAD:app/src/main/java/my/awesome/Garaz/JsonHttpParse.java



            // String jsonInputString="{\"method\":\"login\",\"customer_email\":\""+Email+"\",\"customer_password\":\""+Password+"\"}";
//=======
            // String jsonInputString="{\"method\":\"login\",\"customer_email\":\""+Email+"\",\"customer_password\":\""+Password+"\"}";
//>>>>>>> Aman:app/src/main/java/com/example/custom7/JsonHttpParse.java

            // String jsonInputString="{\"method\":\"login\",\"customer_email\":\"aman123@gmail.com\",\"customer_password\":\"aman@123\"}";
//>>>>>>> d09525c5b5723475dcb70a49dd6483cbaec19c30

            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            try(OutputStream os = httpURLConnection.getOutputStream()){
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }


            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                FinalHttpData=response.toString();
                //System.out.println(response.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return FinalHttpData;
    }
}
