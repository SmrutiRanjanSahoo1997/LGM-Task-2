package com.avengers.lgmtask2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import static com.avengers.lgmtask2.LCOFaceDetection.imgBitmap;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> launcher;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=findViewById(R.id.img);
        LaunchActivityForResult();
        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager())!=null){
                launcher.launch(intent);
            }else
                Toast.makeText(MainActivity.this,"There is no app that support camera",Toast.LENGTH_LONG).show();
        });
    }


    private void LaunchActivityForResult(){
        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode()==RESULT_OK && result.getData()!=null){
                imgBitmap=(Bitmap) result.getData().getExtras().get("data");
                img.setImageBitmap(imgBitmap);
                ResultDialog resultDialog=new ResultDialog();
                resultDialog.show(getFragmentManager(),"Result Dialog");
            }
        });
    }

}