package com.example.hasantarek.vaccinedetailasandschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan Tarek on 10/13/2017.
 */
public class VaccineInfoAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public VaccineInfoAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class handler
    {

        TextView Vname;
        TextView Vdate;

    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        handler datahandaler;
        datahandaler = new handler();
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.vaccine_info_row,parent,false);
            datahandaler.Vname = (TextView) row.findViewById(R.id.v_name_id);
            datahandaler.Vdate = (TextView)row.findViewById(R.id.v_date_id);
            row.setTag(datahandaler);

        }
        else
        {
            datahandaler = (handler) row.getTag();
        }
        VaccineInfoProvider vaccineInfoProvider;
        vaccineInfoProvider = (VaccineInfoProvider) this.getItem(position);

        datahandaler.Vname.setText(vaccineInfoProvider.getVaccine());
        datahandaler.Vdate.setText(vaccineInfoProvider.getVaccineDate());

        return row;
    }
}