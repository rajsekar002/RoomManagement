package com.androindian.raj.roommanagement;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.androindian.raj.roommanagement.databinding.CustomBinding;

/**
 * Created by Raj on 4/23/2018.
 */

public class RoomMenuAdapter extends BaseAdapter {
    String menu_item[];
    int img[];
    Context context;
    CustomBinding binding;

    public RoomMenuAdapter(RoomMenu roomMenu, String[] menu, int[] menuimges) {
   menu_item =menu;
   img=menuimges;
   context=roomMenu;
    }

    @Override
    public int getCount() {
        return menu_item.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       /* LayoutInflater inflater= (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View RowView=inflater.inflate(R.layout.custom,null);

        ImageView imageView=RowView.findViewById(R.id.menusimg);
        TextView textView=RowView.findViewById(R.id.menutext);

        textView.setText(menu_item[i]);
        imageView.setImageResource(img[i]);

        return RowView;
        */

       /* LayoutInflater inflater= (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View RowView;
        binding=DataBindingUtil.inflater.inflate(context,R.layout.custom,null);*/
       binding= DataBindingUtil.inflate
                (LayoutInflater.from(context),R.layout.custom,null,false);


        binding.menutext.setText(menu_item[i]);
        binding.menusimg.setImageResource(img[i]);

        //image action

        /*binding.menusimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        return binding.getRoot();
    }
}
