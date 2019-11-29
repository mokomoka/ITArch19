package com.example.remoteserviceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class RemoteService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder = object : IMyAidlInterface.Stub() {
        public override fun test(text : String){
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }
    }
}
