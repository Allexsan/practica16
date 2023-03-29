package com.example.sensorinfo.application

import android.app.Application
import com.example.sensorinfo.repositories.SensorRepository

class SensorApp: Application(){
    override fun onCreate(){
        super.onCreate()
        SensorRepository.initialize(this)
    }
}