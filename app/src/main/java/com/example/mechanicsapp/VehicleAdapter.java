package com.example.mechanicsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VehicleAdapter extends BaseAdapter {
    private Context context;
    private List<Vehicle> vehicleList;
    private LayoutInflater inflater;

    public VehicleAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return vehicleList.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.vehicle_item, parent, false);
        }

        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView txtBrand = convertView.findViewById(R.id.txtBrand);
        TextView txtYear = convertView.findViewById(R.id.txtYear);

        Vehicle vehicle = vehicleList.get(position);

        txtId.setText(String.valueOf(vehicle.idVehicle));
        txtBrand.setText(vehicle.brand);
        txtYear.setText(String.valueOf(vehicle.year));

        return convertView;
    }
}
