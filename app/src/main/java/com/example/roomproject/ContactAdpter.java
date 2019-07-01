package com.example.roomproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdpter extends ArrayAdapter<Contact> {

    TextView name;
    TextView phone;
    public ContactAdpter( Context context,  List<Contact> contacts) {
        super(context, 0, contacts);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
        name=convertView.findViewById(R.id.name);
        phone=convertView.findViewById(R.id.phone);


        Contact contact=getItem(position);
        name.setText(contact.getName());
        phone.setText(contact.getPhone());

        return  convertView;
    }
}
