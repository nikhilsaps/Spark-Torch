package com.nexus.spark

import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nexus.spark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var cameraManager: CameraManager
    private lateinit var camID:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            camID =cameraManager.cameraIdList[0]
        }
        catch (e:Exception){
            Log.d("SparkMain","camera id failure.${e}")
        }

    }
    override fun onStart() {
        super.onStart()
        Log.d("SparkMain","Activity Started")
    }

    override fun onResume() {
        super.onResume()
        binding.nrmlTrch.setOnClickListener{

            if (binding.nrmlTrch.isChecked) {
                cameraManager.setTorchMode(camID,true)

                Log.d("SparkMain","turned on the camera ")
            }
            else{

                cameraManager.setTorchMode(camID,false)

                Log.d("SparkMain","turing Off Torch")

            }
        }
    }

    override fun onPause() {
        super.onPause()
            cameraManager.setTorchMode(camID,false)
        Log.d("SparkMain","turing Off Torch")

    }

    override fun onStop() {
        super.onStop()
        cameraManager.setTorchMode(camID,false)
        Log.d("SparkMain","turing Off Torch")
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraManager.setTorchMode(camID,false)
        Log.d("SparkMain","turing Off Torch")
    }

    override fun onRestart() {
        super.onRestart()
    }
}