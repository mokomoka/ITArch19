package com.example.clientapp

import android.content.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import com.example.remoteserviceapp.IMyAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if(iRemoteService == null){
            val it  = Intent("MyRemoteService")
            it.setPackage("com.example.remoteserviceapp")
            bindService(it, mConnection, Context.BIND_AUTO_CREATE)
        }
        button.setOnClickListener {
            try {
                val color = iRemoteService?.randomColor()
                constraintLayout.setBackgroundColor(color!!)
                textView.text = "#" + String.format("%X", color).substring(2)
            } catch (e : RemoteException) {
                e.printStackTrace()
            }
        }
        textView.setOnClickListener {
            val clipboardService = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("", textView.text.toString())
            clipboardService.setPrimaryClip(clipData)
            Toast.makeText(applicationContext, "Copied to the clipboard.", Toast.LENGTH_SHORT).show()
        }
    }

    var iRemoteService : IMyAidlInterface? = null

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            iRemoteService = IMyAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName?) {
            Log.e("ClientApplication", "Service has unexpectedly disconnected")
            iRemoteService = null
        }
    }
}
