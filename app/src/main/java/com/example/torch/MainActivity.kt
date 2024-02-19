package com.example.torch

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    var cameraFlash = false
    var flashOn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageButton:ImageButton = findViewById(R.id.imageButton)
        cameraFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        imageButton.setOnClickListener{
            if (cameraFlash){
                if (flashOn){
                    flashOn = false
                    imageButton.setImageResource(R.drawable.off)
                    flashLightOff()
                }
                else{
                    flashOn = true
                    imageButton.setImageResource(R.drawable.on)
                    flashLightOn()
                }
            }
        }
    }

    private fun flashLightOff() {
        val cameraManager:CameraManager = getSystemService(CAMERA_SERVICE) as   CameraManager
        var cameraId:String
        try{
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId,false)
        }catch (e:Exception){}
    }

    private fun flashLightOn() {
        val cameraManager:CameraManager = getSystemService(CAMERA_SERVICE) as   CameraManager
        var cameraId:String
        try{
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId,true)
        }catch (e:Exception){}
    }
}