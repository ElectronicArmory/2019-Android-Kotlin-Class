package com.electronicarmory.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val helloTextView = findViewById<TextView>(R.id.hello_textview)

        val button: Button = findViewById<Button>(R.id.hello_button)

        val firstNameEditText = findViewById<EditText>(R.id.firstName_editText)
        val lastNameEditText = findViewById<EditText>(R.id.lastName_editText)

        button.setOnClickListener {
            val name:String = "${firstNameEditText.text} ${lastNameEditText.text}"
            helloTextView.text = name
        }

        val age:Int = 100
        val salary:Double = 33000.30

        val benefits = age * salary

        Log.d("BSU", "Benefits this year: ${benefits.toString()}.")

        val year = "2019".toInt()
        val nameInt:Int = 5

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}
