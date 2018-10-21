package com.example.hasantarek.vaccinedetailasandschedule;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    TextView text;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        text = (TextView)view.findViewById(R.id.text_id);
        text.setText("Vaccines are very important for your child; " +
                "it protects your child against various dreaded " +
                "diseases like tetanus, diphtheria, pertussis" +
                " (whooping cough), polio, measles, hepatitis B," +
                " haemophilus influenza type b (which causes some " +
                "meningitis and pneumonia), yellow fever, tuberculosis," +
                " etc. Apollo Vaccination Centre offers a range of " +
                "vaccinations not only for your child, but also for adults " +
                "and travellers. ");


       // String htmlText = " %s ";
       /* String myData = "Hello World! This tutorial is to " +
                "show demo of displaying text with justify" +
                " alignment in WebView.";*/
        String text;
        text = "<html><head>" +"<style type=\"text/css\">body{color: #fff; font-size:20;}"+
                "</style></head>"+
                "<body><p align=\"justify\">";
        text+= "Vaccines are very important for your child; " +
                "it protects your child against various dreaded " +
                "diseases like tetanus, diphtheria, pertussis" +
                " (whooping cough), polio, measles, hepatitis B," +
                " haemophilus influenza type b (which causes some " +
                "meningitis and pneumonia), yellow fever, tuberculosis," +
                " etc. Apollo Vaccination Centre offers a range of " +
                "vaccinations not only for your child, but also for adults " +
                "and travellers. ";
        text+= "</p></body></html>";

        WebView webView = (WebView)view.findViewById(R.id.webView1);
        webView.loadData(text, "text/html", "utf-8");
        webView.setBackgroundColor(Color.parseColor("#795548"));


        return view;
    }

}
