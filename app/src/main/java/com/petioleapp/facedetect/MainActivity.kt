package com.petioleapp.facedetect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var takeSelfie: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takeSelfie = findViewById(R.id.takeSelfie)
        takeSelfie?.setOnClickListener{ doTakeSelfie() }
    }

    private fun doTakeSelfie() {

    }
}
