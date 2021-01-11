package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemArrayAdapter extends ArrayAdapter<Customer> {

    ArrayList<Customer> customers;
    Activity context;
    public ItemArrayAdapter(@NonNull Activity context, @NonNull ArrayList<Customer> customers) {
        super(context, R.layout.list_view, customers);
        this.customers = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(R.layout.list_view,null,true);

        TextView names = v.findViewById(R.id.Tv1);
        TextView age = v.findViewById(R.id.TVAge);

        names.setText(this.customers.get(position).getName());
        age.setText(this.customers.get(position).getAge());
        return v;


    }
}
