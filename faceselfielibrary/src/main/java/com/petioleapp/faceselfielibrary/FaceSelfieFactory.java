package com.petioleapp.faceselfielibrary;

import android.content.Context;

public class FaceSelfieFactory {
    private Context context;

    public FaceSelfieFactory(Context context) {
      this.context = context;
    }

    public FaceSelfie getClient() {
      return new FaceSelfie(context);
    }
}
