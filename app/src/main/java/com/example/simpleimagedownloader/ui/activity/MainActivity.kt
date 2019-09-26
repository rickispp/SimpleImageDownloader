package com.example.simpleimagedownloader.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleimagedownloader.R
import com.example.simpleimagedownloader.ui.contract.MainView
import com.example.simpleimagedownloader.ui.presenter.MainActivityPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.imageView
import kotlinx.android.synthetic.main.activity_main.loadFromUrlButton
import kotlinx.android.synthetic.main.activity_main.loadRandomButton
import kotlinx.android.synthetic.main.activity_main.mainContainer
import kotlinx.android.synthetic.main.activity_main.urlEditTextView

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainActivityPresenter(this)

    private var currentImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRandomButton.setOnClickListener {
            presenter.onRandomButtonClicked()
        }

        loadFromUrlButton.setOnClickListener {
            presenter.onLoadFromUrlButtonClicked(urlEditTextView.text.toString())
        }
    }

    override fun showImage(image: Bitmap) {
        currentImage = image

        imageView.setImageBitmap(currentImage)
    }

    override fun showError(message: String) {
        Snackbar.make(mainContainer, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable("image", currentImage)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentImage = savedInstanceState.getParcelable<Bitmap>("image")
        if (currentImage != null) {
            imageView.setImageBitmap(currentImage)
        }
    }
}
