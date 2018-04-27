package com.androindian.raj.roommanagement;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androindian.raj.roommanagement.databinding.ActivitySavingViewBinding;

public class SavingView extends AppCompatActivity {

    ActivitySavingViewBinding activitySavingViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activitySavingViewBinding= DataBindingUtil.
               setContentView(SavingView.this,R.layout.activity_saving_view);

       activitySavingViewBinding.addsave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(SavingView.this,AddSavingType.class);
               startActivity(intent);
           }
       });
    }
}
