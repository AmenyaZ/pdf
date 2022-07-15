package com.example.pdf

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity

import android.widget.Toast
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import com.pspdfkit.document.download.Progress

import java.io.File


class MainActivity : AppCompatActivity() {

    private var imageUrl = "https://pspdfkit.com/downloads/case-study-box.pdf"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val uri = Uri.parse("file:///android_asset/resume.pdf")
        //PdfActivity.showDocument(this, uri, config)

        val request: DownloadRequest = DownloadRequest.Builder(this)
            .uri(imageUrl)
            .build()
        val job: DownloadJob = DownloadJob.startDownload(request)
        val config = PdfActivityConfiguration.Builder(this).build()

        job.setProgressListener(object :DownloadJob.ProgressListenerAdapter(){

            override fun onComplete(output: File) {
                PdfActivity.showDocument(this@MainActivity, Uri.fromFile(output), config)
                Log.d("Config", "============> ${config}")
                Log.d("Docuent", "============> ${job}")
                super.onComplete(output)
            }

            override fun onError(exception: Throwable) {
                super.onError(exception)
                Toast.makeText(this@MainActivity, exception.message,Toast.LENGTH_LONG).show()
            }

            override fun onProgress(progress: Progress) {
                super.onProgress(progress)
            }
        })

    }



}