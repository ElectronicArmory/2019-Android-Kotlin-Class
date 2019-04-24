package com.electronicarmory.fragments

import android.app.Fragment
import android.app.FragmentTransaction
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.electronicarmory.fragments.dummy.PhotoContent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    GalleryFragment.OnFragmentInteractionListener, PhotoFragment.OnListFragmentInteractionListener {



    var galleryFragment:Fragment? = null
    var photoFragment:PhotoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        galleryFragment = GalleryFragment() as Fragment
        photoFragment = PhotoFragment()

        fragmentManager.beginTransaction()
            .add(R.id.fragment_content_view, galleryFragment)
            .addToBackStack("GALLERY")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
//            .newInstance("string 1", "string 2")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_content_view, galleryFragment)
                    .addToBackStack("GALLERY")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
            }
            R.id.nav_slideshow -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_content_view, photoFragment)
                    .addToBackStack("PHOTO")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onListFragmentInteraction(item: PhotoContent.PhotoItem?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
