package com.example.remoteserviceapp

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder

class RemoteService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder = object : IMyAidlInterface.Stub() {
        override fun randomColor() : Int{
            return Color.argb(255, (0..255).random(), (0..255).random(), (0..255).random())
        }
    }
}
