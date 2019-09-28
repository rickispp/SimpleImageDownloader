package com.example.simpleimagedownloader.ui.presenter

import android.graphics.Bitmap
import com.example.simpleimagedownloader.ui.contract.MainContract
import com.example.simpleimagedownloader.ui.interactor.LoadImageInteractor
import com.example.simpleimagedownloader.ui.interactor.LoadImageInteractorImpl

class MainActivityPresenter: MainContract.MainPresenter {

    /**
     * Текущий экземпляр View, с которым работает этот Presenter
     */
    private var view: MainContract.MainView? = null

    /**
     * Интерактор обрабатывающий различные кейсы загрузки изображений
     */
    private val loadImageInteractor = LoadImageInteractorImpl()

    override fun attachView(view: MainContract.MainView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onRandomButtonClicked(needSimulateNetworkDelay: Boolean) {
        loadImageInteractor.loadRandomImage(needSimulateNetworkDelay, LoadImageListener())
    }

    override fun onLoadFromUrlButtonClicked(url: String, needSimulateNetworkDelay: Boolean) {
        loadImageInteractor.loadImageByUrl(url, needSimulateNetworkDelay, LoadImageListener())
    }

    inner class LoadImageListener: LoadImageInteractor.LoadImageInteractorListener {
        override fun onLoadStart() {
            view?.showWaitForm()
        }

        override fun onLoadFinish(image: Bitmap) {
            view?.hideWaitForm()
            view?.showImage(image)
        }

        override fun onError(ex: Exception) {
            view?.hideWaitForm()
            view?.showError(ex.localizedMessage!!)
        }
    }
}