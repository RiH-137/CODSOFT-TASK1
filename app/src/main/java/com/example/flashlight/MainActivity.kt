package com.example.flashlight

import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    lateinit var flashLight:ImageView
    private var isActive = false
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flashLight=findViewById(R.id.imgoff)

        lightState(isActive)
        flashLight.setOnClickListener{
            if (isActive==false){
                flashLight.setImageResource(R.drawable.baseline_flashlight_on_24)
                isActive=true
                lightState(isActive)
            }
            else{
                flashLight.setImageResource(R.drawable.baseline_flashlight_off_24)
                isActive=false
                lightState(isActive)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lightState(state:Boolean) {
        val cameraManager:CameraManager=getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId:String?=null

        try{
            cameraId=cameraManager.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, state)
            }
        }
        catch(e:Exception){
            Toast.makeText(this, e.message,Toast.LENGTH_SHORT).show()
        }
    }
}