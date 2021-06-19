package com.example.mispdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.util.Log;

import android.view.View;

import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;


public class ReportViewCreator {
    private  LinearLayout linearlayout ;
    private Context context;
    int width;
    int height;
    int listOfView;
     ArrayList<Bitmap> bmpUsageGraph = new ArrayList<>();
     ArrayList<Bitmap> bmpFeedbackGraph= new ArrayList<>();
     ArrayList<Bitmap> bmpCollectionGraph =new ArrayList<>();
     ArrayList<Bitmap> bmpNewTicketGraph= new ArrayList<>();
     ArrayList<View> v= new ArrayList<>();

    String[] cType = new String[]{"MWC", "FWC", "PWD", "MUR", "TOTAL"};
    String[] cCount = new String[]{"10", "10", "10", "10", "50"};
    String[] usage = new String[]{"10", "10", "10", "10", "50"};
    String[] feedback = new String[]{"10", "10", "10", "10", "50"};
    String[] collection = new String[]{"10", "10", "10", "10", "50"};
    String[] newTicket = new String[]{"10", "10", "10", "10", "50"};


    public ReportViewCreator( Context context,LinearLayout linearLayout,int listOfView) {
        this.linearlayout = linearLayout;
        this.context = context;
        this.listOfView =listOfView;

    }

    @SuppressLint("InflateParams")
    void createView() {
        for(int i= 0 ; i<listOfView ;i++) {

            View view= View.inflate(context,R.layout.raw_graph,null);
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(widthMeasureSpec, heightMeasureSpec);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

            width = view.getWidth();
            height =view.getHeight();

            GraphView graphView= view.findViewById(R.id.raw_graph);;
            graphView.addSeries(getSeries());


            for(int j =0 ; j<4 ;j++ ){
                switch (j){
                    case 0 :
                        bmpUsageGraph.add(getBitmapOfViewGraph(view));
                        break;
                    case 1 :
                        bmpFeedbackGraph.add(getBitmapOfViewGraph(view));
                        break;
                    case 2 :
                        bmpCollectionGraph.add(getBitmapOfViewGraph(view));
                        break;
                    case 3 :
                        bmpNewTicketGraph.add(getBitmapOfViewGraph(view));
                        break;

                }
            }
     }
    }



    private Series getSeries() {
        return new LineGraphSeries<DataPoint>(new DataPoint[]{
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
    }

    private Bitmap getBitmapOfViewGraph(View view) {
        Log.i("ggg", "getBitmapOfViewGraph: width :" +width);
        Log.i("ggg", "getBitmapOfViewGraph: height :" +height);


        Bitmap returnedBitmap = Bitmap.createBitmap(
               view.getMeasuredWidth(),
               view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) bgDrawable.draw(canvas);
        else canvas.drawColor(Color.WHITE);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return returnedBitmap;
    }

    void createPdf(){
        IText itext = new IText(context);
        itext.openPdf();


        for(int j = 0;j<listOfView;j++) {

            itext.addHeading("Summary","Sub Summary",context);
            itext.addSubHeading("Data Report");
            itext.drawTable();
            itext.drawTableCell(
                    cType,
                    cCount,
                    usage,
                    feedback,
                    collection,
                    newTicket);
            itext.addSubHeading("Usage Comparison");
            itext.drawImage(bmpUsageGraph.get(j));

            itext.addSubHeading("Feedback Distribution");
            itext.drawImage(bmpUsageGraph.get(j));

            itext.addSubHeading("Collection Timeline");
            itext.drawImage(bmpUsageGraph.get(j));

            itext.addSubHeading("Ticket Resolution Timeline");
            itext.drawImage(bmpUsageGraph.get(j));
            if(j+1 <listOfView){

            }
        }
        itext.closePdf();
    }


}
