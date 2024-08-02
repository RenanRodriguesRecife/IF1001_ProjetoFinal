package com.example.teste2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue


import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class WeatherRequest : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_request)

        val viewText = findViewById<TextView>(R.id.textView6) as TextView

        // Initialize the RequestQueue
        val queue: RequestQueue = Volley.newRequestQueue(this)

        // URL da API de tempo
        val url = "http://api.openweathermap.org/data/2.5/forecast?q=Paris&appid=9c7f431fa1554bd540dbb30a7e0c8f63"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                viewText.text = response.toString()
            },
            {
                Log.e("test","That didn't work!")
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

    private fun parseWeatherResponse(response: String) {


    }

}