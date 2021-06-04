package com.example.viewinpdf.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewinpdf.R;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class GraphAdpt extends RecyclerView.Adapter<GraphAdpt.Holder>{
    Context context;
    String[] headline;
    String[] xTop;
    String[] xBottom;
    String[] yStart;
    int image;
    String[] cType,cCount,usage,feedback,collection,newTicket;


    public GraphAdpt(Context context, String[] headline, String[] xTop, String[] xBottom, String[] yStart, int image) {
        this.context = context;
        this.headline = headline;
        this.xTop = xTop;
        this.xBottom = xBottom;
        this.yStart = yStart;
        this.image = image;

    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_graph, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        if (position==0){
//            cType = new String[]{"MWC","FWC","PWD","MUR","TOTAL"};
//            cCount= new String[]{"10","10","10","10","50"};
//            usage= new String[]{"10","10","10","10","50"};
//            feedback=new String[]{"10","10","10","10","50"};
//            collection=new String[]{"10","10","10","10","50"};
//            newTicket=new String[]{"10","10","10","10","50"};
//
//            DataReportAdpt adapter = new DataReportAdpt(context,cType,cCount,usage,feedback,collection,newTicket);
//            holder.tableRecycler.setHasFixedSize(true);
//            holder.tableRecycler.setLayoutManager(new LinearLayoutManager(context));
//            holder.tableRecycler.setAdapter(adapter);
            holder.tableRecycler.setVisibility(View.GONE);

//            holder.headLine.setText(headline[position]);
            holder.headLine.setVisibility(View.GONE);
            holder.xTop.setVisibility(View.GONE);
            holder.xBottom.setVisibility(View.GONE);
            holder.yFront.setVisibility(View.GONE);
            holder.image.setVisibility(View.GONE);

        }
        else if(position >1 && position<5) {
            holder.tableRecycler.setVisibility(View.GONE);
            holder.headLine.setText(headline[position-1]);
            holder.xTop.setText(xTop[position-1]);
            holder.xBottom.setText(xBottom[position-1]);
            holder.yFront.setText(yStart[position-1]);
            holder.image.setImageResource(image);
        }
//        else {
//            holder.tableRecycler.setVisibility(View.GONE);
//            holder.headLine.setText(headline[4]);
//            holder.xTop.setText(xTop[4]);
//            holder.xBottom.setText(xBottom[4]);
//            holder.yFront.setText(yStart[4]);
//            holder.image.setImageResource(image);
//        }
    }

    @Override
    public int getItemCount() {
        return 13;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public TextView headLine;
        public TextView xTop;
        public TextView xBottom;
        public TextView yFront;
        public ImageView image;
        public RecyclerView tableRecycler;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
             headLine =itemView.findViewById(R.id.headline);
             xTop =itemView.findViewById(R.id.xTop);
             xBottom =itemView.findViewById(R.id.xBottom);
             yFront =itemView.findViewById(R.id.yStart);
             image =itemView.findViewById(R.id.imageView);
             tableRecycler =itemView.findViewById(R.id.table);
        }
    }
}
