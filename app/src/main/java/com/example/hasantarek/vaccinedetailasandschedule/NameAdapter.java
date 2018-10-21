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
 * Created by Hasan Tarek on 10/9/2017.
 */
public class NameAdapter extends ArrayAdapter {
    ArrayList<NameProvider> list = new ArrayList();

    public NameAdapter(Context context, int resource,ArrayList<NameProvider>list) {
        super(context, resource);
        this.list = list;
    }

    static class handler
    {

        TextView name;
    }

   /* @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }*/

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
            row = inflater.inflate(R.layout.name_row_layout,parent,false);
            datahandaler.name = (TextView) row.findViewById(R.id.name_list_id);
            row.setTag(datahandaler);

        }
        else
        {
            datahandaler = (handler) row.getTag();
        }
        NameProvider nameProvider;
        nameProvider = (NameProvider) this.getItem(position);

        datahandaler.name.setText(nameProvider.getName());

        return row;
    }
}