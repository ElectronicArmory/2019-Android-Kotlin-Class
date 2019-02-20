package com.electronicarmory.observereventbus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val postButton:Button = findViewById(R.id.post_message_button)

        postButton.setOnClickListener {
            val textView:TextView = findViewById(R.id.editText)

            val messageFromTextView:String = textView.text.toString()

            EventBus.getDefault().post(MessageInABottle(messageFromTextView))
        }
    }
}
