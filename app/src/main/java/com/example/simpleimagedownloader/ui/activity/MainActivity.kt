package com.example.simpleimagedownloader.ui.activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleimagedownloader.R
import com.example.simpleimagedownloader.ui.contract.MainPresenter
import com.example.simpleimagedownloader.ui.contract.MainView
import com.example.simpleimagedownloader.ui.presenter.MainActivityPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.imageView
import kotlinx.android.synthetic.main.activity_main.loadFromUrlButton
import kotlinx.android.synthetic.main.activity_main.loadRandomButton
import kotlinx.android.synthetic.main.activity_main.mainContainer
import kotlinx.android.synthetic.main.activity_main.simulateNetworkDelayCheckBox
import kotlinx.android.synthetic.main.activity_main.urlEditTextView

class MainActivity : AppCompatActivity(), MainView {

    /**
     * Экземпляр презентера обслуживающего эту View
     */
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter()
        presenter.attachView(this)

        loadRandomButton.setOnClickListener {
            presenter.onRandomButtonClicked(simulateNetworkDelayCheckBox.isChecked)
        }

        loadFromUrlButton.setOnClickListener {
            presenter.onLoadFromUrlButtonClicked(urlEditTextView.text.toString(),
                simulateNetworkDelayCheckBox.isChecked)
        }
    }

    override fun showImage(image: Bitmap) {
        imageView.setImageBitmap(image)
    }

    override fun showError(message: String) {
        Snackbar.make(mainContainer, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("url", urlEditTextView.text.toString())
        outState.putParcelable("image", (imageView.drawable as BitmapDrawable).bitmap)
        outState.putBoolean("simulateNetworkDelay", simulateNetworkDelayCheckBox.isChecked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        imageView.setImageBitmap(savedInstanceState.getParcelable<Bitmap>("image"))
        urlEditTextView.setText(savedInstanceState.getString("url").toString(), TextView.BufferType.EDITABLE)
        simulateNetworkDelayCheckBox.isChecked = savedInstanceState.getBoolean("simulateNetworkDelay")
    }

    override fun onDestroy() {
        presenter.detachView()

        super.onDestroy()
    }
}
