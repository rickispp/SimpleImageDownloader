package com.example.simpleimagedownloader.components.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.simpleimagedownloader.components.ImageRepository
import com.example.simpleimagedownloader.exception.DownloadImageException
import java.io.IOException
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
        } catch (ex: IOException) {
            Log.e(TAG, "Connection error", ex)
            throw DownloadImageException("Connection refused")
        } catch (ex: Exception) {
            Log.e(TAG, "Error loading image", ex)
            throw DownloadImageException("Error loading image. Something went wrong.")
        }
    }
}