package com.avengers.lgmtask2;

import static com.avengers.lgmtask2.LCOFaceDetection.detector;
import static com.avengers.lgmtask2.LCOFaceDetection.imgBitmap;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;

public class ResultDialog extends DialogFragment {

    TextView resultText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Result Dialog");
        View view=inflater.inflate(R.layout.fragment_resultdialog,container,false);
        resultText=view.findViewById(R.id.resultText);
        getMLVisionAnalysis();
        view.findViewById(R.id.ok).setOnClickListener(v -> dismiss());
        return view;
    }

    private void getMLVisionAnalysis(){


        InputImage image = InputImage.fromBitmap(imgBitmap,0);
        detector.process(image)
                .addOnSuccessListener(
                        faces -> {
                            int faceNo=1;
                            StringBuilder result= new StringBuilder();
                            for (Face face : faces) {
                                result.append("FACE : ").append(faceNo);
                                // If classification was enabled:
                                if (face.getSmilingProbability() != null) {
                                    float smileProb = face.getSmilingProbability()*100;
                                    int smilePercentage=(int)smileProb;
                                    result.append("\nSmilePercentage : ").append(smilePercentage);
                                }
                                if (face.getRightEyeOpenProbability() != null) {
                                    float rightEyeOpenProb = face.getRightEyeOpenProbability()*100;
                                    int rightEyeOpenPercentage=(int)rightEyeOpenProb;
                                    result.append("\nRightEyeOpenPercentage : ").append(rightEyeOpenPercentage);
                                }
                                if (face.getLeftEyeOpenProbability() !=null){
                                    float leftEyeOpenProb = face.getLeftEyeOpenProbability()*100;
                                    int leftEyeOpenPercentage=(int)leftEyeOpenProb;
                                    result.append("\nLeftEyeOpenPercentage : ").append(leftEyeOpenPercentage);
                                }
                                faceNo++;
                                result.append("\n\n");
                            }
                            resultText.setText(result.toString());
                        })
                .addOnFailureListener(
                        Throwable::printStackTrace);




    }
}
