package com.example.simpleimagedownloader.exception

import java.lang.Exception

/**
 * Exception, генерируемый при любых ошибках загрузки изображения
 */
class DownloadImageException(message: String) : Exception(message)