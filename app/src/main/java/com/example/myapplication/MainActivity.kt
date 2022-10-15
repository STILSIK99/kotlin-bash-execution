package com.example.myapplication

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun save(view : View){
        checkBash(edCommand.text.toString())
    }

    fun checkBash(command : String){
        edText.text.clear()
        try {
            val process = Runtime.getRuntime().exec(command)
            val reader = BufferedReader(
                InputStreamReader(process.inputStream)
            )
            var read: Int
            val buffer = CharArray(4096)
            val output = StringBuffer()
            do {
                read = reader.read(buffer)
                if (read <= 0) break
                output.append(buffer, 0, read)
            } while(true)
            reader.close()
            // Waits for the command to finish.
            process.waitFor()
            edText.append(output.toString() ?: "Empty")
        } catch (e: IOException) {
            Toast.makeText(this, "Невыполнимая команда.", Toast.LENGTH_SHORT).show()
        } catch (e: InterruptedException) {
            Toast.makeText(this, "Невыполнимая команда.", Toast.LENGTH_SHORT).show()
        }
    }

    fun reset(view : View){
        edText.text.clear()
        edCommand.text.clear()
    }


}