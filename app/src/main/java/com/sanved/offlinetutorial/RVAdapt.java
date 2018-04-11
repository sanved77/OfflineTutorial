package com.sanved.offlinetutorial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Sanved on 21-05-2016.
 */
public class RVAdapt extends RecyclerView.Adapter<RVAdapt.DataHolder> {

    ArrayList<DataKaRakhwala> list;
    static ArrayList<DataKaRakhwala> list2;

    Context context;

    RVAdapt(ArrayList<DataKaRakhwala> list, Context context) {
        this.list = list;
        list2 = list;
        this.context = context;
    }

    public static class DataHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPics;
        CardView cv;

        DataHolder(final View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvListName);
            tvPics = (TextView) v.findViewById(R.id.numpics);
            cv = (CardView) v.findViewById(R.id.cvList);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(v.getContext(), PdfScreen.class);
                    String strName = "" + list2.get(getAdapterPosition()).getFilename();
                    i.putExtra("filename", strName);
                    v.getContext().startActivity(i);
                }

            });
        }

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        DataHolder dh = new DataHolder(v);
        return dh;
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        holder.tvName.setText(list.get(position).getTitle());
        holder.tvPics.setText("" + list.get(position).getPageno() + " pages");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
