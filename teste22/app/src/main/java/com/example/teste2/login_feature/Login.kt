package com.example.teste2.login_feature

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.teste2.MainActivity
import com.example.teste2.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(email, password)
        }

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            registerUser(email, password)
        }



    }

    private fun loginUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Log.d("Login","signInWithEmail:success")
                    Toast.makeText(baseContext,"Authentication successful.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent);
                }else{
                    Log.w("Login","signInWithEmail:failure")
                    Toast.makeText(baseContext,"Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun registerUser(email:String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Log.d("Register","createUserWithEmail:success")
                    Toast.makeText(baseContext,"Registration successful", Toast.LENGTH_SHORT).show()

                }else{
                    Log.d("Register","createUserWithEmail:failures",task.exception)
                    Toast.makeText(baseContext,"Registration failed", Toast.LENGTH_SHORT).show()

                }

            }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            reload()
        }
    }
}