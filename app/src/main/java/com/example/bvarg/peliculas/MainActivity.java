package com.example.bvarg.peliculas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView grid;
    GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (GridView) findViewById(R.id.grid);
        getWebsite();
    }
    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> titulo = new ArrayList<>();
                final ArrayList<String> estrellas = new ArrayList<>();
                final ArrayList<String> meta = new ArrayList<>();
                final ArrayList<String> portada = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();
                    //String title = doc.title();
                    Elements links = doc.select(".article .lister .lister-list .lister-item .lister-item-image a img");
                    int i = 0;
                    for (Element link : links) {
                        titulo.add(link.attr("alt"));
                        portada.add(link.attr("src"));
                        i++;
                        if(i==21){
                            break;
                        }
                    }
                    i = 0;
                    links = doc.select(".article .lister .lister-list .lister-item .lister-item-content .ratings-bar .inline-block strong");
                    for (Element link : links) {
                        estrellas.add(link.toString().substring(8,11));
                        i++;
                        if(i==21){
                            break;
                        }
                    }
                    i = 0;
                    links = doc.select(".article .lister .lister-list .lister-item .lister-item-content .ratings-bar .inline-block .metascore");
                    for (Element link : links) {
                        if(link.toString().substring(34,35).equals(">")){
                            meta.add(link.toString().substring(35,37));
                        }
                        else if(link.toString().substring(30,31).equals(">")){
                            meta.add(link.toString().substring(31,33));
                        }
                        else{
                            meta.add(link.toString().substring(37,39));
                        }
                        i++;
                        if(i==21){
                            break;
                        }
                    }
                } catch (IOException e) {
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //TextView grid2 = (TextView) findViewById(R.id.textView2);
                        //grid2.setText(String.valueOf(titulo.size())+" "+String.valueOf(meta.size())+" "+String.valueOf(estrellas.size())+" "+String.valueOf(portada.size()));
                        adapter = new GridAdapter(MainActivity.this,titulo,estrellas,meta,portada);
                        grid.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}
