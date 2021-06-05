package com.example.viewinpdf

import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewinpdf.Adapter.DataReportAdpt
import com.example.viewinpdf.Adapter.GraphAdpt
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

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

    private var tableRecycler :RecyclerView ?= null
    private var listRecycler :RecyclerView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableRecycler  = findViewById(R.id.table)
         listRecycler  = findViewById(R.id.list)
        val button =findViewById<Button>(R.id.button)

         cType = resources.getStringArray(R.array.cabinType)
         cCount= resources.getStringArray(R.array.cabinCount)
         usage= resources.getStringArray(R.array.usage)
         feedback= resources.getStringArray(R.array.feedback)
         collection= resources.getStringArray(R.array.collection)
         newTicket= resources.getStringArray(R.array.newTicket)

        yStart= resources.getStringArray(R.array.yStart)
        xBottom= resources.getStringArray(R.array.xBottom)
        xTop= resources.getStringArray(R.array.xTop)
        headline= resources.getStringArray(R.array.heading)
        image= R.drawable.ic_launcher_background


        tableRecycler!!.layoutManager = LinearLayoutManager(this)
        val tableAdpt= DataReportAdpt(this,cType,cCount,usage,feedback,collection,newTicket)
        tableRecycler!!.adapter = tableAdpt;

        listRecycler!!.layoutManager = LinearLayoutManager(this)
        val graphAdpt= GraphAdpt(this,headline,xTop,xBottom,yStart, image!!)
        listRecycler!!.adapter = graphAdpt


        button.setOnClickListener(View.OnClickListener {

            val scroll =findViewById<ScrollView>(R.id.scrollView)
            val screen = getBitmapFromView(scroll)
            val llist =getBitmapFromView(listRecycler!!)
            val cabin =getBitmapFromView(tableRecycler!!)
            val pdfHeight = cabin?.height!!+llist?.height!!
            val pdfWidth = cabin.width



            val document = PdfDocument()
            val pageInfo: PdfDocument.PageInfo  = PdfDocument.PageInfo.Builder(
               pdfWidth,
                pdfHeight,
                1).create()

            val page: PdfDocument.Page  = document.startPage(pageInfo)
            // Draw the bitmap onto the page
            val canvas: Canvas = page.canvas
            canvas.drawBitmap(cabin, Matrix() ,null)
            canvas.drawBitmap(llist,0f,cabin.height.toFloat(),null)
//            cabin.recycle()
//            llist.recycle()
            document.finishPage(page)

            // Write the PDF file to a file
            val file = File(getExternalFilesDir(null)!!.absolutePath + "/ll.pdf")
            document.writeTo( FileOutputStream(file))
            document.close()
        })
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        var returnedBitmap :Bitmap ?= null
        val exc = view
//        var wid:Int?=null
//        if(view == tableRecycler) {
//            view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            wid= view.width
//        }else{ wid= tableRecycler?.width}
        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            returnedBitmap= Bitmap.createBitmap(
                view.width,
                view.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(returnedBitmap)
            val bgDrawable = view.background
            if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
            view.layout(0,view.top, view.right, view.bottom);
            view.draw(canvas)
           view.layoutParams=exc.layoutParams

        return returnedBitmap

    }


}