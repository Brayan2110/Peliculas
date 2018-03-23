package com.example.bvarg.peliculas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Bvarg on 22/03/2018.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> titulo;
    private ArrayList<String> estrella;
    private ArrayList<String> meta;
    private ArrayList<String> portada;

    public GridAdapter(Context context, ArrayList titulo, ArrayList estrella, ArrayList meta, ArrayList portada){
        this.context = context;
        this.titulo = titulo;
        this.estrella = estrella;
        this.meta = meta;
        this.portada = portada;
    }

    @Override
    public int getCount() {
        return titulo.size();
    }

    @Override
    public Object getItem(int position) {
        return titulo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_grid,null);
        }

        TextView titulo = (TextView)convertView.findViewById(R.id.Titulo);
        titulo.setText(this.titulo.get(position));
        TextView meta = (TextView)convertView.findViewById(R.id.Meta);
        meta.setText(this.meta.get(position));
        TextView numero = (TextView)convertView.findViewById(R.id.Numero);
        numero.setText(String.valueOf(position+1));
        RatingBar estrellas = (RatingBar) convertView.findViewById(R.id.estrellas);
        estrellas.setRating(Float.parseFloat(this.estrella.get(position)));
        ImageView portada = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(context.getApplicationContext()).load(this.portada.get(position)).into(portada);

        return convertView;
    }
}
