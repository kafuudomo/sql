package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {

    private ArrayList<Contact> data;
    private Context context;

    public Adapter(Context context, ArrayList<Contact> data) {

        super(context, R.layout.contact_item);
        this.data = data;
        this.context = context;

    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View v;
        if (convertView == null) {

            holder = new ViewHolder();
            v = LayoutInflater.from(context).inflate(R.layout.contact_item, null);

            holder.tvname = v.findViewById(R.id.name);
            holder.tvphoneNumber = v.findViewById(R.id.phoneNumber);


            v.setTag(holder);
        } else {
            v = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        Contact model = (Contact) getItem(position);

        holder.tvname.setText(model.getName());
        holder.tvphoneNumber.setText(model.getPhoneNumber());


        return v;
    }

    class ViewHolder {
        TextView tvname, tvphoneNumber;
    }

    public void updateData(ArrayList<Contact> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }
}

