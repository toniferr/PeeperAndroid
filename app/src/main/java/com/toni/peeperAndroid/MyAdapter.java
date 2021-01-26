package com.toni.peeperandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        // View Holder Pattern
        ViewHolder holder;

        if (convertView == null) {
            //inflamos la vista que nos ha llegado en nuestro layout
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            //Referenciamos el elemento a modificar y lo rellenamos
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder); //se podria añadir id

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //cogemos valor actual dependiente de la posicion
        String currentName = names.get(position);
        //currentName = (String) getItem(position); se puede usar asi

        //Referenciamos el elemento a modificar y lo rellenamos
        holder.nameTextView.setText(currentName);

        //devolvemos la vista
        return convertView;
    }

    static class ViewHolder {
        private TextView nameTextView;

    }
}
