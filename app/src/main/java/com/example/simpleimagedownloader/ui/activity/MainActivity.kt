package com.example.simpleimagedownloader.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleimagedownloader.R
import com.example.simpleimagedownloader.ui.contract.MainView

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
