package com.example.simpleimagedownloader.ui.interactor

import android.graphics.Bitmap

/**
 * Интерактор для обработки кейсов загрузки изображений
 */
interface LoadImageInteractor {

    /**
     * Загрузить изображение по переданному URL
     */
    fun loadImageByUrl(url: String, emulateDelay: Boolean, listener: LoadImageInteractorListener)

    /**
     * Загрузить случайное изображение
     */
    fun loadRandomImage(emulateDelay: Boolean, listener: LoadImageInteractorListener)

    /**
     * Интерфейс слушателя событий интерактора
     */
    interface LoadImageInteractorListener {

        /**
         * Вызывается перед началом загрузки изображения
         */
        fun onLoadStart()

        /**
         * Вызывается после загрузки изображения, если не возникло ошибок
         * Вызов этого метода взаимоисключает вызов метода onError
         *
         * @param image загруженное изображение
         */
        fun onLoadFinish(image: Bitmap)

        /**
         * Вызывается при возникновении ошибок загрузки изображения
         * Вызов этого метода взаимоисключает вызов метода onLoadFinish
         *
         * @param ex сгенерированое исключение
         */
        fun onError(ex: Exception)
    }
}