package com.example.myapplication

import android.annotation.SuppressLint
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
import org.w3c.dom.Text

class DMinternal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dminternal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView) as TextView

        val editText1 = findViewById<EditText>(R.id.editTextText3) as TextView
        val editText2 = findViewById<EditText>(R.id.editTextText4) as TextView

        val btn_getFile = findViewById<Button>(R.id.btn_getFile) as Button
        val btn_getAllFile = findViewById<Button>(R.id.btn_getAllFiles) as Button
        val btn_createDir = findViewById<Button>(R.id.btn_createDir) as Button

        val btn_writeFile = findViewById<Button>(R.id.btn_writeFile) as Button
        val btn_readFile = findViewById<Button>(R.id.btn_readFile) as Button


        btn_getFile.setOnClickListener{
            val file = applicationContext.filesDir
            textView.setText("getFilesDir() +" + file)
        }

        btn_writeFile.setOnClickListener{
            val filename = editText1.text.toString()
            val fileContent = editText2.text.toString()
            applicationContext.openFileOutput(filename, Context.MODE_PRIVATE).use{
                it.write(fileContent.toByteArray())
            }
            Toast.makeText(this,"Valor " + fileContent + " escrito no arquivo " + filename,Toast.LENGTH_SHORT).show()
        }

        btn_readFile.setOnClickListener {
            var value: String = ""
            val filename = editText1.text.toString()

            applicationContext.openFileInput(filename).bufferedReader().useLines { lines ->
                value = lines.fold(""){ some, text ->
                    "$some\n$text"
                }.toString()

            }
            textView.setText("getFilesDir() +" + value)
        }

        btn_getAllFile.setOnClickListener {
            val files: Array<String> = this.fileList()
            var filesNames: String = ""
            for(file in files){
                filesNames += "file:" + file + "\n"
            }
            textView.setText(filesNames)
        }

        btn_createDir.setOnClickListener {
            val dirName = editText1.text.toString()
            val dir = applicationContext.getDir(dirName, Context.MODE_PRIVATE)
            textView.setText("dir: +" + dirName)
            Toast.makeText(this,"dir: +" + dirName ,Toast.LENGTH_SHORT).show()
        }

    }
}