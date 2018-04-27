package com.androindian.raj.roommanagement;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.androindian.raj.roommanagement.databinding.ActivityRoomMenuBinding;

public class RoomMenu extends AppCompatActivity {

    ActivityRoomMenuBinding activityRoomMenuBinding;

    String Menu[]={"Savings","Group","Transaction"};
    int []Menuimges={R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRoomMenuBinding= DataBindingUtil.setContentView(
                this,R.layout.activity_room_menu);

        activityRoomMenuBinding.menulist.setAdapter(new RoomMenuAdapter(RoomMenu.this,
                Menu,Menuimges));

        activityRoomMenuBinding.menulist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick
                    (AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        Intent intent=new Intent(getApplicationContext(),SavingView.class);
                        startActivity(intent);
                        break;

                }

            }
        });




    }
}
