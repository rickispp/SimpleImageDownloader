package com.example.simpleimagedownloader.ui.interactor

import android.graphics.Bitmap
import android.os.AsyncTask
import com.example.simpleimagedownloader.components.ImageRepository
import com.example.simpleimagedownloader.components.impl.ImageRepositoryImpl

class LoadImageInteractorImpl: LoadImageInteractor {

    /**
     * Репозиторий изображений.
     * Загружает синхронно, все обращения к нему должны быть не в UI-треде.
     */
    private val imageRepository = ImageRepositoryImpl()

    /**
     * Ссылка для получения случайного изображения с сервиса picsum.photos
     */
    private val randomImageLink = "https://picsum.photos/200"

    override fun loadImageByUrl(url: String, emulateDelay: Boolean, listener: LoadImageInteractor.LoadImageInteractorListener) {
        DownloadImageTask(url, emulateDelay, imageRepository, listener).execute()
    }

    override fun loadRandomImage(emulateDelay: Boolean, listener: LoadImageInteractor.LoadImageInteractorListener) {
        DownloadImageTask(randomImageLink, emulateDelay, imageRepository, listener).execute()
    }

    private class DownloadImageTask(
        private val url: String,
        private val needEmulateNetworkDelay: Boolean,
        private val imageRepository: ImageRepository,
        private val listener: LoadImageInteractor.LoadImageInteractorListener
    ) : AsyncTask<Void, Void, Bitmap>() {

        override fun onPreExecute() {
            listener.onLoadStart()
        }

        override fun doInBackground(vararg params: Void): Bitmap? {
            try {
                if (needEmulateNetworkDelay) {
                    Thread.sleep(5000)  //  Если необходимо, имитируем задержки сети
                }
                return imageRepository.getImageFromUrl(url)
            } catch (ex: Exception) {
                listener.onError(ex)
            }

            return null
        }

        override fun onPostExecute(result: Bitmap?) {
            result?.let {
                listener.onLoadFinish(it)
            }
        }
    }
}