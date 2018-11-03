package com.petioleapp.faceselfielibrary.activities

import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.google.firebase.ml.common.FirebaseMLException
import com.petioleapp.faceselfielibrary.R
import com.petioleapp.faceselfielibrary.camera.CameraSource
import com.petioleapp.faceselfielibrary.detector.FaceDetectionProcessor
import kotlinx.android.synthetic.main.activity_face_preview.*
import java.io.IOException

class FacePreviewActivity : AppCompatActivity() {
    private var cameraSource: CameraSource? = null

    private val requiredPermissions: Array<String?>
        get() {
            return try {
                val info = this.packageManager
                    .getPackageInfo(this.packageName, PackageManager.GET_PERMISSIONS)
                val ps = info.requestedPermissions
                if (ps != null && ps.isNotEmpty()) {
                    ps
                } else {
                    arrayOfNulls(0)
                }
            } catch (e: Exception) {
                arrayOfNulls(0)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_face_preview)

        if (facePreview == null) { }

        facePreview.stop()
        if (allPermissionsGranted()) {
            createCameraSource()
            startCameraSource()
        } else {
            getRuntimePermissions()
        }
    }

    private fun startCameraSource() {
        cameraSource?.setFacing(CameraSource.CAMERA_FACING_FRONT)

        cameraSource.let {
            try {
                if (facePreview == null) {
                    Log.d(TAG, "resume: Preview is null")
                }
                if (faceOverlay == null) {
                    Log.d(TAG, "resume: graphOverlay is null")
                }
                facePreview.start(it, faceOverlay)
            } catch (e: IOException) {
                Log.e(TAG, "Unable to start camera source.", e)
                it?.release()
                cameraSource = null
            }
        }
    }

    private fun getRuntimePermissions() {
        val allNeededPermissions = ArrayList<String>()
        for (permission in requiredPermissions) {
            permission?.let {
                if (!isPermissionGranted(this, it)) {
                    allNeededPermissions.add(it)
                }
            }
        }

        if (!allNeededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, allNeededPermissions.toTypedArray(), PERMISSION_REQUESTS)
        }
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in requiredPermissions) {
            permission?.let {
                if (!isPermissionGranted(this, it)) {
                    return false
                }
            }
        }
        return true
    }

    private fun createCameraSource() {
        if (cameraSource == null) {
            cameraSource = CameraSource(this, faceOverlay)
        }

        try {
            cameraSource?.let {
                it.setMachineLearningFrameProcessor(FaceDetectionProcessor())
            }
        } catch (e: FirebaseMLException) {
            Log.e(TAG, "can not create camera source")
        }
    }

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    public override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        startCameraSource()
    }

    /** Stops the camera.  */
    override fun onPause() {
        super.onPause()
        facePreview.stop()
    }

    public override fun onDestroy() {
        super.onDestroy()
        cameraSource?.release()
    }

    companion object {
        private const val PERMISSION_REQUESTS = 1
        private const val TAG = "FacePreviewActivity"
    }
}
