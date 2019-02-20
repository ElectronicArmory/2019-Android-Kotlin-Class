package com.electronicarmory.secondactivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class DetailsActivity : AppCompatActivity() {

    companion object {
        val KEY_MILES = "MILES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val dataFromMainActivity:Int = intent.getIntExtra(KEY_MILES)

        val returnButton = findViewById<Button>(R.id.return_button)

        returnButton.setOnClickListener {
            val mileage_editText = findViewById<EditText>(R.id.mileage_editText)

            val mileageString:String = mileage_editText.text.toString()
//            val mileageInt:Int = mileageString.toInt()

            val returnIntent: Intent = Intent()
            returnIntent.putExtra(KEY_MILES, mileageString)

            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
