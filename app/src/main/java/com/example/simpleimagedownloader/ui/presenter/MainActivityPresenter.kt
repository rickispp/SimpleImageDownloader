package com.example.simpleimagedownloader.ui.presenter

import android.graphics.Bitmap
import android.os.AsyncTask
import com.example.simpleimagedownloader.components.impl.ImageRepositoryImpl
import com.example.simpleimagedownloader.ui.contract.MainPresenter
import com.example.simpleimagedownloader.ui.contract.MainView

class MainActivityPresenter: MainPresenter {

    /**
     * Текущий экземпляр View, с которым работает этот Presenter
     */
    private var view: MainView? = null

    /**
     * Репозиторий изображений.
     * Загружает синхронно, все обращения к нему должны быть не в UI-треде.
     */
    private val imageRepository = ImageRepositoryImpl()

    /**
     * Ссылка для получения случайного изображения с сервиса picsum.photos
     */
    private val randomImageLink = "https://picsum.photos/200"

    override fun attachView(view: MainView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onRandomButtonClicked(needSimulateNetworkDelay: Boolean) {
        showImageByUrl(randomImageLink)
    }

    override fun onLoadFromUrlButtonClicked(url: String, needSimulateNetworkDelay: Boolean) {
        showImageByUrl(url)
    }

    private fun showImageByUrl(url: String) {
        try {
            AsyncDownloadImageTask().execute(url)
        } catch (ex: Exception) {
            view?.showError(ex.message!!)
        }
    }

    inner class AsyncDownloadImageTask: AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap {
            return imageRepository.getImageFromUrl(urls[0])
        }

        override fun onPostExecute(result: Bitmap) {
            view?.showImage(result)
        }
    }
}