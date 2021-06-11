package com.example.viewinpdf

import android.graphics.*
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewinpdf.Adapter.DataReportAdpt
import com.example.viewinpdf.Adapter.GraphAdpt
import com.jjoe64.graphview.GraphView


class MainActivity : AppCompatActivity(),DataReportAdpt.DataReportCallback {

    private var item_count: Int = 0
    private var yStart: Array<String>? = null
    private var xBottom: Array<String>? = null
    private var xTop: Array<String>? = null
    private var headline: Array<String>? = null
    private var image: Int? = null

    private var cType: Array<String>? = null
    private var cCount: Array<String>? = null
    private var usage: Array<String>? = null
    private var feedback: Array<String>? = null
    private var collection: Array<String>? = null
    private var newTicket: Array<String>? = null

    private var getCType: ArrayList<String> = ArrayList()
    private var getCCount: ArrayList<String> = ArrayList()
    private var getUsage: ArrayList<String> = ArrayList()
    private var getFeedback: ArrayList<String> = ArrayList()
    private var getCollection: ArrayList<String> = ArrayList()
    private var getNewTicket: ArrayList<String> = ArrayList()

    private var getBitmap: ArrayList<Bitmap> = ArrayList()
    private var getheading: ArrayList<String> = ArrayList()

    private var listRecycler :RecyclerView ?= null

    var graphAdpt:GraphAdpt ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         listRecycler  = findViewById(R.id.list)
        val button =findViewById<Button>(R.id.button)


        yStart= resources.getStringArray(R.array.yStart)
        xBottom= resources.getStringArray(R.array.xBottom)
        xTop= resources.getStringArray(R.array.xTop)
        headline= resources.getStringArray(R.array.heading)
        image= R.drawable.ic_launcher_background


        listRecycler!!.layoutManager = LinearLayoutManager(this)
        graphAdpt= GraphAdpt(this,headline,xTop,xBottom,yStart, image!!,this)
        listRecycler!!.adapter = graphAdpt
        item_count = graphAdpt!!.itemCount


        button.setOnClickListener(View.OnClickListener {
            convertGraph()
            val itext = IText(this)
            itext.openPdf()
            itext.addHeading("Summary","Sub Summary")
            for(j in 0 until item_count) {
             if(j==0) {
                 itext.addSubHeading(getheading[j])
                 itext.drawTable()
                 itext.drawTableCell(
                     getCType,
                     getCCount,
                     getUsage,
                     getFeedback,
                     getCollection,
                     getNewTicket
                 )
             }else {
                 itext.addSubHeading(getheading[j])
                 itext.drawImage(getBitmap[j-1])
             }
            }
            itext.closePdf()
        })

    }


    override fun onMethodCallback(
        cType: String?,
        cCount: String?,
        usage: String?,
        feedback: String?,
        collection: String?,
        newTicket: String?
    ) {
        getCType.add(cType!!)
        getCCount.add(cCount!!)
        getUsage.add(usage!!)
        getFeedback.add(feedback!!)
        getCollection.add(collection!!)
        getNewTicket.add(newTicket!!)

    }


    private fun getBitmapFromViewgraph(view: View) {
        val exc =view

        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val returnedBitmap: Bitmap = Bitmap.createBitmap(
            view.width,  //hScroll?.measureWidth!!,
            view.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
//        view.layout(0, view.top, view.measuredWidth, view.bottom)
        view.draw(canvas)
        getBitmap.add(returnedBitmap)
//        view.layoutParams = exc.layoutParams
//        return returnedBitmap
    }


    private fun convertGraph(){
        for(j in 0 until item_count){
            val v = listRecycler?.findViewHolderForAdapterPosition(j)?.itemView
            val header = v?.findViewById<TextView>(R.id.headline)
            val s = header?.text
            getheading.add(s!! as String)

            if(j!=0) {
                val view = v.findViewById<GraphView>(R.id.idGraphView)
                getBitmapFromViewgraph(view!!)
            }
        }



    }
}