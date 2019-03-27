package boisestate.edu.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cameraButton:Button = findViewById(R.id.camera_button)


        cameraButton.setOnClickListener {

            val cameraCheckPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA )

            if( cameraCheckPermission != PackageManager.PERMISSION_GRANTED){
                if( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) == true){
                    val builder = AlertDialog.Builder(this)

                    builder.setMessage("I need to see you to work properly!!")
                            .setTitle("Permission required")
                            .setPositiveButton("OK") { dialog, id ->
                        requestPermission()
                    }
                    val dialog = builder.create()
                    dialog.show()

                }
                else{
                    requestPermission()

                }
            }
            else{
                launchCamera()
            }

        }
    }


    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 765)

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for ((index, permission) in permissions.withIndex()){
            if( permission == Manifest.permission.CAMERA){
                if( grantResults[index] == PackageManager.PERMISSION_GRANTED){
                    launchCamera()
                }
            }
        }


    }

    private fun launchCamera(){

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 9090)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode == 9090){

            if( data != null ) {
                val imageData: Bitmap = data.extras.get("data") as Bitmap

                val imageView = findViewById<ImageView>(R.id.image_view)
                imageView.setImageBitmap(imageData)
            }
        }
    }
}
