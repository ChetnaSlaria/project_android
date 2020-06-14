package com.sachtech.datingapp.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*


class MyLocationLIstener(val context: Context, val onLocationChanged: OnLocationChanged) {


    private val UPDATE_INTERVAL = 2000
    private val FASTEST_INTERVAL = 1500

    fun init() {
        createGoogleApi()
    }

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private fun createGoogleApi() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        getDeviceLastLocation()
    }

     var mLastKnownLocation: Location?=null
    @SuppressLint("MissingPermission")
    fun getDeviceLastLocation() {
        val locationResult = mFusedLocationProviderClient?.lastLocation
        locationResult?.addOnCompleteListener {
            if (it.isSuccessful) {
                mLastKnownLocation = it.result
                if (mLastKnownLocation == null)
                    startLocationUpdates()
                else onLocationChanged.onLocationUpdated(mLastKnownLocation!!)

            } else {
                startLocationUpdates()

            }

        }

    }

    private lateinit var locationCallback: LocationCallback


    protected fun startLocationUpdates() {
        var mLocationRequest = LocationRequest.create()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = UPDATE_INTERVAL.toLong()
        mLocationRequest.fastestInterval = FASTEST_INTERVAL.toLong()

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(context)
        settingsClient.checkLocationSettings(locationSettingsRequest)
        //getStreetList()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                if (p0 != null) {
                    //updateMapUI(p0?.getLastLocation())
                    val lastLocation = p0.lastLocation
                    if (lastLocation != null) {
                        removeLocationUpdates()
                        onLocationChanged.onLocationUpdated(lastLocation)
                    }
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
                if (p0?.isLocationAvailable == false)
                    getDeviceLastLocation()
            }
        }

        mFusedLocationProviderClient?.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper())

    }
    fun removeLocationUpdates() {
        mFusedLocationProviderClient?.removeLocationUpdates(locationCallback)
    }

    interface OnLocationChanged {
        fun onLocationUpdated(location: Location)
    }
}

