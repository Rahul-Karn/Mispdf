package com.example.viewinpdf.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewinpdf.MainActivity;
import com.example.viewinpdf.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GraphAdpt extends RecyclerView.Adapter<GraphAdpt.Holder>{
    Context context;
    String[] headline;
//    String[] xTop;
//    String[] xBottom;
//    String[] yStart;
    int image;
    DataReportAdpt.DataReportCallback dataReportCallback;
    String[] cType,cCount,usage,feedback,collection,newTicket;

    public GraphAdpt(Context context, String[] headline, String[] xTop, String[] xBottom, String[] yStart, int image, DataReportAdpt.DataReportCallback dataReportCallback) {
        this.context = context;
        this.headline = headline;
//        this.xTop = xTop;
//        this.xBottom = xBottom;
//        this.yStart = yStart;
//        this.image = image;
        this.dataReportCallback =dataReportCallback;

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
        if (position==0 ){
            holder.headLine.setText(headline[position]);
//            holder.xTop.setVisibility(View.GONE);
//            holder.xBottom.setVisibility(View.GONE);
//            holder.yFront.setVisibility(View.GONE);
            holder.graphView.setVisibility(View.GONE);

            cType = new String[]{"MWC","FWC","PWD","MUR","TOTAL"};
            cCount= new String[]{"10","10","10","10","50"};
            usage= new String[]{"10","10","10","10","50"};
            feedback=new String[]{"10","10","10","10","50"};
            collection=new String[]{"10","10","10","10","50"};
            newTicket=new String[]{"10","10","10","10","50"};

            DataReportAdpt adapter = new DataReportAdpt(context,cType,cCount,usage,feedback,collection,newTicket,dataReportCallback);
            holder.tableRecycler.setHasFixedSize(true);
            holder.tableRecycler.setLayoutManager(new LinearLayoutManager(context));
            holder.tableRecycler.setAdapter(adapter);

        }
        else if(position >0 && position<5) {

            holder.headLine.setText(headline[position]);
//            holder.xTop.setText(xTop[position-1]);
//            holder.xBottom.setText(xBottom[position-1]);
//            holder.yFront.setText(yStart[position-1]);
            holder.tableRecycler.setVisibility(View.GONE);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 40),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2),
                new DataPoint(9, 2),
                new DataPoint(10, 4),
                new DataPoint(50, 100),
                new DataPoint(100, 22),
                new DataPoint(150, 2),
                new DataPoint(200, 20)

        });

        holder.graphView.addSeries(series);
        }

    }

    @Override
    public int getItemCount() {
        return headline.length;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public TextView headLine;
//        public TextView xTop;
//        public TextView xBottom;
//        public TextView yFront;
        public GraphView graphView;
        public RecyclerView tableRecycler;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
             headLine =itemView.findViewById(R.id.headline);
//             xTop =itemView.findViewById(R.id.xTop);
//             xBottom =itemView.findViewById(R.id.xBottom);
//             yFront =itemView.findViewById(R.id.yStart);
            graphView = itemView.findViewById(R.id.idGraphView);
            tableRecycler = itemView.findViewById(R.id.table);

        }
    }
}
