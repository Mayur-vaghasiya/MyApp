package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Model.User;

import java.util.ArrayList;

/**
 * Created by peacock on 3/11/16.
 */
public class Listadapter extends BaseAdapter {

    Context context;
    ArrayList<User> listuser;

    public Listadapter(Context context, ArrayList<User> listitem) {
        this.context = context;
        this.listuser = listitem;

    }

    @Override
    public int getCount() {
        return listuser.size();
    }

    @Override
    public Object getItem(int position) {
        return listuser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutinflater;
        if(convertView ==null){
            layoutinflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutinflater.inflate(R.layout.listitem,null);
            holder=new Holder();
            holder.txtusername=(TextView)convertView.findViewById(R.id.textname);
            holder.txtusermail=(TextView)convertView.findViewById(R.id.textemail);
            holder.txtuserphone=(TextView)convertView.findViewById(R.id.textphone);
            holder.txtgender=(TextView)convertView.findViewById(R.id.textgender);
            holder.txthobbies=(TextView)convertView.findViewById(R.id.texthobbies);
            holder.lableuser=(TextView)convertView.findViewById(R.id.lblname);
            holder.lableemail=(TextView)convertView.findViewById(R.id.lblemail);
            holder.lableephone=(TextView)convertView.findViewById(R.id.lblphone);
            holder.lablegender=(TextView)convertView.findViewById(R.id.lblGender);
            holder.lblhobbies=(TextView)convertView.findViewById(R.id.lblHobbies);
            convertView.setTag(holder);
        }
        else {
            holder=(Holder)convertView.getTag();
        }
        holder.txtusername.setText(listuser.get(position).getName());
        holder.txtusermail.setText(listuser.get(position).getEmail());
        holder.txtuserphone.setText(listuser.get(position).getMobile());
        if (listuser.get(position).getGender()==0) {
            holder.txtgender.setText("Male");
        } else {
            holder.txtgender.setText("Female");

        }
        holder.txthobbies.setText(listuser.get(position).getHoboies());


        return convertView;
    }


    public class Holder {
       public TextView txtusername, txtusermail, txtuserphone, txtgender,txthobbies,lableuser,lableemail,lableephone,lablegender,lblhobbies;
    }
}
