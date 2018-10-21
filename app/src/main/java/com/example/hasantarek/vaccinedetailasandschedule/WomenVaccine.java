package com.example.hasantarek.vaccinedetailasandschedule;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WomenVaccine extends Fragment {

    FloatingActionButton floatingActionButton;
    public WomenVaccine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_women_vaccine, container, false);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.women_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddHuman.class);
                intent.putExtra("vaccine","womenvaccine");
                startActivity(intent);
            }
        });
        return view;
    }

}
