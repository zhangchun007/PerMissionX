package com.jimmy.permissionx

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private var btnCall: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCall = findViewById(R.id.btnCall)
        btnCall?.setOnClickListener {
            PermissionX.request(
                this,
                android.Manifest.permission.CALL_PHONE
            ) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

