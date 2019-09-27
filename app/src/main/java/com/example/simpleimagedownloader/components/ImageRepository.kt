package com.example.simpleimagedownloader.components

import android.graphics.Bitmap
import com.example.simpleimagedownloader.exception.DownloadImageException

/**
 * Интерфейс репозитория изображений
 */
interface ImageRepository {

    /**
     * Вернет изображение по его прямой ссылке
     *
     * @param url ссылка на изображение
     * @return непосредственно само изображение (Bitmap)
     *
     * @throws DownloadImageException если по каким-либо причинам загрузить изображение не удалось
     */
    @Throws(DownloadImageException::class)
    fun getImageFromUrl(url: String) : Bitmap
}