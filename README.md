# FaceDetect Android SDK

## Adding the SDK dependency

implementation project(":faceselfielibrary")

## Instantiating the client

```selfie = FaceSelfieFactory(this).client```

## Starting the flow

`selfie?.startActivityForResult(this, SELFIE_REQUEST)`

SDK will return file_name in OnActivityResult. So you can display captured image

## How it works

For detecting faces SDK uses ML Kit from Firebase.

General flow looks like this:

- FacePreviewActivity started & checks for camera & save file permissions
- activity creates & starts camera source
- CameraSource assigns FaceDetectionProcessor
- CameraSource acquire image frames data, which converts and passed to VisionImageProcessor
- Runner checks every 3 sec amount of faces with was detected
- if face count is 1 it takes picture in CameraSource & saves it to external store
- file name passed back to MainActivity for further processing
- GraphicOverlay uses for display info about detected face

![alt text](https://github.com/pomidorus/facedetect/blob/master/screen_01.png "Screen 01")
![alt text](https://github.com/pomidorus/facedetect/blob/master/screen_02.png "Screen 02")
![alt text](https://github.com/pomidorus/facedetect/blob/master/screen_03.png "Screen 03")
