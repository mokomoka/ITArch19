package com.example.remoteserviceapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Intent(this, RemoteService::class.java).also{ intent ->
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
        button.setOnClickListener {
            try {
                iRemoteService?.test(editText.text.toString())
            } catch (e : RemoteException) {
                e.printStackTrace()
            }
        }
    }

    var iRemoteService : IMyAidlInterface? = null

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            iRemoteService = IMyAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName?) {
            Log.e("MainActivity", "Service has unexpectedly disconnected")
            iRemoteService = null
        }
    }
}
