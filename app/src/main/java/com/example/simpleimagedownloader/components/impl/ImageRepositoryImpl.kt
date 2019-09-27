package com.example.simpleimagedownloader.components.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.simpleimagedownloader.components.ImageRepository
import com.example.simpleimagedownloader.exception.DownloadImageException
import java.net.ConnectException
import java.net.MalformedURLException
import java.net.URL

class ImageRepositoryImpl : ImageRepository {

    private val TAG = "ImageRepositoryImpl"

    override fun getImageFromUrl(url: String): Bitmap {
        try {
            val httpURLConnection = URL(url).openConnection()
            return httpURLConnection.getInputStream().use {
                BitmapFactory.decodeStream(it)
            }
        } catch (ex: MalformedURLException) {
            Log.e(TAG, "Malformed url", ex)
            throw DownloadImageException("Incorrect image url")
        } catch (ex: ConnectException) {
            Log.e(TAG, "Connection error", ex)
            throw DownloadImageException("Connection refused")
        } catch (ex: Exception) {
            Log.e(TAG, "Error img downloading", ex)
            throw DownloadImageException("Unexpected error")
        }
    }
}