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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mispdf.R;

import org.jetbrains.annotations.NotNull;


public class AdapterUsageData extends RecyclerView.Adapter<AdapterUsageData.MyViewHolder> {

    Context context;
    String[] cType,cCount,usage,feedback,collection,newTicket;

    public AdapterUsageData(Context context,String[] cType, String[] cCount, String[] usage, String[] feedback, String[] collection, String[] newTicket) {
        this.context = context;
        this.cType = cType;
        this.cCount = cCount;
        this.usage = usage;
        this.feedback = feedback;
        this.collection = collection;
        this.newTicket = newTicket;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.element_report_data,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position==0){
            holder.cType.setText(R.string.cabin_type);
            holder.cCount.setText(R.string.cabin_count);
            holder.usage.setText(R.string.usage);
            holder.feedback.setText(R.string.feedback);
            holder.collection.setText(R.string.collection);
            holder.newTicket.setText(R.string.new_ticket);
            holder.cType2.setText(R.string.cabin_type);
            holder.cCount2.setText(R.string.cabin_count);
            holder.usage2.setText(R.string.usage);
            holder.feedback2.setText(R.string.feedback);
            holder.collection2.setText(R.string.collection);
            holder.newTicket2.setText(R.string.new_ticket);
        }

        else if (position>0 && position<6){
            holder.cType.setText(cType[position-1]);
            holder.cCount.setText(cCount[position-1]);
            holder.usage.setText(collection[position-1]);
            holder.feedback.setText(feedback[position-1]);
            holder.collection.setText(collection[position-1]);
            holder.newTicket.setText(newTicket[position-1]);
            holder.cType2.setText(cType[position-1]);
            holder.cCount2.setText(cCount[position-1]);
            holder.usage2.setText(collection[position-1]);
            holder.feedback2.setText(feedback[position-1]);
            holder.collection2.setText(collection[position-1]);
            holder.newTicket2.setText(newTicket[position-1]);
        }

        else{
            holder.cType.setText(cType[4]);
            holder.cCount.setText(cCount[4]);
            holder.usage.setText(collection[4]);
            holder.feedback.setText(feedback[4]);
            holder.collection.setText(collection[4]);
            holder.newTicket.setText(newTicket[4]);
            holder.cType2.setText(cType[4]);
            holder.cCount2.setText(cCount[4]);
            holder.usage2.setText(collection[4]);
            holder.feedback2.setText(feedback[4]);
            holder.collection2.setText(collection[4]);
            holder.newTicket2.setText(newTicket[4]);

        }
    }

    @Override
    public int getItemCount() {
               return 11;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cType;
        public TextView cCount;
        public TextView usage;
        public TextView feedback;
        public TextView collection;
        public TextView newTicket;
        public TextView cType2;
        public TextView cCount2;
        public TextView usage2;
        public TextView feedback2;
        public TextView collection2;
        public TextView newTicket2;

        public MyViewHolder(View itemView) {
            super(itemView);
            cType = itemView.findViewById(R.id.cType);
            cCount= itemView.findViewById(R.id.cCount);
            usage= itemView.findViewById(R.id.usage);
            feedback= itemView.findViewById(R.id.feedBack);
            collection= itemView.findViewById(R.id.collection);
            newTicket= itemView.findViewById(R.id.newTicket);
            cType2 = itemView.findViewById(R.id.cType2);
            cCount2= itemView.findViewById(R.id.cCount2);
            usage2= itemView.findViewById(R.id.usage2);
            feedback2= itemView.findViewById(R.id.feedBack2);
            collection2= itemView.findViewById(R.id.collection2);
            newTicket2= itemView.findViewById(R.id.newTicket2);
        }
    }
}
