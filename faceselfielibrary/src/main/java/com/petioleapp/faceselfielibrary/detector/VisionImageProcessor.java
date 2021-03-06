package com.petioleapp.faceselfielibrary.detector;

import android.graphics.Bitmap;
import com.google.firebase.ml.common.FirebaseMLException;
import com.petioleapp.faceselfielibrary.camera.FrameMetadata;
import com.petioleapp.faceselfielibrary.camera.GraphicOverlay;

import java.nio.ByteBuffer;

public interface VisionImageProcessor {
    int sceneClassification();

    /** Processes the images with the underlying machine learning models. */
    void process(ByteBuffer data, FrameMetadata frameMetadata, GraphicOverlay graphicOverlay)
            throws FirebaseMLException;

    /** Processes the bitmap images. */
    void process(Bitmap bitmap, GraphicOverlay graphicOverlay);

    /** Stops the underlying machine learning model and release resources. */
    void stop();
}

