package com.example.viewinpdf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewinpdf.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DataReportAdpt extends RecyclerView.Adapter<DataReportAdpt.ViewHolder>{

    Context context;
    String[] cType;
    String[] cCount;
    String[] usage;
    String[] feedback;
    String[] collection;
    String[] newTicket;

    public DataReportAdpt(Context context, String[] cType, String[] cCount, String[] usage, String[] feedback, String[] collection, String[] newTicket) {
        this.context = context;
        this.cType = cType;
        this.cCount = cCount;
        this.usage = usage;
        this.feedback = feedback;
        this.collection = collection;
        this.newTicket = newTicket;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_table,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (position==0){
            holder.cType.setText(R.string.cabin_type);
            holder.cCount.setText(R.string.cabin_count);
            holder.usage.setText(R.string.collection);
            holder.feedback.setText(R.string.feedback);
            holder.collection.setText(R.string.collection);
            holder.newTicket.setText(R.string.new_ticket);
            holder.cType2.setText(R.string.cabin_type);
            holder.cCount2.setText(R.string.cabin_count);
            holder.usage2.setText(R.string.collection);
            holder.feedback2.setText(R.string.feedback);
            holder.collection2.setText(R.string.collection);
            holder.newTicket2.setText(R.string.new_ticket);
        }
        else if (position>1 && position<6){
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
            holder.cType.setText(cType[4]);
            holder.cCount.setText(cCount[4]);
            holder.usage.setText(collection[4]);
            holder.feedback.setText(feedback[4]);
            holder.collection.setText(collection[4]);
            holder.newTicket.setText(newTicket[4]);
        }

    }

    @Override
    public int getItemCount() {
        return (501);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(@NonNull @NotNull View itemView) {
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
