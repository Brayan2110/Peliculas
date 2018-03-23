package com.example.bvarg.peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

        return convertView;
    }
}
