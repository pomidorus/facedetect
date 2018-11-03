package com.petioleapp.facedetect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.petioleapp.faceselfielibrary.FaceSelfie
import com.petioleapp.faceselfielibrary.FaceSelfieFactory

class MainActivity : AppCompatActivity() {
    private var takeSelfie: Button? = null
    private var selfie: FaceSelfie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      takeSelfie = findViewById(R.id.takeSelfie)
      takeSelfie?.setOnClickListener{ doTakeSelfie() }

      selfie = FaceSelfieFactory(this).client
    }

    private fun doTakeSelfie() {
      selfie?.startActivityForResult(this, 1)
    }
}
