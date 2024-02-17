package com.test.todoapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.todoapp.MainActivity
import com.test.todoapp.R

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)


        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            navigateToHomeScreen()
        }


    }




    private fun navigateToHomeScreen(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("test", "testContent")
        startActivity(intent)
    }
}