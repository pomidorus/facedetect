package com.petioleapp.faceselfielibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.petioleapp.faceselfielibrary.activities.FacePreviewActivity;

public class FaceSelfie {
    private Activity mContext;

    public FaceSelfie(Context context) {
        this.mContext = (Activity) context;
    }

    public void startActivityForResult(Context context, int requestCode) {
        Intent intent = new Intent(context, FacePreviewActivity.class);
        mContext.startActivityForResult(intent, requestCode);
    }
}
