package com.androindian.raj.roommanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androindian.raj.roommanagement.databinding.ActivityAddSavingTypeBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddSavingType extends AppCompatActivity {

    ActivityAddSavingTypeBinding activityAddSavingTypeBinding;
    //EditText ed,sd,amount,reason;

    ArrayList SavingArray=new ArrayList();
    ArrayAdapter arrayAdapter;
    String Name,Code,rescode;
    String UserMobile;

    JsonObject j2=null,j3=null;
    //Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddSavingTypeBinding=
                DataBindingUtil.setContentView(AddSavingType.this,R.layout.activity_add_saving_type);
       // spinner=findViewById(R.id.insert_spinner);


        SharedPreferences preferences=
                getSharedPreferences("Room",MODE_PRIVATE);
        UserMobile=preferences.getString("Mobile","");


        arrayAdapter=new ArrayAdapter(AddSavingType.this,
                android.R.layout.simple_list_item_1,SavingArray);
//spinner
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://androindian.com/apps/fm/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RoomManagement insertSave=retrofit.create(RoomManagement.class);

        JSONObject j1=new JSONObject();
        try {
            j1.put("action","get_saving_types");

            j2=new JsonParser().parse(j1.toString()).getAsJsonObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<InsertPojo> insertPojoCall=insertSave.insersavings(j2);

        insertPojoCall.enqueue(new Callback<InsertPojo>() {
            @Override
            public void onResponse(Call<InsertPojo> call, Response<InsertPojo> response) {

                String Data=response.body().getResponse();
                if(Data.equalsIgnoreCase("success")){

                    List<InsertPojoarray> data=response.body().getData();
                    for (int i=0;i<data.size();i++){
                        Code=data.get(i).getCode();
                        Name=data.get(i).getName();

                        SavingArray.add(Name);

                    }

                    activityAddSavingTypeBinding.insertSpinner.setAdapter(arrayAdapter);

                    activityAddSavingTypeBinding.insertSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            rescode=Code.toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<InsertPojo> call, Throwable t) {

            }
        });

//spinner//

       /* Button insert=findViewById(R.id.bt_insert_save);

        Spinner savetype=findViewById(R.id.insert_spinner);

        reason=findViewById(R.id.insert_reason);
        amount=findViewById(R.id.insert_amount);
        sd=findViewById(R.id.inset_startDate);
        ed=findViewById(R.id.insert_enddate);*/

        activityAddSavingTypeBinding.btInsertSave.
                setOnClickListener(new View.OnClickListener() {

            Retrofit  retrofit=new Retrofit.Builder()
                    .baseUrl("http://androindian.com/apps/fm/")
                    .addConverterFactory(GsonConverterFactory.create()).build();

            RoomManagement savetype=retrofit.create(RoomManagement.class);
            @Override
            public void onClick(View view) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("action","put_saving");
                    jsonObject.put("mobile",UserMobile.trim());
                    jsonObject.put("stype",rescode);
                    jsonObject.put("reason",activityAddSavingTypeBinding.insertReason.getText().toString().trim());
                    jsonObject.put("amount",activityAddSavingTypeBinding.insertAmount.getText().toString().trim());
                    jsonObject.put("start_date",activityAddSavingTypeBinding.insetStartDate.getText().toString().trim());
                    jsonObject.put("end_date",activityAddSavingTypeBinding.insertEnddate.getText().toString().trim());
                    j3=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Call<AddSavings> addSavingsCall=savetype.addsaving(j3);
                addSavingsCall.enqueue(new Callback<AddSavings>() {
                    @Override
                    public void onResponse(Call<AddSavings> call, Response<AddSavings> response) {

                        if(response.body().getResponse().equalsIgnoreCase("success")){

                            // String s2=response.body().

                            Toast.makeText(getApplicationContext(),"Scueess",Toast.LENGTH_SHORT).show();
                            /*Intent intent=new Intent(getApplicationContext(),
                                    SavingView.class);
                            startActivity(intent);*/

                            startActivity(new Intent(getApplicationContext(),SavingView.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<AddSavings> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

