package com.petioleapp.facedetect

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.petioleapp.faceselfielibrary.FaceSelfie
import com.petioleapp.faceselfielibrary.FaceSelfieFactory
import android.graphics.BitmapFactory
import kotlinx.android.synthetic.main.activity_main.*


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SELFIE_REQUEST) {
            if (resultCode == RESULT_OK) {
                val fileName = data?.getStringExtra("fileName")
                val bitmap = BitmapFactory.decodeFile(fileName)
                selfieView.setImageBitmap(bitmap)
            }
        }
    }

    private fun doTakeSelfie() {
      selfie?.startActivityForResult(this, SELFIE_REQUEST)
    }

    companion object {
      private const val SELFIE_REQUEST = 1
    }
}
