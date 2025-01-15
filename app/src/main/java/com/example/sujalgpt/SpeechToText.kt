package com.example.sujalgpt

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SpeechToText : AppCompatActivity() {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var btnStartSpeech: Button
    private lateinit var btnmood:Button
    private lateinit var tvSpeechResult: TextView
    lateinit var da:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_to_text)

        // Initialize UI components
        btnStartSpeech = findViewById(R.id.btnStartSpeech)
        tvSpeechResult = findViewById(R.id.tvSpeechResult)

        // Request audio recording permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        // Set up recognition listener
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                tvSpeechResult.text = "Error recognizing speech"
            }

            override fun onResults(results: Bundle?) {
                val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                tvSpeechResult.text = data?.get(0) ?: "No speech recognized"
                da="Give my mood with this prompt : ${data?.get(0)}"


            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        // Set up button click to start speech recognition
        btnStartSpeech.setOnClickListener {
            // Use the correct way to create an Intent for speech recognition
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            }
            speechRecognizer.startListening(intent)
        }
//        btnmood=findViewById(R.id.mood)
//
//        btnmood.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java).apply {
//                putExtra("EXTRA_DATA", da) // Put extra data
//            }
//
//            // Start SecondActivity
//            startActivity(intent)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy() // Clean up SpeechRecognizer resources
    }
}
