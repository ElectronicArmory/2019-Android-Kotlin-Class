package com.electronicarmory.observereventbus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    lateinit var textView:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        EventBus.getDefault().register(this)
    }

    override fun onStart() {
        super.onStart()

//        EventBus.getDefault().register(this)
        Log.d("BSU", "On Start")
    }

    override fun onStop() {
        super.onStop()
//        EventBus.getDefault().unregister(this)
        Log.d("BSU", "On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageReceived( event:MessageInABottle ){
        val messageString:String = event.messageInABottle

        textView = findViewById(R.id.textView)
        textView.text = messageString
    }
}
