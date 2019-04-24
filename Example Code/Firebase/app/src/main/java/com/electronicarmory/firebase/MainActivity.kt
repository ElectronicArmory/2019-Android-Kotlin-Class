package com.electronicarmory.firebase

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.electronicarmory.firebase.dummy.DummyContent

class MainActivity : AppCompatActivity(), EventFragment.OnListFragmentInteractionListener {



    private lateinit var eventsFragment: EventFragment




    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, eventsFragment)
                    .commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        eventsFragment = EventFragment()

        EventsController.refreshEvents()

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, eventsFragment)
            .commit()
    }


    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
