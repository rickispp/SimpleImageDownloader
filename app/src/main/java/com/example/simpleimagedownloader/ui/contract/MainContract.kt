package com.example.simpleimagedownloader.ui.contract

import android.graphics.Bitmap

interface MainView {

    /**
     * Отобразить изображение на View.
     */
    fun showImage(image: Bitmap)

    /**
     * Отобразить сообщение об ошибке.
     * Вызывается при любых ошибках, о которых должен быть уведомлен пользователь.
     * Например, ошибка загрузки изображения, некорректно введенный URL и т.д.
     *
     * @param message Сообщения для отображения на View.
     */
    fun showError(message: String)
}

interface MainPresenter {

    /**
     * Событие генерируемое View при нажатии на кнопку загрузки рандомного изображения.
     *
     * @param needSimulateNetworkDelay имитировать "долгую" загрузку
     */
    fun onRandomButtonClicked(needSimulateNetworkDelay: Boolean)

    /**
     * Событие генерируемое View при нажатии на кнопку загрузки изображения по ссылке
     * введенной пользователем.
     *
     * @param url ссылка введенная пользователем.
     * @param needSimulateNetworkDelay имитировать "долгую" загрузку
     */
    fun onLoadFromUrlButtonClicked(url: String, needSimulateNetworkDelay: Boolean)

    /**
     * Привязать View к Presenter'у
     */
    fun attachView(view: MainView)

    /**
     * Отвязать View от Presenter'а
     */
    fun detachView()
}