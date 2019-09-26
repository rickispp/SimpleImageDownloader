package com.example.simpleimagedownloader.components

import java.util.Random

/**
 * Генератор ссылок на случайные изображения
 */
class RandomPictureLinkGenerator {

    /**
     * Шаблон URL для получения рандомного изображения формата 200x200 px
     */
    private val urlTemplate: String = "https://picsum.photos/id/%d/200/200"

    /**
     * Рандомайзер для генерации случайного ID изображения
     */
    private val random: Random = Random()

    /**
     * Возвращает URL случайного изображения с сервиса picsum.photos
     */
    fun getRanomPictureLink(): String {
        return String.format(urlTemplate, random.nextInt(1000))
    }
}