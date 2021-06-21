/*
 * Copyright 2013-2017 Amazon.com,
 * Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the
 * License. A copy of the License is located at
 *
 *      http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, express or implied. See the License
 * for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mispdf.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mispdf.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.jetbrains.annotations.NotNull;


public class ComplexUsageReportAdapter extends RecyclerView.Adapter<ComplexUsageReportAdapter.MyViewHolder> {

    private Context context;

    public ComplexUsageReportAdapter(Context context ) {

        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_report_complex_usage, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, final int position) {
        String[] cType = new String[]{"MWC", "FWC", "PWD", "MUR", "TOTAL"};
        String[] cCount = new String[]{"10", "10", "10", "10", "50"};
        String[] usage = new String[]{"10", "10", "10", "10", "50"};
        String[] feedback = new String[]{"10", "10", "10", "10", "50"};
        String[] collection = new String[]{"10", "10", "10", "10", "50"};
        String[] newTicket = new String[]{"10", "10", "10", "10", "50"};

        AdapterUsageData adapter = new AdapterUsageData(context,cType,cCount,usage,feedback,collection,newTicket);
        holder.dataGrid.setHasFixedSize(true);
        holder.dataGrid.setNestedScrollingEnabled(false);
        holder.dataGrid.setLayoutManager(new LinearLayoutManager(context));
        holder.dataGrid.setAdapter(adapter);

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

        holder.usageComparison.addSeries(series);
        holder.feedbackComparison.addSeries(series);
        holder.collectionComparison.addSeries(series);
        holder.newTicket.addSeries(series);


    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        GraphView usageComparison,feedbackComparison,collectionComparison,newTicket;
        RecyclerView dataGrid;

        public MyViewHolder(View itemView) {
            super(itemView);
            usageComparison =itemView.findViewById(R.id.usageComparison);
            feedbackComparison =itemView.findViewById(R.id.feedbackDistribution);
            collectionComparison =itemView.findViewById(R.id.collectionTimeline);
            newTicket  =itemView.findViewById(R.id.ticketResolutionTimeline);
            dataGrid =itemView.findViewById(R.id.dataGrid);

        }
    }
}

