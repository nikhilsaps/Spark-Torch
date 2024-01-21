# Spark: Simple Torch App for Android

Spark is a lightweight Android application designed to provide a quick and easy-to-use torch functionality. The app includes a toggle button to control the flashlight, and it incorporates lifecycle management to turn off the torch when the app is paused, stopped, or closed.

## Features

- Toggle button for flashlight control
- Lifecycle management to turn off the torch when the app is paused, stopped, or closed
- No additional permissions required, utilizing only the device's flashlight capability

## Screenshots

![Spark App Screenshot](path/to/your/screenshot.png)

## Code Sample

```kotlin
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
```
