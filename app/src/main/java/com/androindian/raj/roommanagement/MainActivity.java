package com.androindian.raj.roommanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.androindian.raj.roommanagement.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    JSONObject jsonObject=null;
    JsonObject j1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityMainBinding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);


       activityMainBinding.tvRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,Register.class);
               startActivity(intent);
           }
       });

       activityMainBinding.userMob.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if (charSequence.length() > 0) {
                   activityMainBinding.errMob.setVisibility(View.INVISIBLE);
               }
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

       activityMainBinding.userPass.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if (charSequence.length() > 0) {
                   activityMainBinding.errPass.setVisibility(View.INVISIBLE);
               }
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

        activityMainBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(activityMainBinding.userMob.getText().toString().isEmpty()||
                        activityMainBinding.userMob.getText().toString().length()<10
                        ||activityMainBinding.userMob.getText().toString().length()>10){
                    activityMainBinding.errMob.setVisibility(View.VISIBLE);
                }else if (activityMainBinding.userPass.getText().toString().isEmpty()){
                    activityMainBinding.errPass.setVisibility(View.VISIBLE);
                }else{

                    if (ConnectivityReceiver.isConnected(getApplicationContext())) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(" http://androindian.com/apps/fm/").
                                        addConverterFactory(GsonConverterFactory.create()).build();
                        RoomManagement roomManagement = retrofit.create(RoomManagement.class);

                        jsonObject = new JSONObject();
                        try {
                            jsonObject.put("mobile", activityMainBinding.userMob.getText().toString().trim());
                            jsonObject.put("pswrd", activityMainBinding.userPass.getText().toString().trim());
                            jsonObject.put("action", "login_user");
                            j1 = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Call<JsonObject> loginResCall = roomManagement.loginRes(j1);

                        loginResCall.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                Toast.makeText(MainActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();

                                LoginRes loginRes=new Gson().fromJson(response.body().toString(),LoginRes.class);
                                if (loginRes.getResponse().equalsIgnoreCase("success")) {

                                    SharedPreferences preferences =
                                            getSharedPreferences("Room", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("Mobile", loginRes.getData().getMobile());
                                    //editor.putString("Mobile",activityMainBinding.userMob.getText().toString());
                                    editor.putString("Password", activityMainBinding.userPass.getText().toString());
                                    editor.commit();

                                    Intent intent = new Intent(getApplicationContext(), RoomMenu.class);
                                    startActivity(intent);
                                }
                                Toast.makeText(MainActivity.this,
                                        "" + loginRes.getResponse(), Toast.LENGTH_SHORT).show();



                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                }





            }
        });


    }


}
