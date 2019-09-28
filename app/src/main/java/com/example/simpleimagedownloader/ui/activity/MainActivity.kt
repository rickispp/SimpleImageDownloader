package com.example.simpleimagedownloader.ui.activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.simpleimagedownloader.R
import com.example.simpleimagedownloader.ui.contract.MainContract
import com.example.simpleimagedownloader.ui.presenter.MainActivityPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        runOnUiThread {
            imageView.setImageBitmap(image)
        }
    }

    override fun showError(message: String) {
        runOnUiThread {
            Snackbar.make(mainContainer, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showWaitForm() {
        runOnUiThread {
            progressFormContainer.visibility = View.VISIBLE

            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    override fun hideWaitForm() {
        runOnUiThread {
            progressFormContainer.visibility = View.GONE

            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("url", urlEditTextView.text.toString())
        outState.putParcelable("image", (imageView.drawable as BitmapDrawable).bitmap)
        outState.putBoolean("simulateNetworkDelay", simulateNetworkDelayCheckBox.isChecked)
        outState.putBoolean("waitFormVisible", progressFormContainer.isVisible)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        urlEditTextView.setText(savedInstanceState.getString("url").toString(), TextView.BufferType.EDITABLE)
        imageView.setImageBitmap(savedInstanceState.getParcelable("image"))
        simulateNetworkDelayCheckBox.isChecked = savedInstanceState.getBoolean("simulateNetworkDelay")

        if(savedInstanceState.getBoolean("waitFormVisible")) {
            showWaitForm()
        } else {
            hideWaitForm()
        }
    }

    override fun onDestroy() {
        presenter.detachView()

        super.onDestroy()
    }

    companion object {
        /**
         * Экземпляр презентера обслуживающего эту View
         */
        private val presenter: MainContract.MainPresenter = MainActivityPresenter()
    }
}
