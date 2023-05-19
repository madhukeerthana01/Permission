package com.example.myapplication;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<String[]> mpermissionResultLauncher;
    private boolean isReadpermissionGranted = false;
    private boolean isRecordpermissionGranted = false;
    private boolean isMediaimagespermissiongranted = false;
    private boolean isMediavideoPermissiongranted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonRequest = findViewById(R.id.button);
        buttonRequest.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestpermission();
            }
        }));
        mpermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                if(result.get(Manifest.permission.READ_EXTERNAL_STORAGE)!=null){
                    isReadpermissionGranted = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);
                }


                if(result.get(Manifest.permission.RECORD_AUDIO)!=null){
                    isRecordpermissionGranted = result.get(Manifest.permission.RECORD_AUDIO);
                }

                if(result.get(Manifest.permission.READ_MEDIA_IMAGES)!=null){
                    isMediaimagespermissiongranted = result.get(Manifest.permission.READ_MEDIA_IMAGES);
                }

                if(result.get(Manifest.permission.READ_MEDIA_VIDEO)!=null){
                    isMediavideoPermissiongranted = result.get(Manifest.permission.READ_MEDIA_VIDEO);
                }
            }
        });
    }
    private void requestpermission(){

        Log.d("01","PERMISSION ALREADY GRANTED");
        isReadpermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )== PackageManager.PERMISSION_GRANTED;


        isRecordpermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
        )== PackageManager.PERMISSION_GRANTED;


        isMediaimagespermissiongranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
        )== PackageManager.PERMISSION_GRANTED;



        isRecordpermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_VIDEO
        )== PackageManager.PERMISSION_GRANTED;


        List<String> permissionRequest = new ArrayList<String>();

        if(!isReadpermissionGranted){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if(!isRecordpermissionGranted){
            permissionRequest.add(Manifest.permission.RECORD_AUDIO);

        }
        if(!isMediaimagespermissiongranted){
            permissionRequest.add(Manifest.permission.READ_MEDIA_IMAGES);

        }
        if(!isMediavideoPermissiongranted){
            permissionRequest.add(Manifest.permission.READ_MEDIA_VIDEO);

        }
        if(!permissionRequest.isEmpty()){
            mpermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));
        }
    }
}