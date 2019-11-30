package com.example.remoteserviceapp

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.widget.Toast

class RemoteService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder = object : IMyAidlInterface.Stub() {
        override fun test(text : String){
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }
        override fun randomColor(): String {
            val color = Color.argb(255, (0..255).random(), (0..255).random(), (0..255).random())
            return color.toString()
        }
    }
}
