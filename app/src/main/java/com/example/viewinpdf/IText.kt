package com.example.viewinpdf

import android.content.Context
import android.graphics.Bitmap
import com.itextpdf.io.image.ImageData
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.kernel.colors.Color
import com.itextpdf.kernel.colors.DeviceCmyk
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.*
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.File


class IText(context: Context) {
    private val file = File(context.getExternalFilesDir(null)!!.absolutePath + "/iText.pdf")
    private val pdfDocument = PdfDocument(PdfWriter(file))
    private val width = PageSize.Default.width+80
    private val height =PageSize.Default.height
    private var document :Document ?= null

    fun openPdf() {
        document = Document(pdfDocument, PageSize(width,height))
    }

    fun addText(txt: String) {
        val text = Paragraph(txt)
        document!!.add(text)
    }

    fun addHeading(head: String,subHead :String) {
        val text = Paragraph(head)
        text.setBold()
        text.setFontSize(20f)
        document!!.add(text)


        val letter = Paragraph(subHead)
        letter.setBold()
        letter.setFontSize(14f)
        letter.setMarginBottom(10f)
        document!!.add(letter)

        val canvas = PdfCanvas(pdfDocument.getPage(1))
//        val magentaColor: Color = DeviceCmyk(0f, 1f, 0f, 0f)
        canvas
            .moveTo(0.0, 730.0)
            .lineTo(width.toDouble(), 730.0)
          .closePathStroke()


    }

    fun addSubHeading(txt :String){
        val boldText = Paragraph(txt)
        boldText.setBold()
        boldText.setFontSize(16f)
        boldText.setMarginTop(25f)
        document!!.add(boldText)
    }

    val cSize = width/12
    var table :Table ?=null


    fun drawTable(): Table? {
        table =Table(UnitValue.createPercentArray(floatArrayOf(cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize))).useAllAvailableWidth()
        return table
    }


    fun drawTableCell(cType :ArrayList<String>,cCount :ArrayList<String>,usage :ArrayList<String>,
                  feedback :ArrayList<String>,collection :ArrayList<String>,newTicket :ArrayList<String>){

            table?.addCell(Cell().add(Paragraph("Cabin Type").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("CabinType").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("Usage").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("Feedback").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("collection").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("New Ticket").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("Cabin Type").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("CabinType").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("Usage").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("Feedback").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("collection").setBold().setTextAlignment(TextAlignment.CENTER)))
            table?.addCell(Cell().add(Paragraph("New Ticket").setBold().setTextAlignment(TextAlignment.CENTER)))

        for(i in 0 ..cType.size-1)
        {
                table?.addCell(Cell().add(Paragraph(cType[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(cCount[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(usage[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(feedback[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(collection[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(newTicket[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(cType[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(cCount[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(usage[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(feedback[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(collection[i]).setTextAlignment(TextAlignment.CENTER)))
                table?.addCell(Cell().add(Paragraph(newTicket[i]).setTextAlignment(TextAlignment.CENTER)))


            }

            document!!.add(table)
        }

    fun drawBitmapArray(bitmap : ArrayList<Bitmap>){
        for(b in bitmap){
            drawImage(b)
        }
    }

    fun drawImage(bitmap: Bitmap){
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()
        val data: ImageData = ImageDataFactory.create(byteArray)
        val img = Image(data)
        img.setMarginBottom(25f)
        img.scaleAbsolute(width-80, (height/(3)))
        document!!.add(img)
    }


    fun closePdf(){
        return document!!.close()
    }
}