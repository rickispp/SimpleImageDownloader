package com.example.simpleimagedownloader.ui.contract

interface MainView {

    /**
     * Отобразить изображение на View.
     */
    fun showImage()

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
     */
    fun onRandomButtonClicked()

    /**
     * Событие генерируемое View при нажатии на кнопку загрузки изображения по ссылке
     * введенной пользователем.
     *
     * @param url ссылка введенная пользователем.
     */
    fun onLoadFromUrlButtonClicked(url: String)
}