package com.example.mechanicsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ServiceAdapter extends BaseAdapter {
    private Context context;
    private List<Service> serviceList;
    private LayoutInflater inflater;

    public ServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return serviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.service_item, parent, false);
        }

        TextView txtServiceType = convertView.findViewById(R.id.txtServiceType);
        TextView txtDateOfService = convertView.findViewById(R.id.txtDateOfService);
        TextView txtKmService = convertView.findViewById(R.id.txtKmService);
        TextView txtNextKmService = convertView.findViewById(R.id.txtNextKmService);
        TextView txtOIB = convertView.findViewById(R.id.txtOIB);
        TextView txtVehicleId = convertView.findViewById(R.id.txtVehicleId);
        TextView txtServiceId = convertView.findViewById(R.id.txtServiceId);

        Service service = serviceList.get(position);

        txtServiceType.setText(service.serviceType);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtDateOfService.setText(sdf.format(service.dateOfService));
        txtKmService.setText(String.valueOf(service.kmService));
        txtNextKmService.setText(String.valueOf(service.nextKmService));
        txtOIB.setText(String.valueOf(service.oib));
        txtVehicleId.setText(String.valueOf(service.vehicleId));
        txtServiceId.setText(String.valueOf(service.serviceId));

        return convertView;
    }
}
