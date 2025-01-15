package com.example.sujalgpt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartHere : AppCompatActivity() {

    lateinit var chat:Button
    lateinit var speak:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start_here)

       chat =findViewById(R.id.chatbtn)
        speak=findViewById(R.id.speech)

        chat.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java).also {
                startActivity(it)
            }
        }
        speak.setOnClickListener {
            val intent = Intent(this,SpeechToText::class.java).also {
                startActivity(it)
            }
        }

    }
}