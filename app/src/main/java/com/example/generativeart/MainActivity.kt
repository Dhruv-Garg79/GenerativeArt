package com.example.generativeart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artCanvas = ArtCanvas(this)
        artCanvas.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        artCanvas.setBackgroundColor(Color.BLACK)
        setContentView(artCanvas)

    }
}
