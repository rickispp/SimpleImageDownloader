package com.example.simpleimagedownloader.ui.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.example.simpleimagedownloader.ui.contract.MainPresenter
import com.example.simpleimagedownloader.ui.contract.MainView
import java.net.URL

class MainActivityPresenter(private val view: MainView): MainPresenter {

    private val randomImageLink = "https://picsum.photos/200"

    override fun onRandomButtonClicked() {
        showImageByUrl(randomImageLink)
    }

    override fun onLoadFromUrlButtonClicked(url: String) {
        showImageByUrl(url)
    }

    private fun showImageByUrl(url: String) {
        AsyncDownloadImageTask().execute(url)
    }

    inner class AsyncDownloadImageTask: AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap {
            val httpURLConnection = URL(urls[0]).openConnection()
            return httpURLConnection.getInputStream().use {
                BitmapFactory.decodeStream(it)
            }
        }

        override fun onPostExecute(result: Bitmap) {
            view.showImage(result)
        }
    }
}