package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ApiRequest : AppCompatActivity() {
    val apiSample = "https://regres.in/api/users"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_request)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.e("Entrou aqui","Entrou aqui")
        val reqQueue: RequestQueue = Volley.newRequestQueue(this)
//        val request = StringRequest(Request.Method.GET,apiSample,{ result ->
//            Log.d("Volley Example", result.toString())
//        },{
//            err->
//            Log.d("Volley Example",err.message.toString())
//        })
        val request = JsonObjectRequest(Request.Method.GET,apiSample,null,{ res->


        },{
            err->
            Log.d("Volley Example",err.message.toString())
       })

        reqQueue.add(request)
    }
}