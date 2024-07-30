package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class DMinternalCache : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dminternal_cache)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView) as TextView

        val editText1 = findViewById<EditText>(R.id.editTextText3) as TextView
        val editText2 = findViewById<EditText>(R.id.editTextText4) as TextView

        val btn_getCacheDir = findViewById<Button>(R.id.btn_getCacheDir) as Button
        val btn_createCacheDir = findViewById<Button>(R.id.btn_createCacheDir) as Button
        val btn_createTmpFile = findViewById<Button>(R.id.btn_createCacheTmpFile) as Button
        val btn_createCacheFile = findViewById<Button>(R.id.btn_createCacheFile) as Button
        val btn_getCacheFile = findViewById<Button>(R.id.btn_getCacheFile) as Button

        btn_getCacheDir.setOnClickListener {
            val file = applicationContext.cacheDir
            textView.setText("getCacheDir() " + file)
        }

        btn_createCacheDir.setOnClickListener {
            var dirName = editText1.text.toString()
            val dir = File(applicationContext.cacheDir,dirName)
            dir.mkdirs()
            textView.setText("getCacheDir() " + dir)
        }

        btn_createTmpFile.setOnClickListener {
            var fileName = editText1.text.toString()
            val tmpFile = File.createTempFile(fileName,null,applicationContext.cacheDir)
            textView.setText("createTempFile() " + tmpFile)
        }

        btn_createCacheFile.setOnClickListener {
            var cacheDir = editText1.text.toString()
            var content = editText2.text.toString()
            val dir = File(applicationContext.cacheDir,"asdf")
            val file = File(dir,content)

            BufferedOutputStream(FileOutputStream(file)).use{ bos ->
                bos.write(content.toByteArray())
            }
        }

        btn_getCacheFile.setOnClickListener {
            var cacheDir = editText1.text.toString()
            var dir = File(applicationContext.cacheDir,cacheDir)
            val file = File(dir,"asdf")

            var value: String = ""
            file.bufferedReader().useLines{ lines ->
                value = lines.fold(""){ some, text ->
                    "$some\n$text"
                }.toString()
            }
            textView.setText("cache file:  " + value)
        }




    }
}