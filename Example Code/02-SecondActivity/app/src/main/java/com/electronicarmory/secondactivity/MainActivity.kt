package com.electronicarmory.secondactivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launchButton: Button = findViewById(R.id.launch_button)

        launchButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)

//            intent.putExtra(DetailsActivity.KEY_MILES, 30000)

            startActivityForResult(intent, 42)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("BSU", requestCode.toString())
        Log.d("BSU", resultCode.toString())
        if( resultCode == Activity.RESULT_OK ) {
            Log.d("BSU", "This is the result from the second activity.")

            if( data != null ) {
                val newMileage = data.getStringExtra(DetailsActivity.KEY_MILES)

                if( newMileage != null ) {
                    val mileageTextView = findViewById<TextView>(R.id.mileage_textview)
                    mileageTextView.text = "${newMileage.toString()}\n${mileageTextView.text}"
                }
            }
        }
    }
}
