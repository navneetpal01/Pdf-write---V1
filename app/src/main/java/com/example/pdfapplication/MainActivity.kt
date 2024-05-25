package com.example.pdfapplication

import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import java.io.File
import java.io.FileOutputStream


class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            createPdf()
        }
    }

    fun createPdf(){
        val docsFolder = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString())
        if (!docsFolder.exists()){
            docsFolder.mkdir()
        }
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300,600,1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val paint = Paint()
        paint.textSize = 30f
        var text = "Sample Pdf"
        canvas.drawText(text,80f,50f,paint)
        paint.textSize = 12f
        text = "Congratulations your first Pdf file created successfully"
        canvas.drawText(text,5f,70f,paint)
        pdfDocument.finishPage(page)
        val timeInMills = System.currentTimeMillis().toString()
        val file = File(docsFolder.absoluteFile,"$timeInMills.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()
        Toast.makeText(this,"Pdf file created Successfully",Toast.LENGTH_LONG).show()



    }
}