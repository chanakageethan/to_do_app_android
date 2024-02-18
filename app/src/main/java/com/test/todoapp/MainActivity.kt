package com.test.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.test.todoapp.databinding.ActivityMainBinding
import com.test.todoapp.ui.notes.AddNote

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }



//    fun onClick(v: View){
//        if(v.id == R.id.addNote){
//            navigateToAddNotesScreen()
//        }
//    }
//    private fun navigateToAddNotesScreen(){
//        val intent = Intent(this, AddNote::class.java)
//        intent.putExtra("test", "testContent")
//        startActivity(intent)
//    }
}