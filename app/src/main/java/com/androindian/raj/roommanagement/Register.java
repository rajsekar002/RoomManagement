package com.androindian.raj.roommanagement;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androindian.raj.roommanagement.databinding.ActivityRegisterBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;
    JsonObject j1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.
                setContentView(Register.this, R.layout.activity_register);



        activityRegisterBinding.UserReg.
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnectivityReceiver.isConnected(getApplicationContext())) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://androindian.com/apps/fm/").
                                    addConverterFactory(GsonConverterFactory.create()).build();
                    RoomManagement roomManagement = retrofit.create(RoomManagement.class);

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("mobile", activityRegisterBinding.RegMobile.getText().toString().trim());
                        jsonObject.put("pswrd", activityRegisterBinding.RegPass.getText().toString().trim());
                        jsonObject.put("email", activityRegisterBinding.RegEmail.getText().toString().trim());
                        jsonObject.put("name", activityRegisterBinding.RegName.getText().toString().trim());

                        jsonObject.put("action", "register_user");
                        Log.e("req_body", jsonObject.toString());

                        j1 = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Call<RegUser> regUserCall = roomManagement.Regres(j1);

                    regUserCall.enqueue(new Callback<RegUser>() {
                        @Override
                        public void onResponse(Call<RegUser> call, Response<RegUser> response) {
                            if (response.body() != null) {
                                Toast.makeText(Register.this,""+response.body(), Toast.LENGTH_SHORT).show();

                            }
                            if (response.body().getResponse().equalsIgnoreCase("success")) {

                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                                String res1 = String.valueOf(response.body().getResponse().equalsIgnoreCase("user"));

                                Toast.makeText(getApplicationContext(), "" + res1, Toast.LENGTH_SHORT).show();

                            } else if (response.body().getResponse().equalsIgnoreCase("failed")) {
                                String res1 = String.valueOf(response.body().getResponse().equalsIgnoreCase("user"));

                                Toast.makeText(getApplicationContext(), "" + res1, Toast.LENGTH_SHORT).show();

                            } else {
                                String res1 = String.valueOf(response.body().getResponse().equalsIgnoreCase("user"));

                                Toast.makeText(getApplicationContext(), "" + res1, Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<RegUser> call, Throwable t) {
                            Toast.makeText(Register.this, "" + t, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Register.this, call.request().url().toString(), Toast.LENGTH_SHORT).show();
                            Log.e("req_body", call.request().body().toString());
                        }
                    });

                }else {
                    Toast.makeText(Register.this, "No internet access", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}