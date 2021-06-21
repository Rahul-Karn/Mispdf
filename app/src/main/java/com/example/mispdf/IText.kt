package com.example.mispdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.itextpdf.io.image.ImageData
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.*
import com.itextpdf.layout.layout.LayoutArea
import com.itextpdf.layout.layout.LayoutResult
import com.itextpdf.layout.layout.RootLayoutArea
import com.itextpdf.layout.property.BorderRadius
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import com.itextpdf.layout.renderer.DivRenderer
import com.itextpdf.layout.renderer.DocumentRenderer
import java.io.File


class IText(context: Context) {
    private val file = File(context.getExternalFilesDir(null)!!.absolutePath + "/iText.pdf")
    private val pdfDocument = PdfDocument(PdfWriter(file))
    private val width = 1150f //PageSize.Default.width+80
    private val height = 1700f //PageSize.Default.height
    private var document :Document ?= null

    fun openPdf() {
        document = Document(pdfDocument, PageSize(width,height))
        document!!.setRenderer( CustomDocumentRenderer(document!!))
    }



    fun addHeading(head: String, subHead :String, context: Context) {


        val bmp = BitmapFactory.decodeResource(context.resources,R.drawable.image)
        val icon = convertBmpToImg(bmp)
        icon.scaleAbsolute(45f, 45f)

        val letter = Paragraph(subHead)
        letter.setBold()
        letter.setFontSize(14f)

        val text = Paragraph()
        text.add(head +"\n")
        text.add(letter)
        text.setBold()

        val comp = Paragraph()
        comp.add(icon)
        comp.add(Tab())
        comp.add(text)
        comp.setPadding(5f)
        comp.setFontSize(20f)
        comp.setBackgroundColor(DeviceRgb(0xA6, 0xCB, 0x0B))
//           comp.setBackgroundColor(ColorConstants.GREEN)
        comp.setBorderRadius(BorderRadius(20f))
        document!!.add(comp)

    }

    fun addSubHeading(txt :String){
        val boldText = Paragraph(txt)
        boldText.setBold()
        boldText.setFontSize(16f)
        boldText.setPadding(5f)
        boldText.setMarginTop(25f)
        document!!.add(boldText)
    }

    private val cSize = width/12
    private var table :Table ?=null


    fun drawTable(): Table? {
        table =Table(UnitValue.createPercentArray(floatArrayOf(cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize,cSize))).useAllAvailableWidth()
        return table
    }


    fun drawTableCell(cType :Array<String>,cCount :Array<String>,usage :Array<String>,
                  feedback :Array<String>,collection :Array<String>,newTicket :Array<String>){

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

    fun convertBmpToImg(bitmap: Bitmap): Image {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()
        val data: ImageData = ImageDataFactory.create(byteArray)
        return Image(data)
    }

    fun drawImage(bitmap: Bitmap){
        val img = convertBmpToImg(bitmap)
        img.setHorizontalAlignment(HorizontalAlignment.CENTER)
        img.scaleAbsolute((width/2).toFloat(), ((height/(7)).toFloat()))
        document!!.add(img)
    }


    fun closePdf(){
        return document!!.close()
    }


}

internal class CustomDocumentRenderer(document: Document?) :
    DocumentRenderer(document) {
    override fun updateCurrentArea(overflowResult: LayoutResult?): LayoutArea {
        val area = super.updateCurrentArea(overflowResult) // margins are applied on this level
        val newBBox = area.bBox.clone()

        // apply border
        val borderWidths = floatArrayOf(10f, 10f, 10f, 10f)
        newBBox.applyMargins(
            borderWidths[0],
            borderWidths[1], borderWidths[2], borderWidths[3], false
        )

        // this div will be added as a background
        val div = Div()
            .setWidth(newBBox.width)
            .setHeight(newBBox.height)
            .setBorder(SolidBorder(10F))
            .setBorderRadius(BorderRadius(50f))
//            .setBackgroundColor(ColorConstants.BLUE)
        addChild(DivRenderer(div))

        // apply padding
        val paddingWidths = floatArrayOf(20f, 20f, 20f, 20f)
        newBBox.applyMargins(
            paddingWidths[0],
            paddingWidths[1], paddingWidths[2], paddingWidths[3], false
        )

        return RootLayoutArea(area.pageNumber, newBBox).also { currentArea = it }
    }
}