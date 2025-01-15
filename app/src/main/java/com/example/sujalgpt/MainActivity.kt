package com.example.sujalgpt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sujalgpt.Message
import com.example.sujalgpt.MessageAdapter
import com.example.sujalgpt.R
import com.google.ai.client.generativeai.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
// other imports...
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var welcomeTextView: TextView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var messageList: MutableList<Message>
    private lateinit var messageAdapter: MessageAdapter
    private val client = OkHttpClient()

    companion object {
        val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val receivedData = intent.getStringExtra("EXTRA_DATA")


        messageList = mutableListOf()
        recyclerView = findViewById(R.id.recycler_view_gpt)
        welcomeTextView = findViewById(R.id.welcome_text)
        messageEditText = findViewById(R.id.msg_edit_text)
        sendButton = findViewById(R.id.send_btn)

        // Setup recycler view
        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        val llm = LinearLayoutManager(this).apply { stackFromEnd = true }
        recyclerView.layoutManager = llm

        sendButton.setOnClickListener {
            val question = messageEditText.text.toString().trim()
            addToChat(question, Message.SENT_BY_ME)
            messageEditText.setText("")
            callAPI(question) ///data changing now
            welcomeTextView.visibility = View.GONE
        }
    }

    private fun addToChat(message: String, sentBy: String) {
        runOnUiThread {
            messageList.add(Message(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    private fun addResponse(response: String) {
        messageList.removeAt(messageList.size - 1)
        addToChat(response, Message.SENT_BY_BOT)
    }

    private fun callAPI(question: String) {
        // okhttp
        messageList.add(Message("Typing...", Message.SENT_BY_BOT))

        val generativeModel = GenerativeModel(
            // For text-only input, use the gemini-pro model
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = "AIzaSyBtnxfC1aQ0UkZ6EWv1zgi26mrazrI-Z4E"
        )
        MainScope().launch {
            val response = generativeModel.generateContent(question.trimIndent())

            addResponse(response.text.toString())
        }

    }
}
