package com.avengers.lgmtask2;

import android.app.Application;
import android.graphics.Bitmap;

import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

public class LCOFaceDetection extends Application {
    public static Bitmap imgBitmap;
    public static FaceDetector detector;
    static {
        setFaceDetectorOptionsAndDetector();
    }

    private static void setFaceDetectorOptionsAndDetector(){
         FaceDetectorOptions options= new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();
        detector = FaceDetection.getClient(options);
    }

}
