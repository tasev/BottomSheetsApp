package com.animations.myanimapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_map.*


class MapActivity : AppCompatActivity() {
    private var mMap: GoogleMap? = null
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        init()
        if (savedInstanceState == null) {
            setFragment(DummyDetailsFragmentOne.newInstance())
        }
    }

    private fun init(){
        initMapFragment()
        initComponent()
        text_view_go_to_fragment_one.setOnClickListener {
            setFragment(DummyDetailsFragmentOne.newInstance())
        }
        text_view_go_to_fragment_two.setOnClickListener {
            setFragment(DummyDetailsFragmentTwo.newInstance())
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_navigator, fragment)
            .commit()
    }

    private fun initComponent() { // get the bottom sheet view
        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(fragment_navigator)
        // change the state of the bottom sheet
        (bottomSheetBehavior as BottomSheetBehavior<*>).state = BottomSheetBehavior.STATE_COLLAPSED
        // set callback for changes
        (findViewById<View>(R.id.fab_directions) as FloatingActionButton).setOnClickListener {
                    (bottomSheetBehavior as BottomSheetBehavior<*>).state = if((bottomSheetBehavior as BottomSheetBehavior<*>).state == BottomSheetBehavior.STATE_COLLAPSED) BottomSheetBehavior.STATE_HALF_EXPANDED else BottomSheetBehavior.STATE_COLLAPSED
                    try {
                        mMap!!.animateCamera(zoomingLocation())
                    } catch (e: Exception) {
                    }
            }
    }

    private fun initMapFragment() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync { googleMap ->
            mMap = Utils.configActivityMaps(googleMap)
            val markerOptions =
                MarkerOptions().position(LatLng(42.0050000, 21.4408000))
            mMap!!.addMarker(markerOptions)
            mMap!!.moveCamera(zoomingLocation())
            mMap!!.setOnMarkerClickListener {
                bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
                try {
                    mMap!!.animateCamera(zoomingLocation())
                } catch (e: Exception) {
                }
                true
            }
        }
    }

    private fun zoomingLocation(): CameraUpdate {
        return CameraUpdateFactory.newLatLngZoom(LatLng(42.0050000, 21.4408000), 13f)
    }
}