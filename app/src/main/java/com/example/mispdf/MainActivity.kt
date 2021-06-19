package com.example.mispdf

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mispdf.Adapter.ComplexUsageReportAdapter
import com.jjoe64.graphview.GraphView
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private var listRecycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bmpExp = findViewById<Button>(R.id.button)

        listRecycler = findViewById<RecyclerView>(R.id.outerRecycler)
        listRecycler!!.layoutManager = LinearLayoutManager(this)
        val complexUsageReportAdapter = ComplexUsageReportAdapter(this)
        listRecycler!!.adapter = complexUsageReportAdapter


        bmpExp.setOnClickListener(View.OnClickListener {

            val reportViewCreator = ReportViewCreator(
                this,
                LinearLayout(this),
                10
            )
            reportViewCreator.createView()
            reportViewCreator.createPdf()
        })

    }
}